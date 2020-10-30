package com.example.datingpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.datingpro.Adapter.ChatAdapter;
import com.example.datingpro.Adapter.MessageAdapter;
import com.example.datingpro.Helper.BaseHelper;
import com.example.datingpro.Model.MessageModel;
import com.example.datingpro.Model.chatModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Chat extends AppCompatActivity {
    RecyclerView recyclerView;
    ChatAdapter adapter;
    boolean check = false;
    ProgressBar progressBar;
    TextView chatUsername, sendMessage;
    EditText messageText;
    public ArrayList<chatModel> modelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progressMessage);
        chatUsername = (TextView) findViewById(R.id.userNameChat);
        chatUsername.setText(BaseHelper.chatUserName);
        messageText = (EditText) findViewById(R.id.add_message);
        messageText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });
        messageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.scrollToPosition(modelList.size()-1);
            }
        });
        sendMessage = (TextView) findViewById(R.id.snd_message);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = messageText.getText().toString();
                if (TextUtils.isEmpty(msg)) {
                    Toast.makeText(getApplicationContext(), "Please write message to send", Toast.LENGTH_SHORT).show();
                } else {
                    SendMessage(msg);
                    messageText.setText("");
                }
            }
        });
        dummyData();
    }

    public void dummyData() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("chats").child(BaseHelper.chatId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                modelList.clear();
                try {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        if (dataSnapshot.exists()) {
                            chatModel model = dataSnapshot.getValue(chatModel.class);
                            String id = model.getId();
                            String message = model.getMessage();
                            String from = model.getUserSendId();
                            modelList.add(new chatModel(id, from, message));
                        }
                    }
                    adapter = new ChatAdapter(getApplicationContext(), modelList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(false);
                    progressBar.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(modelList.size()-1);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error Loading Chat...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void SendMessage(String msg) {
        try {
            String randomKey = FirebaseDatabase.getInstance().getReference().child("chats").push().getKey();
            chatModel chat = new chatModel(randomKey, BaseHelper.UserID, msg);
            FirebaseDatabase.getInstance().getReference().child("chats").child(BaseHelper.chatId).child(randomKey).setValue(chat);
//            Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_SHORT).show();
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(messageText.getWindowToken(), 0);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error Sending Message...", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(Chat.this);
    }
}