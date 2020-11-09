package com.example.dolly.Helper;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.example.dolly.Model.FireBaseModels.FilterModel;
import com.example.dolly.Model.FireBaseModels.UserProfileModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseHelper {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private StorageReference mStorageRef;
    private String UserID;
    Context mContext;

    //Vars
    private double mUploadProgress=0;
    private long count=0;
    View view;
    private String videoUserName,videoImage;
    //models
    UserProfileModel userProfileModel;
    UserProfileModel profileModel;

    public FirebaseHelper(Context context)
    {
      mAuth=FirebaseAuth.getInstance();
      mDatabase=FirebaseDatabase.getInstance();
      mRef=mDatabase.getReference();
      mStorageRef=FirebaseStorage.getInstance().getReference();
      mContext=context;
      if(mAuth.getCurrentUser()!=null) {
          UserID = mAuth.getCurrentUser().getUid();
      }
    }

    public void AddProfile(String id,String Name,String UserName,String Phone,String Email,String Date)
    {
        DatabaseReference reference= mDatabase.getReference().child("profiles");
        UserProfileModel model=new UserProfileModel(id,Name,UserName,Phone,Email,BaseHelper.gender,BaseHelper.disabilitySelect,BaseHelper.disability,BaseHelper.profileImage,Date,BaseHelper.location,BaseHelper.locationLat,BaseHelper.locationLong,BaseHelper.description,"Add","Add","Add","Add","Add","Add","Add","Add","Add","English","Add","Add","Add","Add","Add","Add","Add","Add");
        reference.child(id).setValue(model);
    }
    public void UpdateProfile(String id,String Name,String UserName,String Phone,String Email,String Gender,String DisabilitySelect,String Disability,String Image,String Date,String Location,String LocationLat,String LocationLong,String Description,String HomeTown,String Profession,String LookingFor,String RelationShip,String BodyHeight,String Education,String Smoker,String Religion,String BodyType,String Language,String Drinking,String Exercise,String Tattoos,String Pets,String Music,String Diet,String Children,String Bio)
    {
        DatabaseReference reference= mDatabase.getReference().child("profiles");
        UserProfileModel model=new UserProfileModel(id,Name,UserName,Phone,Email,Gender,DisabilitySelect,Disability,Image,Date,Location,LocationLat,LocationLong,Description,HomeTown,Profession,LookingFor,RelationShip,BodyHeight,Education,Smoker,Religion,BodyType,Language,Drinking,Exercise,Tattoos,Pets,Music,Diet,Children,Bio);
        reference.child(id).setValue(model);
    }
    public void AddFilter(String id)
    {
        String OppGender;
        if(TextUtils.equals(BaseHelper.userGender,"Male"))
        {
            OppGender="Female";
        }
        else
        {
            OppGender="Male";
        }
        DatabaseReference reference= mDatabase.getReference().child("filters");
        FilterModel model=new FilterModel(id,OppGender,"18","90","Distance doesn't matter","Disability doesn't matter","All","All","All","All","All","All","All","All","All","All","All","All","All","All","All","All");
        reference.child(id).setValue(model);
    }
    public void UpdateFilter(String id,String Gender,String Age1,String Age2,String Distance,String DisabilitySelect,String Disability,String Profession,String RelationShip,String BodyHeight,String Education,String Smoker,String Religion,String BodyType,String Language,String Drinking,String Exercise,String Tattoos,String Pets,String Music,String Diet,String Children)
    {
        DatabaseReference reference= mDatabase.getReference().child("filters");
        FilterModel model=new FilterModel(id,Gender,Age1,Age2,Distance,DisabilitySelect,Disability,Profession,RelationShip,BodyHeight,Education,Smoker,Religion,BodyType,Language,Drinking,Exercise,Tattoos,Pets,Music,Diet,Children);
        reference.child(id).setValue(model);
    }
//    public UserProfileModel GetProfile()
//    {
//        userProfileModel=new UserProfileModel();
//        DatabaseReference reference= mDatabase.getReference().child("profiles");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    UserProfileModel model = dataSnapshot.getValue(UserProfileModel.class);
//                    if(TextUtils.equals(model.getUser_id(),Helper.UserId)) {
////                        userProfileModel.setUser_id(model.getUser_id());
////                        userProfileModel.setUser_name(model.getUser_name());
////                        userProfileModel.setFollowers(model.getFollowers());
////                        userProfileModel.setFollowing(model.getFollowing());
////                        userProfileModel.setLike(model.getLike());
////                        userProfileModel.setBio(model.getBio());
////                        userProfileModel.setImage(model.getImage());
//                        userProfileModel=model;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        return userProfileModel;
//    }
//    public UserProfileModel GetOthersProfile(final String UserId)
//    {
//        userProfileModel=new UserProfileModel();
//        DatabaseReference reference= mDatabase.getReference("profile");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    UserProfileModel model = dataSnapshot.getValue(UserProfileModel.class);
//                    if(TextUtils.equals(model.getUser_id(),UserId)) {
//                        userProfileModel=model;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        return userProfileModel;
//    }
//    public void UploadVideo(final Uri videoUri, final ProgressBar progressBar)
//    {
//        progressBar.setVisibility(View.VISIBLE);
//        FilePaths filePaths=new FilePaths();
//        getVideoItemsCount();
//        String uid=UUID.randomUUID().toString();
//        final String videoName=uid+".3gp";
//        count++;
//
//        StorageReference videoRef =mStorageRef.child(filePaths.FIREBASE_VIDEO_STORAGE + "/" + UserID + "/" + videoName);
//        videoRef.putFile(videoUri)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
////                        if (taskSnapshot.getMetadata() != null) {
////                            if (taskSnapshot.getMetadata().getReference() != null) {
//                        Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
//                        result.addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                CameraFragment.textView.setText("Uploading Completed");
//                                Toast.makeText(mContext,"Video Uploaded",Toast.LENGTH_SHORT).show();
//                                String videoUrl = uri.toString();
//                                AddVideoToDatabase(videoUrl,videoName);
//                            }
//                        });
//                    }
////                        }
////                    }
//                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                double progress=(100 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
//                if(progress - 5 > mUploadProgress)
//                {
//                    Toast.makeText(mContext,"Upload Progress: Uploading" ,Toast.LENGTH_SHORT).show();
//                    mUploadProgress=progress;
//                }
//                CameraFragment.progressBarVideo.setProgress((int)progress);
//              }
//
//            }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception exception) {
//                        Toast.makeText(mContext,exception.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                });
//        progressBar.setVisibility(View.GONE);
//    }
//    public String getTimStamp()
//    {
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
//        sdf.setTimeZone(TimeZone.getTimeZone("Indian/Reunion"));
//        return sdf.format(new Date());
//    }
//
//    public void AddVideoToDatabase(String videoUrl,String videoName)
//    {
//        long Values=Helper.UserProfileSnapshot.getChildrenCount();
//        if(Helper.UserProfileSnapshot!=null&&Values!=0) {
//            for (DataSnapshot snapshot : Helper.UserProfileSnapshot.getChildren()) {
//                profileModel = snapshot.getValue(UserProfileModel.class);
//                if(TextUtils.equals(profileModel.getUser_id(),Helper.UserId)) {
//                    videoUserName=profileModel.getUser_name();
//                    videoImage=profileModel.getImage();
//                }
//            }
//
//        }
//        else if(Values==0)
//        {
//
//        }
//
//        DatabaseReference dataUsers= mDatabase.getReference();
//        DatabaseReference dataAll= mDatabase.getReference();
//        String id = mDatabase.getReference().child("videos").push().getKey();
//        String date=getTimStamp();
//        ProfileVideoModel model=new ProfileVideoModel(id,date,videoUrl,UserID,videoName,videoImage,videoUserName,"0","0","Music By Disney Official","");
//        dataUsers.child("user_videos").child(UserID).child(id).setValue(model);
//        dataAll.child("videos").child(id).setValue(model);
//
//    }

    }

