package com.mertkoroglu.hw2;

import java.util.ArrayList;
import java.util.Collections;

public class FoodTypeSys {
    private static ArrayList<FoodType> foodTypes;

    public FoodTypeSys() {
    }

    public static void prepareData(){
        foodTypes = new ArrayList<>();
        Collections.addAll(foodTypes,
                new FoodType("Baking", 0),
                new FoodType("Frying", 1),
                new FoodType("Stew", 2));
    }

    public static ArrayList<FoodType> getFoodTypes() {
        return foodTypes;
    }
}
