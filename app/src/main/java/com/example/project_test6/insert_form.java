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
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_test6.ui.history.HistoryFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class insert_form extends Activity {
    private FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference dbRoot;
    private Spinner typeList,category;
    private Button btn_Add;
    private EditText amount;
    Context context = this;


    String Food = "Food";
    String Car = "Car";
    String Health = "Health";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_form);

        dbRoot = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.5));

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
        list.add(Food);
        list.add(Car);
        list.add(Health );
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(dataAdapter);
    }


    // get the selected dropdown list value
    public void addListenerOnButton() {
        typeList = (Spinner) findViewById(R.id.typeList);
        category = (Spinner) findViewById(R.id.category);
        amount = (EditText) findViewById(R.id.editText2);
        btn_Add = (Button) findViewById(R.id.btnAdd);
        user = mAuth.getCurrentUser();
        if(user != null){
            btn_Add.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String ip_type = typeList.getSelectedItem().toString();
                    String ip_category = category.getSelectedItem().toString();
                    int ip_amount = Integer.parseInt(amount.getText().toString());
                    String uid = user.getUid();

//                      public Transaction (Date timestamp, String type, Category category, int amount){
                    Transaction newTransaction = new Transaction(ip_type,ip_category,ip_amount);
                    dbRoot.child("users").child(uid).child("Transactions").push().setValue(newTransaction);
                    Toast.makeText(context, "Added!",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(insert_form.this, MainPage.class));

                }

            });
        }

    }
}
