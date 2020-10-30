package com.example.datingpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.datingpro.Helper.BaseHelper;
import com.example.datingpro.Helper.FirebaseHelper;
import com.example.datingpro.Model.FireBaseModels.UserProfileModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Tattoos extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,none,many,some,inconspicuous;
    RadioButton askMeRadio,noneRadio,manyRadio,someRadio,inconspicuousRadio;
//    String email,gender,dob,image,mobile,userName,name,address,bio,disability,secondDisability,userLat,userLong,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,descriptionText;
    FirebaseHelper helper;
    String selectedValue="Add";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tattoos);
        helper=new FirebaseHelper(Tattoos.this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    helper.UpdateProfile(BaseHelper.UserID, name, userName, mobile, email, gender, disability, secondDisability, image, dob, address, userLat, userLong, descriptionText,homeTownText,professionText,lookingForText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,religionText,bodyTypeText, languageSpeakText, drinkingText,exerciseText, selectedValue, petsText, musicText, dietText, childrenText, bio);
                    FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("tattoos").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ProfileEdit.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Animatoo.animateSlideDown(Tattoos.this);
                    finish();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Problem with profile update", Toast.LENGTH_SHORT).show();
                }
            }
        });
        askMeRadio=(RadioButton)findViewById(R.id.radioAskMe);
        noneRadio=(RadioButton)findViewById(R.id.radioNone);
        manyRadio=(RadioButton)findViewById(R.id.radioMany);
        someRadio=(RadioButton)findViewById(R.id.radioSome);
        inconspicuousRadio=(RadioButton)findViewById(R.id.radioInconspicuous);


        askMe=(RelativeLayout)findViewById(R.id.askMeRelative);
        askMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                askMeRadio.setChecked(true);
                selectedValue="Add";
            }
        });
        none=(RelativeLayout)findViewById(R.id.NoneRelative);
        none.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                noneRadio.setChecked(true);
                selectedValue="None";
            }
        });
        many=(RelativeLayout)findViewById(R.id.ManyRelative);
        many.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                manyRadio.setChecked(true);
                selectedValue="Many";
            }
        });
        some=(RelativeLayout)findViewById(R.id.SomeRelative);
        some.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                someRadio.setChecked(true);
                selectedValue="Some";
            }
        });
        inconspicuous=(RelativeLayout)findViewById(R.id.InconspicuousRelative);
        inconspicuous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                inconspicuousRadio.setChecked(true);
                selectedValue="Only Inconspicuous";
            }
        });

    }

    public void clearChecks()
    {
        askMeRadio.setChecked(false);
        noneRadio.setChecked(false);
        manyRadio.setChecked(false);
        someRadio.setChecked(false);
        inconspicuousRadio.setChecked(false);
    }
    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(Tattoos.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(Tattoos.this);
    }
}