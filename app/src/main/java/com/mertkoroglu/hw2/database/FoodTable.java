package com.mertkoroglu.hw2.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.mertkoroglu.hw2.Food;

import java.util.ArrayList;

public class FoodTable {
    public static final String TABLE_NAME = "foods";
    public static final String FIELD_ID = "_id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_CALORIE = "calorie";
    public static final String FIELD_RECIPE = "recipe";
    public static final String FIELD_TYPE = "type";

    public static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + "(" + FIELD_ID + " number, " + FIELD_NAME + " text, " + FIELD_CALORIE + " number, " + FIELD_RECIPE + " text, " + FIELD_TYPE + " text);";
    public static final String DROP_TABLE_SQL = "DROP TABLE if exists" + TABLE_NAME;
    public static final String FIND_SIZE = "SELECT COUNT (" + FIELD_NAME + ")";

    public static ArrayList<Food> getAllFood(DatabaseHelper db) {
        Cursor cursor = db.getAllRecords(TABLE_NAME, null);

        ArrayList<Food> data = new ArrayList<>();
        Food med = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int calorie = cursor.getInt(2);
            String recipe = cursor.getString(3);
            String type = cursor.getString(4);
            med = new Food(id, calorie, name, recipe, type);
            data.add(med);
        }
        return data;
    }


    public static ArrayList<Food> find(DatabaseHelper db, int id) {
        String where = FIELD_ID + " == " + id +"";
        Cursor cursor = db.getSomeRecords(TABLE_NAME, null, where);
        ArrayList<Food> data = new ArrayList<>();
        Food med = null;
        while (cursor.moveToNext()) {
            int id2 = cursor.getInt(0);
            String name = cursor.getString(1);
            int calorie = cursor.getInt(2);
            String recipe = cursor.getString(3);
            String type = cursor.getString(4);
            med = new Food(id2, calorie, name, recipe, type);
            data.add(med);
        }
        return data;
    }

    public static boolean insert(DatabaseHelper db, int id, int calorie, String name, String recipe, String type) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, id);
        contentValues.put(FIELD_CALORIE, calorie);
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_RECIPE, recipe);
        contentValues.put(FIELD_TYPE, type);
        boolean res = db.insert(TABLE_NAME, contentValues);
        return res;
    }

    public static boolean insert(DatabaseHelper db, Food med) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, med.getId());
        contentValues.put(FIELD_CALORIE, med.getCalorie());
        contentValues.put(FIELD_NAME, med.getName());
        contentValues.put(FIELD_RECIPE, med.getRecipe());
        contentValues.put(FIELD_TYPE, med.getType());
        boolean res = db.insert(TABLE_NAME, contentValues);
        return res;
    }

    public static boolean update(DatabaseHelper db, int id, String recipe) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_RECIPE, recipe);
        String where = FIELD_ID + " = " + id;
        boolean res = db.update(TABLE_NAME, contentValues, where);
        return res;
    }

    public static boolean update(DatabaseHelper db, Food med) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_CALORIE, med.getCalorie());
        contentValues.put(FIELD_NAME, med.getName());
        contentValues.put(FIELD_RECIPE, med.getRecipe());
        contentValues.put(FIELD_TYPE, med.getType());
        String where = FIELD_ID + " = " + med.getId();
        boolean res = db.update(TABLE_NAME, contentValues, where);
        return res;
    }

    public static boolean delete(DatabaseHelper db, int id) {
        String where = FIELD_ID + " = " + id;
        boolean res = db.delete(TABLE_NAME, where);
        return res;
    }

}