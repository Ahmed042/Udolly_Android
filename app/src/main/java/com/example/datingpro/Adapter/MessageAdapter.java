package com.example.datingpro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.datingpro.Chat;
import com.example.datingpro.Helper.BaseHelper;
import com.example.datingpro.Model.MessageModel;
import com.example.datingpro.Model.NearByModel;
import com.example.datingpro.ProfileDetail;
import com.example.datingpro.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    private Context mContex;
    private List<MessageModel> mList;
    DataSnapshot blockSnapShot;
    boolean blockByMe,blockByOther;
    public MessageAdapter(Context mContex, List<MessageModel> mList) {
        this.mContex = mContex;
        this.mList = mList;
    }


    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContex);
        view=mInflater.inflate(R.layout.message_view_item,parent,false);
        final DatabaseReference blockUsers = FirebaseDatabase.getInstance().getReference().child("users").child(BaseHelper.UserID).child("blocks");
        blockUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    blockSnapShot = dataSnapshot;
                    //done.countDown();
                } catch (Exception e) {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MessageViewHolder holder, final int position) {
        holder.id.setText(mList.get(position).getId());
        holder.name.setText(mList.get(position).getName());
        holder.message.setText(mList.get(position).getMessage());
        holder.time.setText(mList.get(position).getTime());
        Picasso.get().load(mList.get(position).getImage()).into(holder.image, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(mContex, "Error Loading Image...", Toast.LENGTH_SHORT).show();
            }
        });
        holder.messageRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.progressBar.setVisibility(View.VISIBLE);
                checkBlockByUser(mList.get(position).getId(),mList.get(position).getChatId(),mList.get(position).getName());
                holder.progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void checkBlockUser(final String userId, final String chatId, final String UserName)
    {
        blockByOther=false;
        // done = new CountDownLatch(1);

        try {
            if (blockSnapShot.child("block_by_others").hasChild(userId)) {
                blockByOther = true;
                Toast.makeText(mContex, "You are blocked by this user\nSo you cannot chat", Toast.LENGTH_SHORT).show();
            } else {

                BaseHelper.chatId = chatId;
                BaseHelper.chatUserName = UserName;
                Intent intent = new Intent(mContex, Chat.class);
                (mContex).startActivity(intent);
                Animatoo.animateSlideUp(mContex);

            }
            //done.countDown();
        } catch (Exception e) {

        }

    }
    public void checkBlockByUser(final String userId,final String chatId, final String UserName)
    {
        blockByMe=false;
        try {
            if (blockSnapShot.child("block_by_me").hasChild(userId)) {
                blockByMe = true;
                Toast.makeText(mContex, "You have blocked this user\nUnblock this user to chat", Toast.LENGTH_SHORT).show();
            } else {
                checkBlockUser(userId,chatId,UserName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class MessageViewHolder extends RecyclerView.ViewHolder {
    TextView id, name,message,time;
    ImageView image;
    RelativeLayout messageRelative;
    ProgressBar progressBar,progress;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        id = (TextView) itemView.findViewById(R.id.messageId);
        name = (TextView) itemView.findViewById(R.id.messageName);
        time = (TextView) itemView.findViewById(R.id.messageTime);
        message = (TextView) itemView.findViewById(R.id.message);
        image = (ImageView) itemView.findViewById(R.id.messageImage);
        progressBar=(ProgressBar)itemView.findViewById(R.id.imageLoading);
        progress=(ProgressBar)itemView.findViewById(R.id.progressBar);
        messageRelative=(RelativeLayout)itemView.findViewById(R.id.messageRelative);
    }
}
