package com.example.dolly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.dolly.Model.FireBaseModels.UserModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class DateOfBirth extends AppCompatActivity {
    private Button btn_signup;
    private EditText txt_phone;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference databasreference;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView,txt_date_of_birth;
    FirebaseHelper helper;
    private int year, month, day;
    String Age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_of_birth);
        helper=new FirebaseHelper(this);
        databasreference = FirebaseDatabase.getInstance().getReference("users");
        txt_date_of_birth = (TextView) findViewById(R.id.txt_date_of_birth);
        txt_phone = (EditText) findViewById(R.id.txt_phone);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        auth = FirebaseAuth.getInstance();
        txt_date_of_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(999);
                //showDate(year, month+1, day);
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = txt_date_of_birth.getText().toString();
                String ph = txt_phone.getText().toString().trim();
                if (TextUtils.isEmpty(date)) {
                    Toast.makeText(getApplicationContext(), "Enter your Date of Birth!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ph)) {
                    Toast.makeText(getApplicationContext(), "Enter your Phone Number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                String Date = txt_date_of_birth.getText().toString();
                String Phone = txt_phone.getText().toString();
                FirebaseUser User = auth.getCurrentUser();
                GoogleSignInAccount acct= GoogleSignIn.getLastSignedInAccount(DateOfBirth.this);
                if(acct!=null)
                {
                BaseHelper.firstName=acct.getDisplayName();
                BaseHelper.userName=acct.getGivenName();
                BaseHelper.email=acct.getEmail();
                }
                if(BaseHelper.isFacebook==true)
                {
                    FirebaseUser user = auth.getCurrentUser();
                    BaseHelper.firstName=user.getDisplayName();
                    BaseHelper.userName=user.getDisplayName();
                    BaseHelper.email=user.getEmail();
                }
                String id = User.getUid();
                byte[] b = BaseHelper.profileImageBytes;
                UploadPhoto(b, id, BaseHelper.userName,Phone,BaseHelper.email, Date);

                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.

            }

        });
    }
    public void UploadPhoto(byte[] UploadBytes, final String id, final String UserName, final String Phone, final String Email, final String Date)
    {
        try {
            StorageReference pictureRef = FirebaseStorage.getInstance().getReference().child("pictures/users/" + "/" + id + "/" + "profile_picture");
            UploadTask uploadTask = null;
            uploadTask = pictureRef.putBytes(UploadBytes);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    if (taskSnapshot.getMetadata() != null) {
                        if (taskSnapshot.getMetadata().getReference() != null) {
                            Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                            result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //Toast.makeText(getApplicationContext(), "Photo Uploaded", Toast.LENGTH_SHORT).show();
                                    String pictureUrl = uri.toString();
                                    BaseHelper.profileImage = pictureUrl;
                                    UserModel user = new UserModel(id, BaseHelper.firstName, UserName, Phone, Email, BaseHelper.gender, BaseHelper.disabilitySelect, BaseHelper.disability, BaseHelper.description, Age, BaseHelper.profileImage);
                                    databasreference.child(id).setValue(user);
                                    helper.AddProfile(id, BaseHelper.firstName, UserName, Phone, Email, Date);
                                    helper.AddFilter(id);
                                    progressBar.setVisibility(View.GONE);
                                    Intent intent = new Intent(getApplicationContext(), MainHome.class);
                                    startActivity(intent);
                                    Animatoo.animateFade(DateOfBirth.this);
                                    finish();
                                }
                            });
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                    //CameraFragment.progressBarVideo.setProgress((int)progress);
                }
            }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(DateOfBirth.this, "Can't be able to sign up",
                    Toast.LENGTH_SHORT).show();
        }
    }
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        Dialog dateDialog;
        if (id == 999) {
            dateDialog=new DatePickerDialog(this, myDateListener, year-19, month, day);
            return  dateDialog;
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
//                    Calendar today = Calendar.getInstance();
//                    int years=today.get(Calendar.YEAR);
                    Calendar min = Calendar.getInstance();
                    Calendar max = Calendar.getInstance();
                    min.add(Calendar.YEAR, -90); // subtract 2 years from now
                    arg0.setMinDate(min.getTimeInMillis());
                    max.add(Calendar.YEAR, -19); // add 4 years to min date to have 2 years after now
                    arg0.setMaxDate(max.getTimeInMillis());
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg0.getYear(), arg0.getMonth()+1, arg0.getDayOfMonth());
                }
            };

    private void showDate(int year, int month, int day) {
        txt_date_of_birth.setText("");
        txt_date_of_birth.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
        Age=getAge(year,month,day);
    }
    private String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }
}