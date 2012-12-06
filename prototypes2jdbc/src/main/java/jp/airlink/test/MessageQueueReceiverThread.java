/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.test;

import jp.airlink.control.ServiceControlerIF;
import jp.airlink.control.impl.ServiceControlerParameter;

import org.apache.log4j.Logger;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Class to retrieve messages from the message queue, the service processing if found, sends a message to a queue and then the results.
 *
 * @author sis r.iwaki
 */
public class MessageQueueReceiverThread extends Thread {

    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(MessageQueueReceiverThread.class);

    /** キューからメッセージを取得する際のキー文字列. */
    private static final String QUEUE_NAME = "hello";

    @Override
    public void run() {

        try {

            ConnectionFactory factory = new ConnectionFactory();

            factory.setHost("192.168.116.199");
            factory.setPort(5672);
            factory.setUsername("dev0");
            factory.setPassword("dev0");
            factory.setVirtualHost("dev0");

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            logger.info("Recv#main [*] Waiting for messages. To exit press CTRL+C");
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(QUEUE_NAME, true, consumer);
            int waitTime = 1;
            while (true) {
                try {
                    Thread.sleep(waitTime);
                    logger.info("Recv#main receive message done.... \"" + waitTime + "\" seconds ");

                    waitTime = 5000;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                logger.info("Recv#main [x] Received '" + message + "'");

                // 圧縮形式
//                    String cmpWay = message.substring(0, 1);
                // リクエスト文字列のフォーマット
                String fmtWay = message.substring(1, 2);
                // サービスの名称
                String svcNm = message.substring(2, 3);
                // 暫定！
                svcNm = "CalculateBussinesDate";

                // 本体
                String rqStr = message.substring(3);

                /*
                 * 実際は、１バイト目を利用した解凍処理
                 */

                // コントローラ呼び出し
                ServiceControlerIF ctrlIF = (ServiceControlerIF) SingletonS2ContainerFactory.getContainer().getComponent("svCtrl");

                // 成功以外は例外
                if (0 != ctrlIF.proc(new ServiceControlerParameter(rqStr, fmtWay, svcNm))) {

                }

                logger.info("service returned string [" + ctrlIF.getResponse() + "]");

                /*
                 * 実際は、１バイト目を利用した圧縮処理を行い、キューに返す
                 */

            }

        } catch (Exception e) {
            logger.info("MQ proc is Error ", e);
        }
    }

}
