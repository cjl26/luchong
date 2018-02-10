
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
 *         &lt;element name="SmsReportList" type="{http://tempuri.org/}ArrayOfSmsReportList" minOccurs="0"/>
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
    "smsReportList"
})
@XmlRootElement(name = "Sms_GetReportResponse")
public class SmsGetReportResponse {

    @XmlElement(name = "Count")
    protected long count;
    @XmlElement(name = "ErrMsg")
    protected String errMsg;
    @XmlElement(name = "SmsReportList")
    protected ArrayOfSmsReportList smsReportList;

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
     * ��ȡsmsReportList���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSmsReportList }
     *     
     */
    public ArrayOfSmsReportList getSmsReportList() {
        return smsReportList;
    }

    /**
     * ����smsReportList���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSmsReportList }
     *     
     */
    public void setSmsReportList(ArrayOfSmsReportList value) {
        this.smsReportList = value;
    }

}
