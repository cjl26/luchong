
package  com.gzyct.m.api.busi.wsdl.sms;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfSmsReportList complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSmsReportList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SmsReportGroup" type="{http://tempuri.org/}SmsReportGroup" maxOccurs="200" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSmsReportList", propOrder = {
    "smsReportGroup"
})
public class ArrayOfSmsReportList {

    @XmlElement(name = "SmsReportGroup", nillable = true)
    protected List<SmsReportGroup> smsReportGroup;

    /**
     * Gets the value of the smsReportGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the smsReportGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSmsReportGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SmsReportGroup }
     * 
     * 
     */
    public List<SmsReportGroup> getSmsReportGroup() {
        if (smsReportGroup == null) {
            smsReportGroup = new ArrayList<SmsReportGroup>();
        }
        return this.smsReportGroup;
    }

}
