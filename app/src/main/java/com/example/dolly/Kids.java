package com.example.dolly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.Helper.BaseHelper;
import com.example.dolly.Helper.FirebaseHelper;
import com.google.firebase.database.FirebaseDatabase;

public class Kids extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,want,notWant,haveWant,haveNotWant;
    RadioButton askMeRadio,wantRadio,notWantRadio,haveWantRadio,haveNotWantRadio;
//    String email,gender,dob,image,mobile,userName,name,address,bio,disability,secondDisability,userLat,userLong,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,descriptionText;
    FirebaseHelper helper;
    String selectedValue="Add";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kids);
        helper=new FirebaseHelper(Kids.this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    helper.UpdateProfile(BaseHelper.UserID, name, userName, mobile, email, gender, disability, secondDisability, image, dob, address, userLat, userLong, descriptionText,homeTownText,professionText,lookingForText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,religionText,bodyTypeText, languageSpeakText, drinkingText,exerciseText, tattoosText, petsText, musicText,dietText,selectedValue, bio);
                    FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("children").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ProfileEdit.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Animatoo.animateSlideDown(Kids.this);
                    finish();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Problem with profile update", Toast.LENGTH_SHORT).show();
                }
            }
        });
        askMeRadio=(RadioButton)findViewById(R.id.radioAskMe);
        wantRadio=(RadioButton)findViewById(R.id.radioWantSomeday);
        notWantRadio=(RadioButton)findViewById(R.id.radioNoWant);
        haveWantRadio=(RadioButton)findViewById(R.id.radioHaveWant);
        haveNotWantRadio=(RadioButton)findViewById(R.id.radioHaveNoMore);


        askMe=(RelativeLayout)findViewById(R.id.askMeRelative);
        askMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                askMeRadio.setChecked(true);
                selectedValue="Add";
            }
        });
        want=(RelativeLayout)findViewById(R.id.WantSomedayRelative);
        want.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                wantRadio.setChecked(true);
                selectedValue="Want Someday";
            }
        });
        notWant=(RelativeLayout)findViewById(R.id.NoWantRelative);
        notWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                notWantRadio.setChecked(true);
                selectedValue="Don't Want";
            }
        });
        haveWant=(RelativeLayout)findViewById(R.id.HaveWantRelative);
        haveWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                haveWantRadio.setChecked(true);
                selectedValue="Have & want more";
            }
        });
        haveNotWant=(RelativeLayout)findViewById(R.id.HaveNoMoreRelative);
        haveNotWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                haveNotWantRadio.setChecked(true);
                selectedValue="Have & don't want more";
            }
        });

    }

    public void clearChecks()
    {
        askMeRadio.setChecked(false);
        wantRadio.setChecked(false);
        notWantRadio.setChecked(false);
        haveWantRadio.setChecked(false);
        haveNotWantRadio.setChecked(false);
    }
    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(Kids.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(Kids.this);
    }
}