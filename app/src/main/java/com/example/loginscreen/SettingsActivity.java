package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "firebaseData";
    private DatabaseReference mRef;
    TextView tvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mRef = database.getReference("image_state");

        tvMsg = findViewById(R.id.tv_msg);
        Button btnFake = findViewById(R.id.btn_fake);
        Button btnGenuine = findViewById(R.id.btn_genuine);

        btnFake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewChild(new ImageState("This will be Fake Image!", "This is a Fake/Modified Image", true));
            }
        });

        btnGenuine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewChild(new ImageState("This will be Genuine Image!", "This is a Genuine Image", false));

            }
        });

    }

    private void addNewChild(ImageState imageState) {
     /*   Toast.makeText(this, imageState.getAdminMsg(), Toast.LENGTH_SHORT).show();
        mRef.child("img").setValue(imageState);
*/


        mRef.child("img").setValue(imageState);
        ;

       readMsg();

    }

    private void readMsg() {
        // Read from the database
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                tvMsg.setText("");
                for (DataSnapshot d : dataSnapshot.getChildren()) {

                    ImageState value = d.getValue(ImageState.class);
                    Log.d(TAG, "Value is: " + value);
                    tvMsg.setText(value.getAdminMsg());
                    if (value.getColorRed()) {
                        tvMsg.setTextColor((getResources().getColor(R.color.colorRed)));
                    } else {
                        tvMsg.setTextColor((getResources().getColor(R.color.colorGreen)));

                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value. "+error.getMessage(), error.toException());
            }
        });


    }
}