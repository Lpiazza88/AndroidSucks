package com.example.cis183_hw3_piazza;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Currency;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String Database_name = "Employ.db";
    private static final String Table_name = "Employ";

    public DatabaseHelper(Context context){
        super(context, Database_name, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + Table_name + "(username TEXT PRIMARY KEY NOT NULL, firstname TEXT, lastname TEXT, passw TEXT, emails TEXT, ages TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS "+ Table_name);
        onCreate(db);
    }

    public boolean initializeDB()
    {
        if(numberOfRowsInTable()==0){
            SQLiteDatabase db= this.getWritableDatabase();
            db.execSQL("INSERT INTO " + Table_name+" VALUES('Lpiazza','Lee','Piazza', 'badpass', 'lpiazza@my.monroeccc.edu', '34');");
            db.close();
            return true;
        }
        else{
            return false;
        }
    }

    public int numberOfRowsInTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Table_name);

        db.close();

        return numRows;
    }

    @SuppressLint("Range")
    public ArrayList<Employ> getAllRows(){
        ArrayList<Employ> Listemploy = new ArrayList<Employ>();

        String selectQuery = "SELECT * FROM " + Table_name +";";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        String fname;
        String lname;
        String uname;
        String passw;
        String email;
        String age;

        if(cursor.moveToFirst()){
            do{
                uname = cursor.getString(cursor.getColumnIndex("username"));
                fname = cursor.getString(cursor.getColumnIndex("firstname"));
                lname = cursor.getString(cursor.getColumnIndex("lastname"));
                passw = cursor.getString(cursor.getColumnIndex("password"));
                email = cursor.getString(cursor.getColumnIndex("emails"));
                age = cursor.getString(cursor.getColumnIndex("ages"));

                Listemploy.add(new Employ(uname, fname, lname, passw, email, age));
            }
            while(cursor.moveToNext());
        }
        db.close();

        return Listemploy;
    }

    public void addNewEmploy(Employ u){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO "+Table_name+" VALUES ('" + u.getUname()+ "','" + u.getFname()+ "','" + u.getLname()+ "','" + u.getPassw()+ "','" + u.getEmail()+ "','" + u.getAge()+ "', );");

        db.close();
    }

    public void EmpFired(String uName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + Table_name + " WHERE username = '"+uName+"';");

        db.close();
    }

    public void EmpChange(Employ u){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + Table_name + " SET firstname = '"+u.getFname()+"', lastname = '"+u.getLname()+"', password = '"+u.getPassw()+"', emails = '"+u.getEmail()+"', age = '"+u.getAge()+"' WHERE username = '"+u.getUname()+"';");

        db.close();
    }

}
