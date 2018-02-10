
package  com.gzyct.m.api.busi.wsdl.sms;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MobsetApi", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://sms3.mobset.com:8080/Api?wsdl")
public class MobsetApi
    extends Service
{

    private final static URL MOBSETAPI_WSDL_LOCATION;
    private final static WebServiceException MOBSETAPI_EXCEPTION;
    private final static QName MOBSETAPI_QNAME = new QName("http://tempuri.org/", "MobsetApi");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://sms3.mobset.com:8080/Api?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MOBSETAPI_WSDL_LOCATION = url;
        MOBSETAPI_EXCEPTION = e;
    }

    public MobsetApi() {
        super(__getWsdlLocation(), MOBSETAPI_QNAME);
    }

    public MobsetApi(WebServiceFeature... features) {
        super(__getWsdlLocation(), MOBSETAPI_QNAME, features);
    }

    public MobsetApi(URL wsdlLocation) {
        super(wsdlLocation, MOBSETAPI_QNAME);
    }

    public MobsetApi(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MOBSETAPI_QNAME, features);
    }

    public MobsetApi(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MobsetApi(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MobsetApiSoap
     */
    @WebEndpoint(name = "MobsetApiSoap")
    public MobsetApiSoap getMobsetApiSoap() {
        return super.getPort(new QName("http://tempuri.org/", "MobsetApiSoap"), MobsetApiSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MobsetApiSoap
     */
    @WebEndpoint(name = "MobsetApiSoap")
    public MobsetApiSoap getMobsetApiSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "MobsetApiSoap"), MobsetApiSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MOBSETAPI_EXCEPTION!= null) {
            throw MOBSETAPI_EXCEPTION;
        }
        return MOBSETAPI_WSDL_LOCATION;
    }

}
