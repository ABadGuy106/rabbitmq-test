package net.abadguy.workmq.recv;

import com.rabbitmq.client.*;
import net.abadguy.rabbitmq.util.ConnectionUtile;

import java.io.IOException;

public class Recv {
    private static final String QUEUE_NAME="test_work_queue";
    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection= ConnectionUtile.getConnection();

        Channel channel=connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //定义一个消费者
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String msg=new String(body,"utf-8");

                System.out.println("消费者【1】收到的消息:"+msg);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("消费者【1】接收完毕");
                }
            }
        };

        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
