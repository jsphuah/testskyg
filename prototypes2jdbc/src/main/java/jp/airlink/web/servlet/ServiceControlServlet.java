/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.airlink.control.ServiceControlerIF;
import jp.airlink.control.impl.ServiceControlerParameter;

import org.apache.log4j.Logger;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * Servlet API service calls that trigger new app.
 *
 * @author sis r.iwaki
 */
public class ServiceControlServlet extends HttpServlet {

    /** serialized number. */
    private static final long serialVersionUID = 1L;
    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(ServiceControlServlet.class);

    @Override
    public void init()
        throws ServletException {
        super.init();
        logger.debug("ServiceControlServlet is initialized");
    }

    @Override
    protected void doPost(
        HttpServletRequest req,
        HttpServletResponse resp)
        throws ServletException,
        IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(
        HttpServletRequest req,
        HttpServletResponse resp)
        throws ServletException,
        IOException {
        logger.debug("ServiceControlServlet#doGet Start");

        PrintWriter out = null;

        // コントローラ呼び出し
        ServiceControlerIF ctrlIF = (ServiceControlerIF) SingletonS2ContainerFactory.getContainer().getComponent("svCtrl");

        String request = req.getParameter("param");
        String format = req.getParameter("format");
        String serviceName = req.getParameter("serviceName");

        // デフォルトをjson
        if (format == null) {
            format = "json";
        }

//        logger.info("session id is ... [" + req.getSession().getId() + "]");

        // 成功以外は例外
        if (0 != ctrlIF.proc(new ServiceControlerParameter(request, format, serviceName))) {

        }

        out = resp.getWriter();
        out.println(ctrlIF.getResponse());

        logger.debug("ServiceControlServlet#doGet End");
    }

}
