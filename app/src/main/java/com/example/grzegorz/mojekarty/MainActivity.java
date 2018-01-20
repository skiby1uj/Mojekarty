package com.example.grzegorz.mojekarty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    static Row actualSee;
    GestureDetector gestureDetector;
    CodeListTwoWay codeListTwoWay;
    ControlerCode controlerCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.codeListTwoWay = CodeListTwoWay.getInstance();
        this.codeListTwoWay.refreshListCode(this);

        this.controlerCode = new ControlerCode(this);
        this.controlerCode.setActualValue(this.codeListTwoWay.head);

        gestureDetector = new GestureDetector(MainActivity.this, MainActivity.this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if ( id == R.id.menu_settings_add )
        {
            InsertDialog insertDialog = new InsertDialog(this);
            insertDialog.showDialog();
            return true;
        }
        else if ( id == R.id.menu_settings_edit )
        {
            EditDialog editDialog = new EditDialog(this);
            editDialog.showDialog(actualSee.id, actualSee.name, actualSee.code);
            return true;
        }
        else if ( id == R.id.menu_settings_delete )
        {
            DeleteDialog deleteDialog = new DeleteDialog(this);
            deleteDialog.showDialog(actualSee.id);
        }
        return false;
    }

    @Override
    public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float X, float Y) {

        if(motionEvent1.getY() - motionEvent2.getY() > 50){

            Toast.makeText(MainActivity.this , " Swipe Up " , Toast.LENGTH_LONG).show();

            return true;
        }

        if(motionEvent2.getY() - motionEvent1.getY() > 50){

            Toast.makeText(MainActivity.this , " Swipe Down " , Toast.LENGTH_LONG).show();

            return true;
        }

        if(motionEvent1.getX() - motionEvent2.getX() > 50){

            this.controlerCode.setActualValue(this.actualSee.next);
            Toast.makeText(MainActivity.this , " Swipe Left " , Toast.LENGTH_LONG).show();

            return true;
        }

        if(motionEvent2.getX() - motionEvent1.getX() > 50) {

            this.controlerCode.setActualValue(this.actualSee.prev);
            Toast.makeText(MainActivity.this, " Swipe Right ", Toast.LENGTH_LONG).show();

            return true;
        }
        else {

            return true ;
        }
    }

    public void button_previous(View v)
    {
        this.controlerCode.setActualValue(this.actualSee.prev);
    }

    public void button_next(View v)
    {
        this.controlerCode.setActualValue(this.actualSee.next);
    }

    @Override
    public void onLongPress(MotionEvent arg0) {

        // TODO Auto-generated method stub
        EditDialog editDialog = new EditDialog(this);
        editDialog.showDialog(actualSee.id, actualSee.name, actualSee.code);

    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {

        // TODO Auto-generated method stub

        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {

        // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {

        // TODO Auto-generated method stub

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        // TODO Auto-generated method stub

        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent arg0) {

        // TODO Auto-generated method stub

        return false;
    }
}
