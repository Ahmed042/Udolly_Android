package com.example.dolly.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.Adapter.arrayAdapter;
import com.example.dolly.Helper.BaseHelper;
import com.example.dolly.MainHome;
import com.example.dolly.Model.FireBaseModels.FilterModel;
import com.example.dolly.Model.FireBaseModels.UserProfileModel;
import com.example.dolly.Model.SwipeCardsModel;
import com.example.dolly.ProfileDetail;
import com.example.dolly.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ZappingFragment extends Fragment {
    View view;
    private ArrayList<String> al;
    //private ArrayAdapter<String> arrayAdapter;
    com.example.dolly.Adapter.arrayAdapter adapter;
    private int i;
    float compareDistance;
    SwipeFlingAdapterView flingContainer;
    private String userSex,locationLat,locationLong;
    private String oppositeUserSex;
    public ArrayList<SwipeCardsModel> modelList = new ArrayList<>();
    private DatabaseReference Db;
    FirebaseAuth auth;
    ProgressBar progressBar;
    TextView textView;
    FirebaseUser user;
    String gender,disability,secondDisability,professionText,relationshipStatusText,favouriteSportsText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,distanceText,ageText,ageMaxText;
    String genderOriginal,disabilityOriginal,secondDisabilityOriginal,professionTextOriginal,relationshipStatusTextOriginal,favouriteSportsTextOriginal,educationTextOriginal,smokerTextOriginal,religionTextOriginal,bodyTypeTextOriginal,languageSpeakTextOriginal,
            drinkingTextOriginal,exerciseTextOriginal,tattoosTextOriginal,petsTextOriginal,musicTextOriginal,dietTextOriginal,childrenTextOriginal,distanceTextOriginal,ageTextOriginal,ageMaxTextOriginal;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(BaseHelper.Zapping==false) {
        view = inflater.inflate(R.layout.zapping_fragment, container, false);
        auth=FirebaseAuth.getInstance();
            user = FirebaseAuth.getInstance().getCurrentUser();
            BaseHelper.isBlocked=false;
        progressBar=(ProgressBar)view.findViewById(R.id.progressZapping);
        textView=(TextView)view.findViewById(R.id.loadingZapping);
        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) view.findViewById(R.id.frame);
        //Db = FirebaseDatabase.getInstance().getReference().child("users");
        //checkUserSex();
        try {
            final FirebaseUser user = auth.getCurrentUser();
            DatabaseReference MaleuserDb = FirebaseDatabase.getInstance().getReference().child("profiles").child(user.getUid());
            MaleuserDb.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        if (dataSnapshot != null) {
                            userSex = dataSnapshot.child("gender").getValue().toString();
                            String lat=dataSnapshot.child("location_lat").getValue().toString();
                            String lng=dataSnapshot.child("location_long").getValue().toString();
                            BaseHelper.locationLat=lat;
                            BaseHelper.locationLong=lng;
                            if (TextUtils.equals(userSex, "Male")) {
                                oppositeUserSex = "Female";
                                BaseHelper.userGender = "Male";
                                BaseHelper.oppositeGender = "Female";
                                //SaveGenders("Male","Female");
                            } else {
                                oppositeUserSex = "Male";
                                BaseHelper.oppositeGender = "Male";
                                BaseHelper.userGender = "Female";

                                //SaveGenders("Female","Male");
                            }
                            //getOppositeSexUsers();
                            GetUserFilters();
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
catch (Exception e)
{
    Toast.makeText(getContext(), "Error Loading...", Toast.LENGTH_SHORT).show();
}
            al = new ArrayList<>();
            adapter = new arrayAdapter(getContext(), R.layout.item, modelList);
            flingContainer.setAdapter(adapter);

            //flingContainer.setAdapter(arrayAdapter);
            flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
                @Override
                public void removeFirstObjectInAdapter() {
                    // this is the simplest way to delete an object from the Adapter (/AdapterView)
                    Log.d("LIST", "removed object!");
                    modelList.remove(0);
                    adapter.notifyDataSetChanged();
                    if (modelList.size() == 0) {
                        textView.setText("Empty");
                    }
                }

                @Override
                public void onLeftCardExit(Object dataObject) {
                    //Do something on the left!
                    //You also have access to the original object.
                    //If you want to use it just cast it (String) dataObject
                    try {
                        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");
                        SwipeCardsModel model = (SwipeCardsModel) dataObject;
                        String userId = model.getId();
                        userRef.child(userId).child("connections").child("dislikes").child(user.getUid()).setValue(true);
                        //userRef.child(oppositeUserSex).child(userId).child("connections").child("dislikes").child(user.getUid()).setValue(true);
                        Toast.makeText(getActivity(), "Disliked!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Error Loading...", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onRightCardExit(Object dataObject) {
                    try {
                        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");
                        SwipeCardsModel model = (SwipeCardsModel) dataObject;
                        String userId = model.getId();
                        userRef.child(userId).child("connections").child("likes").child(user.getUid()).setValue(true);
                        userRef.child(user.getUid()).child("connections").child("sentLikes").child(userId).setValue(true);
//                    userRef.child(oppositeUserSex).child(userId).child("connections").child("likes").child(user.getUid()).setValue(true);
//                    userRef.child(userSex).child(user.getUid()).child("connections").child("sentLikes").child(userId).setValue(true);
                        isConnectionMatch(userId);
                        Toast.makeText(getActivity(), "Liked!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Error Loading...", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onAdapterAboutToEmpty(int itemsInAdapter) {
                    // Ask for more data here
//                al.add("Description ".concat(String.valueOf(i)));
//                arrayAdapter.notifyDataSetChanged();
//                Log.d("LIST", "notified");
//                i++;
                    //textView.setText("Empty");
                }

                @Override
                public void onScroll(float scrollProgressPercent) {
//                View view = flingContainer.getSelectedView();
//                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
//                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
                }
            });


            // Optionally add an OnItemClickListener
            flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
                @Override
                public void onItemClicked(int itemPosition, Object dataObject) {
                    if(dataObject!=null) {
                        Toast.makeText(getActivity(), "Opening Profile", Toast.LENGTH_SHORT).show();
                        SwipeCardsModel model = (SwipeCardsModel) dataObject;
                        String userId = model.getId();
                        String userDistance = model.getDistance();
                        BaseHelper.userIdProfileOthers = userId;
                        BaseHelper.userDistance = userDistance;
                        Intent intent = new Intent(getContext(), ProfileDetail.class);
                        ((MainHome)getContext()).startActivity(intent);
                        Animatoo.animateSlideUp(getContext());
                    }
                }
            });
            //BaseHelper.Zapping=true;
        }
        return view;
    }

    static void makeToast(Context ctx, String s) {
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }

    private void isConnectionMatch(String userId) {
        final DatabaseReference usersDb=FirebaseDatabase.getInstance().getReference("users");
        DatabaseReference currentUserConnectionsDb = usersDb.child(user.getUid()).child("connections").child("likes").child(userId);
        //DatabaseReference currentUserConnectionsDb = usersDb.child(userSex).child(user.getUid()).child("connections").child("likes").child(userId);
        currentUserConnectionsDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText(getContext(), "New Connection", Toast.LENGTH_LONG).show();

                    //String key = FirebaseDatabase.getInstance().getReference().child("Chat").push().getKey();
                    usersDb.child(dataSnapshot.getKey()).child("connections").child("matches").child(user.getUid()).setValue(true);
                    usersDb.child(user.getUid()).child("connections").child("matches").child(dataSnapshot.getKey()).setValue(true);
//                    usersDb.child(oppositeUserSex).child(dataSnapshot.getKey()).child("connections").child("matches").child(user.getUid()).setValue(true);
//                    usersDb.child(userSex).child(user.getUid()).child("connections").child("matches").child(dataSnapshot.getKey()).setValue(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    public void GetUserFilters()
    {
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("filters");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        FilterModel model = dataSnapshot.getValue(FilterModel.class);
                        if (TextUtils.equals(model.getId(),user.getUid())) {
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
                            getOppositeSexUsers();
                            break;
                        }
                    }

                } catch (Exception e) {
                    Toast.makeText(getContext(), "Error loading...", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void getUserProfile(final String id){
        try {
            DatabaseReference OppositeuserDb = FirebaseDatabase.getInstance().getReference().child("profiles");
            OppositeuserDb.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    try {
                        UserProfileModel model = dataSnapshot.getValue(UserProfileModel.class);
                        getOriginalValues();
                        int ageLow = Integer.parseInt(ageText);
                        int ageHigh = Integer.parseInt(ageMaxText);
                        int userAge;
                        if (dataSnapshot.exists() && TextUtils.equals(model.getUser_id(), id)) {
                            String[] arr = model.getDate_of_birth().split("/");
                            int day = Integer.parseInt(arr[0]);
                            int month = Integer.parseInt(arr[1]);
                            int year = Integer.parseInt(arr[2]);
                            String age = getAge(year, month, day);
                            userAge = Integer.parseInt(age);
                            locationLat=model.getLocation_lat();
                            locationLong=model.getLocation_long();
                            float distance=getDistance(locationLat,locationLong,BaseHelper.locationLat,BaseHelper.locationLong);
                            float Distance=(distance/1000);
                            String userDistance=String.valueOf(String.format("%.1f",Distance))+" Km";
                            if (TextUtils.equals(disability, "Disability doesn't matter")) {
                                disability = model.getSelected_disability();
                            }
                            if (TextUtils.equals(distanceText, "Distance doesn't matter")||TextUtils.equals(distanceText, "La distancia no importa")||TextUtils.equals(distanceText, "La distance n'a pas d'importance")) {
                                compareDistance = 1000000;
                            }
                            if (TextUtils.equals(distanceText, "Distance up to 10 km")||TextUtils.equals(distanceText, "Distancia hasta 10 km")||TextUtils.equals(distanceText, "Distance jusqu'à 10 km")) {
                                compareDistance = 10;
                            }
                            if (TextUtils.equals(distanceText, "Distance up to 50 km")||TextUtils.equals(distanceText, "Distancia hasta 50 km")||TextUtils.equals(distanceText, "Distance jusqu'à 50 km")) {
                                compareDistance = 50;
                            }
                            if (TextUtils.equals(distanceText, "Distance up to 100 km")||TextUtils.equals(distanceText, "Distancia hasta 100 km")||TextUtils.equals(distanceText, "Distance jusqu'à 100 km")) {
                                compareDistance = 100;
                            }
                            if (TextUtils.equals(distanceText, "Distance up to 500 km")||TextUtils.equals(distanceText, "Distancia hasta 500 km")||TextUtils.equals(distanceText, "Distance jusqu'à 500 km")) {
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
                                    && Distance<=compareDistance
                                    && TextUtils.equals(disability, model.getSelected_disability())
                                    && TextUtils.equals(professionText, model.getProfession())
                                    && TextUtils.equals(relationshipStatusText, model.getRelationship_status())
//                                    && TextUtils.equals(favouriteSportsText, model.getFavourite_sports())
                                    && TextUtils.equals(educationText, model.getEducation())
                                    && TextUtils.equals(smokerText, model.getSmoker())
                                    && TextUtils.equals(religionText, model.getReligion())
//                                    && TextUtils.equals(bodyTypeText, model.getBody_type())
                                    && TextUtils.equals(drinkingText, model.getDrinking())
                                    && TextUtils.equals(exerciseText, model.getExercise())
                                    && TextUtils.equals(tattoosText, model.getTattoos())
                                    && TextUtils.equals(petsText, model.getPets())
                                    && TextUtils.equals(dietText, model.getDiet())
                                    && TextUtils.equals(childrenText, model.getChildren())) {
                                String id = model.getUser_id();
                                String name = model.getUser_name();
                                String description = model.getDescription();
                                modelList.add(new SwipeCardsModel(id, name, description,userDistance));
                                adapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                textView.setText("");
                            } else {
                                if(modelList.size()==0) {
                                    progressBar.setVisibility(View.GONE);
                                    textView.setText(R.string.notFound);
                                }
                            }
                        }
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        //textView.setText("Loading...");
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
    public void getOppositeSexUsers() {
        try {
            DatabaseReference OppositeuserDb = FirebaseDatabase.getInstance().getReference().child("users");
            OppositeuserDb.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    try {
                        if (dataSnapshot.exists() && !dataSnapshot.child("user_id").getValue().equals(user.getUid())
                                && !dataSnapshot.child("connections").child("dislikes").hasChild(user.getUid())
                                && !dataSnapshot.child("connections").child("likes").hasChild(user.getUid())
                                && !dataSnapshot.child("blocks").child("block_by_others").hasChild(user.getUid())
                                && !dataSnapshot.child("blocks").child("block_by_me").hasChild(user.getUid())){
                            String id = dataSnapshot.child("user_id").getValue().toString();
                            getUserProfile(id);
                            //Toast.makeText(getContext(), "Getting data from profile...", Toast.LENGTH_SHORT).show();
//                            String id = dataSnapshot.child("user_id").getValue().toString();
//                            String name = dataSnapshot.child("username").getValue().toString();
//                            String description = dataSnapshot.child("description").getValue().toString();
//                            //al.add(description);
//                            //al.add("hello");
//                            modelList.add(new SwipeCardsModel(id, name, description));
//                            adapter.notifyDataSetChanged();
//                            progressBar.setVisibility(View.GONE);
//                            textView.setText("");
                        } else {
//                            if (modelList.size() == 0) {
//                                progressBar.setVisibility(View.GONE);
//                                textView.setText("Empty");
//                            }
                        }
                    }catch (Exception e)
                    {
                        //Toast.makeText(getContext(), "Error Loading....", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        textView.setText("Error Loading...");
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
//    public void SaveGenders(String userGender,String oppositeGender)
//    {
//        SharedPreferences genders = PreferenceManager.getDefaultSharedPreferences(getContext());
//        SharedPreferences.Editor editor = genders.edit();
//        editor.putString("userGender",userGender);
//        editor.putString("oppositeGender",oppositeGender);
//        editor.commit();
//    }
}
