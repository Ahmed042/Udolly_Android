package com.example.datingpro.Fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.datingpro.Adapter.MessageAdapter;
import com.example.datingpro.Adapter.NearByAdapter;
import com.example.datingpro.Helper.BaseHelper;
import com.example.datingpro.Model.FireBaseModels.UserProfileModel;
import com.example.datingpro.Model.MessageModel;
import com.example.datingpro.Model.NearByModel;
import com.example.datingpro.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MessagesFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    MessageAdapter adapter;
    boolean check=false;
    FirebaseAuth auth;
    RelativeLayout noMessage,message;
    ProgressBar progressBar;
    public ArrayList<MessageModel> modelList=new ArrayList<>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(BaseHelper.Messages==false) {
            view = inflater.inflate(R.layout.messages_fragment, container, false);
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            noMessage=(RelativeLayout)view.findViewById(R.id.noMessageRelative);
            message=(RelativeLayout)view.findViewById(R.id.messageRelative);
            progressBar=(ProgressBar)view.findViewById(R.id.progressMessage);
            auth=FirebaseAuth.getInstance();
            dummyData();
            //BaseHelper.Messages=true;
        }
        return view;
    }
    public void dummyData()
    {
//        modelList.add(new MessageModel("01","John","Hey there how r you","few seconds ago",R.drawable.john));
//        modelList.add(new MessageModel("01","Wick","WHat are you doing","9 mins ago",R.drawable.john2));
//        modelList.add(new MessageModel("01","Johnny","How are you","29 mins ago",R.drawable.john3));
//        modelList.add(new MessageModel("01","Baba Yaga","Lets go","45 mins ago",R.drawable.john2));
//        modelList.add(new MessageModel("01","Boogey Man","I don't have time","1 hour ago",R.drawable.john));
//        modelList.add(new MessageModel("01","Assassin","Assign me anything","4 hours ago",R.drawable.john3));
//        modelList.add(new MessageModel("01","Agent","Lets get it","5 hours ago",R.drawable.john3));
//        modelList.add(new MessageModel("01","Agent","Lets get it","5 hours ago",R.drawable.john2));
//        modelList.add(new MessageModel("01","Agent","Lets get it","5 hours ago",R.drawable.john));
//        modelList.add(new MessageModel("01","Agent","Lets get it","5 hours ago",R.drawable.john3));

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("chatsUsers").child(BaseHelper.UserID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            if (dataSnapshot.exists()) {
                                message.setVisibility(View.VISIBLE);
                                noMessage.setVisibility(View.GONE);
                                MessageModel model = dataSnapshot.getValue(MessageModel.class);
                                String id = model.getId();
                                String chat_id = model.getChatId();
                                String name = model.getName();
                                String message = model.getMessage();
                                String from = model.getSendFrom();
                                String image = model.getImage();
                                modelList.add(new MessageModel(id, chat_id, name, message, "", from, image));
                            }
                        }
                    } else {
                        message.setVisibility(View.GONE);
                        noMessage.setVisibility(View.VISIBLE);
                    }
                    adapter = new MessageAdapter(getContext(), modelList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(false);
                    progressBar.setVisibility(View.GONE);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Error Loading...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}
