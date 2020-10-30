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

public class FilterDistance extends AppCompatActivity {
    Button save;
    RelativeLayout distanceDefault,distance10,distance50,distance100,distance500;
    RadioButton distanceDefaultRadio,distance10Radio,distance50Radio,distance100Radio,distance500Radio;
//    String gender,disability,secondDisability,professionText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    FirebaseHelper helper;
    ProgressBar progressBar;
    String selectedValue= "Distance doesn't matter";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_distance);
        String intentDistance=getIntent().getStringExtra("distanceFilter");
        //selectedValue=intentDistance;
        helper=new FirebaseHelper(this);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressBar.setVisibility(View.VISIBLE);
//                    helper.UpdateFilter(BaseHelper.UserID,gender,ageText,ageMaxText,selectedValue, disability, secondDisability,professionText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,religionText,bodyTypeText, languageSpeakText, drinkingText,exerciseText, tattoosText, petsText, musicText, dietText, childrenText);
                    FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("distance").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(),Filter.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    Animatoo.animateSlideDown(FilterDistance.this);
                    finish();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Problem with filter update", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        distanceDefaultRadio=(RadioButton)findViewById(R.id.radioNoMatter);
        distance10Radio=(RadioButton)findViewById(R.id.radio10km);
        distance50Radio=(RadioButton)findViewById(R.id.radio50km);
        distance100Radio=(RadioButton)findViewById(R.id.radio100km);
        distance500Radio=(RadioButton)findViewById(R.id.radio500km);

        distanceDefault=(RelativeLayout)findViewById(R.id.noMatterRelative);
        distanceDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                distanceDefaultRadio.setChecked(true);
                selectedValue="Distance doesn't matter";
            }
        });
        distance10=(RelativeLayout)findViewById(R.id.km10Relative);
        distance10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                distance10Radio.setChecked(true);
                selectedValue="Distance up to 10 km";
            }
        });
        distance50=(RelativeLayout)findViewById(R.id.km50Relative);
        distance50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                distance50Radio.setChecked(true);
                selectedValue="Distance up to 50 km";
            }
        });
        distance100=(RelativeLayout)findViewById(R.id.km100Relative);
        distance100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                distance100Radio.setChecked(true);
                selectedValue="Distance up to 100 km";
            }
        });
        distance500=(RelativeLayout)findViewById(R.id.km500Relative);
        distance500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                distance500Radio.setChecked(true);
                selectedValue="Distance up to 500 km";
            }
        });
        if(TextUtils.equals(intentDistance,getResources().getString(R.string.distance_doesn_t_matter)))
        {
            selectedValue="Distance doesn't matter";
            distanceDefaultRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentDistance,getResources().getString(R.string.distance_up_to_10_km)))
        {
            selectedValue="Distance up to 10 km";
            distance10Radio.setChecked(true);
        }
        else if(TextUtils.equals(intentDistance,getResources().getString(R.string.distance_up_to_50_km)))
        {
            selectedValue="Distance up to 50 km";
            distance50Radio.setChecked(true);
        }
        else if(TextUtils.equals(intentDistance,getResources().getString(R.string.distance_up_to_100_km)))
        {
            selectedValue="Distance up to 100 km";
            distance100Radio.setChecked(true);
        }
        else if(TextUtils.equals(intentDistance,getResources().getString(R.string.distance_up_to_500_km)))
        {
            selectedValue="Distance up to 500 km";
            distance500Radio.setChecked(true);
        }
        //GetUserFilters();
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
        distanceDefaultRadio.setChecked(false);
        distance10Radio.setChecked(false);
        distance100Radio.setChecked(false);
        distance50Radio.setChecked(false);
        distance500Radio.setChecked(false);
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