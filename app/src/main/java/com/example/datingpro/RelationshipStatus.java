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

public class RelationshipStatus extends AppCompatActivity {
Button save;
RelativeLayout askMe,single,inRelation,married,separated,divorced,widowed,openRelationship,complicated;
RadioButton askMeRadio,singleRadio,inRelationRadio,marriedRadio,separatedRadio,divorcedRadio,widowedRadio,openRelationshipRadio,complicatedRadio;
//    String email,gender,dob,image,mobile,userName,name,address,bio,disability,secondDisability,userLat,userLong,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,descriptionText;
FirebaseHelper helper;
String selectedValue="Add";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relationship_status);
        helper=new FirebaseHelper(RelationshipStatus.this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    helper.UpdateProfile(BaseHelper.UserID, name, userName, mobile, email, gender, disability, secondDisability, image, dob, address, userLat, userLong, descriptionText, homeTownText, professionText, lookingForText, selectedValue,
//                            bodyHeightText, educationText, smokerText, religionText, bodyTypeText, languageSpeakText, drinkingText, exerciseText, tattoosText, petsText, musicText, dietText, childrenText, bio);
                    FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("relationship_status").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), ProfileEdit.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    Animatoo.animateSlideDown(RelationshipStatus.this);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Problem with profile update", Toast.LENGTH_SHORT).show();
                }
            }
        });
        askMeRadio=(RadioButton)findViewById(R.id.radioAskMe);
        singleRadio=(RadioButton)findViewById(R.id.radioSingle);
        inRelationRadio=(RadioButton)findViewById(R.id.radioRelation);
        marriedRadio=(RadioButton)findViewById(R.id.radioMarried);
        separatedRadio=(RadioButton)findViewById(R.id.radioSeparated);
        divorcedRadio=(RadioButton)findViewById(R.id.radioDivorced);
        widowedRadio=(RadioButton)findViewById(R.id.radioWidowed);
        openRelationshipRadio=(RadioButton)findViewById(R.id.radioOpenRelation);
        complicatedRadio=(RadioButton)findViewById(R.id.radioComplicated);

        askMe=(RelativeLayout)findViewById(R.id.askMeRelative);
        askMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                askMeRadio.setChecked(true);
                selectedValue="Add";
            }
        });
        single=(RelativeLayout)findViewById(R.id.singleRelative);
        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                singleRadio.setChecked(true);
                selectedValue="Single";
            }
        });
        inRelation=(RelativeLayout)findViewById(R.id.relationShipRelative);
        inRelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                inRelationRadio.setChecked(true);
                selectedValue="In a relationship";
            }
        });
        married=(RelativeLayout)findViewById(R.id.marriedRelative);
        married.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                marriedRadio.setChecked(true);
                selectedValue="Married";
            }
        });
        separated=(RelativeLayout)findViewById(R.id.separatedRelative);
        separated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                separatedRadio.setChecked(true);
                selectedValue="Separated";
            }
        });
        divorced=(RelativeLayout)findViewById(R.id.divorcedRelative);
        divorced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                divorcedRadio.setChecked(true);
                selectedValue="Divorced";
            }
        });
        widowed=(RelativeLayout)findViewById(R.id.widowedRelative);
        widowed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                widowedRadio.setChecked(true);
                selectedValue="Widowed";
            }
        });
        openRelationship=(RelativeLayout)findViewById(R.id.OpenRelationShipRelative);
        openRelationship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                openRelationshipRadio.setChecked(true);
                selectedValue="In an open Relationship";
            }
        });
        complicated=(RelativeLayout)findViewById(R.id.complicatedRelative);
        complicated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                complicatedRadio.setChecked(true);
                selectedValue="Complicated";
            }
        });
        //GetUserProfile();
    }

    public void clearChecks()
    {
        askMeRadio.setChecked(false);
        singleRadio.setChecked(false);
        inRelationRadio.setChecked(false);
        marriedRadio.setChecked(false);
        separatedRadio.setChecked(false);
        divorcedRadio.setChecked(false);
        widowedRadio.setChecked(false);
        openRelationshipRadio.setChecked(false);
        complicatedRadio.setChecked(false);
    }
    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(RelationshipStatus.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(RelationshipStatus.this);
    }
}
