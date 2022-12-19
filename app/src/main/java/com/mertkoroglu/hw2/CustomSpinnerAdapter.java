package com.mertkoroglu.hw2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mertkoroglu.mertkoroglu_hw2.R;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends ArrayAdapter<FoodType> {
    Context context;
    ArrayList<FoodType> spinnerItems;

    public CustomSpinnerAdapter(@NonNull Context context, @NonNull ArrayList<FoodType> values) {
        super(context, 0, values);
        this.context = context;
        this.spinnerItems = values;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       return getMyCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getMyCustomView(position, convertView, parent);
    }

    public View getMyCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view=null;
        FoodType stemp = spinnerItems.get(position);

        TextView tvItemName;

        if(stemp.getId() == 0){
            view = inflator.inflate(R.layout.spinner_baking_item, parent, false);
            tvItemName = view.findViewById(R.id.tv_baking);
        }else if(stemp.getId() == 1){
            view = inflator.inflate(R.layout.spinner_frying_item, parent, false);
            tvItemName = view.findViewById(R.id.tv_frying);
        }else{
            view = inflator.inflate(R.layout.spinner_stew_item, parent, false);
            tvItemName = view.findViewById(R.id.tv_stew);
        }

        tvItemName.setText(stemp.getType());
        return view;
    }
}
