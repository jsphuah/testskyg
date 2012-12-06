/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.control;

import jp.airlink.control.impl.ServiceControlerParameter;

/**
 * Class that controls the web services.
 *
 * @author sis r.iwaki
 */
public interface ServiceControlerIF {

    /** String constant that indicates the "NG" result of the service processing. */
    String SERVICE_PROC_CODE_NG = "0";
    /** String constant that indicates the "OK" service processing results. */
    String SERVICE_PROC_CODE_OK = "1";
    /** String constant that indicates the request format "XML" to the service. */
    String TO_SERVICE_STRING_FORMAT_XML = "xml";

    /**
     * Based on the request string, performs the execution of the service.
     *
     * @param parameterObject String passed to the service
     * @return Processing result code
     */
    int proc(
        ServiceControlerParameter parameterObject);

    /**
     * service returns a string result.
     *
     * @return String result Service
     */
    String getResponse();

}
