package com.example.shomnathfirstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    EditText mUsernameLogin, mPasswordLogin;
    Button mLoginBtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mUsernameLogin = (EditText) findViewById(R.id.usernameLogin);
        mPasswordLogin = (EditText) findViewById(R.id.passwordLogin);

        mLoginBtn = (Button) findViewById(R.id.btnLogin);

        DB = new DBHelper(this);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameLogin = mUsernameLogin.getText().toString();
                String passwordLogin = mPasswordLogin.getText().toString();

                if (usernameLogin.equals("") || passwordLogin.equals("")) {
                    Toast.makeText(SignIn.this, "Please enter the credentials.", Toast.LENGTH_SHORT).show();
                } else {

                    Boolean checkUsernamePassword = DB.checkUsernamePassword(usernameLogin, passwordLogin);

                    if (checkUsernamePassword) {
                        Toast.makeText(SignIn.this, "Logged in successfully.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        Toast.makeText(SignIn.this, "Username or password is not correct.", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }

}