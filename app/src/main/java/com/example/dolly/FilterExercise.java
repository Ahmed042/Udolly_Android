package com.example.dolly;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.dolly.Helper.BaseHelper;
import com.example.dolly.Helper.FirebaseHelper;
import com.google.firebase.database.FirebaseDatabase;

public class FilterExercise extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,active,someTimes,almostNever;
    RadioButton askMeRadio,activeRadio,someTimesRadio,almostNeverRadio;
//    String gender,disability,secondDisability,professionText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    FirebaseHelper helper;
    String selectedValue="All";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_exercise);
        String intentExercise=getIntent().getStringExtra("exerciseFilter");
        selectedValue=intentExercise;
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        helper=new FirebaseHelper(this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressBar.setVisibility(View.VISIBLE);
//                    helper.UpdateFilter(BaseHelper.UserID,gender,ageText,ageMaxText,distanceText, disability, secondDisability,professionText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,religionText,bodyTypeText, languageSpeakText, drinkingText,selectedValue, tattoosText, petsText, musicText, dietText, childrenText);
                    FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("exercise").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(),Filter.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    Animatoo.animateSlideDown(FilterExercise.this);
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
        activeRadio=(RadioButton)findViewById(R.id.radioActive);
        someTimesRadio=(RadioButton)findViewById(R.id.radioSometimes);
        almostNeverRadio=(RadioButton)findViewById(R.id.radioAlmostNever);


        askMe=(RelativeLayout)findViewById(R.id.askMeRelative);
        askMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                askMeRadio.setChecked(true);
                selectedValue="All";
            }
        });
        active=(RelativeLayout)findViewById(R.id.ActiveRelative);
        active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                activeRadio.setChecked(true);
                selectedValue="Active";
            }
        });
        someTimes=(RelativeLayout)findViewById(R.id.SometimesRelative);
        someTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                someTimesRadio.setChecked(true);
                selectedValue="Sometimes";
            }
        });
        almostNever=(RelativeLayout)findViewById(R.id.AlmostNeverRelative);
        almostNever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                almostNeverRadio.setChecked(true);
                selectedValue="Almost Never";
            }
        });
        //GetUserFilters();
        if(TextUtils.equals(intentExercise,getResources().getString(R.string.all)))
        {
            selectedValue="All";
            askMeRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentExercise,getResources().getString(R.string.active)))
        {
            selectedValue="Active";
            activeRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentExercise,getResources().getString(R.string.sometimes)))
        {
            selectedValue="Sometimes";
            someTimesRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentExercise,getResources().getString(R.string.almost_never)))
        {
            selectedValue="Almost Never";
            almostNeverRadio.setChecked(true);
        }
    }

    public void clearChecks()
    {
        askMeRadio.setChecked(false);
        activeRadio.setChecked(false);
        someTimesRadio.setChecked(false);
        almostNeverRadio.setChecked(false);
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