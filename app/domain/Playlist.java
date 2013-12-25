package domain;

import java.util.List;
import java.util.Map;

public class Playlist {
	private int playlistId;
	private static String PLAYLIST_ID = "Playlist ID";
	private String name;
	private static String NAME = "Name";
	private String persistentID;
	private static String PERSISTENT_ID = "Playlist Persistent ID";
	private int distinguishedKind;
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
			int distinguishedKind, Boolean master, Boolean visible,
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
				playlistMap.get(PERSISTENT_ID), Integer.parseInt(playlistMap.get(DISTINGUISHED_KIND)), 
				Boolean.parseBoolean(playlistMap.get(MASTER)), Boolean.parseBoolean(playlistMap.get(VISIBLE)),
				Boolean.parseBoolean(playlistMap.get(MUSIC)), Boolean.parseBoolean(playlistMap.get(MOVIES)), 
				Boolean.parseBoolean(playlistMap.get(TV_SHOWS)), Boolean.getBoolean(playlistMap.get(ALL_ITEMS)),
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

	public int getDistinguishedKind() {
		return distinguishedKind;
	}

	public void setDistinguishedKind(int distinguishedKind) {
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
}
