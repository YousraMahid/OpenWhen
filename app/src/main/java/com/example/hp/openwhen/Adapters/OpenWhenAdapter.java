package com.example.hp.openwhen.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hp.openwhen.Model.OpenWhen;
import com.example.hp.openwhen.R;

import java.util.ArrayList;

/**
 * Created by hp on 11/3/2017.
 */

public class OpenWhenAdapter extends ArrayAdapter<OpenWhen> {
    private Context context;
    private int resource;
    private ArrayList<OpenWhen> objects;

    public OpenWhenAdapter(Context context, int resource, ArrayList<OpenWhen> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, null);
        }
        OpenWhen openWhen=objects.get(position);
        TextView id=convertView.findViewById(R.id.id);
        TextView feeling=convertView.findViewById(R.id.feeling);
        TextView year=convertView.findViewById(R.id.year);
        TextView month=convertView.findViewById(R.id.month);
        TextView day=convertView.findViewById(R.id.day);
       // TextView content=convertView.findViewById(R.id.edit_say);
        id.setText(String.valueOf(openWhen.getId()));
        feeling.setText(openWhen.getFeeling());
        year.setText(String.valueOf(openWhen.getYear())+"/");
        month.setText(String.valueOf(openWhen.getMonth())+"/");
        day.setText(String.valueOf(openWhen.getDay()));
        //content.setText(openWhen.getContent());


        return convertView;
    }
}
