package com.packag.yvyor.projecttouristhelper;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class LocationAdapter extends ArrayAdapter<LocationItem> {
    Context mCtx;
    List<LocationItem> locationItems;
    SQLiteDatabase database;
    frg_locations locationFrg;
    int listLayoutRes;

    public LocationAdapter(@NonNull Context mCtx, frg_locations locationFrg, int listLayoutRes, List<LocationItem> locationItems, SQLiteDatabase database) {
        super(mCtx, listLayoutRes, locationItems);
        this.mCtx = mCtx;
        this.locationItems = locationItems;
        this.database = database;
        this.listLayoutRes = listLayoutRes;
        this.locationFrg = locationFrg;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        final View view = inflater.inflate(listLayoutRes, null);

        //getting the selected item
        final LocationItem locationItem = locationItems.get(position);

        //load items
        final TextView name = view.findViewById(R.id.location_name);
        TextView time = view.findViewById(R.id.location_time);
        TextView date = view.findViewById(R.id.location_date);

        ImageView btn_google = view.findViewById(R.id.location_googleMaps);
        ImageView btn_wiki = view.findViewById(R.id.location_wikipedia);
        ImageView btn_remove = view.findViewById(R.id.location_cancel);

        //insert inputs
        name.setText(locationItem.getName());
        time.setText(locationItem.getHour());
        date.setText(locationItem.getDate());

        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+ name.getText().toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                mCtx.startActivity(mapIntent);
            }
        });

        btn_wiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/"+name.getText().toString()));
                mCtx.startActivity(browserIntent);
            }
        });

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sql = "DELETE FROM locations WHERE id = ?";
                database.execSQL(sql, new Integer[]{locationItem.getId()});
                locationFrg.RefreshList();
            }
        });

        return view;
    }
}
