package com.example.demo.model;

public class Song {
    private String name;
    private String genre;
    private double duration; // duration in minutes
	//private int addedByUserID; //Holds ID of user who added song to queue
	
    public Song(String name, String genre, double duration) {
        this.name = name;
        this.genre = genre;
        this.duration = duration;
		//this.addedByUserID = -1; //Not added by user or not in queue
    }
	
	/*
	public Song(String name, String genre, double duration, int addedByUserID) {
        this.name = name;
        this.genre = genre;
        this.duration = duration;
        //this.addedByUserID = addedByUserID;
    }
	*/
	
    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public double getDuration() {
        return duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
	
	/*public int getAddedByUserID() {
        return addedByUserID;
    }
	*/
	
    public boolean equals(Song other){
    	if(!name.equals(other.name)){
	    return false;
    	}
    	else if(!genre.equals(other.genre)){
	    return false;
    	}
    	else if(!(duration == other.duration)){
	    return false;
    	}
	else{
	    return true;
	}
    }
    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                '}';
    }
}
