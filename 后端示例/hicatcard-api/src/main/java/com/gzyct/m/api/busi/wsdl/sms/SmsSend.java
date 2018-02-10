
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
 *         &lt;element name="AddNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Timer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LongSms" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="MobileList" type="{http://tempuri.org/}ArrayOfMobileList" minOccurs="0"/>
 *         &lt;element name="Content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "addNum",
    "timer",
    "longSms",
    "mobileList",
    "content"
})
@XmlRootElement(name = "Sms_Send")
public class SmsSend {

    @XmlElement(name = "CorpID")
    protected long corpID;
    @XmlElement(name = "LoginName")
    protected String loginName;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "TimeStamp")
    protected String timeStamp;
    @XmlElement(name = "AddNum")
    protected String addNum;
    @XmlElement(name = "Timer")
    protected String timer;
    @XmlElement(name = "LongSms")
    protected long longSms;
    @XmlElement(name = "MobileList")
    protected ArrayOfMobileList mobileList;
    @XmlElement(name = "Content")
    protected String content;

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
     * ��ȡaddNum���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddNum() {
        return addNum;
    }

    /**
     * ����addNum���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddNum(String value) {
        this.addNum = value;
    }

    /**
     * ��ȡtimer���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimer() {
        return timer;
    }

    /**
     * ����timer���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimer(String value) {
        this.timer = value;
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
     * ��ȡmobileList���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMobileList }
     *     
     */
    public ArrayOfMobileList getMobileList() {
        return mobileList;
    }

    /**
     * ����mobileList���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMobileList }
     *     
     */
    public void setMobileList(ArrayOfMobileList value) {
        this.mobileList = value;
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

}
