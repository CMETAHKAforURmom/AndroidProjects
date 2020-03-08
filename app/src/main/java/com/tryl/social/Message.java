package com.tryl.social;

import java.util.Date;

public class Message {

    boolean side_user;
    String message;
    Date currentDate = new Date();
    String timeStr;

    Message(boolean _side, String _message){

        side_user = _side;
        message = _message;
    }
}
