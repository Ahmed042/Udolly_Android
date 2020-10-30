package com.example.datingpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.datingpro.Helper.BaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginWithPhone extends AppCompatActivity {
    private EditText txt_phone;
    private Button btn_signin;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_phone);
        FirebaseApp.initializeApp(this);
        txt_phone = (EditText) findViewById(R.id.txt_phone);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btn_signin = (Button) findViewById(R.id.btn_signin);
        //Get Firebase auth instance

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone = txt_phone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(LoginWithPhone.this, "Enter Phone Number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //progressBar.setVisibility(View.VISIBLE);
                BaseHelper.phone=phone;
                Intent intent = new Intent(LoginWithPhone.this, PhoneVerification.class);
                startActivity(intent);
                Toast.makeText(LoginWithPhone.this, "We sent you verification code!", Toast.LENGTH_SHORT).show();
                Animatoo.animateFade(LoginWithPhone.this);

                                }

             // end of onclick
        });

    }
}