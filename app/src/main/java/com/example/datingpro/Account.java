package com.example.datingpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class Account extends AppCompatActivity {
RelativeLayout changeEmail,changePassword,restorePurchases,hideProfile,deleteAccount;
boolean checkEmail=false,checkPass=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        changeEmail=(RelativeLayout)findViewById(R.id.changeEmail);
        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (UserInfo user : FirebaseAuth.getInstance().getCurrentUser().getProviderData()) {

                    if (user.getProviderId().equals("facebook.com")) {
                        //For linked facebook account
                        checkEmail=true;
                        Toast.makeText(getApplicationContext(), "You are logged in from facebook, You can not update your email", Toast.LENGTH_SHORT).show();

                    } else if (user.getProviderId().equals("google.com")) {
                        //For linked Google account
                        checkEmail=true;
                        Toast.makeText(getApplicationContext(), "You are logged in from google, You can not update your email", Toast.LENGTH_SHORT).show();
                    }
                }
                if(checkEmail==false)
                {
                    Intent intent = new Intent(getApplicationContext(), ChangeEmail.class);
                    startActivity(intent);
                    Animatoo.animateSlideLeft(Account.this);
                }
            }
        });
        changePassword=(RelativeLayout)findViewById(R.id.changePassword);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (UserInfo user : FirebaseAuth.getInstance().getCurrentUser().getProviderData()) {

                    if (user.getProviderId().equals("facebook.com")) {
                        //For linked facebook account
                        checkPass=true;
                        Toast.makeText(getApplicationContext(), "You are logged in from facebook, You can not change your password", Toast.LENGTH_SHORT).show();

                    } else if (user.getProviderId().equals("google.com")) {
                        //For linked Google account
                        checkPass=true;
                        Toast.makeText(getApplicationContext(), "You are logged in from google, You can not change your password", Toast.LENGTH_SHORT).show();
                    }
                }
                if(checkPass==false)
                {
                    Intent intent = new Intent(getApplicationContext(), ChangePassword.class);
                    startActivity(intent);
                    Animatoo.animateSlideLeft(Account.this);
                }
            }
        });
        restorePurchases=(RelativeLayout)findViewById(R.id.restorePurchases);
        restorePurchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Restore Purchases", Toast.LENGTH_SHORT).show();
            }
        });
        hideProfile=(RelativeLayout)findViewById(R.id.hideProfile);
        hideProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Hide Profile", Toast.LENGTH_SHORT).show();
            }
        });
        deleteAccount=(RelativeLayout)findViewById(R.id.deleteAccount);
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Delete Account", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(this);
        finish();
    }
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(Account.this);
    }

}