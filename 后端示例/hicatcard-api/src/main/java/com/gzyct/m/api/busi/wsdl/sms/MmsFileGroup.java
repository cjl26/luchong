
package  com.gzyct.m.api.busi.wsdl.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MmsFileGroup complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="MmsFileGroup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PlayTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Image_FileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Image_FileData" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="Text_FileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text_Content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Audio_FileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Audio_FileData" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MmsFileGroup", propOrder = {
    "playTime",
    "imageFileName",
    "imageFileData",
    "textFileName",
    "textContent",
    "audioFileName",
    "audioFileData"
})
public class MmsFileGroup {

    @XmlElement(name = "PlayTime")
    protected long playTime;
    @XmlElement(name = "Image_FileName")
    protected String imageFileName;
    @XmlElement(name = "Image_FileData")
    protected byte[] imageFileData;
    @XmlElement(name = "Text_FileName")
    protected String textFileName;
    @XmlElement(name = "Text_Content")
    protected String textContent;
    @XmlElement(name = "Audio_FileName")
    protected String audioFileName;
    @XmlElement(name = "Audio_FileData")
    protected byte[] audioFileData;

    /**
     * ��ȡplayTime���Ե�ֵ��
     * 
     */
    public long getPlayTime() {
        return playTime;
    }

    /**
     * ����playTime���Ե�ֵ��
     * 
     */
    public void setPlayTime(long value) {
        this.playTime = value;
    }

    /**
     * ��ȡimageFileName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageFileName() {
        return imageFileName;
    }

    /**
     * ����imageFileName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageFileName(String value) {
        this.imageFileName = value;
    }

    /**
     * ��ȡimageFileData���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImageFileData() {
        return imageFileData;
    }

    /**
     * ����imageFileData���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImageFileData(byte[] value) {
        this.imageFileData = value;
    }

    /**
     * ��ȡtextFileName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextFileName() {
        return textFileName;
    }

    /**
     * ����textFileName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextFileName(String value) {
        this.textFileName = value;
    }

    /**
     * ��ȡtextContent���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextContent() {
        return textContent;
    }

    /**
     * ����textContent���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextContent(String value) {
        this.textContent = value;
    }

    /**
     * ��ȡaudioFileName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAudioFileName() {
        return audioFileName;
    }

    /**
     * ����audioFileName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAudioFileName(String value) {
        this.audioFileName = value;
    }

    /**
     * ��ȡaudioFileData���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getAudioFileData() {
        return audioFileData;
    }

    /**
     * ����audioFileData���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setAudioFileData(byte[] value) {
        this.audioFileData = value;
    }

}
