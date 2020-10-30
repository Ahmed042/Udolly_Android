package com.example.datingpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.datingpro.Helper.BaseHelper;
import com.ortiz.touchview.TouchImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class FullPhoto extends AppCompatActivity {
    TouchImageView Main;
    ImageView back;
    ProgressBar imageProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_photo);
        back=(ImageView) findViewById(R.id.imageBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animatoo.animateSlideDown(FullPhoto.this);
                finish();
            }
        });
        Main=(TouchImageView) findViewById(R.id.mainImage);
        imageProgress=(ProgressBar)findViewById(R.id.imageLoading);
        Picasso.get().load(BaseHelper.fullPhoto).into(Main, new Callback() {
            @Override
            public void onSuccess() {
                imageProgress.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(getApplicationContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(FullPhoto.this);
    }
}