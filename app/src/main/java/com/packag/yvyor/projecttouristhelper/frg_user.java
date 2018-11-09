package com.packag.yvyor.projecttouristhelper;

import android.content.Entity;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class frg_user extends Fragment implements EntityDialog.EntityDialogListener {

    SQLiteDatabase entitiesDB;
    ListView entityList;
    List<EntityItem> entityListItems;
    ImageButton addEntity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_frg_user,null);

    }



    @Override //same as OnCreate in Activity ; GetActivity, instead of "this" or "Context"
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        entityListItems = new ArrayList<>();
        entityList = view.findViewById(R.id.entriesList);
        addEntity = view.findViewById(R.id.btnAdd);
        entitiesDB = getActivity().openOrCreateDatabase(MainActivity.nameEntitiesDB,MODE_PRIVATE,null);

        createTable();

        RefreshList();

        addEntity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    addEntity();
                openDialog();
            }
        });
    }

    private void openDialog(){
        FragmentManager fm = getFragmentManager();
        EntityDialog DialogFragment = EntityDialog.newInstance("Add Entity");
        DialogFragment.setTargetFragment(frg_user.this, 300);
        DialogFragment.show(fm, "fragment_new_entity");
    }

    private void createTable()
    {
        String sql = "CREATE TABLE IF NOT EXISTS entities(\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "name varchar(200) NOT NULL\n" +
                ");";

        entitiesDB.execSQL(sql);
    }

    public final void removeItem(EntityItem entity)
    {
        String sql = "DELETE FROM employees WHERE id = ?";
        entitiesDB.execSQL(sql, new Integer[]{entity.getId()});
        RefreshList();
    }

    public void RefreshList()
    {
        entityListItems.clear();
        String sql = "SELECT * FROM entities";
        Cursor cursor = entitiesDB.rawQuery(sql, null);

        if(cursor.moveToFirst())
        {
            do {
                entityListItems.add(new EntityItem(cursor.getInt(0), cursor.getString(1))
                );
            }while(cursor.moveToNext());
        }
        EntityAdapter entityAdapter = new EntityAdapter(getActivity(), this, R.layout.layout_entity, entityListItems, entitiesDB);
        entityList.setAdapter(entityAdapter);
    }

    @Override
    public void onFinishEditDialog(String inputText) {

        String sql = "INSERT INTO entities \n" +
                "(name)\n" +
                "VALUES \n" +
                "(?);";
        entitiesDB.execSQL(sql, new String[]{inputText});
        RefreshList();
    }

}
