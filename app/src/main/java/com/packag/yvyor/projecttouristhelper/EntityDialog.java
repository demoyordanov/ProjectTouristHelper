package com.packag.yvyor.projecttouristhelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EntityDialog extends DialogFragment  {
    EditText EntityDialog_name;
    Button EntityDialog_button, EntityDialog_Cancel;
    EntityDialogListener listener;
    public EntityDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    // 1. Defines the listener interface with a method passing back data result.

    public interface EntityDialogListener {

        void onFinishEditDialog(String inputText);

    }

    public static EntityDialog newInstance(String title) {
        EntityDialog frag = new EntityDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflater = getActivity().getLayoutInflater();
        return inflater.inflate(R.layout.dialog_entity, null);//container);
    }



    @Override

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        listener = (EntityDialogListener) getTargetFragment();
        EntityDialog_name = (EditText) view.findViewById(R.id.dialog_entity_username);
        EntityDialog_button = view.findViewById(R.id.dialog_entity_button);
        EntityDialog_Cancel = view.findViewById(R.id.dialog_entity_btn_cancel);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field

        EntityDialog_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(EntityDialog_name.getText().toString()))
                    {
                        listener.onFinishEditDialog(EntityDialog_name.getText().toString());
                        dismiss();
                    }
            }
        });
        EntityDialog_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
//        EntityDialog_name.setOnEditorActionListener(this);

    }

    // Fires whenever the textfield has an action performed

    // In this case, when the "Done" button is pressed

    // REQUIRES a 'soft keyboard' (virtual keyboard)

//    @Override
//    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//        return false;
//    }

}
