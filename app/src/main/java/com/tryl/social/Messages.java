package com.tryl.social;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Messages extends AppCompatActivity {

    Object map;
    long inte;
    EditText send_text;
    String length_messages_list;
    ArrayList<Message> ms_list;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        send_text = findViewById(R.id.editText);
        Button send = findViewById(R.id.button);

        mContext = getApplicationContext();

        inte = getIntent().getLongExtra("DIALOG_ID", 4);

        ms_list = new ArrayList<Message>();

        final DatabaseReference mRefetence = FirebaseDatabase.getInstance().getReference("Messages");
        mRefetence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snap = dataSnapshot.child("dialogs").child(String.valueOf(inte)).getChildren();

                map =  dataSnapshot.child("dialogs").child(String.valueOf(inte)).getValue();

                    rebuild();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mess = send_text.getText().toString();
                mRefetence.child("dialogs").child(String.valueOf(inte)).push().setValue(mess);
                send_text.setText("");

            }
        });

    }

    private void rebuild() {

        ms_list.add(new Message(true, String.valueOf(map)));

        MessAdapter ms_adapter = new MessAdapter(mContext, ms_list);

        ListView LView = findViewById(R.id.list);
        LView.setAdapter(ms_adapter);

    }
}
