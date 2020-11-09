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

public class FilterReligion extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,cristian,muslim,buddhist,orthodox,jewish,spiritual,believing,nonBelieving,other;
    RadioButton askMeRadio,cristianRadio,muslimRadio,buddhistRadio,orthodoxRadio,jewishRadio,spiritualRadio,believingRadio,nonBelievingRadio,otherRadio;
//    String gender,disability,secondDisability,professionText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    FirebaseHelper helper;
    String selectedValue="All";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_religion);
        String intentReligion=getIntent().getStringExtra("religionFilter");
        //selectedValue=intentReligion;
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
                    FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("religion").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(),Filter.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    Animatoo.animateSlideDown(FilterReligion.this);
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
        cristianRadio=(RadioButton)findViewById(R.id.radioChristian);
        muslimRadio=(RadioButton)findViewById(R.id.radioMuslim);
        buddhistRadio=(RadioButton)findViewById(R.id.radioBuddhist);
        orthodoxRadio=(RadioButton)findViewById(R.id.radioOrthodox);
        jewishRadio=(RadioButton)findViewById(R.id.radioJewish);
        spiritualRadio=(RadioButton)findViewById(R.id.radioSpiritual);
        believingRadio=(RadioButton)findViewById(R.id.radioBelieving);
        nonBelievingRadio=(RadioButton)findViewById(R.id.radioNotBelieving);
        otherRadio=(RadioButton)findViewById(R.id.radioOther);

        askMe=(RelativeLayout)findViewById(R.id.askMeRelative);
        askMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                askMeRadio.setChecked(true);
                selectedValue="All";
            }
        });
        cristian=(RelativeLayout)findViewById(R.id.christianRelative);
        cristian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                cristianRadio.setChecked(true);
                selectedValue="Christian";
            }
        });
        muslim=(RelativeLayout)findViewById(R.id.muslimRelative);
        muslim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                muslimRadio.setChecked(true);
                selectedValue="Muslim";
            }
        });
        buddhist=(RelativeLayout)findViewById(R.id.buddhistRelative);
        buddhist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                buddhistRadio.setChecked(true);
                selectedValue="Buddhist";
            }
        });
        orthodox=(RelativeLayout)findViewById(R.id.OrthodoxRelative);
        orthodox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                orthodoxRadio.setChecked(true);
                selectedValue="Orthodox";
            }
        });
        jewish=(RelativeLayout)findViewById(R.id.JewishRelative);
        jewish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                jewishRadio.setChecked(true);
                selectedValue="Jewish";
            }
        });
        spiritual=(RelativeLayout)findViewById(R.id.SpiritualRelative);
        spiritual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                spiritualRadio.setChecked(true);
                selectedValue="Spiritual";
            }
        });
        other=(RelativeLayout)findViewById(R.id.OtherRelative);
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                otherRadio.setChecked(true);
                selectedValue="Other";
            }
        });
        believing=(RelativeLayout)findViewById(R.id.BelievingRelative);
        believing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                believingRadio.setChecked(true);
                selectedValue="Believing but not religious";
            }
        });
        nonBelieving=(RelativeLayout)findViewById(R.id.NotBelievingRelative);
        nonBelieving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                nonBelievingRadio.setChecked(true);
                selectedValue="Neither believing nor religious";
            }
        });
        //GetUserFilters();
        if(TextUtils.equals(intentReligion,getResources().getString(R.string.all)))
        {
            selectedValue="All";
            askMeRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentReligion,getResources().getString(R.string.christian)))
        {
            selectedValue="Christian";
            cristianRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentReligion,getResources().getString(R.string.muslim)))
        {
            selectedValue="Muslim";
            muslimRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentReligion,getResources().getString(R.string.buddhist)))
        {
            selectedValue="Buddhist";
            buddhistRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentReligion,getResources().getString(R.string.orthodox)))
        {
            selectedValue="Orthodox";
            orthodoxRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentReligion,getResources().getString(R.string.jewish)))
        {
            selectedValue="Jewish";
            jewishRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentReligion,getResources().getString(R.string.spiritual)))
        {
            selectedValue="Spiritual";
            spiritualRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentReligion,getResources().getString(R.string.other)))
        {
            selectedValue="Other";
            otherRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentReligion,getResources().getString(R.string.believing_but_not_religious)))
        {
            selectedValue="Believing but not religious";
            believingRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentReligion,getResources().getString(R.string.neither_believing_nor_religious)))
        {
            selectedValue="Neither believing nor religious";
            nonBelievingRadio.setChecked(true);
        }
//        if(TextUtils.equals(intentReligion,getResources().getString(R.string.all)))
//        {
//            selectedValue="All";
//            askMeRadio.setChecked(true);
//        }
//       else if(TextUtils.equals(intentReligion,"Christian"))
//        {
//            cristianRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentReligion,"Muslim"))
//        {
//            muslimRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentReligion,"Buddhist"))
//        {
//            buddhistRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentReligion,"Orthodox"))
//        {
//            orthodoxRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentReligion,"Jewish"))
//        {
//            jewishRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentReligion,"Spiritual"))
//        {
//            spiritualRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentReligion,"Other"))
//        {
//            otherRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentReligion,"Believing but not religious"))
//        {
//            believingRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentReligion,"Neither believing nor religious"))
//        {
//            nonBelievingRadio.setChecked(true);
//        }

    }

    public void clearChecks()
    {
        askMeRadio.setChecked(false);
        cristianRadio.setChecked(false);
        muslimRadio.setChecked(false);
        buddhistRadio.setChecked(false);
        orthodoxRadio.setChecked(false);
        jewishRadio.setChecked(false);
        spiritualRadio.setChecked(false);
        otherRadio.setChecked(false);
        believingRadio.setChecked(false);
        nonBelievingRadio.setChecked(false);
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