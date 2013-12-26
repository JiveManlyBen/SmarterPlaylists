package domain;

import java.util.List;
import java.util.Map;

import com.apple.itunes.Array;
import com.apple.itunes.Dict;

public class Playlist {
	private int playlistId;
	private static String PLAYLIST_ID = "Playlist ID";
	private String name;
	private static String NAME = "Name";
	private String persistentID;
	private static String PERSISTENT_ID = "Playlist Persistent ID";
	private Integer distinguishedKind;
	private static String DISTINGUISHED_KIND = "Distinguished Kind";
	private Boolean master;
	private static String MASTER = "Master";
	private Boolean visible;
	private static String VISIBLE = "Visible";
	private Boolean music;
	private static String MUSIC = "Music";
	private Boolean movies;
	private static String MOVIES = "Movies";
	private Boolean tvShows;
	private static String TV_SHOWS = "TV Shows";
	private boolean allItems;
	private static String ALL_ITEMS = "All Items";
	private List<Integer> playlistItems;
	public static String PLAYLIST_ITEMS = "Playlist Items";

	public Playlist(int playlistId, String name, String persistentID,
			Integer distinguishedKind, Boolean master, Boolean visible,
			Boolean music, Boolean movies, Boolean tvShows, boolean allItems,
			List<Integer> playlistItems) {
		this.playlistId = playlistId;
		this.name = name;
		this.persistentID = persistentID;
		this.distinguishedKind = distinguishedKind;
		this.master = master;
		this.visible = visible;
		this.music = music;
		this.movies = movies;
		this.tvShows = tvShows;
		this.allItems = allItems;
		this.playlistItems = playlistItems;
	}
	
	public Playlist(Map<String, String> playlistMap) {
		this(Integer.parseInt(playlistMap.get(PLAYLIST_ID)), playlistMap.get(NAME), 
				playlistMap.get(PERSISTENT_ID), 
				(playlistMap.get(DISTINGUISHED_KIND) != null ? Integer.parseInt(playlistMap.get(DISTINGUISHED_KIND)) : null), 
				getBooleanValue(playlistMap.get(MASTER)), getBooleanValue(playlistMap.get(VISIBLE)),
				getBooleanValue(playlistMap.get(MUSIC)), getBooleanValue(playlistMap.get(MOVIES)), 
				getBooleanValue(playlistMap.get(TV_SHOWS)), getBooleanValue(playlistMap.get(ALL_ITEMS)),
				null);
	}

	public int getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersistentID() {
		return persistentID;
	}

	public void setPersistentID(String persistentID) {
		this.persistentID = persistentID;
	}

	public Integer getDistinguishedKind() {
		return distinguishedKind;
	}

	public void setDistinguishedKind(Integer distinguishedKind) {
		this.distinguishedKind = distinguishedKind;
	}

	public Boolean getMaster() {
		return master;
	}

	public void setMaster(Boolean master) {
		this.master = master;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getMusic() {
		return music;
	}

	public void setMusic(Boolean music) {
		this.music = music;
	}

	public Boolean getMovies() {
		return movies;
	}

	public void setMovies(Boolean movies) {
		this.movies = movies;
	}

	public Boolean getTvShows() {
		return tvShows;
	}

	public void setTvShows(Boolean tvShows) {
		this.tvShows = tvShows;
	}

	public boolean isAllItems() {
		return allItems;
	}

	public void setAllItems(boolean allItems) {
		this.allItems = allItems;
	}

	public List<Integer> getPlaylistItems() {
		return playlistItems;
	}

	public void setPlaylistItems(List<Integer> playlistItems) {
		this.playlistItems = playlistItems;
	}
	
	@Override
	public String toString() {
		return "Playlist [playlistId=" + playlistId + ", name=" + name
				+ ", persistentID=" + persistentID + ", distinguishedKind="
				+ distinguishedKind + ", master=" + master + ", music=" + music
				+ ", movies=" + movies + ", tvShows=" + tvShows + ", allItems="
				+ ", playlistItems=" + (playlistItems != null ? playlistItems.size() : 0) + "]";
	}
	
	public Dict getDict() {
		Dict dict = new Dict();
		dict.addKeyAndValue(NAME, name);
		dict.addKeyAndValue(PLAYLIST_ID, playlistId);
		dict.addKeyAndValue(PERSISTENT_ID, persistentID);
		if (distinguishedKind != null) dict.addKeyAndValue(DISTINGUISHED_KIND, distinguishedKind);
		if (master != null) dict.addKeyAndValue(MASTER, master);
		if (music != null) dict.addKeyAndValue(MUSIC, music);
		if (movies != null) dict.addKeyAndValue(MOVIES, movies);
		if (tvShows != null) dict.addKeyAndValue(TV_SHOWS, tvShows);
		dict.addKeyAndValue(ALL_ITEMS, allItems);
		if (playlistItems != null && playlistItems.size() > 0) {
			Array itemsArray = new Array();
			for (Integer i : playlistItems) {
				if (i != null) {
					Dict itemDict = new Dict();
					itemDict.addKeyAndValue(Track.TRACK_ID, i);
					itemsArray.getDict().add(itemDict);
				}
			}
			dict.addKeyAndValue(PLAYLIST_ITEMS, itemsArray);
		}
		return dict;
	}
	
	private static Boolean getBooleanValue(String value) {
		if (value == null)
			return null;
		return Boolean.valueOf(value);
	}
}
