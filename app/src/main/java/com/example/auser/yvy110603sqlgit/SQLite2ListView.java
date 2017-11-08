package com.example.auser.yvy110603sqlgit;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class SQLite2ListView extends AppCompatActivity {

    ListView listView;
    ArrayList<String> alrShow=new ArrayList();;
    ArrayList<House> boundleList=new ArrayList();;
    ArrayAdapter adapter;

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite2_list_view);
        DBInfo.DB_FILE = getFilesDir() + File.separator + "mydata.sqlite";
//        copyDBfile();//若手機裏無此資料,則進行複製
        db=SQLiteDatabase.openDatabase(DBInfo.DB_FILE,null,SQLiteDatabase.OPEN_READWRITE);

        Cursor c=db.query("house",new String[]{"no","name","addr","price"},null,null,null,null,null);

        if (c.moveToFirst()){
            do{
                alrShow.add(c.getString(0)+ "," + c.getString(1)+ "," + c.getString(2));
                boundleList.add(new House(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3)));
//                Log.d("data",c.getString(0) + "," + c.getString(1)+ "," + c.getString(2));
            }while (c.moveToNext());
        }


        adapter=new ArrayAdapter(SQLite2ListView.this
                ,android.R.layout.simple_list_item_1
                ,alrShow);
        listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this
//                        ,"第" + (position+1) + "項" + activityClass[position].getClass()
//                        ,Toast.LENGTH_SHORT).show();

                //將畫面指向另一個java頁面

                Log.d("boundl=",boundleList.get(position).id+"");
                Log.d("boundl=",boundleList.get(position).username+"");
                Log.d("boundl=",boundleList.get(position).addr+"");
                Log.d("boundl=",boundleList.get(position).price+"");
                Intent intent=new Intent(SQLite2ListView.this,DetailActivity.class);
                intent.putExtra("id",boundleList.get(position).id);
                startActivity(intent);

//                Intent it = new Intent(MainActivity.this, DetailActivity.class);
//                it.putExtra("id", mylist.get(position).id);
//                startActivity(it);
            }
        });

    }
//
//                intent.putExtra("name",boundleList.get(position).username);
//                intent.putExtra("addr",boundleList.get(position).addr);
//                intent.putExtra("price",boundleList.get(position).price);




    public void SelectSQLite(View v){
        alrShow.clear();

//        Cursor c=db.rawQuery("select * from house",null);
        Cursor c=db.query("house",new String[]{"no","name","addr","price"},null,null,null,null,null);

        if (c.moveToFirst()){
            do{
                alrShow.add(c.getString(0)+ "," + c.getString(1)+ "," + c.getString(2));

                Log.d("data",c.getString(0) + "," + c.getString(1)+ "," + c.getString(2));
            }while (c.moveToNext());
        }


        adapter=new ArrayAdapter(SQLite2ListView.this
                ,android.R.layout.simple_list_item_1
                ,alrShow);
        listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("R.id.menu_add=",R.id.menu_add+"");
        if (item.getItemId()==R.id.menu_add){
            Intent it=new Intent(SQLite2ListView.this,AddActivity.class);
            startActivity(it);
            Log.d("item.getItemId()=",item.getItemId()+"");
        }else if (item.getItemId()==R.id.menu_manage) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        alrShow.clear();
        SQLiteDatabase db=SQLiteDatabase.openDatabase(DBInfo.DB_FILE,null,SQLiteDatabase.OPEN_READWRITE);
        Cursor c=db.rawQuery("select * from house",null);
//        Cursor c=db.query("house",new String[]{"no","name","addr","price"},null,null,null,null,null);

        if (c.moveToFirst()){
            do{
                alrShow.add(c.getString(0)+ "," + c.getString(1)+ "," + c.getString(2));

                Log.d("data",c.getString(0) + "," + c.getString(1)+ "," + c.getString(2));
            }while (c.moveToNext());
        }
        adapter.notifyDataSetChanged();
//
    }
}
