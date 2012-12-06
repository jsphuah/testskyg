/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.service.entity.output;

/**
 * Entity for the item response service.
 *
 * @author sis r.iwaki
 */
public class ServiceResponseEntity {

    /** Whether treatment success. */
    private String status = null;
    /** {@link ServiceErrorInfoEntity}. */
    private ServiceErrorInfoEntity errorInfo = null;
    /** ID that uniquely identifies the service. */
    private String requestId = null;
    /** ID that uniquely identifies the calling client service. */
    private String clientId = null;
    /**
     * get the status.
     * @return status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status Set the status.
     */
    public void setStatus(
        String status) {
        this.status = status;
    }
    /**
     * get the errorInfo.
     * @return errorInfo
     */
    public ServiceErrorInfoEntity getErrorInfo() {
        return errorInfo;
    }
    /**
     * @param errorInfo Set the errorInfo.
     */
    public void setErrorInfo(
        ServiceErrorInfoEntity errorInfo) {
        this.errorInfo = errorInfo;
    }
    /**
     * get the requestId.
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
