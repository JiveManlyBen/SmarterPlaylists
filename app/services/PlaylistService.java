package services;

import java.io.File;
import java.io.IOException;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

import domain.PlayList;

import play.Logger;

public class PlaylistService {
	public static String parseXMLFile(File file) throws SAXException, IOException, 
		ParserConfigurationException, NumberFormatException, ParseException {
		Source xmlFile = new StreamSource(file);
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("conf"+File.separator+"iTunes.xsd"));
		Validator validator = schema.newValidator();
		validator.validate(xmlFile);
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setValidating(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(file);
		PlayList playList = getPlayList(doc);
		Logger.debug(playList.toString());
		return "ok";
	}
	private static List<Node> getElementNodes(NodeList nodes) {
		int nodeCount = nodes.getLength();
		List<Node> nodeList = new ArrayList<Node>();
		for (int i = 0; i < nodeCount; i++)
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE)
				nodeList.add(nodes.item(i));
		return nodeList;
	}
	private static Node getFirstElementNode(NodeList nodeList, String elementName) {
		for (Node n : getElementNodes(nodeList)) {
			if (n.getNodeName().equals(elementName))
				return n;
		}
		return null;
	}
	private static Map<String, String> getKeysAndValues(NodeList nodeList) {
		Map<String, String> keyMap = new HashMap<String, String>();
		String key = null;
		for (Node node : getElementNodes(nodeList)) {
			if (node.getNodeName().equals("key")) {
				key = node.getTextContent();
			}
			else if (key != null) {
				if (node.getNodeName().equals("true") || node.getNodeName().equals("false"))
					keyMap.put(key, node.getNodeName().equals("true") ? "1" : "0");
				else
					keyMap.put(key, node.getTextContent());
				key = null;
			}
		}
		return keyMap;
	}
	public static PlayList getPlayList(Document doc) throws NumberFormatException, ParseException{
		NodeList plist = getFirstElementNode(doc.getChildNodes(), "plist").getChildNodes();
		return new PlayList(getKeysAndValues(getFirstElementNode(plist, "dict").getChildNodes()));
	}
}
