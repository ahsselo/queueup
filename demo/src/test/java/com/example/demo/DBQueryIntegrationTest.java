package test.java.com.example.demo;

import com.example.demo.model.DBQuery;
import com.example.demo.model.Song;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DBQueryIntegrationTest {
    
    private static Connection con;

    @BeforeAll
    public static void setup() throws SQLException {
        String dbUrl = "jdbc:postgresql://localhost:5432/musicbrainz_db"; //this should end up being something like jdbc:postgresql://<vps_ip>>:<port_number>/musicbrainz_db if not running on vps

        con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        assertNotNull(con, "Connection to the database should not be null.");
    }

    @Test
    public void testGetBySongName() throws SQLException {
        DBQuery dbQuery = new DBQuery(con);
        dbQuery.searchTerm = "Hurt";

        Song[] songs = dbQuery.getBySongName();

        assertNotNull(songs, "The query should return at least one result.");
        assertTrue(songs.length > 0, "The query should return some songs.");

        for (Song song : songs) {
            assertNotNull(song.getName(), "Song name should not be null.");
            assertNotNull(song.getGenre(), "Song genre should not be null.");
            assertTrue(song.getDuration() > 0, "Song duration should be greater than 0.");
        }
    }

    @Test
    public void testGetByArtist() throws SQLException {
        DBQuery dbQuery = new DBQuery(con);
        dbQuery.searchTerm = "Beatles";

        Song[] songs = dbQuery.getByArtist();

        assertNotNull(songs, "The query should return at least one result.");
        assertTrue(songs.length > 0, "The query should return some songs.");

        for (Song song : songs) {
            assertNotNull(song.getName(), "Song name should not be null.");
            assertNotNull(song.getGenre(), "Song genre should not be null.");
            assertTrue(song.getDuration() > 0, "Song duration should be greater than 0.");
        }
    }

    @Test
    public void testGetByGenre() throws SQLException {
        DBQuery dbQuery = new DBQuery(con);
        dbQuery.searchTerm = "hip-hop";

        Song[] songs = dbQuery.getByGenre();

        assertNotNull(songs, "The query should return at least one result.");
        assertTrue(songs.length > 0, "The query should return some songs.");

        for (Song song : songs) {
            assertNotNull(song.getName(), "Song name should not be null.");
            assertNotNull(song.getGenre(), "Song genre should not be null.");
            assertTrue(song.getDuration() > 0, "Song duration should be greater than 0.");
        }
    }

    @AfterAll
    public static void cleanup() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
}
