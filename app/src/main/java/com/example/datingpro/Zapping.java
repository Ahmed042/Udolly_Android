package com.example.datingpro;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.datingpro.Adapter.SwipeCardsAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.example.datingpro.Model.NearByModel;
import com.example.datingpro.Model.SwipeCardsModel;
import com.google.firebase.auth.FirebaseUser;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


public class Zapping extends AppCompatActivity {

    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    SwipeCardsAdapter adapter;
    private int i;
    SwipeFlingAdapterView flingContainer;
    public ArrayList<SwipeCardsModel> modelList=new ArrayList<>();
    private String userSex;
    private String oppositeUserSex;
    FirebaseUser user;
//    @InjectView(R.id.frame)
//    SwipeFlingAdapterView flingContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapping);
        user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("users").child("Male").child(user.getUid());
        userDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    if (dataSnapshot.child("gender").getValue() != null){
                        userSex = dataSnapshot.child("gender").getValue().toString();
                        switch (userSex){
                            case "Male":
                                oppositeUserSex = "Female";
                                break;
                            case "Female":
                                oppositeUserSex = "Male";
                                break;
                        }
                        getOppositeSexUsers();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        DatabaseReference userDbf = FirebaseDatabase.getInstance().getReference("users").child("Female").child(user.getUid());
        userDbf.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    if (dataSnapshot.child("gender").getValue() != null){
                        userSex = dataSnapshot.child("gender").getValue().toString();
                        switch (userSex){
                            case "Male":
                                oppositeUserSex = "Female";
                                break;
                            case "Female":
                                oppositeUserSex = "Male";
                                break;
                        }
                        getOppositeSexUsers();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        al = new ArrayList<>();
//        al.add("i am tall");
//        al.add("i am fat");
//        al.add("i like video game");
//        al.add("i play fifa");
//        al.add("i watch movies");
//        al.add("I love food");
//        al.add("Gamer");
//        al.add("Android developer");

        //arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, al);
        flingContainer=(SwipeFlingAdapterView)findViewById(R.id.frame);


        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject

                Toast.makeText(getApplicationContext(), "Disliked!", Toast.LENGTH_SHORT);
            }

            @Override
            public void onRightCardExit(Object dataObject) {

                Toast.makeText(getApplicationContext(), "Liked!", Toast.LENGTH_SHORT);
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                al.add("Description ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
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
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT);
            }
        });
        checkUserSex();

    }

    static void makeToast(Context ctx, String s) {
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }

    public void checkUserSex(){
//        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("Male").child(user.getUid());
//        userDb.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()){
//                    if (dataSnapshot.child("gender").getValue() != null){
//                        userSex = dataSnapshot.child("gender").getValue().toString();
//                        switch (userSex){
//                            case "Male":
//                                oppositeUserSex = "Female";
//                                break;
//                            case "Female":
//                                oppositeUserSex = "Male";
//                                break;
//                        }
//                        getOppositeSexUsers();
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        DatabaseReference userDbf = FirebaseDatabase.getInstance().getReference("users").child("Female").child(user.getUid());
//        userDbf.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()){
//                    if (dataSnapshot.child("gender").getValue() != null){
//                        userSex = dataSnapshot.child("gender").getValue().toString();
//                        switch (userSex){
//                            case "Male":
//                                oppositeUserSex = "Female";
//                                break;
//                            case "Female":
//                                oppositeUserSex = "Male";
//                                break;
//                        }
//                        getOppositeSexUsers();
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    public void getOppositeSexUsers(){
        DatabaseReference usersDb = FirebaseDatabase.getInstance().getReference("users").child(oppositeUserSex);
        usersDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.exists()) {
                    if (dataSnapshot.exists() && !dataSnapshot.child("connections").child("dislike").hasChild(user.getUid()) && !dataSnapshot.child("connections").child("like").hasChild(user.getUid()))
                        for (DataSnapshot snap : dataSnapshot.getChildren()) {
                            String id = snap.child("id").getValue().toString();
                            String name = snap.child("name").getValue().toString();
                            String description = snap.child("description").getValue().toString();
                           // modelList.add(new SwipeCardsModel(id, name, description));
                        }
                    adapter = new SwipeCardsAdapter(getApplicationContext(), modelList);
                    flingContainer.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();
                }
                else
                {

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

//    @OnClick(R.id.right)
//    public void right() {
//        /**
//         * Trigger the right event manually.
//         */
//        flingContainer.getTopCardListener().selectRight();
//    }
//
//    @OnClick(R.id.left)
//    public void left() {
//        flingContainer.getTopCardListener().selectLeft();
//



}