package com.example.tiktoklike;

public class Video {
    private int ID;
    private String URL;
    private int LIKE;

    public Video() {
    }

    public Video(int ID, String URL, int LIKE) {
        this.ID = ID;
        this.URL = URL;
        this.LIKE = LIKE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getLIKE() {
        return LIKE;
    }

    public void setLIKE(int LIKE) {
        this.LIKE = LIKE;
    }
}
