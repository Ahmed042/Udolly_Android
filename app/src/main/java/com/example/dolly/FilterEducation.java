package com.example.dolly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.Helper.BaseHelper;
import com.example.dolly.Helper.FirebaseHelper;
import com.google.firebase.database.FirebaseDatabase;

public class FilterEducation extends AppCompatActivity {
    Button save;
    RelativeLayout askMe,noDegree,specialSchool,someHigh,associate,highSchool,someCollege,currentCollege,bachelors,masters,phd,other;
    RadioButton askMeRadio,noDegreeRadio,specialSchoolRadio,someHighRadio,associateRadio,highSchoolRadio,someCollegeRadio,currentCollegeRadio,bachelorsRadio,mastersRadio,phdRadio,otherRadio;
//    String gender,disability,secondDisability,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    FirebaseHelper helper;
    String selectedValue="All";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_education);
        String intentEducation=getIntent().getStringExtra("educationFilter");
        //selectedValue=intentEducation;
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        helper=new FirebaseHelper(this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressBar.setVisibility(View.VISIBLE);
//                    helper.UpdateFilter(BaseHelper.UserID,gender,ageText,ageMaxText,distanceText, disability, secondDisability,professionText,relationshipStatusText,
//                            bodyHeightText, selectedValue, smokerText,religionText,bodyTypeText, languageSpeakText, drinkingText,exerciseText, tattoosText, petsText, musicText, dietText, childrenText);
                    FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("education").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(),Filter.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    Animatoo.animateSlideDown(FilterEducation.this);
                    finish();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Problem with filter update", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
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
                selectedValue="All";
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
        //GetUserFilters();
        if(TextUtils.equals(intentEducation,getResources().getString(R.string.all)))
        {
            selectedValue="All";
            askMeRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentEducation,getResources().getString(R.string.no_degree)))
        {
            selectedValue="No degree";
            noDegreeRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentEducation,getResources().getString(R.string.special_school)))
        {
            selectedValue="Special school";
            specialSchoolRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentEducation,getResources().getString(R.string.some_high_school)))
        {
            selectedValue="Some high school";
            someHighRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentEducation,getResources().getString(R.string.associate_degree)))
        {
            selectedValue="Associate degree";
            associateRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentEducation,getResources().getString(R.string.high_school_graduate)))
        {
            selectedValue="High school graduate";
            highSchoolRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentEducation,getResources().getString(R.string.some_college_studies)))
        {
            selectedValue="Some college studies";
            someCollegeRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentEducation,getResources().getString(R.string.current_college_student)))
        {
            selectedValue="Current college student";
            currentCollegeRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentEducation,getResources().getString(R.string.bachelor_s_degree)))
        {
            selectedValue="Bachelor's degree";
            bachelorsRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentEducation,getResources().getString(R.string.master_s_degree)))
        {
            selectedValue="Master's degree";
            mastersRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentEducation,getResources().getString(R.string.phd_md_post_doctorate)))
        {
            selectedValue="PhD,MD,Post doctorate";
            phdRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentEducation,getResources().getString(R.string.other)))
        {
            selectedValue="Other";
            otherRadio.setChecked(true);
        }
//        if(TextUtils.equals(intentEducation,getResources().getString(R.string.all)))
//        {
//            selectedValue="All";
//            askMeRadio.setChecked(true);
//        }
//       else if(TextUtils.equals(intentEducation,"No degree"))
//        {
//            selectedValue="No degree";
//            noDegreeRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentEducation,"Special school"))
//        {
//            selectedValue="Special school";
//            specialSchoolRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentEducation,"Some high school"))
//        {
//            selectedValue="Some high school";
//            someHighRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentEducation,"Associate degree"))
//        {
//            selectedValue="Associate degree";
//            associateRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentEducation,"High school graduate"))
//        {
//            selectedValue="High school graduate";
//            highSchoolRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentEducation,"Some college studies"))
//        {
//            selectedValue="Some college studies";
//            someCollegeRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentEducation,"Current college student"))
//        {
//            selectedValue="Current college student";
//            currentCollegeRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentEducation,"Bachelor's degree"))
//        {
//            selectedValue="Bachelor's degree";
//            bachelorsRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentEducation,"Master's degree"))
//        {
//            selectedValue="Master's degree";
//            mastersRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentEducation,"PhD,MD,Post doctorate"))
//        {
//            selectedValue="PhD,MD,Post doctorate";
//            phdRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentEducation,"Other"))
//        {
//            selectedValue="Other";
//            otherRadio.setChecked(true);
//        }


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
        Animatoo.animateSlideDown(this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(this);
    }
}