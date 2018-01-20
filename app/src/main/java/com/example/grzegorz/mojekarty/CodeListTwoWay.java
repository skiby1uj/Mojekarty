package com.example.grzegorz.mojekarty;

import android.content.Context;
import android.database.Cursor;

class Row
{
    public int id;
    public String name;
    public String code;
    public Row next;
    public Row prev;

    public Row(int id, String name, String code)
    {
        this.id = id;
        this.name = name;
        this.code = code;
        this.next = null;
        this.prev = null;
    }

    public void setRelation(Row next, Row prev)
    {
        this.next = next;
        this.prev = prev;
    }
}

public class CodeListTwoWay {

    Row head;
    Row end;
    private static CodeListTwoWay instance;

    public static CodeListTwoWay getInstance()
    {
        if ( instance == null )
        {
            instance = new CodeListTwoWay();
            return instance;
        }
        else
            return instance;

    }

    private CodeListTwoWay()
    {
        this.head = null;
    }

    public void add(int id, String name, String code)
    {
        Row row = new Row(id, name, code);

        if ( MainActivity.actualSee != null && MainActivity.actualSee.id == id )
            MainActivity.actualSee = row;

        if ( this.head == null )
        {
            row.setRelation(row, row);
            this.head = row;
            this.end = row;
        }
        else
        {
            row.setRelation(head, end);
            this.end.next = row;
            this.end = row;
            this.head.prev = this.end;
        }
    }

    public void printAll()
    {
        if ( this.head != null )
        {
            Row copyRow = this.head;
            do
            {
                copyRow = copyRow.next;
            }
            while (this.head != copyRow);
        }
    }

    public CodeListTwoWay refreshListCode(Context context)
    {
        SqLite sqLite = new SqLite(context);
        Cursor cursor = sqLite.getAllCode();
        this.head = null;
        this.end = null;

        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String code = cursor.getString(1);
            String name = cursor.getString(2);
            this.add(id, name, code);
        }

        return this;
    }
}
