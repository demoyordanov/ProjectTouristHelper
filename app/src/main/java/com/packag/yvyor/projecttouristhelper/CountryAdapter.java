package com.packag.yvyor.projecttouristhelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CountryAdapter  extends ArrayAdapter<CountryItem> {

    public CountryAdapter(Context context, ArrayList<CountryItem> countryList)
    {
        super(context,0,countryList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        if(convertView==null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_country_item, parent, false);
        }

        ImageView imageViewFlag = convertView.findViewById(R.id.countryItemFlag);
        TextView textViewName = convertView.findViewById(R.id.countryItemName);

        CountryItem currentItem = getItem(position);

        if(currentItem!=null) {
            imageViewFlag.setImageResource(currentItem.getCountryFlag());
            textViewName.setText(currentItem.getCountryName());
        }
        return convertView;
    }
}
