/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.web.servlet;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import jp.airlink.common.util.EnvUtil;

import org.apache.log4j.AsyncAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.NullAppender;

/**
 * Servlet for initialization whatnot.
 *
 * @author sis r.iwaki
 */
public class InitializeServlet extends HttpServlet {

    /** serialized number. */
    private static final long serialVersionUID = 1L;

    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(InitializeServlet.class);

    @Override
    public final void init(
        ServletConfig config)
        throws ServletException {

        try {

            String logBaseDir = config.getInitParameter("logdir");

            Logger gwLogger = LogManager.getLogger("jp.airlink");
            AsyncAppender async = (AsyncAppender) gwLogger.getAppender("FILE");
            NullAppender gwApp = (NullAppender) async.getAppender("FILE-FOR-ASYNC");
            FileAppender gwFileApp = new FileAppender(gwApp.getLayout(), logBaseDir + "/application.log");
            Enumeration<?> e = LogManager.getCurrentLoggers();
            while (e.hasMoreElements()) {
                Logger one = (Logger) e.nextElement();
                if (one.getAppender("FILE") != null) {
                    LogManager.getLogger(one.getName()).addAppender(gwFileApp);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean isDevMode = true;

        try {
            isDevMode = EnvUtil.isDevelopMode();
        } catch (Exception t) {
            throw new ServletException();
        }

//        System.out.println("Locale#getDefault()"  + Locale.getDefault());
//        Locale newLocale = new Locale("en_US");
//        Locale.setDefault(newLocale);


        try {

            // システムプロパティ(非環境依存系)
            StringBuilder sFileNm = new StringBuilder("/system.properties");
            URL sysPropResourceName = getClass().getResource(sFileNm.toString());
            logger.info("Instantiated as a system property [" + sysPropResourceName.getPath() + "]");
            String[] sysPropArg = { sysPropResourceName.getPath()
            };
            jp.airlink.common.SystemProperties.main(sysPropArg);

            // エンブプロパティ(環境依存系)
            StringBuilder eFileNm = new StringBuilder("/env");

            String addStr = ".properties";

            if (isDevMode) {
                addStr = "_develop.properties";
            }
            eFileNm.append(addStr);

            URL envPropResourceName = getClass().getResource(eFileNm.toString());
            logger.info("Instantiated as an environment-dependent property [" + envPropResourceName.getPath() + "]");

            String[] envPropArg = { envPropResourceName.getPath()
            };
            jp.airlink.common.EnvDependenceProperties.main(envPropArg);

        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

}
