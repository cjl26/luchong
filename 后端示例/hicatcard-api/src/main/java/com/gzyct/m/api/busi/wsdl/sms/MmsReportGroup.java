
package  com.gzyct.m.api.busi.wsdl.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MmsReportGroup complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="MmsReportGroup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MmsID" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
@XmlType(name = "MmsReportGroup", propOrder = {
    "mmsID",
    "status",
    "reportTime",
    "exStatus"
})
public class MmsReportGroup {

    @XmlElement(name = "MmsID")
    protected long mmsID;
    @XmlElement(name = "Status")
    protected long status;
    @XmlElement(name = "ReportTime")
    protected String reportTime;
    @XmlElement(name = "ExStatus")
    protected String exStatus;

    /**
     * ��ȡmmsID���Ե�ֵ��
     * 
     */
    public long getMmsID() {
        return mmsID;
    }

    /**
     * ����mmsID���Ե�ֵ��
     * 
     */
    public void setMmsID(long value) {
        this.mmsID = value;
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
