package com.example.dolly;

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
import com.example.dolly.Helper.BaseHelper;
import com.example.dolly.Helper.FirebaseHelper;
import com.google.firebase.database.FirebaseDatabase;

public class Profession extends AppCompatActivity {
    Button save;
    EditText ProfessionText;
    FirebaseHelper helper;
//    String email,gender,dob,image,mobile,userName,name,address,bio,disability,secondDisability,userLat,userLong,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profession);
        helper=new FirebaseHelper(Profession.this);
        ProfessionText=(EditText)findViewById(R.id.editTextProfession);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prof=ProfessionText.getText().toString();
                if(TextUtils.isEmpty(prof))
                {
                    Toast.makeText(getApplicationContext(),"Please enter your profession", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("profession").setValue(prof);
//                        helper.UpdateProfile(BaseHelper.UserID, name, userName, mobile, email, gender, disability, secondDisability, image, dob, address, userLat, userLong, descriptionText, homeTownText, prof, lookingForText, relationshipStatusText,
//                                bodyHeightText, educationText, smokerText, religionText, bodyTypeText, languageSpeakText, drinkingText, exerciseText, tattoosText, petsText, musicText, dietText, childrenText, bio);
                        Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ProfileEdit.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        Animatoo.animateSlideDown(Profession.this);
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Problem with profile update", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //GetUserProfile();
    }

    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(Profession.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(Profession.this);
    }
}