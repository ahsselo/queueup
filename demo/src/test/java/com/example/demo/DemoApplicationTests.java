package com.example.demo;

import com.example.demo.controller.SongQueueController;
import com.example.demo.model.Song;
import com.example.demo.service.SongQueueService;
import com.example.demo.model.DBQuery;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DemoApplicationTests {

    private SongQueueService songQueueService = Mockito.mock(SongQueueService.class);
    private SongQueueController songQueueController = new SongQueueController(songQueueService);

    @Test
    void testGetQueue() {
        List<Song> mockQueue = Arrays.asList(new Song("Test Song 1", "Test Genre 1", 1.0),
                                             new Song("Test Song 2", "Test Genre 2", 2.0));
        when(songQueueService.getQueue()).thenReturn(mockQueue);

        ResponseEntity<List<Song>> response = songQueueController.getQueue();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
		
        assertEquals("Test Song 1", response.getBody().get(0).getName());
        assertEquals("Test Genre 1", response.getBody().get(0).getGenre());
        assertEquals(1.0, response.getBody().get(0).getDuration());

		assertEquals("Test Song 2", response.getBody().get(1).getName());
        assertEquals("Test Genre 2", response.getBody().get(1).getGenre());
        assertEquals(2.0, response.getBody().get(1).getDuration());
    }

    @Test
    void testGetQueueSize() {

        when(songQueueService.getQueueSize()).thenReturn(3); // Mock the service to return size 3

        ResponseEntity<Integer> response = songQueueController.getQueueSize();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(3, response.getBody());
    }

	@Test 
	void testRemoveAtIndex(){
		Song mockSong = new Song("Test Song 2", "Test Genre 1", 1.0);
		when(songQueueService.removeAtIndex(0)).thenReturn(mockSong);

		Song response = songQueueService.removeAtIndex(0);
		
		assertEquals("Test Song 2", response.getName());
	}

	@Test
	void testPop(){
		Song mockSong = new Song("Test Song 2", "Test Genre 1", 1.0);
		when(songQueueService.pop()).thenReturn(mockSong);

		Song response = songQueueService.pop();
		
		assertEquals("Test Song 2", response.getName());
	}
}
