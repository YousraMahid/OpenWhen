package com.example.hp.openwhen.Activitis;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import com.example.hp.openwhen.R;

import static com.example.hp.openwhen.DBComponent.OpenWhenContract.OpenWhenEntry.*;

public class AddFeeling extends AppCompatActivity {

    EditText feeling;
    EditText year;
    EditText month;
    EditText day;
    EditText content;

    public void referenceVoid() {
        feeling = findViewById(R.id.edit_feel);
        year = findViewById(R.id.edit_year);
        month = findViewById(R.id.edit_month);
        day = findViewById(R.id.edit_day);
        content = findViewById(R.id.edit_say);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feeling);
        referenceVoid();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            if (!(feeling.getText().toString().trim().isEmpty() || year.getText().toString().trim().isEmpty() || month.getText().toString().trim().isEmpty() || day.getText().toString().trim().isEmpty() || content.getText().toString().trim().isEmpty())) {
                ContentValues contentValues=new ContentValues();
                contentValues.put(FEELING,feeling.getText().toString());
                contentValues.put(YEAR,year.getText().toString());
                contentValues.put(MONTH,month.getText().toString());
                contentValues.put(DAY,day.getText().toString());
                contentValues.put(CONTENT,content.getText().toString());

                getContentResolver().insert(OPEN_URI,contentValues);

                //OpenWhenDBUtils.insert(AddFeeling.this, feeling.getText().toString(), Integer.parseInt(year.getText().toString()), Integer.parseInt(month.getText().toString()), Integer.parseInt(day.getText().toString()), content.getText().toString());
                finish();
            }
            else {
                Toast.makeText(this, "Please fill the Edit Text !", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
