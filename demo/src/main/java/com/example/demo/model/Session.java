package com.example.demo.model;

import java.util.*;
import java.net.*;

public class Session {
    private User host;
    private int sessionID;
    private List<User> participants;
    private Queue<Song> songQueue;
    private URL sessionURL;


    //Use for testing
    public Session() {
        this.host = new User(1, "testUser", "testUser@gmail.com", true);
        this.sessionID = 1;
        this.participants = new ArrayList<>();
		participants.add(host);
        this.songQueue = new LinkedList<>();
        try {
            this.sessionURL = new URI("https://localhost:8080").toURL();
        } catch (MalformedURLException | URISyntaxException   e) {
            throw new RuntimeException(e);
        }
    }

    public Session(User host, int sessionID) {
        this.host = host;
        this.sessionID = sessionID;
        this.participants = new ArrayList<>();
		participants.add(host);
        this.songQueue = new LinkedList<>();
        this.sessionURL = null;
    }

    public Session(User host, int sessionID, URL sessionURL) {
        this.host = host;
        this.sessionID = sessionID;
        this.participants = new ArrayList<>();
		participants.add(host);
        this.songQueue = new LinkedList<>();
        this.sessionURL = sessionURL;
    }


    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public URL getSessionURL() {
        return sessionURL;
    }

    public void setSessionURL(URL sessionURL) {
        this.sessionURL = sessionURL;
    }

    public boolean enqueue(Song song) {
        return songQueue.add(song);
    }

    public Song dequeue() {
        return songQueue.poll();
    }

    public boolean removeSong(Song song) {
        return this.songQueue.remove(song);
    }

    public boolean addParticipant(User user) {
        return participants.add(user);
    }

    public boolean removeParticipant(User user) {
        return participants.remove(user);
    }
	
	public int generateNewId() {
		if(participants == null){
			return 1;
		}
		User last = participants.get(participants.size()-1);
		int lastId = last.getUserID();
		return lastId+1;
	}

    //For testing only
    public List<User> getParticipants() {
        return participants;
    }

    public Queue<Song> getSongQueue() {
        return songQueue;
    }

}
