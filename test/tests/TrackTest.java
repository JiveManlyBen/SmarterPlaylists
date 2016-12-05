package tests;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.math.BigDecimal;
import java.util.List;
import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.xml.sax.SAXException;

import domain.Library;
import domain.Track;

import services.PlaylistService;

public class TrackTest {

	private Library getFirstPlayList() throws JAXBException, ParseException, SAXException {
		Library returnedLibrary = PlaylistService.getLibrary(new File("test/assets/Library1.xml"));
		return returnedLibrary;
	}

	private Library getSecondPlayList() throws JAXBException, ParseException, SAXException {
		Library returnedLibrary = PlaylistService.getLibrary(new File("test/assets/LibraryA.xml"));
		return returnedLibrary;
	}

	@Test
	public void checkSimilarTracks() throws JAXBException, ParseException, SAXException {
		Library firstLibrary = getFirstPlayList();
		Library secondLibrary = getSecondPlayList();

		Track first = firstLibrary.getTracks().get(8081);
		Track second = secondLibrary.getTracks().get(8081);

		assertThat(first.similar(second)).as(first.getTrackId() + " vs. " + second.getTrackId() + ": " + first.getComparisonScore(second)).isTrue();

		first = firstLibrary.getTracks().get(8844);
		second = secondLibrary.getTracks().get(8844);

		assertThat(first.similar(second)).as(first.getTrackId() + " vs. " + second.getTrackId() + ": " + first.getComparisonScore(second)).isTrue();
		
		first = firstLibrary.getTracks().get(15619);
		second = secondLibrary.getTracks().get(14495);
		
		assertThat(first.similar(second)).as(first.getTrackId() + " vs. " + second.getTrackId() + ": " + first.getComparisonScore(second)).isFalse();
		
		first = firstLibrary.getTracks().get(4367);
		second = secondLibrary.getTracks().get(3674);
		
		assertThat(first.similar(second)).as(first.getTrackId() + " vs. " + second.getTrackId() + ": " + first.getComparisonScore(second)).isTrue();
	}

}
