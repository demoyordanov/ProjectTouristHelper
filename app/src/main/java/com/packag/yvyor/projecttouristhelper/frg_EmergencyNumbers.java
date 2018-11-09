package com.packag.yvyor.projecttouristhelper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class frg_EmergencyNumbers extends Fragment {

    private ArrayList<CountryItem> countryItems;
    private CountryAdapter adapter;
    TextView  policeNumber, ambulanceNumber, fireNumber;
    ImageView policeBtn, ambulanceBtn, fireBtn;
    private Spinner spinner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_frg_emergency_numbers,null);
    }

    @Override //same as OnCreate in Activity ; GetActivity, instead of "this" or "Context"
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        policeNumber = view.findViewById(R.id.emergencyNumbers_Police);
        ambulanceNumber = view.findViewById(R.id.emergencyNumbers_Ambulance);
        fireNumber = view.findViewById(R.id.emergencyNumbers_FireDepartment);
        policeBtn = view.findViewById(R.id.emergencyNumbers_btn_police);
        ambulanceBtn = view.findViewById(R.id.emergencyNumbers_btn_ambulance);
        fireBtn = view.findViewById(R.id.emergencyNumbers_btn_fire);
        populateList();

        spinner = view.findViewById(R.id.countryNumberSpinner);
        adapter = new CountryAdapter(getActivity(), countryItems);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CountryItem clickedItem = (CountryItem) parent.getItemAtPosition(position);
                String clickedCountryPolice = Integer.toString(clickedItem.getPoliceNumber());
                String clickedCountryHealth = Integer.toString(clickedItem.getHealthNumber());
                String clickedCountryFire = Integer.toString(clickedItem.getFireDepartmentNumber());

                policeNumber.setText(clickedCountryPolice);
                ambulanceNumber.setText(clickedCountryHealth);
                fireNumber.setText(clickedCountryFire);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        policeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri call = Uri.parse("tel:" + policeNumber.getText());
                Intent surf = new Intent(Intent.ACTION_DIAL, call);
                startActivity(surf);
            }
        });

        ambulanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri call = Uri.parse("tel:" + ambulanceNumber.getText());
                Intent surf = new Intent(Intent.ACTION_DIAL, call);
                startActivity(surf);
            }
        });

        fireBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri call = Uri.parse("tel:" + fireNumber.getText());
                Intent surf = new Intent(Intent.ACTION_DIAL, call);
                startActivity(surf);
            }
        });

    }

    private void populateList()
    {
        countryItems = new ArrayList<>();
        countryItems.add(new CountryItem("Albania", "Euro", R.drawable.flag_albania, 129, 127,128));
        countryItems.add(new CountryItem("Austria", "Euro", R.drawable.flag_austria, 133, 144,122));
        countryItems.add(new CountryItem("Belgium", "Euro", R.drawable.flag_belgium, 101, 112,100));
        countryItems.add(new CountryItem("Bulgaria", "Euro", R.drawable.flag_bulgaria, 166, 150,160));
        countryItems.add(new CountryItem("Croatia", "Euro", R.drawable.flag_croatia, 192, 194,193));
        countryItems.add(new CountryItem("Cyprus", "Euro", R.drawable.flag_cyrus, 112, 112,112));
        countryItems.add(new CountryItem("Czech Republic", "Euro", R.drawable.flag_czech_republic, 158, 155,150));
        countryItems.add(new CountryItem("Denmark", "Euro", R.drawable.flag_denmark, 112, 112,112));
        countryItems.add(new CountryItem("Estonia", "Euro", R.drawable.flag_estonia, 112, 112,112));
        countryItems.add(new CountryItem("Finland", "Euro", R.drawable.flag_finland, 112, 112,112));
        countryItems.add(new CountryItem("France", "Euro", R.drawable.flag_france, 17, 15,18));
        countryItems.add(new CountryItem("Germany", "Euro", R.drawable.flag_germany, 110, 112,112));
        countryItems.add(new CountryItem("Gibraltar", "Euro", R.drawable.flag_united_kingdom, 199, 190,190));
        countryItems.add(new CountryItem("Greece", "Euro", R.drawable.flag_greece, 100, 166,199));
        countryItems.add(new CountryItem("Hungary", "Euro", R.drawable.flag_hungary, 107, 104,105));
        countryItems.add(new CountryItem("Iceland", "Euro", R.drawable.flag_iceland, 112, 112,112));
        countryItems.add(new CountryItem("Ireland", "Euro", R.drawable.flag_ireland, 112, 112,112));
        countryItems.add(new CountryItem("Isle of Man", "Euro", R.drawable.right, 112, 112,112));
        countryItems.add(new CountryItem("Italy", "Euro", R.drawable.flag_italy, 113, 118,115));
        countryItems.add(new CountryItem("Jersey", "Euro", R.drawable.right, 112, 112,112));
        countryItems.add(new CountryItem("Latvia", "Euro", R.drawable.flag_latvia, 110, 113,114));
        countryItems.add(new CountryItem("Lithuania", "Euro" ,R.drawable.right, 112, 112,112));
        countryItems.add(new CountryItem("Lichtenstein", "Euro", R.drawable.left, 117, 144,118));
        countryItems.add(new CountryItem("Luxembourg", "Euro", R.drawable.flag_luxembourg, 113, 112,112));
        countryItems.add(new CountryItem("Malta", "Euro", R.drawable.left, 113, 112,112));
        countryItems.add(new CountryItem("Monaco", "Euro", R.drawable.right, 17, 15,18));
        countryItems.add(new CountryItem("Netherlands", "Euro", R.drawable.flag_the_netherlands, 113, 112,112));
        countryItems.add(new CountryItem("Norway", "Euro", R.drawable.flag_norway, 112, 113,110));
        countryItems.add(new CountryItem("Poland", "Euro", R.drawable.flag_poland, 997, 999,998));
        countryItems.add(new CountryItem("Romania", "Euro", R.drawable.flag_romania, 113, 112,112));
        countryItems.add(new CountryItem("San Marino", "Euro", R.drawable.left, 113, 118,115));
        countryItems.add(new CountryItem("Slovakia", "Euro", R.drawable.flag_slovakia, 158, 155,150));
        countryItems.add(new CountryItem("Slovenia", "Euro", R.drawable.flag_slovenia, 113, 112,112));
        countryItems.add(new CountryItem("Spain", "Euro", R.drawable.flag_spain, 112, 112,112));
        countryItems.add(new CountryItem("Sweden", "Euro", R.drawable.flag_sweden, 112, 112,112));
        countryItems.add(new CountryItem("Switzerland", "Euro", R.drawable.flag_switzerland, 117, 144,118));
        countryItems.add(new CountryItem("Turkey", "Euro", R.drawable.flag_turkey, 155, 112,110));
        countryItems.add(new CountryItem("United Kingdom", "Euro", R.drawable.flag_united_kingdom, 112, 112,112));
        countryItems.add(new CountryItem("Vatican", "Euro", R.drawable.left, 113, 118,115));

    }
}
