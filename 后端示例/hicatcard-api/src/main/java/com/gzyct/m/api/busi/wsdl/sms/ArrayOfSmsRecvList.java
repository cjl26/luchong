
package  com.gzyct.m.api.busi.wsdl.sms;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfSmsRecvList complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSmsRecvList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SmsRecvGroup" type="{http://tempuri.org/}SmsRecvGroup" maxOccurs="200" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSmsRecvList", propOrder = {
    "smsRecvGroup"
})
public class ArrayOfSmsRecvList {

    @XmlElement(name = "SmsRecvGroup", nillable = true)
    protected List<SmsRecvGroup> smsRecvGroup;

    /**
     * Gets the value of the smsRecvGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the smsRecvGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSmsRecvGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SmsRecvGroup }
     * 
     * 
     */
    public List<SmsRecvGroup> getSmsRecvGroup() {
        if (smsRecvGroup == null) {
            smsRecvGroup = new ArrayList<SmsRecvGroup>();
        }
        return this.smsRecvGroup;
    }

}
