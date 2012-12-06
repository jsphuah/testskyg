/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.service;

import jp.airlink.service.entity.input.ServiceRequestEntity;
import jp.airlink.service.entity.output.ServiceErrorInfoEntity;
import jp.airlink.service.entity.output.ServiceResponseEntity;

/**
 * Processing service interface.
 *
 * @author sis r.iwaki
 */
public interface ServiceMainIF {

    /**
     * Based on what has become the object string requested execution of the service.
     *
     * @param svRq Those into a string object that was requested
     * @return Processing result code
     */
    int proc(
        ServiceRequestEntity svRq);

    /**
     * return a result object services.
     *
     * @return Service object result
     */
    ServiceResponseEntity getResponse();

    /**
     * return error information.
     *
     * @return Name error information
     */
    ServiceErrorInfoEntity getErrorInfo();

    /**
     *eturn the service code.
     *
     * @return Service Code
     */
    String getServiceCode();

    /**
     * return the name of the service.
     *
     * @return Name Service
     */
    String getServiceName();

}
