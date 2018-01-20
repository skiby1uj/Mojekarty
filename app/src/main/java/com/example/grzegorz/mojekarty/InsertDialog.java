package com.example.grzegorz.mojekarty;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by grzegorz on 21.11.17.
 */

public class InsertDialog {

    private final Context context;

    public InsertDialog(Context context)
    {
        this.context = context;
    }

    protected void showDialog()
    {
        final Dialog dialog = new Dialog(this.context);

        dialog.setContentView(R.layout.insert_dialog);
        dialog.setTitle(R.string.dialog_add_cart);

        setCancelButtonListener(dialog);

        setSaveButtonListener(dialog);

        dialog.show();
    }

    private boolean setSaveButtonListener(final Dialog dialog)
    {
        Button button_add = (Button) dialog.findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SqLite sqLite = new SqLite(context);

                EditText name = (EditText)dialog.findViewById(R.id.edit_name_dialog);
                EditText code = (EditText)dialog.findViewById(R.id.edit_code_dialog);

                if ( !name.getText().toString().trim().equals("") && !code.getText().toString().trim().equals("") && (code.length() == 13 || code.length() == 7 ) )
                {
                    Toast.makeText(context, "Oba ", Toast.LENGTH_LONG).show();
                    sqLite.onInsert( code.getText().toString(), name.getText().toString());

                    CodeListTwoWay codeListTwoWay = CodeListTwoWay.getInstance();
                    codeListTwoWay.refreshListCode(context);

                    dialog.dismiss();
                }
                else
                {
                    Validation validation = new Validation(dialog);
                    if ( name.getText().toString().trim().equals("") )
                    {
                        validation.setValidatorToEditName();
                    }
                    if ( !(code.length() == 13 || code.length() == 7)  )
                    {
                        validation.setValidatorToEditCode();
                    }
                }
            }
        });
        return true;
    }

    private void setCancelButtonListener(final Dialog dialog){
        Button button_cancel = (Button) dialog.findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
