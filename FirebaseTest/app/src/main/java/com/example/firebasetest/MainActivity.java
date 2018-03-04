package com.example.firebasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    String ID;
    String GPS;
    int status;
    int priority =0;

    EditText user = findViewById(R.id.name);
    EditText userstatus = findViewById(R.id.personstatus);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void sendinfo(View view) {//sends to firebase
        ID = user.getText().toString();
        //status = userstatus.getText().to
        Entry myentry = new Entry(ID, GPS,status);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Priority"+priority);//determins were it is sent to

        myRef.push().setValue(myentry);//sends the entry

    }

    public void getGPS(View view) {


    }


}
