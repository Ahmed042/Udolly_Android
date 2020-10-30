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
import com.example.datingpro.Model.FireBaseModels.FilterModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FilterBodyType extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,thin,normal,athletic,chubby;
    RadioButton askMeRadio,thinRadio,normalRadio,athleticRadio,chubbyRadio;
    String gender,disability,secondDisability,professionText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    FirebaseHelper helper;
    String selectedValue="All";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_body_type);
        helper=new FirebaseHelper(this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    helper.UpdateFilter(BaseHelper.UserID,gender,ageText,ageMaxText,distanceText, disability, secondDisability,professionText,relationshipStatusText,
                            bodyHeightText, educationText, smokerText,religionText,selectedValue, languageSpeakText, drinkingText,exerciseText, tattoosText, petsText, musicText, dietText, childrenText);
                    Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Filter.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Animatoo.animateSlideDown(FilterBodyType.this);
                    finish();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Problem with filter update", Toast.LENGTH_SHORT).show();
                }
            }
        });
        askMeRadio=(RadioButton)findViewById(R.id.radioAskMe);
        thinRadio=(RadioButton)findViewById(R.id.radioThin);
        normalRadio=(RadioButton)findViewById(R.id.radioNormal);
        athleticRadio=(RadioButton)findViewById(R.id.radioAthletic);
        chubbyRadio=(RadioButton)findViewById(R.id.radioChubby);

        askMe=(RelativeLayout)findViewById(R.id.askMeRelative);
        askMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                askMeRadio.setChecked(true);
                selectedValue="All";
            }
        });
        thin=(RelativeLayout)findViewById(R.id.thinRelative);
        thin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                thinRadio.setChecked(true);
                selectedValue="Thin";
            }
        });
        normal=(RelativeLayout)findViewById(R.id.NormalRelative);
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                normalRadio.setChecked(true);
                selectedValue="Normal";
            }
        });
        athletic=(RelativeLayout)findViewById(R.id.AthleticRelative);
        athletic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                athleticRadio.setChecked(true);
                selectedValue="Athletic";
            }
        });
        chubby=(RelativeLayout)findViewById(R.id.ChubbyRelative);
        chubby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                chubbyRadio.setChecked(true);
                selectedValue="Chubby";
            }
        });
        GetUserFilters();
    }
    public void GetUserFilters()
    {
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("filters");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FilterModel model = dataSnapshot.getValue(FilterModel.class);
                    if(TextUtils.equals(model.getId(), BaseHelper.UserID)) {
                        ageText=model.getAgeMin();
                        ageMaxText=model.getAgeMax();
                        gender=model.getGender();
                        distanceText=model.getDistance();
                        disability=model.getSelected_disability();
                        secondDisability=model.getDisability();
                        professionText=model.getProfession();
                        relationshipStatusText=model.getRelationship_status();
                        //bodyHeightText=model.getBody_height();
                        educationText=model.getEducation();
                        smokerText=model.getSmoker();
                        religionText=model.getReligion();
                        bodyTypeText=model.getBody_type();
                        languageSpeakText=model.getLanguage();
                        drinkingText=model.getDrinking();
                        exerciseText=model.getExercise();
                        tattoosText=model.getTattoos();
                        petsText=model.getPets();
                        musicText=model.getMusic();
                        dietText=model.getDiet();
                        childrenText=model.getChildren();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void clearChecks()
    {
        askMeRadio.setChecked(false);
        thinRadio.setChecked(false);
        normalRadio.setChecked(false);
        athleticRadio.setChecked(false);
        chubbyRadio.setChecked(false);

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