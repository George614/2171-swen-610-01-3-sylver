package com.webcheckers.model;

import com.sun.org.apache.xml.internal.security.algorithms.MessageDigestAlgorithm;

/**
 *  Class to represent game messages
 *  Author: <a href="mailto:mfabbu@rit.edu">Matt Arlauckas</a>
 *  Date: 2017-10-25
 */
public class Message {

    //
    //  Constants
    //


    //
    //  Attributes
    //
    private String text;
    private MessageType type;

    public Message(String text, MessageType type) {
        this.text = text;
        this.type = type;
    }

    //
    //  Constructors
    //


    //
    //  Methods
    //

    public MessageType getType(){
        return this.type;
    }

    public String getText(){
        return this.text;
    }

    public void setType(MessageType type) { this.type = type; }

}
