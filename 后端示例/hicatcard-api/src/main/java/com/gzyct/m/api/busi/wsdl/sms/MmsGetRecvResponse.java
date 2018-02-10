
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
 *         &lt;element name="Mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecvNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AddNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecvTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MmsRecvFileList" type="{http://tempuri.org/}ArrayOfMmsRecvFileGroup" minOccurs="0"/>
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
    "mobile",
    "recvNum",
    "addNum",
    "subject",
    "recvTime",
    "mmsRecvFileList"
})
@XmlRootElement(name = "Mms_GetRecvResponse")
public class MmsGetRecvResponse {

    @XmlElement(name = "Count")
    protected long count;
    @XmlElement(name = "ErrMsg")
    protected String errMsg;
    @XmlElement(name = "Mobile")
    protected String mobile;
    @XmlElement(name = "RecvNum")
    protected String recvNum;
    @XmlElement(name = "AddNum")
    protected String addNum;
    @XmlElement(name = "Subject")
    protected String subject;
    @XmlElement(name = "RecvTime")
    protected String recvTime;
    @XmlElement(name = "MmsRecvFileList")
    protected ArrayOfMmsRecvFileGroup mmsRecvFileList;

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
     * ��ȡmobile���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * ����mobile���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobile(String value) {
        this.mobile = value;
    }

    /**
     * ��ȡrecvNum���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecvNum() {
        return recvNum;
    }

    /**
     * ����recvNum���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecvNum(String value) {
        this.recvNum = value;
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
     * ��ȡrecvTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecvTime() {
        return recvTime;
    }

    /**
     * ����recvTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecvTime(String value) {
        this.recvTime = value;
    }

    /**
     * ��ȡmmsRecvFileList���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMmsRecvFileGroup }
     *     
     */
    public ArrayOfMmsRecvFileGroup getMmsRecvFileList() {
        return mmsRecvFileList;
    }

    /**
     * ����mmsRecvFileList���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMmsRecvFileGroup }
     *     
     */
    public void setMmsRecvFileList(ArrayOfMmsRecvFileGroup value) {
        this.mmsRecvFileList = value;
    }

}
