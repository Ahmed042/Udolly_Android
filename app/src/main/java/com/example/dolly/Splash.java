package com.example.dolly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.Helper.BaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gyf.immersionbar.ImmersionBar;

import java.util.Locale;

public class Splash extends AppCompatActivity {
FirebaseAuth auth;
String userSex;
    Locale myLocale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
    }
    public void getUserGender() {
        DatabaseReference MaleuserDb = FirebaseDatabase.getInstance().getReference().child("profiles").child(auth.getCurrentUser().getUid());
        MaleuserDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                if (dataSnapshot != null) {

                    userSex = dataSnapshot.child("gender").getValue().toString();
                    BaseHelper.userLocationLat=dataSnapshot.child("location_lat").getValue().toString();
                    BaseHelper.userLocationLong=dataSnapshot.child("location_long").getValue().toString();
                    if (TextUtils.equals(userSex, "Male")) {
                        BaseHelper.userGender = "Male";
                        BaseHelper.oppositeGender = "Female";
                        //SaveGenders("Male","Female");
                    } else {
                        BaseHelper.oppositeGender = "Male";
                        BaseHelper.userGender = "Female";

                        //SaveGenders("Female","Male");
                    }

                }
                } catch (Exception e) {
                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    protected void init() {
        ImmersionBar.with(this).init();
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            BaseHelper.UserID = auth.getCurrentUser().getUid();
            //getUserGender();
        }
        new CountDownTimer(1500, 1500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                auth = FirebaseAuth.getInstance();
                if (auth.getCurrentUser() != null) {
                    checkUser();
//                    Toast.makeText(Splash.this, "User is already logged in "+auth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
//                    //BaseHelper.UserID=auth.getCurrentUser().getUid();
//                    //getUserGender();
//                    startActivity(new Intent(Splash.this, MainHome.class));
//                    Animatoo.animateFade(Splash.this);
                }
                else
                {
                    startActivity(new Intent(Splash.this,MainActivity.class));
                    Animatoo.animateFade(Splash.this);
                    finish();
                }
                //finish();
            }
        }.start();
    }
    public void getSubscription() {
        try {
            DatabaseReference MaleuserDb = FirebaseDatabase.getInstance().getReference().child("vip_users").child(auth.getCurrentUser().getUid());
            MaleuserDb.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        if (dataSnapshot.exists()) {
                            BaseHelper.isVip=true;
                        }
                    } catch (Exception e) {
                        //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
        } catch (Exception e) {
            //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
        }
    }
    public void checkUser()
    {
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    if (auth != null) {
                        FirebaseUser user = auth.getCurrentUser();
                        String userId = user.getUid();
                        if (snapshot.hasChild(userId)) {
                            getSubscription();
                            setLanguage();
                            Intent intent = new Intent(getApplicationContext(), MainHome.class);
                            startActivity(intent);
                            //Toast.makeText(getApplicationContext(), "User Logged in \n"+user.getEmail(), Toast.LENGTH_SHORT).show();
                            Animatoo.animateFade(Splash.this);
                            finish();
                        } else {
                            if (user.getProviderId().equals("facebook.com")) {
                                BaseHelper.isFacebook = true;
                                Intent intent = new Intent(getApplicationContext(), Gender.class);
                                Toast.makeText(getApplicationContext(), "Setup account for first time login", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                Animatoo.animateFade(Splash.this);
                                finish();
                            } else if (user.getProviderId().equals("google.com")) {
                                BaseHelper.isGoogle = true;
                                Intent intent = new Intent(getApplicationContext(), Gender.class);
                                Toast.makeText(getApplicationContext(), "Setup account for first time login", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                Animatoo.animateFade(Splash.this);
                                finish();
                            }
                            else
                            {
                                Intent intent = new Intent(getApplicationContext(), FirstName.class);
                                Toast.makeText(getApplicationContext(), "Setup account for first time login", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                Animatoo.animateFade(Splash.this);
                                finish();
                            }

                        }
                    }
                } catch (Exception e) {
                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void setLanguage()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(prefs!=null) {
            String lang = prefs.getString("appLanguage", "");
            setLocale(lang);
        }
    }
    public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }
}