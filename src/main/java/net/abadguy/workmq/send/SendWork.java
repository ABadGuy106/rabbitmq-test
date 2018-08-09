package net.abadguy.workmq.send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import net.abadguy.rabbitmq.util.ConnectionUtile;

public class SendWork {
    private static final String QUEUE_NAME="test_work_queue";
    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection= ConnectionUtile.getConnection();
        //获取channel
        Channel channel=connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        for(int i=0;i<50;i++){
            String msg="发送消息"+i;

            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            Thread.sleep(100);
        }
        channel.close();
        connection.close();
    }
}
