package com.example.dolly.Fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dolly.Adapter.NearByAdapter;
import com.example.dolly.Helper.BaseHelper;
import com.example.dolly.Model.FireBaseModels.FilterModel;
import com.example.dolly.Model.FireBaseModels.UserProfileModel;
import com.example.dolly.Model.NearByModel;
import com.example.dolly.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class NearByFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    NearByAdapter adapter;
    FirebaseAuth auth;
    String userGender,oppositeGender;
    ProgressBar progressBar;
    String locationLat,locationLong;
    RelativeLayout noNearBy,nearby;
    public ArrayList<NearByModel> modelList=new ArrayList<>();
    String gender,disability,secondDisability,professionText,relationshipStatusText,favouriteSportsText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    String genderOriginal,disabilityOriginal,secondDisabilityOriginal,professionTextOriginal,relationshipStatusTextOriginal,favouriteSportsTextOriginal,educationTextOriginal,smokerTextOriginal,religionTextOriginal,bodyTypeTextOriginal,languageSpeakTextOriginal,
            drinkingTextOriginal,exerciseTextOriginal,tattoosTextOriginal,petsTextOriginal,musicTextOriginal,dietTextOriginal,childrenTextOriginal,distanceTextOriginal,ageTextOriginal,ageMaxTextOriginal;
    float compareDistance=0;
    FirebaseUser user;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(BaseHelper.NearBy==false) {
        view = inflater.inflate(R.layout.nearby_fragment, container, false);
//            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
//            userGender = prefs.getString("userGender", "");
//            oppositeGender = prefs.getString("oppositeGender", "");
            BaseHelper.isBlocked=false;
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        progressBar=(ProgressBar)view.findViewById(R.id.progressNearBy);
        nearby=(RelativeLayout)view.findViewById(R.id.nearByRelative);
        noNearBy=(RelativeLayout)view.findViewById(R.id.noNearByRelative);
        auth=FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
            GetUserFilters();
            //BaseHelper.NearBy=true;
        }
        return view;
    }
    public void GetUserFilters()
    {
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("filters");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FilterModel model = dataSnapshot.getValue(FilterModel.class);
                    if(TextUtils.equals(model.getId(), BaseHelper.UserID)) {
                        ageTextOriginal=model.getAgeMin();
                        ageMaxTextOriginal=model.getAgeMax();
                        genderOriginal=model.getGender();
                        distanceTextOriginal=model.getDistance();
                        disabilityOriginal=model.getSelected_disability();
                        secondDisabilityOriginal=model.getDisability();
                        professionTextOriginal=model.getProfession();
                        relationshipStatusTextOriginal=model.getRelationship_status();
                        favouriteSportsTextOriginal=model.getFavourite_sports();
                        educationTextOriginal=model.getEducation();
                        smokerTextOriginal=model.getSmoker();
                        religionTextOriginal=model.getReligion();
                        bodyTypeTextOriginal=model.getBody_type();
                        languageSpeakTextOriginal=model.getLanguage();
                        drinkingTextOriginal=model.getDrinking();
                        exerciseTextOriginal=model.getExercise();
                        tattoosTextOriginal=model.getTattoos();
                        petsTextOriginal=model.getPets();
                        musicTextOriginal=model.getMusic();
                        dietTextOriginal=model.getDiet();
                        childrenTextOriginal=model.getChildren();
                        getUserLatLong();
                        break;
                    }
                }
                //dummyData();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void dummyData(final String userId)
    {
        final FirebaseUser user = auth.getCurrentUser();
        try {
        DatabaseReference profileDb = FirebaseDatabase.getInstance().getReference().child("profiles");
        profileDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    if(dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            UserProfileModel model = snapshot.getValue(UserProfileModel.class);
                            getOriginalValues();
                            int ageLow = Integer.parseInt(ageText);
                            int ageHigh = Integer.parseInt(ageMaxText);
                            int userAge;
                            if (TextUtils.equals(model.getUser_id(), userId)) {
                                String[] arr = model.getDate_of_birth().split("/");
                                int day = Integer.parseInt(arr[0]);
                                int month = Integer.parseInt(arr[1]);
                                int year = Integer.parseInt(arr[2]);
                                String age = getAge(year, month, day);
                                userAge = Integer.parseInt(age);
                                locationLat = model.getLocation_lat();
                                locationLong = model.getLocation_long();
                                float distance = getDistance(locationLat, locationLong, BaseHelper.locationLat, BaseHelper.locationLong);
                                float Distance = (distance / 1000);
                                String userDistance = String.valueOf(String.format("%.1f", Distance)) + " Km";
                                if (TextUtils.equals(disability, "Disability doesn't matter")) {
                                    disability = model.getSelected_disability();
                                }
                                if (TextUtils.equals(distanceText, "Distance doesn't matter")) {
                                    compareDistance = 1000000;
                                }
                                if (TextUtils.equals(distanceText, "Distance up to 10 km")) {
                                    compareDistance = 10;
                                }
                                if (TextUtils.equals(distanceText, "Distance up to 50 km")) {
                                    compareDistance = 50;
                                }
                                if (TextUtils.equals(distanceText, "Distance up to 100 km")) {
                                    compareDistance = 100;
                                }
                                if (TextUtils.equals(distanceText, "Distance up to 500 km")) {
                                    compareDistance = 500;
                                }
                                if (TextUtils.equals(professionText, "All")) {
                                    professionText = model.getProfession();
                                }
                                if (TextUtils.equals(relationshipStatusText, "All")) {
                                    relationshipStatusText = model.getRelationship_status();
                                }
                                if (TextUtils.equals(favouriteSportsText, "All")) {
                                    favouriteSportsText = model.getFavourite_sports();
                                }
                                if (TextUtils.equals(educationText, "All")) {
                                    educationText = model.getEducation();
                                }
                                if (TextUtils.equals(smokerText, "All")) {
                                    smokerText = model.getSmoker();
                                }
                                if (TextUtils.equals(religionText, "All")) {
                                    religionText = model.getReligion();
                                }
                                if (TextUtils.equals(bodyTypeText, "All")) {
                                    bodyTypeText = model.getBody_type();
                                }
                                if (TextUtils.equals(drinkingText, "All")) {
                                    drinkingText = model.getDrinking();
                                }
                                if (TextUtils.equals(exerciseText, "All")) {
                                    exerciseText = model.getExercise();
                                }
                                if (TextUtils.equals(tattoosText, "All")) {
                                    tattoosText = model.getTattoos();
                                }
                                if (TextUtils.equals(petsText, "All")) {
                                    petsText = model.getPets();
                                }
                                if (TextUtils.equals(dietText, "All")) {
                                    dietText = model.getDiet();
                                }
                                if (TextUtils.equals(childrenText, "All")) {
                                    childrenText = model.getChildren();
                                }
                                if (TextUtils.equals(gender, model.getGender())
                                        && userAge <= ageHigh && userAge >= ageLow
                                        && Distance <= compareDistance
                                        && TextUtils.equals(disability, model.getSelected_disability())
                                        && TextUtils.equals(professionText, model.getProfession())
                                        && TextUtils.equals(relationshipStatusText, model.getRelationship_status())
//                                        && TextUtils.equals(favouriteSportsText, model.getFavourite_sports())
                                        && TextUtils.equals(educationText, model.getEducation())
                                        && TextUtils.equals(smokerText, model.getSmoker())
                                        && TextUtils.equals(religionText, model.getReligion())
//                                        && TextUtils.equals(bodyTypeText, model.getBody_type())
                                        && TextUtils.equals(drinkingText, model.getDrinking())
                                        && TextUtils.equals(exerciseText, model.getExercise())
                                        && TextUtils.equals(tattoosText, model.getTattoos())
                                        && TextUtils.equals(petsText, model.getPets())
                                        && TextUtils.equals(dietText, model.getDiet())
                                        && TextUtils.equals(childrenText, model.getChildren())) {
                                    noNearBy.setVisibility(View.GONE);
                                    nearby.setVisibility(View.VISIBLE);
                                    String id = model.getUser_id();
                                    String name = model.getUser_name();
                                    String image = model.getImage();
                                    modelList.add(new NearByModel(id, name, userDistance, image));
                                } else {
                                    if(modelList.size()==0) {
                                        noNearBy.setVisibility(View.VISIBLE);
                                        nearby.setVisibility(View.GONE);
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        noNearBy.setVisibility(View.VISIBLE);
                        nearby.setVisibility(View.GONE);
                    }
                    adapter = new NearByAdapter(getContext(), modelList);
                    //recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(false);
                    progressBar.setVisibility(View.GONE);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Error Loading...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),"Loading...",Toast.LENGTH_SHORT).show();
        }
    }
    public void getUnblockedUsers() {
        try {
            DatabaseReference OppositeuserDb = FirebaseDatabase.getInstance().getReference().child("users");
            OppositeuserDb.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    try {
                        if (dataSnapshot.exists() && !dataSnapshot.child("user_id").getValue().equals(user.getUid())
                                && !dataSnapshot.child("connections").child("dislikes").hasChild(user.getUid())
                                && !dataSnapshot.child("connections").child("likes").hasChild(user.getUid())
                                && !dataSnapshot.child("connections").child("sent_likes").hasChild(user.getUid())
                                && !dataSnapshot.child("blocks").child("block_by_others").hasChild(user.getUid())
                                && !dataSnapshot.child("blocks").child("block_by_me").hasChild(user.getUid())) {
                            String id = dataSnapshot.child("user_id").getValue().toString();
                            dummyData(id);

                        } else {
                        }

                    }catch (Exception e)
                    {
                        //Toast.makeText(getContext(), "Error Loading....", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), "Error Loading...", Toast.LENGTH_SHORT).show();
        }

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
                        getUnblockedUsers();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Error Loading...", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void getOriginalValues() {
        ageText = ageTextOriginal;
        ageMaxText = ageMaxTextOriginal;
        gender = genderOriginal;
        distanceText = distanceTextOriginal;
        disability = disabilityOriginal;
        secondDisability = secondDisabilityOriginal;
        professionText = professionTextOriginal;
        relationshipStatusText = relationshipStatusTextOriginal;
        favouriteSportsText = favouriteSportsTextOriginal;
        educationText = educationTextOriginal;
        smokerText = smokerTextOriginal;
        religionText = religionTextOriginal;
        bodyTypeText = bodyTypeTextOriginal;
        languageSpeakText = languageSpeakTextOriginal;
        drinkingText = drinkingTextOriginal;
        exerciseText = exerciseTextOriginal;
        tattoosText = tattoosTextOriginal;
        petsText = petsTextOriginal;
        musicText = musicTextOriginal;
        dietText = dietTextOriginal;
        childrenText = childrenTextOriginal;
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
    public float getDistance (String lat_A, String lng_A, String lat_B, String lng_B )
    {
        if(TextUtils.isEmpty(lat_A)||TextUtils.isEmpty(lat_B)||TextUtils.isEmpty(lng_A)||TextUtils.isEmpty(lng_B))
        {
            return 0;
        }
        else {
            float lat_a = Float.parseFloat(lat_A);
            float lng_a = Float.parseFloat(lng_A);
            float lat_b = Float.parseFloat(lat_B);
            float lng_b = Float.parseFloat(lng_B);
            double earthRadius = 3958.75;
            double latDiff = Math.toRadians(lat_b - lat_a);
            double lngDiff = Math.toRadians(lng_b - lng_a);
            double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) +
                    Math.cos(Math.toRadians(lat_a)) * Math.cos(Math.toRadians(lat_b)) *
                            Math.sin(lngDiff / 2) * Math.sin(lngDiff / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double distance = earthRadius * c;

            int meterConversion = 1609;

            return new Float(distance * meterConversion).floatValue();
        }
    }
}
