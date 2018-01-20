package com.example.grzegorz.mojekarty;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

/**
 * Created by grzegorz on 27.11.17.
 */

public class DeleteDialog {

    private Context context;

    public DeleteDialog(Context context)
    {
        this.context = context;
    }

    protected void showDialog(int id_delete)
    {
        final Dialog dialog = new Dialog(this.context);

        dialog.setContentView(R.layout.delete_dialog);

        setCancelButtonListener(dialog);

        setDeleteButtonListener(dialog, id_delete);

        dialog.show();
    }

    private void setCancelButtonListener(final Dialog dialog)
    {
        Button button_cancel = (Button) dialog.findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void setDeleteButtonListener(final Dialog dialog, final int id_delete)
    {
        Button button_delete = (Button)dialog.findViewById(R.id.button_delete);
        button_delete.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SqLite sqLite = new SqLite(context);
                sqLite.onDelete(id_delete);

                ControlerCode controlerCode = new ControlerCode(context);
                controlerCode.setActualValue(MainActivity.actualSee.next);

                CodeListTwoWay codeListTwoWay = CodeListTwoWay.getInstance();
                codeListTwoWay.refreshListCode(context);

                dialog.dismiss();
            }
        } );
    }


}
