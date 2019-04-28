package com.example.bookseyes.handlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bookseyes.entities.Livre;
import com.example.bookseyes.entities.Proverbe;

public class DBHandler extends SQLiteOpenHelper {

    //information of database

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "livresDB.db";

    public static final String TABLE_NAME = "Livre";

    public static final String COLUMN_ID = "LivreID";


    public static final String COLUMN_TITRE = "LivreTitre";


    public static final String COLUMN_AUTEUR = "LivreAuteur";

    //initialize the database

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID +

                " INTEGER PRIMARY KEY AUTOINCREMENT ,"+ COLUMN_TITRE +

                " TEXT,"+ COLUMN_AUTEUR + " TEXT )";
        db.execSQL(CREATE_TABLE);

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}

    public Cursor loadHandler() {
        String result = "";

        String query = "Select * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public boolean addHandler(Livre livre) {

        ContentValues values = new ContentValues();

        values.put(COLUMN_TITRE, livre.getTitre());

        values.put(COLUMN_AUTEUR, livre.getAuteur());

        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.insert(TABLE_NAME, null, values);

        db.close();
        if (result == -1){
            return false ;
        }else{
            return true ;
        }
    }

    public Livre findHandler(int bookID) {


        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "= '" + bookID + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Livre livre = new Livre();

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();

            livre.setId(Integer.parseInt(cursor.getString(0)));

            livre.setTitre(cursor.getString(1));

            livre.setAuteur(cursor.getString(2));

            cursor.close();

        } else {

            livre = null;

        }

        db.close();

        return livre;
    }

    public boolean deleteHandler(int ID) {
        boolean result = false;

        String query = "Select * FROM " + TABLE_NAME + " WHERE  " + COLUMN_ID + "= '" + String.valueOf(ID) + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Livre livre = new Livre();

        if (cursor.moveToFirst()) {

            livre.setId(Integer.parseInt(cursor.getString(0)));

            db.delete(TABLE_NAME, COLUMN_ID + "=?",

                    new String[] {

                String.valueOf(livre.getId())

            });

            cursor.close();

            result = true;

        }

        db.close();

        return result;
    }

    public boolean updateHandler(int ID, String titre, String auteur) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(COLUMN_ID, ID);

        args.put(COLUMN_TITRE, titre);

        args.put(COLUMN_AUTEUR, auteur);

        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null) > 0;
    }
}
