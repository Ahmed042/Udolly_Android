package com.example.datingpro.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.datingpro.Helper.BaseHelper;
import com.example.datingpro.Model.NearByModel;
import com.example.datingpro.ProfileDetail;
import com.example.datingpro.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NearByAdapter extends RecyclerView.Adapter<NearByViewHolder>{
    private Context mContex;
    private List<NearByModel> mList;
    ViewGroup root;
    public NearByAdapter(Context mContex, List<NearByModel> mList) {
        this.mContex = mContex;
        this.mList = mList;
    }


    @NonNull
    @Override
    public NearByViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContex);
        view=mInflater.inflate(R.layout.nearby_view_item,parent,false);
        root=parent;
        return new NearByViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NearByViewHolder holder, final int position) {
        holder.id.setText(mList.get(position).getId());
        holder.name.setText(mList.get(position).getName());
        holder.distance.setText(mList.get(position).getDistance());
        //holder.image.setImageResource(mList.get(position).getImage());

        Random random = new Random();
        int randomWidth = random.nextInt(300-240) + 240;
        int randomHeight = random.nextInt(580-400) + 400;
        URL url = null;
        Bitmap bmp;
        try {
            url = new URL(mList.get(position).getImage());
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.image.getLayoutParams().height=bmp.getHeight();
            holder.image.getLayoutParams().width=bmp.getWidth();
        } catch (Exception e) {
            //e.printStackTrace();
        }

        Picasso.get().load(mList.get(position).getImage()).resize(500, 500).centerInside().into(holder.image, new Callback() {
            @Override
            public void onSuccess() {
                    holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(root.getContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseHelper.userIdProfileOthers=mList.get(position).getId();
                BaseHelper.userDistance =mList.get(position).getDistance();
                Intent intent=new Intent(root.getContext(), ProfileDetail.class);
                (root.getContext()).startActivity(intent);
                Animatoo.animateSlideUp(root.getContext());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
class NearByViewHolder extends RecyclerView.ViewHolder {
    TextView id, name,distance;
    ImageView image;
    ProgressBar progressBar;
    CardView cardView;

    public NearByViewHolder(@NonNull View itemView) {
        super(itemView);
        id = (TextView) itemView.findViewById(R.id.nearbyId);
        name = (TextView) itemView.findViewById(R.id.nearbyName);
        distance = (TextView) itemView.findViewById(R.id.nearbyDistance);
        image = (ImageView) itemView.findViewById(R.id.nearbyImage);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        cardView=(CardView)itemView.findViewById(R.id.nearByCardView);
    }
}
