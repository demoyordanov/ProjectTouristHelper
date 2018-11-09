package com.packag.yvyor.projecttouristhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EntityAdapter extends ArrayAdapter<EntityItem> {
    Context mCtx;
    List<EntityItem> entityItems;
    SQLiteDatabase database;
    int listLayoutRes;
    private frg_user user;

    public EntityAdapter(@NonNull Context mCtx, frg_user user, int listLayoutRes, List<EntityItem> entityItems, SQLiteDatabase database) {
        super(mCtx, listLayoutRes, entityItems);
        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.entityItems = entityItems;
        this.database = database;
        this.user = user;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        final View view = inflater.inflate(listLayoutRes, null);

        //getting the selected item
        final EntityItem entityItem = entityItems.get(position);

        //get layout itme
        TextView entityName = view.findViewById(R.id.entityName);
        ImageButton entityButton = view.findViewById(R.id.entityDelete);

        //set layout text
        entityName.setText(entityItem.getName());

        entityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Selected: " + entityItem.getName(), Toast.LENGTH_LONG).show();
                MainActivity.currentEntity = entityItem.getId();
            }
        });
        entityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sql2 = "DELETE FROM calltable WHERE entity_id = ?";
                database.execSQL(sql2, new Integer[]{entityItem.getId()});

                String sql3 = "DELETE FROM locations WHERE entity_id = ?";
                database.execSQL(sql3, new Integer[]{entityItem.getId()});

                String sql = "DELETE FROM entities WHERE id = ?";
                database.execSQL(sql, new Integer[]{entityItem.getId()});

                user.RefreshList();
              //  listener.onEntityListener();
            }
        });
        return view;
    }
}
