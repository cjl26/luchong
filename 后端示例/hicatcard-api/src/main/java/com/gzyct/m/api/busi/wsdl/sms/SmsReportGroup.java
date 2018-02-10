
package  com.gzyct.m.api.busi.wsdl.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SmsReportGroup complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="SmsReportGroup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SmsID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ReportTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SmsReportGroup", propOrder = {
    "smsID",
    "status",
    "reportTime",
    "exStatus"
})
public class SmsReportGroup {

    @XmlElement(name = "SmsID")
    protected long smsID;
    @XmlElement(name = "Status")
    protected long status;
    @XmlElement(name = "ReportTime")
    protected String reportTime;
    @XmlElement(name = "ExStatus")
    protected String exStatus;

    /**
     * ��ȡsmsID���Ե�ֵ��
     * 
     */
    public long getSmsID() {
        return smsID;
    }

    /**
     * ����smsID���Ե�ֵ��
     * 
     */
    public void setSmsID(long value) {
        this.smsID = value;
    }

    /**
     * ��ȡstatus���Ե�ֵ��
     * 
     */
    public long getStatus() {
        return status;
    }

    /**
     * ����status���Ե�ֵ��
     * 
     */
    public void setStatus(long value) {
        this.status = value;
    }

    /**
     * ��ȡreportTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportTime() {
        return reportTime;
    }

    /**
     * ����reportTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportTime(String value) {
        this.reportTime = value;
    }

    /**
     * ��ȡexStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExStatus() {
        return exStatus;
    }

    /**
     * ����exStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExStatus(String value) {
        this.exStatus = value;
    }

}
