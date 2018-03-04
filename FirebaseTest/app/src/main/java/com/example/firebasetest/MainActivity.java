package com.example.firebasetest;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    String ID ="";
    String GPS ="";
    int status =0;
    int priority =0;

  public  EditText user; //= findViewById(R.id.name);
  public  EditText userstatus;// = findViewById(R.id.personstatus);

       private LocationManager locationManager;
       private LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         user = findViewById(R.id.name);
         userstatus = findViewById(R.id.personstatus);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //t.append("\n " + location.getLongitude() + " " + location.getLatitude());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        //locationManager.requestLocationUpdates("");
    }



    public void sendinfo(View view) {//sends to firebase
        //ID = user.getText().toString();
        //status = userstatus.getText().to
        Entry toast = new Entry(ID, GPS,status);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Priority"+priority);//determins were it is sent to

        myRef.push().setValue(toast);//sends the entry

    }

    public void getGPS(View view) {


    }


}
