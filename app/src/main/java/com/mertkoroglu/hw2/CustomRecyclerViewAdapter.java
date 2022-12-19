package com.mertkoroglu.hw2;


import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mertkoroglu.hw2.database.DatabaseHelper;
import com.mertkoroglu.hw2.database.FoodTable;
import com.mertkoroglu.mertkoroglu_hw2.R;

import java.util.ArrayList;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomRecyclerViewItemHolder> {
    private Context context;
    private ArrayList<Food> recyclerItemValues;

    DatabaseHelper dbHelper;

    public CustomRecyclerViewAdapter(Context context, ArrayList<Food> values) {
        this.context = context;
        this.recyclerItemValues = values;
        dbHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public CustomRecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View itemView = inflater.inflate(R.layout.recycler_layout, viewGroup, false);

        CustomRecyclerViewItemHolder mViewHolder = new CustomRecyclerViewItemHolder(itemView);
        return mViewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerViewItemHolder myRecyclerViewItemHolder, int i) {
        int pos = i;
        final Food foodItem = recyclerItemValues.get(pos);

        myRecyclerViewItemHolder.name.setText(foodItem.getName());
        myRecyclerViewItemHolder.calorie.setText("" + foodItem.getCalorie() + "kcal");

        //shorten the recipe in main activity
        if(foodItem.getRecipe().length() > 45){
            myRecyclerViewItemHolder.recipe.setText(foodItem.getRecipe().substring(0,45) + "...");
        }else{
            myRecyclerViewItemHolder.recipe.setText(foodItem.getRecipe());
        }

        myRecyclerViewItemHolder.type.setText(foodItem.getType());

        //color change according to type
        if (foodItem.getType().equals("Baking")){
            myRecyclerViewItemHolder.type.setTextColor(ContextCompat.getColor(context, R.color.baking));
        }else if(foodItem.getType().equals("Frying")){
            myRecyclerViewItemHolder.type.setTextColor(ContextCompat.getColor(context, R.color.frying));
        }else{
            myRecyclerViewItemHolder.type.setTextColor(ContextCompat.getColor(context, R.color.stew));
        }

        myRecyclerViewItemHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete from db
                boolean res = FoodTable.delete(dbHelper, foodItem.getId());

                if (res) {
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                    //delete from arraylist
                    refreshCustomAdapterAfterDelete(foodItem.getId(), pos);
                } else {
                    Toast.makeText(context, "Delete Unsuccessful", Toast.LENGTH_SHORT).show();
                }


            }
        });

        myRecyclerViewItemHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("item", foodItem);
                context.startActivity(intent);
            }
        });

    }

    public void refreshCustomAdapterAfterDelete(int id, int pos) {
        //delete where id matches
        recyclerItemValues.removeIf(food -> food.getId() == id);
        notifyItemRemoved(pos);
    }


    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

    class CustomRecyclerViewItemHolder extends RecyclerView.ViewHolder {
        TextView name, type, recipe, calorie, delete;
        LinearLayout parentLayout;

        public CustomRecyclerViewItemHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.rv_name);
            calorie = itemView.findViewById(R.id.rv_calorie);
            delete = itemView.findViewById(R.id.rv_delete);
            recipe = itemView.findViewById(R.id.rv_recipe);
            type = itemView.findViewById(R.id.rv_type);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }


}