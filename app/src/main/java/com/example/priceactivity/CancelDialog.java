package com.example.priceactivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class CancelDialog extends DialogFragment {
    Removable form;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        form=(Removable)context;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInst)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String item = getArguments().getString("deleteitem");
        final int keyitem = getArguments().getInt("deleteitemkey");
        return

                builder.setTitle("Confirm")
                        .setMessage("Are you sure in deleting "+item+"?")
                        .setIcon(R.drawable.ic_launcher_background)
                        .setPositiveButton("Delete",  new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                form.Remove(keyitem);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();

    }
}
