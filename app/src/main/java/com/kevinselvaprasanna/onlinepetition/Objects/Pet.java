package com.kevinselvaprasanna.onlinepetition.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.kevinselvaprasanna.onlinepetition.Helper.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by kevinselvaprasanna on 10/2/16.
 */
public class Pet {
    public String name, head,content;
    public static String dbname = "Onlinepetition";
    public static String TABLE_NAME = "pets";
    public static String[] columns = {"name", "head","content","up","down"};
    public int up,down;
    public Pet(String name,String head,String content, int up, int down){
        this.name = name;
        this.head = head;
        this.content = content;
        this.up = up;
        this.down = down;
    }
    public static ArrayList<Pet> getArrayList(Cursor c) {
        ArrayList<Pet> arrayList = new ArrayList<>();
        //Gson gson = new Gson();
        while (c.moveToNext()) {
            arrayList.add(parseNot(c));
        }
        return arrayList;
    }

    public static Pet parseNot(Cursor c) {
        //Gson gson = new Gson();
        Pet pet = new Pet(c.getString(0), c.getString(1), c.getString(2), c.getInt(3),c.getInt(4));
        return pet;
    }

    public static ArrayList<Pet> getAllPets(Context context){
        DatabaseHelper data = new DatabaseHelper(context);
        return data.getAllPets();
    }
}
