package com.example.dolly.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.BecomeVip;
import com.example.dolly.Filter;
import com.example.dolly.Helper.BaseHelper;
import com.example.dolly.MainHome;
import com.example.dolly.Model.FireBaseModels.UserProfileModel;
import com.example.dolly.ProfileEdit;
import com.example.dolly.R;
import com.example.dolly.SentLikes;
import com.example.dolly.Settings;
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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {
    View view;
ImageView profileImage;
LinearLayout openProfile,editImageLinear;
RelativeLayout filter,settings,sentLikes;
ProgressBar imageProgress,progressBar;
Button vip;
double mUploadProgress;
    byte[] bytes;
Uri uri;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(BaseHelper.Profile==false) {
            view = inflater.inflate(R.layout.profile_fragment, container, false);
            imageProgress = (ProgressBar) view.findViewById(R.id.imageLoading);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);
            filter = (RelativeLayout) view.findViewById(R.id.relativeFilter);
            filter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), Filter.class);
                    ((MainHome) getActivity()).startActivity(intent);
                    Animatoo.animateSlideUp(getContext());
                }
            });
            settings = (RelativeLayout) view.findViewById(R.id.relativeSettings);
            settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), Settings.class);
                    ((MainHome) getActivity()).startActivity(intent);
                    Animatoo.animateSlideUp(getContext());
                }
            });
            sentLikes = (RelativeLayout) view.findViewById(R.id.relativeSentLikes);
            sentLikes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), SentLikes.class);
                    ((MainHome) getActivity()).startActivity(intent);
                    Animatoo.animateSlideUp(getContext());
                }
            });
            profileImage = (ImageView) view.findViewById(R.id.profileImage);
            profileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ProfileEdit.class);
                    ((MainHome) getActivity()).startActivity(intent);
                    Animatoo.animateSlideUp(getContext());
                }
            });
            openProfile=(LinearLayout)view.findViewById(R.id.openProfileLinear);
            openProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ProfileEdit.class);
                    ((MainHome) getActivity()).startActivity(intent);
                    Animatoo.animateSlideUp(getContext());
                }
            });
            vip=(Button) view.findViewById(R.id.btnVIP);
            if(BaseHelper.isVip==true)
            {
                vip.setVisibility(View.GONE);
            }
            else
            {
                vip.setVisibility(View.VISIBLE);
            }
            vip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), BecomeVip.class);
                    ((MainHome) getActivity()).startActivity(intent);
                    Animatoo.animateSlideUp(getContext());
                }
            });
            editImageLinear=(LinearLayout)view.findViewById(R.id.editImageLinear);
            editImageLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CropImage.activity().start(getActivity());
                    //progressBar.setVisibility(View.VISIBLE);
                }
            });
            GetUserProfile();
            //BaseHelper.Profile=true;
        }
            return view;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                });
                Uri cropImage=result.getUri();
                if (cropImage != null) {
                    try {

                        Toast.makeText(getContext(),"Uploading photo...", Toast.LENGTH_SHORT).show();
                        InputStream inputStream = getActivity().getContentResolver().openInputStream(cropImage);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        //Bitmap.createScaledBitmap(bitmap,250,300,false);

                        bytes = getBytesFromBitmap(bitmap, 50);
                        BaseHelper.profileImageBytes = bytes;
                        UploadPhoto(bytes,bitmap);
                        //if yu want to upload image file to server
                        //File selectedImageFile = new File(getPathFomUri(selectedImageUri));
                    } catch (Exception ex) {
                        Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            }
        }
        else {
            Toast.makeText(getContext(),"Permission denied", Toast.LENGTH_SHORT).show();

        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    public void UploadPhoto(byte[] UploadBytes, final Bitmap bitmap)
    {

        try {
            final StorageReference pictureRef = FirebaseStorage.getInstance().getReference().child("pictures/users/" + "/" + BaseHelper.UserID + "/" + "profile_picture");
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
                                    Toast.makeText(getContext(), "Profile Picture Successfully Updated", Toast.LENGTH_SHORT).show();
                                    String pictureUrl = uri.toString();
                                    FirebaseDatabase.getInstance().getReference("users").child(BaseHelper.UserID).child("image").setValue(pictureUrl);
                                    FirebaseDatabase.getInstance().getReference("profiles").child(BaseHelper.UserID).child("image").setValue(pictureUrl);
                                    profileImage.setImageBitmap(bitmap);
                                }
                            });
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                    if (progress - 5 > mUploadProgress) {
                        Toast.makeText(getContext(),"Upload Progress: "+progress ,Toast.LENGTH_SHORT).show();
                        mUploadProgress = progress;
                    }
                    //CameraFragment.progressBarVideo.setProgress((int)progress);
                }
            }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), "Can't be able to upload photo",
                    Toast.LENGTH_SHORT).show();
        }

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public byte[] getBytesFromBitmap(Bitmap bitmap,int quality)
    {
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,quality,stream);
        return stream.toByteArray();
    }
    public void GetUserProfile()
    {

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("profiles");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        UserProfileModel model = dataSnapshot.getValue(UserProfileModel.class);
                        if (TextUtils.equals(model.getUser_id(), BaseHelper.UserID)) {

                            Picasso.get().load(model.getImage()).into(profileImage, new Callback() {
                                @Override
                                public void onSuccess() {
                                    imageProgress.setVisibility(View.GONE);
                                }

                                @Override
                                public void onError(Exception e) {
                                    Toast.makeText(getContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Error Loading Profile...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
