package com.example.hp.openwhen.DBComponent;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by hp on 11/3/2017.
 */

public class OpenWhenContract {
public  static final String authority="com.example.hp.openwhen";
    private OpenWhenContract() {
    }


    public static class OpenWhenEntry implements BaseColumns {
        public static final String ID = BaseColumns._ID;
        public static final String TABLE = "openwhen";
        public static final String FEELING = "feeling";
        public static final String YEAR = "year";
        public static final String MONTH = "month";
        public static final String DAY = "day";
        public static final String CONTENT = "content";
        public static final Uri OPEN_URI=Uri.parse("content://"+authority+"/"+TABLE);

    }
}
