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

public class Pets extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,dog,cat,bird,rabbit,lots,none,notWant;
    RadioButton askMeRadio,dogRadio,catRadio,birdRadio,rabbitRadio,lotsRadio,noneRadio,notWantRadio;
//    String email,gender,dob,image,mobile,userName,name,address,bio,disability,secondDisability,userLat,userLong,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,descriptionText;
    FirebaseHelper helper;
    String selectedValue="Add";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);
        helper=new FirebaseHelper(Pets.this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    helper.UpdateProfile(BaseHelper.UserID, name, userName, mobile, email, gender, disability, secondDisability, image, dob, address, userLat, userLong, descriptionText,homeTownText,professionText,lookingForText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,religionText,bodyTypeText, languageSpeakText, drinkingText,exerciseText, tattoosText, selectedValue, musicText, dietText, childrenText, bio);
                    FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("pets").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ProfileEdit.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Animatoo.animateSlideDown(Pets.this);
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
        dogRadio=(RadioButton)findViewById(R.id.radioDog);
        catRadio=(RadioButton)findViewById(R.id.radioCat);
        birdRadio=(RadioButton)findViewById(R.id.radioBird);
        rabbitRadio=(RadioButton)findViewById(R.id.radioRabbit);
        lotsRadio=(RadioButton)findViewById(R.id.radioLots);
        notWantRadio=(RadioButton)findViewById(R.id.radioNotWant);


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
        dog=(RelativeLayout)findViewById(R.id.DogRelative);
        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                dogRadio.setChecked(true);
                selectedValue="Dog( s )";
            }
        });
        cat=(RelativeLayout)findViewById(R.id.CatRelative);
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                catRadio.setChecked(true);
                selectedValue="Cat( s )";
            }
        });
        bird=(RelativeLayout)findViewById(R.id.BirdRelative);
        bird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                birdRadio.setChecked(true);
                selectedValue="Bird( s )";
            }
        });
        rabbit=(RelativeLayout)findViewById(R.id.RabbitRelative);
        rabbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                rabbitRadio.setChecked(true);
                selectedValue="Rabbit";
            }
        });
        lots=(RelativeLayout)findViewById(R.id.LotsRelative);
        lots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                lotsRadio.setChecked(true);
                selectedValue="Lots";
            }
        });
        notWant=(RelativeLayout)findViewById(R.id.NotWantRelative);
        notWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                notWantRadio.setChecked(true);
                selectedValue="Don't Want";
            }
        });

    }

    public void clearChecks()
    {
        askMeRadio.setChecked(false);
        noneRadio.setChecked(false);
        dogRadio.setChecked(false);
        catRadio.setChecked(false);
        birdRadio.setChecked(false);
        rabbitRadio.setChecked(false);
        lotsRadio.setChecked(false);
        notWantRadio.setChecked(false);
    }
    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(Pets.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(Pets.this);
    }
}