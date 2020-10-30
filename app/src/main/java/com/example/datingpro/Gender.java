package com.example.datingpro;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.datingpro.Helper.BaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Helper;

public class Gender extends AppCompatActivity {
Button wmn,mn,con3;
boolean check=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        con3=(Button)findViewById(R.id.btnContinue3);
        wmn=(Button)findViewById(R.id.btnWmn);
        mn=(Button)findViewById(R.id.btnMn);
        BaseHelper.gender="Male";
        wmn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = true;
                ColorCheck();
                BaseHelper.gender="Female";
                BaseHelper.userGender="Female";
            }
        });
        mn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check=false;
                ColorCheck();
                BaseHelper.gender="Male";
                BaseHelper.userGender="Male";
            }
        });
        con3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Disability.class);
                startActivity(intent);
                Animatoo.animateFade(Gender.this);
                finish();
            }
        });


    }
    void ColorCheck()
    {
        if(check==true)
        {
            wmn.setTextColor(getResources().getColor(R.color.white));
            wmn.setBackground(getResources().getDrawable(R.drawable.roundbuttonw2));
            mn.setTextColor(getResources().getColor(R.color.black));
            mn.setBackground(getResources().getDrawable(R.drawable.roundbutton4));

        }
        else
        {
            wmn.setTextColor(getResources().getColor(R.color.black));
            wmn.setBackground(getResources().getDrawable(R.drawable.roundbutton4));
            mn.setTextColor(getResources().getColor(R.color.white));
            mn.setBackground(getResources().getDrawable(R.drawable.roundbutton2));
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideRight(Gender.this);
    }
}
