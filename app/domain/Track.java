package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Track {

	private int trackId;
	private static final String TRACK_ID = "Track ID";
	private String name;
	private static final String NAME = "Name";
	private String artist;
	private static final String ARTIST = "Artist";
	private String album;
	private static final String ALBUM = "Album";
	private String genre;
	private static final String GENRE = "Genre";
	private String kind;
	private static final String KIND = "Kind";
	private int size;
	private static final String SIZE = "Size";
	private int totalTime;
	private static final String TOTAL_TIME = "Total Time";
	private int trackNumber;
	private static final String TRACK_NUMBER = "Track Number";
	private int year;
	private static final String YEAR = "Year";
	private Date dateModified;
	private static final String DATE_MODIFIED = "Date Modified";
	private Date dateAdded;
	private static final String DATE_ADDED = "Date Added";
	private int bitRate;
	private static final String BIT_RATE = "Bit Rate";
	private int sampleRate;
	private static final String SAMPLE_RATE = "Sample Rate";
	private int playCount;
	private static final String PLAY_COUNT = "Play Count";
	private Date playDate;
	private static final String PLAY_DATE = "Play Date";
	private int skipCount;
	private static final String SKIP_COUNT = "Skip Count";
	private Date skipDate;
	private static final String SKIP_DATE = "Skip Date";
	private int artworkCount;
	private static final String ARTWORK_COUNT = "Artwork Count";
	private String persistentID;
	private static final String PERSISTENT_ID = "Persistent ID";
	private String trackType;
	private static final String TRACK_TYPE = "Track Type";
	private String Location;
	private static final String LOCATION = "Location";
	private int fileFolderCount;
	private static final String FILE_FOLDER_COUNT = "File Folder Count";
	private int libraryFolderCount;
	private static final String LIBRARY_FOLDER_COUNT = "Library Folder Count";
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");

	public Track(int trackId, String name, String artist, String album,
			String genre, String kind, int size, int totalTime,
			int trackNumber, int year, Date dateModified, Date dateAdded,
			int bitRate, int sampleRate, int playCount, Date playDate,
			int skipCount, Date skipDate, int artworkCount,
			String persistentID, String trackType, String location,
			int fileFolderCount, int libraryFolderCount) {
		this.trackId = trackId;
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.genre = genre;
		this.kind = kind;
		this.size = size;
		this.totalTime = totalTime;
		this.trackNumber = trackNumber;
		this.year = year;
		this.dateModified = dateModified;
		this.dateAdded = dateAdded;
		this.bitRate = bitRate;
		this.sampleRate = sampleRate;
		this.playCount = playCount;
		this.playDate = playDate;
		this.skipCount = skipCount;
		this.skipDate = skipDate;
		this.artworkCount = artworkCount;
		this.persistentID = persistentID;
		this.trackType = trackType;
		Location = location;
		this.fileFolderCount = fileFolderCount;
		this.libraryFolderCount = libraryFolderCount;
	}

	public int getTrackId() {
		return trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public int getBitRate() {
		return bitRate;
	}

	public void setBitRate(int bitRate) {
		this.bitRate = bitRate;
	}

	public int getSampleRate() {
		return sampleRate;
	}

	public void setSampleRate(int sampleRate) {
		this.sampleRate = sampleRate;
	}

	public int getPlayCount() {
		return playCount;
	}

	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}

	public Date getPlayDate() {
		return playDate;
	}

	public void setPlayDate(Date playDate) {
		this.playDate = playDate;
	}

	public int getSkipCount() {
		return skipCount;
	}

	public void setSkipCount(int skipCount) {
		this.skipCount = skipCount;
	}

	public Date getSkipDate() {
		return skipDate;
	}

	public void setSkipDate(Date skipDate) {
		this.skipDate = skipDate;
	}

	public int getArtworkCount() {
		return artworkCount;
	}

	public void setArtworkCount(int artworkCount) {
		this.artworkCount = artworkCount;
	}

	public String getPersistentID() {
		return persistentID;
	}

	public void setPersistentID(String persistentID) {
		this.persistentID = persistentID;
	}

	public String getTrackType() {
		return trackType;
	}

	public void setTrackType(String trackType) {
		this.trackType = trackType;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public int getFileFolderCount() {
		return fileFolderCount;
	}

	public void setFileFolderCount(int fileFolderCount) {
		this.fileFolderCount = fileFolderCount;
	}

	public int getLibraryFolderCount() {
		return libraryFolderCount;
	}

	public void setLibraryFolderCount(int libraryFolderCount) {
		this.libraryFolderCount = libraryFolderCount;
	}

	public String toString() {
		return "Track [trackId=" + trackId + ", name=" + name + ", artist="
				+ artist + ", album=" + album + ", genre=" + genre + ", kind="
				+ kind + ", size=" + size + ", totalTime=" + totalTime
				+ ", trackNumber=" + trackNumber + ", year=" + year
				+ ", dateModified=" + dateModified + ", dateAdded=" + dateAdded
				+ ", bitRate=" + bitRate + ", sampleRate=" + sampleRate
				+ ", playCount=" + playCount + ", playDate=" + playDate
				+ ", skipCount=" + skipCount + ", skipDate=" + skipDate
				+ ", artworkCount=" + artworkCount + ", persistentID="
				+ persistentID + ", trackType=" + trackType + ", Location="
				+ Location + ", fileFolderCount=" + fileFolderCount
				+ ", libraryFolderCount=" + libraryFolderCount + "]";
	}
}
