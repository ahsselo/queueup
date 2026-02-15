package com.example.demo.model;
import java.sql.*;
import java.util.ArrayList;

public class DBQuery {
	public static final String dbUrl = "jdbc:postgresql://localhost:5432/musicbrainz_db"; //this should end up being something like jdbc:postgresql://<vps_ip>>:<port_number>/musicbrainz_db if not running on vps
	public String searchTerm;
	public Attribute searchAttribute;
	public String sortCriteria;
	private Connection con;
	private Statement search;

	public DBQuery() throws SQLException {
		con = DriverManager.getConnection(dbUrl);
		search = con.createStatement();
	}

	public DBQuery(Connection con) throws SQLException {
		this.con = con;
		search = con.createStatement();
	}

	public DBQuery(String searchTerm) throws SQLException {
		this.searchTerm = searchTerm;
		con = DriverManager.getConnection(dbUrl);
		search = con.createStatement();
	}

	public DBQuery(String searchTerm, Attribute searchAttribute) throws SQLException {
		this.searchTerm = searchTerm;
		this.searchAttribute = searchAttribute;
		con = DriverManager.getConnection(dbUrl);
		search = con.createStatement();
	}

	public DBQuery(String searchTerm, Attribute searchAttribute, String sortCriteria) throws SQLException {
		this.searchTerm = searchTerm;
		this.searchAttribute = searchAttribute;
		this.sortCriteria = sortCriteria;
		con = DriverManager.getConnection(dbUrl);
		search = con.createStatement();
	}

	public Song[] getByGenre(){
		return this.getByGenre(searchTerm);
	}

	public Song[] getByGenre(String searchTerm){
		try{
			search.execute("SELECT DISTINCT ON (musicbrainz.artist.name, musicbrainz.track.name) \n" + //
								"musicbrainz.artist.name AS artist_name, musicbrainz.genre.name AS genre_name, musicbrainz.track.name AS track_name, musicbrainz.track.length AS duration\n" + //
								"FROM musicbrainz.artist, musicbrainz.artist_tag, musicbrainz.tag, musicbrainz.genre, musicbrainz.artist_credit, musicbrainz.track WHERE\n" + //
								"musicbrainz.genre.name LIKE '%" + searchTerm + "%' AND\n" + //
								"musicbrainz.artist.id = musicbrainz.artist_tag.artist AND\n" + //
								"musicbrainz.artist_tag.tag = musicbrainz.tag.id AND\n" + //
								"musicbrainz.tag.name = musicbrainz.genre.name AND\n" + //
								"musicbrainz.artist.id = musicbrainz.artist_credit.id AND\n" + //
								"musicbrainz.track.artist_credit = musicbrainz.artist_credit.id \n" + //
								"LIMIT 10;"); //change limit to how many songs you want to obtain
			return getSongArrayFromSet(search.getResultSet());
		}
		catch(SQLException e){
			return null;
		}
	}

	public Song[] getByArtist(){
		return this.getByArtist(searchTerm);
	}

	public Song[] getByArtist(String searchTerm){
		try{
			search.execute("SELECT DISTINCT ON (musicbrainz.artist.name, musicbrainz.track.name) \n" + //
								"musicbrainz.artist.name AS artist_name, musicbrainz.genre.name AS genre_name, musicbrainz.track.name AS track_name, musicbrainz.track.length AS duration\n" + //
								"FROM musicbrainz.artist, musicbrainz.artist_tag, musicbrainz.tag, musicbrainz.genre, musicbrainz.artist_credit, musicbrainz.track WHERE\n" + //
								"musicbrainz.artist.name LIKE '%" + searchTerm + "%' AND\n" + //
								"musicbrainz.artist.id = musicbrainz.artist_tag.artist AND\n" + //
								"musicbrainz.artist_tag.tag = musicbrainz.tag.id AND\n" + //
								"musicbrainz.tag.name = musicbrainz.genre.name AND\n" + //
								"musicbrainz.artist.id = musicbrainz.artist_credit.id AND\n" + //
								"musicbrainz.track.artist_credit = musicbrainz.artist_credit.id \n" + //
								"LIMIT 10;");
			return getSongArrayFromSet(search.getResultSet());
		}
		catch(SQLException e){
			return null;
		}
	}

	public Song[] getBySongName(){
		return this.getBySongName(searchTerm);	
	}

	public Song[] getBySongName(String searchTerm){
		try{
			search.execute("SELECT DISTINCT ON (musicbrainz.artist.name, musicbrainz.track.name) \n" + //
								"musicbrainz.artist.name AS artist_name, musicbrainz.genre.name AS genre_name, musicbrainz.track.name AS track_name, musicbrainz.track.length AS duration\n" + //
								"FROM musicbrainz.artist, musicbrainz.artist_tag, musicbrainz.tag, musicbrainz.genre, musicbrainz.artist_credit, musicbrainz.track WHERE\n" + //
								"musicbrainz.track.name LIKE '%" + searchTerm + "%' AND\n" + //
								"musicbrainz.artist.id = musicbrainz.artist_tag.artist AND\n" + //
								"musicbrainz.artist_tag.tag = musicbrainz.tag.id AND\n" + //
								"musicbrainz.tag.name = musicbrainz.genre.name AND\n" + //
								"musicbrainz.artist.id = musicbrainz.artist_credit.id AND\n" + //
								"musicbrainz.track.artist_credit = musicbrainz.artist_credit.id \n" + //
								"LIMIT 10;");
			return getSongArrayFromSet(search.getResultSet());
		}
		catch(SQLException e){
			return null;
		}
	}
	
	private Song[] getSongArrayFromSet(ResultSet set) throws SQLException{
		if(!set.next()){
			return null;
		}
		ArrayList<Song> songs = new ArrayList<Song>();
		String name = set.getString("track_name");
		String genre = set.getString("genre_name");
		double duration = set.getDouble("duration");
		Song song = new Song(name, genre, duration);
		songs.add(song);
		while(set.next()){
			name = set.getString("track_name");
			genre = set.getString("genre_name");
			duration = set.getDouble("duration");
			song = new Song(name, genre, duration);
			songs.add(song);
		}
		return arrayListToArray(songs);
	}

	private Song[] arrayListToArray(ArrayList<Song> al){
		Song[] songs = new Song[al.size()];
		for(int i = 0; i < songs.length; i++){
			songs[i] = al.get(i);
		}
		return songs;
	}
}
