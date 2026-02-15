package com.example.demo.controller;

import com.example.demo.model.Song;
import java.util.ArrayList;
import java.util.List;

public class Queue {
    private List<Song> queue;

    // Constructor to initialize the queue
    public Queue() {
        this.queue = new ArrayList<>();
    }

    // Adds a Song to the end of the queue
    public void push(Song song) {
        queue.add(song);
    }

    // Removes and returns the first Song in the queue
    public Song pop() {
        if (!queue.isEmpty()) {
            return queue.remove(0);
        }
        return null; // Or throw an exception if preferred
    }

    // Removes and returns the Song at the specified index
    public Song removeAtIndex(int index) {
        if (index >= 0 && index < queue.size()) {
            return queue.remove(index);
        }
        return null; // Or throw an exception if preferred
    }

    // Returns the entire queue
    public List<Song> getQueue() {
        return queue;
    }

    // Returns the size of the queue
    public int getQueueSize() {
        return queue.size();
    }
}
