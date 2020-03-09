package com.tryl.social;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Inter_account extends AppCompatActivity {

    ArrayList<User> Users = new ArrayList<User>();
    ListView LView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_account);

        LView = findViewById(R.id.users_list);

        final DatabaseReference mRefetence = FirebaseDatabase.getInstance().getReference("Profiles");
        mRefetence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> snapshot = dataSnapshot.getChildren();

                for(DataSnapshot name : snapshot){

                    String snapshot_name = name.getValue(String.class);
                    String[] split_names = snapshot_name.split("#/");
                    for(String some : split_names){
                        Log.i("Split", some);
                    }
                    String name_right = split_names[1];
                    String UserId = split_names[0];
                    String UserPass =split_names[2];

                    Users.add(new User(UserId, name_right, UserPass));
                    rebuild();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        LView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent message = new Intent(getContext(), Messages.class);
                message.putExtra(TAG_ID, UM.get(position).getId());
                startActivity(message);

            }
        });
        }


    private void rebuild(){
        UsersAdapter UAdapter = new UsersAdapter(getApplicationContext(), Users);
        LView.setAdapter(UAdapter);

}
}

class UsersAdapter extends BaseAdapter{

    ArrayList<User> Name;
    LayoutInflater inflator;

    UsersAdapter(Context context, ArrayList<User> _name){

        inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Name = _name;
    };

    @Override
    public int getCount() {
        return Name.size();
    }

    @Override
    public Object getItem(int position) {
        return Name.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null) view = inflator.inflate(R.layout.list_messages, parent, false);

        User us = getUser(position);

        TextView tw = view.findViewById(R.id.tw_list);
        tw.setText(us.Name);

        return view;
    }

    public User getUser(int position){
        return (User) getItem(position);
    }
}
