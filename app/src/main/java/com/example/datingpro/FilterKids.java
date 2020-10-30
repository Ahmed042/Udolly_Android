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

public class FilterKids extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,want,notWant,haveWant,haveNotWant;
    RadioButton askMeRadio,wantRadio,notWantRadio,haveWantRadio,haveNotWantRadio;
//    String gender,disability,secondDisability,professionText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    FirebaseHelper helper;
    String selectedValue="All";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_kids);
        String intentKids=getIntent().getStringExtra("childrenFilter");
        selectedValue=intentKids;
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        helper=new FirebaseHelper(this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressBar.setVisibility(View.VISIBLE);
//                    helper.UpdateFilter(BaseHelper.UserID,gender,ageText,ageMaxText,distanceText, disability, secondDisability,professionText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,religionText,bodyTypeText, languageSpeakText, drinkingText,exerciseText, tattoosText, petsText, musicText, dietText, selectedValue);
                    FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("children").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(),Filter.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    Animatoo.animateSlideDown(FilterKids.this);
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
        wantRadio=(RadioButton)findViewById(R.id.radioWantSomeday);
        notWantRadio=(RadioButton)findViewById(R.id.radioNoWant);
        haveWantRadio=(RadioButton)findViewById(R.id.radioHaveWant);
        haveNotWantRadio=(RadioButton)findViewById(R.id.radioHaveNoMore);


        askMe=(RelativeLayout)findViewById(R.id.askMeRelative);
        askMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                askMeRadio.setChecked(true);
                selectedValue="All";
            }
        });
        want=(RelativeLayout)findViewById(R.id.WantSomedayRelative);
        want.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                wantRadio.setChecked(true);
                selectedValue="Want Someday";
            }
        });
        notWant=(RelativeLayout)findViewById(R.id.NoWantRelative);
        notWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                notWantRadio.setChecked(true);
                selectedValue="Don't Want";
            }
        });
        haveWant=(RelativeLayout)findViewById(R.id.HaveWantRelative);
        haveWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                haveWantRadio.setChecked(true);
                selectedValue="Have & want more";
            }
        });
        haveNotWant=(RelativeLayout)findViewById(R.id.HaveNoMoreRelative);
        haveNotWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                haveNotWantRadio.setChecked(true);
                selectedValue="Have & don't want more";
            }
        });
        //GetUserFilters();
        if(TextUtils.equals(intentKids,getResources().getString(R.string.all)))
        {
            selectedValue="All";
            askMeRadio.setChecked(true);
        }
       else if(TextUtils.equals(intentKids,getResources().getString(R.string.want_someday)))
        {
            selectedValue="Want Someday";
            wantRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentKids,getResources().getString(R.string.don_t_want)))
        {
            selectedValue="Don't Want";
            notWantRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentKids,getResources().getString(R.string.have_amp_want_more)))
        {
            selectedValue="Have & don't want more";
            haveWantRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentKids,getResources().getString(R.string.have_amp_don_t_want_more)))
        {
            selectedValue="Have & don't want more";
            haveNotWantRadio.setChecked(true);
        }
    }

    public void clearChecks()
    {
        askMeRadio.setChecked(false);
        wantRadio.setChecked(false);
        notWantRadio.setChecked(false);
        haveWantRadio.setChecked(false);
        haveNotWantRadio.setChecked(false);
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