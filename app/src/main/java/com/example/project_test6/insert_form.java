package com.example.project_test6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_test6.ui.history.HistoryFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class insert_form extends Activity {

    private Spinner typeList,category;
    private Button btnAdd;
    private TextView amount;


    Category Food = new Category("Food");
    Category Car = new Category("Car");
    Category Health = new Category("Health");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_form);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.7));

        addItemsOnSpinner();
        addItemsOnSpinner2();
        addListenerOnButton();
    }

    public void addItemsOnSpinner() {

        typeList = (Spinner) findViewById(R.id.typeList);
        List<String> list = new ArrayList<String>();
        list.add("Income");
        list.add("Expense");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeList.setAdapter(dataAdapter);
    }

    public void addItemsOnSpinner2(){


        category = (Spinner) findViewById(R.id.category);
        List<String> list = new ArrayList<String>();
        list.add(Food.getCategory());
        list.add(Car.getCategory());
        list.add(Health.getCategory());
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(dataAdapter);
    }


    // get the selected dropdown list value
    public void addListenerOnButton() {
        typeList = (Spinner) findViewById(R.id.typeList);
        category = (Spinner) findViewById(R.id.category);
        amount = (TextView) findViewById(R.id.amount);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }

        });
    }
}
