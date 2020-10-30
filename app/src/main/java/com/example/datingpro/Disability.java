package com.example.datingpro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.datingpro.Helper.BaseHelper;

import androidx.appcompat.app.AppCompatActivity;

public class Disability extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
Button disability;
   Spinner spin,spin2;
   EditText disabilityText;
   boolean check=false,secondCheck=false,isDependent=false;
        String[] disabilityString = {"I Take","Physics", "Auditory", "Visual", "Intelectual", "Mental", "Mental Acquired brain damage",
                "Autism or Asperger's", "Down's Syndrome", "Person in charge of a handicapped person"};
    String[] defaultString = {"None"};
    String[] populateString = {"I Take", "Physics", "Auditory", "Visual", "Intelectual", "Mental", "Mental Acquired brain damage",
            "Autism or Asperger's", "Down's Syndrome"};
        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disability);
        //disabilityText=(EditText)findViewById(R.id.editTextDisability);
            spin2=(Spinner)findViewById(R.id.secondDisabilitySpinner);
            SpinDefault();
        disability = (Button) findViewById(R.id.btnDisable);
        disability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(check==false)
                {
                    Toast.makeText(getApplicationContext(),"Please Choose Your disability",Toast.LENGTH_SHORT).show();
                }
                else if(isDependent==true&&secondCheck==false)
                {
                    Toast.makeText(getApplicationContext(),"Please Choose your disability",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), Location.class);
                    startActivity(intent);
                    Animatoo.animateFade(Disability.this);
                    finish();
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
                BaseHelper.disability="None";
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
                    BaseHelper.disability=populateString[i];
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
                BaseHelper.disabilitySelect=disabilityString[position];
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideRight(Disability.this);
    }
}
