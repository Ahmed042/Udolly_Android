package com.example.dolly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.Helper.BaseHelper;
import com.example.dolly.Helper.FirebaseHelper;
import com.example.dolly.Model.FireBaseModels.UserProfileModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class UpdateName extends AppCompatActivity {
    Button save;
    private EditText txt_username;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView,txt_date_of_birth;
    FirebaseAuth auth;
    boolean userNameAlready=false;
    ProgressBar progressBar;
    FirebaseHelper helper;
    private int year, month, day;
//    String email,gender,dob,image,mobile,userName,name,address,bio,disability,secondDisability,userLat,userLong,homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
//            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,descriptionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_name);
        helper=new FirebaseHelper(UpdateName.this);
        txt_username = (EditText) findViewById(R.id.txt_Username);
        txt_date_of_birth = (TextView) findViewById(R.id.txt_date_of_birth);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        txt_date_of_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(999);
                showDate(year, month+1, day);
            }
        });
        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String date=txt_date_of_birth.getText().toString();
                    String changeName=txt_username.getText().toString();
                    if(TextUtils.isEmpty(changeName))
                    {
                        Toast.makeText(getApplicationContext(), "Please enter your new Username", Toast.LENGTH_SHORT).show();
                    }
                    else {
                      checkUserName(date,changeName);
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Problem with profile update", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void checkUserName(final String date, final String changeName)
    {
        progressBar.setVisibility(View.VISIBLE);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("profiles");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        if(!snapshot.hasChild(auth.getCurrentUser().getUid())) {
                            if (dataSnapshot.exists()) {
                                UserProfileModel model = dataSnapshot.getValue(UserProfileModel.class);
                                if (TextUtils.equals(model.getUser_name(), changeName)) {
                                    userNameAlready = true;
                                    break;
                                }
                            }
                        }
                    }

                    if (userNameAlready == true) {
                        Toast.makeText(getApplicationContext(), "Username already exist, Please choose unique username",
                                Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        userNameAlready=false;
                    }
                    else {
                        FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("date_of_birth").setValue(date);
                        FirebaseDatabase.getInstance().getReference().child("profiles").child(BaseHelper.UserID).child("user_name").setValue(changeName);
                        FirebaseDatabase.getInstance().getReference().child("users").child(BaseHelper.UserID).child("username").setValue(changeName);
                        Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ProfileEdit.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        Animatoo.animateSlideDown(UpdateName.this);
                        finish();
                        progressBar.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error Logging In...", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year-19, month, day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    Calendar min = Calendar.getInstance();
                    Calendar max = Calendar.getInstance();
                    min.add(Calendar.YEAR, -90); // subtract 2 years from now
                    arg0.setMinDate(min.getTimeInMillis());
                    max.add(Calendar.YEAR, -19); // add 4 years to min date to have 2 years after now
                    arg0.setMaxDate(max.getTimeInMillis());
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        txt_date_of_birth.setText("");
        txt_date_of_birth.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(UpdateName.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(UpdateName.this);
    }
}