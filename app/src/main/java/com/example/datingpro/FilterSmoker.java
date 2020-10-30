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

public class FilterSmoker extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,noSmoke,yes,someTimes,onlyAfterSex;
    RadioButton askMeRadio,noSmokeRadio,yesRadio,someTimesRadio,onlyAfterSexRadio;
//    String gender,disability,secondDisability,professionText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    FirebaseHelper helper;
    String selectedValue="All";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_smoker);
        String intentSmoker=getIntent().getStringExtra("smokerFilter");
        selectedValue=intentSmoker;
        helper=new FirebaseHelper(this);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressBar.setVisibility(View.VISIBLE);
//                    helper.UpdateFilter(BaseHelper.UserID,gender,ageText,ageMaxText,distanceText, disability, secondDisability,professionText,relationshipStatusText,
//                            bodyHeightText, educationText, selectedValue,religionText,bodyTypeText, languageSpeakText, drinkingText,exerciseText, tattoosText, petsText, musicText, dietText, childrenText);
                    FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("smoker").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(),Filter.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    Animatoo.animateSlideDown(FilterSmoker.this);
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
        noSmokeRadio=(RadioButton)findViewById(R.id.radioNoSmoke);
        yesRadio=(RadioButton)findViewById(R.id.radioYes);
        someTimesRadio=(RadioButton)findViewById(R.id.radioSometimes);
        onlyAfterSexRadio=(RadioButton)findViewById(R.id.radioOnlyAfterSex);

        askMe=(RelativeLayout)findViewById(R.id.askMeRelative);
        askMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                askMeRadio.setChecked(true);
                selectedValue="All";
            }
        });
        noSmoke=(RelativeLayout)findViewById(R.id.noSmokeRelative);
        noSmoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                noSmokeRadio.setChecked(true);
                selectedValue="No";
            }
        });
        yes=(RelativeLayout)findViewById(R.id.yesRelative);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                yesRadio.setChecked(true);
                selectedValue="Yes, regularly";
            }
        });
        someTimes=(RelativeLayout)findViewById(R.id.sometimesRelative);
        someTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                someTimesRadio.setChecked(true);
                selectedValue="Sometimes";
            }
        });
        onlyAfterSex=(RelativeLayout)findViewById(R.id.onlyAfterSexRelative);
        onlyAfterSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                onlyAfterSexRadio.setChecked(true);
                selectedValue="Only After Sex";
            }
        });
        //GetUserFilters();
        if(TextUtils.equals(intentSmoker,getResources().getString(R.string.all)))
        {
            selectedValue="All";
            askMeRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentSmoker,getResources().getString(R.string.no)))
        {
            selectedValue="No";
            noSmokeRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentSmoker,getResources().getString(R.string.yes_regularly)))
        {
            selectedValue="Yes, regularly";
            yesRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentSmoker,getResources().getString(R.string.sometimes)))
        {
            selectedValue="Sometimes";
            someTimesRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentSmoker,getResources().getString(R.string.only_after_sex)))
        {
            selectedValue="Only After Sex";
            onlyAfterSexRadio.setChecked(true);
        }
    }

    public void clearChecks()
    {
        askMeRadio.setChecked(false);
        noSmokeRadio.setChecked(false);
        yesRadio.setChecked(false);
        someTimesRadio.setChecked(false);
        onlyAfterSexRadio.setChecked(false);

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