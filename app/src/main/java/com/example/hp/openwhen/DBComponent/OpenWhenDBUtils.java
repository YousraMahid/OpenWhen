package com.example.hp.openwhen.DBComponent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hp.openwhen.Model.OpenWhen;

import java.util.ArrayList;

import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.CONTENT;
import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.DAY;
import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.FEELING;
import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.ID;
import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.MONTH;
import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.TABLE;
import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.YEAR;

/**
 * Created by hp on 11/7/2017.
 */

public class OpenWhenDBUtils {
    public static void insert(Context context, String feel, int year,int month,int day,String content) {

        SQLiteDatabase db = getDatabase(context, true);

        ContentValues contentValues = new ContentValues();
        contentValues.put(FEELING, feel);
        contentValues.put(YEAR, year);
        contentValues.put(MONTH, month);
        contentValues.put(DAY, day);
        contentValues.put(CONTENT,content);

        db.insert(TABLE, null, contentValues);

    }

    public static ArrayList<OpenWhen> read(Context context) {

        SQLiteDatabase db = getDatabase(context, false);

        String[] fields = {ID, FEELING, YEAR, MONTH, DAY, CONTENT};

        Cursor cursor = db.query(TABLE, fields, null, null, null, null, null);

        ArrayList<OpenWhen> students = new ArrayList<>();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(cursor.getColumnIndex(ID));
            String feel = cursor.getString(cursor.getColumnIndex(FEELING));
            int year = cursor.getInt(cursor.getColumnIndex(YEAR));
            int month = cursor.getInt(cursor.getColumnIndex(MONTH));
            int day = cursor.getInt(cursor.getColumnIndex(DAY));
            String content = cursor.getString(cursor.getColumnIndex(CONTENT));


            OpenWhen student = new OpenWhen(id, feel, year, month, day, content);

            students.add(student);

        }


        return students;
    }

    public static void update(Context context, int id, String feel, int year, int month, int day, String content) {

        SQLiteDatabase db = getDatabase(context, true);

        ContentValues contentValues = new ContentValues();
        contentValues.put(FEELING, feel);
        contentValues.put(YEAR, year);
        contentValues.put(MONTH, month);
        contentValues.put(DAY, day);
        contentValues.put(CONTENT, content);

        String[] args = {String.valueOf(id)};

        db.update(TABLE, contentValues, ID + " = ?", args);

    }

    public static void delete(Context context, int id) {

        SQLiteDatabase db = getDatabase(context, true);

        String[] args = {String.valueOf(id)};

        db.delete(TABLE, ID + " = ?", args);
    }

    private static SQLiteDatabase getDatabase(Context context, boolean isWritable) {
        OpenWhenHelper helper = new OpenWhenHelper(context);
        if (isWritable)
            return helper.getWritableDatabase();
        else
            return helper.getReadableDatabase();
    }
}
