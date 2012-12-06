/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.service.entity.output;

/**
 * Entity for the item response error.
 *
 * @author sis r.iwaki
 */
public class ServiceErrorInfoEntity {

    /** ID that uniquely identifies the error. */
    private String errorId = null;

    /** Error detail message. */
    private String errorMessage = null;

    /**
     * get the errorId.
     *
     * @return errorId
     */
    public String getErrorId() {
        return errorId;
    }

    /**
     * @param errorId Set the errorId.
     */
    public void setErrorId(
        String errorId) {
        this.errorId = errorId;
    }

    /**
     * get the errorMessage.
     *
     * @return errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage Set the errorMessage.
     */
    public void setErrorMessage(
        String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
