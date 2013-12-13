package tests;

import domain.PlayList;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import services.PlaylistService;

public class PlayListServiceTest {
	
	@Test
	public void checkPlaylistParsing() throws ParserConfigurationException, SAXException, IOException, NumberFormatException, ParseException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setValidating(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(new File("test/assets/Well_Formed.xml"));
		PlayList returnedList = PlaylistService.getPlayList(doc);
		assertThat(returnedList.getMajorVersion()).isEqualTo(1);
		assertThat(returnedList.getMinorVersion()).isEqualTo(2);
        Calendar calendarDate=Calendar.getInstance();
        calendarDate.clear();
        calendarDate.set(2013, 10, 28, 2, 17, 11);
		assertThat(returnedList.getDate()).isEqualTo(calendarDate.getTime());
		assertThat(returnedList.getApplicationVersion()).isEqualTo("11.1.3");
		assertThat(returnedList.getFeatures()).isEqualTo(5);
		assertThat(returnedList.isShowContentRatings()).isTrue();
		assertThat(returnedList.getMusicFolder()).isEqualTo(("file://localhost/C:/Music/"));
		assertThat(returnedList.getLibraryPersistentId()).isEqualTo("437026SJ3UJ3Y3T7");
	}
}
