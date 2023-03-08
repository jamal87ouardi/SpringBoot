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
        super(context, "Book_BDD", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String req = "CREATE TABLE Livre ( REF INTEGER PRIMARY KEY , TITRE TEXT, IMG TEXT)";
        db.execSQL(req);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Livre");

        onCreate(db);

    }

    public List<Book> get_Books() {

        SQLiteDatabase db = this.getWritableDatabase();
        List<Book> books = new ArrayList<>();

        Cursor cur = db.rawQuery("SELECT * FROM Livre",null);
        if(cur.moveToFirst()) {
            do {
                Book m = new Book();
                m.setRef(cur.getInt(0));
                m.setTitre(cur.getString(1));
                m.setImg(cur.getString(2));
                books.add(m);
            } while (cur.moveToNext());
        }
        return books;
    }

    public boolean search(int ref) {

        List<Book> books = get_Books();
        boolean exist = false;
        for(Book b : books) {

            if(b.getRef()==ref) exist=true;

        }
        return exist;
    }

    public void clear_All() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS Livre");
        onCreate(db);
    }

    public long add_Book(Book b) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("REF", b.getRef());
        values.put("TITRE", b.getTitre());
        values.put("IMG",b.getImg());

        long r = db.insert("Livre", null, values);
        db.close();
        return r;
    }

}
