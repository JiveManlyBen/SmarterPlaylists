package tests;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import org.xml.sax.SAXException;

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
		running(fakeApplication(), new Runnable() {
			public void run() {
				File file = new File("test/assets/Well_Formed.xml");
				try {
					FileService.createTempPlaylistFiles(file, getCodeMap("5"), "uuid_1");
					FileService.createTempPlaylistFiles(file, getCodeMap(""), "uuid_2");
					FileService.createTempPlaylistFiles(file, getCodeMap(" 5"), "uuid_3");
					FileService.createTempPlaylistFiles(file, getCodeMap("   "), "uuid_4");
					FileService.createTempPlaylistFiles(file, getCodeMap("0"), "uuid_5");
					FileService.createTempPlaylistFiles(file, getCodeMap("-1"), "uuid_6");
					FileService.createTempPlaylistFiles(file, getCodeMap((Integer.MAX_VALUE + 1) + ""), "uuid_7");
					
					FileService.createTempPlaylistFiles(file, getCodeMap("5"), "uuid_1");
					for (String code : TrackFilterType.getCodes()) {
						File output = new File(FileService.M3U_TEMP_DIRECTORY + "uuid_1" + File.separator + 
								code + ".m3u");
						assertThat(output.exists()).isTrue();
					}
				} catch (Exception e) {
					e.printStackTrace();
					assertThat(e).isNull();
				}
			}
		});
	}
	
	@Test
	public void checkTempPlaylistFilesExist() throws NumberFormatException, JAXBException, ParseException, SAXException, IOException {
		File file = new File("test/assets/Well_Formed.xml");
		String uuid = "uuid_1";
		FileService.createTempPlaylistFiles(file, getCodeMap("5"), uuid);
		List<String> downloadFiles = new ArrayList<String>();
		for (String code : TrackFilterType.getCodes()) {
			downloadFiles.add(code + ".m3u");
		}
		assertThat(FileService.getTempPlaylistFiles(uuid)).isEqualTo(downloadFiles);
	}
	
	@Test
	public void checkTempPlaylistFilesDeleted() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				File file = new File("test/assets/Well_Formed.xml");
				String uuid = "uuid_1";
				try {
					FileService.createTempPlaylistFiles(file, getCodeMap("5"), uuid);
					FileService.deleteTempPlaylistFiles("uuid_1");
					File dir = new File(FileService.M3U_TEMP_DIRECTORY + uuid);
					assertThat(dir.exists()).isFalse();
				} catch (Exception e) {
					e.printStackTrace();
					assertThat(e).isNull();
				}
			}
		});
	}

	@After
	public void removeTempDirectories() throws IOException {
		for (int i = 1; i < 8; i++) {
			FileUtils.deleteDirectory(new File(FileService.M3U_TEMP_DIRECTORY + "uuid_" + i + File.separator));
		}
	}
}
