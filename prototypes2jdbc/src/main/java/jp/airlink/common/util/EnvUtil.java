/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.common.util;

import org.apache.log4j.Logger;

/**
 * Class set of utility methods environment system.
 *
 * @author SIS
 */
public class EnvUtil {

    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(EnvUtil.class);

    /**
     * Returns TRUE or FALSE based on whether or not the development machine host name.
     *
     * @return Whether development machine
     * @throws Exception When tomcat is started on the host name is not in the register
     */
    public static boolean isDevelopMode()
        throws Exception {

        final String methodName = "isDevelopHosts()";

        boolean devMode = true;
        String devModeStr = System.getenv("DEV_MODE");
        logger.debug(methodName + " : devModeStr [" + devModeStr + "]");
        if (!CommonUtil.isEmpty(devModeStr)) {
            devMode = new Boolean(System.getenv("DEV_MODE")).booleanValue();
        }
        logger.info(methodName + " : dev_mode is .. [" + devMode + "]");
        return devMode;

    }
}
