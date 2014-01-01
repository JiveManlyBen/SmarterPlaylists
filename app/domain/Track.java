package domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.apple.itunes.Dict;

public class Track {

	private int trackId;
	public static final String TRACK_ID = "Track ID";
	private String name;
	private static final String NAME = "Name";
	private String artist;
	private static final String ARTIST = "Artist";
	private String album;
	private static final String ALBUM = "Album";
	private String albumArtist;
	private static final String ALBUM_ARTIST = "Album Artist";
	private String genre;
	private static final String GENRE = "Genre";
	private String kind;
	private static final String KIND = "Kind";
	private Integer size;
	private static final String SIZE = "Size";
	private int totalTime;
	private static final String TOTAL_TIME = "Total Time";
	private Integer trackNumber;
	private static final String TRACK_NUMBER = "Track Number";
	private Integer year;
	private static final String YEAR = "Year";
	private Date dateModified;
	private static final String DATE_MODIFIED = "Date Modified";
	private Date dateAdded;
	private static final String DATE_ADDED = "Date Added";
	private Integer bitRate;
	private static final String BIT_RATE = "Bit Rate";
	private Integer sampleRate;
	private static final String SAMPLE_RATE = "Sample Rate";
	private Integer playCount;
	private static final String PLAY_COUNT = "Play Count";
	private Date playDate;
	private static final String PLAY_DATE = "Play Date UTC";
	private Integer skipCount;
	private static final String SKIP_COUNT = "Skip Count";
	private Date skipDate;
	private static final String SKIP_DATE = "Skip Date";
	private Integer artworkCount;
	private static final String ARTWORK_COUNT = "Artwork Count";
	private String persistentID;
	private static final String PERSISTENT_ID = "Persistent ID";
	private String trackType;
	private static final String TRACK_TYPE = "Track Type";
	private String location;
	private static final String LOCATION = "Location";
	private Integer fileFolderCount;
	private static final String FILE_FOLDER_COUNT = "File Folder Count";
	private Integer libraryFolderCount;
	private static final String LIBRARY_FOLDER_COUNT = "Library Folder Count";
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	
	public Track(int trackId, String name, String artist, String album, 
			String kind, int totalTime, Date dateAdded, Integer playCount, 
			Date playDate, String persistentID, String location) {
		this.trackId = trackId;
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.kind = kind;
		this.dateAdded = dateAdded;
		this.playCount = playCount;
		this.playDate = playDate;
		this.persistentID = persistentID;
		this.location = location;
	}
	
	public Track(int trackId, String name, String artist, String albumArtist,
			String album, String genre, String kind, Integer size, int totalTime,
			Integer trackNumber, Integer year, Date dateModified, Date dateAdded,
			Integer bitRate, Integer sampleRate, Integer playCount, Date playDate,
			Integer skipCount, Date skipDate, Integer artworkCount,
			String persistentID, String trackType, String location,
			Integer fileFolderCount, Integer libraryFolderCount) {
		this.trackId = trackId;
		this.name = name;
		this.artist = artist;
		this.albumArtist = albumArtist;
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
		this.location = location;
		this.fileFolderCount = fileFolderCount;
		this.libraryFolderCount = libraryFolderCount;
	}
	
	public Track(Map<String, String> trackMap) throws NumberFormatException, ParseException {
		this(Integer.parseInt(trackMap.get(TRACK_ID)), trackMap.get(NAME), 
				trackMap.get(ARTIST), trackMap.get(ALBUM_ARTIST), trackMap.get(ALBUM), 
				trackMap.get(GENRE), trackMap.get(KIND), getInteger(trackMap.get(SIZE)), 
				getInteger(trackMap.get(TOTAL_TIME)), getInteger(trackMap.get(TRACK_NUMBER)), 
				getInteger(trackMap.get(YEAR)), getDate(trackMap.get(DATE_MODIFIED)), 
				dateFormat.parse(trackMap.get(DATE_ADDED)), getInteger(trackMap.get(BIT_RATE)), 
				getInteger(trackMap.get(SAMPLE_RATE)), getInteger(trackMap.get(PLAY_COUNT)), 
				getDate(trackMap.get(PLAY_DATE)), getInteger(trackMap.get(SKIP_COUNT)), 
				getDate(trackMap.get(SKIP_DATE)), getInteger(trackMap.get(ARTWORK_COUNT)),
				trackMap.get(PERSISTENT_ID), trackMap.get(TRACK_TYPE), trackMap.get(LOCATION),
				getInteger(trackMap.get(FILE_FOLDER_COUNT)), 
				getInteger(trackMap.get(LIBRARY_FOLDER_COUNT)));
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

	public String getAlbumArtist() {
		return albumArtist;
	}

	public void setAlbumArtist(String albumArtist) {
		this.albumArtist = albumArtist;
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

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(Integer trackNumber) {
		this.trackNumber = trackNumber;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
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

	public Integer getBitRate() {
		return bitRate;
	}

	public void setBitRate(Integer bitRate) {
		this.bitRate = bitRate;
	}

	public Integer getSampleRate() {
		return sampleRate;
	}

	public void setSampleRate(Integer sampleRate) {
		this.sampleRate = sampleRate;
	}

	public Integer getPlayCount() {
		return playCount == null ? 0 : playCount;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public Date getPlayDate() {
		return playDate;
	}

	public void setPlayDate(Date playDate) {
		this.playDate = playDate;
	}

	public Integer getSkipCount() {
		return skipCount;
	}

	public void setSkipCount(Integer skipCount) {
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
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getFileFolderCount() {
		return fileFolderCount;
	}

	public void setFileFolderCount(Integer fileFolderCount) {
		this.fileFolderCount = fileFolderCount;
	}

	public Integer getLibraryFolderCount() {
		return libraryFolderCount;
	}

	public void setLibraryFolderCount(Integer libraryFolderCount) {
		this.libraryFolderCount = libraryFolderCount;
	}

	@Override
	public String toString() {
		return "Track [trackId=" + trackId + ", name=" + name + ", artist="
				+ artist + ", album=" + album + ", albumArtist=" + albumArtist
				+ ", genre=" + genre + ", kind=" + kind + ", size=" + size
				+ ", totalTime=" + totalTime + ", trackNumber=" + trackNumber
				+ ", year=" + year + ", dateModified=" + dateModified
				+ ", dateAdded=" + dateAdded + ", bitRate=" + bitRate
				+ ", sampleRate=" + sampleRate + ", playCount=" + playCount
				+ ", playDate=" + playDate + ", skipCount=" + skipCount
				+ ", skipDate=" + skipDate + ", artworkCount=" + artworkCount
				+ ", persistentID=" + persistentID + ", trackType=" + trackType
				+ ", Location=" + location + ", fileFolderCount="
				+ fileFolderCount + ", libraryFolderCount="
				+ libraryFolderCount + "]";
	}
	
	private static Integer getInteger(String strValue) {
		return strValue != null ? Integer.parseInt(strValue) : null;
	}
	
	private static Date getDate(String strValue) throws ParseException {
		return strValue != null ? dateFormat.parse(strValue) : null;
	}
	
	public Double getPlaysPerDay() {
		Calendar calendarDate = Calendar.getInstance();
		return getPlaysPerDay(calendarDate);
	}
	
	private Double getPlaysPerDay(Calendar calendarDate) {
		return new Double((double)this.getPlayCount()/ ( (calendarDate.getTimeInMillis() - this.getDateAdded().getTime()) / (1000*60*60*24)) );
	}
	
    static class MostOftenPlayedComparator implements Comparator<Track> {
    	private static Calendar calendarDate = Calendar.getInstance();
    	public int compare(Track t1, Track t2) {
			int days1 = (int) ((calendarDate.getTimeInMillis() - t1.getDateAdded().getTime()) / (1000*60*60*24));
			Double playCount1 = t1.getPlaysPerDay(calendarDate) / (days1 < 30 ? 2 * (4 - (days1/10)) : 1);
			int days2 = (int) ((calendarDate.getTimeInMillis() - t2.getDateAdded().getTime()) / (1000*60*60*24));
			Double playCount2 = t2.getPlaysPerDay(calendarDate) / (days2 < 30 ? 2 * (4 - (days2/10)) : 1);
			return - playCount1.compareTo(playCount2);
    	}
    }
    
	public static List<Track> getMostOftenPlayedTracks(Collection<Track> tracks) {
		List<Track> trackList = new ArrayList<Track>(tracks);
		Collections.sort(trackList, new MostOftenPlayedComparator());
		return trackList;
	}
	
	public static List<Track> getMostOftenPlayedTracks(Collection<Track> tracks, int limit) {
		List<Track> trackList = getMostOftenPlayedTracks(tracks);
		try {
			return trackList.subList(0, limit);
		}
		catch(IndexOutOfBoundsException ex) {
			return trackList;
		}
	}

	public Dict getDict() {
		Dict dict = new Dict();
		dict.addKeyAndValue(TRACK_ID, trackId);
		dict.addKeyAndValue(NAME, name);
		dict.addKeyAndValue(ARTIST, artist);
		if (albumArtist != null) dict.addKeyAndValue(ALBUM_ARTIST, albumArtist);
		//Composer
		dict.addKeyAndValue(ALBUM, album);
		if (genre != null) dict.addKeyAndValue(GENRE, genre);
		if (kind != null) dict.addKeyAndValue(KIND, kind);
		//Size
		dict.addKeyAndValue(TOTAL_TIME, totalTime);
		//Disc Number
		//Disc Count
		if (trackNumber != null) dict.addKeyAndValue(TRACK_NUMBER, trackNumber);
		//Track Count
		if (year != null) dict.addKeyAndValue(YEAR, year);
		if (dateModified != null) dict.addKeyAndValue(DATE_MODIFIED, dateModified, dateFormat);
		if (dateAdded != null) dict.addKeyAndValue(DATE_ADDED, dateAdded, dateFormat);
		//Bit Rate
		//Sample Rate
		dict.addKeyAndValue(PLAY_COUNT, playCount);
		//Play Date
		dict.addKeyAndValue(PLAY_DATE, playDate, dateFormat);
		//Normalization
		//Compilation
		//Artwork Count
		dict.addKeyAndValue(PERSISTENT_ID, persistentID);
		if (trackType != null) dict.addKeyAndValue(TRACK_TYPE, trackType);
		if (location != null) dict.addKeyAndValue(LOCATION, location);
		if (fileFolderCount != null) dict.addKeyAndValue(FILE_FOLDER_COUNT, fileFolderCount);
		if (libraryFolderCount != null) dict.addKeyAndValue(LIBRARY_FOLDER_COUNT, libraryFolderCount);
		return dict;
	}
}
