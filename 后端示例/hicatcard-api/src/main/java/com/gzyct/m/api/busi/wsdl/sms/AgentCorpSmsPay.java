
package com.gzyct.m.api.busi.wsdl.sms;

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
 *         &lt;element name="AgentID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TimeStamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorpID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PayMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PayMoney" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="PayPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="PayPresent" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PayMemo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "agentID",
    "loginName",
    "password",
    "timeStamp",
    "corpID",
    "payMode",
    "payMoney",
    "payPrice",
    "payPresent",
    "payMemo"
})
@XmlRootElement(name = "Agent_CorpSmsPay")
public class AgentCorpSmsPay {

    @XmlElement(name = "AgentID")
    protected long agentID;
    @XmlElement(name = "LoginName")
    protected String loginName;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "TimeStamp")
    protected String timeStamp;
    @XmlElement(name = "CorpID")
    protected long corpID;
    @XmlElement(name = "PayMode")
    protected String payMode;
    @XmlElement(name = "PayMoney")
    protected double payMoney;
    @XmlElement(name = "PayPrice")
    protected double payPrice;
    @XmlElement(name = "PayPresent")
    protected long payPresent;
    @XmlElement(name = "PayMemo")
    protected String payMemo;

    /**
     * ��ȡagentID���Ե�ֵ��
     * 
     */
    public long getAgentID() {
        return agentID;
    }

    /**
     * ����agentID���Ե�ֵ��
     * 
     */
    public void setAgentID(long value) {
        this.agentID = value;
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
     * ��ȡpayMode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayMode() {
        return payMode;
    }

    /**
     * ����payMode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayMode(String value) {
        this.payMode = value;
    }

    /**
     * ��ȡpayMoney���Ե�ֵ��
     * 
     */
    public double getPayMoney() {
        return payMoney;
    }

    /**
     * ����payMoney���Ե�ֵ��
     * 
     */
    public void setPayMoney(double value) {
        this.payMoney = value;
    }

    /**
     * ��ȡpayPrice���Ե�ֵ��
     * 
     */
    public double getPayPrice() {
        return payPrice;
    }

    /**
     * ����payPrice���Ե�ֵ��
     * 
     */
    public void setPayPrice(double value) {
        this.payPrice = value;
    }

    /**
     * ��ȡpayPresent���Ե�ֵ��
     * 
     */
    public long getPayPresent() {
        return payPresent;
    }

    /**
     * ����payPresent���Ե�ֵ��
     * 
     */
    public void setPayPresent(long value) {
        this.payPresent = value;
    }

    /**
     * ��ȡpayMemo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayMemo() {
        return payMemo;
    }

    /**
     * ����payMemo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayMemo(String value) {
        this.payMemo = value;
    }

}
