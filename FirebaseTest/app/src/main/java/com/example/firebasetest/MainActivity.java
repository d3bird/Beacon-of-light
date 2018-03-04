package com.example.firebasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    String ID;
    String GPS;
    int status;
    int priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

   /* @Override
    protected void onStart() {
        super.onStart();
        Entry myentry = new Entry("scott2", "nutterbutters", 8);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Priority0");

        myRef.push().setValue(myentry);
    }*/


    public void sendinfo(View view) {
        Entry myentry = new Entry(ID, GPS, status);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Priority"+priority);

        myRef.push().setValue(myentry);

    }
}
