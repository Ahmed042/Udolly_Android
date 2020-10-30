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
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

public class LookingFor extends AppCompatActivity {
RangeSeekBar seekBar;
Button save;
    FirebaseHelper helper;
String Want,Gender;
boolean wantCheck=false,genderCheck=false;
    Spinner WantSpinner,GenderSpinner;
    String[] wantString = {getString(R.string.ask_meLook),getString(R.string.romantic_relationshipLook),getString(R.string.flirt_Look),getString(R.string.something_look),getString(R.string.friendship_look)};
    String[] genderString = {getString(R.string.man_look),getString(R.string.women_look),getString(R.string.man_woman_look)};
//    String email,gender,dob,image,mobile,userName,name,address,bio,disability,secondDisability,userLat,userLong,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking_for);
        helper=new FirebaseHelper(LookingFor.this);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(wantCheck==true&&genderCheck==true)
               {
                   String looking=Want+" and "+Gender;
//                   helper.UpdateProfile(BaseHelper.UserID, name, userName, mobile, email, gender, disability, secondDisability, image, dob, address, userLat, userLong, descriptionText,homeTownText,professionText, looking, relationshipStatusText,
//                           bodyHeightText, educationText, smokerText, religionText, bodyTypeText, languageSpeakText, drinkingText, exerciseText, tattoosText, petsText, musicText, dietText, childrenText, bio);
                   FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("looking_for").setValue(looking);
                   Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(getApplicationContext(),ProfileEdit.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   startActivity(intent);
                   Animatoo.animateSlideDown(LookingFor.this);
                   finish();
               }
               else
               {
                   if(wantCheck==false)
                   {
                       Toast.makeText(getApplicationContext(),"Please select your want", Toast.LENGTH_SHORT).show();
                   }
                   else if(genderCheck==false)
                   {
                       Toast.makeText(getApplicationContext(),"Please select your desired gender", Toast.LENGTH_SHORT).show();
                   }
               }
            }
        });
//        seekBar=(RangeSeekBar)findViewById(R.id.rangeSeekbar);
//        seekBar.setRangeValues(20,35);
//        seekBar.setSelectedMinValue(20);
//        seekBar.setSelectedMaxValue(35);
//        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
//            @Override
//            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
//                //Now you have the minValue and maxValue of your RangeSeekbar
//                //Toast.makeText(getApplicationContext(), minValue + "-" + maxValue, Toast.LENGTH_LONG).show();
//            }
//        });
//        seekBar.setNotifyWhileDragging(true);
        WantSpinner=(Spinner)findViewById(R.id.WantSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, wantString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        WantSpinner.setAdapter(adapter);
        WantSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0) {
                    wantCheck=true;
                    Want = wantString[i];
                    Toast.makeText(getApplicationContext(), wantString[i], Toast.LENGTH_SHORT).show();
                }
                else
                {
                    wantCheck=false;
                    Toast.makeText(getApplicationContext(),"Please select your want", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        GenderSpinner=(Spinner)findViewById(R.id.GenderSpinner);
        ArrayAdapter<String> GenderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genderString);
        GenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        GenderSpinner.setAdapter(GenderAdapter);
        GenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0) {
                    genderCheck=true;
                    Gender = genderString[i];
                    Toast.makeText(getApplicationContext(), genderString[i], Toast.LENGTH_SHORT).show();
                }
                else
                {
                    genderCheck=false;
                    Toast.makeText(getApplicationContext(),"Please select your desired gender", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //GetUserProfile();
    }

    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(LookingFor.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(LookingFor.this);
    }
}