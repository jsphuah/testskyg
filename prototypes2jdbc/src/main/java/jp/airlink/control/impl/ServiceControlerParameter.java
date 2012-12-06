/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.control.impl;

/**
 * {@link ServiceControlerImpl} Parameters only classes.
 *
 * @author sis r.iwaki
 */
public class ServiceControlerParameter {

    /** String requested service. */
    private String request = null;
    /** Specifies the format of the request string. */
    private String format = null;
    /** Service name. */
    private String serviceName = null;

    /**
     * Constructor.
     *
     * @param request String requested service
     * @param format Specifies the format of the request string
     * @param serviceName Service name
     */
    public ServiceControlerParameter(
                                     String request,
                                     String format,
                                     String serviceName) {
        this.request = request;
        this.format = format;
        this.serviceName = serviceName;
    }

    /**
     * get the request.
     *
     * @return request
     */
    public String getRequest() {
        return request;
    }

    /**
     * @param request Set the request.
     */
    public void setRequest(
        String request) {
        this.request = request;
    }

    /**
     * get the format.
     *
     * @return format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format Set the format.
     */
    public void setFormat(
        String format) {
        this.format = format;
    }

    /**
     * get the serviceName.
     *
     * @return serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName Set the serviceName.
     */
    public void setServiceName(
        String serviceName) {
        this.serviceName = serviceName;
    }

}
