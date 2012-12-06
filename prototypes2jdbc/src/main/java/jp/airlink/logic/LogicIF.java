/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.logic;

/**
 * Interface for logic class.
 *
 * @author sis r.iwaki
 */
public interface LogicIF {

    /**
     * Process execution logic.
     *
     * @param input Parameters for the service class
     * @return Code indicating the success or failure of treatment
     */
    int proc(
        Object input);

    /**
     * return the result class.
     *
     * @return Class results
     */
    Object getResult();

}
