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

public class FilterFavouriteSports extends AppCompatActivity {
    Button save;
    FirebaseHelper helper;
    String selectedValue="All";
    ProgressBar progressBar;
    String intentFavourite;
    RelativeLayout askMe,soccer,basket,volley,running,swimming,handBall,tennis,surfing,paddleTennis,fitness,combatSports,extreme,sportsAnimal;
    RadioButton askMeRadio,soccerRadio,basketRadio,volleyRadio,runningRadio,swimmingRadio,handBallRadio,tennisRadio,surfingRadio,paddleTennisRadio,fitnessRadio,combatSportsRadio,extremeRadio,sportsAnimalRadio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_favourite_sports);
        if( getIntent().getExtras() != null)
        {
            intentFavourite=getIntent().getStringExtra("favouriteFilter");
            selectedValue=intentFavourite;
        }
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        helper=new FirebaseHelper(this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressBar.setVisibility(View.VISIBLE);
//                    helper.UpdateFilter(BaseHelper.UserID,gender,ageText,ageMaxText,distanceText, disability, secondDisability,professionText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,selectedValue,bodyTypeText, languageSpeakText, drinkingText,exerciseText, tattoosText, petsText, musicText, dietText, childrenText);
                    FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("favourite_sports").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(),Filter.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    Animatoo.animateSlideDown(FilterFavouriteSports.this);
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
                selectedValue="All";
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
        if(TextUtils.equals(intentFavourite,getResources().getString(R.string.all)))
        {
            selectedValue="All";
            askMeRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentFavourite,getResources().getString(R.string.soccer)))
        {
            selectedValue="Soccer";
            soccerRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentFavourite,getResources().getString(R.string.basketball)))
        {
            selectedValue="Basket ball";
            basketRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentFavourite,getResources().getString(R.string.volleyball)))
        {
            selectedValue="Volley ball";
            volleyRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentFavourite,getResources().getString(R.string.running)))
        {
            selectedValue="Running";
            runningRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentFavourite,getResources().getString(R.string.swimming)))
        {
            selectedValue="Swimming";
            swimmingRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentFavourite,getResources().getString(R.string.hand_ball)))
        {
            selectedValue="Hand Ball";
            handBallRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentFavourite,getResources().getString(R.string.tennis)))
        {
            selectedValue="Tennis";
            tennisRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentFavourite,getResources().getString(R.string.surfing)))
        {
            selectedValue="Surfing";
            surfingRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentFavourite,getResources().getString(R.string.paddle_tennis)))
        {
            selectedValue="Paddle Tennis";
            paddleTennisRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentFavourite,getResources().getString(R.string.fitness)))
        {
            selectedValue="Fitness";
            fitnessRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentFavourite,getResources().getString(R.string.combat_sports)))
        {
            selectedValue="Combat Sports";
            combatSportsRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentFavourite,getResources().getString(R.string.extreme_sports)))
        {
            selectedValue="Extreme Sports";
            extremeRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentFavourite,getResources().getString(R.string.sports_with_animals)))
        {
            selectedValue="Sports with animals";
            sportsAnimalRadio.setChecked(true);
        }
//        if(TextUtils.equals(intentFavourite,getResources().getString(R.string.all)))
//        {
//            selectedValue="All";
//            askMeRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentFavourite,"Soccer"))
//        {
//            soccerRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentFavourite,"Basket ball"))
//        {
//            basketRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentFavourite,"Volley ball"))
//        {
//            volleyRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentFavourite,"Running"))
//        {
//            runningRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentFavourite,"Swimming"))
//        {
//            swimmingRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentFavourite,"Hand Ball"))
//        {
//            handBallRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentFavourite,"Tennis"))
//        {
//            tennisRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentFavourite,"Fitness"))
//        {
//            fitnessRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentFavourite,"Combat Sports"))
//        {
//            combatSportsRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentFavourite,"Extreme Sports"))
//        {
//            extremeRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentFavourite,"Sports with animals"))
//        {
//            sportsAnimalRadio.setChecked(true);
//        }

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