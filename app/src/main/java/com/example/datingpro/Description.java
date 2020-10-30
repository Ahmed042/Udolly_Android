package com.example.datingpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.datingpro.Helper.BaseHelper;

public class Description extends AppCompatActivity {
EditText description;
Button btnDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        description=(EditText)findViewById(R.id.editTextDescription);
        btnDesc=(Button)findViewById(R.id.btnDescription);
        btnDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desc=description.getText().toString();
                if(TextUtils.isEmpty(desc))
                {
                    Toast.makeText(getApplicationContext(),"Please enter short description",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(BaseHelper.isGoogle==true||BaseHelper.isFacebook==true)
                    {
                        BaseHelper.description = desc.trim();
                        Intent intent = new Intent(getApplicationContext(), DateOfBirth.class);
                        startActivity(intent);
                        Animatoo.animateFade(Description.this);
                        finish();
                    }
                    else {
                        BaseHelper.description = desc.trim();
                        Intent intent = new Intent(getApplicationContext(), SignUpWithPhone.class);
                        startActivity(intent);
                        Animatoo.animateFade(Description.this);
                        finish();
                    }
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideRight(Description.this);
    }
}