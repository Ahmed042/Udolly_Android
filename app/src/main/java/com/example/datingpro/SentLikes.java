package com.example.datingpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.datingpro.Adapter.NearByAdapter;
import com.example.datingpro.Helper.BaseHelper;
import com.example.datingpro.Model.FireBaseModels.UserModel;
import com.example.datingpro.Model.FireBaseModels.UserProfileModel;
import com.example.datingpro.Model.NearByModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SentLikes extends AppCompatActivity {
    View view;
    RecyclerView recyclerView;
    NearByAdapter adapter;
    FirebaseAuth auth;
    ProgressBar progressBar;
    RelativeLayout sentLikes,noSentLikes;
    String locationLat,locationLong;
    boolean check=false;
    public ArrayList<NearByModel> modelList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_likes);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        progressBar=(ProgressBar)findViewById(R.id.progressSentLikes);
        sentLikes=(RelativeLayout)findViewById(R.id.sentLikesRelative);
        noSentLikes=(RelativeLayout)findViewById(R.id.noSentLikesRelative);
        auth=FirebaseAuth.getInstance();
        getUserLatLong();
        //dummyData();
    }
    public void dummyData()
    {
        final FirebaseUser user = auth.getCurrentUser();
        final DatabaseReference profileDb = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("connections").child("sentLikes");
        profileDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            sentLikes.setVisibility(View.VISIBLE);
                            noSentLikes.setVisibility(View.GONE);
                            String id = snapshot.getKey().toString();
                            getProfile(id);
                    }
                }
                else {
                    sentLikes.setVisibility(View.GONE);
                    noSentLikes.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void getProfile(final String userId)
    {
        final FirebaseUser user = auth.getCurrentUser();
        DatabaseReference profileDb = FirebaseDatabase.getInstance().getReference().child("profiles");
        profileDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    UserProfileModel model = snapshot.getValue(UserProfileModel.class);
                    if (TextUtils.equals(model.getUser_id(),userId)) {
                        locationLat=model.getLocation_lat();
                        locationLong=model.getLocation_long();
                        float distance=getDistance(locationLat,locationLong,BaseHelper.locationLat,BaseHelper.locationLong);
                        float Distance=(distance/1000);
                        String userDistance=String.valueOf(String.format("%.1f",Distance))+" Km";
                        String id = model.getUser_id();
                        String name = model.getFull_name();
                        String image = model.getImage();
                        modelList.add(new NearByModel(id, name, userDistance, image));
                    }
                }
                adapter=new NearByAdapter(getApplicationContext(),modelList);
                //recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(false);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        progressBar.setVisibility(View.GONE);
    }
    public void getUserLatLong()
    {
        DatabaseReference MaleuserDb = FirebaseDatabase.getInstance().getReference().child("profiles").child(auth.getCurrentUser().getUid());
        MaleuserDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot != null) {
                        String lat=dataSnapshot.child("location_lat").getValue().toString();
                        String lng=dataSnapshot.child("location_long").getValue().toString();
                        BaseHelper.locationLat=lat;
                        BaseHelper.locationLong=lng;
                        dummyData();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error Loading...", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public float getDistance (String lat_A, String lng_A, String lat_B, String lng_B )
    {
        float lat_a=Float.parseFloat(lat_A);
        float lng_a=Float.parseFloat(lng_A);
        float lat_b=Float.parseFloat(lat_B);
        float lng_b=Float.parseFloat(lng_B);
        double earthRadius = 3958.75;
        double latDiff = Math.toRadians(lat_b-lat_a);
        double lngDiff = Math.toRadians(lng_b-lng_a);
        double a = Math.sin(latDiff /2) * Math.sin(latDiff /2) +
                Math.cos(Math.toRadians(lat_a)) * Math.cos(Math.toRadians(lat_b)) *
                        Math.sin(lngDiff /2) * Math.sin(lngDiff /2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = earthRadius * c;

        int meterConversion = 1609;

        return new Float(distance * meterConversion).floatValue();
    }
    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(SentLikes.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
            Animatoo.animateSlideDown(SentLikes.this
            );
    }
}