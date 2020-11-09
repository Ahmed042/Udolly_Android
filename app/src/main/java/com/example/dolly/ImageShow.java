package com.example.dolly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.Helper.BaseHelper;
import com.ortiz.touchview.TouchImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ImageShow extends AppCompatActivity {
TouchImageView Main;
ImageView back;
ProgressBar imageProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        back=(ImageView) findViewById(R.id.imageBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animatoo.animateSlideDown(ImageShow.this);
                finish();
            }
        });
        Main=(TouchImageView) findViewById(R.id.mainImage);
        imageProgress=(ProgressBar)findViewById(R.id.imageLoading);
        Picasso.get().load(BaseHelper.fullImage).into(Main, new Callback() {
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
        Animatoo.animateSlideDown(ImageShow.this);
    }
}