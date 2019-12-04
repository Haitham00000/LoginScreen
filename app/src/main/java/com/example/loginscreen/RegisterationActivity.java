package com.example.loginscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterationActivity extends AppCompatActivity {
    private EditText username, useremail, userpassword;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        setupUIViews();
        firebaseAuth = FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    //uploud data to the database
                    String user_email = useremail.getText().toString().trim();
                    String user_password = userpassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterationActivity.this, "Registeration Successful!!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterationActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(RegisterationActivity.this, "Registeration Failed!!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to move from the registeration activity to the main activity
                startActivity(new Intent(RegisterationActivity.this, MainActivity.class));
            }
        });
    }

    private void setupUIViews() {
        username = (EditText) findViewById(R.id.etName);
        userpassword = (EditText) findViewById(R.id.etPassword);
        useremail = (EditText) findViewById(R.id.etUserEmail);
        regButton = (Button) findViewById(R.id.btnRegister);
        userLogin = (TextView) findViewById(R.id.tvUserLogin);
    }

    private Boolean validate() {
        Boolean result = false;

        String name = username.getText().toString();
        String password = userpassword.getText().toString();
        String email = useremail.getText().toString();

        if (name.isEmpty() && password.isEmpty() && email.isEmpty()) {
            //this will display error message if the user did not enter the details
            Toast.makeText(this, "Please Enter All The Details", Toast.LENGTH_SHORT).show();

        } else {
            result = true;
        }
        return result;
    }
}
