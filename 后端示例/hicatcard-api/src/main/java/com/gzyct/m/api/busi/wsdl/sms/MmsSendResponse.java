
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
 *         &lt;element name="MmsIDList" type="{http://tempuri.org/}ArrayOfMmsIDList" minOccurs="0"/>
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
    "mmsIDList"
})
@XmlRootElement(name = "Mms_SendResponse")
public class MmsSendResponse {

    @XmlElement(name = "Count")
    protected long count;
    @XmlElement(name = "ErrMsg")
    protected String errMsg;
    @XmlElement(name = "MmsIDList")
    protected ArrayOfMmsIDList mmsIDList;

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
     * ��ȡmmsIDList���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMmsIDList }
     *     
     */
    public ArrayOfMmsIDList getMmsIDList() {
        return mmsIDList;
    }

    /**
     * ����mmsIDList���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMmsIDList }
     *     
     */
    public void setMmsIDList(ArrayOfMmsIDList value) {
        this.mmsIDList = value;
    }

}
