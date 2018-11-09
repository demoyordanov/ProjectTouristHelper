package com.packag.yvyor.projecttouristhelper;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class LocationDialog extends DialogFragment {
    EditText d_locationName;
    TimePicker d_timePicker;
    DatePicker d_datePicker;
    Button d_locationAccept;
    Button d_locationCancel;
    LocationDialogListener listener;

    public LocationDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    // 1. Defines the listener interface with a method passing back data result.

    public interface LocationDialogListener {

        void onLocationDialog(String name, String time, String date);

    }

    public static LocationDialog newInstance(String title) {
        LocationDialog frag = new LocationDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflater = getActivity().getLayoutInflater();
        return inflater.inflate(R.layout.dialog_location, null);//container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        listener = (LocationDialogListener) getTargetFragment();
        d_locationName = view.findViewById(R.id.d_location_name);
        d_timePicker = view.findViewById(R.id.d_location_timePicker);
        d_timePicker.setIs24HourView(true);
        d_datePicker = view.findViewById(R.id.d_location_datePicker);
        d_locationAccept = view.findViewById(R.id.d_location_btn_accept);
        d_locationCancel = view.findViewById(R.id.d_location_btn_reject);

        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field

        d_locationAccept.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(d_locationName.getText().toString()))

                {
                    listener.onLocationDialog(
                            d_locationName.getText().toString(),
                            d_timePicker.getHour() + ":" + d_timePicker.getMinute(),
                            d_datePicker.getDayOfMonth() + "/" + d_datePicker.getMonth());
                    //  listener.onLocationDialog("Boo","11:30","09/11/2007");
                    dismiss();

                }
            }
        });

        d_locationCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

}
