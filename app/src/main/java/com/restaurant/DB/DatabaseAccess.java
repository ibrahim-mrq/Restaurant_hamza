package com.restaurant.DB;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.restaurant.model.Cart;
import com.restaurant.model.Order;
import com.restaurant.model.User;

import java.util.ArrayList;

@SuppressLint("Range")
public class DatabaseAccess {

    private SQLiteDatabase database;
    private final SQLiteOpenHelper openHelper;

    private static DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new Database(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = this.openHelper.getWritableDatabase();
    }

    public void close() {
        if (this.database != null) {
            this.database.close();
        }
    }

    /// TODO : >>> Cart

    public void insertCart(Cart model) {
        ContentValues cv = new ContentValues();
        cv.put(Database.CART_COLUMN_NAME, model.getName());
        cv.put(Database.CART_COLUMN_INGREDIENTS, model.getIngredients());
        cv.put(Database.CART_COLUMN_PRICE, model.getPrice());
        cv.put(Database.CART_COLUMN_IMAGE, model.getImage());
        cv.put(Database.CART_COLUMN_QUANTITY, model.getQuantity());
        cv.put(Database.CART_COLUMN_USER_ID, model.getUserId());
        cv.put(Database.CART_COLUMN_PRODUCT_ID, model.getProductId());
        database.insert(Database.CART_TB_NAME, null, cv);
    }

    public Cart getCartItem(int id) {
        Cart cart = new Cart();
        Cursor c = database.rawQuery("SELECT * FROM " + Database.CART_TB_NAME + " WHERE " + Database.CART_COLUMN_PRODUCT_ID
                + " LIKE ? ", new String[]{id + "%"});

        if (c != null && c.moveToFirst()) {
            do {
                int ids = c.getInt(c.getColumnIndex(Database.CART_COLUMN_ID));
                String name = c.getString(c.getColumnIndex(Database.CART_COLUMN_NAME));
                String ingredients = c.getString(c.getColumnIndex(Database.CART_COLUMN_INGREDIENTS));
                String price = c.getString(c.getColumnIndex(Database.CART_COLUMN_PRICE));
                int image = c.getInt(c.getColumnIndex(Database.CART_COLUMN_IMAGE));
                int quantity = c.getInt(c.getColumnIndex(Database.CART_COLUMN_QUANTITY));
                int userId = c.getInt(c.getColumnIndex(Database.CART_COLUMN_USER_ID));
                int productId = c.getInt(c.getColumnIndex(Database.CART_COLUMN_PRODUCT_ID));
                cart = new Cart(ids, name, ingredients, price, image, quantity, userId, productId);
            } while (c.moveToNext());
            c.close();
        }
        return cart;
    }

    public void updateCart(Cart model) {
        ContentValues values = new ContentValues();
        values.put(Database.CART_COLUMN_NAME, model.getName());
        values.put(Database.CART_COLUMN_INGREDIENTS, model.getIngredients());
        values.put(Database.CART_COLUMN_PRICE, model.getPrice());
        values.put(Database.CART_COLUMN_IMAGE, model.getImage());
        values.put(Database.CART_COLUMN_QUANTITY, model.getQuantity());
        values.put(Database.CART_COLUMN_PRODUCT_ID, model.getProductId());
        String[] args = {String.valueOf(model.getId())};
        database.update(Database.CART_TB_NAME, values, "id=?", args);
    }

    public void deleteCart(Cart model) {
        String[] args = {String.valueOf(model.getId())};
        database.delete(Database.CART_TB_NAME, "id=?", args);
    }

    public ArrayList<Cart> getAllCartByUserId(int UserId) {
        ArrayList<Cart> models = new ArrayList<>();
        Cursor c = database.rawQuery("SELECT * FROM " + Database.CART_TB_NAME + " WHERE " + Database.CART_COLUMN_USER_ID
                + " LIKE ? ", new String[]{UserId + "%"});
        if (c != null && c.moveToFirst()) {
            do {
                int id = c.getInt(c.getColumnIndex(Database.CART_COLUMN_ID));
                String name = c.getString(c.getColumnIndex(Database.CART_COLUMN_NAME));
                String ingredients = c.getString(c.getColumnIndex(Database.CART_COLUMN_INGREDIENTS));
                String price = c.getString(c.getColumnIndex(Database.CART_COLUMN_PRICE));
                int image = c.getInt(c.getColumnIndex(Database.CART_COLUMN_IMAGE));
                int quantity = c.getInt(c.getColumnIndex(Database.CART_COLUMN_QUANTITY));
                int userId = c.getInt(c.getColumnIndex(Database.CART_COLUMN_USER_ID));
                int productId = c.getInt(c.getColumnIndex(Database.CART_COLUMN_PRODUCT_ID));
                Cart model = new Cart(id, name, ingredients, price, image, quantity, userId, productId);
                models.add(model);
            }
            while (c.moveToNext());
            c.close();
        }
        return models;
    }

    /// TODO : >>> Order

    public void insertOrder(Order model) {
        ContentValues cv = new ContentValues();
        cv.put(Database.ORDER_COLUMN_PRICE, model.getPrice());
        cv.put(Database.ORDER_COLUMN_QUANTITY, model.getQuantity());
        cv.put(Database.ORDER_COLUMN_USER_ID, model.getUserId());
        cv.put(Database.ORDER_COLUMN_USER_EMAIL, model.getUserEmail());
        cv.put(Database.ORDER_COLUMN_USER_NAME, model.getUserName());
        cv.put(Database.ORDER_COLUMN_USER_PHONE, model.getUserPhone());
        cv.put(Database.ORDER_COLUMN_DATE, model.getDate());
        database.insert(Database.ORDER_TB_NAME, null, cv);
    }

    public boolean updateOrder(Order model) {
        ContentValues values = new ContentValues();
        values.put(Database.ORDER_COLUMN_PRICE, model.getPrice());
        values.put(Database.ORDER_COLUMN_QUANTITY, model.getQuantity());
        values.put(Database.ORDER_COLUMN_USER_ID, model.getUserId());
        values.put(Database.ORDER_COLUMN_USER_EMAIL, model.getUserEmail());
        values.put(Database.ORDER_COLUMN_USER_NAME, model.getUserName());
        values.put(Database.ORDER_COLUMN_USER_PHONE, model.getUserPhone());
        values.put(Database.ORDER_COLUMN_DATE, model.getDate());
        String[] args = {String.valueOf(model.getId())};
        int res = database.update(Database.ORDER_TB_NAME, values, "id=?", args);
        return res > 0;
    }

    public ArrayList<Order> getAllOrderByUserId(int UserId) {
        ArrayList<Order> models = new ArrayList<>();
        Cursor c = database.rawQuery("SELECT * FROM " + Database.ORDER_TB_NAME + " WHERE " + Database.ORDER_COLUMN_USER_ID
                + " LIKE ? ", new String[]{UserId + "%"});
        if (c != null && c.moveToFirst()) {
            do {
                int id = c.getInt(c.getColumnIndex(Database.ORDER_COLUMN_ID));
                String price = c.getString(c.getColumnIndex(Database.ORDER_COLUMN_PRICE));
                int quantity = c.getInt(c.getColumnIndex(Database.ORDER_COLUMN_QUANTITY));
                int userId = c.getInt(c.getColumnIndex(Database.ORDER_COLUMN_USER_ID));
                String userName = c.getString(c.getColumnIndex(Database.ORDER_COLUMN_USER_NAME));
                String userPhone = c.getString(c.getColumnIndex(Database.ORDER_COLUMN_USER_PHONE));
                String userEmail = c.getString(c.getColumnIndex(Database.ORDER_COLUMN_USER_EMAIL));
                String date = c.getString(c.getColumnIndex(Database.ORDER_COLUMN_DATE));
                Order model = new Order(id, price, quantity, userId, userName, userPhone, userEmail, date);
                models.add(model);
            }
            while (c.moveToNext());
            c.close();
        }
        return models;
    }

    /// TODO : >>> USER

    public void insertUser(User model) {
        ContentValues cv = new ContentValues();
        cv.put(Database.USER_COLUMN_FIRST_NAME, model.getFirstName());
        cv.put(Database.USER_COLUMN_LAST_NAME, model.getLastName());
        cv.put(Database.USER_COLUMN_EMAIL, model.getEmail());
        cv.put(Database.USER_COLUMN_PASSWORD, model.getPassword());
        cv.put(Database.USER_COLUMN_PHONE, model.getPhone());
        cv.put(Database.USER_COLUMN_PHONE_2, model.getPhone2());
        cv.put(Database.USER_COLUMN_GOVERNORATE, model.getGovernorate());
        cv.put(Database.USER_COLUMN_NEIGHBORHOOD, model.getNeighborhood());
        cv.put(Database.USER_COLUMN_HOUSE_NUMBER, model.getHouseNumber());
        cv.put(Database.USER_COLUMN_NAVIGATIONAL, model.getNavigational());

        database.insert(Database.USER_TB_NAME, null, cv);
    }

    public void updateUser(User model) {
        ContentValues values = new ContentValues();
        values.put(Database.USER_COLUMN_FIRST_NAME, model.getFirstName());
        values.put(Database.USER_COLUMN_LAST_NAME, model.getLastName());
//        values.put(Database.USER_COLUMN_EMAIL, model.getEmail());
        values.put(Database.USER_COLUMN_PASSWORD, model.getPassword());
        values.put(Database.USER_COLUMN_PHONE, model.getPhone());
        values.put(Database.USER_COLUMN_PHONE_2, model.getPhone2());
        values.put(Database.USER_COLUMN_GOVERNORATE, model.getGovernorate());
        values.put(Database.USER_COLUMN_NEIGHBORHOOD, model.getNeighborhood());
        values.put(Database.USER_COLUMN_HOUSE_NUMBER, model.getHouseNumber());
        values.put(Database.USER_COLUMN_NAVIGATIONAL, model.getNavigational());
        String[] args = {String.valueOf(model.getId())};
        database.update(Database.USER_TB_NAME, values, "id=?", args);
    }

    public boolean checkUser(String email, String pass) {
        String[] columns = {Database.USER_COLUMN_ID};
        String selection = Database.USER_COLUMN_EMAIL + "=?" + " AND " + Database.USER_COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {email, pass};
        Cursor c = database.query(Database.USER_TB_NAME, columns, selection, selectionArgs,
                null, null, null);
        int cursorCount = c.getCount();
        c.close();
        return cursorCount > 0;
    }

    public User getUser(String email) {
        User user = new User();
        Cursor c = database.rawQuery("SELECT * FROM " + Database.USER_TB_NAME + " WHERE " + Database.USER_COLUMN_EMAIL
                + " LIKE ? ", new String[]{email + "%"});
        if (c != null && c.moveToFirst()) {
            do {
                int id = c.getInt(c.getColumnIndex(Database.USER_COLUMN_ID));
                String f_name = c.getString(c.getColumnIndex(Database.USER_COLUMN_FIRST_NAME));
                String l_name = c.getString(c.getColumnIndex(Database.USER_COLUMN_LAST_NAME));
                String password = c.getString(c.getColumnIndex(Database.USER_COLUMN_PASSWORD));
                String emails = c.getString(c.getColumnIndex(Database.USER_COLUMN_EMAIL));
                String phone = c.getString(c.getColumnIndex(Database.USER_COLUMN_PHONE));
                String phone2 = c.getString(c.getColumnIndex(Database.USER_COLUMN_PHONE_2));
                String governorate = c.getString(c.getColumnIndex(Database.USER_COLUMN_GOVERNORATE));
                String neighborhood = c.getString(c.getColumnIndex(Database.USER_COLUMN_NEIGHBORHOOD));
                String houseNumber = c.getString(c.getColumnIndex(Database.USER_COLUMN_HOUSE_NUMBER));
                String navigational = c.getString(c.getColumnIndex(Database.USER_COLUMN_NAVIGATIONAL));
                user = new User(id, f_name, l_name, emails, phone, phone2, password, governorate, neighborhood, houseNumber, navigational);
            } while (c.moveToNext());
            c.close();
        }
        return user;
    }

}
