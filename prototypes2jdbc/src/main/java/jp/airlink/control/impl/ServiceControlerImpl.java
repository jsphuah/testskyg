/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.control.impl;

import jp.airlink.common.constants.Const;
import jp.airlink.control.ServiceControlerIF;
import jp.airlink.dxo.ServiceEntityDxoIF;
import jp.airlink.dxo.impl.ServiceEntityDxoJsonImpl;
import jp.airlink.dxo.impl.ServiceEntityDxoXmlImpl;
import jp.airlink.service.ServiceMainIF;
import jp.airlink.service.entity.input.ServiceRequestEntity;
import jp.airlink.service.entity.output.ServiceErrorInfoEntity;
import jp.airlink.service.entity.output.ServiceResponseEntity;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * Implementation class that controls a common pre-processing and post-processing of web services.
 *
 * @author sis r.iwaki
 */
public class ServiceControlerImpl implements ServiceControlerIF {

    /** If an exception occurs during the service control, the number of levels to trace. */
    private static final int TRACE_LEVEL_COUNT = 5;

    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(ServiceControlerImpl.class);

    /** Response string for the service. */
    private String svRsStr = null;

    @Override
    public int proc(
        ServiceControlerParameter svCtrlParam) {

        String status = SERVICE_PROC_CODE_NG;
        String clientId = Const.EMPTY_STR;
        String requestId = Const.EMPTY_STR;
        ServiceRequestEntity svRq = null;
        ServiceResponseEntity svRs = null;
        ServiceErrorInfoEntity svErrInfo = null;
        // リクエスト、レスポンスの相互変換用クラス
        ServiceEntityDxoIF dxo = null;
        try {

            dxo = null;

            // フォーマット"XML"指定の場合、変換クラスを変更する
            if (TO_SERVICE_STRING_FORMAT_XML.equals(svCtrlParam.getFormat())) {
                dxo = new ServiceEntityDxoXmlImpl();
            }

            // 指定がない場合はJSONとみなす
            if (dxo == null) {
                dxo = new ServiceEntityDxoJsonImpl();
            }

            long start = System.currentTimeMillis();
            svRq = dxo.decode(svCtrlParam.getRequest(), svCtrlParam.getServiceName());
            logger.debug("request string convert any bean [" + (System.currentTimeMillis() - start) + "] ms");

            if (svRq == null) {
                logger.info("failed to convert the request object");
                return 1;
            }

            clientId = svRq.getClientId();
            requestId = svRq.getRequestId();

            // validation

            // サービス呼び出し
            ServiceMainIF service = (ServiceMainIF) SingletonS2ContainerFactory.getContainer().getComponent("service-" + svCtrlParam.getServiceName());

            // log4Jスレッドローカル値に値を埋め込み
            StringBuilder ndc = new StringBuilder(service.getServiceCode());
            ndc.append(Const.DELIMITA_HYPHEN).append(svRq.getClientId());
            ndc.append(Const.DELIMITA_HYPHEN).append(svRq.getRequestId());
            // クライアントID + リクエストID + サービス名
            NDC.push(ndc.toString());

            // サービス実行
            if (service.proc(svRq) != 0) {
                logger.info("Processing services, the failure");
                svErrInfo = service.getErrorInfo();
                return 1;
            }

            svRs = service.getResponse();
            status = SERVICE_PROC_CODE_OK;

        } catch (Exception | Error t) {
            StringBuilder detail = new StringBuilder(t.getClass().getSimpleName());
            StackTraceElement[] trses = t.getStackTrace();

            if (trses != null && trses.length > 0) {
                int level = TRACE_LEVEL_COUNT;

                for (int idx = 0; idx < level; idx++) {
                    detail.append("  ");
                    StackTraceElement trs = trses[idx];
                    detail.append(trs.toString());
                }
            }
            logger.error("web servises runtime error [" + t.toString() + "] detail [" + detail.toString() + "]");

            if (logger.isDebugEnabled()) {
                t.printStackTrace();
            }

        } finally {
            if (svRs == null) {
                svRs = new ServiceResponseEntity();
            }
            svRs.setStatus(status);
            svRs.setClientId(clientId);
            svRs.setRequestId(requestId);
            svRs.setErrorInfo(svErrInfo);
            long start = System.currentTimeMillis();
            svRsStr = dxo.encode(svRs);
            svRsStr = svRsStr.replace("Entity", Const.EMPTY_STR);
            logger.debug("bean convert any response string [" + (System.currentTimeMillis() - start) + "] ms");

            // スレッドローカル値を初期化
            NDC.remove();
        }

        return 0;
    }

    @Override
    public String getResponse() {
        return svRsStr;
    }

}
