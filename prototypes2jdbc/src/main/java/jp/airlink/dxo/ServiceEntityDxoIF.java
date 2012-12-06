/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.dxo;

import jp.airlink.service.entity.input.ServiceRequestEntity;
import jp.airlink.service.entity.output.ServiceResponseEntity;

/**
 * Interface entity dedicated to convert string requested service.
 *
 * @author sis r.iwaki
 */
public interface ServiceEntityDxoIF {

    /**
     * convert any object to the target string.
     *
     * @param rqStr String passed to the service
     * @param serviceName Name Service
     * @return From any format, object decoded into bean
     */
    ServiceRequestEntity decode(
        String rqStr,
        String serviceName);

    /**
     * converted to a string for any object.
     *
     * @param rsObj Object returned by the service
     * @return From bean, any object that was encoded in a format
     */
    String encode(
        ServiceResponseEntity rsObj);

}
