package org.mobile.bookseyes.handlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.mobile.bookseyes.entities.Livre;
import org.mobile.bookseyes.entities.Proverbe;

public class DBHandlerProverbs extends SQLiteOpenHelper {

    //information of database

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "proverbesDB.db";

    public static final String TABLE_NAME = "Proverbe";

    public static final String COLUMN_ID = "ProverbeID";

    public static final String COLUMN_TITRE = "ProverbeTitre";

    public static final String COLUMN_TEXTE = "ProverbeContenu";

    public static final String COLUMN_PAGE = "ProverbePage";

    public static final String COLUMN_BOOK_ID = "ProverbeBookID";

    //initialize the database

    public DBHandlerProverbs(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID +

                " INTEGER PRIMARY KEY AUTOINCREMENT ,"+ COLUMN_TITRE +

                " TEXT,"+ COLUMN_TEXTE +

                " TEXT,"+ COLUMN_PAGE +

                " TEXT,"+ COLUMN_BOOK_ID + " TEXT )";
        db.execSQL(CREATE_TABLE);
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}

    public Cursor loadHandler(int bookID) {
        String result = "";

        String query = "Select * FROM " + TABLE_NAME + " WHERE  " + COLUMN_BOOK_ID + "= '" + String.valueOf(bookID) + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public boolean addHandler(int bookID, Proverbe proverbe) {

        ContentValues values = new ContentValues();

        values.put(COLUMN_TITRE, proverbe.getTitre());

        values.put(COLUMN_TEXTE, proverbe.getContenu());

        values.put(COLUMN_PAGE, proverbe.getPage());

        values.put(COLUMN_BOOK_ID, bookID);

        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.insert(TABLE_NAME, null, values);

        db.close();
        if (result == -1){
            return false ;
        }else{
            return true ;
        }
    }

    public Livre findHandler(String studentname) {


        Livre student = new Livre();


        return student;
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

//    public boolean updateHandler(int ID, String titre, String auteur) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues args = new ContentValues();
//
//        args.put(COLUMN_ID, ID);
//
//        args.put(COLUMN_TITRE, titre);
//
//        args.put(COLUMN_AUTEUR, auteur);
//
//        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null) > 0;
//    }
}
