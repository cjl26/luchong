
package  com.gzyct.m.api.busi.wsdl.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ErrMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MobileCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="YFMobileCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="BeginTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EndTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "status",
    "errMsg",
    "mobileCount",
    "yfMobileCount",
    "beginTime",
    "endTime"
})
@XmlRootElement(name = "Task_GetSmsStatusResponse")
public class TaskGetSmsStatusResponse {

    @XmlElement(name = "Status")
    protected long status;
    @XmlElement(name = "ErrMsg")
    protected String errMsg;
    @XmlElement(name = "MobileCount")
    protected long mobileCount;
    @XmlElement(name = "YFMobileCount")
    protected long yfMobileCount;
    @XmlElement(name = "BeginTime")
    protected String beginTime;
    @XmlElement(name = "EndTime")
    protected String endTime;

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
     * ��ȡerrMsg���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * ����errMsg���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrMsg(String value) {
        this.errMsg = value;
    }

    /**
     * ��ȡmobileCount���Ե�ֵ��
     * 
     */
    public long getMobileCount() {
        return mobileCount;
    }

    /**
     * ����mobileCount���Ե�ֵ��
     * 
     */
    public void setMobileCount(long value) {
        this.mobileCount = value;
    }

    /**
     * ��ȡyfMobileCount���Ե�ֵ��
     * 
     */
    public long getYFMobileCount() {
        return yfMobileCount;
    }

    /**
     * ����yfMobileCount���Ե�ֵ��
     * 
     */
    public void setYFMobileCount(long value) {
        this.yfMobileCount = value;
    }

    /**
     * ��ȡbeginTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeginTime() {
        return beginTime;
    }

    /**
     * ����beginTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeginTime(String value) {
        this.beginTime = value;
    }

    /**
     * ��ȡendTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * ����endTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndTime(String value) {
        this.endTime = value;
    }

}
