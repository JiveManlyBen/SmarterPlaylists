package services;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;

import play.Logger;
import play.Play;
import domain.Library;
import domain.Track;
import enums.TrackFilterType;

public class FileService {
	public static final String M3U_TEMP_DIRECTORY = Play.application().path() + File.separator + "tmp" + File.separator + "m3u" + File.separator;
	public static final String M3U_EXTENSION = ".m3u";

	public static void createTempM3uPlaylistFiles(File file, Map<String, String> codeMap, String uuid) throws NumberFormatException, 
		JAXBException, ParseException, SAXException, IOException {
		Library library = PlaylistService.parseXMLFile(file);
		for (Map.Entry<String, String> entry : codeMap.entrySet()) {
			TrackFilterType filter = TrackFilterType.get(entry.getKey());
			Integer limit = StringUtils.isEmpty(entry.getValue().trim()) ? null : new Integer(entry.getValue().trim());
			List<Track> trackList = Track.getSortedTracks(library.getTracks().values(), filter.getComparator(), limit);
			String m3uContent = Library.getM3U(trackList);
			String fileName = M3U_TEMP_DIRECTORY + uuid + File.separator + entry.getKey()  + M3U_EXTENSION;
			FileUtils.writeStringToFile(new File(fileName), m3uContent, "UTF-8");
			Logger.debug("Created: " + fileName);
		}
	}

	public static List<String> getTempM3uPlaylistFiles(String uuid) {
		return getTempPlaylistFiles(M3U_TEMP_DIRECTORY, uuid, M3U_EXTENSION);
	}

	private static List<String> getTempPlaylistFiles(String directory, String uuid, final String extension) {
		File downloadDir = new File(directory + uuid + File.separator);
		List<String> files = new ArrayList<String>();
		for (File file : downloadDir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(extension);
			}
		})) {
			if (file.isFile())
				files.add(file.getName());
		}
		return files;
	}

	public static File getTempM3uPlaylistFile(String uuid, String file) {
		return new File(M3U_TEMP_DIRECTORY + uuid + File.separator + file);
	}

	public static void deleteTempPlaylistFiles(String uuid) throws IOException {
		File dir = new File(M3U_TEMP_DIRECTORY + uuid + File.separator);
		FileUtils.deleteDirectory(dir);
	}
}
