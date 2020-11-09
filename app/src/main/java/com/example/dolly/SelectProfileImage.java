package com.example.dolly;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dolly.Helper.BaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class SelectProfileImage extends AppCompatActivity {
Button gallery,upload;
ImageView profileImage;
    byte[] bytes;
    FirebaseAuth auth;
    Uri uri;
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;
    private double mUploadProgress=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_profile_image);
        auth=FirebaseAuth.getInstance();
        gallery=(Button)findViewById(R.id.btnChooseFromGallery);
        upload=(Button)findViewById(R.id.btnUpload);
        profileImage=(ImageView) findViewById(R.id.selectedProfileImage);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (ContextCompat.checkSelfPermission(getApplicationContext(),
//                        Manifest.permission.READ_EXTERNAL_STORAGE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(
//                            SelectProfileImage.this,
//                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                            REQUEST_CODE_STORAGE_PERMISSION
//                    );
//                } else {
//                    selectImage();
//                }
                //CropImage.startPickImageActivity(SelectProfileImage.this);
                CropImage.activity().start(SelectProfileImage.this);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hasImage(profileImage)) {
                    if (bytes != null) {
                        Intent intent = new Intent(getApplicationContext(), Description.class);
                        startActivity(intent);
                        Animatoo.animateFade(SelectProfileImage.this);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Please select photo to Sign Up",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please select photo to Sign Up",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean hasImage(@NonNull ImageView view) {
        Drawable drawable = view.getDrawable();
        boolean hasImage = (drawable != null);

        if (hasImage && (drawable instanceof BitmapDrawable)) {
            hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
        }

        return hasImage;
    }

    public void selectImage() {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent,REQUEST_CODE_SELECT_IMAGE);
        }
    }
    //Getting permission to read storage or open gallery
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.length > 0) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                selectImage();
//            } else {
//                Toast.makeText(getApplicationContext(), "Request Denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
    @Override
    public void onRequestPermissionsResult(
            int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //CropImage.startPickImageActivity(this);
                startCrop(uri);
            } else {
                Toast.makeText(this, "Cancelling, required permissions are not granted", Toast.LENGTH_LONG)
                        .show();
            }
        }
        if (requestCode == CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE) {
            if (uri != null
                    && grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCrop(uri);
            } else {
                Toast.makeText(this, "Cancelling, required permissions are not granted", Toast.LENGTH_LONG)
                        .show();
            }
        }
    }
    //Getting permission to select image and setting our ImageView to our selected image
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==REQUEST_CODE_SELECT_IMAGE&& resultCode==RESULT_OK)
//        {
//            if(data!=null)
//            {
//                Uri selectedImageUri=data.getData();
//                if(selectedImageUri!=null)
//                {
//                    try {
//                        InputStream inputStream=getContentResolver().openInputStream(selectedImageUri);
//                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                        //Bitmap.createScaledBitmap(bitmap,250,300,false);
//                        profileImage.setImageBitmap(bitmap);
//                        bytes= getBytesFromBitmap(bitmap,50);
//                        BaseHelper.profileImageBytes=bytes;
//                        //UploadPhoto(bytes);
//                        //if yu want to upload image file to server
//                        File selectedImageFile=new File(getPathFomUri(selectedImageUri));
//                    }catch (Exception ex)
//                    {
//                        Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        }
//        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK) {
//            if (data != null) {
//                Uri selectedImageUri = CropImage.getPickImageResultUri(this, data);
//                if (CropImage.isReadExternalStoragePermissionsRequired(this, selectedImageUri)) {
//                    uri = selectedImageUri;
//                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
//                } else {
//                    startCrop(selectedImageUri);
//                }
//
//            }
//        }
//        if (requestCode == CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE && resultCode == RESULT_OK) {
//            if (data != null) {
//                Uri selectedImageUri = CropImage.getPickImageResultUri(this, data);
//                if (CropImage.isExplicitCameraPermissionRequired(this)) {
//                    uri = selectedImageUri;
//                    requestPermissions(
//                            new String[] {Manifest.permission.CAMERA},
//                            CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE);
//                } else {
//                    startCrop(selectedImageUri);
//                    //CropImage.startPickImageActivity(this);
//                }
//
//            }
//        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri cropImage=result.getUri();
                if (cropImage != null) {
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(cropImage);
                        BitmapFactory.Options options=new BitmapFactory.Options();
                        options.inSampleSize=2;
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        //Bitmap.createScaledBitmap(bitmap,250,300,false);
                        profileImage.setImageBitmap(bitmap);
                        bytes = getBytesFromBitmap(bitmap, 50);
                        BaseHelper.profileImageBytes = bytes;
                        //UploadPhoto(bytes);
                        //if yu want to upload image file to server
                        //File selectedImageFile = new File(getPathFomUri(selectedImageUri));
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
    public void startCrop(Uri imageUri)
    {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
    }
//    public void UploadPhoto(byte[] UploadBytes)
//    {
//        StorageReference pictureRef = FirebaseStorage.getInstance().getReference().child("pictures/users/" + "/" + auth.getCurrentUser().getUid() + "/" + "profile_picture");
//        UploadTask uploadTask=null;
//        uploadTask=pictureRef.putBytes(UploadBytes);
//        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                if (taskSnapshot.getMetadata() != null) {
//                    if (taskSnapshot.getMetadata().getReference() != null) {
//                        Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
//                        result.addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                Toast.makeText(getApplicationContext(), "Photo Uploaded", Toast.LENGTH_SHORT).show();
//                                String pictureUrl = uri.toString();
//                                BaseHelper.profileImage=pictureUrl;
//                            }
//                        });
//                    }
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
//            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                double progress=(100 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
//                if(progress - 15 > mUploadProgress)
//                {
//                    Toast.makeText(getApplicationContext(),"Upload Progress:"+progress ,Toast.LENGTH_SHORT).show();
//                    mUploadProgress=progress;
//                }
//                //CameraFragment.progressBarVideo.setProgress((int)progress);
//            }
//        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                Intent intent = new Intent(getApplicationContext(), Disability.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
    public byte[] getBytesFromBitmap(Bitmap bitmap,int quality)
    {
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,quality,stream);
        return stream.toByteArray();
    }
    //Getting image path to get image file for upload purposes
    private String getPathFomUri(Uri contentUri)
    {
        String filePath;
        Cursor cursor=getContentResolver()
                .query(contentUri,null,null,null,null);
        if(cursor!=null)
        {
            filePath=contentUri.getPath();
        }
        else {
            cursor.moveToFirst();
            int index =cursor.getColumnIndex("_data");
            filePath=cursor.getString(index);
            cursor.close();
        }
        return filePath;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideRight(SelectProfileImage.this);
    }
}