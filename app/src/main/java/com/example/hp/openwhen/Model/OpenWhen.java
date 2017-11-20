package com.example.hp.openwhen.Model;

import java.io.Serializable;

/**
 * Created by hp on 11/3/2017.
 */

public class OpenWhen implements Serializable {
    private int id;
    private String feeling;
    private int year;
    private  int month;
    private  int day;
    private String content;



    public OpenWhen(int id, String feeling, int year, int month, int day, String content) {
        this.id=id;
        this.feeling = feeling;
        this.year = year;
        this.month = month;
        this.day = day;
        this.content = content;
    }

    public String getFeeling() {
        return feeling;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getContent() {
        return content;
    }
}
