
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
 *         &lt;element name="CorpID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TimeStamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LongSms" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Priority" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="AtTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MobileList" type="{http://tempuri.org/}ArrayOfMobileFileGroup" minOccurs="0"/>
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
    "corpID",
    "loginName",
    "password",
    "timeStamp",
    "content",
    "longSms",
    "priority",
    "atTime",
    "mobileList"
})
@XmlRootElement(name = "Task_SmsSend")
public class TaskSmsSend {

    @XmlElement(name = "CorpID")
    protected long corpID;
    @XmlElement(name = "LoginName")
    protected String loginName;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "TimeStamp")
    protected String timeStamp;
    @XmlElement(name = "Content")
    protected String content;
    @XmlElement(name = "LongSms")
    protected long longSms;
    @XmlElement(name = "Priority")
    protected long priority;
    @XmlElement(name = "AtTime")
    protected String atTime;
    @XmlElement(name = "MobileList")
    protected ArrayOfMobileFileGroup mobileList;

    /**
     * ��ȡcorpID���Ե�ֵ��
     * 
     */
    public long getCorpID() {
        return corpID;
    }

    /**
     * ����corpID���Ե�ֵ��
     * 
     */
    public void setCorpID(long value) {
        this.corpID = value;
    }

    /**
     * ��ȡloginName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * ����loginName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginName(String value) {
        this.loginName = value;
    }

    /**
     * ��ȡpassword���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * ����password���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * ��ȡtimeStamp���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * ����timeStamp���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeStamp(String value) {
        this.timeStamp = value;
    }

    /**
     * ��ȡcontent���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * ����content���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * ��ȡlongSms���Ե�ֵ��
     * 
     */
    public long getLongSms() {
        return longSms;
    }

    /**
     * ����longSms���Ե�ֵ��
     * 
     */
    public void setLongSms(long value) {
        this.longSms = value;
    }

    /**
     * ��ȡpriority���Ե�ֵ��
     * 
     */
    public long getPriority() {
        return priority;
    }

    /**
     * ����priority���Ե�ֵ��
     * 
     */
    public void setPriority(long value) {
        this.priority = value;
    }

    /**
     * ��ȡatTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtTime() {
        return atTime;
    }

    /**
     * ����atTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtTime(String value) {
        this.atTime = value;
    }

    /**
     * ��ȡmobileList���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMobileFileGroup }
     *     
     */
    public ArrayOfMobileFileGroup getMobileList() {
        return mobileList;
    }

    /**
     * ����mobileList���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMobileFileGroup }
     *     
     */
    public void setMobileList(ArrayOfMobileFileGroup value) {
        this.mobileList = value;
    }

}
