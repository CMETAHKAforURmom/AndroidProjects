package com.tryl.social;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessagesList extends Fragment {

    String name;
    String lastMess;
    MessagesAdapter adapter;
    ArrayList<User_messages> UM;
    Context mContext;
    ListView LView;
    String TAG_ID = "DIALOG_ID";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.messages_list, container, false);

        UM = new ArrayList<User_messages>();
        mContext = getContext();

        DatabaseReference mRefetence = FirebaseDatabase.getInstance().getReference("Messages");

            mRefetence.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String _name = dataSnapshot.child("name").getValue(String.class);
                    String _last = dataSnapshot.child("last_mess").getValue(String.class);
                    long _id = dataSnapshot.child("dialog_id").getValue(Long.class);

                name = _name;
                lastMess = _last;

                UM.add(new User_messages(name, lastMess, R.mipmap.ic_launcher, _id));

                    rebuild();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        LView = view.findViewById(R.id.listView);

        LView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent message = new Intent(getContext(), Messages.class);
                message.putExtra(TAG_ID, UM.get(position).getId());
                startActivity(message);

            }
        });

            rebuild();

        return view;
    }

    private void rebuild() {

        adapter = new MessagesAdapter(mContext, UM);

        LView.setAdapter(adapter);

    }


}
