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

public class FilterDiet extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,omnivore,vegetarian,kosher,halal,other;
    ProgressBar progressBar;
    RadioButton askMeRadio,omnivoreRadio,vegetarianRadio,kosherRadio,halalRadio,otherRadio;
//    String gender,disability,secondDisability,professionText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    FirebaseHelper helper;
    String selectedValue="All";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_diet);
        String intentDiet=getIntent().getStringExtra("dietFilter");
        selectedValue=intentDiet;
        helper=new FirebaseHelper(this);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressBar.setVisibility(View.VISIBLE);
                    FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("diet").setValue(selectedValue);
//                    helper.UpdateFilter(BaseHelper.UserID,gender,ageText,ageMaxText,distanceText, disability, secondDisability,professionText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,religionText,bodyTypeText, languageSpeakText, drinkingText,exerciseText, tattoosText, petsText, musicText, selectedValue, childrenText);
                    Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(),Filter.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    Animatoo.animateSlideDown(FilterDiet.this);
                    finish();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Problem with filter update", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        askMeRadio=(RadioButton)findViewById(R.id.radioAskMe);
        omnivoreRadio=(RadioButton)findViewById(R.id.radioOmnivore);
        vegetarianRadio=(RadioButton)findViewById(R.id.radioVegetarian);
        kosherRadio=(RadioButton)findViewById(R.id.radioKosher);
        halalRadio=(RadioButton)findViewById(R.id.radioHalal);
        otherRadio=(RadioButton)findViewById(R.id.radioOther);



        askMe=(RelativeLayout)findViewById(R.id.askMeRelative);
        askMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                askMeRadio.setChecked(true);
                selectedValue="All";
            }
        });
        omnivore=(RelativeLayout)findViewById(R.id.OmnivoreRelative);
        omnivore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                omnivoreRadio.setChecked(true);
                selectedValue="Omnivore";
            }
        });
        vegetarian=(RelativeLayout)findViewById(R.id.VegetarianRelative);
        vegetarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                vegetarianRadio.setChecked(true);
                selectedValue="Vegetarian";
            }
        });
        kosher=(RelativeLayout)findViewById(R.id.KosherRelative);
        kosher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                kosherRadio.setChecked(true);
                selectedValue="Kosher";
            }
        });
        halal=(RelativeLayout)findViewById(R.id.HalalRelative);
        halal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                halalRadio.setChecked(true);
                selectedValue="Halal";
            }
        });
        other=(RelativeLayout)findViewById(R.id.OtherRelative);
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                otherRadio.setChecked(true);
                selectedValue="Other";
            }
        });
        //GetUserFilters();
        if(TextUtils.equals(intentDiet,getResources().getString(R.string.all)))
        {
            selectedValue="All";
            askMeRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentDiet,getResources().getString(R.string.omnivore)))
        {
            selectedValue="Omnivore";
            omnivoreRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentDiet,getResources().getString(R.string.vegetarian)))
        {
            selectedValue="Vegetarian";
            vegetarianRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentDiet,getResources().getString(R.string.kosher)))
        {
            selectedValue="Kosher";
            kosherRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentDiet,getResources().getString(R.string.halal)))
        {
            selectedValue="Halal";
            halalRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentDiet,getResources().getString(R.string.other)))
        {
            selectedValue="Other";
            otherRadio.setChecked(true);
        }
    }

    public void clearChecks()
    {
        askMeRadio.setChecked(false);
        omnivoreRadio.setChecked(false);
        vegetarianRadio.setChecked(false);
        kosherRadio.setChecked(false);
        halalRadio.setChecked(false);
        otherRadio.setChecked(false);
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