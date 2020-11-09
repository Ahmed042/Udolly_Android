package com.example.dolly;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.Helper.BaseHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class FirstName extends AppCompatActivity {
Button con2;
EditText firstName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_name);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("profiles");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                        BaseHelper.profileSnapshot=snapshot;
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error Logging In...", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        firstName=(EditText)findViewById(R.id.editTextFirstName);
        con2=(Button)findViewById(R.id.btnContinue2);
        con2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first=firstName.getText().toString();

                if(TextUtils.isEmpty(first))
                {
                    Toast.makeText(getApplicationContext(),"Please enter first name",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    BaseHelper.firstName=first;
                    Intent intent = new Intent(getApplicationContext(), Gender.class);
                    startActivity(intent);
                    Animatoo.animateFade(FirstName.this);
                    finish();
                }

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideRight(FirstName.this);
    }
}
