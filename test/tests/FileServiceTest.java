package tests;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.xml.sax.SAXException;

import play.i18n.Messages;

import services.FileService;

import enums.TrackFilterType;

public class FileServiceTest {
	private Map<String, String> getCodeMap(String limit){
		Map<String, String> codeMap = new LinkedHashMap<String, String>();
		for (String code : TrackFilterType.getCodes()) {
			codeMap.put(code, limit);
		}
		return codeMap;
	}
	
	@Test
	public void checkLibraryParsing() throws NumberFormatException, JAXBException, ParseException, SAXException, IOException {
		File file = new File("test/assets/Well_Formed.xml");
		FileService.createTempPlaylistFiles(file, getCodeMap("5"), "uuid_1");
		FileService.createTempPlaylistFiles(file, getCodeMap(""), "uuid_2");
		FileService.createTempPlaylistFiles(file, getCodeMap(" 5"), "uuid_3");
		FileService.createTempPlaylistFiles(file, getCodeMap("   "), "uuid_4");
		FileService.createTempPlaylistFiles(file, getCodeMap("0"), "uuid_5");
		FileService.createTempPlaylistFiles(file, getCodeMap("-1"), "uuid_6");
		FileService.createTempPlaylistFiles(file, getCodeMap((Integer.MAX_VALUE + 1) + ""), "uuid_7");
		
		FileService.createTempPlaylistFiles(file, getCodeMap("5"), "uuid_1");
		for (String code : TrackFilterType.getCodes()) {
			File output = new File(File.separator + "tmp" + File.separator + "m3u" + File.separator + "uuid_1" + File.separator + 
					"5 " + Messages.get("filter.label." + code) + ".m3u");
			assertThat(output.exists()).isTrue();
		}
	}
	
	@Test
	public void checkTempPlaylistFilesExist() throws NumberFormatException, JAXBException, ParseException, SAXException, IOException {
		File file = new File("test/assets/Well_Formed.xml");
		String uuid = "uuid_1";
		FileService.createTempPlaylistFiles(file, getCodeMap("5"), uuid);
		List<String> downloadFiles = new ArrayList<String>();
		for (String code : TrackFilterType.getCodes()) {
			downloadFiles.add("5 " + Messages.get("filter.label." + code) + ".m3u");
		}
		assertThat(FileService.getTempPlaylistFiles(uuid)).isEqualTo(downloadFiles);
	}
	
	@Test
	public void checkTempPlaylistFilesDeleted() throws IOException, NumberFormatException, JAXBException, ParseException, SAXException {
		File file = new File("test/assets/Well_Formed.xml");
		String uuid = "uuid_1";
		FileService.createTempPlaylistFiles(file, getCodeMap("5"), uuid);
		FileService.deleteTempPlaylistFiles("uuid_1");
		File dir = new File(File.separator + "tmp" + File.separator + "m3u" + File.separator + uuid);
		assertThat(dir.exists()).isFalse();
	}
}
