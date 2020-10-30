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

public class Education extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,noDegree,specialSchool,someHigh,associate,highSchool,someCollege,currentCollege,bachelors,masters,phd,other;
    RadioButton askMeRadio,noDegreeRadio,specialSchoolRadio,someHighRadio,associateRadio,highSchoolRadio,someCollegeRadio,currentCollegeRadio,bachelorsRadio,mastersRadio,phdRadio,otherRadio;
//    String email,gender,dob,image,mobile,userName,name,address,bio,disability,secondDisability,userLat,userLong,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,descriptionText;
    FirebaseHelper helper;
    String selectedValue="Add";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        helper=new FirebaseHelper(Education.this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    helper.UpdateProfile(BaseHelper.UserID, name, userName, mobile, email, gender, disability, secondDisability, image, dob, address, userLat, userLong, descriptionText, homeTownText, professionText, lookingForText, relationshipStatusText,
//                            bodyHeightText, selectedValue, smokerText, religionText, bodyTypeText, languageSpeakText, drinkingText, exerciseText, tattoosText, petsText, musicText, dietText, childrenText, bio);
                    FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("education").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ProfileEdit.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Animatoo.animateSlideDown(Education.this);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Problem with profile update", Toast.LENGTH_SHORT).show();
                }
            }
        });
        askMeRadio=(RadioButton)findViewById(R.id.radioAskMe);
        noDegreeRadio=(RadioButton)findViewById(R.id.radioNoDegree);
        specialSchoolRadio=(RadioButton)findViewById(R.id.radioSpecialSchool);
        someHighRadio=(RadioButton)findViewById(R.id.radioSomeHighSchool);
        associateRadio=(RadioButton)findViewById(R.id.radioAssociate);
        highSchoolRadio=(RadioButton)findViewById(R.id.radioHighSchoolGraduate);
        someCollegeRadio=(RadioButton)findViewById(R.id.radioSomeCollegeStudies);
        currentCollegeRadio=(RadioButton)findViewById(R.id.radioCurrentCollegeStudent);
        bachelorsRadio=(RadioButton)findViewById(R.id.radioBachelors);
        mastersRadio=(RadioButton)findViewById(R.id.radioMasters);
        phdRadio=(RadioButton)findViewById(R.id.radioPhd);
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
        noDegree=(RelativeLayout)findViewById(R.id.noDegreeRelative);
        noDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                noDegreeRadio.setChecked(true);
                selectedValue="No degree";
            }
        });
        specialSchool=(RelativeLayout)findViewById(R.id.specialSchoolRelative);
        specialSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
               specialSchoolRadio.setChecked(true);
                selectedValue="Special school";
            }
        });
        someHigh=(RelativeLayout)findViewById(R.id.someHighSchoolRelative);
        someHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                someHighRadio.setChecked(true);
                selectedValue="Some high school";
            }
        });
       associate=(RelativeLayout)findViewById(R.id.AssociateRelative);
        associate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                associateRadio.setChecked(true);
                selectedValue="Associate degree";
            }
        });
        highSchool=(RelativeLayout)findViewById(R.id.HighSchoolGraduateRelative);
        highSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                highSchoolRadio.setChecked(true);
                selectedValue="High school graduate";
            }
        });
        someCollege=(RelativeLayout)findViewById(R.id.someCollegeStudiesRelative);
        someCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                someCollegeRadio.setChecked(true);
                selectedValue="Some college studies";
            }
        });
        currentCollege=(RelativeLayout)findViewById(R.id.currentCollegeStudentRelative);
        currentCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                currentCollegeRadio.setChecked(true);
                selectedValue="Current college student";
            }
        });
        bachelors=(RelativeLayout)findViewById(R.id.BachelorsRelative);
        bachelors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                bachelorsRadio.setChecked(true);
                selectedValue="Bachelor's degree";
            }
        });
        masters=(RelativeLayout)findViewById(R.id.MastersRelative);
        masters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                mastersRadio.setChecked(true);
                selectedValue="Master's degree";
            }
        });
        phd=(RelativeLayout)findViewById(R.id.PhdRelative);
        phd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                phdRadio.setChecked(true);
                selectedValue="PhD,MD,Post doctorate";
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
        noDegreeRadio.setChecked(false);
        specialSchoolRadio.setChecked(false);
        someHighRadio.setChecked(false);
        associateRadio.setChecked(false);
        highSchoolRadio.setChecked(false);
        someCollegeRadio.setChecked(false);
        currentCollegeRadio.setChecked(false);
        bachelorsRadio.setChecked(false);
        mastersRadio.setChecked(false);
        phdRadio.setChecked(false);
        otherRadio.setChecked(false);
    }
    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(Education.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(Education.this);
    }
}