package net.abadguy.newre;

import com.rabbitmq.client.*;
import net.abadguy.rabbitmq.util.ConnectionUtile;

import java.io.IOException;

public class Recv {

    private static final String QUEUE_NAME="test_simple_queue";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection= ConnectionUtile.getConnection();
        //从连接获取一个通道
       Channel channel=connection.createChannel();
        //队列申明
       channel.queueDeclare(QUEUE_NAME,false,false,false,null);

       DefaultConsumer consumer=new DefaultConsumer(channel){
           @Override
           public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
               super.handleDelivery(consumerTag, envelope, properties, body);
               String newStr=new String(body,"utf-8");
               System.out.println(newStr);
           }
       };

       //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);

    }
}
