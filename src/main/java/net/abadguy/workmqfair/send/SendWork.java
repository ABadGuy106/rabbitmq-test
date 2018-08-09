package net.abadguy.workmqfair.send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import net.abadguy.rabbitmq.util.ConnectionUtile;

public class SendWork {
    private static final String QUEUE_NAME="test_work_queue1";
    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection= ConnectionUtile.getConnection();
        //获取channel
        Channel channel=connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        /**
         * 每个消费之发送确认消息之前，消息队列不发送下一个消息到消费者,
         * 一次只处理一个消息
         *
         * 限制发送给同一个消费者不得超过一条数据
         */
        int prefetchCount=1;
        channel.basicQos(1);

        for(int i=0;i<50;i++){
            String msg="发送消息"+i;

            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            Thread.sleep(100);
        }
        channel.close();
        connection.close();
    }
}
