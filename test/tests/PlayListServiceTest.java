package tests;

import domain.PlayList;
import domain.Track;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import services.PlaylistService;

public class PlayListServiceTest {

	private List<Track> getTrackList() {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.clear();
		
        calendarDate.set(2010, 2, 21, 5, 0, 27);
        Date dateAdded = calendarDate.getTime();
        calendarDate.set(2013, 7, 8, 21, 5, 7);
        Date playDate = calendarDate.getTime();
		List<Track> trackList = new ArrayList<Track>();
		trackList.add(new Track(11716, "My Friends", "Red Hot Chili Peppers", "One Hot Minute", 
			"MPEG audio file", dateAdded, 36, playDate,
			"58129546BE036721"));
		
	    calendarDate.set(2010, 2, 21, 4, 49, 15);
	    dateAdded = calendarDate.getTime();
	    calendarDate.set(2013, 10, 19, 4, 43, 39);
	    playDate = calendarDate.getTime();
		trackList.add(new Track(8844, "Come Alive", "Foo Fighters", "Echoes, Silence, Patience & Grace", 
			"MPEG audio file", dateAdded, 153, playDate,
			"FEBA0A5A4AD727B0"));
	
	    calendarDate.set(2013, 11, 11, 6, 12, 24);
	    dateAdded = calendarDate.getTime();
		trackList.add(new Track(54321, "Losing My Edge", "LCD Soundsystem", "LCD Soundsystem", 
			"MPEG audio file", dateAdded, null, null,
			"HDF9EJDU7HEKDNEU"));
		
	    calendarDate.set(2010, 6, 5, 22, 46, 19);
	    dateAdded = calendarDate.getTime();
	    calendarDate.set(2013, 8, 18, 5, 11, 31);
	    playDate = calendarDate.getTime();
		trackList.add(new Track(12326, "4th Of July", "Soundgarden", "Superunknown", 
			"MPEG audio file", dateAdded, 106, playDate,
			"64CEC1404A7F5071"));
		
		return trackList;
	}

	@Test
	public void checkPlaylistParsing() throws ParserConfigurationException, SAXException, IOException, NumberFormatException, ParseException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setValidating(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(new File("test/assets/Well_Formed.xml"));
		PlayList returnedList = PlaylistService.getPlayList(doc);
		assertThat(returnedList.getMajorVersion()).isEqualTo(1);
		assertThat(returnedList.getMinorVersion()).isEqualTo(2);
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.clear();
        calendarDate.set(2013, 10, 28, 2, 17, 11);
		assertThat(returnedList.getDate()).isEqualTo(calendarDate.getTime());
		assertThat(returnedList.getApplicationVersion()).isEqualTo("11.1.3");
		assertThat(returnedList.getFeatures()).isEqualTo(5);
		assertThat(returnedList.isShowContentRatings()).isTrue();
		assertThat(returnedList.getMusicFolder()).isEqualTo(("file://localhost/C:/Music/"));
		assertThat(returnedList.getLibraryPersistentId()).isEqualTo("437026SJ3UJ3Y3T7");

		assertThat(returnedList.getTracks().size()).isEqualTo(24);
		Track track = returnedList.getTracks().get(10892);
		assertThat(track).isNotNull();
		assertThat(track.getName()).isEqualTo("World (Demo)");
		assertThat(track.getArtist()).isEqualTo("Foo Fighters");
		assertThat(track.getAlbum()).isEqualTo("Five Songs & A Cover");
		assertThat(track.getKind()).isEqualTo("MPEG audio file");
		assertThat(track.getTrackNumber()).isEqualTo(4);
		assertThat(track.getYear()).isEqualTo(2005);
		calendarDate.set(2010, 2, 21, 4, 50, 12);
		assertThat(track.getDateAdded()).isEqualTo(calendarDate.getTime());
		assertThat(track.getPlayCount()).isEqualTo(117);
		calendarDate.set(2013, 9, 16, 16, 8, 59);
		assertThat(track.getPlayDate()).isEqualTo(calendarDate.getTime());
		assertThat(track.getSkipCount()).isEqualTo(3);
		assertThat(track.getPersistentID()).isEqualTo("023DDE089E93FEF0");
		assertThat(track.getTrackType()).isEqualTo("File");
	}

	@Test
	public void checkTrackOrdering() {
		List<Track> trackList = getTrackList();
		trackList = Track.getMostPlayedTracks(trackList);
		assertThat(trackList.get(0).getTrackId()).isEqualTo(8844);
		assertThat(trackList.get(trackList.size() - 1).getTrackId()).isEqualTo(54321);
	}
	
	@Test
	public void checkTrackOrderingWithLimit() {
		List<Track> trackList = getTrackList();
		trackList = Track.getMostPlayedTracks(trackList, 3);
		assertThat(trackList.size()).isEqualTo(3);
		assertThat(trackList.get(0).getTrackId()).isEqualTo(8844);
		assertThat(trackList.get(trackList.size() - 1).getTrackId()).isEqualTo(11716);
		

		trackList = getTrackList();
		int initialCount = trackList.size();
		int limit = 100 + initialCount;
		trackList = Track.getMostPlayedTracks(trackList, limit);
		assertThat(trackList.size()).isEqualTo(initialCount);
		
		trackList = getTrackList();
		trackList = Track.getMostPlayedTracks(trackList, 0);
		assertThat(trackList.size()).isEqualTo(0);
	}
}
