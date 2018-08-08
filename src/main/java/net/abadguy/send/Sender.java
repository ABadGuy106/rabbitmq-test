package net.abadguy.send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import net.abadguy.rabbitmq.util.ConnectionUtile;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {

    private static final String QUEUE_NAME="test_simple_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection= ConnectionUtile.getConnection();
        //从连接获取一个通道
        Channel channel=null;
        try {
            channel=connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建队列申明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String msg="hello simple!";

        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());

        channel.close();
        connection.close();



    }
}
