/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.service.entity.input;

/**
 * {@link jp.airlink.service.impl.ServiceBusinessDateCalculation} Input Entity.
 *
 * @author sis r.iwaki
 */
public class ServiceRequestCalculateBussinesDateEntity extends ServiceRequestEntity {

    /** {@link BussinesEntity}. */
    private BussinesEntity bussines = null;

    /**
     * get the bussines.
     *
     * @return bussines
     */
    public BussinesEntity getBussines() {
        return bussines;
    }

    /**
     * @param bussines Set the bussines.
     */
    public void setBussines(
        BussinesEntity bussines) {
        this.bussines = bussines;
    }

}
