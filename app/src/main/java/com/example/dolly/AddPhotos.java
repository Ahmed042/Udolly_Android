package com.example.dolly;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.Helper.BaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AddPhotos extends AppCompatActivity {
    CardView card1,card2,card3,card4,card5;
    ImageView image1,image2,image3,image4,image5;
    ProgressBar progress1,progress2,progress3,progress4,progress5;
    byte[] bytes;
    double mUploadProgress;
    String pictureName,firstImage,secondImage,thirdImage,fourthImage,fifthImage;
    int checkPicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photos);
        progress1=(ProgressBar)findViewById(R.id.progressBarPictureOne);
        progress2=(ProgressBar)findViewById(R.id.progressBarPictureTwo);
        progress3=(ProgressBar)findViewById(R.id.progressBarPictureThree);
        progress4=(ProgressBar)findViewById(R.id.progressBarPictureFour);
        progress5=(ProgressBar)findViewById(R.id.progressBarPictureFive);
        image1=(ImageView)findViewById(R.id.profilePicture1);
        image2=(ImageView)findViewById(R.id.profilePicture2);
        image3=(ImageView)findViewById(R.id.profilePicture3);
        image4=(ImageView)findViewById(R.id.profilePicture4);
        image5=(ImageView)findViewById(R.id.profilePicture5);
        card1=(CardView)findViewById(R.id.ProfilePicOne);
        card2=(CardView)findViewById(R.id.ProfilePicTwo);
        card3=(CardView)findViewById(R.id.ProfilePicThree);
        card4=(CardView)findViewById(R.id.ProfilePicFour);
        card5=(CardView)findViewById(R.id.ProfilePicFive);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pictureName="picture_one";
                checkPicture=1;
                CropImage.activity().start(AddPhotos.this);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pictureName="picture_two";
                checkPicture=2;
                CropImage.activity().start(AddPhotos.this);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pictureName="picture_three";
                checkPicture=3;
                CropImage.activity().start(AddPhotos.this);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pictureName="picture_four";
                checkPicture=4;
                CropImage.activity().start(AddPhotos.this);
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pictureName="picture_five";
                checkPicture=5;
                CropImage.activity().start(AddPhotos.this);
            }
        });
        GetPictures();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri cropImage=result.getUri();
                if (cropImage != null) {
                    try {
                        if(checkPicture==1) {
                            new Handler().post(new Runnable() {
                                @Override
                                public void run() {
                                    progress1.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                        else if(checkPicture==2)
                        {
                            new Handler().post(new Runnable() {
                                @Override
                                public void run() {
                                    progress2.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                        else if(checkPicture==3)
                        {
                            new Handler().post(new Runnable() {
                                @Override
                                public void run() {
                                    progress3.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                        else if(checkPicture==4)
                        {
                            new Handler().post(new Runnable() {
                                @Override
                                public void run() {
                                    progress4.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                        else if(checkPicture==5)
                        {
                            new Handler().post(new Runnable() {
                                @Override
                                public void run() {
                                    progress5.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                        InputStream inputStream = getContentResolver().openInputStream(cropImage);
                        BitmapFactory.Options options=new BitmapFactory.Options();
                        options.inSampleSize=2;
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        bytes = getBytesFromBitmap(bitmap, 50);
                        BaseHelper.profileImageBytes = bytes;
                        UploadPhoto(bytes,bitmap);
                        //if yu want to upload image file to server
                        //File selectedImageFile = new File(getPathFomUri(selectedImageUri));
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
    public void UploadPhoto(byte[] UploadBytes, final Bitmap bitmap)
    {
        try {
            StorageReference pictureRef = FirebaseStorage.getInstance().getReference().child("pictures/users/" + "/" + BaseHelper.UserID + "/" + pictureName);
            UploadTask uploadTask = null;
            uploadTask = pictureRef.putBytes(UploadBytes);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    if (taskSnapshot.getMetadata() != null) {
                        if (taskSnapshot.getMetadata().getReference() != null) {
                            Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                            result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Toast.makeText(getApplicationContext(), "Photo Uploaded", Toast.LENGTH_SHORT).show();
                                    String pictureUrl = uri.toString();
                                    FirebaseDatabase.getInstance().getReference().child("pictures").child(BaseHelper.UserID).child(pictureName).setValue(pictureUrl);
                                    if(checkPicture==1) {
                                        new Handler().post(new Runnable() {
                                            @Override
                                            public void run() {
                                                progress1.setVisibility(View.GONE);
                                            }
                                        });
                                    }
                                    else if(checkPicture==2)
                                    {
                                        new Handler().post(new Runnable() {
                                            @Override
                                            public void run() {
                                                progress2.setVisibility(View.GONE);
                                            }
                                        });
                                    }
                                    else if(checkPicture==3)
                                    {
                                        new Handler().post(new Runnable() {
                                            @Override
                                            public void run() {
                                                progress3.setVisibility(View.GONE);
                                            }
                                        });
                                    }
                                    else if(checkPicture==4)
                                    {
                                        new Handler().post(new Runnable() {
                                            @Override
                                            public void run() {
                                                progress4.setVisibility(View.GONE);
                                            }
                                        });
                                    }
                                    else if(checkPicture==5)
                                    {
                                        new Handler().post(new Runnable() {
                                            @Override
                                            public void run() {
                                                progress5.setVisibility(View.GONE);
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                    if (progress - 5 > mUploadProgress) {
                        Toast.makeText(getApplicationContext(),"Upload Progress: "+progress ,Toast.LENGTH_SHORT).show();
                        mUploadProgress = progress;
                    }
                }
            }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(AddPhotos.this, "Can't be able to upload picture",
                    Toast.LENGTH_SHORT).show();
        }
        progress1.setVisibility(View.GONE);
        progress2.setVisibility(View.GONE);
        progress3.setVisibility(View.GONE);
        progress4.setVisibility(View.GONE);
        progress5.setVisibility(View.GONE);
    }
    public byte[] getBytesFromBitmap(Bitmap bitmap, int quality)
    {
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,quality,stream);
        return stream.toByteArray();
    }
    public void clickItem(MenuItem menuItem)
    {
        Animatoo.animateSlideDown(this);
        finish();
    }
        public void GetPictures() {
        try {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("pictures").child(BaseHelper.UserID);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        try {
                            if (snapshot.hasChild("picture_one")) {
                               firstImage = snapshot.child("picture_one").getValue().toString();
                                Picasso.get().load(firstImage).resize(500, 500).centerCrop().into(image1, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        progress1.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        Toast.makeText(getApplicationContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                progress1.setVisibility(View.GONE);

                            }
                            if (snapshot.hasChild("picture_two")) {
                               secondImage = snapshot.child("picture_two").getValue().toString();
                                Picasso.get().load(secondImage).resize(500, 500).centerCrop().into(image2, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        progress2.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        Toast.makeText(getApplicationContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                progress2.setVisibility(View.GONE);
                            }
                            if (snapshot.hasChild("picture_three")) {
                               thirdImage = snapshot.child("picture_three").getValue().toString();
                                Picasso.get().load(thirdImage).resize(500, 500).centerCrop().into(image3, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        progress3.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        Toast.makeText(getApplicationContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                progress3.setVisibility(View.GONE);
                            }
                            if (snapshot.hasChild("picture_four")) {
                               fourthImage = snapshot.child("picture_four").getValue().toString();
                                Picasso.get().load(fourthImage).resize(500, 500).centerCrop().into(image4, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        progress4.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        Toast.makeText(getApplicationContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                progress4.setVisibility(View.GONE);
                            }
                            if (snapshot.hasChild("picture_five")) {
                               fifthImage= snapshot.child("picture_five").getValue().toString();
                                Picasso.get().load(fifthImage).resize(500, 500).centerCrop().into(image5, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        progress5.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        Toast.makeText(getApplicationContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                progress5.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error Loading...", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        progress1.setVisibility(View.GONE);
                        progress2.setVisibility(View.GONE);
                        progress3.setVisibility(View.GONE);
                        progress4.setVisibility(View.GONE);
                        progress5.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error Loading...", Toast.LENGTH_SHORT).show();
        }


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(this);
    }
}