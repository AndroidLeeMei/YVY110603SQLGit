package com.example.auser.yvy110603sqlgit;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    EditText fieldName,fieldAddr;
    String strName,strAddr,strInsertSQL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        fieldName=(EditText) findViewById(R.id.edtName);
        fieldAddr=(EditText)findViewById(R.id.edtAddr);




    }
    int cnt=0;
    public void clickAdd(View target){

        SQLiteDatabase db=SQLiteDatabase.openDatabase(DBInfo.DB_FILE,null,SQLiteDatabase.OPEN_READWRITE);
        strName=fieldName.getText().toString();
        strAddr=fieldAddr.getText().toString();
        strInsertSQL="insert into house (name,addr) values('" + strName + "','" + strAddr + "')";
//        strInsertSQL="delete from house where name='name'";
        cnt++;
        db.execSQL(strInsertSQL);
        Log.d("cnt=",cnt+"");

        finish();
    }

    public void clickBack(View target){
        finish();
    }
}
