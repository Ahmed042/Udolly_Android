package com.example.datingpro;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.datingpro.Fragments.LikesFragment;
import com.example.datingpro.Fragments.MessagesFragment;
import com.example.datingpro.Fragments.NearByFragment;
import com.example.datingpro.Fragments.ProfileFragment;
import com.example.datingpro.Fragments.ZappingFragment;
import com.example.datingpro.Helper.BaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainHome extends AppCompatActivity {
    BottomNavigationView navView;
    FrameLayout frame;
    FirebaseAuth auth;
    FirebaseUser User;
    ZappingFragment zappingFragment;
    NearByFragment nearByFragment;
    LikesFragment likesFragment;
    MessagesFragment messagesFragment;
    ProfileFragment profileFragment;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);
        frame=findViewById(R.id.fragment_container);
        navView.setOnNavigationItemSelectedListener(navListener);
        zappingFragment=new ZappingFragment();
        nearByFragment=new NearByFragment();
        likesFragment=new LikesFragment();
        messagesFragment=new MessagesFragment();
        profileFragment=new ProfileFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new ZappingFragment()).commit();
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        auth = FirebaseAuth.getInstance();
        User=auth.getCurrentUser();
        BaseHelper.UserID=User.getUid();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            selectedFragment = new ZappingFragment();
//                            navView.setBackgroundColor(Color.TRANSPARENT);
//                            navView.setItemTextColor(ColorStateList.valueOf(Color.WHITE));
//                            navView.setItemIconTintList(ColorStateList.valueOf(Color.WHITE));
                            break;
                        case R.id.navigation_discover:
                            selectedFragment = new NearByFragment();
                            break;
                        case R.id.navigation_add:
                            selectedFragment = new LikesFragment();
                            break;
                        case R.id.navigation_inbox:
                            selectedFragment = new MessagesFragment();
                            break;
                        case R.id.navigation_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}