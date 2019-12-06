package com.example.loginscreen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG = 100;
    private static final String TAG = "fbd";
    private DatabaseReference mRef;

    int progressStatus = 0;
    Handler handler = new Handler();
    ProgressBar progressBar;
    TextView tvProgress;


    TextView tvMsg;
    ImageView ivImg;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ivImg = findViewById(R.id.iv_img);
        tvMsg = findViewById(R.id.tv_msg);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tvProgress = (TextView) findViewById(R.id.tv_progress);
        setProgressBarVisibility(View.INVISIBLE);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mRef = database.getReference("image_state");


        btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectImage();
            }
        });

    }

    private void selectImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }


    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                ivImg.setImageBitmap(selectedImage);
                StartProgress();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(MainActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    private void StartProgress() {

        btnSubmit.setClickable(false);
        Toast.makeText(this, "huh", Toast.LENGTH_SHORT).show();

        setProgressBarVisibility(View.VISIBLE);

        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            tvProgress.setText(progressStatus + "/" + progressBar.getMax());
                        }
                    });
                    try {
                        // Sleep for 150 milliseconds.
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                readMsg();
            }
        }).start();


    }


    private void readMsg() {

        Log.w(TAG, "readFirebaseUsers!");

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
                    tvMsg.setText(value.getUserMsg());

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
                Log.w(TAG, "Failed to read value.", error.toException());
            }


        });

        btnSubmit.setClickable(true);

        progressStatus = 0;
        setProgressBarVisibility(View.INVISIBLE);
    }

    private void setProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
        tvProgress.setVisibility(visibility);
    }


}
