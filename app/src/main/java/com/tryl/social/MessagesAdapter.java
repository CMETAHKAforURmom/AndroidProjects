package com.tryl.social;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MessagesAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflator;
    ArrayList<User_messages> message;

    MessagesAdapter(Context _context, ArrayList<User_messages> _message){

        context = _context;
        inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        message = _message;

    }

    @Override
    public int getCount() {
        return message.size();
    }

    @Override
    public Object getItem(int position) {
        return message.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null)view = inflator.inflate(R.layout.list_view_for_mesages, parent, false);
        User_messages UM = getMessages(position);

        ((TextView) view.findViewById(R.id.userName)).setText(UM.Name);
        ((TextView) view.findViewById(R.id.lastMessage)).setText(UM.LastMessage);
        ((ImageView) view.findViewById(R.id.image)).setImageResource(UM.Avatar);

        return view;
    }

    User_messages getMessages(int position){
        return (User_messages) getItem(position);
    }
}
