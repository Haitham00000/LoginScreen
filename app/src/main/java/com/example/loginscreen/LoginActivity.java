package com.example.loginscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {


    private EditText Name, Password;
    private TextView Info;
    private Button Login;
    private int counter = 3;
    private TextView userRegisteration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        userRegisteration = (TextView) findViewById(R.id.tvRegister);
        Info.setText("No of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                // validate(Name.getText().toString(), Password.getText().toString());
            }
        });
        userRegisteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterationActivity.class));
            }
        });
    }

    private void validate(String userName, String userPassword) {
        if ((userName == "Admin") && (userPassword == "1234")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            //to move to the next activity
            startActivity(intent);
        } else {
            counter--;

            Info.setText("No of attempts remaining: " + String.valueOf(counter));

            if (counter == 0) {
                Login.setEnabled(false);
            }
        }

    }

}