package com.example.grzegorz.mojekarty;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqLite extends SQLiteOpenHelper{
    private static final String table_codes = "codes";

    public static final String column_id = "id";
    public static final String column_code = "code";
    public static final String column_name = "name";

    public SqLite(Context context)
    {
        super(context, table_codes + ".db", null, 4);
//        SQLiteDatabase db = getWritableDatabase();
//        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
//        db.execSQL("DROP TABLE IF EXISTS " + table_codes);
        db.execSQL("CREATE TABLE codes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "code TEXT NOT NULL," +
                "name TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + table_codes);
    }

    public void onInsert(String code, String name)
    {
        SQLiteDatabase db = getWritableDatabase();
//        onCreate(db);
//        Log.e("code", code);
//        Log.e("name", name);
        ContentValues values = new ContentValues();
//        values.put(this.column_id, 1);
        values.put(this.column_code, code);
        values.put(this.column_name, name);
        db.insertOrThrow(this.table_codes, null, values);
    }

    public void onUpdate(int id, String code, String name)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(this.column_code, code);
        values.put(this.column_name, name);

        Log.e(this.column_code, code);
        Log.e(this.column_name, name);

        db.update(this.table_codes, values, this.column_id + " = " + id, null);

        db.close();
    }

    public void onDelete(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(this.table_codes, this.column_id + " = " + id, null);
    }

    public Cursor getAllCode()
    {
        String[] columns = {this.column_id, this.column_code, this.column_name};
        SQLiteDatabase db = getReadableDatabase();
        return db.query(this.table_codes, columns, null, null, null, null, null);
    }

    public Cursor getCode(int id)
    {
        String[] columns = {this.column_code};
        SQLiteDatabase db = getReadableDatabase();
        return db.query(this.table_codes, columns, this.column_id + "=" + id, null, null, null, null);
    }
}
