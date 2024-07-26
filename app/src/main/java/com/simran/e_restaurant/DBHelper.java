package com.simran.e_restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(id INTEGER primary key, name TEXT, age TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertData(String name, String age) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("age", age);

        // Get the next ID
        int nextId = getNextId();
        contentValues.put("id", nextId);

        long result = DB.insert("Userdetails", null, contentValues);
        return result != -1;
    }

    private int getNextId() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT MAX(id) FROM Userdetails", null);
        if (cursor.moveToFirst()) {
            int maxId = cursor.getInt(0);
            cursor.close();
            return maxId + 1;
        }
        cursor.close();
        return 1; // Start from 1 if no records exist
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from Userdetails", null);
    }

    public Boolean updateData(String id, String name, String age) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("age", age);

        Cursor cursor = DB.rawQuery("Select * from Userdetails where id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "id=?", new String[]{id});
            cursor.close();
            return result != -1;
        }
        cursor.close();
        return false;
    }

    public Boolean deleteData(String id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "id=?", new String[]{id});
            cursor.close();
            resetIds(); // Optional: reset IDs after deletion
            return result != -1;
        }
        cursor.close();
        return false;
    }

    private void resetIds() {
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = 'Userdetails'"); // Reset the auto-increment sequence
    }
}
