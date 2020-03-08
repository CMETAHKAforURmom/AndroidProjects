package com.tryl.social;

import android.media.Image;

public class User_messages {

    String Name;
    String LastMessage;
    int Avatar;
    long ID;

    User_messages(String _Name, String _LastMessage, int _Avatar, long _id){
       Name = _Name;
       LastMessage = _LastMessage;
       Avatar = _Avatar;
       ID = _id;
    }

    public long getId() {
        return ID;
    }
}
