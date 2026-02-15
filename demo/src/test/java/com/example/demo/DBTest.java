package com.example.demo;

import com.example.demo.model.DBQuery;
import java.sql.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.model.DBQuery;
import com.example.demo.model.Song;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DBTest{
	private static final String dbUrl = "jdbc:postgresql:database";
	private DBQuery query;
	private Connection mockCon = Mockito.mock(Connection.class);
	private Statement mockStatement = Mockito.mock(Statement.class);
	private ResultSet mockSet = Mockito.mock(ResultSet.class);


	@Test
	public void testGetByName() throws Exception {
		when(mockCon.createStatement()).thenReturn(mockStatement);
		when(mockStatement.getResultSet()).thenReturn(mockSet);
		query = new DBQuery(mockCon);

		when(mockSet.next()).thenReturn(true, true, false);
		//the first one is the one by Nine Inch Nails and the Second is the one
		//by Johnny Cash
		when(mockSet.getString("Name")).thenReturn("Hurt", "Hurt");
		when(mockSet.getString("Genre")).thenReturn("Alternative Rock", "Country");
		when(mockSet.getDouble("Duration")).thenReturn(6.25, 3.5);

		Song[] songs = query.getBySongName("Hurt");
		Song[] expectedSongs = new Song[2];
		expectedSongs[0] = new Song("Hurt", "Alternative Rock", 6.25);
		expectedSongs[1] = new Song("Hurt", "Country", 3.5);
		for(int i = 0; i < songs.length; i++){
			if(i > expectedSongs.length){
				fail();
			}
			else{
				assertTrue(songs[i].equals(expectedSongs[i]));
			}
		}
	}

	@Test
	public void testGetByName2() throws Exception {
		when(mockCon.createStatement()).thenReturn(mockStatement);
		when(mockStatement.getResultSet()).thenReturn(mockSet);
		query = new DBQuery(mockCon);

		when(mockSet.next()).thenReturn(true, true, false);
		//the first one is the one by Nine Inch Nails and the Second is the one
		//by Johnny Cash
		when(mockSet.getString("Name")).thenReturn("Hurt", "Hurt");
		when(mockSet.getString("Genre")).thenReturn("Alternative Rock", "Country");
		when(mockSet.getDouble("Duration")).thenReturn(6.25, 3.5);

		query.searchTerm = "Hurt";
		Song[] songs = query.getBySongName();
		Song[] expectedSongs = new Song[2];
		expectedSongs[0] = new Song("Hurt", "Alternative Rock", 6.25);
		expectedSongs[1] = new Song("Hurt", "Country", 3.5);
		for(int i = 0; i < songs.length; i++){
			if(i > expectedSongs.length){
				fail();
			}
			else{
				assertTrue(songs[i].equals(expectedSongs[i]));
			}
		}
	}

	@Test
	public void testGetByGenre() throws Exception {
		when(mockCon.createStatement()).thenReturn(mockStatement);
		when(mockStatement.getResultSet()).thenReturn(mockSet);
		query = new DBQuery(mockCon);

		when(mockSet.next()).thenReturn(true, true, false);
		when(mockSet.getString("Name")).thenReturn("Fear of the Dark", "Master of Puppets");
		when(mockSet.getString("Genre")).thenReturn("Metal", "Metal");
		when(mockSet.getDouble("Duration")).thenReturn(7.3, 8.6);

		Song[] songs = query.getByGenre("Metal");
		Song[] expectedSongs = new Song[2];
		expectedSongs[0] = new Song("Fear of the Dark", "Metal", 7.3);
		expectedSongs[1] = new Song("Master of Puppets", "Metal", 8.6);
		for(int i = 0; i < songs.length; i++){
			if(i > expectedSongs.length){
				fail();
			}
			else{
				assertTrue(songs[i].equals(expectedSongs[i]));
			}
		}
	}

	@Test
	public void testGetByGenre2() throws Exception {
		when(mockCon.createStatement()).thenReturn(mockStatement);
		when(mockStatement.getResultSet()).thenReturn(mockSet);
		query = new DBQuery(mockCon);

		when(mockSet.next()).thenReturn(true, true, false);
		when(mockSet.getString("Name")).thenReturn("Fear of the Dark", "Master of Puppets");
		when(mockSet.getString("Genre")).thenReturn("Metal", "Metal");
		when(mockSet.getDouble("Duration")).thenReturn(7.3, 8.6);

		query.searchTerm = "Metal";
		Song[] songs = query.getByGenre();
		Song[] expectedSongs = new Song[2];
		expectedSongs[0] = new Song("Fear of the Dark", "Metal", 7.3);
		expectedSongs[1] = new Song("Master of Puppets", "Metal", 8.6);
		for(int i = 0; i < songs.length; i++){
			if(i > expectedSongs.length){
				fail();
			}
			else{
				assertTrue(songs[i].equals(expectedSongs[i]));
			}
		}
	}

	@Test 
	void testGetByArtist() throws Exception {
		when(mockCon.createStatement()).thenReturn(mockStatement);
		when(mockStatement.getResultSet()).thenReturn(mockSet);
		query = new DBQuery(mockCon);

		when(mockSet.next()).thenReturn(true, true, false);
		when(mockSet.getString("Name")).thenReturn("Fear of the Dark", "Number of the Beast");
		when(mockSet.getString("Genre")).thenReturn("Metal", "Metal");
		when(mockSet.getDouble("Duration")).thenReturn(7.3, 4.9);

		Song[] songs = query.getByGenre("Iron Maiden");
		Song[] expectedSongs = new Song[2];
		expectedSongs[0] = new Song("Fear of the Dark", "Metal", 7.3);
		expectedSongs[1] = new Song("Number of the Beast", "Metal", 4.9);
		for(int i = 0; i < songs.length; i++){
			if(i > expectedSongs.length){
				fail();
			}
			else{
				assertTrue(songs[i].equals(expectedSongs[i]));
			}
		}
	}

	@Test 
	void testGetByArtist2() throws Exception {
		when(mockCon.createStatement()).thenReturn(mockStatement);
		when(mockStatement.getResultSet()).thenReturn(mockSet);
		query = new DBQuery(mockCon);

		when(mockSet.next()).thenReturn(true, true, false);
		when(mockSet.getString("Name")).thenReturn("Fear of the Dark", "Number of the Beast");
		when(mockSet.getString("Genre")).thenReturn("Metal", "Metal");
		when(mockSet.getDouble("Duration")).thenReturn(7.3, 4.9);

		query.searchTerm = "Iron Maiden";
		Song[] songs = query.getByAritist();
		Song[] expectedSongs = new Song[2];
		expectedSongs[0] = new Song("Fear of the Dark", "Metal", 7.3);
		expectedSongs[1] = new Song("Number of the Beast", "Metal", 4.9);
		for(int i = 0; i < songs.length; i++){
			if(i > expectedSongs.length){
				fail();
			}
			else{
				assertTrue(songs[i].equals(expectedSongs[i]));
			}
		}
	}
}
