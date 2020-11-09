package com.example.dolly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.Helper.BaseHelper;
import com.example.dolly.Helper.FirebaseHelper;
import com.google.firebase.database.FirebaseDatabase;

public class FilterAge extends AppCompatActivity {
    Button save;
    EditText minAge,maxAge;
//    String gender,disability,secondDisability,professionText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText;
   FirebaseHelper helper;
    ProgressBar progressBar;
    String selectedValue="18 to 90 years";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_age);
        helper=new FirebaseHelper(this);
        String minimumAge=getIntent().getStringExtra("ageMinFilter");
        String maximumAge=getIntent().getStringExtra("ageMaxFilter");
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressBar.setVisibility(View.VISIBLE);
                    String min=minAge.getText().toString();
                    String max=maxAge.getText().toString();
                    if(TextUtils.isEmpty(min)&&TextUtils.isEmpty(max))
                    {
                        Toast.makeText(getApplicationContext(), "Please enter age to update filter", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                    else {

                        String ageString=min+" to "+max+" years";
                        FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("ageMin").setValue(min);
                        FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("ageMax").setValue(max);
//                        helper.UpdateFilter(BaseHelper.UserID, gender, min,max, distanceText, disability, secondDisability, professionText, relationshipStatusText,
//                                bodyHeightText, educationText, smokerText, religionText, bodyTypeText, languageSpeakText, drinkingText, exerciseText, tattoosText, petsText, musicText, dietText, childrenText);
                        Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getApplicationContext(), Filter.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
                        Animatoo.animateSlideDown(FilterAge.this);
                        //progressBar.setVisibility(View.GONE);
                        finish();
                    }
                }
                catch (Exception e)
                {
                   Toast.makeText(getApplicationContext(), "Problem with filter update", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
                //progressBar.setVisibility(View.GONE);
            }
        });
        minAge=(EditText) findViewById(R.id.editTextMinAge);
        maxAge=(EditText) findViewById(R.id.editTextMaxAge);
        minAge.setText(minimumAge);
        maxAge.setText(maximumAge);
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
//                        minAge.setText(model.getAgeMin());
//                        maxAge.setText(model.getAgeMax());
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