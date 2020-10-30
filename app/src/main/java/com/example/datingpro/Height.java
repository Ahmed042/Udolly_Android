package com.example.datingpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Height extends AppCompatActivity {
EditText height;
Button save;
    FirebaseHelper helper;
    String email,gender,dob,image,mobile,userName,name,address,bio,disability,secondDisability,userLat,userLong,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);
        helper=new FirebaseHelper(Height.this);
        height=(EditText)findViewById(R.id.editTextHeight);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String heightInCm=height.getText().toString();
                if(TextUtils.isEmpty(heightInCm))
                {
                    Toast.makeText(getApplicationContext(),"Please enter your height in centimeters", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        helper.UpdateProfile(BaseHelper.UserID, name, userName, mobile, email, gender, disability, secondDisability, image, dob, address, userLat, userLong, descriptionText, homeTownText, professionText, lookingForText, relationshipStatusText,
                                heightInCm, educationText, smokerText, religionText, bodyTypeText, languageSpeakText, drinkingText, exerciseText, tattoosText, petsText, musicText, dietText, childrenText, bio);
                        Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ProfileEdit.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        Animatoo.animateSlideDown(Height.this);
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Problem with profile update", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        GetUserProfile();
    }
    public void GetUserProfile()
    {
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("profiles");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserProfileModel model = dataSnapshot.getValue(UserProfileModel.class);
                    if(TextUtils.equals(model.getUser_id(), BaseHelper.UserID)) {
                        email=model.getEmail();
                        gender=model.getGender();
                        dob=model.getDate_of_birth();
                        image=model.getImage();
                        mobile=model.getUser_mobile_no();
                        userName=model.getUser_name();
                        name=model.getFull_name();
                        address=model.getAddress();
                        bio=model.getBio();
                        disability=model.getSelected_disability();
                        secondDisability=model.getDisability();
                        userLat=model.getLocation_lat();
                        userLong=model.getLocation_long();
                        homeTownText=model.getHome_town();
                        professionText=model.getProfession();
                        lookingForText=model.getLooking_for();
                        relationshipStatusText=model.getRelationship_status();
//                        bodyHeightText=model.getBody_height();
//                        height.setText(model.getBody_height());
                        educationText=model.getEducation();
                        smokerText=model.getSmoker();
                        religionText=model.getReligion();
                        bodyTypeText=model.getBody_type();
                        languageSpeakText=model.getLanguage();
                        drinkingText=model.getDrinking();
                        exerciseText=model.getExercise();
                        tattoosText=model.getTattoos();
                        petsText=model.getPets();
                        musicText=model.getMusic();
                        dietText=model.getDiet();
                        childrenText=model.getChildren();
                        descriptionText=model.getDescription();
                        if(TextUtils.equals(model.getProfession(),"Add")){
                            height.setText("");
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(Height.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(Height.this);
    }
}