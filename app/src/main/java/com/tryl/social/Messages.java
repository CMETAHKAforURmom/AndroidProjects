package com.tryl.social;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Messages extends AppCompatActivity {

    String mess;
    long inte;
    ArrayList<Message> ms_list;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        mContext = getApplicationContext();

        inte = getIntent().getLongExtra("DIALOG_ID", 4);

        final DatabaseReference mRefetence = FirebaseDatabase.getInstance().getReference("Messages");
        mRefetence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mess = dataSnapshot.child("dialogs").child(String.valueOf(inte)).child("some_mess").getValue(String.class);
                rebuild();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void rebuild() {

        ms_list = new ArrayList<Message>();
        ms_list.add(new Message(true, "Hello"));
        ms_list.add(new Message(false, "Hello"));

        MessAdapter ms_adapter = new MessAdapter(mContext, ms_list);

        ListView LView = findViewById(R.id.list);
        LView.setAdapter(ms_adapter);

    }
}
