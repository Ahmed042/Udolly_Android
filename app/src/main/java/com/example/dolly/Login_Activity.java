package com.example.dolly;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.Helper.BaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Login_Activity extends AppCompatActivity {
    private EditText txt_pwd, txt_email;
    private Button btn_signin;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        FirebaseApp.initializeApp(this);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_pwd = (EditText) findViewById(R.id.txt_pwd);
        TextView forget=(TextView)findViewById(R.id.forgettxt);
        TextView txt_signUp=(TextView)findViewById(R.id.btn_signUp);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btn_signin = (Button) findViewById(R.id.btn_signin);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Activity.this,ForgetPassword.class));
                Animatoo.animateSlideLeft(Login_Activity.this);
            }
        });
        //Get Firebase auth instance

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {

//            Toast.makeText(Login_Activity.this, "Already User Session Existed "+auth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(Login_Activity.this, MainHome.class));
//            finish();
        }

        txt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseHelper.isGoogle=false;
                BaseHelper.isFacebook=false;
                startActivity(new Intent(Login_Activity.this, Welcome.class));
                Animatoo.animateSlideLeft(Login_Activity.this);
            }
        });
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = txt_email.getText().toString();
                final String password = txt_pwd.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login_Activity.this, "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login_Activity.this, "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        txt_pwd.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(Login_Activity.this, getString(R.string.auth_failed),
                                                Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(Login_Activity.this, MainHome.class);
                                    startActivity(intent);
                                    Toast.makeText(Login_Activity.this, "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                                    Animatoo.animateSlideUp(Login_Activity.this);
                                    finish();
                                }
                            }
                        });
            } // end of onclick
        });

    }


}
