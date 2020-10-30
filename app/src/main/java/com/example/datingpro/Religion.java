package com.example.datingpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.datingpro.Helper.BaseHelper;
import com.example.datingpro.Helper.FirebaseHelper;
import com.example.datingpro.Model.FireBaseModels.UserProfileModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Religion extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,cristian,muslim,buddhist,orthodox,jewish,spiritual,believing,nonBelieving,other;
    RadioButton askMeRadio,cristianRadio,muslimRadio,buddhistRadio,orthodoxRadio,jewishRadio,spiritualRadio,believingRadio,nonBelievingRadio,otherRadio;
//    String email,gender,dob,image,mobile,userName,name,address,bio,disability,secondDisability,userLat,userLong,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,descriptionText;
    FirebaseHelper helper;
    String selectedValue="Add";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_religion);
        helper=new FirebaseHelper(Religion.this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    helper.UpdateProfile(BaseHelper.UserID, name, userName, mobile, email, gender, disability, secondDisability, image, dob, address, userLat, userLong, descriptionText, homeTownText, professionText, lookingForText, relationshipStatusText,
//                            bodyHeightText, educationText, smokerText, selectedValue, bodyTypeText, languageSpeakText, drinkingText, exerciseText, tattoosText, petsText, musicText, dietText, childrenText, bio);
                    FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("religion").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ProfileEdit.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Animatoo.animateSlideDown(Religion.this);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Problem with profile update", Toast.LENGTH_SHORT).show();
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
                selectedValue="Add";
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
        Animatoo.animateSlideDown(Religion.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(Religion.this);
    }
}