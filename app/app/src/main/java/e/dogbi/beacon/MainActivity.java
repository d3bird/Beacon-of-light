package e.dogbi.beacon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button sendbutton;
    Button recivebutton;
    EditText input;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendbutton = findViewById(R.id.send);
        recivebutton =findViewById(R.id.recive);
        input=findViewById(R.id.sendtext);

    }



    public void sendfirebase(View view) {

    }

    public void recivefirebase(View view) {

    }

}
