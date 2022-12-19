package com.mertkoroglu.hw2.database;

import android.database.Cursor;

import com.mertkoroglu.hw2.Food;

import java.util.ArrayList;

public class Commons {
    public static Food food;
    public static Cursor cursor;
    public static ArrayList<Food> data;

    public static Food getFood() {
        return food;
    }
    public static void setFood(Food food) {
        Commons.food = food;
    }
    public static ArrayList<Food> getData() {
        return data;
    }
    public static void setData(ArrayList<Food> data) {
        Commons.data = data;
    }
}
