package com.example.firebasetest;


import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, LocationListener, GestureDetector.OnGestureListener {

    private GestureDetectorCompat detector;

    String ID = "event #";
    int num =1;

    int GPSnume =0;
    int status =4;

    boolean evil = false;


    String[] posi  ={"evac","police","medical care","firemen"};
    String[] evi = {"shooter","fire","tornado","gas leak"};
    double[] LO ={-84.506192,-84.503379,-84.502511,-84.511164,-84.520706};
    double[] LA ={39.140375,39.139537,39.135282,39.129161,39.129311};
    TextView danger;
    TextView eviltex;// = findViewById(R.id.eviltext);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        detector = new GestureDetectorCompat(this, this);

        danger = findViewById(R.id.dangerevents);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.positives, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Priority0");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Entry myentry = dataSnapshot.getValue(Entry.class);
                if(myentry.getStatus() ==4) {
                    MapsActivity.importplaces(myentry);
                }else if(myentry.getStatus()==5){
                    danger.append(myentry.getID()+'\n');
                }
                eviltex = findViewById(R.id.eviltext);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        if(!evil) {
            ID = posi[pos];
        }else{
            ID = evi[pos];
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    public void sendinfo(View view) {//sends to firebase

        //getgetGPS();
        if(evil){
            status=5;
        }else{
            status=4;
        }
        Entry waffles = new Entry(ID, LA[GPSnume]+":"+LO[GPSnume],status);
        GPSnume++;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Priority"+"1");//determins were it is sent to

        myRef.push().setValue(waffles);//sends the entry

    }




    public void getgetGPS(){
       // GPS= MapsActivity.getcord();


    }

    public void switchtomap(View view) {
        startActivity(new Intent(getApplicationContext(),MapsActivity.class));
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
        if (e1.getX() < e2.getX()) {
            // This moves the screen left and starts the map activity
            Intent move_left = new Intent(this, MapsActivity.class);
            startActivity(move_left);

            return true;
        }
        return false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void switchevil(View view) {
        if(evil){
            evil = false;
            eviltex.setText("good");
        }else{
            eviltex.setText("evil");
            evil =true;
        }

    }
}
