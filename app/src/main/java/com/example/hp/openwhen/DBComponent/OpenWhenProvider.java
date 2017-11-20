package com.example.hp.openwhen.DBComponent;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.*;

/**
 * Created by hp on 11/7/2017.
 */

public class OpenWhenProvider extends ContentProvider {
    OpenWhenHelper openWhenHelper;

    final static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(OpenWhenContract.authority, OpenWhenContract.OpenWhenEntry.TABLE, 1);
        uriMatcher.addURI(OpenWhenContract.authority, OpenWhenContract.OpenWhenEntry.TABLE + "/#", 2);
    }


    @Override
    public boolean onCreate() {
        openWhenHelper = new OpenWhenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        int code = uriMatcher.match(uri);
        String[] columns = {ID, FEELING, YEAR, MONTH, DAY, CONTENT};
        if (code == 1) {
            Cursor cursor = openWhenHelper.getReadableDatabase().query(TABLE, columns, null, null, null, null, null);
            return cursor;
        } else if (code == 2) {
            long id = ContentUris.parseId(uri);
            String[] args = {String.valueOf(id)};
            Cursor cursor = openWhenHelper.getReadableDatabase().query(TABLE, columns, ID + "=?", args, null, null, null);
            return cursor;

        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        openWhenHelper.getWritableDatabase().insert(TABLE, null, contentValues);
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
       return openWhenHelper.getWritableDatabase().delete(TABLE, s, strings);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
      return openWhenHelper.getWritableDatabase().update(TABLE,contentValues,s,strings);

    }
}
