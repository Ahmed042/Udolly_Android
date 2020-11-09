package com.example.dolly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class FavouriteSports extends AppCompatActivity {
    Button save;
    FirebaseHelper helper;
    String selectedValue="Add";
    ProgressBar progressBar;
    RelativeLayout askMe,soccer,basket,volley,running,swimming,handBall,tennis,surfing,paddleTennis,fitness,combatSports,extreme,sportsAnimal;
    RadioButton askMeRadio,soccerRadio,basketRadio,volleyRadio,runningRadio,swimmingRadio,handBallRadio,tennisRadio,surfingRadio,paddleTennisRadio,fitnessRadio,combatSportsRadio,extremeRadio,sportsAnimalRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_sports);
        helper=new FirebaseHelper(this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

//                    helper.UpdateFilter(BaseHelper.UserID,gender,ageText,ageMaxText,distanceText, disability, secondDisability,professionText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,selectedValue,bodyTypeText, languageSpeakText, drinkingText,exerciseText, tattoosText, petsText, musicText, dietText, childrenText);
                    FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("favourite_sports").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ProfileEdit.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Animatoo.animateSlideDown(FavouriteSports.this);
                    finish();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Problem with filter update", Toast.LENGTH_SHORT).show();

                }
            }
        });
        askMeRadio=(RadioButton)findViewById(R.id.radioAskMe);
        soccerRadio=(RadioButton)findViewById(R.id.radioSoccer);
        basketRadio=(RadioButton)findViewById(R.id.radioBasketBall);
        volleyRadio=(RadioButton)findViewById(R.id.radioVolley);
        runningRadio=(RadioButton)findViewById(R.id.radioRunning);
        swimmingRadio=(RadioButton)findViewById(R.id.radioSwimming);
        handBallRadio=(RadioButton)findViewById(R.id.radioHandBall);
        tennisRadio=(RadioButton)findViewById(R.id.radioTennis);
        surfingRadio=(RadioButton)findViewById(R.id.radioSurfing);
        paddleTennisRadio=(RadioButton)findViewById(R.id.radioPaddle);
        fitnessRadio=(RadioButton)findViewById(R.id.radioFitness);
        combatSportsRadio=(RadioButton)findViewById(R.id.radioCombatSports);
        extremeRadio=(RadioButton)findViewById(R.id.radioExtremeSports);
        sportsAnimalRadio=(RadioButton)findViewById(R.id.radioSportsAnimals);

        askMe=(RelativeLayout)findViewById(R.id.askMeRelative);
        askMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                askMeRadio.setChecked(true);
                selectedValue="Add";
            }
        });
        soccer=(RelativeLayout)findViewById(R.id.SoccerRelative);
        soccer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                soccerRadio.setChecked(true);
                selectedValue="Soccer";
            }
        });
        basket=(RelativeLayout)findViewById(R.id.BasketBallRelative);
        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                basketRadio.setChecked(true);
                selectedValue="Basket ball";
            }
        });
        volley=(RelativeLayout)findViewById(R.id.VolleyRelative);
        volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                volleyRadio.setChecked(true);
                selectedValue="Volley ball";
            }
        });
        running=(RelativeLayout)findViewById(R.id.RunningRelative);
        running.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                runningRadio.setChecked(true);
                selectedValue="Running";
            }
        });
        swimming=(RelativeLayout)findViewById(R.id.SwimmingRelative);
        swimming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                swimmingRadio.setChecked(true);
                selectedValue="Swimming";
            }
        });
        handBall=(RelativeLayout)findViewById(R.id.HandBallRelative);
        handBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                handBallRadio.setChecked(true);
                selectedValue="Hand Ball";
            }
        });
        tennis=(RelativeLayout)findViewById(R.id.TennisRelative);
        tennis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                tennisRadio.setChecked(true);
                selectedValue="Tennis";
            }
        });
        surfing=(RelativeLayout)findViewById(R.id.SurfingRelative);
        surfing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                surfingRadio.setChecked(true);
                selectedValue="Surfing";
            }
        });
        paddleTennis=(RelativeLayout)findViewById(R.id.PaddleRelative);
        paddleTennis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                paddleTennisRadio.setChecked(true);
                selectedValue="Paddle Tennis";
            }
        });
        fitness=(RelativeLayout)findViewById(R.id.FitnessRelative);
        fitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                fitnessRadio.setChecked(true);
                selectedValue="Fitness";
            }
        });
        combatSports=(RelativeLayout)findViewById(R.id.CombatSportsRelative);
        combatSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                combatSportsRadio.setChecked(true);
                selectedValue="Combat Sports";
            }
        });
        extreme=(RelativeLayout)findViewById(R.id.ExtremeSportsRelative);
        extreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                extremeRadio.setChecked(true);
                selectedValue="Extreme Sports";
            }
        });
        sportsAnimal=(RelativeLayout)findViewById(R.id.SportsAnimalsRelative);
        sportsAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                sportsAnimalRadio.setChecked(true);
                selectedValue="Sports with animals";
            }
        });
    }
    public void clearChecks()
    {
        askMeRadio.setChecked(false);
        soccerRadio.setChecked(false);
        basketRadio.setChecked(false);
        volleyRadio.setChecked(false);
        runningRadio.setChecked(false);
        swimmingRadio.setChecked(false);
        handBallRadio.setChecked(false);
        tennisRadio.setChecked(false);
        surfingRadio.setChecked(false);
        paddleTennisRadio.setChecked(false);
        fitnessRadio.setChecked(false);
        combatSportsRadio.setChecked(false);
        extremeRadio.setChecked(false);
        sportsAnimalRadio.setChecked(false);
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