package com.example.dolly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import com.example.dolly.Model.FireBaseModels.UserProfileModel;
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

public class SignUp extends AppCompatActivity {
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
    String Age;
    boolean userNameAlready=false,check=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
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

//        txt_date_of_birth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    showDialog(999);
//                    showDate(year, month+1, day);
//                }
//            }
//        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameAlready=false;
                if(check==false) {
                    CheckUserName();
                }
//                String email = txt_email.getText().toString().trim();
//                String ph = txt_phone.getText().toString().trim();
//                String password = txt_pwd.getText().toString().trim();
//                String Username = txt_username.getText().toString().trim();
//                String date=txt_date_of_birth.getText().toString();
//                //CheckUserName();
//                if (TextUtils.isEmpty(ph)) {
//                    Toast.makeText(getApplicationContext(), "Enter your Phone Number!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(date)) {
//                    Toast.makeText(getApplicationContext(), "Enter your Date of Birth!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(Username)) {
//                    Toast.makeText(getApplicationContext(), "Enter Username!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(email)) {
//                    Toast.makeText(getApplicationContext(), "Enter Email Address!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (TextUtils.isEmpty(password)) {
//                    Toast.makeText(getApplicationContext(), "Enter Password!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (password.length() < 6) {
//                    txt_pwd.setError(getString(R.string.minimum_password));
//                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(userNameAlready==true)
//                {
//                    Toast.makeText(getApplicationContext(), "Username already exist, Please choose unique username",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                progressBar.setVisibility(View.VISIBLE);
//                auth.createUserWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                Toast.makeText(SignUp.this, "Successfully Sign Up" +
//                                        task.isSuccessful(), Toast.LENGTH_SHORT).show();
//                                //String Name=txt_name.getText().toString();
//                                String UserName=txt_username.getText().toString();
//                                String Phone=txt_phone.getText().toString();
//                                String Email=txt_email.getText().toString();
//                                String Date=txt_date_of_birth.getText().toString();
//                                FirebaseUser User=auth.getCurrentUser();
//                                String id=User.getUid();
//                                byte[] b=BaseHelper.profileImageBytes;
//                                UploadPhoto(b,id,UserName,Phone,Email,Date);
//                                progressBar.setVisibility(View.GONE);
//                                // If sign in fails, display a message to the user. If sign in succeeds
//                                // the auth state listener will be notified and logic to handle the
//                                // signed in user can be handled in the listener.
//                                if (!task.isSuccessful()) {
//                                    Toast.makeText(SignUp.this, "Authentication failed." +
//                                                    task.getException(),
//                                            Toast.LENGTH_SHORT).show();
//                                } else {
//
//                                    Animatoo.animateSlideRight(SignUp.this);
//                                    finish();
//                                }
//                            }
//                        });
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
                                    Toast.makeText(getApplicationContext(), "Signed Up Successfully", Toast.LENGTH_SHORT).show();
                                    String pictureUrl = uri.toString();
                                    BaseHelper.profileImage = pictureUrl;
                                    check=true;
                                    UserModel user = new UserModel(id, BaseHelper.firstName, UserName, Phone, Email, BaseHelper.gender, BaseHelper.disabilitySelect, BaseHelper.disability, BaseHelper.description, Age, BaseHelper.profileImage);
                                    databasreference.child(id).setValue(user);
                                    helper.AddProfile(id, BaseHelper.firstName, UserName, Phone, Email, Date);
                                    helper.AddFilter(id);
                                    progressBar.setVisibility(View.GONE);
                                    Animatoo.animateSlideRight(SignUp.this);
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
                    if (progress - 15 > mUploadProgress) {
                        //Toast.makeText(getApplicationContext(),"Upload Progress:"+progress ,Toast.LENGTH_SHORT).show();
                        mUploadProgress = progress;
                    }
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
            Toast.makeText(SignUp.this, "Can't be able to sign up",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void CheckUserName()
    {
        if(check==false) {
            progressBar.setVisibility(View.VISIBLE);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("profiles");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            if (dataSnapshot.exists()) {
                                UserProfileModel model = dataSnapshot.getValue(UserProfileModel.class);
                                if (TextUtils.equals(model.getUser_name(), txt_username.getText().toString().trim())) {
                                    userNameAlready = true;
                                }
                            }
                        }
                        String email = txt_email.getText().toString().trim();
                        String ph = txt_phone.getText().toString().trim();
                        String password = txt_pwd.getText().toString().trim();
                        String Username = txt_username.getText().toString().trim();
                        String date = txt_date_of_birth.getText().toString();
                        //CheckUserName();
                        if (TextUtils.isEmpty(ph)) {
                            Toast.makeText(getApplicationContext(), "Enter your Phone Number!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            return;
                        }
                        if (TextUtils.isEmpty(date)) {
                            Toast.makeText(getApplicationContext(), "Enter your Date of Birth!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            return;
                        }
                        if (TextUtils.isEmpty(Username)) {
                            Toast.makeText(getApplicationContext(), "Enter Username!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            return;
                        }
                        if (TextUtils.isEmpty(email)) {
                            Toast.makeText(getApplicationContext(), "Enter Email Address!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            return;
                        }

                        if (TextUtils.isEmpty(password)) {
                            Toast.makeText(getApplicationContext(), "Enter Password!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            return;
                        }
                        if (password.length() < 6) {
                            txt_pwd.setError(getString(R.string.minimum_password));
                            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            return;
                        }
                        if (userNameAlready == true && check == false) {
                            Toast.makeText(getApplicationContext(), "Username already exist, Please choose unique username",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            return;
                        }

                        auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        // If sign in fails, display a message to the user. If sign in succeeds
                                        // the auth state listener will be notified and logic to handle the
                                        // signed in user can be handled in the listener.
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(SignUp.this, "Authentication failed." +
                                                            task.getException(),
                                                    Toast.LENGTH_SHORT).show();
                                            return;
                                        } else {
//                                            Toast.makeText(SignUp.this, "Successfully Sign Up" +
//                                                    task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                            //String Name=txt_name.getText().toString();
                                            String UserName = txt_username.getText().toString();
                                            String Phone = txt_phone.getText().toString();
                                            String Email = txt_email.getText().toString();
                                            String Date = txt_date_of_birth.getText().toString();
                                            FirebaseUser User = auth.getCurrentUser();
                                            String id = User.getUid();
                                            byte[] b = BaseHelper.profileImageBytes;
                                            UploadPhoto(b, id, UserName, Phone, Email, Date);

                                            return;
                                        }
                                    }
                                });
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error Signing Up...", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideRight(SignUp.this);
    }
}