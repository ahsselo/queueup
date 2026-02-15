package com.example.demo.controller;

import com.example.demo.model.Song;
import com.example.demo.model.User;
import com.example.demo.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/session")
public class SessionController {

	//@PostMapping("/push")
	//@DeleteMapping("/pop")
	//@GetMapping("/size")
	
    private SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/newid")	//tries to add a new user to the session, and returns their id for future calls
    public ResponseEntity<Integer> newUser(@RequestParam String name, @RequestParam String email, @RequestParam boolean hasPremium) {
        return ResponseEntity.ok(sessionService.newUser(name, email, hasPremium));
	}
	
	@PostMapping("/push")
    public ResponseEntity<String> pushSong(@RequestBody Song song, @RequestParam int id) {
        System.out.println("User ID: " + id);
		sessionService.enqueue(song);
        return ResponseEntity.ok("Song added to the queue.");
    }

    @DeleteMapping("/pop")
    public ResponseEntity<Song> popSong() {
        Song song = sessionService.dequeue();
        return song != null ? ResponseEntity.ok(song) : ResponseEntity.notFound().build();
    }
	
	/*
    @DeleteMapping("/remove/{index}")
    public ResponseEntity<Song> removeSongAtIndex(@PathVariable int index) {
        Song removedSong = sessionService.removeAtIndex(index);
        return removedSong != null ? ResponseEntity.ok(removedSong) : ResponseEntity.notFound().build();
    }
	*/

    @GetMapping("/queue")
    public ResponseEntity<List<Song>> getQueue() {
        List<Song> queue = sessionService.getSongQueue();
        return ResponseEntity.ok(queue);
    }
	
	@GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> queue = sessionService.getUsers();
        return ResponseEntity.ok(queue);
    }
	
}
