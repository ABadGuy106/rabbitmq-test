package net.abadguy.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtile {

    /**
     * 获取mq的连接
     * @return
     */
    public static Connection getConnection(){
        Connection connection=null;
        //定义一个连接工厂
        ConnectionFactory connectionFactory=new ConnectionFactory();
        //设置服务地址
        connectionFactory.setHost("118.24.166.176");
        //设置端口号
        connectionFactory.setPort(5672);
        //vhost
        connectionFactory.setVirtualHost("/abadguy");
        //用户名
        connectionFactory.setUsername("abadguy");
        //用户密码
        connectionFactory.setPassword("abadguy");

        try {
            connection=connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();

        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
