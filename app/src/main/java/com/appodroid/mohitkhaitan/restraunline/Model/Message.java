package com.appodroid.mohitkhaitan.restraunline.Model;

/**
 * Created by MOHIT KHAITAN on 22-03-2016.
 */
public class Message {

    private String message;
    private long timestamp;

    public Message(){}

    public Message(String message, long timeStamp){
        this.message = message;
        this.timestamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
