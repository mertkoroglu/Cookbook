package com.mertkoroglu.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mertkoroglu.hw2.database.DatabaseHelper;
import com.mertkoroglu.hw2.database.FoodTable;
import com.mertkoroglu.mertkoroglu_hw2.R;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    EditText add_name, add_recipe, add_calorie, add_id;
    Button add_btn;
    DatabaseHelper dbHelper;
    Food food;
    Intent intentGet;
    Spinner spinner;
    ArrayList<FoodType> foodTypes;
    String spinnerValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        FoodTypeSys.prepareData();
        foodTypes = FoodTypeSys.getFoodTypes();

        spinner = findViewById(R.id.add_type);

        CustomSpinnerAdapter adapterSp = new CustomSpinnerAdapter(this, foodTypes);
        spinner.setAdapter(adapterSp);

        add_btn = findViewById(R.id.add_btn);
        add_name = findViewById(R.id.add_name);
        add_recipe = findViewById(R.id.add_recipe);
        add_calorie = findViewById(R.id.add_calorie);
        add_id = findViewById(R.id.add_id);

        intentGet = getIntent();

        dbHelper = new DatabaseHelper(this);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FoodTable.find(dbHelper, Integer.parseInt(add_id.getText().toString())).size() == 0){
                    if(add_name.length() == 0 || add_recipe.length() == 0 || add_calorie.length() == 0){
                        Toast.makeText(AddActivity.this, "Name, Calorie and Recipe fields cannot be empty", Toast.LENGTH_SHORT).show();
                    }else{
                        switch(spinner.getSelectedItemPosition()){
                            case 0:
                                spinnerValue = "Baking";
                                break;
                            case 1:
                                spinnerValue = "Frying";
                                break;
                            case 2:
                                spinnerValue = "Stew";
                                break;
                        }

                        food = new Food(Integer.parseInt(add_id.getText().toString()), Integer.parseInt(add_calorie.getText().toString()), add_name.getText().toString(), add_recipe.getText().toString(), spinnerValue);
                        FoodTable.insert(dbHelper, food);
                        intentGet = new Intent(AddActivity.this, MainActivity.class);
                        startActivity(intentGet);
                    }
                }else{
                    Toast.makeText(AddActivity.this, "Id Already Exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}