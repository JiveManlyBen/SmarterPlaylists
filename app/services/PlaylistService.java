package services;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import play.Logger;

import com.apple.itunes.Dict;
import com.apple.itunes.False;
import com.apple.itunes.Plist;
import com.apple.itunes.True;

import domain.PlayList;
import domain.Track;

public class PlaylistService {
	public static String parseXMLFile(File file) throws NumberFormatException, JAXBException, ParseException, SAXException, IOException {
		PlayList playList = getPlayList(file);
		Logger.debug(playList.toString());
		return "ok";
	}
	private static Map<String, String> getKeysAndValues(Dict dict) {
		Map<String, String> keyMap = new HashMap<String, String>();
		String key = null;
        for (Object o : dict.getDictOrArrayOrData()) {
        	if (o instanceof JAXBElement) {
        		JAXBElement<?> element = (JAXBElement<?>) o;
        		if (key != null) {
        			keyMap.put(key, element.getValue().toString());
        			key = null;
        		}
        		else if (element.getName().getLocalPart().equals("key")) {
    				key = element.getValue().toString();
    			}
        	}
        	else if (o instanceof True || o instanceof False) {
        		keyMap.put(key, o instanceof True ? "1" : "0");
        		key = null;
        	}
        }
		
		
		return keyMap;
	}
	public static PlayList getPlayList(File file) throws JAXBException, NumberFormatException, ParseException, SAXException, IOException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("conf"+File.separator+"iTunes.xsd"));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(file));
        JAXBContext jc = JAXBContext.newInstance(Plist.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Plist plist = (Plist) unmarshaller.unmarshal(file);
		PlayList playList = new PlayList(getKeysAndValues(plist.getDict()));
		playList.setTracks(getTracks(plist.getDict()));
		return playList;
	}
	private static Map<Integer, Track> getTracks(Dict dict) throws NumberFormatException, ParseException {
		Map<Integer, Track> trackMap = new HashMap<Integer, Track>();
		boolean foundTracks = false;
		for (Object o : dict.getDictOrArrayOrData()) {
			if (foundTracks && o instanceof Dict) {
				for (Object element : ((Dict) o).getDictOrArrayOrData()) {
					if (element instanceof Dict) {
						Map<String, String> map = getKeysAndValues((Dict) element);
						trackMap.put(Integer.parseInt(map.get("Track ID")), new Track(map));
					}
				}
				break;
			}
			if (o instanceof JAXBElement<?>) {
				JAXBElement<?> element = (JAXBElement<?>) o;
				if (element.getName().getLocalPart().equals("key") && element.getValue().toString().equals(PlayList.TRACKS)) {
					foundTracks = true;
				}
			}
		}
		return trackMap;
	}
}
