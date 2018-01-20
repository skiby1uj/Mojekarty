package com.example.grzegorz.mojekarty;

import android.app.Dialog;
import android.graphics.Color;
import android.widget.TextView;

/**
 * Created by grzegorz on 25.11.17.
 */

public class Validation {

    Dialog dialog;

    public Validation(Dialog dialog)
    {
        this.dialog = dialog;
    }

    public void setValidatorToEditName()
    {
        TextView validate_edit_name = (TextView)this.dialog.findViewById(R.id.validate_edit_name);

        validate_edit_name.setText(R.string.validate_required);
        validate_edit_name.setTextColor(Color.RED);
    }

    public void setValidatorToEditCode()
    {
        TextView validate_edit_code = (TextView)dialog.findViewById(R.id.validate_edit_code);

        validate_edit_code.setText(R.string.validate_edit_code);
        validate_edit_code.setTextColor(Color.RED);
    }
}
