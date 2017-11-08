package com.example.auser.yvy110603sqlgit;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    int fieldId,fieldPrice;
    String fieldName,fieldAddr;
    EditText edtName,edtPrice,edtAddr;
    TextView txtId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txtId=(TextView)findViewById(R.id.txtId);
        edtAddr=(EditText)findViewById(R.id.edtAddr);
        edtPrice=(EditText)findViewById(R.id.edtPrice);
        edtName=(EditText)findViewById(R.id.edtName);



        Intent it=getIntent();
        Log.d("LogfieldId",it.getIntExtra("id",12)+"");
//        id = it.getIntExtra("id", -1);
        fieldId=it.getIntExtra("id",-1);//若無資料則傳回-1
        fieldPrice=it.getIntExtra("price",-1);
        fieldName=it.getStringExtra("username");
        fieldAddr=it.getStringExtra("addr");
        Log.d("LogfieldId",fieldId+"");
        Log.d("LogfieldPrice",fieldPrice+"");
//        Log.d("fieldName",fieldName);
//        Log.d("fieldAddr",fieldAddr);
//        txtId.setText(fieldId+"");
//        edtAddr.setText(fieldAddr+"");
//        edtPrice.setText(fieldPrice+"");
//        edtName.setText(fieldName+"");


    }

    @Override
    protected void onResume() {
        super.onResume();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DBInfo.DB_FILE, null, SQLiteDatabase.OPEN_READWRITE);
        Cursor c = db.query("house", new String[] {"no", "name", "addr"}, "no=?", new String[] {String.valueOf(fieldId)}, null, null, null);
        if (c.moveToFirst())
        {
            txtId.setText(String.valueOf(c.getInt(0)));
            edtName.setText(c.getString(1));
            edtAddr.setText(c.getString(2));
        }
    }

    public void clickBack(View v)
    {
        finish();
    }
    public void clickDelete(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        builder.setTitle("刪除確認");
        builder.setMessage("請確認刪除");
        builder.setPositiveButton("刪除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SQLiteDatabase db = SQLiteDatabase.openDatabase(DBInfo.DB_FILE, null, SQLiteDatabase.OPEN_READWRITE);
                db.delete("house", "no=?", new String[] {String.valueOf(fieldId)});
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    public void clickEdit(View v)
    {
        Intent it = new Intent(this, EditActivity.class);
        it.putExtra("id", fieldId);
        startActivity(it);
    }
}
