package com.tryl.social;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class MessAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflator;
    ArrayList<Message> messages;

    MessAdapter(Context _context, ArrayList<Message> _message) {

        context = _context;
        inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        messages = _message;

    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RelativeLayout layout = new RelativeLayout(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams paramsLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        Message ms = getMess(position);

        TextView message = new TextView(context);
        message.setBackground(context.getDrawable(R.drawable.message_single_background));
        message.setText(ms.message);


        if(ms.side_user){
            layout.setGravity(Gravity.RIGHT);
        }else{
            layout.setGravity(Gravity.LEFT);
        }

        params.setMargins(25, 20, 25, 20);
        layout.setLayoutParams(paramsLayout);
        message.setPadding(30, 10, 30, 10);
        layout.addView(message, params);

        View view = layout;
        return view;
    }
    public Message getMess(int position){
        return  (Message) getItem(position);
    }

}