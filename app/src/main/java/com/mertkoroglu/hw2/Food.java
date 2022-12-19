package com.mertkoroglu.hw2;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {
    private int id;
    private int calorie;
    private String name, recipe, type;

    public Food(int id, int calorie, String name, String recipe, String type) {
        this.id = id;
        this.calorie = calorie;
        this.name = name;
        this.recipe = recipe;
        this.type = type;
    }

    protected Food(Parcel in) {
        id = in.readInt();
        calorie = in.readInt();
        name = in.readString();
        recipe = in.readString();
        type = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public int getId() {
        return id;
    }

    public int getCalorie() {
        return calorie;
    }

    public String getName() {
        return name;
    }

    public String getRecipe() {
        return recipe;
    }

    public String getType() {
        return type;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(calorie);
        dest.writeString(name);
        dest.writeString(recipe);
        dest.writeString(type);
    }
}
