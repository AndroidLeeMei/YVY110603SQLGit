package com.example.auser.yvy110603sqlgit;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//mydata.sqlite這個檔案摸擬從某個遊戲網站上手動抓下來貼到raw
public class MainActivity extends AppCompatActivity {
    String DB_FILE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB_FILE = getFilesDir() + File.separator + "mydata.sqlite";
        try {
            File f=new File(DB_FILE);
            Log.d("f.exists()=",f.exists()+"");
            if (!f.exists()){  //先檢查檔案是否己經存在,若己經存在則不重複執行
                Toast.makeText(this,"檔案存在,開始複製",Toast.LENGTH_LONG).show();
                InputStream is=getResources().openRawResource(R.raw.mydata);
                OutputStream os=new FileOutputStream(getFilesDir() + File.separator + "mydata.sqlite");
                int read;
                while ((read=is.read())!=-1) {
                    os.write(read);
                }
                os.close();
                is.close();
            }else {
                Log.d("f.exists()=","else");
                Toast.makeText(MainActivity.this, "檔案己經存在,無需複製", Toast.LENGTH_LONG).show();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void click1(View v){
        SQLiteDatabase db=SQLiteDatabase.openDatabase(DB_FILE,null,SQLiteDatabase.OPEN_READWRITE);
        Cursor c=db.rawQuery("select * from house",null);
        if (c.moveToFirst()){
            do{
                Log.d("data",c.getString(0) + "," + c.getString(1)+ "," + c.getString(2));
            }while (c.moveToNext());
        }

//        int cnt=c.getCount();
//        for (int i=0;i<cnt;i++){
//            c.moveToFirst();
//            Log.d("data",c.getString(0) + "," + c.getString(1)+ "," + c.getString(2));
//        }
//        c.close();
//        c.moveToNext();
//        Log.d("data",c.getString(0) + "," + c.getString(1)+ "," + c.getString(2));
//        Log.d("c.count=" , c.getCount()+"");
//        c.moveToNext();
//        Log.d("data",c.getString(1) + "," + c.getString(2));
    }
}
