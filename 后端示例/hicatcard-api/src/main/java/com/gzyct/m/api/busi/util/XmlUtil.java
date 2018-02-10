package com.gzyct.m.api.busi.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.LoggerFactory;

public class XmlUtil {
	private final static Logger logger = LoggerFactory.getLogger(XmlUtil.class);

	/**
     * 将对象转换为xml, obj的属性为null时不出现在xml中
     * @param obj 对象
     * @param clazz 对象类
     * @return String xml串
     */
    public static String toXML(Object obj, Class clazz)
	{
		try
		{
			StringWriter writer = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(clazz);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FRAGMENT, true);
			m.marshal(obj, writer);
			StringBuffer bf = writer.getBuffer();
			return bf.toString();
		}
		catch(Exception e)
		{
			logger.error("toXml error: " + e.getMessage());
			return "";
		}
	}
    
    /**
     * 将xml转换为对象
     * @param xmlContent xml内容
     * @param clazz 对象类
     * @return String 对象
     */
    public static Object fromXml(String xmlContent, Class clazz)
    {
    	try
    	{
	    	JAXBContext context = JAXBContext.newInstance(clazz);
	    	Unmarshaller un = context.createUnmarshaller();
	    	return un.unmarshal(new ByteArrayInputStream(xmlContent.getBytes("UTF-8")));
    	}
    	catch(Exception e)
    	{
    		logger.error("fromXml error: " + e.getMessage());
    		return null;
    	}
    }
    
    /**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map<String,String> doXMLParse(String strxml) throws JDOMException, IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		Map<String,String> m = new HashMap<String,String>();
		
		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = XmlUtil.getChildrenText(children);
			}
			
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		
		return m;
	}
	
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(XmlUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
}
