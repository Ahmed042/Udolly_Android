package com.example.datingpro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datingpro.Model.NearByModel;
import com.example.datingpro.Model.SwipeCardsModel;
import com.example.datingpro.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeCardsAdapter extends RecyclerView.Adapter<SwipeViewHolder>{
    private Context mContex;
    private List<SwipeCardsModel> mList;

    public SwipeCardsAdapter(Context mContex, List<SwipeCardsModel> mList) {
        this.mContex = mContex;
        this.mList = mList;
    }


    @NonNull
    @Override
    public SwipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContex);
        view=mInflater.inflate(R.layout.item,parent,false);
        return new SwipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SwipeViewHolder holder, int position) {
        holder.id.setText(mList.get(position).getId());
        holder.name.setText(mList.get(position).getName());
        holder.discription.setText(mList.get(position).getDescription());
        //String distance=mList.get(position).getDistance()+" Km Away";
        holder.distance.setText(mList.get(position).getDistance());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
class SwipeViewHolder extends RecyclerView.ViewHolder {
    TextView id, name,discription,distance;

    public SwipeViewHolder(@NonNull View itemView) {
        super(itemView);
        id = (TextView) itemView.findViewById(R.id.idSwipe);
        name = (TextView) itemView.findViewById(R.id.userNameSwipe);
        discription = (TextView) itemView.findViewById(R.id.userDescriptionSwipe);
        distance = (TextView) itemView.findViewById(R.id.userDistanceSwipe);
    }
}
