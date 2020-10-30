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

public class UpdateDescription extends AppCompatActivity {
    EditText updateDescription;
    Button save;
//    String email,gender,dob,image,mobile,userName,name,address,bio,disability,secondDisability,userLat,userLong,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,descriptionText;
    FirebaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_description);
        helper=new FirebaseHelper(UpdateDescription.this);
        updateDescription=(EditText)findViewById(R.id.editTextDescriptionUpdate);
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String UpdatedDesc=updateDescription.getText().toString();
                    if(TextUtils.isEmpty(UpdatedDesc))
                    {
                        Toast.makeText(getApplicationContext(), "Please enter description to update", Toast.LENGTH_SHORT).show();
                    }
                    else {
//                        helper.UpdateProfile(BaseHelper.UserID, name, userName, mobile, email, gender, disability, secondDisability, image, dob, address, userLat, userLong, UpdatedDesc, homeTownText, professionText, lookingForText, relationshipStatusText,
//                                bodyHeightText, educationText, smokerText, religionText, bodyTypeText, languageSpeakText, drinkingText, exerciseText, tattoosText, petsText, musicText, dietText, childrenText, bio);
                        FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("description").setValue(UpdatedDesc);
                        Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ProfileEdit.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        Animatoo.animateSlideDown(UpdateDescription.this);
                        finish();
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Problem with profile update", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(UpdateDescription.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(UpdateDescription.this);
    }
}