package com.example.dolly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import java.util.Locale;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class SetLanguage extends AppCompatActivity {
    Button changeLang;
    Locale myLocale;
    RelativeLayout english,spanish,french;
    RadioButton englishRadio,spanishRadio,frenchRadio;

    String selectedValue="en";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_language);
        changeLang = (Button) findViewById(R.id.btnSave);
        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SaveLanguage(selectedValue);
                  setLocale(selectedValue);
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Problem with profile update", Toast.LENGTH_SHORT).show();
                }
            }
        });

        englishRadio=(RadioButton)findViewById(R.id.radioAskMe);
        spanishRadio=(RadioButton)findViewById(R.id.radioNoWant);
        frenchRadio=(RadioButton)findViewById(R.id.radioHaveWant);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(prefs!=null) {
            selectedValue = prefs.getString("appLanguage", "");
        }
        if(TextUtils.equals(selectedValue,"en"))
        {
            englishRadio.setChecked(true);
        }
        else  if(TextUtils.equals(selectedValue,"es"))
        {
            spanishRadio.setChecked(true);
        }
        else  if(TextUtils.equals(selectedValue,"fr"))
        {
            frenchRadio.setChecked(true);
        }
        english=(RelativeLayout)findViewById(R.id.askMeRelative);
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                englishRadio.setChecked(true);
                selectedValue="en";
            }
        });
        spanish=(RelativeLayout)findViewById(R.id.NoWantRelative);
        spanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                spanishRadio.setChecked(true);
                selectedValue="es";
            }
        });
        french=(RelativeLayout)findViewById(R.id.HaveWantRelative);
        french.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearChecks();
                frenchRadio.setChecked(true);
                selectedValue="fr";
            }
        });
    }
    public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent intent = new Intent(getApplicationContext(), MainHome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Animatoo.animateSlideDown(SetLanguage.this);
        finish();
    }
    public void clearChecks()
    {
        englishRadio.setChecked(false);
        spanishRadio.setChecked(false);
        frenchRadio.setChecked(false);
      }
    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(this);
        finish();
    }
    public void SaveLanguage(String language)
    {
        SharedPreferences genders = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = genders.edit();
        editor.putString("appLanguage",language);
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(this);
    }
}