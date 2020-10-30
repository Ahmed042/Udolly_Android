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

public class UpdateDisability extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button save;
    Spinner spin, spin2;
    EditText disabilityText;
    boolean check = false, secondCheck = false, isDependent = false;
    String[] disabilityString = {"I Take", "Physics", "Auditory", "Visual", "Intelectual", "Mental", "Mental Acquired brain damage",
            "Autism or Asperger's", "Down's Syndrome", "Person in charge of a handicapped person"};
    String[] defaultString = {"None"};
    String[] populateString = {"I Take", "Physics", "Auditory", "Visual", "Intelectual", "Mental", "Mental Acquired brain damage",
            "Autism or Asperger's", "Down's Syndrome"};
//    String email, gender, dob, image, mobile, userName, name, address, bio, disability, secondDisability, userLat, userLong, homeTownText, professionText, lookingForText, relationshipStatusText, bodyHeightText, educationText, smokerText, religionText, bodyTypeText, languageSpeakText,
////            drinkingText, exerciseText, tattoosText, petsText, musicText, dietText, childrenText, descriptionText;
//String[] disabilityString = {"I Take", getResources().getString(R.string.f_sica), getResources().getString(R.string.auditiva), getResources().getString(R.string.visual), getResources().getString(R.string.intelectual), getResources().getString(R.string.mental), getResources().getString(R.string.da_o_cerebral_adquirido),
//        getResources().getString(R.string.autismo_o_asperger), getResources().getString(R.string.s_ndrome_de_down), getResources().getString(R.string.persona_a_cargo_de_un_a_minusvalido_a)};
//    String[] defaultString = {"None"};
//    String[] populateString = {"I Take", getResources().getString(R.string.f_sica), getResources().getString(R.string.auditiva), getResources().getString(R.string.visual), getResources().getString(R.string.intelectual), getResources().getString(R.string.mental), getResources().getString(R.string.da_o_cerebral_adquirido),
//            getResources().getString(R.string.autismo_o_asperger), getResources().getString(R.string.s_ndrome_de_down)};
    FirebaseHelper helper;
    String selectedValue = "Add";
    String selectedValue2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_disability);
        helper=new FirebaseHelper(UpdateDisability.this);
        spin2 = (Spinner) findViewById(R.id.secondDisabilitySpinner);
        SpinDefault();
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == false) {
                    Toast.makeText(getApplicationContext(), "Please Choose Your disability", Toast.LENGTH_SHORT).show();
                } else if (isDependent == true && secondCheck == false) {
                    Toast.makeText(getApplicationContext(), "Please Choose your disability", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("selected_disability").setValue(selectedValue);
                        FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("disability").setValue(selectedValue2);

//                        helper.UpdateProfile(BaseHelper.UserID, name, userName, mobile, email, gender, selectedValue, selectedValue2, image, dob, address, userLat, userLong, descriptionText,homeTownText,professionText,lookingForText,relationshipStatusText,
//                                bodyHeightText, educationText, smokerText,religionText,bodyTypeText, languageSpeakText, drinkingText,exerciseText, tattoosText, petsText, musicText,dietText,selectedValue, bio);
                        Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),ProfileEdit.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        Animatoo.animateSlideDown(UpdateDisability.this);
                        finish();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(), "Problem with profile update", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        spin=(Spinner)findViewById(R.id.DisabilitySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, disabilityString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

    }
    public void SpinDefault()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, defaultString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adapter);
        secondCheck=false;
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedValue2="None";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void SpinPopulate()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, populateString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adapter);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0) {
                    secondCheck=true;
                    selectedValue2=populateString[i];
                    Toast.makeText(getApplicationContext(), populateString[i], Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    @Override
    //Performing action onItemSelected and onNothing selected
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        if(position!=0) {
            check=true;
            selectedValue=disabilityString[position];
            Toast.makeText(getApplicationContext(), disabilityString[position], Toast.LENGTH_SHORT).show();
            if(position==9)
            {
                SpinPopulate();
                spin2.setVisibility(View.VISIBLE);
                isDependent=true;
            }
            else
            {
                SpinDefault();
                spin2.setVisibility(View.GONE);
                isDependent=false;
            }
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
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