package com.cyheo.test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlHandling {

	/**
	 * @brief 
	 * @details
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		//parsingXmlTest();
		parsingXmlDps();

	}

	private static void parsingXmlDps() {

		String fileName = "";
		fileName = "D:/TMP/tmp2/dps_test2.xml";
//		fileName = "D:/TMP/tmp2/test.xml";
		
		try {

			InputSource  is = new InputSource(new FileReader(fileName));
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			doc.getDocumentElement().normalize();
			final List<String> l = new ArrayList<String>();
			final List<String> l2 = new ArrayList<String>();
			parseImsi(doc, l, l2, doc.getDocumentElement());

			for(int i=0; i<l.size(); i++) {
				logW(l.get(i) + ":" + l2.get(i));
			}

		} catch(Exception e) {
			//logW(e.printStackTrace().toString());
			logW(e.getMessage());
		}
	}

	private static void parseImsi(Document doc, final List<String> list, final List<String> list2, final Element e) {

		final NodeList children = e.getChildNodes();
		Node n = null;

		for (int i = 0; i < children.getLength(); i++) {

			n = children.item(i);
			switch (n.getNodeType()) {

				case Node.ELEMENT_NODE :
					logW("ELEMENT_NODE:" + n.getNodeName());
					parseAttr((Element) n);
					parseImsi(doc, list, list2, (Element) n);
					break;
				case Node.TEXT_NODE :
					logW("TEXT_NODE:(" + n.getNodeName() + ") " + n.getNodeValue());
					break;
			}
		}
	}

	private static void parseAttr(Element e) {
		if(e.hasAttributes()) {
			NamedNodeMap nodeMap = e.getAttributes();
			if(nodeMap != null && nodeMap.getLength() > 0) {
				for(int j=0; j<nodeMap.getLength(); j++) {
					if((nodeMap.item(j)).getNodeValue() != null) {
						logW("    " + (nodeMap.item(j)).getNodeName() + ":" + (nodeMap.item(j)).getNodeValue());
					}
				}
			}
		}
	}

//	private static void parse(Document doc, final List<String> list, final List<String> list2, final Element e) {
//
//		final NodeList children = e.getChildNodes();
//		Node n = null;
//		NamedNodeMap nodeMap = null;
//
//		for (int i = 0; i < children.getLength(); i++) {
//
//			n = children.item(i);
//			if (n.getNodeType() == Node.ELEMENT_NODE) {
//				list.add(n.getNodeName());
//				list2.add(n.getNodeValue());
//				parse(doc, list, list2, (Element) n);
//			}
//
//			if(n.hasAttributes()) {
//				nodeMap = n.getAttributes();
//				if(nodeMap != null && nodeMap.getLength() > 0) {
//					for(int j=0; j<nodeMap.getLength(); j++) {
//						logW("attr:" + (nodeMap.item(j)).getNodeValue());
//					}
//				}
//			}
//		}
//	}
//
//	private static void searchXml(NodeList nodeList) {
//
//		Node node = null;
//		Element element = null;
//		NodeList nodeList1 = null;
//
//		for(int i=0; i<nodeList.getLength(); i++) {
//
//			node = nodeList.item(i);
//
//			logW("Current Element:"+node.getNodeName());
//
//			if(node.getNodeType() == Node.ELEMENT_NODE) {
//
//				element = (Element) node;
//
//				logW(element.getAttribute("cid"));
//				logW(element.getAttribute("user"));
//				logW(element.getAttribute("terminalGUID"));
//				logW(element.getAttribute("type"));
//				logW(element.getAttribute("uploadType"));
//				logW(element.getAttribute("isUpdateDataRela"));
//				logW(element.getAttribute("isCheck"));
//
//				nodeList1 = element.getChildNodes();
//
//				for(int j=0; j<nodeList1.getLength(); j++) {
//
//					logW(element.getAttribute("storeId"));
//					logW(element.getElementsByTagName("store").item(0).getTextContent());
//					logW(element.getElementsByTagName("store").item(1).getTextContent());
//					logW(element.getElementsByTagName("store").item(2).getTextContent());
//
//				}
//
//			}
//
//		}
//	}
//
//	private static String getTagValue(String sTag, Element eElement) {
//		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
//
//		Node nValue = (Node) nlList.item(0);
//
//		return nValue.getNodeValue();
//	}

//	private static void parsingXmlTest() {
//		try {
//
//			InputSource  is = new InputSource(new FileReader("D:/TMP/tmp2/test.xml"));
//			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
//
//			// xpath 생성
//			XPath xpath = XPathFactory.newInstance().newXPath();
//
//			String expression = "//*/person";
//			NodeList cols = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
//			String firstName = "";
//			String lastName = "";
//
//			for(int idx=0; idx<cols.getLength(); idx++) {
//
//				String ssn=cols.item(idx).getAttributes().item(0).getTextContent();
//				logW("ssn................"+ssn);
//
//				expression = "//*[@ssn="+ssn+"]/firstName";
//				firstName = xpath.compile(expression).evaluate(document);
//				logW("firstName................"+firstName);
//
//				expression = "//*[@ssn="+ssn+"]/lastName";
//				lastName = xpath.compile(expression).evaluate(document);
//				logW("lastName................"+lastName);
//
//			}
//
//		} catch(Exception e) {
//			//logW(e.printStackTrace().toString());
//			logW(e.getMessage());
//		}
//	}

	private static void logW(String msg) {
		java.lang.System.out.println(msg);
	}

}