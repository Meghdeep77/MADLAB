package com.example.movie;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GroceryDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_GROCERIES = "groceries";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_GROCERIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT UNIQUE, "
                + COLUMN_PRICE + " REAL)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROCERIES);
        onCreate(db);
    }

    // Insert Grocery Item
    public void addGroceryItem(String name, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PRICE, price);
        db.insertWithOnConflict(TABLE_GROCERIES, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
    }

    // Get all grocery item names
    public List<String> getAllGroceryItems() {
        List<String> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_NAME + " FROM " + TABLE_GROCERIES, null);
        if (cursor.moveToFirst()) {
            do {
                itemList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return itemList;
    }

    // Get price of selected item
    public double getItemPrice(String itemName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_PRICE + " FROM " + TABLE_GROCERIES + " WHERE " + COLUMN_NAME + "=?", new String[]{itemName});
        double price = 0;
        if (cursor.moveToFirst()) {
            price = cursor.getDouble(0);
        }
        cursor.close();
        db.close();
        return price;
    }
}
