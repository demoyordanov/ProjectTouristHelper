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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CallAdapter extends ArrayAdapter<CallItem>{
    Context mCtx;
    List<CallItem> callItems;
    SQLiteDatabase database;
    frg_call callFrg;
    int listLayoutRes;

    public CallAdapter(@NonNull Context mCtx, frg_call callFrg, int listLayoutRes, List<CallItem> callItems, SQLiteDatabase database) {
        super(mCtx,listLayoutRes,callItems);
        this.mCtx = mCtx;
        this.callItems = callItems;
        this.database = database;
        this.listLayoutRes = listLayoutRes;
        this.callFrg = callFrg;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        final View view = inflater.inflate(listLayoutRes, null);

        //getting the selected item
        final CallItem callItem = callItems.get(position);

        //get layout itme
        TextView callName = view.findViewById(R.id.callName);
        TextView callDescription = view.findViewById(R.id.callDescription);

        ImageView callButton = view.findViewById(R.id.btn_StartCall);
        ImageView editButton = view.findViewById(R.id.btn_EditCall);

        //set layout text
        callName.setText(callItem.getCallName());
        callDescription.setText(callItem.getCallDescription());

        callName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), callItem.getCallName() + " " + MainActivity.currentEntity, Toast.LENGTH_LONG).show();
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri call = Uri.parse("tel:" + callItem.getCallNumber());
                Intent surf = new Intent(Intent.ACTION_DIAL, call);
                mCtx.startActivity(surf);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        String sql = "DELETE FROM calltable WHERE id = ?";
                        database.execSQL(sql, new Integer[]{callItem.getId()});
                        callFrg.RefreshList();
            }
        });

        return view;
    }
}