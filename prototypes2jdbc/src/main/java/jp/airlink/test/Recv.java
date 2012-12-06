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
import com.rabbitmq.client.QueueingConsumer;

/**
 * Class for the test that you want to retrieve messages from the MQ.
 *
 * @author sis r.iwaki
 */
public class Recv {

    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(Recv.class);

    /** String key when you go to pick up the message queue. */
    private static final String QUEUE_NAME = "hello";

    /**
     * main method.
     *
     * @param args String at run time, the specified
     * @throws IOException can not see the MQ server
     * @throws InterruptedException exception occurs in the operation thread
     */
    public static void main(
        String[] args)
        throws IOException,
        InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        logger.info("Recv#main [*] Waiting for messages. To exit press CTRL+C");
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);
        while (true) {
            try {
                final int waitTime = 5000;
                Thread.sleep(waitTime);
                logger.info("Recv#main receive message done.... \"" + waitTime + "\" seconds ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            logger.info("Recv#main [x] Received '" + message + "'");

        }
    }
}
