package com.lienhuong.fashionbrandapp.model;

import java.sql.Timestamp;

public class PostMessage {
    public final  int id;
    public final String author;
    public final String content;
    public final Timestamp timestamp;
    public final String backgroundColor;

    public PostMessage(int id, String author, String content, Timestamp timestamp, String backgroundColor) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
        this.backgroundColor = backgroundColor;
    }
}

