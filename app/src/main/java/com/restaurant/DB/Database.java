package com.restaurant.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String DB_NAME = "Restaurant.db";
    public static final int DB_VERSION = 1;

    /* TODO : >> Cart DB */
    public static final String CART_TB_NAME = "cart";
    public static final String CART_COLUMN_ID = "id";
    public static final String CART_COLUMN_NAME = "name";
    public static final String CART_COLUMN_INGREDIENTS = "ingredients";
    public static final String CART_COLUMN_PRICE = "price";
    public static final String CART_COLUMN_IMAGE = "image";
    public static final String CART_COLUMN_QUANTITY = "quantity";
    public static final String CART_COLUMN_USER_ID = "userId";
    public static final String CART_COLUMN_PRODUCT_ID = "productId";

    /* TODO : >> Order DB */
    public static final String ORDER_TB_NAME = "orders";
    public static final String ORDER_COLUMN_ID = "id";
    public static final String ORDER_COLUMN_PRICE = "price";
    public static final String ORDER_COLUMN_QUANTITY = "quantity";
    public static final String ORDER_COLUMN_USER_ID = "userId";
    public static final String ORDER_COLUMN_USER_NAME = "userName";
    public static final String ORDER_COLUMN_USER_PHONE = "userPhone";
    public static final String ORDER_COLUMN_USER_EMAIL = "userEmail";
    public static final String ORDER_COLUMN_DATE = "date";
    public static final String ORDER_COLUMN_LIST_PRODUCT = "listProduct";

    /* TODO : >> User DB */
    public static final String USER_TB_NAME = "user";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_FIRST_NAME = "first_name";
    public static final String USER_COLUMN_LAST_NAME = "last_name";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_PHONE = "phone";
    public static final String USER_COLUMN_PHONE_2 = "phone_2";
    public static final String USER_COLUMN_PASSWORD = "password";

    public static final String USER_COLUMN_GOVERNORATE = "governorate";
    public static final String USER_COLUMN_NEIGHBORHOOD = "neighborhood";
    public static final String USER_COLUMN_HOUSE_NUMBER = "houseNumber";
    public static final String USER_COLUMN_NAVIGATIONAL = "navigational";

    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + USER_TB_NAME + " " + "("
                + USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + USER_COLUMN_FIRST_NAME + " TEXT ,"
                + USER_COLUMN_LAST_NAME + " TEXT,"
                + USER_COLUMN_EMAIL + " TEXT,"
                + USER_COLUMN_PHONE + " TEXT,"
                + USER_COLUMN_PHONE_2 + " TEXT,"
                + USER_COLUMN_GOVERNORATE + " TEXT,"
                + USER_COLUMN_NEIGHBORHOOD + " TEXT,"
                + USER_COLUMN_HOUSE_NUMBER + " TEXT,"
                + USER_COLUMN_NAVIGATIONAL + " TEXT,"
                + USER_COLUMN_PASSWORD + " TEXT )");

        db.execSQL("CREATE TABLE " + CART_TB_NAME + " " + "("
                + CART_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + CART_COLUMN_NAME + " TEXT ,"
                + CART_COLUMN_INGREDIENTS + " TEXT ,"
                + CART_COLUMN_PRICE + " TEXT,"
                + CART_COLUMN_IMAGE + " TEXT,"
                + CART_COLUMN_QUANTITY + " TEXT,"
                + CART_COLUMN_USER_ID + " TEXT,"
                + CART_COLUMN_PRODUCT_ID + " TEXT )");

        db.execSQL("CREATE TABLE " + ORDER_TB_NAME + " " + "("
                + ORDER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + ORDER_COLUMN_PRICE + " TEXT ,"
                + ORDER_COLUMN_QUANTITY + " TEXT,"
                + ORDER_COLUMN_USER_ID + " TEXT,"
                + ORDER_COLUMN_USER_NAME + " TEXT,"
                + ORDER_COLUMN_USER_PHONE + " TEXT,"
                + ORDER_COLUMN_USER_EMAIL + " TEXT,"
                + ORDER_COLUMN_DATE + " TEXT,"
                + ORDER_COLUMN_LIST_PRODUCT + " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CART_TB_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_TB_NAME);

        db.execSQL("CREATE TABLE " + CART_TB_NAME + " " + "("
                + CART_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + CART_COLUMN_NAME + " TEXT ,"
                + CART_COLUMN_INGREDIENTS + " TEXT ,"
                + CART_COLUMN_PRICE + " TEXT,"
                + CART_COLUMN_IMAGE + " TEXT,"
                + CART_COLUMN_QUANTITY + " TEXT,"
                + CART_COLUMN_USER_ID + " TEXT,"
                + CART_COLUMN_PRODUCT_ID + " TEXT )");

        db.execSQL("CREATE TABLE " + ORDER_TB_NAME + " " + "("
                + ORDER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + ORDER_COLUMN_PRICE + " TEXT ,"
                + ORDER_COLUMN_QUANTITY + " TEXT,"
                + ORDER_COLUMN_USER_ID + " TEXT,"
                + ORDER_COLUMN_USER_NAME + " TEXT,"
                + ORDER_COLUMN_USER_PHONE + " TEXT,"
                + ORDER_COLUMN_USER_EMAIL + " TEXT,"
                + ORDER_COLUMN_DATE + " TEXT,"
                + ORDER_COLUMN_LIST_PRODUCT + " TEXT )");
    }
}
