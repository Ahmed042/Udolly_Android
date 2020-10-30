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

public class FilterDrinking extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,frequently,socially,never;
    RadioButton askMeRadio,frequentlyRadio,sociallyRadio,neverRadio;
//    String gender,disability,secondDisability,professionText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    FirebaseHelper helper;
    String selectedValue="All";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_drinkng);
        String intentDrinking=getIntent().getStringExtra("drinkingFilter");
        selectedValue=intentDrinking;
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        helper=new FirebaseHelper(this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressBar.setVisibility(View.VISIBLE);
//                    helper.UpdateFilter(BaseHelper.UserID,gender,ageText,ageMaxText,distanceText, disability, secondDisability,professionText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,religionText,bodyTypeText, languageSpeakText, selectedValue,exerciseText, tattoosText, petsText, musicText, dietText, childrenText);
                    FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("drinking").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(),Filter.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    Animatoo.animateSlideDown(FilterDrinking.this);
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
        frequentlyRadio=(RadioButton)findViewById(R.id.radioFrequently);
        sociallyRadio=(RadioButton)findViewById(R.id.radioSocially);
        neverRadio=(RadioButton)findViewById(R.id.radioNever);


        askMe=(RelativeLayout)findViewById(R.id.askMeRelative);
        askMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                askMeRadio.setChecked(true);
                selectedValue="All";
            }
        });
        frequently=(RelativeLayout)findViewById(R.id.FrequentlyRelative);
        frequently.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                frequentlyRadio.setChecked(true);
                selectedValue="Frequently";
            }
        });
        socially=(RelativeLayout)findViewById(R.id.SociallyRelative);
        socially.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                sociallyRadio.setChecked(true);
                selectedValue="Socially";
            }
        });
        never=(RelativeLayout)findViewById(R.id.NeverRelative);
        never.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                neverRadio.setChecked(true);
                selectedValue="Never";
            }
        });
        //GetUserFilters();
        if(TextUtils.equals(intentDrinking,getResources().getString(R.string.all)))
        {
            selectedValue="All";
            askMeRadio.setChecked(true);
        }
       else if(TextUtils.equals(intentDrinking,getResources().getString(R.string.frequently)))
        {
            selectedValue="Frequently";
            frequentlyRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentDrinking,getResources().getString(R.string.socially)))
        {
            selectedValue="Socially";
            sociallyRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentDrinking,getResources().getString(R.string.never)))
        {
            selectedValue="Never";
            neverRadio.setChecked(true);
        }
    }
//    public void GetUserFilters()
//    {
//        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("filters");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    FilterModel model = dataSnapshot.getValue(FilterModel.class);
//                    if(TextUtils.equals(model.getId(), BaseHelper.UserID)) {
//                        ageText=model.getAgeMin();
//                        ageMaxText=model.getAgeMax();
//                        gender=model.getGender();
//                        distanceText=model.getDistance();
//                        disability=model.getSelected_disability();
//                        secondDisability=model.getDisability();
//                        professionText=model.getProfession();
//                        relationshipStatusText=model.getRelationship_status();
//                        bodyHeightText=model.getBody_height();
//                        educationText=model.getEducation();
//                        smokerText=model.getSmoker();
//                        religionText=model.getReligion();
//                        bodyTypeText=model.getBody_type();
//                        languageSpeakText=model.getLanguage();
//                        drinkingText=model.getDrinking();
//                        exerciseText=model.getExercise();
//                        tattoosText=model.getTattoos();
//                        petsText=model.getPets();
//                        musicText=model.getMusic();
//                        dietText=model.getDiet();
//                        childrenText=model.getChildren();
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
    public void clearChecks()
    {
        askMeRadio.setChecked(false);
        frequentlyRadio.setChecked(false);
        sociallyRadio.setChecked(false);
        neverRadio.setChecked(false);
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