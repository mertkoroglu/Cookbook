package com.mertkoroglu.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mertkoroglu.hw2.database.DatabaseHelper;
import com.mertkoroglu.hw2.database.FoodTable;
import com.mertkoroglu.mertkoroglu_hw2.R;

public class SecondActivity extends AppCompatActivity{
    Intent intent;
    TextView name, type, calorie, ingredients;
    Button changeBtn;
    EditText et;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_second);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        intent = getIntent();
        Food item = intent.getParcelableExtra("item");
        dbHelper = new DatabaseHelper(this);

        name = findViewById(R.id.second_name);
        type = findViewById(R.id.second_type);
        calorie = findViewById(R.id.second_calorie);
        ingredients = findViewById(R.id.second_ing);
        changeBtn = findViewById(R.id.second_btn);
        et = findViewById(R.id.second_et);

        name.setText(item.getName());
        type.setText(item.getType());
        calorie.setText(item.getCalorie() + "kcal");
        ingredients.setText(item.getRecipe());

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipe = et.getText().toString();
                FoodTable.update(dbHelper,item.getId(),recipe);
                displayDialog(et.getText().toString());
                //notifyItemRemoved(position);
            }
        });
    }

    public void displayDialog(final String msg){
        final TextView tv;
        Button btnClose;
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_layout);
        tv = dialog.findViewById(R.id.dialog_text);
        btnClose = dialog.findViewById(R.id.dialog_ok);
        tv.setText(msg);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent i = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        dialog.show();
    }
}


