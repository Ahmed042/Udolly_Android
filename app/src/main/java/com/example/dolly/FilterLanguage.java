package com.example.dolly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.Helper.BaseHelper;
import com.example.dolly.Helper.FirebaseHelper;
import com.google.firebase.database.FirebaseDatabase;

public class FilterLanguage extends AppCompatActivity {
    Button save;
    RelativeLayout english,arabic,spanish,urdu,turkish;
    ImageView englishImage,arabicImage,spanishImage,urduImage,turkishImage;
//    String gender,disability,secondDisability,professionText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    FirebaseHelper helper;
    String languages="English";
    String arabicLang="",spanishLang="",urduLang="",turkishLang="";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_language);
        helper=new FirebaseHelper(this);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressBar.setVisibility(View.VISIBLE);
                    String lang=languages+arabicLang+spanishLang+urduLang+turkishLang;

//                    helper.UpdateFilter(BaseHelper.UserID,gender,ageText,ageMaxText,distanceText, disability, secondDisability,professionText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,religionText,bodyTypeText, lang, drinkingText,exerciseText, tattoosText, petsText, musicText, dietText, childrenText);
                    FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("language").setValue(lang);
                    Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Filter.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Animatoo.animateSlideDown(FilterLanguage.this);
                    finish();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Problem with filter update", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        englishImage=(ImageView) findViewById(R.id.imageEnglish);
        arabicImage=(ImageView)findViewById(R.id.imageArabic);
        spanishImage=(ImageView)findViewById(R.id.imageSpanish);
        urduImage=(ImageView)findViewById(R.id.imageUrdu);
        turkishImage=(ImageView)findViewById(R.id.imageTurkish);

        english=(RelativeLayout)findViewById(R.id.EnglishRelative);
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(englishImage.getVisibility()==View.VISIBLE)
//                {
//                    englishImage.setVisibility(View.GONE);
//
//                }
//                else
//                {
//                    englishImage.setVisibility(View.VISIBLE);
//                }
            }
        });
        arabic=(RelativeLayout)findViewById(R.id.ArabicRelative);
        arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arabicImage.getVisibility()==View.VISIBLE)
                {
                    arabicImage.setVisibility(View.GONE);
                    arabicLang="";
                }
                else
                {
                    arabicLang=", Arabic";
                    arabicImage.setVisibility(View.VISIBLE);
                }
            }
        });
        spanish=(RelativeLayout)findViewById(R.id.SpanishRelative);
        spanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spanishImage.getVisibility()==View.VISIBLE)
                {
                    spanishLang="";
                    spanishImage.setVisibility(View.GONE);
                }
                else
                {
                    spanishLang=", Spanish";
                    spanishImage.setVisibility(View.VISIBLE);
                }
            }
        });
        urdu=(RelativeLayout)findViewById(R.id.UrduRelative);
        urdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(urduImage.getVisibility()==View.VISIBLE)
                {
                    urduLang="";
                    urduImage.setVisibility(View.GONE);
                }
                else
                {
                    urduLang=", Urdu";
                    urduImage.setVisibility(View.VISIBLE);
                }
            }
        });
        turkish=(RelativeLayout)findViewById(R.id.TurkishRelative);
        turkish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turkishImage.getVisibility()==View.VISIBLE)
                {
                    turkishLang="";
                    turkishImage.setVisibility(View.GONE);
                }
                else
                {
                    turkishLang=", Turkish";
                    turkishImage.setVisibility(View.VISIBLE);
                }
            }
        });
        //GetUserFilters();
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