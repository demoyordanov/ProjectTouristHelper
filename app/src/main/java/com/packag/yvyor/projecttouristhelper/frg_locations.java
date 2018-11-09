package com.packag.yvyor.projecttouristhelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class frg_locations extends Fragment implements LocationDialog.LocationDialogListener{

    SQLiteDatabase entitiesDB;
    ListView locationList;
    List<LocationItem> locationListItems;
    Button addLocation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_frg_locations,null);
    }

    @Override //same as OnCreate in Activity ; GetActivity, instead of "this" or "Context"
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        locationListItems = new ArrayList<>();
        locationList = view.findViewById(R.id.location_list);
        addLocation = view.findViewById(R.id.location_add);
        entitiesDB = getActivity().openOrCreateDatabase(MainActivity.nameEntitiesDB,MODE_PRIVATE,null);

        createTable();
        RefreshList();
        addLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    private void openDialog(){
        //   EntityDialog entityDialog = new EntityDialog();
        //   entityDialog.show(getActivity().getSupportFragmentManager(),"S H O W");

        FragmentManager fm = getFragmentManager();
        LocationDialog locationDialogFragment = LocationDialog.newInstance("Some Title");
        // SETS the target fragment for use later when sending results
        locationDialogFragment.setTargetFragment(frg_locations.this, 300);
        locationDialogFragment.show(fm, "fragment_edit_name");
    }

    public void RefreshList()
    {
        locationListItems.clear();
        String sql = "SELECT * FROM locations WHERE entity_id="+MainActivity.currentEntity;
        Cursor cursor = entitiesDB.rawQuery(sql, null);

        if(cursor.moveToFirst())
        {
            do {
                locationListItems.add(new LocationItem(cursor.getInt(0), cursor.getInt(4), cursor.getString(1),
                        cursor.getString(2),cursor.getString(3))
                );
            }while(cursor.moveToNext());
        }
        LocationAdapter locationAdapter = new LocationAdapter(getActivity(), this,R.layout.layout_location_item,locationListItems,entitiesDB);
        locationList.setAdapter(locationAdapter);
    }

    private void createTable()
    {
        String sql = "CREATE TABLE IF NOT EXISTS locations(\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "name varchar(200) NOT NULL,\n" +
                "time varchar(200) NOT NULL,\n" +
                "date varchar(200) NOT NULL,\n" +
                "entity_id INTEGER\n" +
                ");";

        entitiesDB.execSQL(sql);
    }

    @Override
    public void onLocationDialog(String name, String time, String date) {
        String sql = "INSERT INTO locations \n" +
                "(name, time, date, entity_id)\n" +
                "VALUES \n" +
                "(?, ?, ?, ?);";
        entitiesDB.execSQL(sql, new String[]{name, time, date, Integer.toString(MainActivity.currentEntity)});
        Toast.makeText(getActivity(),name + " " + time + " " + date, Toast.LENGTH_SHORT);
        RefreshList();
    }


}
