package com.example.dolly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.Helper.BaseHelper;
import com.example.dolly.Helper.FirebaseHelper;
import com.example.dolly.Model.FireBaseModels.FilterModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Filter extends AppCompatActivity {
    RelativeLayout profession,disabilityRel,reset,relationshipStatus,bodyHeight,education,smoker,religion,bodyType,languageSpeak,
            drinking,exercise,tattoos,pets,favouriteSports,diet,children,gender,age,distance;
    TextView professionText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,favouriteSportsText,
            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,genderText,ageText,distanceText;
    private FirebaseAuth auth;
    FirebaseHelper helper;
    TextView disability;
    String firstDisability,secondDisability,ageMinText,ageMaxText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        auth = FirebaseAuth.getInstance();
        helper=new FirebaseHelper(this);
        gender=(RelativeLayout)findViewById(R.id.relativeGender);
        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userGender=genderText.getText().toString();
                Intent intent = new Intent(getApplicationContext(),FilterGender.class);
                intent.putExtra("genderFilter",userGender);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
        age=(RelativeLayout)findViewById(R.id.relativeAge);
        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userAgeMin=ageMinText;
                String userAgeMax=ageMaxText;
                Intent intent = new Intent(getApplicationContext(), FilterAge.class);
                intent.putExtra("ageMinFilter",userAgeMin);
                intent.putExtra("ageMaxFilter",userAgeMax);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
        distance=(RelativeLayout)findViewById(R.id.relativeDistance);
        distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userDistance=distanceText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), FilterDistance.class);
                intent.putExtra("distanceFilter",userDistance);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
        profession=(RelativeLayout)findViewById(R.id.relativeProfession);
        profession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userProfession=professionText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), FilterProfession.class);
                intent.putExtra("professionFilter",userProfession);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
        relationshipStatus=(RelativeLayout)findViewById(R.id.relativeRelationship);
        relationshipStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userRelationship=relationshipStatusText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), FilterRelationshipStatus.class);
                intent.putExtra("relationshipFilter",userRelationship);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
        bodyHeight=(RelativeLayout)findViewById(R.id.relativeBodyHeight);
        bodyHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FilterBodyHeight.class);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
        favouriteSports=(RelativeLayout)findViewById(R.id.relativeFavouriteSports);
        favouriteSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userFavourite=favouriteSportsText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), FilterFavouriteSports.class);
                intent.putExtra("favouriteFilter",userFavourite);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
        education=(RelativeLayout)findViewById(R.id.relativeEducation);
        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEducation=educationText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), FilterEducation.class);
                intent.putExtra("educationFilter",userEducation);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
        smoker=(RelativeLayout)findViewById(R.id.relativeSmoker);
        smoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userSmoker=smokerText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), FilterSmoker.class);
                intent.putExtra("smokerFilter",userSmoker);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
        disabilityRel=(RelativeLayout)findViewById(R.id.relativeDisability);
        disabilityRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userDisability=firstDisability;
                Intent intent = new Intent(getApplicationContext(), FilterDisability.class);
                intent.putExtra("disabilityFilter",userDisability);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
        religion=(RelativeLayout)findViewById(R.id.relativeReligion);
        religion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userReligion=religionText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), FilterReligion.class);
                intent.putExtra("religionFilter",userReligion);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
        bodyType=(RelativeLayout)findViewById(R.id.relativeBodyType);
        bodyType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FilterBodyType.class);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
        languageSpeak=(RelativeLayout)findViewById(R.id.relativeLanguage);
//        languageSpeak.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(getApplicationContext(), LanguagesSpeak.class);
////                startActivity(intent);
////                Animatoo.animateSlideUp(Filter.this);
//                //finish();
//            }
//        });
        drinking=(RelativeLayout)findViewById(R.id.relativeDrinking);
        drinking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userDrinking=drinkingText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), FilterDrinking.class);
                intent.putExtra("drinkingFilter",userDrinking);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
        exercise=(RelativeLayout)findViewById(R.id.relativeExercise);
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userExercise=exerciseText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), FilterExercise.class);
                intent.putExtra("exerciseFilter",userExercise);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
        tattoos=(RelativeLayout)findViewById(R.id.relativeTattoos);
        tattoos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userTattoos=tattoosText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), FilterTattoos.class);
                intent.putExtra("tattoosFilter",userTattoos);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
        pets=(RelativeLayout)findViewById(R.id.relativePets);
        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userPets=petsText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), FilterPets.class);
                intent.putExtra("petsFilter",userPets);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
//        music=(RelativeLayout)findViewById(R.id.relativeMusic);
//        music.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(getApplicationContext(), Music.class);
////                startActivity(intent);
////                Animatoo.animateSlideUp(Filter.this);
//                //finish();
//            }
//        });
        diet=(RelativeLayout)findViewById(R.id.relativeDiet);
        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userDiet=dietText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), FilterDiet.class);
                intent.putExtra("dietFilter",userDiet);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
        children=(RelativeLayout)findViewById(R.id.relativeChildren);
        children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userChildren=childrenText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), FilterKids.class);
                intent.putExtra("childrenFilter",userChildren);
                startActivity(intent);
                Animatoo.animateSlideUp(Filter.this);
                //finish();
            }
        });
        reset=(RelativeLayout)findViewById(R.id.relativeReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Filter.this)
                        .setTitle(R.string.reset_filters_op)
                        .setMessage(R.string.sureReset)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                helper.AddFilter(BaseHelper.UserID);
                                Toast.makeText(getApplicationContext(),R.string.reset_filters_op,Toast.LENGTH_SHORT).show();
                                finish();
//                                Intent intent = new Intent(getApplicationContext(), Filter.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                startActivity(intent);
//                                Animatoo.animateSlideUp(Filter.this);
//                                finish();
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getApplicationContext(), "Problem with filter update", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
        genderText=(TextView)findViewById(R.id.filterGender);
        ageText=(TextView)findViewById(R.id.filterAge);
        distanceText=(TextView)findViewById(R.id.filterDistance);
        disability=(TextView)findViewById(R.id.filterDisability);
        professionText=(TextView)findViewById(R.id.professionText);
        relationshipStatusText=(TextView)findViewById(R.id.relationShipText);
        bodyHeightText=(TextView)findViewById(R.id.bodyHeightText);
        favouriteSportsText=(TextView)findViewById(R.id.favouriteSportsText);
        educationText=(TextView)findViewById(R.id.educationText);
        smokerText=(TextView)findViewById(R.id.smokerText);
        religionText=(TextView)findViewById(R.id.religionText);
        bodyTypeText=(TextView)findViewById(R.id.bodyTypeText);
        //languageSpeakText=(TextView)findViewById(R.id.languageText);
        drinkingText=(TextView)findViewById(R.id.drinkingText);
        exerciseText=(TextView)findViewById(R.id.exerciseText);
        tattoosText=(TextView)findViewById(R.id.tattoosText);
        petsText=(TextView)findViewById(R.id.petText);
        //musicText=(TextView)findViewById(R.id.musicText);
        dietText=(TextView)findViewById(R.id.dietText);
        childrenText=(TextView)findViewById(R.id.kidText);
    GetUserFilter();
    }

    public void GetUserFilter()
    {
        try{
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("filters");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        FilterModel model = dataSnapshot.getValue(FilterModel.class);
                        if (TextUtils.equals(model.getId(), BaseHelper.UserID)) {
                            if(TextUtils.equals(model.getGender(),"Male"))
                            {
                                genderText.setText(R.string.male);
                            }
                            else
                            {
                                genderText.setText(R.string.female);
                            }

                            ageMinText = model.getAgeMin();
                            ageMaxText = model.getAgeMax();
                            if(TextUtils.equals(model.getDistance(),"Distance doesn't matter"))
                            {
                                distanceText.setText(R.string.distance_doesn_t_matter);
                            }
                            else if(TextUtils.equals(model.getDistance(),"Distance up to 50 km"))
                            {
                                distanceText.setText(R.string.distance_up_to_50_km);
                            }
                            else if(TextUtils.equals(model.getDistance(),"Distance up to 10 km"))
                            {
                                distanceText.setText(R.string.distance_up_to_10_km);
                            }
                            else if(TextUtils.equals(model.getDistance(),"Distance up to 100 km"))
                            {
                                distanceText.setText(R.string.distance_up_to_100_km);
                            }
                            else if(TextUtils.equals(model.getDistance(),"Distance up to 500 km"))
                            {
                                distanceText.setText(R.string.distance_up_to_500_km);
                            }

                            if(TextUtils.equals(model.getSelected_disability(),"Disability doesn't matter"))
                            {
                                firstDisability=getResources().getString(R.string.disability_doesn_t_matter);
                            }
                            else if(TextUtils.equals(model.getSelected_disability(),"Physics"))
                            {
                                firstDisability=getResources().getString(R.string.f_sica);
                            }
                            else if(TextUtils.equals(model.getSelected_disability(),"Auditory"))
                            {
                                firstDisability=getResources().getString(R.string.auditiva);
                            }
                            else if(TextUtils.equals(model.getSelected_disability(),"Visual"))
                            {
                                firstDisability=getResources().getString(R.string.visual);
                            }
                            else if(TextUtils.equals(model.getSelected_disability(),"Intelectual"))
                            {
                                firstDisability=getResources().getString(R.string.intelectual);
                            }
                            else if(TextUtils.equals(model.getSelected_disability(),"Mental"))
                            {
                                firstDisability=getResources().getString(R.string.mental);
                            }
                            else if(TextUtils.equals(model.getSelected_disability(),"Mental Acquired brain damage"))
                            {
                                firstDisability=getResources().getString(R.string.da_o_cerebral_adquirido);
                            }
                            else if(TextUtils.equals(model.getSelected_disability(),"Autism or Asperger's"))
                            {
                                firstDisability=getResources().getString(R.string.autismo_o_asperger);
                            }
                            else if(TextUtils.equals(model.getSelected_disability(),"Down's Syndrome"))
                            {
                                firstDisability=getResources().getString(R.string.s_ndrome_de_down);
                            }
                            else if(TextUtils.equals(model.getSelected_disability(),"Person in charge of a handicapped person"))
                            {
                                firstDisability=getResources().getString(R.string.persona_a_cargo_de_un_a_minusvalido_a);
                            }
                            else
                            {
                                firstDisability = model.getSelected_disability();
                            }

                            if(TextUtils.equals(model.getProfession(),"All")) {
                                professionText.setText(R.string.all);
                            }

                            else
                            {
                                professionText.setText(model.getProfession());
                            }
                            if(TextUtils.equals(model.getRelationship_status(),"All")) {
                                relationshipStatusText.setText(R.string.all);
                            }
                            else if(TextUtils.equals(model.getRelationship_status(),"Single"))
                            {
                                relationshipStatusText.setText(R.string.single);
                            }
                            else if(TextUtils.equals(model.getRelationship_status(),"In a relationship"))
                            {
                                relationshipStatusText.setText(R.string.in_a_relationship);
                            }
                            else if(TextUtils.equals(model.getRelationship_status(),"Married"))
                            {
                                relationshipStatusText.setText(R.string.married);
                            }
                            else if(TextUtils.equals(model.getRelationship_status(),"Separated"))
                            {
                                relationshipStatusText.setText(R.string.separated);
                            }
                            else if(TextUtils.equals(model.getRelationship_status(),"Divorced"))
                            {
                                relationshipStatusText.setText(R.string.divorced);
                            }
                            else if(TextUtils.equals(model.getRelationship_status(),"Widowed"))
                            {
                                relationshipStatusText.setText(R.string.widowed);
                            }
                            else if(TextUtils.equals(model.getRelationship_status(),"In an open Relationship"))
                            {
                                relationshipStatusText.setText(R.string.in_an_open_relationship);
                            }
                            else if(TextUtils.equals(model.getRelationship_status(),"Complicated"))
                            {
                                relationshipStatusText.setText(R.string.it_s_complicated);
                            }

                            if(TextUtils.equals(model.getFavourite_sports(),"All")) {
                                favouriteSportsText.setText(R.string.all);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Soccer"))
                            {
                                favouriteSportsText.setText(R.string.soccer);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Basket ball"))
                            {
                                favouriteSportsText.setText(R.string.basketball);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Volley ball"))
                            {
                                favouriteSportsText.setText(R.string.volleyball);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Running"))
                            {
                                favouriteSportsText.setText(R.string.running);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Swimming"))
                            {
                                favouriteSportsText.setText(R.string.swimming);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Hand Ball"))
                            {
                                favouriteSportsText.setText(R.string.hand_ball);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Tennis"))
                            {
                                favouriteSportsText.setText(R.string.tennis);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Surfing"))
                            {
                                favouriteSportsText.setText(R.string.surfing);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Paddle Tennis"))
                            {
                                favouriteSportsText.setText(R.string.paddle_tennis);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Fitness"))
                            {
                                favouriteSportsText.setText(R.string.fitness);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Combat Sports"))
                            {
                                favouriteSportsText.setText(R.string.combat_sports);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Extreme Sports"))
                            {
                                favouriteSportsText.setText(R.string.extreme_sports);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Sports with animals"))
                            {
                                favouriteSportsText.setText(R.string.sports_with_animals);
                            }

                            String intentEducation=model.getEducation();
                            if(TextUtils.equals(model.getEducation(),"All")) {
                                educationText.setText(R.string.all);
                            }
                            else if(TextUtils.equals(intentEducation,"No degree"))
                            {
                                educationText.setText(R.string.no_degree);
                            }
                            else if(TextUtils.equals(intentEducation,"Special school"))
                            {
                                educationText.setText(R.string.special_school);
                            }
                            else if(TextUtils.equals(intentEducation,"Some high school"))
                            {
                                educationText.setText(R.string.some_high_school);
                            }
                            else if(TextUtils.equals(intentEducation,"Associate degree"))
                            {
                                educationText.setText(R.string.associate_degree);
                            }
                            else if(TextUtils.equals(intentEducation,"High school graduate"))
                            {
                                educationText.setText(R.string.high_school_graduate);
                            }
                            else if(TextUtils.equals(intentEducation,"Some college studies"))
                            {
                                educationText.setText(R.string.some_college_studies);
                            }
                            else if(TextUtils.equals(intentEducation,"Current college student"))
                            {
                                educationText.setText(R.string.current_college_student);
                            }
                            else if(TextUtils.equals(intentEducation,"Bachelor's degree"))
                            {
                                educationText.setText(R.string.bachelor_s_degree);
                            }
                            else if(TextUtils.equals(intentEducation,"Master's degree"))
                            {
                                educationText.setText(R.string.master_s_degree);
                            }
                            else if(TextUtils.equals(intentEducation,"PhD,MD,Post doctorate"))
                            {
                                educationText.setText(R.string.phd_md_post_doctorate);
                            }
                            else if(TextUtils.equals(intentEducation,"Other"))
                            {
                                educationText.setText(R.string.other);
                            }

                            String intentSmoker=model.getSmoker();
                            if(TextUtils.equals(model.getSmoker(),"All")) {
                                smokerText.setText(R.string.all);
                            }
                            else if(TextUtils.equals(intentSmoker,"No"))
                            {
                                smokerText.setText(R.string.no);
                            }
                            else if(TextUtils.equals(intentSmoker,"Yes, regularly"))
                            {
                                smokerText.setText(R.string.yes_regularly);
                            }
                            else if(TextUtils.equals(intentSmoker,"Sometimes"))
                            {
                                smokerText.setText(R.string.sometimes);
                            }
                            else if(TextUtils.equals(intentSmoker,"Only After Sex"))
                            {
                                smokerText.setText(R.string.only_after_sex);
                            }

                           String intentReligion=model.getReligion();
                            if(TextUtils.equals(model.getReligion(),"All")) {
                                religionText.setText(R.string.all);
                            }
                            else if(TextUtils.equals(intentReligion,"Christian"))
                            {
                                religionText.setText(R.string.christian);
                            }
                            else if(TextUtils.equals(intentReligion,"Muslim"))
                            {
                                religionText.setText(R.string.muslim);
                            }
                            else if(TextUtils.equals(intentReligion,"Buddhist"))
                            {
                                religionText.setText(R.string.buddhist);
                            }
                            else if(TextUtils.equals(intentReligion,"Orthodox"))
                            {
                                religionText.setText(R.string.orthodox);
                            }
                            else if(TextUtils.equals(intentReligion,"Jewish"))
                            {
                                religionText.setText(R.string.jewish);
                            }
                            else if(TextUtils.equals(intentReligion,"Spiritual"))
                            {
                                religionText.setText(R.string.spiritual);
                            }
                            else if(TextUtils.equals(intentReligion,"Other"))
                            {
                                religionText.setText(R.string.other);
                            }
                            else if(TextUtils.equals(intentReligion,"Believing but not religious"))
                            {
                                religionText.setText(R.string.believing_but_not_religious);
                            }
                            else if(TextUtils.equals(intentReligion,"Neither believing nor religious"))
                            {
                                religionText.setText(R.string.neither_believing_nor_religious);
                            }

                            String intentDrinking=model.getDrinking();
                            if(TextUtils.equals(model.getDrinking(),"All")) {
                                drinkingText.setText(R.string.all);
                            }
                            else if(TextUtils.equals(intentDrinking,"Frequently"))
                            {
                                drinkingText.setText(R.string.frequently);
                            }
                            else if(TextUtils.equals(intentDrinking,"Socially"))
                            {
                                drinkingText.setText(R.string.socially);
                            }
                            else if(TextUtils.equals(intentDrinking,"Never"))
                            {
                                drinkingText.setText(R.string.never);
                            }

                            String intentExercise=model.getExercise();
                            if(TextUtils.equals(model.getExercise(),"All")) {
                                exerciseText.setText(R.string.all);
                            }
                            else if(TextUtils.equals(intentExercise,"Active"))
                            {
                                exerciseText.setText(R.string.active);
                            }
                            else if(TextUtils.equals(intentExercise,"Sometimes"))
                            {
                                exerciseText.setText(R.string.sometimes);
                            }
                            else if(TextUtils.equals(intentExercise,"Almost Never"))
                            {
                                exerciseText.setText(R.string.almost_never);
                            }

                            String intentTattoos=model.getTattoos();
                            if(TextUtils.equals(model.getTattoos(),"All")) {
                                tattoosText.setText(R.string.all);
                            }
                            else if(TextUtils.equals(intentTattoos,"None"))
                            {
                                tattoosText.setText(R.string.none);
                            }
                            else if(TextUtils.equals(intentTattoos,"Many"))
                            {
                                tattoosText.setText(R.string.many);
                            }
                            else if(TextUtils.equals(intentTattoos,"Some"))
                            {
                                tattoosText.setText(R.string.some);
                            }
                            else if(TextUtils.equals(intentTattoos,"Only Inconspicuous"))
                            {
                                tattoosText.setText(R.string.only_inconspicuous);
                            }

                           String intentPets=model.getPets();
                            if(TextUtils.equals(model.getPets(),"All")) {
                                petsText.setText(R.string.all);
                            }
                            else if(TextUtils.equals(intentPets,"None"))
                            {
                                petsText.setText(R.string.none);
                            }
                            else if(TextUtils.equals(intentPets,"Dog( s )"))
                            {
                                petsText.setText(R.string.dog_s);
                            }
                            else if(TextUtils.equals(intentPets,"Cat( s )"))
                            {
                                petsText.setText(R.string.cat_s);
                            }
                            else if(TextUtils.equals(intentPets,"Bird( s )"))
                            {
                                petsText.setText(R.string.bird_s);
                            }
                            else if(TextUtils.equals(intentPets,"Rabbit"))
                            {
                                petsText.setText(R.string.rabbit);
                            }
                            else if(TextUtils.equals(intentPets,"Lots"))
                            {
                                petsText.setText(R.string.lots);
                            }
                            else if(TextUtils.equals(intentPets,"Don't Want"))
                            {
                                petsText.setText(R.string.don_t_want);
                            }

                            String intentDiet=model.getDiet();
                            if(TextUtils.equals(model.getDiet(),"All")) {
                                dietText.setText(R.string.all);
                            }
                            else if(TextUtils.equals(intentDiet,"Omnivore"))
                            {
                                dietText.setText(R.string.omnivore);
                            }
                            else if(TextUtils.equals(intentDiet,"Vegetarian"))
                            {
                                dietText.setText(R.string.vegetarian);
                            }
                            else if(TextUtils.equals(intentDiet,"Kosher"))
                            {
                                dietText.setText(R.string.kosher);
                            }
                            else if(TextUtils.equals(intentDiet,"Halal"))
                            {
                                dietText.setText(R.string.halal);
                            }
                            else if(TextUtils.equals(intentDiet,"Other"))
                            {
                                dietText.setText(R.string.other);
                            }

                            String intentKids=model.getChildren();
                            if(TextUtils.equals(model.getChildren(),"All")) {
                                childrenText.setText(R.string.all);
                            }
                            else if(TextUtils.equals(intentKids,"Want Someday"))
                            {
                                childrenText.setText(R.string.want_someday);
                            }
                            else if(TextUtils.equals(intentKids,"Don't Want"))
                            {
                                childrenText.setText(R.string.don_t_want);
                            }
                            else if(TextUtils.equals(intentKids,"Have & want more"))
                            {
                                childrenText.setText(R.string.have_amp_want_more);
                            }
                            else if(TextUtils.equals(intentKids,"Have & don't want more"))
                            {
                                childrenText.setText(R.string.have_amp_don_t_want_more);
                            }


                            //bodyHeightText.setText(model.getBody_height());

//                            professionText.setText(model.getProfession());
//                            relationshipStatusText.setText(model.getRelationship_status());
//                            favouriteSportsText.setText(model.getFavourite_sports());
//                            educationText.setText(model.getEducation());
//                            smokerText.setText(model.getSmoker());
//                            drinkingText.setText(model.getDrinking());
//                            exerciseText.setText(model.getExercise());
//                            tattoosText.setText(model.getTattoos());
//                            petsText.setText(model.getPets());
//                            dietText.setText(model.getDiet());
//                            childrenText.setText(model.getChildren());
                            bodyTypeText.setText(model.getBody_type());
                            //languageSpeakText.setText(model.getLanguage());
                              //musicText.setText(model.getMusic());
                            secondDisability = model.getDisability();
                            disability.setText(firstDisability);
                            String ageString = ageMinText + " to " + ageMaxText + " years";
                            ageText.setText(ageString);
//                            if (!TextUtils.equals(model.getBody_height(), "All")) {
//                                String height = model.getBody_height() + " cm";
//                                bodyHeightText.setText(height);
//                            } else {
//                                bodyHeightText.setText("All");
//                            }
//                        if(TextUtils.equals(secondDisability,"None"))
//                        {
//                            disability.setText(firstDisability);
//                        }
//                        else
//                        {
//                            String text=firstDisability+" ("+secondDisability+")";
//                            disability.setText(text);
//                        }
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error loading filters....", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Error loading filters....", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(Filter.this);
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(Filter.this);
    }
}