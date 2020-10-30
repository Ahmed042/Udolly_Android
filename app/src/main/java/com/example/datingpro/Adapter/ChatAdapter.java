package com.example.datingpro.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.datingpro.Chat;
import com.example.datingpro.Helper.BaseHelper;
import com.example.datingpro.Model.MessageModel;
import com.example.datingpro.Model.chatModel;
import com.example.datingpro.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder>{
    private Context mContex;
    private List<chatModel> mList;

    public ChatAdapter(Context mContex, List<chatModel> mList) {
        this.mContex = mContex;
        this.mList = mList;
    }


    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContex);
        view=mInflater.inflate(R.layout.chat_view_item,parent,false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.id.setText(mList.get(position).getId());
        holder.message.setText(mList.get(position).getMessage());
        String user=mList.get(position).getUserSendId();
        if(TextUtils.equals(user, BaseHelper.UserID))
        {
            holder.message.setBackground(mContex.getResources().getDrawable(R.drawable.roundbutton8));
            holder.message.setTextColor(Color.parseColor("#ffffff"));
            holder.linearLayout.setGravity(Gravity.END);
        }
        else
        {
            holder.message.setBackground(mContex.getResources().getDrawable(R.drawable.roundbutton7));
            holder.linearLayout.setGravity(Gravity.START);
        }
        //holder.image.setImageResource(mList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
class ChatViewHolder extends RecyclerView.ViewHolder {
    TextView id,message;
    LinearLayout linearLayout;
    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);
        id = (TextView) itemView.findViewById(R.id.chatId);
        message = (TextView) itemView.findViewById(R.id.chatText);
        linearLayout=(LinearLayout)itemView.findViewById(R.id.linearChat);
    }
}
