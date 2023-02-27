package com.example.m214_retrofit_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "Movie_BDD", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String req = "CREATE TABLE Movie ( ID INTEGER PRIMARY KEY , Name TEXT,PRIX float, IMAGE text)";
        db.execSQL(req);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Movie");

        onCreate(db);

    }

    public List<Movie> get_Movies() {

        SQLiteDatabase db = this.getWritableDatabase();

        List<Movie> movies = new ArrayList<>();

        Cursor cur = db.rawQuery("SELECT * FROM Movie",null);
        if(cur.moveToFirst()) {
            do {
                Movie m = new Movie();

               // m.ID=cur.getInt(0);
               // p.NOM= cur.getString(1);
                m.setId(cur.getInt(0));
                m.setName(cur.getString(1));
                m.setImage(cur.getString(3));
                //p.PRIX= cur.getFloat(2);



                movies.add(m);
            } while (cur.moveToNext());
        }

        return movies;
    }

    public boolean search(int id) {

        List<Movie> movies = get_Movies();
        boolean exist = false;
        for(Movie m : movies) {

            if(m.getId()==id) exist=true;

        }
        return exist;
    }

    public void clear_All() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS Movie");
        onCreate(db);
    }

    public long add_Movie(Movie m) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("ID", m.getId());

        values.put("Name", m.getName());

        values.put("PRIX",500);

        values.put("IMAGE",m.getImage());

        // Inserting Row
        long r = db.insert("Movie", null, values);

        // Closing database connection
        db.close();

        return r;

    }

}
