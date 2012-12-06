/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.service.impl;

import jp.airlink.common.util.CommonUtil;
import jp.airlink.logic.LogicIF;
import jp.airlink.logic.entity.LogicBussinesInputEntity;
import jp.airlink.logic.entity.LogicBussinesOutputEntity;
import jp.airlink.logic.impl.LogicBusinessDateCalculator;
import jp.airlink.service.entity.input.BussinesEntity;
import jp.airlink.service.entity.input.ServiceRequestCalculateBussinesDateEntity;
import jp.airlink.service.entity.input.ServiceRequestEntity;
import jp.airlink.service.entity.output.ServiceResponseCalculateBussinesDateEntity;
import jp.airlink.service.entity.output.ServiceResponseEntity;

import org.apache.log4j.Logger;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * Based on the parameter, the service class to calculate the number of business days specified date.
 *
 * @author sis r.iwaki
 */
public class ServiceBusinessDateCalculation extends ServiceMain {

    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(ServiceBusinessDateCalculation.class);

    @Override
    public String getServiceCode() {
        return "OTHER_01";
    }

    @Override
    public String getServiceName() {
        return "CalculateBussinesDate";
    }

    @Override
    protected ServiceResponseEntity eachProc(
        ServiceRequestEntity svRq) {

        ServiceRequestCalculateBussinesDateEntity mySvRq = (ServiceRequestCalculateBussinesDateEntity) svRq;
        BussinesEntity bussines = mySvRq.getBussines();

        LogicBussinesInputEntity input = new LogicBussinesInputEntity();
        input.setDate(bussines.getDate());
        input.setDayNum(bussines.getDayNum());
        input.setPast(bussines.getPast());
        input.setToday(bussines.getToday());

        LogicIF busLogic = (LogicIF) SingletonS2ContainerFactory.getContainer().getComponent(LogicBusinessDateCalculator.class);

        int rslt = busLogic.proc(input);

        logger.debug(" svRq.ClientId [" + svRq.getClientId() + "] svRq.RequestId [" + svRq.getRequestId() + "] rslt [" + rslt + "] ");

        LogicBussinesOutputEntity result = (LogicBussinesOutputEntity) busLogic.getResult();

        jp.airlink.service.entity.output.BussinesEntity rsBus = new jp.airlink.service.entity.output.BussinesEntity();
        rsBus.setDate(result.getDate());
        ServiceResponseCalculateBussinesDateEntity svRs = new ServiceResponseCalculateBussinesDateEntity();
        svRs.setBussines(rsBus);

        return svRs;
    }

    @Override
    protected boolean check(
        ServiceRequestEntity svRq) {

        ServiceRequestCalculateBussinesDateEntity myRqObj = (ServiceRequestCalculateBussinesDateEntity) svRq;
        BussinesEntity busRqObj = myRqObj.getBussines();

        if (busRqObj.getDate() == null || "".equals(busRqObj.getDate())) {
            logger.info("There is no Date. I, the process is terminated");
            return false;
        }

        if (!CommonUtil.checkNumber(busRqObj.getDayNum())) {
            logger.info("There is no Day_num. I, the process is terminated");
            return false;
        }

        return true;
    }

}
