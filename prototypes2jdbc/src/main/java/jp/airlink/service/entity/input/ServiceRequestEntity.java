/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.service.entity.input;

/**
 * Common entities service request.
 *
 * @author sis r.iwaki
 */
public class ServiceRequestEntity {

    /** ID that uniquely identifies the service. */
    private String requestId = null;
    /** ID that uniquely identifies the calling client service. */
    private String clientId = null;

    /**
     * get the requestId.
     *
     * @return requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * @param requestId Set the requestId.
     */
    public void setRequestId(
        String requestId) {
        this.requestId = requestId;
    }

    /**
     * get the clientId.
     *
     * @return clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId Set the clientId.
     */
    public void setClientId(
        String clientId) {
        this.clientId = clientId;
    }

}
