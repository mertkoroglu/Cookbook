package com.mertkoroglu.hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mertkoroglu.hw2.database.DatabaseHelper;
import com.mertkoroglu.hw2.database.FoodTable;
import com.mertkoroglu.mertkoroglu_hw2.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
        ->  Double tap "Add new" button to go to AddActivity
        ->  Click on "X" buttons of the items to delete them from the database
        ->  Click on layout of the items to see a more detailed look or change their recipe
     */

    DatabaseHelper dbHelper;
    RecyclerView main_rv;
    Button main_btn;
    Intent intent;
    TextView main_guide;

    private GestureDetectorCompat gestureDetector;
    private CustomGestureListener customGestureListener;

    ArrayList<Food> foodList =new ArrayList<>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        customGestureListener = new CustomGestureListener();
        gestureDetector = new GestureDetectorCompat(MainActivity.this, customGestureListener);
        gestureDetector.setOnDoubleTapListener(customGestureListener);

        main_rv = findViewById(R.id.main_rv);
        main_btn = findViewById(R.id.main_btn);
        main_guide = findViewById(R.id.main_guide);

        intent = new Intent(MainActivity.this, AddActivity.class);

        dbHelper = new DatabaseHelper(this);

        //foods from db
        foodList = FoodTable.getAllFood(dbHelper);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        main_rv.setLayoutManager(layoutManager);


        CustomRecyclerViewAdapter adapterRv = new CustomRecyclerViewAdapter(MainActivity.this, foodList);
        main_rv.setAdapter(adapterRv);


        main_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

    class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTapEvent(MotionEvent event) {
            startActivity(intent);
            return true;
        }
    }

    public void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
        // Copy 1K bytes at a time
        byte[] buffer = new byte[1024];
        int length;
        Log.d("BURDA", "BURDA2");

        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
            Log.d("BURDA", "BURDA3");
        }
        inputStream.close();
        outputStream.close();
    }
}

