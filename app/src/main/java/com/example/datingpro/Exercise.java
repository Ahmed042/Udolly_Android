package com.example.datingpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Exercise extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,active,someTimes,almostNever;
    RadioButton askMeRadio,activeRadio,someTimesRadio,almostNeverRadio;
//    String email,gender,dob,image,mobile,userName,name,address,bio,disability,secondDisability,userLat,userLong,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,descriptionText;
    FirebaseHelper helper;
    String selectedValue="Add";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        helper=new FirebaseHelper(Exercise.this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    helper.UpdateProfile(BaseHelper.UserID, name, userName, mobile, email, gender, disability, secondDisability, image, dob, address, userLat, userLong, descriptionText,homeTownText,professionText,lookingForText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,religionText,bodyTypeText, languageSpeakText, drinkingText, selectedValue, tattoosText, petsText, musicText, dietText, childrenText, bio);
                    FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("exercise").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ProfileEdit.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Animatoo.animateSlideDown(Exercise.this);
                    finish();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Problem with profile update", Toast.LENGTH_SHORT).show();
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
                selectedValue="Add";
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
        Animatoo.animateSlideDown(Exercise.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(Exercise.this);
    }
}