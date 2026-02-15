//Calls are now handled in SessionController, will remove soon

package com.example.demo.controller;

import com.example.demo.model.Song;
import com.example.demo.service.SongQueueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/queue")
public class SongQueueController {

	
    private SongQueueService songQueueService;

    public SongQueueController(SongQueueService songQueueService) {
        this.songQueueService = songQueueService;
    }

    @PostMapping("/push")
    public ResponseEntity<String> pushSong(@RequestBody Song song) {
        songQueueService.push(song);
        return ResponseEntity.ok("Song added to the queue.");
    }

    @DeleteMapping("/pop")
    public ResponseEntity<Song> popSong() {
        Song song = songQueueService.pop();
        return song != null ? ResponseEntity.ok(song) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/remove/{index}")
    public ResponseEntity<Song> removeSongAtIndex(@PathVariable int index) {
        Song removedSong = songQueueService.removeAtIndex(index);
        return removedSong != null ? ResponseEntity.ok(removedSong) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Song>> getQueue() {
        List<Song> queue = songQueueService.getQueue();
        return ResponseEntity.ok(queue);
    }
	
	@GetMapping("/size")
    public ResponseEntity<Integer> getQueueSize() {
        return ResponseEntity.ok(songQueueService.getQueueSize());
    }
}
