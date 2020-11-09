package com.example.dolly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.Helper.BaseHelper;
import com.example.dolly.Helper.FirebaseHelper;
import com.google.firebase.database.FirebaseDatabase;

public class Smoke extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,noSmoke,yes,someTimes,onlyAfterSex;
    RadioButton askMeRadio,noSmokeRadio,yesRadio,someTimesRadio,onlyAfterSexRadio;
//    String email,gender,dob,image,mobile,userName,name,address,bio,disability,secondDisability,userLat,userLong,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,descriptionText;
    FirebaseHelper helper;
    String selectedValue="Add";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoke);
        helper=new FirebaseHelper(Smoke.this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    helper.UpdateProfile(BaseHelper.UserID, name, userName, mobile, email, gender, disability, secondDisability, image, dob, address, userLat, userLong, descriptionText, homeTownText, professionText, lookingForText, relationshipStatusText,
//                            bodyHeightText, educationText, selectedValue, religionText, bodyTypeText, languageSpeakText, drinkingText, exerciseText, tattoosText, petsText, musicText, dietText, childrenText, bio);
                    FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("smoker").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ProfileEdit.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Animatoo.animateSlideDown(Smoke.this);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Problem with profile update", Toast.LENGTH_SHORT).show();
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
               selectedValue="Add";
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
        //GetUserProfile();
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
        Animatoo.animateSlideDown(Smoke.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(Smoke.this);
    }
}