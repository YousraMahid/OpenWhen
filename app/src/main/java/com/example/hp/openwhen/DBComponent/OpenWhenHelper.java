package com.example.hp.openwhen.DBComponent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hp.openwhen.DBComponent.OpenWhenContract;

/**
 * Created by hp on 11/3/2017.
 */

public class OpenWhenHelper extends SQLiteOpenHelper {

    public OpenWhenHelper(Context context) {
        super(context, "openwhen.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE openwhen(" + OpenWhenContract.OpenWhenEntry.ID + " INTEGER PRIMARY KEY, "+ OpenWhenContract.OpenWhenEntry.FEELING + " TEXT, "+ OpenWhenContract.OpenWhenEntry.YEAR + "  INTEGER,"+ OpenWhenContract.OpenWhenEntry.MONTH + "  INTEGER," +OpenWhenContract.OpenWhenEntry.DAY + "  INTEGER,"+OpenWhenContract.OpenWhenEntry.CONTENT+" text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
