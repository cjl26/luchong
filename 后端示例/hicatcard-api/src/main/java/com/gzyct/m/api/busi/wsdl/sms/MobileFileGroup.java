
package  com.gzyct.m.api.busi.wsdl.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MobileFileGroup complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="MobileFileGroup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TaskFileType" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="TaskFileID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MobileFileGroup", propOrder = {
    "taskFileType",
    "taskFileID"
})
public class MobileFileGroup {

    @XmlElement(name = "TaskFileType")
    protected long taskFileType;
    @XmlElement(name = "TaskFileID")
    protected String taskFileID;

    /**
     * ��ȡtaskFileType���Ե�ֵ��
     * 
     */
    public long getTaskFileType() {
        return taskFileType;
    }

    /**
     * ����taskFileType���Ե�ֵ��
     * 
     */
    public void setTaskFileType(long value) {
        this.taskFileType = value;
    }

    /**
     * ��ȡtaskFileID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaskFileID() {
        return taskFileID;
    }

    /**
     * ����taskFileID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskFileID(String value) {
        this.taskFileID = value;
    }

}
