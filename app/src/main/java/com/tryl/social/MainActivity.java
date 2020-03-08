package com.tryl.social;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction frTransaction = null;

    MessagesList ms_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ms_list = new MessagesList();

        RelativeLayout container = findViewById(R.id.main_container);
        BottomNavigationView navigator = findViewById(R.id.navigator);

        frTransaction = getSupportFragmentManager().beginTransaction();
        frTransaction.replace(R.id.main_container, ms_list);
        frTransaction.commit();


    }
}
