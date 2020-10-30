package com.example.datingpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.datingpro.Helper.BaseHelper;
import com.example.datingpro.Helper.FirebaseHelper;
import com.example.datingpro.Model.FireBaseModels.FilterModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FilterDisability extends AppCompatActivity {
    Button save;
    RelativeLayout disabilityDefault,física,auditiva,visual,intelectual,mental,dañoCerebralAdquirido,
            autismoOAsperger,síndromeDeDown,persona;
    RadioButton disabilityDefaultRadio,físicaRadio,auditivaRadio,visualRadio,intelectualRadio,mentalRadio,dañoCerebralAdquiridoRadio,
            autismoOAspergerRadio,síndromeDeDownRadio,personaRadio;
    String gender,disability,secondDisability,professionText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    FirebaseHelper helper;
    String selectedValue= "Disability doesn't matter";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_disability);
        String intentDisability=getIntent().getStringExtra("disabilityFilter");
        selectedValue=intentDisability;
        helper=new FirebaseHelper(this);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressBar.setVisibility(View.VISIBLE);
//                    helper.UpdateFilter(BaseHelper.UserID,gender,ageText,ageMaxText,distanceText, selectedValue, secondDisability,professionText,relationshipStatusText,
//                            bodyHeightText, educationText, smokerText,religionText,bodyTypeText, languageSpeakText, drinkingText,exerciseText, tattoosText, petsText, musicText, dietText, childrenText);
                    FirebaseDatabase.getInstance().getReference().child("filters").child(BaseHelper.UserID).child("selected_disability").setValue(selectedValue);
                    Toast.makeText(getApplicationContext(), "Filter Updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(),Filter.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    Animatoo.animateSlideDown(FilterDisability.this);
                    finish();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Problem with filter update", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        disabilityDefaultRadio=(RadioButton)findViewById(R.id.radioNoMatter);
        físicaRadio=(RadioButton)findViewById(R.id.radioFísica);
        auditivaRadio=(RadioButton)findViewById(R.id.radioAuditiva);
        visualRadio=(RadioButton)findViewById(R.id.radioVisual);
        intelectualRadio=(RadioButton)findViewById(R.id.radioIntelectual);
        mentalRadio=(RadioButton)findViewById(R.id.radioMental);
        dañoCerebralAdquiridoRadio=(RadioButton)findViewById(R.id.radioDañoCerebralAdquirido);
        autismoOAspergerRadio=(RadioButton)findViewById(R.id.radioAutismooAsperger);
        síndromeDeDownRadio=(RadioButton)findViewById(R.id.radioSíndromeDeDown);
        personaRadio=(RadioButton)findViewById(R.id.radioPersona);

        disabilityDefault=(RelativeLayout)findViewById(R.id.noMatterRelative);
        disabilityDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                disabilityDefaultRadio.setChecked(true);
                selectedValue="Disability doesn't matter";
            }
        });
        física=(RelativeLayout)findViewById(R.id.FísicaRelative);
        física.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                físicaRadio.setChecked(true);
                selectedValue="Physics";
            }
        });
        auditiva=(RelativeLayout)findViewById(R.id.AuditivaRelative);
        auditiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                auditivaRadio.setChecked(true);
                selectedValue="Auditory";
            }
        });
        visual=(RelativeLayout)findViewById(R.id.VisualRelative);
        visual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                visualRadio.setChecked(true);
                selectedValue="Visual";
            }
        });
        intelectual=(RelativeLayout)findViewById(R.id.IntelectualRelative);
        intelectual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                intelectualRadio.setChecked(true);
                selectedValue="Intelectual";
            }
        });
        mental=(RelativeLayout)findViewById(R.id.MentalRelative);
        mental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                mentalRadio.setChecked(true);
                selectedValue="Mental";
            }
        });
        dañoCerebralAdquirido=(RelativeLayout)findViewById(R.id.DañoCerebralAdquiridoRelative);
        dañoCerebralAdquirido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                dañoCerebralAdquiridoRadio.setChecked(true);
                selectedValue="Mental Acquired brain damage";
            }
        });
        autismoOAsperger=(RelativeLayout)findViewById(R.id.AutismooAspergerRelative);
        autismoOAsperger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                autismoOAspergerRadio.setChecked(true);
                selectedValue="Autism or Asperger's";
            }
        });
        síndromeDeDown=(RelativeLayout)findViewById(R.id.SíndromeDeDownRelative);
        síndromeDeDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                síndromeDeDownRadio.setChecked(true);
                selectedValue="Down's Syndrome";
            }
        });
        persona=(RelativeLayout)findViewById(R.id.PersonaRelative);
        persona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                personaRadio.setChecked(true);
                selectedValue="Person in charge of a handicapped person";
            }
        });
        //GetUserFilters();
//        if(TextUtils.equals(intentDisability,"Disability doesn't matter"))
//        {
//            selectedValue="Disability doesn't matter";
//            disabilityDefaultRadio.setChecked(true);
//        }
//       else if(TextUtils.equals(intentDisability,"Physics"))
//        {
//            selectedValue="Physics";
//            físicaRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentDisability,"Auditory"))
//        {
//            selectedValue="Auditory";
//            auditivaRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentDisability,"Visual"))
//        {
//            selectedValue="Visual";
//            visualRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentDisability,"Intelectual"))
//        {
//            selectedValue="Intelectual";
//            intelectualRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentDisability,"Mental"))
//        {
//            selectedValue="Mental";
//            mentalRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentDisability,"Mental Acquired brain damage"))
//        {
//            selectedValue="Mental Acquired brain damage";
//            dañoCerebralAdquiridoRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentDisability,"Autism or Asperger's"))
//        {
//            selectedValue="Autism or Asperger's";
//            autismoOAspergerRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentDisability,"Down's Syndrome"))
//        {
//            selectedValue="Down's Syndrome";
//            síndromeDeDownRadio.setChecked(true);
//        }
//        else if(TextUtils.equals(intentDisability,"Persona a cargo de un/a minusvalido/a"))
//        {
//            selectedValue="Persona a cargo de un/a minusvalido/a";
//            personaRadio.setChecked(true);
//        }

        if(TextUtils.equals(intentDisability,getResources().getString(R.string.disability_doesn_t_matter)))
        {
            disabilityDefaultRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentDisability,getResources().getString(R.string.f_sica)))
        {
            selectedValue="Physics";
            físicaRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentDisability,getResources().getString(R.string.auditiva)))
        {
            selectedValue="Auditory";
            auditivaRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentDisability,getResources().getString(R.string.visual)))
        {
            selectedValue="Visual";
            visualRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentDisability,getResources().getString(R.string.intelectual)))
        {
            selectedValue="Intelectual";
            intelectualRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentDisability,getResources().getString(R.string.mental)))
        {
            selectedValue="Mental";
            mentalRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentDisability,getResources().getString(R.string.da_o_cerebral_adquirido)))
        {
            selectedValue="Mental Acquired brain damage";
            dañoCerebralAdquiridoRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentDisability,getResources().getString(R.string.autismo_o_asperger)))
        {
            selectedValue="Autism or Asperger's";
            autismoOAspergerRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentDisability,getResources().getString(R.string.s_ndrome_de_down)))
        {
            selectedValue="Down's Syndrome";
            síndromeDeDownRadio.setChecked(true);
        }
        else if(TextUtils.equals(intentDisability,getResources().getString(R.string.persona_a_cargo_de_un_a_minusvalido_a)))
        {
            selectedValue="Person in charge of a handicapped person";
            personaRadio.setChecked(true);
        }


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
        disabilityDefaultRadio.setChecked(false);
        físicaRadio.setChecked(false);
        auditivaRadio.setChecked(false);
        visualRadio.setChecked(false);
        intelectualRadio.setChecked(false);
        mentalRadio.setChecked(false);
        dañoCerebralAdquiridoRadio.setChecked(false);
        autismoOAspergerRadio.setChecked(false);
        síndromeDeDownRadio.setChecked(false);
        personaRadio.setChecked(false);
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