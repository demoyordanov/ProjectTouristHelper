package com.packag.yvyor.projecttouristhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CallDialog extends DialogFragment{
    EditText d_callName;
    EditText d_callDescription;
    EditText d_callNumber;
    Button btn_call_accept, btn_call_deny;

    CallDialogListener listener;
    public CallDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    // 1. Defines the listener interface with a method passing back data result.

    public interface CallDialogListener {

        void onFinishEditDialog(String name, String description, String number);

    }

    public static CallDialog newInstance(String title) {
        CallDialog frag = new CallDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflater = getActivity().getLayoutInflater();
        return inflater.inflate(R.layout.dialog_call, null);//container);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        listener = (CallDialogListener) getTargetFragment();
        d_callName = view.findViewById(R.id.dialog_callName);
        d_callDescription = view.findViewById(R.id.dialog_callDescription);
        d_callNumber = view.findViewById(R.id.dialog_callNumber);
        btn_call_accept = view.findViewById(R.id.dialog_call_btn_yes);
        btn_call_deny = view.findViewById(R.id.dialog_call_btn_no);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field

        btn_call_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(d_callName.getText().toString()) && !TextUtils.isEmpty(d_callDescription.getText().toString()) && !TextUtils.isEmpty(d_callNumber.getText().toString())) {
                    listener.onFinishEditDialog(d_callName.getText().toString(), d_callDescription.getText().toString(), d_callNumber.getText().toString());
                    dismiss();
                }
            }
        });

        btn_call_deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

}
