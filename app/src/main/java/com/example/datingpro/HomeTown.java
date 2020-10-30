package com.example.datingpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.squareup.picasso.Picasso;

public class HomeTown extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button save;
    Spinner spin;
    EditText LocationText;
    FirebaseHelper helper;
    String[] countryString = {"Country", "Pakistan", "Afghanistan", "Bahrain", "Chad","Egypt",
            "Iran", "Iraq","Jordan", "Kuwait", "Spain"};
//   String email,gender,dob,image,mobile,userName,name,address,bio,disability,secondDisability,userLat,userLong,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,descriptionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_town);
        helper=new FirebaseHelper(HomeTown.this);
       LocationText=(EditText)findViewById(R.id.editTextLocation);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String home=LocationText.getText().toString();
                if(TextUtils.isEmpty(home))
                {
                    Toast.makeText(getApplicationContext(),"Please enter home town location", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
//                        helper.UpdateProfile(BaseHelper.UserID, name, userName, mobile, email, gender, disability, secondDisability, image, dob, address, userLat, userLong, descriptionText, home, professionText, lookingForText, relationshipStatusText,
//                                bodyHeightText, educationText, smokerText, religionText, bodyTypeText, languageSpeakText, drinkingText, exerciseText, tattoosText, petsText, musicText, dietText, childrenText, bio);
                        FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("home_town").setValue(home);
                        Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ProfileEdit.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        Animatoo.animateSlideDown(HomeTown.this);
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Problem with profile update", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        spin=(Spinner)findViewById(R.id.CountrySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countryString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
        //GetUserProfile();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(), countryString[i], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
//    public void GetUserProfile()
//    {
//        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("profiles");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    UserProfileModel model = dataSnapshot.getValue(UserProfileModel.class);
//                    if(TextUtils.equals(model.getUser_id(), BaseHelper.UserID)) {
//                        email=model.getEmail();
//                        gender=model.getGender();
//                        dob=model.getDate_of_birth();
//                        image=model.getImage();
//                        mobile=model.getUser_mobile_no();
//                        userName=model.getUser_name();
//                        name=model.getFull_name();
//                        address=model.getAddress();
//                        bio=model.getBio();
//                        disability=model.getSelected_disability();
//                        secondDisability=model.getDisability();
//                        userLat=model.getLocation_lat();
//                        userLong=model.getLocation_long();
//                        homeTownText=model.getHome_town();
//                        professionText=model.getProfession();
//                        lookingForText=model.getLooking_for();
//                        relationshipStatusText=model.getRelationship_status();
//                        bodyHeightText=model.getBody_height();
//                        educationText=model.getEducation();
//                        smokerText=model.getSmoker();
//                        religionText=model.getReligion();
//                        bodyTypeText=model.getBody_type();
//                        languageSpeakText=model.getLanguage();
//                        drinkingText=model.getDrinking();
//                        exerciseText=model.getExercise();
//                        tattoosText=model.getTattoos();
//                        petsText=model.getPets();
//                        musicText=model.getMusic();
//                        dietText=model.getDiet();
//                        childrenText=model.getChildren();
//                        descriptionText=model.getDescription();
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(HomeTown.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(HomeTown.this);
    }
}