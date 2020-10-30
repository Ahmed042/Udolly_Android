package com.example.datingpro;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class Settings extends AppCompatActivity {
RelativeLayout account,notifications,privacy,help,info,language,blocked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        account=(RelativeLayout)findViewById(R.id.account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Account.class);
                startActivity(intent);
                Animatoo.animateSlideUp(Settings.this);
            }
        });
        notifications=(RelativeLayout)findViewById(R.id.notifications);
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Notifications.class);
                startActivity(intent);
                Animatoo.animateSlideUp(Settings.this);
            }
        });

        privacy=(RelativeLayout)findViewById(R.id.privacy);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Privacy.class);
                startActivity(intent);
                Animatoo.animateSlideUp(Settings.this);
            }
        });
        help=(RelativeLayout)findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Help.class);
                startActivity(intent);
                Animatoo.animateSlideUp(Settings.this);
            }
        });
        info=(RelativeLayout)findViewById(R.id.info_and_terms);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InfoAndTerms.class);
                startActivity(intent);
                Animatoo.animateSlideUp(Settings.this);
            }
        });
        language=(RelativeLayout)findViewById(R.id.setLanguage);
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SetLanguage.class);
                startActivity(intent);
                Animatoo.animateSlideUp(Settings.this);
            }
        });
        blocked=(RelativeLayout)findViewById(R.id.blocked_users);
        blocked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BlockUsers.class);
                startActivity(intent);
                Animatoo.animateSlideUp(Settings.this);
            }
        });
    }
    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(Settings.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(Settings.this);
    }
}