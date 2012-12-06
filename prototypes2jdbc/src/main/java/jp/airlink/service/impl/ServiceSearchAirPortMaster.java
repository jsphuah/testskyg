/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import jp.airlink.logic.LogicIF;
import jp.airlink.logic.entity.LogicSearchAirPortMasterInputEntity;
import jp.airlink.logic.entity.LogicSearchAirPortMasterOutputEntity;
import jp.airlink.logic.impl.LogicSearchAirPortMaster;
import jp.airlink.service.entity.input.AirPortEntity;
import jp.airlink.service.entity.input.ServiceRequestEntity;
import jp.airlink.service.entity.input.ServiceRequestSearchAirPortMasterEntity;
import jp.airlink.service.entity.output.ServiceResponseEntity;
import jp.airlink.service.entity.output.ServiceResponseSearchAirPortMasterEntity;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * Class of service based on the parameters, locate the master airport, and returns the result.
 *
 * @author sis r.iwaki
 */
public class ServiceSearchAirPortMaster extends ServiceMain {

    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(ServiceSearchAirPortMaster.class);

    @Override
    public String getServiceCode() {
        return "OTHER_03";
    }

    @Override
    public String getServiceName() {
        return "SearchAirPortMaster";
    }

    @Override
    protected ServiceResponseEntity eachProc(
        ServiceRequestEntity svRq) {

        ServiceRequestSearchAirPortMasterEntity mySvRq = (ServiceRequestSearchAirPortMasterEntity) svRq;
        AirPortEntity bussines = mySvRq.getAirPort();

        LogicSearchAirPortMasterInputEntity input = new LogicSearchAirPortMasterInputEntity();
        input.setAptCd(bussines.getAptCd());

        LogicIF busLogic = (LogicIF) SingletonS2ContainerFactory.getContainer().getComponent(LogicSearchAirPortMaster.class);

        int rslt = busLogic.proc(input);

        logger.debug(" svRq.ClientId [" + svRq.getClientId() + "] svRq.RequestId [" + svRq.getRequestId() + "] rslt [" + rslt + "] ");

        LogicSearchAirPortMasterOutputEntity result = (LogicSearchAirPortMasterOutputEntity) busLogic.getResult();

        jp.airlink.service.entity.output.AirPortEntity rsAp = new jp.airlink.service.entity.output.AirPortEntity();

        Field[] fields = rsAp.getClass().getDeclaredFields();

        for (int m = 0; m < fields.length; m++) {
            Field field = fields[m];
            try {
                String ovOneStr = BeanUtils.getProperty(result, field.getName());
                BeanUtils.setProperty(rsAp, field.getName(), (Object) ovOneStr);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                logger.debug("bean replace exception [" + e.toString() + "] msg [" + e.getMessage() + "]");
                continue;
            }
        }
        ServiceResponseSearchAirPortMasterEntity svRs = new ServiceResponseSearchAirPortMasterEntity();
        svRs.setAirPort(rsAp);

        return svRs;
    }

    @Override
    protected boolean check(
        ServiceRequestEntity svRq) {

        ServiceRequestSearchAirPortMasterEntity myRqObj = (ServiceRequestSearchAirPortMasterEntity) svRq;
        AirPortEntity apRqObj = myRqObj.getAirPort();

        if (apRqObj.getAptCd() == null || "".equals(apRqObj.getAptCd())) {
            logger.info("code value [" + apRqObj.getAptCd() + "] is illegality");
            return false;
        }

        return true;
    }

}
