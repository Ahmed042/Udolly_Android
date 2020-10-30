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

public class Diet extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,omnivore,vegetarian,kosher,halal,other;
    RadioButton askMeRadio,omnivoreRadio,vegetarianRadio,kosherRadio,halalRadio,otherRadio;
//    String email,gender,dob,image,mobile,userName,name,address,bio,disability,secondDisability,userLat,userLong,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,descriptionText;
    FirebaseHelper helper;
    String selectedValue="Add";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        helper=new FirebaseHelper(Diet.this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    helper.UpdateProfile(BaseHelper.UserID, name, userName, mobile, email, gender, disability, secondDisability, image, dob, address, userLat, userLong, descriptionText,homeTownText,professionText,lookingForText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,religionText,bodyTypeText, languageSpeakText, drinkingText,exerciseText, tattoosText, petsText, musicText, selectedValue, childrenText, bio);
                    FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("diet").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ProfileEdit.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Animatoo.animateSlideDown(Diet.this);
                    finish();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Problem with profile update", Toast.LENGTH_SHORT).show();
                }
            }
        });
        askMeRadio=(RadioButton)findViewById(R.id.radioAskMe);
        omnivoreRadio=(RadioButton)findViewById(R.id.radioOmnivore);
        vegetarianRadio=(RadioButton)findViewById(R.id.radioVegetarian);
        kosherRadio=(RadioButton)findViewById(R.id.radioKosher);
        halalRadio=(RadioButton)findViewById(R.id.radioHalal);
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
        omnivore=(RelativeLayout)findViewById(R.id.OmnivoreRelative);
        omnivore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                omnivoreRadio.setChecked(true);
                selectedValue="Omnivore";
            }
        });
        vegetarian=(RelativeLayout)findViewById(R.id.VegetarianRelative);
        vegetarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                vegetarianRadio.setChecked(true);
                selectedValue="Vegetarian";
            }
        });
        kosher=(RelativeLayout)findViewById(R.id.KosherRelative);
        kosher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                kosherRadio.setChecked(true);
                selectedValue="Kosher";
            }
        });
        halal=(RelativeLayout)findViewById(R.id.HalalRelative);
        halal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                halalRadio.setChecked(true);
                selectedValue="Halal";
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
        //GetUserProfile();
    }

    public void clearChecks()
    {
        askMeRadio.setChecked(false);
        omnivoreRadio.setChecked(false);
        vegetarianRadio.setChecked(false);
        kosherRadio.setChecked(false);
        halalRadio.setChecked(false);
        otherRadio.setChecked(false);
    }
    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(Diet.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(Diet.this);
    }
}