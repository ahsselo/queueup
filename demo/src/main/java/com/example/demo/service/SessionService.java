package com.example.demo.service;

import com.example.demo.model.*;
import java.util.*;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class SessionService {

    private Session session = new Session();


    public URL getSessionURL() {
        return session.getSessionURL();
    }

    public boolean enqueue(Song song) {
        return session.enqueue(song);
    }

    public Song dequeue() {
        return session.dequeue();
    }

    public boolean removeSong(Song song) {
        return session.removeSong(song);
    }
	
	public List<Song> getSongQueue() {
        return new ArrayList<>(session.getSongQueue());
    }
	
	public List<User> getUsers() {
        return session.getParticipants();
    }

	public int newUser(String name, String email, boolean hasPremium){
		int newId = session.generateNewId();
		User newUser = new User(newId, name, email, hasPremium);
		session.addParticipant(newUser);
		return newId;
	}

}
