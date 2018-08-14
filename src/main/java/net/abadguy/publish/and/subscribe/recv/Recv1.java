package net.abadguy.publish.and.subscribe.recv;

import com.rabbitmq.client.*;
import net.abadguy.rabbitmq.util.ConnectionUtile;

import java.io.IOException;

public class Recv1 {

    private static final String QUEUE_NAME="test_queue_fanout_email";
    private static final String EXCHANGE_NAME="test_exchange_fanout";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtile.getConnection();
        Channel channel = connection.createChannel();


        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);

                String msg=new String(body,"utf-8");
                System.out.println("Recv1 收到的信息"+msg);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("Recv1 完成接受消息");

                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };

        boolean autoAck=false;
        channel.basicConsume(QUEUE_NAME,autoAck,consumer);


    }
}
