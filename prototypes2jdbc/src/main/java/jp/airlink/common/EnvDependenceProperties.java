/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.common;

import java.util.Properties;

import jp.airlink.common.constants.Const;

import org.apache.log4j.Logger;

/**
 * Class to read the file, create the environment-dependent Properties.
 *
 * @author SIS
 */
public class EnvDependenceProperties {

    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(EnvDependenceProperties.class);

    /** Property class. */
    private static CProperties com;

    /** objects java.util.Propertes. */
    private static Properties common;

    /**
     * Main method.
     *
     * <pre>
     * Based on the name of the file that was passed to hold the keys and values ​​to the member variables of the class itself
     * </pre>
     *
     * @param args Read file name array of string (absolute path)
     * @throws Exception If the file does not exist, if an unexpected exception occurred
     */
    public static void main(
        String[] args)
        throws Exception {
        com = new CProperties(args[0]);
        common = com.get();
    }

    /**
     * Based on a string key to obtain the corresponding value.
     *
     * @param key String key that corresponds to the value you want
     * @return String corresponding to the key
     */
    private static String getEnv(
        String key) {
        if (common == null) {
            logger.trace("[EnvDependenceProperties.getEnv] common is null at key[" + key + "]");

            return Const.EMPTY_STR;
        } else if (key == null) {
            logger.trace("[EnvDependenceProperties.getEnv] key is null");

            return Const.EMPTY_STR;
        } else if (key.equals(Const.EMPTY_STR)) {
            logger.trace("[EnvDependenceProperties.getEnv] key is empty");

            return Const.EMPTY_STR;
        }
        Object tmp = common.get(key);

        if (tmp == null) {
            logger.trace("[EnvDependenceProperties.getEnv] value is null at key[" + key + "]");

            return Const.EMPTY_STR;
        } else if (!(tmp instanceof String)) {
            logger.trace("[EnvDependenceProperties.getEnv] value is " + tmp.getClass().getName() + " at key[" + key + "]");

            return Const.EMPTY_STR;
        }

        return tmp.toString();
    }

    /**
     * Method for reloading.
     *
     * @throws Exception If an unexpected exception occurred
     */
    public static synchronized void reload()
        throws Exception {
        com.reload();
        common = com.get();
    }

    /**
     * Method for reloading.
     *
     * @param path String name of the file to be re-read (absolute path)
     * @throws Exception If an unexpected exception occurred
     */
    public static synchronized void reload(
        String path)
        throws Exception {
        com.reload(path);
        common = com.get();
    }

    /**
     * Pass a string key, returns a string containing the value of the corresponding instance after it has been read from the properties file.
     *
     * @param key String key that corresponds to the value
     * @return If there is no corresponding value that corresponds to the key string, returns an empty string instead of null
     */
    static String get(
        String key) {
        return getEnv(key);
    }
}
