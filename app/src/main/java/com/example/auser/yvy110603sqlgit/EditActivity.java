package com.example.auser.yvy110603sqlgit;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    EditText ed3, ed4;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ed3 = (EditText) findViewById(R.id.edtName);
        ed4 = (EditText) findViewById(R.id.edtAddr);
        Intent it = getIntent();
        id = it.getIntExtra("id", -1);
        Log.d("DATA", "id:" + id);
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DBInfo.DB_FILE, null, SQLiteDatabase.OPEN_READWRITE);
        Cursor c = db.query("house", new String[] {"no", "name", "addr"}, "no=?", new String[] {String.valueOf(id)}, null, null, null);
        if (c.moveToFirst())
        {
            ed3.setText(c.getString(1));
            ed4.setText(c.getString(2));
        }
    }
    public void clickUpdate(View v)
    {
        Log.d("id=",String.valueOf(id));
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DBInfo.DB_FILE, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues cv = new ContentValues();
        cv.put("name", ed3.getText().toString());
        cv.put("addr", ed4.getText().toString());
        db.update("house", cv, "no=?", new String[] {String.valueOf(id)});
        db.close();
        finish();
    }
    public void clickBack(View v)
    {
        finish();
    }
}
