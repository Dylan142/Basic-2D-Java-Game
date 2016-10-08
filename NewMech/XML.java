package com.AGameStudio.NewMech;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XML {
	
	public static String read(String s, String xml) {
		try{
			File file = new File(xml + ".xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			
			XPath xpath = XPathFactory.newInstance().newXPath();
			XPathExpression xExpression = xpath.compile(s);
			NodeList nl = (NodeList) xExpression.evaluate(doc, XPathConstants.NODESET);
			//System.out.println("Found " + nl.getLength() + " matches");
			//System.out.println(nl.item(0).getTextContent());
			return nl.item(0).getTextContent();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "nothing to read!";
	}
}
