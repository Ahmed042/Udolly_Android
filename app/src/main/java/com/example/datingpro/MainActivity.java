package com.example.datingpro;

import android.accounts.Account;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.datingpro.Helper.BaseHelper;
import com.example.datingpro.Model.FireBaseModels.FilterModel;
import com.example.datingpro.Model.FireBaseModels.UserModel;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
Button login,email,fb,phone;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 108;
    private FirebaseAuth mAuth;
    boolean checkMatch=false;
    private CallbackManager mCallbackManager;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(Button)findViewById(R.id.btnGoogle);
        email=(Button)findViewById(R.id.btnEmail);
        fb=(Button)findViewById(R.id.btnFacebook);
        phone=(Button)findViewById(R.id.btnPhone);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();
        createRequest();
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("djvbdfjbv", "facebook:onSuccess:" + loginResult);
                        progressBar.setVisibility(View.VISIBLE);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d("djvbdfjbv", "facebook:onCancel");
                        progressBar.setVisibility(View.GONE);
                        // ...
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d("djvbdfjbv", "facebook:onError", error);
                        progressBar.setVisibility(View.GONE);
                        // ...
                    }
                });
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
//                startActivity(intent);
                progressBar.setVisibility(View.VISIBLE);
                signIn();


            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                startActivity(intent);
               Animatoo.animateFade(MainActivity.this);
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginWithPhone.class);
                startActivity(intent);
                Animatoo.animateFade(MainActivity.this);
            }
        });
    }
    private void createRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(this, "Failed...", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            BaseHelper.firstName=acct.getDisplayName();
                            BaseHelper.userName=acct.getGivenName();
                            BaseHelper.email=acct.getEmail();
                            BaseHelper.isGoogle=true;
                            BaseHelper.isFacebook=false;
                            boolean isNew = task.getResult().getAdditionalUserInfo().isNewUser();

                                    if (isNew == true) {
                                        BaseHelper.isGoogle=true;
                                        Intent intent = new Intent(getApplicationContext(), Gender.class);
                                        Toast.makeText(getApplicationContext(), "Setup account for first time login", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                        Animatoo.animateFade(MainActivity.this);
                                        finish();

                                    } else {
                                        Intent intent = new Intent(getApplicationContext(), MainHome.class);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(), "Profile is already setup by this account", Toast.LENGTH_SHORT).show();
                                        Animatoo.animateFade(MainActivity.this);
                                        finish();
                                    }
                                }

                        else {
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                    progressBar.setVisibility(View.GONE);
                        // ...
                    }
                });
    }


    private void handleFacebookAccessToken(final AccessToken token) {
        Log.d("smdbcdbsc", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Log.d("smdbcdbsc", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            BaseHelper.firstName=user.getDisplayName();
                            BaseHelper.userName=user.getDisplayName();
                            BaseHelper.email=user.getEmail();
                            BaseHelper.isFacebook=true;
                            BaseHelper.isGoogle=false;
                            boolean isNew = task.getResult().getAdditionalUserInfo().isNewUser();

                            if (isNew == true) {
                                BaseHelper.isFacebook=true;
                                Intent intent = new Intent(getApplicationContext(), Gender.class);
                                Toast.makeText(getApplicationContext(), "Setup account for first time login", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                Animatoo.animateFade(MainActivity.this);
                                finish();

                            } else {
                                Intent intent = new Intent(getApplicationContext(), MainHome.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Profile is already setup by this account", Toast.LENGTH_SHORT).show();
                                Animatoo.animateFade(MainActivity.this);
                                finish();
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("smdbcdbsc", "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }

                        // ...
                    }
                });
        progressBar.setVisibility(View.GONE);
    }


}
