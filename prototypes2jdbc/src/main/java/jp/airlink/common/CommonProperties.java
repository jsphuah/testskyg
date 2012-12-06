/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.common;

import org.apache.log4j.Logger;

/**
 * Class to read the file, create a Properties.
 *
 * @author SIS
 */
public class CommonProperties {

    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(CommonProperties.class);

    /**
     * Based on a string key to obtain the corresponding value.
     *
     * @param key String key that corresponds to the value you want
     * @return If there is no string corresponding to the key, it returns an empty string instead of null
     */
    public static String get(
        String key) {
        final String methodName = "getEnv(key)";

        String ret = SystemProperties.getEnv(key);

        if ((ret == null) || ret.isEmpty()) {
            ret = EnvDependenceProperties.get(key);
        }

        if ((ret == null) || ret.isEmpty()) {
            logger.warn(methodName + " value is null at key[" + key + "]");
        }

        return ret;
    }
}
