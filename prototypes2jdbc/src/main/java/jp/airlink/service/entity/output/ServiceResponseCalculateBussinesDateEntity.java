/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.service.entity.output;

/**
 * {@link jp.airlink.service.impl.ServiceBusinessDateCalculation} Output Entity.
 *
 * @author sis r.iwaki
 */
public class ServiceResponseCalculateBussinesDateEntity extends ServiceResponseEntity {

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
