package com.example.micer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_table";
    private static final String COLUMN_NAME = "item";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_NAME + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }
    public static void insertItems(Context context, List<String> itemList) {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (String item : itemList) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, item);
            db.insert(TABLE_NAME, null, values);
        }

        db.close();
    }
    public static ArrayList<String> readItems(Context context) {
        ArrayList<String> itemList = new ArrayList<>();
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {COLUMN_NAME};
        Cursor cursor = db.query(TABLE_NAME, projection, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int columnIndex = cursor.getColumnIndexOrThrow(COLUMN_NAME);
            String item = cursor.getString(columnIndex);
            itemList.add(item);
        }

        cursor.close();
        db.close();

        return itemList;
    }
}

