/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import jp.airlink.test.MessageQueueReceiverThread;

import org.apache.log4j.Logger;

/**
 * Search for start servlet thread message queue.
 *
 * @author sis r.iwaki
 */
public class MessageQueueDaemonStartServlet extends HttpServlet {

    /** serialized number. */
    private static final long serialVersionUID = 1L;

    /** logger definition. */
    private static final Logger logger = Logger.getLogger(MessageQueueDaemonStartServlet.class);

    /** {@link MessageQueueReceiverThread}. */
    private MessageQueueReceiverThread queueReceiverThread = null;

    @Override
    public final void init(
        ServletConfig config)
        throws ServletException {

        logger.info("MQ Receive Thread Starting Start");

        queueReceiverThread = new MessageQueueReceiverThread();
        queueReceiverThread.start();

        logger.info("MQ Receive Thread Starting End");
    }

    @Override
    public void destroy() {
        logger.info("MQ Receive Thread Stopped Start");
        if (queueReceiverThread.isAlive()) {
            try {
                queueReceiverThread.interrupt();
            } catch (Exception e) {
                logger.warn("MQ Receive Thread Stopped Error [" + e.toString() + "]");
            }
        }
        queueReceiverThread = null;
        logger.info("MQ Receive Thread Stopped End");
    }

}
