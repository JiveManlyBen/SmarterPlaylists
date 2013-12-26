package domain;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.apple.itunes.Array;
import com.apple.itunes.Dict;
import com.apple.itunes.Plist;

public class Library {
	private int majorVersion;
	private static final String MAJOR_VERSION = "Major Version";
	private int minorVersion;
	private static final String MINOR_VERSION = "Minor Version";
	private Date date;
	private static final String DATE = "Date";
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	private String applicationVersion;
	private static final String APPLICATION_VERSION = "Application Version";
	private int features;
	private static final String FEATURES = "Features";
	private boolean showContentRatings;
	private static final String SHOW_CONTENT_RATINGS = "Show Content Ratings";
	private String musicFolder;
	private static final String MUSIC_FOLDER = "Music Folder";
	private String libraryPersistentId;
	private static final String LIBRARY_PERSISTENT_ID = "Library Persistent ID";
	private Map<Integer, Track> tracks;
	public static final String TRACKS = "Tracks";
	private List<Playlist> playlists;
	public static final String PLAYLISTS = "Playlists";

	public Library(int majorVersion, int minorVersion, Date date,
			String applicationVersion, int features, boolean showContentRatings,
			String musicFolder, String libraryPersistentId) {
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
		this.date = date;
		this.applicationVersion = applicationVersion;
		this.features = features;
		this.showContentRatings = showContentRatings;
		this.musicFolder = musicFolder;
		this.libraryPersistentId = libraryPersistentId;
	}

	public Library(Map<String, String> keyMap) throws NumberFormatException, ParseException {
		this(
				Integer.parseInt(keyMap.get(MAJOR_VERSION)),
				Integer.parseInt(keyMap.get(MINOR_VERSION)),
				dateFormat.parse(keyMap.get(DATE)),
				keyMap.get(APPLICATION_VERSION),
				Integer.parseInt(keyMap.get(FEATURES)),
				Boolean.parseBoolean(keyMap.get(SHOW_CONTENT_RATINGS)),
				keyMap.get(MUSIC_FOLDER),
				keyMap.get(LIBRARY_PERSISTENT_ID));
	}

	public int getMajorVersion() {
		return majorVersion;
	}

	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}

	public int getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getApplicationVersion() {
		return applicationVersion;
	}

	public void setApplicationVersion(String applicationVersion) {
		this.applicationVersion = applicationVersion;
	}

	public int getFeatures() {
		return features;
	}

	public void setFeatures(int features) {
		this.features = features;
	}

	public boolean isShowContentRatings() {
		return showContentRatings;
	}

	public void setShowContentRatings(boolean showContentRatings) {
		this.showContentRatings = showContentRatings;
	}

	public String getMusicFolder() {
		return musicFolder;
	}

	public void setMusicFolder(String musicFolder) {
		this.musicFolder = musicFolder;
	}

	public String getLibraryPersistentId() {
		return libraryPersistentId;
	}

	public void setLibraryPersistentId(String libraryPersistentId) {
		this.libraryPersistentId = libraryPersistentId;
	}

	public Map<Integer, Track> getTracks() {
		return tracks;
	}

	public void setTracks(Map<Integer, Track> tracks) {
		this.tracks = tracks;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Plist getPlist() {
		Plist plist = new Plist();
		plist.setVersion(new BigDecimal("1.0"));
		Dict libraryDict = new Dict();
		libraryDict.addKeyAndValue(MAJOR_VERSION, majorVersion);
		libraryDict.addKeyAndValue(MINOR_VERSION, minorVersion);
		libraryDict.addKeyAndValue(DATE, date, dateFormat);
		libraryDict.addKeyAndValue(APPLICATION_VERSION, applicationVersion);
		libraryDict.addKeyAndValue(FEATURES, features);
		libraryDict.addKeyAndValue(SHOW_CONTENT_RATINGS, showContentRatings);
		libraryDict.addKeyAndValue(MUSIC_FOLDER, musicFolder);
		libraryDict.addKeyAndValue(LIBRARY_PERSISTENT_ID, libraryPersistentId);
		Dict tracksDict = new Dict();
		for (Track track : tracks.values())
			tracksDict.addKeyAndValue(Integer.toString(track.getTrackId()), track.getDict());
		libraryDict.addKeyAndValue(TRACKS, tracksDict);
		if (playlists != null && playlists.size() > 0) {
			Array playlistsArray = new Array();
			for (Playlist playlist : playlists)
				playlistsArray.getDict().add(playlist.getDict());
			libraryDict.addKeyAndValue(PLAYLISTS, playlistsArray);
		}
		plist.setDict(libraryDict);
		return plist;
	}

	@Override
	public String toString() {
		return "Library [majorVersion=" + majorVersion + ", minorVersion="
				+ minorVersion + ", date=" + date + ", applicationVersion="
				+ applicationVersion + ", features=" + features
				+ ", showContentRatings=" + showContentRatings
				+ ", musicFolder=" + musicFolder + ", libraryPersistentId="
				+ libraryPersistentId + ", tracks=" + (tracks != null ? tracks.size() : 0)
				+ ", playlists=" + (playlists != null ? playlists.size() : 0) + "]";
	}
}
