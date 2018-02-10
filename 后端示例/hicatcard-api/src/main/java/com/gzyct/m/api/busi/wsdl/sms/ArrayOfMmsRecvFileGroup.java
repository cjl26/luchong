
package  com.gzyct.m.api.busi.wsdl.sms;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfMmsRecvFileGroup complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMmsRecvFileGroup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MmsRecvFileGroup" type="{http://tempuri.org/}MmsRecvFileGroup" maxOccurs="200" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMmsRecvFileGroup", propOrder = {
    "mmsRecvFileGroup"
})
public class ArrayOfMmsRecvFileGroup {

    @XmlElement(name = "MmsRecvFileGroup", nillable = true)
    protected List<MmsRecvFileGroup> mmsRecvFileGroup;

    /**
     * Gets the value of the mmsRecvFileGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mmsRecvFileGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMmsRecvFileGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MmsRecvFileGroup }
     * 
     * 
     */
    public List<MmsRecvFileGroup> getMmsRecvFileGroup() {
        if (mmsRecvFileGroup == null) {
            mmsRecvFileGroup = new ArrayList<MmsRecvFileGroup>();
        }
        return this.mmsRecvFileGroup;
    }

}
