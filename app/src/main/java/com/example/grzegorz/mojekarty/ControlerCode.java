package com.example.grzegorz.mojekarty;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ControlerCode
{
    private Context context;

    public ControlerCode(Context context)
    {
        this.context = context;
    }

    public void setActualValue(Row row)
    {
        if ( row != null )
        {
            MainActivity.actualSee = row;

            TextView textName = (TextView)((Activity)this.context).findViewById(R.id.text_name);
            textName.setText(MainActivity.actualSee.name);

            this.changeShowCode(MainActivity.actualSee.code);
        }
        else
            MainActivity.actualSee = null;
    }

    private void changeShowCode(String code)
    {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(580, 155);

        AndroidBarcodeView yourView = new AndroidBarcodeView(this.context, code);

        LinearLayout linearLayoutCode;
        linearLayoutCode = (LinearLayout) ((Activity)this.context).findViewById(R.id.linear_layout_code);

        linearLayoutCode.removeAllViews();
        linearLayoutCode.addView(yourView, params);
    }
}
