/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.dxo.impl;

import jp.airlink.common.util.CommonUtil;
import jp.airlink.dxo.ServiceEntityDxoIF;
import jp.airlink.service.entity.input.ServiceRequestEntity;
import jp.airlink.service.entity.output.ServiceResponseEntity;

import org.apache.log4j.Logger;

/**
 * Abstract class to convert JSON string to an entity dedicated to the service you have requested.
 *
 * @author sis r.iwaki
 */
public abstract class ServiceEntityDxoAbst implements ServiceEntityDxoIF {

    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(ServiceEntityDxoAbst.class);

    @Override
    public ServiceRequestEntity decode(
        String rqStr,
        String serviceName) {

        if (CommonUtil.isEmpty(rqStr)) {
            logger.info("There is no request string. I, the process is terminated.");
            return null;
        }

        Class<?> target = null;

        StringBuilder clazzName = new StringBuilder("jp.airlink.service.entity.input.ServiceRequest");
        clazzName.append(serviceName);
        clazzName.append("Entity");

        try {
            target = Class.forName(clazzName.toString());
        } catch (ClassNotFoundException classNotFoundException) {
            target = null;
            logger.info("Class not found to be converted [" + clazzName.toString() + "] I, the process is terminated.");
            return null;
        }

        Object rtn = exchangeToObj(rqStr, target);

        return (ServiceRequestEntity) rtn;
    }

    /**
     * convert any object to the target string.
     *
     * @param rqStr String passed to the service
     * @param clazz Class to be converted
     * @return From any format, object decoded into bean
     */
    protected abstract Object exchangeToObj(
        String rqStr,
        Class<?> clazz);

    @Override
    public String encode(
        ServiceResponseEntity rsObj) {
        if (rsObj == null) {
            logger.info("There is no response object. I, the process is terminated.");
            return null;
        }

        return exchangeToStr(rsObj);

    }

    /**
     * converted to a string for any object.
     *
     * @param rsObj Object returned by the service
     * @return from bean, objects encoded in any format
     */
    protected abstract String exchangeToStr(
        ServiceResponseEntity rsObj);

}
