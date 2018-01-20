package com.example.grzegorz.mojekarty;

import android.app.Dialog;
import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDialog {

    private final Context context;
    private EditText name;
    private EditText code;
    private int id;

    public EditDialog(Context context)
    {
        this.context = context;
    }

    protected void showDialog(int id, String name, String code)
    {
        final Dialog dialog = new Dialog(this.context);

        dialog.setContentView(R.layout.edit_dialog);
//        dialog.setTitle(R.string.dialog_add_cart);

        setCancelButtonListener(dialog);
//
        setSaveButtonListener(dialog);
        this.id = id;
        this.name = (EditText)dialog.findViewById(R.id.edit_name_dialog);
        this.code = (EditText)dialog.findViewById(R.id.edit_code_dialog);

        this.name.setText(name);
        this.code.setText(code);

        Vibrator v = (Vibrator) this.context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(200);

        dialog.show();
    }

    private boolean setSaveButtonListener(final Dialog dialog)
    {
        Button button_add = (Button) dialog.findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SqLite sqLite = new SqLite(context);

                if ( !name.getText().toString().trim().equals("") && !code.getText().toString().trim().equals("") && (code.length() == 13 || code.length() == 7 ) )
                {
                    Toast.makeText(context, "Oba ", Toast.LENGTH_LONG).show();
                    sqLite.onUpdate(id, code.getText().toString().trim(), name.getText().toString().trim() );

                    CodeListTwoWay codeListTwoWay = CodeListTwoWay.getInstance();
                    codeListTwoWay.refreshListCode(context);

                    ControlerCode controlerCode = new ControlerCode(context);
                    controlerCode.setActualValue(MainActivity.actualSee);

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
