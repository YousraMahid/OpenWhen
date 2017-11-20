package com.example.hp.openwhen.Activitis;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hp.openwhen.Adapters.OpenWhenAdapter;
import com.example.hp.openwhen.Model.OpenWhen;
import com.example.hp.openwhen.R;

import java.util.ArrayList;

import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.CONTENT;
import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.DAY;
import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.FEELING;
import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.ID;
import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.MONTH;
import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.OPEN_URI;
import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.YEAR;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = findViewById(R.id.list_item);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddFeeling.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OpenWhen openWhen = (OpenWhen) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, DetailFeeling.class);
                intent.putExtra("ID", openWhen.getId());

                startActivity(intent);

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Cursor cursor=getContentResolver().query(OPEN_URI,null,null,null,null);
        ArrayList<OpenWhen> openWhens =new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndex(ID));
            String feel=cursor.getString(cursor.getColumnIndex(FEELING));
            String content=cursor.getString(cursor.getColumnIndex(CONTENT));
            int year=cursor.getInt(cursor.getColumnIndex(YEAR));
            int month=cursor.getInt(cursor.getColumnIndex(MONTH));
            int day=cursor.getInt(cursor.getColumnIndex(DAY));
            OpenWhen openWhen=new OpenWhen(id,feel,year,month,day,content);
            openWhens.add(openWhen);
        }
        cursor.close();
        OpenWhenAdapter openWhenAdapter = new OpenWhenAdapter(this, R.layout.item_design, openWhens);
        listView.setAdapter(openWhenAdapter);

    }
}
