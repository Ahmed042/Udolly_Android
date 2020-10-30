package com.example.datingpro;

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
import com.example.datingpro.Helper.BaseHelper;
import com.example.datingpro.Helper.FirebaseHelper;
import com.example.datingpro.Model.FireBaseModels.UserModel;
import com.example.datingpro.Model.FireBaseModels.UserProfileModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class SignUpWithPhone extends AppCompatActivity {
    private EditText txt_pwd, txt_email,txt_username,txt_phone;
    private Button btn_signup;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference databasreference;
    private double mUploadProgress=0;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView,txt_date_of_birth;
    FirebaseHelper helper;
    private int year, month, day;
    String Age,email,Username,date;
    boolean userNameAlready=false,check=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_with_phone);
        helper=new FirebaseHelper(this);
//        if(TextUtils.equals(BaseHelper.gender,"Male")) {
//            databasreference = FirebaseDatabase.getInstance().getReference("users").child("Male");
//        }
//        else
//        {
//            databasreference = FirebaseDatabase.getInstance().getReference("users").child("Female");
//        }
        databasreference = FirebaseDatabase.getInstance().getReference("users");
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_pwd = (EditText) findViewById(R.id.txt_pwd);
        txt_phone = (EditText) findViewById(R.id.txt_phone);
        TextView txt=(TextView)findViewById(R.id.btn_Login);
        txt_username = (EditText) findViewById(R.id.txt_Username);
        txt_date_of_birth = (TextView) findViewById(R.id.txt_date_of_birth);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                email = txt_email.getText().toString().trim();
                Username = txt_username.getText().toString().trim();
                date = txt_date_of_birth.getText().toString();
                if (TextUtils.isEmpty(date)) {
                    Toast.makeText(getApplicationContext(), "Enter your Date of Birth!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter Email Address!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(Username)) {
                    Toast.makeText(getApplicationContext(), "Enter Username!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                else
                {
                    boolean check=CheckUserName();
                    if (check == true) {
                        Toast.makeText(getApplicationContext(), "Username already exist, Please choose unique username",
                                Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                    else {
                        byte[] b = BaseHelper.profileImageBytes;
                        BaseHelper.userName = Username;
                        BaseHelper.email = email;
                        BaseHelper.date = date;
                        BaseHelper.age = Age;
                        String id = auth.getCurrentUser().getUid();
                        UploadPhoto(BaseHelper.profileImageBytes, id, BaseHelper.userName, BaseHelper.phone, BaseHelper.email, BaseHelper.date);
                    }
                }

            } // end of onclick
        });
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

    public boolean CheckUserName()
    {
            progressBar.setVisibility(View.VISIBLE);
            userNameAlready=false;
                    try {
                        for (DataSnapshot dataSnapshot : BaseHelper.profileSnapshot.getChildren()) {
                            if(!dataSnapshot.hasChild(auth.getCurrentUser().getUid())) {
                                if (dataSnapshot.exists()) {
                                    UserProfileModel model = dataSnapshot.getValue(UserProfileModel.class);
                                    if (TextUtils.equals(model.getUser_name(), txt_username.getText().toString().trim())) {
                                        userNameAlready = true;
                                    }
                                }
                            }
                        }

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error Logging In...", Toast.LENGTH_SHORT).show();
                    }
            return userNameAlready;
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

                                    Toast.makeText(getApplicationContext(), "Logged In Successfully \n"+Phone, Toast.LENGTH_SHORT).show();
                                    String pictureUrl = uri.toString();
                                    BaseHelper.profileImage = pictureUrl;
                                    UserModel user = new UserModel(id, BaseHelper.firstName, UserName, Phone, Email, BaseHelper.gender, BaseHelper.disabilitySelect, BaseHelper.disability, BaseHelper.description,BaseHelper.age, BaseHelper.profileImage);
                                    databasreference.child(id).setValue(user);
                                    helper.AddProfile(id, BaseHelper.firstName, UserName, Phone, Email, Date);
                                    helper.AddFilter(id);
                                    progressBar.setVisibility(View.GONE);
                                    Intent intent = new Intent(getApplicationContext(), MainHome.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
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
            Toast.makeText(SignUpWithPhone.this, "Can't be able to log in by phone",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideRight(SignUpWithPhone.this);
    }
}