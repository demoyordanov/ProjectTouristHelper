package com.packag.yvyor.projecttouristhelper;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class frg_nearby extends Fragment {
    private String[] types;

    ArrayAdapter<String> adapterTypes;
    ArrayAdapter<String> adapterSubTypes;

    private String[] SleepCamping;
    private String[] ParkingTransportation;
    private String[] Culture;
    private String[] Finance;
    private String[] Entertainment;
    private String[] VehicleMaintnance;
    private String[] Emergency;
    private String[] StoresCare;
    private String[] Food;

    private Spinner spinnerTypes, spinnerSubTypes;
    private ImageView mapButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_frg_nearby,null);
    }



    @Override //same as OnCreate in Activity ; GetActivity, instead of "this" or "Context"
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerTypes = view.findViewById(R.id.spinnerType);
        spinnerSubTypes = view.findViewById(R.id.spinnerSubType);
        fillTypes();
        adapterTypes = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, types);
        spinnerTypes.setAdapter(adapterTypes);

        SleepCamping = new String[]{"lodging", "rv_park", "campground"};
        ParkingTransportation = new String[]{"airport", "transit_station", "car_rental", "gas_station", "park", "parking"};
        Culture = new String[]{"art_gallery", "museum", "library"};
        Finance = new String[]{"atm", "bank"};
        Entertainment = new String[]{"bowling_alley", "casino", "amusement_park", "aquarium", "stadium", "zoo"};
        VehicleMaintnance = new String[]{"bicycle_store", "car_repair", "car_wash"};
        Emergency = new String[]{"doctor", "hospital", "pharmacy", "police"};
        StoresCare = new String[]{"shopping_mall", "department_store", "laundry", "hair_care", "spa", "beauty_salon"};

        Food = new String[]{"bar", "restaurant", "cafe", "night_club", "liquor_store", "supermarket"};

        mapButton = view.findViewById(R.id.mapButton);

        spinnerTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String getItem = spinnerTypes.getSelectedItem().toString();
                if(getItem.equals("Sleep & Camping"))
                {
                    adapterSubTypes = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,SleepCamping);
                    spinnerSubTypes.setAdapter(adapterSubTypes);
                }
                else if(getItem.equals("Parking & Transportation"))
                {
                    adapterSubTypes = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,ParkingTransportation);
                    spinnerSubTypes.setAdapter(adapterSubTypes);
                }

                else if(getItem.equals("Culture"))
                {
                    adapterSubTypes = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,Culture);
                    spinnerSubTypes.setAdapter(adapterSubTypes);
                }

                else if(getItem.equals("Finance"))
                {
                    adapterSubTypes = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,Finance);
                    spinnerSubTypes.setAdapter(adapterSubTypes);
                }

                else if(getItem.equals("Entertainment"))
                {
                    adapterSubTypes = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,Entertainment);
                    spinnerSubTypes.setAdapter(adapterSubTypes);
                }

                else if(getItem.equals("Vehicle Maintnance"))
                {
                    adapterSubTypes = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,VehicleMaintnance);
                    spinnerSubTypes.setAdapter(adapterSubTypes);
                }

                else if(getItem.equals("Emergency"))
                {
                    adapterSubTypes = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,Emergency);
                    spinnerSubTypes.setAdapter(adapterSubTypes);
                }

                else if(getItem.equals("Stores & Care"))
                {
                    adapterSubTypes = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,StoresCare);
                    spinnerSubTypes.setAdapter(adapterSubTypes);
                }

                else if(getItem.equals("Food"))
                {
                    adapterSubTypes = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,Food);
                    spinnerSubTypes.setAdapter(adapterSubTypes);
                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+ spinnerSubTypes.getSelectedItem().toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

    }
        private void fillTypes()
    {
        types  = new String[]{"Sleep & Camping",
        "Parking & Transportation",
        "Culture",
        "Finance",
        "Entertainment",
        "Vehicle Maintnance",
        "Emergency",
        "Stores & Care",
        "Food"};
    }


}
