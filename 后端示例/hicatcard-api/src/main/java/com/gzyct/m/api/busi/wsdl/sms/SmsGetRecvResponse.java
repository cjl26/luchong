
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
 *         &lt;element name="Count" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ErrMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SmsRecvList" type="{http://tempuri.org/}ArrayOfSmsRecvList" minOccurs="0"/>
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
    "count",
    "errMsg",
    "smsRecvList"
})
@XmlRootElement(name = "Sms_GetRecvResponse")
public class SmsGetRecvResponse {

    @XmlElement(name = "Count")
    protected long count;
    @XmlElement(name = "ErrMsg")
    protected String errMsg;
    @XmlElement(name = "SmsRecvList")
    protected ArrayOfSmsRecvList smsRecvList;

    /**
     * ��ȡcount���Ե�ֵ��
     * 
     */
    public long getCount() {
        return count;
    }

    /**
     * ����count���Ե�ֵ��
     * 
     */
    public void setCount(long value) {
        this.count = value;
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
     * ��ȡsmsRecvList���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSmsRecvList }
     *     
     */
    public ArrayOfSmsRecvList getSmsRecvList() {
        return smsRecvList;
    }

    /**
     * ����smsRecvList���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSmsRecvList }
     *     
     */
    public void setSmsRecvList(ArrayOfSmsRecvList value) {
        this.smsRecvList = value;
    }

}
