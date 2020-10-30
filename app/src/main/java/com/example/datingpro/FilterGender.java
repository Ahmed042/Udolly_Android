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
import com.example.datingpro.Model.FireBaseModels.UserProfileModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FilterGender extends AppCompatActivity {
    Button save;
    RelativeLayout male,female;
    RadioButton maleRadio,femaleRadio;
//    String gender,disability,secondDisability,professionText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    FirebaseHelper helper;
    String selectedValue="Female";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_gender);
        String intentGender=getIntent().getStringExtra("genderFilter");
        selectedValue=intentGender;
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        helper=new FirebaseHelper(this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressBar.setVisibility(View.VISIBLE);
//                    helper.UpdateFilter(BaseHelper.UserID,selectedValue,ageText,ageMaxText,distanceText, disability, secondDisability,professionText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,religionText,bodyTypeText, languageSpeakText, drinkingText,exerciseText, tattoosText, petsText, musicText, dietText, childrenText);
                    FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("gender").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(),Filter.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    Animatoo.animateSlideDown(FilterGender.this);
                    finish();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Problem with filter update", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        maleRadio=(RadioButton)findViewById(R.id.radioMale);
        femaleRadio=(RadioButton)findViewById(R.id.radioFemale);

        male=(RelativeLayout)findViewById(R.id.MaleRelative);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                maleRadio.setChecked(true);
                selectedValue="Male";
            }
        });
        female=(RelativeLayout)findViewById(R.id.FemaleRelative);
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                femaleRadio.setChecked(true);
                selectedValue="Female";
            }
        });
        if(TextUtils.equals(intentGender,getResources().getString(R.string.male)))
        {
            selectedValue="Male";
            maleRadio.setChecked(true);
        }
        else
        {
            selectedValue="Female";
            femaleRadio.setChecked(true);
        }
        //GetUserFilters();
    }

    public void clearChecks()
    {
        maleRadio.setChecked(false);
        femaleRadio.setChecked(false);
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