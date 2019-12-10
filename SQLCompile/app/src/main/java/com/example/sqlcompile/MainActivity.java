package com.example.sqlcompile;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        StudentDatabaseHelper dbHelper = new StudentDatabaseHelper(this.getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String insert1 = "INSERT INTO student1(name) values('John')";
        db.execSQL(insert1);
        db.execSQL("INSERT INTO student1(name) values('Laye')");
        ContentValues cv = new ContentValues();
        cv.put("name","Cris");
        cv.put("name","OuZy");
        db.insert("student1",null,cv);

        //select and print the content of student1 using log.i - select * from student1
        Cursor cur = db.query("student1", null,null,null,null,null,null);
        cur.moveToFirst();
        while (!cur.isAfterLast()){
            Log.i("MainActivity","id "+cur.getInt(0)+ " name "+ cur.getString(0));
            cur.moveToNext();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
