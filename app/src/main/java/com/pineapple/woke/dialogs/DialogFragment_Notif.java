package com.pineapple.woke.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.pineapple.woke.R;
import com.pineapple.woke.resources.Singleton;

public class DialogFragment_Notif extends DialogFragment {

    public DialogFragment_Notif() {
        // Empty constructor required for DialogFragment
    }

    public static DialogFragment_Notif newInstance(String title, String message, String type) {
        DialogFragment_Notif frag = new DialogFragment_Notif();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        args.putString("type", type);
        if(type.equals("notify")){
            args.putString("button", "I'm awake");
        }
        else if(type.equals("alarm")){
            args.putString("button", "Dismiss");
        }

        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        String message = getArguments().getString("message");
        String button = getArguments().getString("button");

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);

        // Add the buttons
        alertDialogBuilder.setPositiveButton(button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Singleton.getInstance().getCurrUser().getCurrStudySession().dismissWokeNotification();
                DialogFragment_Notif.this.getDialog().dismiss();
            }
        });
        return alertDialogBuilder.create();
    }


}
