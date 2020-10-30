package com.example.datingpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class Music extends AppCompatActivity {
    Button save;
    RelativeLayout pop,rock,hipHop,jazz,rnb,classical,folk,kPop;
    ImageView popImage,rockImage,hipHopImage,jazzImage,rnbImage,classicalImage,folkImage,kPopImage;
//    String email,gender,dob,image,mobile,userName,name,address,bio,disability,secondDisability,userLat,userLong,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,descriptionText;
    FirebaseHelper helper;
    String music="";
    String popMusic="",rockMusic="",hipHopMusic="",jazzMusic="",rnbMusic="",classicalMusic="",folkMusic="",kPopMusic="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        helper=new FirebaseHelper(Music.this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(TextUtils.isEmpty(popMusic)&&TextUtils.isEmpty(rockMusic)&&TextUtils.isEmpty(hipHopMusic)&&TextUtils.isEmpty(jazzMusic)&&TextUtils.isEmpty(rnbMusic)&&TextUtils.isEmpty(classicalMusic)&&TextUtils.isEmpty(folkMusic)&&TextUtils.isEmpty(kPopMusic))
                    {
                        music="Add";
                    }
                    else {
                        music=popMusic+rockMusic+hipHopMusic+jazzMusic+rnbMusic+classicalMusic+folkMusic+kPopMusic;
                    }
//                    helper.UpdateProfile(BaseHelper.UserID, name, userName, mobile, email, gender, disability, secondDisability, image, dob, address, userLat, userLong, descriptionText,homeTownText,professionText,lookingForText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,religionText, bodyTypeText,languageSpeakText, drinkingText, exerciseText, tattoosText, petsText, music, dietText, childrenText, bio);
                    FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("music").setValue(music);
                    Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ProfileEdit.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Animatoo.animateSlideDown(Music.this);
                    finish();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Problem with profile update", Toast.LENGTH_SHORT).show();
                }
            }
        });
        popImage=(ImageView) findViewById(R.id.imagePop);
        rockImage=(ImageView)findViewById(R.id.imageRock);
        hipHopImage=(ImageView)findViewById(R.id.imageHipHop);
        jazzImage=(ImageView)findViewById(R.id.imageJazz);
        rnbImage=(ImageView)findViewById(R.id.imageRnB);
        classicalImage=(ImageView)findViewById(R.id.imageClassical);
        folkImage=(ImageView)findViewById(R.id.imageFolk);
        kPopImage=(ImageView)findViewById(R.id.imageKPop);

        pop=(RelativeLayout)findViewById(R.id.PopRelative);
        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(popImage.getVisibility()==View.VISIBLE)
                {
                    popImage.setVisibility(View.GONE);
                    popMusic="";
                }
                else
                {
                    popImage.setVisibility(View.VISIBLE);
                    popMusic="Pop, ";
                }
            }
        });
        rock=(RelativeLayout)findViewById(R.id.RockRelative);
        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rockImage.getVisibility()==View.VISIBLE)
                {
                    rockImage.setVisibility(View.GONE);
                    rockMusic="";
                }
                else
                {
                    rockImage.setVisibility(View.VISIBLE);
                    rockMusic="Rock, ";
                }
            }
        });
        hipHop=(RelativeLayout)findViewById(R.id.HipHopRelative);
        hipHop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hipHopImage.getVisibility()==View.VISIBLE)
                {
                    hipHopImage.setVisibility(View.GONE);
                    hipHopMusic="";
                }
                else
                {
                    hipHopImage.setVisibility(View.VISIBLE);
                    hipHopMusic="Hip-Hop, ";
                }
            }
        });
        jazz=(RelativeLayout)findViewById(R.id.JazzRelative);
        jazz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(jazzImage.getVisibility()==View.VISIBLE)
                {
                    jazzImage.setVisibility(View.GONE);
                    jazzMusic="";
                }
                else
                {
                    jazzImage.setVisibility(View.VISIBLE);
                    jazzMusic="Jazz, ";
                }
            }
        });
        rnb=(RelativeLayout)findViewById(R.id.RnBRelative);
        rnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rnbImage.getVisibility()==View.VISIBLE)
                {
                    rnbImage.setVisibility(View.GONE);
                    rnbMusic="";
                }
                else
                {
                    rnbImage.setVisibility(View.VISIBLE);
                    rnbMusic="RnB, ";
                }
            }
        });
        classical=(RelativeLayout)findViewById(R.id.ClassicalRelative);
        classical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(classicalImage.getVisibility()==View.VISIBLE)
                {
                    classicalImage.setVisibility(View.GONE);
                    classicalMusic="";
                }
                else
                {
                    classicalImage.setVisibility(View.VISIBLE);
                    classicalMusic="Classical, ";
                }
            }
        });
        folk=(RelativeLayout)findViewById(R.id.FolkRelative);
        folk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(folkImage.getVisibility()==View.VISIBLE)
                {
                    folkImage.setVisibility(View.GONE);
                    folkMusic="";
                }
                else
                {
                    folkImage.setVisibility(View.VISIBLE);
                    folkMusic="Folk, ";
                }
            }
        });
        kPop=(RelativeLayout)findViewById(R.id.KPopRelative);
        kPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kPopImage.getVisibility()==View.VISIBLE)
                {
                    kPopImage.setVisibility(View.GONE);
                    kPopMusic="";
                }
                else
                {
                    kPopImage.setVisibility(View.VISIBLE);
                    kPopMusic="K-Pop, ";
                }
            }
        });

    }

    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(Music.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(Music.this);
    }
}