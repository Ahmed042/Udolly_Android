package com.example.dolly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.Helper.BaseHelper;
import com.google.firebase.database.FirebaseDatabase;

public class BecomeVip extends AppCompatActivity {
    Button subscribe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_vip);
        subscribe=(Button)findViewById(R.id.btnSubscribe);
        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(BecomeVip.this)
                        .setTitle(R.string.become_vip)
                        .setMessage(R.string.are_you_vip)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    FirebaseDatabase.getInstance().getReference().child("vip_users").child(BaseHelper.UserID).setValue(true);
                                    Toast.makeText(getApplicationContext(),"You have successfully subscribed, Thank you",Toast.LENGTH_SHORT).show();
                                    BaseHelper.isVip=true;
                                    Intent intent = new Intent(getApplicationContext(), MainHome.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    Animatoo.animateSlideUp(BecomeVip.this);
                                    finish();
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getApplicationContext(), "Problem with subscription", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(getResources().getDrawable(R.drawable.ic_baseline_monetization_on_24))
                        .show();
            }
        });
        // getSubscription();
    }
//    public void getSubscription() {
//        try {
//            DatabaseReference MaleuserDb = FirebaseDatabase.getInstance().getReference().child("subscribed_users").child(BaseHelper.UserID);
//            MaleuserDb.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    try {
//                        if (dataSnapshot.exists()) {
//                            BaseHelper.isSubscribed=true;
//                            subscribe.setEnabled(false);
//                            subscribe.setText("Subscribed");
//                            Toast.makeText(getApplicationContext(), "Your", Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//                            BaseHelper.isSubscribed=false;
//                            subscribe.setEnabled(true);
//                            subscribe.setText("Subscribe");
//                        }
//                    } catch (Exception e) {
//                        //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//
//            });
//        } catch (Exception e) {
//            //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
//        }
//    }
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(BecomeVip.this);
    }
}