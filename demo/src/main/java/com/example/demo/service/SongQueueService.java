package com.example.demo.service;

import com.example.demo.model.Song;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SongQueueService {
    private LinkedList<Song> queue = new LinkedList<>();

    public void push(Song song) {
        queue.addLast(song);
    }

    public Song pop() {
        if (!queue.isEmpty()) {
            return queue.removeFirst();
        }
        return null;
    }

    public Song removeAtIndex(int index) {
        if (index >= 0 && index < queue.size()) {
            return queue.remove(index);
        }
        return null;
    }

    public List<Song> getQueue() {
        return queue;
    }
	
    public int getQueueSize() {
        return queue.size();
    }
}
