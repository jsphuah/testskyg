/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.test;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Class for the test that you want to send a message to the MQ.
 *
 * @author sisi r.iwaki
 */
public class Send {

    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(Send.class);

    /** String key when you go to pick up the message queue. */
    private static final String QUEUE_NAME = "hello";

    /**
     * method main.
     *
     * @param args String at run time, the specified
     * @throws IOException can not see the MQ server
     */
    public static void main(
        String[] args)
        throws IOException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        for (int idx = 0; idx < 1; idx++) {
            String message = "0 1{\"clientId\": \"iwaki\",    \"requestId\": \"ryosuke\",    \"bussines\":{ \"date\":\"20121201\",\"dayNum\":\"10\"} }";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            logger.info("Send#main [x] Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }
}
