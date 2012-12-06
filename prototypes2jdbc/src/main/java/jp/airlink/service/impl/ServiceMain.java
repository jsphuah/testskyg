/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.service.impl;

import jp.airlink.service.ServiceMainIF;
import jp.airlink.service.entity.input.ServiceRequestEntity;
import jp.airlink.service.entity.output.ServiceErrorInfoEntity;
import jp.airlink.service.entity.output.ServiceResponseEntity;

import org.apache.log4j.Logger;

/**
 * Common parent class for each service.
 *
 * @author sis r.iwaki
 */
public abstract class ServiceMain implements ServiceMainIF {

    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(ServiceMain.class);

    /** {@link ServiceResponseEntity}. */
    private ServiceResponseEntity svRs = null;

    /** {@link ServiceErrorInfoEntity}. */
    private ServiceErrorInfoEntity svErrInfo = null;

    @Override
    public ServiceResponseEntity getResponse() {
        return svRs;
    }

    @Override
    public ServiceErrorInfoEntity getErrorInfo() {
        return svErrInfo;
    }

    @Override
    public int proc(
        ServiceRequestEntity svRq) {

        // 共通チェック
        if (svRq.getClientId() == null || "".equals(svRq.getClientId())) {
            logger.info("There is no clientId. I, the process is terminated");
            svErrInfo = new ServiceErrorInfoEntity();
            svErrInfo.setErrorId("ERROR_1");
            return 1;
        }
        if (svRq.getRequestId() == null || "".equals(svRq.getRequestId())) {
            logger.info("There is no requestId. I, the process is terminated");
            svErrInfo = new ServiceErrorInfoEntity();
            svErrInfo.setErrorId("ERROR_1");
            return 1;
        }
        // 固有チェック
        if (!check(svRq)) {
            logger.info("NG at specific checks. I, the process is terminated");
            svErrInfo = new ServiceErrorInfoEntity();
            svErrInfo.setErrorId("ERROR_1");
            return 1;
        }

        svRs = eachProc(svRq);

        return 0;
    }

    /**
     * Based on what has become the object string requested execution of each service-specific.
     *
     * @param svRq Those into a string object that was requested
     * @return Processing result code
     */
    protected abstract ServiceResponseEntity eachProc(
        ServiceRequestEntity svRq);

    /**
     * Based on what has become the object request string, each service-specific checks.
     *
     * @param svRq Those into a string object that was requested
     * @return Success or failure of the check results
     */
    protected boolean check(
        ServiceRequestEntity svRq) {

        logger.debug("Service-Code [" + getServiceCode() + "] チェック無し！");

        return true;
    }
}
