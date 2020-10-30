package com.example.datingpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.datingpro.Helper.BaseHelper;
import com.example.datingpro.Helper.FirebaseHelper;
import com.example.datingpro.Model.FireBaseModels.FilterModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FilterRelationshipStatus extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,single,inRelation,married,separated,divorced,widowed,openRelationship,complicated;
    RadioButton askMeRadio,singleRadio,inRelationRadio,marriedRadio,separatedRadio,divorcedRadio,widowedRadio,openRelationshipRadio,complicatedRadio;
//    String gender,disability,secondDisability,professionText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    FirebaseHelper helper;
    String selectedValue="All";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_relationship_status);
        String intentRelationship=getIntent().getStringExtra("relationshipFilter");
        selectedValue=intentRelationship;
        helper=new FirebaseHelper(this);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    progressBar.setVisibility(View.VISIBLE);
//                    helper.UpdateFilter(BaseHelper.UserID, gender, ageText,ageMaxText, distanceText, disability, secondDisability, professionText, selectedValue,
//                            bodyHeightText, educationText, smokerText, religionText, bodyTypeText, languageSpeakText, drinkingText, exerciseText, tattoosText, petsText, musicText, dietText, childrenText);
                    FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("relationship_status").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), Filter.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    Animatoo.animateSlideDown(FilterRelationshipStatus.this);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Problem with filter update", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
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
                selectedValue="All";
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
        //GetUserFilters();
        if(TextUtils.equals(intentRelationship,getResources().getString(R.string.all)))
        {
            selectedValue="All";
            askMeRadio.setChecked(true);
        }
       else if(TextUtils.equals(intentRelationship,getResources().getString(R.string.single)))
        {
            selectedValue="Single";
            singleRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentRelationship,getResources().getString(R.string.in_a_relationship)))
        {
            selectedValue="In a relationship";
            inRelationRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentRelationship,getResources().getString(R.string.married)))
        {
            selectedValue="Married";
            marriedRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentRelationship,getResources().getString(R.string.separated)))
        {
            selectedValue="Separated";
            separatedRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentRelationship,getResources().getString(R.string.divorced)))
        {
            selectedValue="Divorced";
            divorcedRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentRelationship,getResources().getString(R.string.widowed)))
        {
            selectedValue="Widowed";
            widowedRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentRelationship,getResources().getString(R.string.in_an_open_relationship)))
        {
            selectedValue="In an open Relationship";
            openRelationshipRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentRelationship,getResources().getString(R.string.it_s_complicated)))
        {
            selectedValue="Complicated";
            complicatedRadio.setChecked(true);
        }
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
        Animatoo.animateSlideDown(this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(this);
    }
}