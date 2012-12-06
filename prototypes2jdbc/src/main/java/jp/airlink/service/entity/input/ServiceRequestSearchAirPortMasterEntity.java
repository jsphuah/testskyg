/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.service.entity.input;

/**
 * {@link jp.airlink.service.impl.ServiceSearchAirPortMaster} Input Entity.
 *
 * @author sis r.iwaki
 */
public class ServiceRequestSearchAirPortMasterEntity extends ServiceRequestEntity {

    /** {@link AirPortEntity}. */
    private AirPortEntity airPort = null;

    /**
     * get the airPort.
     *
     * @return airPort
     */
    public AirPortEntity getAirPort() {
        return airPort;
    }

    /**
     * @param airPort Set the airPort.
     */
    public void setAirPort(
        AirPortEntity airPort) {
        this.airPort = airPort;
    }

}
