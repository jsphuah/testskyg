/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.xml.XMLConfigurer;
import net.sf.oval.guard.Guard;
import net.sf.oval.localization.message.ResourceBundleMessageResolver;

import org.apache.log4j.Logger;

/**
 * Class for the test that you want to send a message to the MQ.
 *
 * @author sisi r.iwaki
 */
public class Oval {

    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(Oval.class);

    /**
     * method main.
     *
     * @param args String at run time, the specified
     * @throws IOException can not see the MQ server
     */
    public static void main(
        String[] args)
        throws IOException {

        ResourceBundleMessageResolver resolver = (ResourceBundleMessageResolver) Validator.getMessageResolver();
        resolver.addMessageBundle(ResourceBundle.getBundle("Messages"));

        BusinessParents bo = new BusinessParents("iwaki", "ryosuke", new BusinessChild(null));

        XMLConfigurer xmlConfigurer = new XMLConfigurer(new File(
                "\\\\TS-HTGL287\\project-info\\User\\r.iwaki\\新サイト\\workspace\\prototypes2jdbc\\src\\main\\webapp\\WEB-INF\\classes\\oval-config.xml"));
        Guard guard = new Guard(xmlConfigurer);

        List<ConstraintViolation> violations = guard.validate(bo);

        if (violations.size() < 1) {
            System.out.println("valid true .");
            return;
        }
        System.out.println("valid false ...");
        for (int idx = 0; idx < violations.size(); idx++) {
            ConstraintViolation violation = violations.get(idx);
            System.out.println("violation.getCheckName()" + violation.getCheckName());
            System.out.println(violation.toString());
//            violation.get
        }
    }

}

class BusinessParents {

    /**
     * Constractor.
     *
     * @param name set the name
     * @param type set the type
     */
    public BusinessParents(
                           String name,
                           String type,
                           BusinessChild businessChild) {
        this.name = name;
        this.type = type;
        this.businessChild = businessChild;
    }

    private String name = null;
    private String type = null;
    private BusinessChild businessChild = null;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public BusinessChild getBusinessChild() {
        return businessChild;
    }

}

class BusinessChild {

    /**
     * Constractor.
     *
     * @param child set the child
     */
    public BusinessChild(
                         String child) {
        this.child = child;
    }

    private String child = null;

    public String getChild() {
        return child;
    }
}
