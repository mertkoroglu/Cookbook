package com.mertkoroglu.hw2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME="foodsDB";
    private static int DATABASE_VERSION = 1;
    SQLiteDatabase db;

    public DatabaseHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    public void close(){
        if(db != null && db.isOpen()) {
            db.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(FoodTable.CREATE_TABLE_SQL);

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        try {
            sqLiteDatabase.execSQL(FoodTable.DROP_TABLE_SQL);
        }catch (SQLException e){
            e.printStackTrace();
        }
        onCreate(sqLiteDatabase);
     }

    public Cursor getAllRecords(String tableName, String[] columns ){
        Cursor cursor = db.query(tableName, columns, null, null, null, null, null );

        return cursor;
    }

    public Cursor getSomeRecords(String tableName, String[] columns, String where ){
        Cursor cursor = db.query(tableName, columns, where, null, null, null, null);
        return cursor;
    }
    public boolean insert(String tableName, ContentValues contentValues){

        return db.insert(tableName, null, contentValues)>0;
    }
    public boolean update(String tableName, ContentValues contentValues, String whereCondition){

        return db.update(tableName,contentValues,whereCondition,null)>0;
    }

    public boolean delete(String tableName, String whereCondition){

        return db.delete(tableName, whereCondition, null)>0;
    }
}
