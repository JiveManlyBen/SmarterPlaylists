package services;

import java.io.File;
import java.io.IOException;

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

import org.xml.sax.SAXException;

public class PlaylistService {
	public static String parseXMLFile(File file) throws SAXException, IOException, ParserConfigurationException {
		Source xmlFile = new StreamSource(file);
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("conf"+File.separator+"iTunes.xsd"));
		Validator validator = schema.newValidator();
		validator.validate(xmlFile);
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setValidating(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(file);
		return "ok";
	}
}
