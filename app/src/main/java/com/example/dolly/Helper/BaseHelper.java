package com.example.dolly.Helper;

import com.google.firebase.database.DataSnapshot;

public class BaseHelper {
    public static boolean Zapping=false;
    public static boolean NearBy=false;
    public static boolean Likes=false;
    public static boolean Messages=false;
    public static boolean Profile=false;

    public static DataSnapshot profileSnapshot;
    public static Boolean isGoogle=false;
    public static Boolean isFacebook=false;
    public static String UserID;
    public static String id;
    public static String age;
    public static String firstName;
    public static String userName;
    public static String email;
    public static String phone;
    public static String date;
    public static String gender;
    public static String disabilitySelect;
    public static String disability;
    public static String location;
    public static String locationLat;
    public static String locationLong;
    public static byte[] profileImageBytes;
    public static String profileImage;
    public static String description;

    public static String userGender;
    public static String oppositeGender;
    public static String userLocationLat;
    public static String userLocationLong;
    public static String otherUserLocationLat;
    public static String otherUserLocationLong;
    public static String userDistance;
    public static boolean isBlocked=false;
    public static boolean isVip=false;

    public static String userIdProfileOthers;
    public static String chatId;
    public static String chatUserName;
    public static String fullImage;
    public static String fullPhoto;
}
