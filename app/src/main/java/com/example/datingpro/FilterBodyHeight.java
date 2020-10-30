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
import android.widget.ProgressBar;
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

public class FilterBodyHeight extends AppCompatActivity {
    EditText height;
    Button save;
    FirebaseHelper helper;
    ProgressBar progressBar;
//    String gender,disability,secondDisability,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_body_height);
        helper=new FirebaseHelper(this);
        height=(EditText)findViewById(R.id.editTextHeight);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String heightInCm=height.getText().toString();
                if(TextUtils.isEmpty(heightInCm))
                {
                    Toast.makeText(getApplicationContext(),"Please enter height in centimeters", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("ageMin").setValue(heightInCm);
//                        helper.UpdateFilter(BaseHelper.UserID, gender, ageText, ageMaxText, distanceText, disability, secondDisability, professionText, relationshipStatusText,
//                                heightInCm, educationText, smokerText, religionText, bodyTypeText, languageSpeakText, drinkingText, exerciseText, tattoosText, petsText, musicText, dietText, childrenText);
                       Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Filter.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        Animatoo.animateSlideDown(FilterBodyHeight.this);
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Problem with filter update", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //GetUserFilters();
    }
//    public void GetUserFilters()
//    {
//        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("filters");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    FilterModel model = dataSnapshot.getValue(FilterModel.class);
//                    if(TextUtils.equals(model.getId(), BaseHelper.UserID)) {
//                        ageText=model.getAgeMin();
//                        ageMaxText=model.getAgeMax();
//                        gender=model.getGender();
//                        distanceText=model.getDistance();
//                        disability=model.getSelected_disability();
//                        secondDisability=model.getDisability();
//                        professionText=model.getProfession();
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
        Animatoo.animateSlideDown(this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(this);
    }
}