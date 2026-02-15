
import com.example.demo.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import java.net.URL;
import java.util.List;
import java.util.Queue;

public class SessionTests {
    private Session session;
    private User testHost;
    private Song testSong;

    @BeforeEach
    public void setUp() throws Exception {
        testHost = new User(1, "testUser", "testUser@gmail.com", true);
        session = new Session(testHost, 1);
        testSong = new Song("Test Song", "Test Genre", 42.00);
    }

    @Test
    public void testDefaultConstructor() {
        Session defaultSession = new Session();
        assertNotNull(defaultSession.getHost());
        assertEquals(1, defaultSession.getSessionID());
        assertNotNull(defaultSession.getParticipants());
        assertNotNull(defaultSession.getSongQueue());
        assertNotNull(defaultSession.getSessionURL());
    }

    @Test
    public void testConstructorWithHostAndSessionID() {
        assertEquals(testHost, session.getHost());
        assertEquals(1, session.getSessionID());
        assertNull(session.getSessionURL());
        assertNotNull(session.getParticipants());
        assertNotNull(session.getSongQueue());
    }

    @Test
    public void testConstructorWithHostSessionIDAndURL() throws Exception {
        URL testURL = new URL("https://localhost:8080");
        Session urlSession = new Session(testHost, 1, testURL);
        assertEquals(testHost, urlSession.getHost());
        assertEquals(1, urlSession.getSessionID());
        assertEquals(testURL, urlSession.getSessionURL());
    }

    @Test
    public void testSetAndGetHost() {
        User newHost = new User(2, "newHost", "newHost@gmail.com", false);
        session.setHost(newHost);
        assertEquals(newHost, session.getHost());
    }

    @Test
    public void testSetAndGetSessionURL() throws Exception {
        URL newURL = new URL("https://example.com");
        session.setSessionURL(newURL);
        assertEquals(newURL, session.getSessionURL());
    }

    @Test
    public void testEnqueueAndDequeue() {
        assertTrue(session.enqueue(testSong));
        assertEquals(testSong, session.dequeue());
    }

    @Test
    public void testRemoveSong() {
        session.enqueue(testSong);
        assertTrue(session.removeSong(testSong));
        assertFalse(session.getSongQueue().contains(testSong));
    }

    @Test
    public void testAddAndRemoveParticipant() {
        User participant = new User(3, "participant", "participant@gmail.com", false);
        assertTrue(session.addParticipant(participant));
        assertTrue(session.getParticipants().contains(participant));
        assertTrue(session.removeParticipant(participant));
        assertFalse(session.getParticipants().contains(participant));
    }

    @Test
    public void testGetParticipants() {
        List<User> participants = session.getParticipants();
        assertNotNull(participants);
    }

    @Test
    public void testGetSongQueue() {
        Queue<Song> songQueue = session.getSongQueue();
        assertNotNull(songQueue);
    }
}
