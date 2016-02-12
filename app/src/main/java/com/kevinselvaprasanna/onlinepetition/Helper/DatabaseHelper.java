package com.kevinselvaprasanna.onlinepetition.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.kevinselvaprasanna.onlinepetition.Objects.Pet;

import java.util.ArrayList;



public class DatabaseHelper {

    private static String LOG_TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = Pet.dbname;
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;


    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME,null,DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + Pet.TABLE_NAME + "(name VARCHAR, head VARCHAR, content VARCHAR, up INT, down INT)");
            Log.d("dmydb", "DATABSE CREATED");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL(" DROP TABLE IF EXISTS " + Pet.TABLE_NAME  );
            Log.d("dmydb", "DATABSE dropped");
        }

    }

    public DatabaseHelper(Context c){
        ourContext = c;
    }

    public DatabaseHelper open(){
        ourHelper = new DbHelper (ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public long addPet(ContentValues cv) {
        open();
        long id = ourDatabase.insert(Pet.TABLE_NAME, null, cv);
        Log.d("dmydb","PET ADDED");
        close();
        return id;
    }

    public ArrayList<Pet> getAllPets () {
        open();
        String[] columns = Pet.columns;
        Cursor c = ourDatabase.query(Pet.TABLE_NAME, columns, null, null, null, null, null);
        ArrayList<Pet> arrayList = Pet.getArrayList(c);
        close();
        return arrayList;
    }
    public void support(String head,ContentValues cv){
        open();
        ourDatabase.update(Pet.TABLE_NAME, cv, "head" + " = ?", new String[]{head});
        Log.d("dmydb", "supported");
        close();

    }
    public void deleteTable(){
        open();
        ourDatabase.execSQL("DELETE FROM " + Pet.TABLE_NAME);
        Log.d("dmydb", "Table deletd");
        close();
    }





}