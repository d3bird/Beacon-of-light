package com.example.firebasetest;

import android.*;
import android.Manifest;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    String ID = "";
    String GPS = "";
    int status = 0;
    int priority = 0;

    public EditText user;
    public EditText userstatus;
    public TextView gpsstring;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.name);
        userstatus = findViewById(R.id.personstatus);

        gpsstring = findViewById(R.id.GPStext);

    }




    public void sendinfo(View view) {//sends to firebase
        ID = user.getText().toString();
        if(userstatus.getText().toString()==""){
            status =2;
        }else {
            status = Integer.valueOf(userstatus.getText().toString());
        }
        getgetGPS();
        Entry waffles = new Entry(ID, GPS,status);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Priority"+priority);//determins were it is sent to

        myRef.push().setValue(waffles);//sends the entry

    }

    public void receive(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Priority1");
        //myRef.
    }


    public void getgetGPS(){
        GPS= MapsActivity.getcord();


    }

    public void switchtomap(View view) {
        startActivity(new Intent(getApplicationContext(),MapsActivity.class));
    }
}
