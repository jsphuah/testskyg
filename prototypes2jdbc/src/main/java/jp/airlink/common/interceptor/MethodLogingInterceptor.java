/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.common.interceptor;

import jp.airlink.common.util.CommonUtil;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

/**
 * Interceptor time required to output the start method, for each end.
 *
 * @author SIS
 */
public class MethodLogingInterceptor extends AbstractInterceptor {

    /** serialized number. */
    private static final long serialVersionUID = 1L;
    /** Logger definition. */
    private static Logger logger = Logger.getLogger(MethodLogingInterceptor.class);


    @Override
    public Object invoke(
        MethodInvocation arg0)
        throws Throwable {


        StringBuffer buf = new StringBuffer();

        String clsNm = arg0.getClass().getName();
        String pkgNm = arg0.getClass().getPackage().getName() + ".";

        int nmIdx = clsNm.indexOf("$$");

        if (nmIdx == -1) {
            clsNm = clsNm.substring(pkgNm.length());
        } else {
            clsNm = clsNm.substring(pkgNm.length(), nmIdx);
        }
        buf.append(clsNm);
        buf.append("#").append(arg0.getMethod().getName()).append("(");
        buf.append(")");

        logger.info("###### [" + buf.toString() + "] : START " + "(" + CommonUtil.getSystemDate("HH:mm:ss") + ") ######");
        long mStart = System.currentTimeMillis();
        Object ret = arg0.proceed();
        long mEnd = System.currentTimeMillis();
        logger.info("###### [" + buf.toString() + "] : END " + "(" + CommonUtil.getSystemDate("HH:mm:ss") + ") Processing time [" + (mEnd - mStart) + "] ms ######");

        return ret;
    }

}
