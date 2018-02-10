
package  com.gzyct.m.api.busi.wsdl.sms;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfMmsIDList complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMmsIDList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MmsIDGroup" type="{http://tempuri.org/}MmsIDGroup" maxOccurs="200" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMmsIDList", propOrder = {
    "mmsIDGroup"
})
public class ArrayOfMmsIDList {

    @XmlElement(name = "MmsIDGroup", nillable = true)
    protected List<MmsIDGroup> mmsIDGroup;

    /**
     * Gets the value of the mmsIDGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mmsIDGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMmsIDGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MmsIDGroup }
     * 
     * 
     */
    public List<MmsIDGroup> getMmsIDGroup() {
        if (mmsIDGroup == null) {
            mmsIDGroup = new ArrayList<MmsIDGroup>();
        }
        return this.mmsIDGroup;
    }

}
