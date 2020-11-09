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

public class FilterPets extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,dog,cat,bird,rabbit,lots,none,notWant;
    RadioButton askMeRadio,dogRadio,birdRadio,rabbitRadio,catRadio,lotsRadio,noneRadio,notWantRadio;
//    String gender,disability,secondDisability,professionText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    FirebaseHelper helper;
    String selectedValue="All";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_pets);
        String intentPets=getIntent().getStringExtra("petsFilter");
        selectedValue=intentPets;
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        helper=new FirebaseHelper(this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressBar.setVisibility(View.VISIBLE);
//                    helper.UpdateFilter(BaseHelper.UserID,gender,ageText,ageMaxText,distanceText, disability, secondDisability,professionText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,religionText,bodyTypeText, languageSpeakText, drinkingText,exerciseText, tattoosText, selectedValue, musicText, dietText, childrenText);
                    FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("pets").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(),Filter.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    Animatoo.animateSlideDown(FilterPets.this);
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
        noneRadio=(RadioButton)findViewById(R.id.radioNone);
        dogRadio=(RadioButton)findViewById(R.id.radioDog);
        catRadio=(RadioButton)findViewById(R.id.radioCat);
        birdRadio=(RadioButton)findViewById(R.id.radioBird);
        rabbitRadio=(RadioButton)findViewById(R.id.radioRabbit);
        lotsRadio=(RadioButton)findViewById(R.id.radioLots);
        notWantRadio=(RadioButton)findViewById(R.id.radioNotWant);


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
        dog=(RelativeLayout)findViewById(R.id.DogRelative);
        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                dogRadio.setChecked(true);
                selectedValue="Dog( s )";
            }
        });
        cat=(RelativeLayout)findViewById(R.id.CatRelative);
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                catRadio.setChecked(true);
                selectedValue="Cat( s )";
            }
        });
        bird=(RelativeLayout)findViewById(R.id.BirdRelative);
        bird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                birdRadio.setChecked(true);
                selectedValue="Bird( s )";
            }
        });
        rabbit=(RelativeLayout)findViewById(R.id.RabbitRelative);
        rabbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                rabbitRadio.setChecked(true);
                selectedValue="Rabbit";
            }
        });
        lots=(RelativeLayout)findViewById(R.id.LotsRelative);
        lots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                lotsRadio.setChecked(true);
                selectedValue="Lots";
            }
        });
        notWant=(RelativeLayout)findViewById(R.id.NotWantRelative);
        notWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                notWantRadio.setChecked(true);
                selectedValue="Don't Want";
            }
        });
        //GetUserFilters();
        if(TextUtils.equals(intentPets,getResources().getString(R.string.all)))
        {
            selectedValue="All";
            askMeRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentPets,getResources().getString(R.string.none)))
        {
            selectedValue="None";
            noneRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentPets,getResources().getString(R.string.dog_s)))
        {
            selectedValue="Dog( s )";
            dogRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentPets,getResources().getString(R.string.cat_s)))
        {
            selectedValue="Cat( s )";
            catRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentPets,getResources().getString(R.string.bird_s)))
        {
            selectedValue="Bird( s )";
            birdRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentPets,getResources().getString(R.string.rabbit)))
        {
            selectedValue="Rabbit";
            rabbitRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentPets,getResources().getString(R.string.lots)))
        {
            selectedValue="Lots";
            lotsRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentPets,getResources().getString(R.string.don_t_want)))
        {
            selectedValue="Don't Want";
            notWantRadio.setChecked(true);
        }
//        if(TextUtils.equals(intentPets,getResources().getString(R.string.all)))
//        {
//            selectedValue="All";
//            askMeRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentPets,"None"))
//        {
//            noneRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentPets,"Dog( s )"))
//        {
//            dogRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentPets,"Cat( s )"))
//        {
//            catRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentPets,"Bird( s )"))
//        {
//            birdRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentPets,"Rabbit"))
//        {
//            rabbitRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentPets,"Lots"))
//        {
//            lotsRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentPets,"Don't Want"))
//        {
//            notWantRadio.setChecked(true);
//        }
    }

    public void clearChecks()
    {
        askMeRadio.setChecked(false);
        noneRadio.setChecked(false);
        dogRadio.setChecked(false);
        catRadio.setChecked(false);
        birdRadio.setChecked(false);
        rabbitRadio.setChecked(false);
        lotsRadio.setChecked(false);
        notWantRadio.setChecked(false);
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