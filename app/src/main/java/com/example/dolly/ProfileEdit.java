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
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.Helper.BaseHelper;
import com.example.dolly.Model.FireBaseModels.UserProfileModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Calendar;

public class ProfileEdit extends AppCompatActivity {
RelativeLayout userNameRel,Logout,homeTown,profession,disabilityRel,lookingFor,relationshipStatus,bodyHeight,education,smoker,religion,bodyType,languageSpeak,
    drinking,exercise,tattoos,pets,music,diet,children,description,favouriteSports;
   TextView homeTownText,professionText,lookingForText,relationshipStatusText,bodyHeightText,educationText,smokerText,religionText,bodyTypeText,languageSpeakText,
            drinkingText,exerciseText,tattoosText,petsText,musicText,dietText,childrenText,descriptionText,ageText,favouriteSportsText;
    private FirebaseAuth auth;
    //ImageView profileImage;
    TextView name,address,disability,completeProfile;
    ProgressBar imageProgress,profileProgress;
    String firstDisability,secondDisability,dob,age,pictureName,firstImage,secondImage,thirdImage,fourthImage,fifthImage,profileImage;
    int checkPicture;
    byte[] bytes;
    String[] sample;
    double mUploadProgress;
    Button addPics;
    CarouselView carouselView;
    CardView card1,card2,card3,card4,card5;
    ImageView image1,image2,image3,image4,image5;
    ProgressBar progress1,progress2,progress3,progress4,progress5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        imageProgress=(ProgressBar)findViewById(R.id.imageLoading);
        profileProgress=(ProgressBar)findViewById(R.id.progressBar2);
        completeProfile=(TextView)findViewById(R.id.completeProfileText);
        auth = FirebaseAuth.getInstance();
        addPics=(Button)findViewById(R.id.btnPics);
        addPics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddPhotos.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
            }
        });
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
                CropImage.activity().start(ProfileEdit.this);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pictureName="picture_two";
                checkPicture=2;
                CropImage.activity().start(ProfileEdit.this);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pictureName="picture_three";
                checkPicture=3;
                CropImage.activity().start(ProfileEdit.this);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pictureName="picture_four";
                checkPicture=4;
                CropImage.activity().start(ProfileEdit.this);
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pictureName="picture_five";
                checkPicture=5;
                CropImage.activity().start(ProfileEdit.this);
            }
        });
        userNameRel=(RelativeLayout)findViewById(R.id.relativeUserName);
        userNameRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UpdateName.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        homeTown=(RelativeLayout)findViewById(R.id.relativeHomeTown);
        homeTown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeTown.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        profession=(RelativeLayout)findViewById(R.id.relativeProfession);
       profession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Profession.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        lookingFor=(RelativeLayout)findViewById(R.id.relativeLooking);
        lookingFor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LookingFor.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        relationshipStatus=(RelativeLayout)findViewById(R.id.relativeRelationship);
        relationshipStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RelationshipStatus.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        bodyHeight=(RelativeLayout)findViewById(R.id.relativeBodyHeight);
        bodyHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Height.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        favouriteSports=(RelativeLayout)findViewById(R.id.relativeFavouriteSports);
        favouriteSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FavouriteSports.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        education=(RelativeLayout)findViewById(R.id.relativeEducation);
        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Education.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        smoker=(RelativeLayout)findViewById(R.id.relativeSmoker);
        smoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Smoke.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        disabilityRel=(RelativeLayout)findViewById(R.id.relativeDisability);
        disabilityRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UpdateDisability.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        religion=(RelativeLayout)findViewById(R.id.relativeReligion);
        religion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Religion.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        bodyType=(RelativeLayout)findViewById(R.id.relativeBodyType);
        bodyType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BodyType.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        languageSpeak=(RelativeLayout)findViewById(R.id.relativeLanguage);
        languageSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LanguagesSpeak.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        drinking=(RelativeLayout)findViewById(R.id.relativeDrinking);
        drinking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Drinking.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        exercise=(RelativeLayout)findViewById(R.id.relativeExercise);
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Exercise.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        tattoos=(RelativeLayout)findViewById(R.id.relativeTattoos);
        tattoos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Tattoos.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        pets=(RelativeLayout)findViewById(R.id.relativePets);
        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Pets.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        music=(RelativeLayout)findViewById(R.id.relativeMusic);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Music.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        diet=(RelativeLayout)findViewById(R.id.relativeDiet);
        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Diet.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        children=(RelativeLayout)findViewById(R.id.relativeChildren);
        children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Kids.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        description=(RelativeLayout)findViewById(R.id.relativeDescription);
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UpdateDescription.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileEdit.this);
                //finish();
            }
        });
        Logout=(RelativeLayout)findViewById(R.id.logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileEdit.this, auth.getCurrentUser().getEmail()+" Logged Out!", Toast.LENGTH_SHORT).show();
                auth.signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Animatoo.animateSlideDown(ProfileEdit.this);
                finish();
            }
        });
//        profileImage=(ImageView)findViewById(R.id.profileEditImage);
//        profileImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), ImageShow.class);
//                startActivity(intent);
//                Animatoo.animateSlideUp(ProfileEdit.this);
//            }
//        });
        name=(TextView)findViewById(R.id.profileEditName);
        ageText=(TextView)findViewById(R.id.profileEditAge);
        address=(TextView)findViewById(R.id.profileEditAddress);
        disability=(TextView)findViewById(R.id.profileEditDisability);
        homeTownText=(TextView)findViewById(R.id.homeTownText);
        professionText=(TextView)findViewById(R.id.professionText);
        lookingForText=(TextView)findViewById(R.id.lookingForText);
        relationshipStatusText=(TextView)findViewById(R.id.relationShipText);
        favouriteSportsText=(TextView)findViewById(R.id.favouriteSportsText);
        educationText=(TextView)findViewById(R.id.educationText);
        smokerText=(TextView)findViewById(R.id.smokerText);
        religionText=(TextView)findViewById(R.id.religionText);
        bodyTypeText=(TextView)findViewById(R.id.bodyTypeText);
        languageSpeakText=(TextView)findViewById(R.id.languageText);
        drinkingText=(TextView)findViewById(R.id.drinkingText);
        exerciseText=(TextView)findViewById(R.id.exerciseText);
        tattoosText=(TextView)findViewById(R.id.tattoosText);
        petsText=(TextView)findViewById(R.id.petText);
        musicText=(TextView)findViewById(R.id.musicText);
        dietText=(TextView)findViewById(R.id.dietText);
        childrenText=(TextView)findViewById(R.id.kidText);
        descriptionText=(TextView)findViewById(R.id.descriptionText);
        GetUserProfile();
        GetPictures();
        carouselView=(CarouselView)findViewById(R.id.carouselView);
    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.get().load(sample[position]).resize(500, 500).centerCrop().into(imageView, new Callback() {
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
    };
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
                            sample = new String[]{
                                    profileImage, firstImage
                            };
                        }
                        else {
                            progress1.setVisibility(View.GONE);
                            card1.setVisibility(View.GONE);
                            sample = new String[] {
                                    profileImage
                            };
                        }
                        if (snapshot.hasChild("picture_two")) {
                            secondImage = snapshot.child("picture_two").getValue().toString();
                            sample = new String[] {
                                    profileImage,firstImage,secondImage
                            };
//                                Picasso.get().load(secondImage).resize(500, 500).centerCrop().into(image2, new Callback() {
//                                    @Override
//                                    public void onSuccess() {
//                                        progress2.setVisibility(View.GONE);
//                                    }
//
//                                    @Override
//                                    public void onError(Exception e) {
//                                        Toast.makeText(getApplicationContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
                        } else {
                            progress2.setVisibility(View.GONE);
                            card2.setVisibility(View.GONE);
                        }
                        if (snapshot.hasChild("picture_three")) {
                            thirdImage = snapshot.child("picture_three").getValue().toString();
                            sample = new String[] {
                                    profileImage,firstImage,secondImage,thirdImage
                            };
//                                Picasso.get().load(thirdImage).resize(500, 500).centerCrop().into(image3, new Callback() {
//                                    @Override
//                                    public void onSuccess() {
//                                        progress3.setVisibility(View.GONE);
//                                    }
//
//                                    @Override
//                                    public void onError(Exception e) {
//                                        Toast.makeText(getApplicationContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
                        } else {
                            progress3.setVisibility(View.GONE);
                            card3.setVisibility(View.GONE);
                        }
                        if (snapshot.hasChild("picture_four")) {
                            fourthImage = snapshot.child("picture_four").getValue().toString();
                            sample = new String[] {
                                    profileImage,firstImage,secondImage,thirdImage,fourthImage
                            };
//                                Picasso.get().load(fourthImage).resize(500, 500).centerCrop().into(image4, new Callback() {
//                                    @Override
//                                    public void onSuccess() {
//                                        progress4.setVisibility(View.GONE);
//                                    }
//
//                                    @Override
//                                    public void onError(Exception e) {
//                                        Toast.makeText(getApplicationContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
                        } else {
                            progress4.setVisibility(View.GONE);
                            card4.setVisibility(View.GONE);
                        }
                        if (snapshot.hasChild("picture_five")) {
                            fifthImage = snapshot.child("picture_five").getValue().toString();
                            sample = new String[] {
                                    profileImage,firstImage,secondImage,thirdImage,fourthImage,fifthImage
                            };
//                                Picasso.get().load(fifthImage).resize(500, 500).centerCrop().into(image5, new Callback() {
//                                    @Override
//                                    public void onSuccess() {
//                                        progress5.setVisibility(View.GONE);
//                                    }
//
//                                    @Override
//                                    public void onError(Exception e) {
//                                        Toast.makeText(getApplicationContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
                        } else {
                            progress5.setVisibility(View.GONE);
                            card5.setVisibility(View.GONE);
                        }
                        carouselView.setImageListener(imageListener);
                        carouselView.setPageCount(sample.length);
                        carouselView.setIndicatorGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error Loading...", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        sample = new String[] {
                                profileImage
                        };
                        carouselView.setImageListener(imageListener);
                        carouselView.setPageCount(sample.length);
                        carouselView.setIndicatorGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
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
//    public void GetPictures() {
//        try {
//            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("pictures").child(BaseHelper.UserID);
//            reference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if(snapshot.exists()) {
//                        try {
//                            if (snapshot.hasChild("picture_one")) {
//                               firstImage = snapshot.child("picture_one").getValue().toString();
//                                Picasso.get().load(firstImage).resize(500, 500).centerCrop().into(image1, new Callback() {
//                                    @Override
//                                    public void onSuccess() {
//                                        progress1.setVisibility(View.GONE);
//                                    }
//
//                                    @Override
//                                    public void onError(Exception e) {
//                                        Toast.makeText(getApplicationContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            } else {
//                                progress1.setVisibility(View.GONE);
//
//                            }
//                            if (snapshot.hasChild("picture_two")) {
//                               secondImage = snapshot.child("picture_two").getValue().toString();
//                                Picasso.get().load(secondImage).resize(500, 500).centerCrop().into(image2, new Callback() {
//                                    @Override
//                                    public void onSuccess() {
//                                        progress2.setVisibility(View.GONE);
//                                    }
//
//                                    @Override
//                                    public void onError(Exception e) {
//                                        Toast.makeText(getApplicationContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            } else {
//                                progress2.setVisibility(View.GONE);
//                            }
//                            if (snapshot.hasChild("picture_three")) {
//                               thirdImage = snapshot.child("picture_three").getValue().toString();
//                                Picasso.get().load(thirdImage).resize(500, 500).centerCrop().into(image3, new Callback() {
//                                    @Override
//                                    public void onSuccess() {
//                                        progress3.setVisibility(View.GONE);
//                                    }
//
//                                    @Override
//                                    public void onError(Exception e) {
//                                        Toast.makeText(getApplicationContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            } else {
//                                progress3.setVisibility(View.GONE);
//                            }
//                            if (snapshot.hasChild("picture_four")) {
//                               fourthImage = snapshot.child("picture_four").getValue().toString();
//                                Picasso.get().load(fourthImage).resize(500, 500).centerCrop().into(image4, new Callback() {
//                                    @Override
//                                    public void onSuccess() {
//                                        progress4.setVisibility(View.GONE);
//                                    }
//
//                                    @Override
//                                    public void onError(Exception e) {
//                                        Toast.makeText(getApplicationContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            } else {
//                                progress4.setVisibility(View.GONE);
//                            }
//                            if (snapshot.hasChild("picture_five")) {
//                               fifthImage= snapshot.child("picture_five").getValue().toString();
//                                Picasso.get().load(fifthImage).resize(500, 500).centerCrop().into(image5, new Callback() {
//                                    @Override
//                                    public void onSuccess() {
//                                        progress5.setVisibility(View.GONE);
//                                    }
//
//                                    @Override
//                                    public void onError(Exception e) {
//                                        Toast.makeText(getApplicationContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            } else {
//                                progress5.setVisibility(View.GONE);
//                            }
//                        } catch (Exception e) {
//                            Toast.makeText(getApplicationContext(), "Error Loading...", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    else {
//                        progress1.setVisibility(View.GONE);
//                        progress2.setVisibility(View.GONE);
//                        progress3.setVisibility(View.GONE);
//                        progress4.setVisibility(View.GONE);
//                        progress5.setVisibility(View.GONE);
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "Error Loading...", Toast.LENGTH_SHORT).show();
//        }
//
//
//    }
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
//                            Picasso.get().load(model.getImage()).resize(500,500).centerCrop().into(profile, new Callback() {
//                                @Override
//                                public void onSuccess() {
//                                    imageProgress.setVisibility(View.GONE);
//                                }
//
//                                @Override
//                                public void onError(Exception e) {
//                                    Toast.makeText(getApplicationContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show();
//                                }
//                            });
                            profileImage=model.getImage();
                            BaseHelper.fullImage=model.getImage();
                            name.setText(model.getUser_name());
                            address.setText(model.getAddress());
                            dob = model.getDate_of_birth();
                            if(TextUtils.equals(model.getProfession(),"Add")) {
                                professionText.setText(R.string.add);
                            }

                            else
                            {
                                professionText.setText(model.getProfession());
                            }
                            if(TextUtils.equals(model.getProfession(),"Add")) {
                                homeTownText.setText(R.string.add);
                            }

                            else
                            {
                                homeTownText.setText(model.getHome_town());
                            }

                            if(TextUtils.equals(model.getRelationship_status(),"Add")) {
                                relationshipStatusText.setText(R.string.add);
                            }
                            else if(TextUtils.equals(model.getRelationship_status(),"Single"))
                            {
                                relationshipStatusText.setText(R.string.single);
                            }
                            else if(TextUtils.equals(model.getRelationship_status(),"In a relationship"))
                            {
                                relationshipStatusText.setText(R.string.in_a_relationship);
                            }
                            else if(TextUtils.equals(model.getRelationship_status(),"Married"))
                            {
                                relationshipStatusText.setText(R.string.married);
                            }
                            else if(TextUtils.equals(model.getRelationship_status(),"Separated"))
                            {
                                relationshipStatusText.setText(R.string.separated);
                            }
                            else if(TextUtils.equals(model.getRelationship_status(),"Divorced"))
                            {
                                relationshipStatusText.setText(R.string.divorced);
                            }
                            else if(TextUtils.equals(model.getRelationship_status(),"Widowed"))
                            {
                                relationshipStatusText.setText(R.string.widowed);
                            }
                            else if(TextUtils.equals(model.getRelationship_status(),"In an open Relationship"))
                            {
                                relationshipStatusText.setText(R.string.in_an_open_relationship);
                            }
                            else if(TextUtils.equals(model.getRelationship_status(),"Complicated"))
                            {
                                relationshipStatusText.setText(R.string.it_s_complicated);
                            }

                            if(TextUtils.equals(model.getFavourite_sports(),"Add")) {
                                favouriteSportsText.setText(R.string.add);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Soccer"))
                            {
                                favouriteSportsText.setText(R.string.soccer);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Basket ball"))
                            {
                                favouriteSportsText.setText(R.string.basketball);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Volley ball"))
                            {
                                favouriteSportsText.setText(R.string.volleyball);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Running"))
                            {
                                favouriteSportsText.setText(R.string.running);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Swimming"))
                            {
                                favouriteSportsText.setText(R.string.swimming);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Hand Ball"))
                            {
                                favouriteSportsText.setText(R.string.hand_ball);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Tennis"))
                            {
                                favouriteSportsText.setText(R.string.tennis);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Surfing"))
                            {
                                favouriteSportsText.setText(R.string.surfing);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Paddle Tennis"))
                            {
                                favouriteSportsText.setText(R.string.paddle_tennis);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Fitness"))
                            {
                                favouriteSportsText.setText(R.string.fitness);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Combat Sports"))
                            {
                                favouriteSportsText.setText(R.string.combat_sports);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Extreme Sports"))
                            {
                                favouriteSportsText.setText(R.string.extreme_sports);
                            }
                            else if(TextUtils.equals(model.getFavourite_sports(),"Sports with animals"))
                            {
                                favouriteSportsText.setText(R.string.sports_with_animals);
                            }

                            String intentEducation=model.getEducation();
                            if(TextUtils.equals(model.getEducation(),"Add")) {
                                educationText.setText(R.string.add);
                            }
                            else if(TextUtils.equals(intentEducation,"No degree"))
                            {
                                educationText.setText(R.string.no_degree);
                            }
                            else if(TextUtils.equals(intentEducation,"Special school"))
                            {
                                educationText.setText(R.string.special_school);
                            }
                            else if(TextUtils.equals(intentEducation,"Some high school"))
                            {
                                educationText.setText(R.string.some_high_school);
                            }
                            else if(TextUtils.equals(intentEducation,"Associate degree"))
                            {
                                educationText.setText(R.string.associate_degree);
                            }
                            else if(TextUtils.equals(intentEducation,"High school graduate"))
                            {
                                educationText.setText(R.string.high_school_graduate);
                            }
                            else if(TextUtils.equals(intentEducation,"Some college studies"))
                            {
                                educationText.setText(R.string.some_college_studies);
                            }
                            else if(TextUtils.equals(intentEducation,"Current college student"))
                            {
                                educationText.setText(R.string.current_college_student);
                            }
                            else if(TextUtils.equals(intentEducation,"Bachelor's degree"))
                            {
                                educationText.setText(R.string.bachelor_s_degree);
                            }
                            else if(TextUtils.equals(intentEducation,"Master's degree"))
                            {
                                educationText.setText(R.string.master_s_degree);
                            }
                            else if(TextUtils.equals(intentEducation,"PhD,MD,Post doctorate"))
                            {
                                educationText.setText(R.string.phd_md_post_doctorate);
                            }
                            else if(TextUtils.equals(intentEducation,"Other"))
                            {
                                educationText.setText(R.string.other);
                            }

                            String intentSmoker=model.getSmoker();
                            if(TextUtils.equals(model.getSmoker(),"Add")) {
                                smokerText.setText(R.string.add);
                            }
                            else if(TextUtils.equals(intentSmoker,"No"))
                            {
                                smokerText.setText(R.string.no);
                            }
                            else if(TextUtils.equals(intentSmoker,"Yes, regularly"))
                            {
                                smokerText.setText(R.string.yes_regularly);
                            }
                            else if(TextUtils.equals(intentSmoker,"Sometimes"))
                            {
                                smokerText.setText(R.string.sometimes);
                            }
                            else if(TextUtils.equals(intentSmoker,"Only After Sex"))
                            {
                                smokerText.setText(R.string.only_after_sex);
                            }

                            String intentReligion=model.getReligion();
                            if(TextUtils.equals(model.getReligion(),"Add")) {
                                religionText.setText(R.string.add);
                            }
                            else if(TextUtils.equals(intentReligion,"Christian"))
                            {
                                religionText.setText(R.string.christian);
                            }
                            else if(TextUtils.equals(intentReligion,"Muslim"))
                            {
                                religionText.setText(R.string.muslim);
                            }
                            else if(TextUtils.equals(intentReligion,"Buddhist"))
                            {
                                religionText.setText(R.string.buddhist);
                            }
                            else if(TextUtils.equals(intentReligion,"Orthodox"))
                            {
                                religionText.setText(R.string.orthodox);
                            }
                            else if(TextUtils.equals(intentReligion,"Jewish"))
                            {
                                religionText.setText(R.string.jewish);
                            }
                            else if(TextUtils.equals(intentReligion,"Spiritual"))
                            {
                                religionText.setText(R.string.spiritual);
                            }
                            else if(TextUtils.equals(intentReligion,"Other"))
                            {
                                religionText.setText(R.string.other);
                            }
                            else if(TextUtils.equals(intentReligion,"Believing but not religious"))
                            {
                                religionText.setText(R.string.believing_but_not_religious);
                            }
                            else if(TextUtils.equals(intentReligion,"Neither believing nor religious"))
                            {
                                religionText.setText(R.string.neither_believing_nor_religious);
                            }

                            String intentDrinking=model.getDrinking();
                            if(TextUtils.equals(model.getDrinking(),"Add")) {
                                drinkingText.setText(R.string.add);
                            }
                            else if(TextUtils.equals(intentDrinking,"Frequently"))
                            {
                                drinkingText.setText(R.string.frequently);
                            }
                            else if(TextUtils.equals(intentDrinking,"Socially"))
                            {
                                drinkingText.setText(R.string.socially);
                            }
                            else if(TextUtils.equals(intentDrinking,"Never"))
                            {
                                drinkingText.setText(R.string.never);
                            }

                            String intentExercise=model.getExercise();
                            if(TextUtils.equals(model.getExercise(),"Add")) {
                                exerciseText.setText(R.string.add);
                            }
                            else if(TextUtils.equals(intentExercise,"Active"))
                            {
                                exerciseText.setText(R.string.active);
                            }
                            else if(TextUtils.equals(intentExercise,"Sometimes"))
                            {
                                exerciseText.setText(R.string.sometimes);
                            }
                            else if(TextUtils.equals(intentExercise,"Almost Never"))
                            {
                                exerciseText.setText(R.string.almost_never);
                            }

                            String intentTattoos=model.getTattoos();
                            if(TextUtils.equals(model.getTattoos(),"Add")) {
                                tattoosText.setText(R.string.add);
                            }
                            else if(TextUtils.equals(intentTattoos,"None"))
                            {
                                tattoosText.setText(R.string.none);
                            }
                            else if(TextUtils.equals(intentTattoos,"Many"))
                            {
                                tattoosText.setText(R.string.many);
                            }
                            else if(TextUtils.equals(intentTattoos,"Some"))
                            {
                                tattoosText.setText(R.string.some);
                            }
                            else if(TextUtils.equals(intentTattoos,"Only Inconspicuous"))
                            {
                                tattoosText.setText(R.string.only_inconspicuous);
                            }

                            String intentPets=model.getPets();
                            if(TextUtils.equals(model.getPets(),"Add")) {
                                petsText.setText(R.string.add);
                            }
                            else if(TextUtils.equals(intentPets,"None"))
                            {
                                petsText.setText(R.string.none);
                            }
                            else if(TextUtils.equals(intentPets,"Dog( s )"))
                            {
                                petsText.setText(R.string.dog_s);
                            }
                            else if(TextUtils.equals(intentPets,"Cat( s )"))
                            {
                                petsText.setText(R.string.cat_s);
                            }
                            else if(TextUtils.equals(intentPets,"Bird( s )"))
                            {
                                petsText.setText(R.string.bird_s);
                            }
                            else if(TextUtils.equals(intentPets,"Rabbit"))
                            {
                                petsText.setText(R.string.rabbit);
                            }
                            else if(TextUtils.equals(intentPets,"Lots"))
                            {
                                petsText.setText(R.string.lots);
                            }
                            else if(TextUtils.equals(intentPets,"Don't Want"))
                            {
                                petsText.setText(R.string.don_t_want);
                            }

                            String intentDiet=model.getDiet();
                            if(TextUtils.equals(model.getDiet(),"Add")) {
                                dietText.setText(R.string.add);
                            }
                            else if(TextUtils.equals(intentDiet,"Omnivore"))
                            {
                                dietText.setText(R.string.omnivore);
                            }
                            else if(TextUtils.equals(intentDiet,"Vegetarian"))
                            {
                                dietText.setText(R.string.vegetarian);
                            }
                            else if(TextUtils.equals(intentDiet,"Kosher"))
                            {
                                dietText.setText(R.string.kosher);
                            }
                            else if(TextUtils.equals(intentDiet,"Halal"))
                            {
                                dietText.setText(R.string.halal);
                            }
                            else if(TextUtils.equals(intentDiet,"Other"))
                            {
                                dietText.setText(R.string.other);
                            }

                            String intentKids=model.getChildren();
                            if(TextUtils.equals(model.getChildren(),"Add")) {
                                childrenText.setText(R.string.add);
                            }
                            else if(TextUtils.equals(intentKids,"Want Someday"))
                            {
                                childrenText.setText(R.string.want_someday);
                            }
                            else if(TextUtils.equals(intentKids,"Don't Want"))
                            {
                                childrenText.setText(R.string.don_t_want);
                            }
                            else if(TextUtils.equals(intentKids,"Have & want more"))
                            {
                                childrenText.setText(R.string.have_amp_want_more);
                            }
                            else if(TextUtils.equals(intentKids,"Have & don't want more"))
                            {
                                childrenText.setText(R.string.have_amp_don_t_want_more);
                            }
                            firstDisability = model.getSelected_disability();
                            //homeTownText.setText(model.getHome_town());
                            //professionText.setText(model.getProfession());
                            lookingForText.setText(model.getLooking_for());
                            relationshipStatusText.setText(model.getRelationship_status());
                            //bodyHeightText.setText(model.getBody_height());
//                            educationText.setText(model.getEducation());
//                            smokerText.setText(model.getSmoker());
//                            religionText.setText(model.getReligion());
                            bodyTypeText.setText(model.getBody_type());
                            languageSpeakText.setText(model.getLanguage());
//                            drinkingText.setText(model.getDrinking());
//                            exerciseText.setText(model.getExercise());
//                            tattoosText.setText(model.getTattoos());
//                            petsText.setText(model.getPets());
                            musicText.setText(model.getMusic());
//                            dietText.setText(model.getDiet());
//                            childrenText.setText(model.getChildren());
                            descriptionText.setText(model.getDescription());
                            secondDisability = model.getDisability();
                           // String height = model.getFa() + " cm";
                            //favouriteSportsText.setText(model.getFavourite_sports());
                            if (TextUtils.equals(secondDisability, "None")) {
                                disability.setText(firstDisability);
                            } else {
                                String text = firstDisability + " (" + secondDisability + ")";
                                disability.setText(text);
                            }
                            checkProgress();
                            try {
                                String[] arr = dob.split("/");
                                int day = Integer.parseInt(arr[0]);
                                int month = Integer.parseInt(arr[1]);
                                int year = Integer.parseInt(arr[2]);
                                age = getAge(year, month, day);
                                ageText.setText(age + " Years");
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Somethings not right...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error Loading...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
            Toast.makeText(ProfileEdit.this, "Can't be able to upload picture",
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
    //Getting image path to get image file for upload purposes

    private String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }
    public void checkProgress()
    {
        profileProgress.setProgress(60);
        if(!TextUtils.equals( homeTownText.getText().toString(),"Add"))
        {
            int prog=profileProgress.getProgress();
            profileProgress.setProgress(prog+10);
        }
        if(!TextUtils.equals(professionText.getText().toString(),"Add"))
        {
            int prog=profileProgress.getProgress();
            profileProgress.setProgress(prog+10);
        }
        if(!TextUtils.equals(lookingForText.getText().toString(),"Add"))
        {
            int prog=profileProgress.getProgress();
            profileProgress.setProgress(prog+10);
        }
        if(!TextUtils.equals(relationshipStatusText.getText().toString(),"Add"))
        {
            int prog=profileProgress.getProgress();
            profileProgress.setProgress(prog+10);
        }
        if(!TextUtils.equals( favouriteSportsText.getText().toString(),"Add"))
        {
            int prog=profileProgress.getProgress();
            profileProgress.setProgress(prog+10);
        }
        if(!TextUtils.equals(educationText.getText().toString(),"Add"))
        {
            int prog=profileProgress.getProgress();
            profileProgress.setProgress(prog+10);
        }
        if(!TextUtils.equals(smokerText.getText().toString(),"Add"))
        {
            int prog=profileProgress.getProgress();
            profileProgress.setProgress(prog+10);
        }
        if(!TextUtils.equals(religionText.getText().toString(),"Add"))
        {
            int prog=profileProgress.getProgress();
            profileProgress.setProgress(prog+10);
        }
//        if(!TextUtils.equals( bodyTypeText.getText().toString(),"Add"))
//        {
//            int prog=profileProgress.getProgress();
//            profileProgress.setProgress(prog+10);
//        }
//        if(!TextUtils.equals(languageSpeakText.getText().toString(),"Add"))
//        {
//            int prog=profileProgress.getProgress();
//            profileProgress.setProgress(prog+10);
//        }
        if(!TextUtils.equals(drinkingText.getText().toString(),"Add"))
        {
            int prog=profileProgress.getProgress();
            profileProgress.setProgress(prog+10);
        }
        if(!TextUtils.equals( exerciseText.getText().toString(),"Add"))
        {
            int prog=profileProgress.getProgress();
            profileProgress.setProgress(prog+10);
        }
        if(!TextUtils.equals(tattoosText.getText().toString(),"Add"))
        {
            int prog=profileProgress.getProgress();
            profileProgress.setProgress(prog+10);
        }
        if(!TextUtils.equals(petsText.getText().toString(),"Add"))
        {
            int prog=profileProgress.getProgress();
            profileProgress.setProgress(prog+10);
        }
        if(!TextUtils.equals( musicText.getText().toString(),"Add"))
        {
            int prog=profileProgress.getProgress();
            profileProgress.setProgress(prog+10);
        }
        if(!TextUtils.equals(dietText.getText().toString(),"Add"))
        {
            int prog=profileProgress.getProgress();
            profileProgress.setProgress(prog+10);
        }
        if(!TextUtils.equals(childrenText.getText().toString(),"Add"))
        {
            int prog=profileProgress.getProgress();
            profileProgress.setProgress(prog+10);
        }
        if(profileProgress.getProgress()==210)
        {
            completeProfile.setText("Your profile is Completed");
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(ProfileEdit.this);
    }
}

