package com.example.hp.openwhen.Activitis;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.openwhen.DBComponent.OpenWhenDBUtils;
import com.example.hp.openwhen.Model.OpenWhen;
import com.example.hp.openwhen.DBComponent.OpenWhenContract;
import com.example.hp.openwhen.DBComponent.OpenWhenHelper;
import com.example.hp.openwhen.R;

import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.*;
import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.TABLE;

public class DetailFeeling extends AppCompatActivity {
    EditText feels;
    EditText years;
    EditText months;
    EditText days;
    EditText contents;
    ImageButton delete;
    ImageButton update;
    TextView OpenID;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_feeling);
        final OpenWhen openWhen = (OpenWhen) getIntent().getSerializableExtra("OPEN");
        OpenID = findViewById(R.id.feel_id);
        feels = findViewById(R.id.edit_feel);
        years = findViewById(R.id.edit_year);
        months = findViewById(R.id.edit_month);
        days = findViewById(R.id.edit_day);
        contents = findViewById(R.id.edit_say);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        id=getIntent().getIntExtra("ID",-1);
        Uri uri=Uri.parse("content://"+OpenWhenContract.authority+"/"+TABLE+"/"+id);
       // Uri uri=Uri.withAppendedPath(OPEN_URI,id);
        //equal with above
        Cursor cursor=getContentResolver().query(uri,null,null,null,null);
        cursor.moveToNext();
        feels.setText(cursor.getString(cursor.getColumnIndex(FEELING)));
        contents.setText(cursor.getString(cursor.getColumnIndex(CONTENT)));
        years.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex(YEAR))));
        months.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex(MONTH))));
        days.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex(DAY))));
        OpenID.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex(ID))));



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[]args={String.valueOf(id)};
                getContentResolver().delete(OPEN_URI,ID+"=?",args);
               // OpenWhenDBUtils.delete(DetailFeeling.this, openWhen.getId());
                Toast.makeText(DetailFeeling.this, "Delete it ", Toast.LENGTH_SHORT).show();
                feels.setText("");
                years.setText("");
                months.setText("");
                days.setText("");
                contents.setText("");
                finish();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values=new ContentValues();
                values.put(FEELING,feels.getText().toString());
                values.put(YEAR,years.getText().toString());
                values.put(MONTH,months.getText().toString());
                values.put(DAY,days.getText().toString());
                values.put(CONTENT,contents.getText().toString());
                String[] args={String.valueOf(id)};

                getContentResolver().update(OPEN_URI,values,ID+"=?",args);

                // OpenWhenDBUtils.update(DetailFeeling.this, openWhen.getId(), openWhen.getFeeling(), openWhen.getYear(), openWhen.getMonth(), openWhen.getDay(), openWhen.getContent());

                Toast.makeText(DetailFeeling.this, "Update it ", Toast.LENGTH_SHORT).show();
                finish();

            }
        });


    }
}
