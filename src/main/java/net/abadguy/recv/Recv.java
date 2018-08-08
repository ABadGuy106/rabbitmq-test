package net.abadguy.recv;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import net.abadguy.rabbitmq.util.ConnectionUtile;

import java.io.IOException;

public class Recv {

    private static final String QUEUE_NAME="test_simple_queue";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection= ConnectionUtile.getConnection();
        //从连接获取一个通道
        Channel channel=null;
        try {
            channel=connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
       //定义队列的消费端
        QueueingConsumer consumer=new QueueingConsumer(channel);
        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);
        while (true){
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String str=new String(delivery.getBody());
            System.out.println(str);
        }


    }
}
