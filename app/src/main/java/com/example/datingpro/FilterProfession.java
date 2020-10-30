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

public class FilterProfession extends AppCompatActivity {
    Button save;
    EditText ProfessionText;
    FirebaseHelper helper;
    ProgressBar progressBar;
//    String gender,disability,secondDisability,professionText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_profession);
        String intentProfession=getIntent().getStringExtra("professionFilter");
        helper=new FirebaseHelper(FilterProfession.this);
        ProfessionText=(EditText)findViewById(R.id.editTextProfession);
        ProfessionText.setText(intentProfession);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prof=ProfessionText.getText().toString();
                if(TextUtils.isEmpty(prof))
                {
                    Toast.makeText(getApplicationContext(),"Please enter profession", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        progressBar.setVisibility(View.VISIBLE);
//                        helper.UpdateFilter(BaseHelper.UserID, gender, ageText,ageMaxText, distanceText, disability, secondDisability, prof, relationshipStatusText,
//                                bodyHeightText, educationText, smokerText, religionText, bodyTypeText, languageSpeakText, drinkingText, exerciseText, tattoosText, petsText, musicText, dietText, childrenText);
                        FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("profession").setValue(prof);
                        Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getApplicationContext(), Filter.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
                        Animatoo.animateSlideDown(FilterProfession.this);
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Problem with filter update", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }
        });
        //GetUserFilters();
    }

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