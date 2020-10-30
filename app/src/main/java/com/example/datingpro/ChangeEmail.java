package com.example.datingpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.datingpro.Helper.BaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeEmail extends AppCompatActivity {
EditText email,pass,newEmail;
Button updateEmail;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        email=(EditText)findViewById(R.id.txt_email);
        pass=(EditText)findViewById(R.id.txt_pwd);
        newEmail=(EditText)findViewById(R.id.txt_emailNew);
        updateEmail=(Button)findViewById(R.id.btnUpdateEmail);
        updateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String em=email.getText().toString().trim();
                String emNew=newEmail.getText().toString().trim();
                String pwd=pass.getText().toString().trim();
                if(TextUtils.isEmpty(em)||TextUtils.isEmpty(emNew)||TextUtils.isEmpty(pwd))
                {
                    Toast.makeText(getApplicationContext(),"Please fill all the required fields to update email",Toast.LENGTH_SHORT).show();
                }
                changeEmail(em,emNew,pwd);
            }
        });
    }
    public void changeEmail(final String Email, final String NewEmail, String Pass)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // Get auth credentials from the user for re-authentication
        AuthCredential credential = EmailAuthProvider
                .getCredential(Email, Pass); // Current Login Credentials \\
        // Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       if(task.isSuccessful()) {
                           FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                           user.updateEmail(NewEmail)
                                   .addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {
                                           if (!task.isSuccessful()) {
                                               Toast.makeText(ChangeEmail.this, "Email Update failed." +
                                                               task.getException(),
                                                       Toast.LENGTH_SHORT).show();
                                           }
                                           else if (task.isSuccessful()) {
                                               FirebaseDatabase.getInstance().getReference("users").child(BaseHelper.UserID).child("email").setValue(NewEmail);
                                               FirebaseDatabase.getInstance().getReference("profiles").child(BaseHelper.UserID).child("email").setValue(NewEmail);
                                               Toast.makeText(getApplicationContext(),"Email Successfully Updated\n"+NewEmail,Toast.LENGTH_SHORT).show();
                                               Animatoo.animateSlideRight(ChangeEmail.this);
                                               finish();
                                           }
                                       }
                                   });
                       }
                       else {
                           Toast.makeText(getApplicationContext(),"Invalid credentials",Toast.LENGTH_SHORT).show();
                       }
                        //----------------------------------------------------------\\
                    }
                });

        progressBar.setVisibility(View.GONE);
    }
    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideRight(this);
        finish();
    }
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideRight(this);
    }
}