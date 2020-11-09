package com.example.dolly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.Helper.BaseHelper;
import com.example.dolly.Model.FireBaseModels.UserProfileModel;
import com.example.dolly.Model.MessageModel;
import com.example.dolly.Model.chatModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.Calendar;

public class ProfileDetail extends AppCompatActivity {
    ImageView profile,message,close,profileAlternate;
    TextView name,address,disability,sendMessage,age,distance,gender,homeTown,profession,lookingFor,relationShip,language,noPhotosText,description;
    BottomSheetDialog bottomSheetDialog;
    View bottomView;
    EditText messageText;
    ProgressBar imageProgress;
    String latitude,longitude;
    boolean alreadyChat=false;
    String[] sample;
    Button blockUser,reportUser,unblockUser;
    CarouselView carouselView;
    int width,height,checkPicture;
    String nameUser,imageUser,idUser,myName,myImage,chatId,dob,ageString,pictureName,firstImage,secondImage,thirdImage,fourthImage,fifthImage,profileImage;
    CardView card1,card2,card3,card4,card5;
    ImageView image1,image2,image3,image4,image5;
    ProgressBar progress1,progress2,progress3,progress4,progress5;
    GridLayout mainGrid;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);
        auth=FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        mainGrid=(GridLayout)findViewById(R.id.mainGrid);
        noPhotosText=(TextView)findViewById(R.id.noPhotosText);
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
                BaseHelper.fullPhoto=firstImage;
                Intent intent = new Intent(getApplicationContext(), FullPhoto.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileDetail.this);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseHelper.fullPhoto=secondImage;
                Intent intent = new Intent(getApplicationContext(), FullPhoto.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileDetail.this);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseHelper.fullPhoto=thirdImage;
                Intent intent = new Intent(getApplicationContext(), FullPhoto.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileDetail.this);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseHelper.fullPhoto=fourthImage;
                Intent intent = new Intent(getApplicationContext(), FullPhoto.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileDetail.this);
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseHelper.fullPhoto=fifthImage;
                Intent intent = new Intent(getApplicationContext(), FullPhoto.class);
                startActivity(intent);
                Animatoo.animateSlideUp(ProfileDetail.this);
            }
        });
        imageProgress=(ProgressBar)findViewById(R.id.imageLoading);
        //profile=(ImageView)findViewById(R.id.profileDetailImage);
        profileAlternate=(ImageView)findViewById(R.id.profileDetailImageAlternate);
//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), ImageShow.class);
//                startActivity(intent);
//                Animatoo.animateSlideUp(ProfileDetail.this);
//            }
//        });
        message=(ImageView)findViewById(R.id.messageImage);
        name=(TextView)findViewById(R.id.profileDetailName);
        address=(TextView)findViewById(R.id.profileDetailAddress);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // try {
                String uri = "http://maps.google.com/maps?daddr=" + latitude + "," + longitude + " (" + "Where the party is at" + ")";;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                    startActivity(intent);
//                }catch (Exception e){
//
//                }
            }
        });
        disability=(TextView)findViewById(R.id.profileDetailDisability);
        age=(TextView)findViewById(R.id.profileDetailAge);
        distance=(TextView)findViewById(R.id.profileDetailDistance);
        gender=(TextView)findViewById(R.id.profileDetailGender);
        description=(TextView)findViewById(R.id.profileDetailDescription);
        homeTown=(TextView)findViewById(R.id.profileDetailHomeTown);
        profession=(TextView)findViewById(R.id.profileDetailProfession);
        lookingFor=(TextView)findViewById(R.id.profileDetailLookingFor);
        relationShip=(TextView)findViewById(R.id.profileDetailRelationship);
        language=(TextView)findViewById(R.id.profileDetailLanguage);
        bottomSheetDialog=new BottomSheetDialog(ProfileDetail.this,R.style.BottomSheetDialogTheme);
        bottomView= LayoutInflater.from(ProfileDetail.this)
                .inflate(R.layout.message_bottom_sheet,
                        (LinearLayout)findViewById(R.id.bottomSheetContainer));
        bottomSheetDialog.setContentView(bottomView);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();
            }
        });
        close=(ImageView)bottomView.findViewById(R.id.closePopup);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
        messageText=(EditText)bottomView.findViewById(R.id.add_message);
        sendMessage=(TextView)bottomView.findViewById(R.id.snd_message);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=messageText.getText().toString();
                if(TextUtils.isEmpty(msg))
                {
                    Toast.makeText(ProfileDetail.this,"Please write message to send",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SendMessage(msg);
                    messageText.setText("");
                    bottomSheetDialog.dismiss();
                }
            }
        });
        blockUser=(Button)findViewById(R.id.btnBlockUser);
        blockUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ProfileDetail.this)
                        .setTitle("Block User")
                        .setMessage("Are you sure you want to block this user?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");
                                    String userId = BaseHelper.userIdProfileOthers;
                                    userRef.child(userId).child("blocks").child("block_by_others").child(user.getUid()).setValue(true);
                                    userRef.child(user.getUid()).child("blocks").child("block_by_me").child(userId).setValue(true);
                                    userRef.child(userId).child("connections").child("likes").child(user.getUid()).removeValue();
                                    userRef.child(user.getUid()).child("connections").child("sentLikes").child(userId).removeValue();
                                    userRef.child(userId).child("connections").child("matches").child(user.getUid()).removeValue();
                                    userRef.child(user.getUid()).child("connections").child("matches").child(userId).removeValue();
                                    Toast.makeText(ProfileDetail.this,"User Blocked",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainHome.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    Animatoo.animateSlideUp(ProfileDetail.this);
                                    finish();
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getApplicationContext(), "Problem with blocking", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(getResources().getDrawable(R.drawable.ic_baseline_block_24))
                        .show();

            }
        });
        unblockUser=(Button)findViewById(R.id.btnUnblock);
        unblockUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ProfileDetail.this)
                        .setTitle("Unblock User")
                        .setMessage("Are you sure you want to unblock this user?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");
                                    String userId = BaseHelper.userIdProfileOthers;
                                    userRef.child(userId).child("blocks").child("block_by_others").child(user.getUid()).removeValue();
                                    userRef.child(user.getUid()).child("blocks").child("block_by_me").child(userId).removeValue();
                                    Toast.makeText(ProfileDetail.this,"User unblocked",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainHome.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    Animatoo.animateSlideUp(ProfileDetail.this);
                                    finish();
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getApplicationContext(), "Problem with unblocking", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(getResources().getDrawable(R.drawable.ic_baseline_block_24))
                        .show();

            }
        });
        reportUser=(Button)findViewById(R.id.btnReport);
        reportUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Reporting.....", Toast.LENGTH_SHORT).show();
            }
        });
        if(BaseHelper.isBlocked==true)
        {
            reportUser.setVisibility(View.GONE);
            blockUser.setVisibility(View.GONE);
            message.setVisibility(View.GONE);
            unblockUser.setVisibility(View.VISIBLE);
        }
        else
        {
            reportUser.setVisibility(View.VISIBLE);
            blockUser.setVisibility(View.VISIBLE);
            message.setVisibility(View.VISIBLE);
            unblockUser.setVisibility(View.GONE);
        }
        GetOtherUserProfile();
        GetUserProfile();
        checkChatUsers();
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
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("pictures").child(BaseHelper.userIdProfileOthers);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        try {
                            if (snapshot.hasChild("picture_one")) {
                                firstImage = snapshot.child("picture_one").getValue().toString();
                                sample = new String[] {
                                        profileImage,firstImage
                                };
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
                            } else {
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
                        mainGrid.setVisibility(View.GONE);
                        noPhotosText.setVisibility(View.GONE);
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
    private void SendMessage(String msg) {
        String key;
        if(alreadyChat==false)
        {
        key=FirebaseDatabase.getInstance().getReference().child("chatsUsers").push().getKey();
        }
        else {
            key=chatId;
        }
        MessageModel model=new MessageModel(idUser,key,nameUser,msg,"",BaseHelper.UserID,imageUser);
        MessageModel model2=new MessageModel(BaseHelper.UserID,key,myName,msg,"",idUser,myImage);
        FirebaseDatabase.getInstance().getReference().child("chatsUsers").child(BaseHelper.UserID).child(BaseHelper.userIdProfileOthers).setValue(model);
        FirebaseDatabase.getInstance().getReference().child("chatsUsers").child(BaseHelper.userIdProfileOthers).child(BaseHelper.UserID).setValue(model2);
        String randomKey=FirebaseDatabase.getInstance().getReference().child("chats").push().getKey();
        chatModel chat=new chatModel(randomKey,BaseHelper.UserID,msg);
        FirebaseDatabase.getInstance().getReference().child("chats").child(key).child(randomKey).setValue(chat);
        Toast.makeText(getApplicationContext(),"Message sent",Toast.LENGTH_SHORT).show();
    }
    public void checkChatUsers() {
        try {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("chatsUsers").child(BaseHelper.UserID);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try {
                        if (snapshot.exists()) {
                            if (snapshot.hasChild(BaseHelper.userIdProfileOthers)) {
                                alreadyChat = true;
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    MessageModel model = dataSnapshot.getValue(MessageModel.class);
                                    if (TextUtils.equals(model.getId(), BaseHelper.userIdProfileOthers)) {
                                        chatId = model.getChatId();
                                    }
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
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error Loading...", Toast.LENGTH_SHORT).show();
        }
    }
    public void GetUserProfile()
    {
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("profiles");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserProfileModel model = dataSnapshot.getValue(UserProfileModel.class);
                    if(TextUtils.equals(model.getUser_id(), BaseHelper.UserID)) {
                        myName=model.getFull_name();
                        myImage=model.getImage();
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
        //imageProgress.setVisibility(View.GONE);
    }

    public void GetOtherUserProfile()
    {
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("profiles");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        UserProfileModel model = dataSnapshot.getValue(UserProfileModel.class);
                        if (TextUtils.equals(model.getUser_id(), BaseHelper.userIdProfileOthers)) {
                            String userDistance=BaseHelper.userDistance+" Away";
                            distance.setText(userDistance);
                            gender.setText(model.getGender());
                            description.setText(model.getDescription());
                            nameUser = model.getUser_name();
                            idUser = model.getUser_id();
                            imageUser = model.getImage();
                            dob = model.getDate_of_birth();
                            latitude=model.getLocation_lat();
                            longitude=model.getLocation_long();
                            profileImage=model.getImage();
//                            Picasso.get().load(model.getImage()).into(new Target() {
//                                @Override
//                                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                                    width = bitmap.getWidth();
//                                    height = bitmap.getHeight();
//                                    profileAlternate.setImageBitmap(bitmap);
//                                }
//
//                                @Override
//                                public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//                                }
//
//                                @Override
//                                public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                                }
//                            });
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
                            BaseHelper.fullImage=model.getImage();
                            name.setText(model.getUser_name());
                            address.setText(model.getAddress());
                            disability.setText(model.getSelected_disability());
                            if (TextUtils.equals(model.getHome_town(), getString(R.string.hometown_Add))) {
                                homeTown.setText(R.string.hometown_profile);
                            } else {
                                homeTown.setText(model.getHome_town());
                            }
                            if (TextUtils.equals(model.getProfession(), getString(R.string.profession_Add))) {
                                profession.setText(R.string.profession_notaddedyet);
                            } else {
                                profession.setText(model.getProfession());
                            }
                            if (TextUtils.equals(model.getLooking_for(), getString(R.string.looking_Add))) {
                                lookingFor.setText(R.string.lookingfor__notaddedyet);
                            } else {
                                lookingFor.setText(model.getLooking_for());
                            }
                            if (TextUtils.equals(model.getRelationship_status(), getString(R.string.relationship_add))) {
                                relationShip.setText(R.string.relationship__notaddedyet);
                            } else {
                                relationShip.setText(model.getRelationship_status());
                            }
                            language.setText(model.getLanguage());
                            try {
                                String[] arr = dob.split("/");
                                int day = Integer.parseInt(arr[0]);
                                int month = Integer.parseInt(arr[1]);
                                int year = Integer.parseInt(arr[2]);
                                ageString = getAge(year, month, day);
                                age.setText(ageString + " Years");
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
        //imageProgress.setVisibility(View.GONE);
    }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(ProfileDetail.this);
    }
}