
package  com.gzyct.m.api.busi.wsdl.sms;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfMmsReportList complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMmsReportList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MmsReportGroup" type="{http://tempuri.org/}MmsReportGroup" maxOccurs="200" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMmsReportList", propOrder = {
    "mmsReportGroup"
})
public class ArrayOfMmsReportList {

    @XmlElement(name = "MmsReportGroup", nillable = true)
    protected List<MmsReportGroup> mmsReportGroup;

    /**
     * Gets the value of the mmsReportGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mmsReportGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMmsReportGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MmsReportGroup }
     * 
     * 
     */
    public List<MmsReportGroup> getMmsReportGroup() {
        if (mmsReportGroup == null) {
            mmsReportGroup = new ArrayList<MmsReportGroup>();
        }
        return this.mmsReportGroup;
    }

}
