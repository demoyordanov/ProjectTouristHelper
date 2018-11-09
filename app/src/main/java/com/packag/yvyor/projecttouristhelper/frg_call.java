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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class frg_call extends Fragment implements CallDialog.CallDialogListener{

    SQLiteDatabase entitiesDB;
    ListView callList;
    List<CallItem> callListItems;
    ImageView addCall;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_frg_call,null);
    }

    @Override //same as OnCreate in Activity ; GetActivity, instead of "this" or "Context"
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        callListItems = new ArrayList<>();
        addCall = view.findViewById(R.id.button_callNew);
        callList = view.findViewById(R.id.callList);
        entitiesDB = getActivity().openOrCreateDatabase(MainActivity.nameEntitiesDB,MODE_PRIVATE,null);

        createTable();
        RefreshList();
        addCall.setOnClickListener(new View.OnClickListener() {
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
        CallDialog CallDialogFragment = CallDialog.newInstance("Some Title");
        // SETS the target fragment for use later when sending results
        CallDialogFragment.setTargetFragment(frg_call.this, 300);
        CallDialogFragment.show(fm, "fragment_edit_call");
    }

    private void createTable()
    {
        String sql = "CREATE TABLE IF NOT EXISTS calltable(\n" +
                "id INTEGER NOT NULL CONSTRAINT calltable_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "name varchar(200) NOT NULL,\n" +
                "description varchar(200),\n" +
                "number varchar(200), \n" +
                "entity_id INTEGER\n" +
                ");";

        entitiesDB.execSQL(sql);
    }

    public void RefreshList()
    {
        callListItems.clear();
        String sql = "SELECT * FROM calltable WHERE entity_id =" + MainActivity.currentEntity;
        Cursor cursor = entitiesDB.rawQuery(sql, null);

        if(cursor.moveToFirst())
        {
            do {
                callListItems.add(new CallItem(cursor.getInt(0), cursor.getInt(4), cursor.getString(1),
                                 cursor.getString(2),cursor.getString(3))
                                 );
            }while(cursor.moveToNext());
        }
        CallAdapter CallAdapter = new CallAdapter(getActivity(), this, R.layout.layout_call_item, callListItems, entitiesDB);
        callList.setAdapter(CallAdapter);
    }


    @Override
    public void onFinishEditDialog(String name, String description, String number) {
        String sql = "INSERT INTO calltable \n" +
                "(name, description, number, entity_id)\n" +
                "VALUES \n" +
                "(?, ?, ?, ?);";
        entitiesDB.execSQL(sql, new String[]{name, description, number, Integer.toString(MainActivity.currentEntity)});
        RefreshList();
    }

}
