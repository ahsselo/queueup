package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SongTest {
    private Song song;

    @BeforeEach
    public void setUp() {
        song = new Song("Test Song", "Rock", 3.5);
    }

    @Test
    public void testConstructor() {
        assertEquals("Test Song", song.getName());
        assertEquals("Rock", song.getGenre());
        assertEquals(3.5, song.getDuration());
    }

    @Test
    public void testSetName() {
        song.setName("New Song");
        assertEquals("New Song", song.getName());
    }

    @Test
    public void testSetGenre() {
        song.setGenre("Jazz");
        assertEquals("Jazz", song.getGenre());
    }

    @Test
    public void testSetDuration() {
        song.setDuration(4.0);
        assertEquals(4.0, song.getDuration());
    }

    @Test
    public void testEquals_sameAttributes() {
        Song sameSong = new Song("Test Song", "Rock", 3.5);
        assertTrue(song.equals(sameSong));
    }

    @Test
    public void testEquals_differentName() {
        Song differentSong = new Song("Different Song", "Rock", 3.5);
        assertFalse(song.equals(differentSong));
    }

    @Test
    public void testEquals_differentGenre() {
        Song differentSong = new Song("Test Song", "Pop", 3.5);
        assertFalse(song.equals(differentSong));
    }

    @Test
    public void testEquals_differentDuration() {
        Song differentSong = new Song("Test Song", "Rock", 4.0);
        assertFalse(song.equals(differentSong));
    }
}
