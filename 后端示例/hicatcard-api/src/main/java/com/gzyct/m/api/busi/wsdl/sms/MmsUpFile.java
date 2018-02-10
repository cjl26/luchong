
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
 *         &lt;element name="Subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SmilType" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="MmsFileList" type="{http://tempuri.org/}ArrayOfMmsFileGroup" minOccurs="0"/>
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
    "subject",
    "smilType",
    "mmsFileList"
})
@XmlRootElement(name = "Mms_UpFile")
public class MmsUpFile {

    @XmlElement(name = "CorpID")
    protected long corpID;
    @XmlElement(name = "LoginName")
    protected String loginName;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "TimeStamp")
    protected String timeStamp;
    @XmlElement(name = "Subject")
    protected String subject;
    @XmlElement(name = "SmilType")
    protected long smilType;
    @XmlElement(name = "MmsFileList")
    protected ArrayOfMmsFileGroup mmsFileList;

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
     * ��ȡsubject���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * ����subject���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * ��ȡsmilType���Ե�ֵ��
     * 
     */
    public long getSmilType() {
        return smilType;
    }

    /**
     * ����smilType���Ե�ֵ��
     * 
     */
    public void setSmilType(long value) {
        this.smilType = value;
    }

    /**
     * ��ȡmmsFileList���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMmsFileGroup }
     *     
     */
    public ArrayOfMmsFileGroup getMmsFileList() {
        return mmsFileList;
    }

    /**
     * ����mmsFileList���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMmsFileGroup }
     *     
     */
    public void setMmsFileList(ArrayOfMmsFileGroup value) {
        this.mmsFileList = value;
    }

}
