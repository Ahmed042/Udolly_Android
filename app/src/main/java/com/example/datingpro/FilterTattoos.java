package com.example.datingpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class FilterTattoos extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,none,many,some,inconspicuous;
    RadioButton askMeRadio,noneRadio,manyRadio,someRadio,inconspicuousRadio;
//    String gender,disability,secondDisability,professionText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    FirebaseHelper helper;
    String selectedValue="All";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_tattoos);
        String intentTattoos=getIntent().getStringExtra("tattoosFilter");
        selectedValue=intentTattoos;
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        helper=new FirebaseHelper(this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressBar.setVisibility(View.VISIBLE);
//                    helper.UpdateFilter(BaseHelper.UserID,gender,ageText,ageMaxText,distanceText, disability, secondDisability,professionText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,religionText,bodyTypeText, languageSpeakText, drinkingText,exerciseText, selectedValue, petsText, musicText, dietText, childrenText);
                    FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("tattoos").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(),Filter.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    Animatoo.animateSlideDown(FilterTattoos.this);
                    finish();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Problem with filter update", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);
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
                selectedValue="All";
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
        //GetUserFilters();
        if(TextUtils.equals(intentTattoos,getResources().getString(R.string.all)))
        {
            selectedValue="All";
            askMeRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentTattoos,getResources().getString(R.string.none)))
        {
            selectedValue="None";
            noneRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentTattoos,getResources().getString(R.string.many)))
        {
            selectedValue="Many";
            manyRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentTattoos,getResources().getString(R.string.some)))
        {
            selectedValue="Some";
            someRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentTattoos,getResources().getString(R.string.only_inconspicuous)))
        {
            selectedValue="Only Inconspicuous";
            inconspicuousRadio.setChecked(true);
        }
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
        Animatoo.animateSlideDown(this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(this);
    }
}