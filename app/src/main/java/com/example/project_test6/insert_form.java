package com.example.project_test6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Constraints;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class insert_form extends Activity {
    private FirebaseAuth mAuth;
    DatabaseReference dbRoot;
    private Spinner typeList,category;
    private Button btn_Add;
    private EditText amount;
    Context context = this;

    public double acc_bal;
    public double allowance_per_day;
    public double fixed_dedic_spend;
    public double fixed_dedic_save;
    public double dedic_to_spend;
    public double dedic_to_saving;
    public double buffer;
    public double current_saving;

    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dRef;
    DatabaseReference userRef;
    String uid;

    String Food = "Food";
    String Car = "Car";
    String Health = "Health";
    User localUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_form);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        firebaseDatabase = FirebaseDatabase.getInstance();
        dRef = firebaseDatabase.getReference().child("users").child(uid).child("Transactions");
        userRef = firebaseDatabase.getReference().child("users").child(uid);

        addItemsOnSpinner();
        addItemsOnSpinner2();
        addListenerOnButton();

        dRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dsBitch: dataSnapshot.getChildren()){
                    Map map = (Map)dsBitch.getValue();
                    String amount = String.valueOf(map.get("amount"));
                    String type = String.valueOf(map.get("type"));

                    Log.e(Constraints.TAG, amount);
                    Log.e(Constraints.TAG, type);

                    Double am = Double.parseDouble(amount);
                    calTransac(type,am);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



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
            list.add(Health);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            category.setAdapter(dataAdapter);
    }


    // get the selected dropdown list value
    public void addListenerOnButton() {
        typeList = (Spinner) findViewById(R.id.typeList);
        category = (Spinner) findViewById(R.id.category);
        amount = (EditText) findViewById(R.id.amount);
        btn_Add = (Button) findViewById(R.id.btnAdd);
        user = mAuth.getCurrentUser();
        if(user != null){
            btn_Add.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String ip_type = typeList.getSelectedItem().toString();
                    String ip_category = category.getSelectedItem().toString();
                    String str_amount = amount.getText().toString();

                    if(!str_amount.isEmpty()){
                        int ip_amount = Integer.parseInt(amount.getText().toString());
                        Transaction newTransaction = new Transaction(ip_type,ip_category,ip_amount);
                        dbRoot.child("users").child(uid).child("Transactions").push().setValue(newTransaction);
                        Toast.makeText(context, "Added!",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(insert_form.this, MainPage.class));
                    }else {
                        Toast.makeText(context, "Please add the amount!",
                                Toast.LENGTH_SHORT).show();
                    }


                }

            });
        }

    }
    public void calTransac(String tranType, double trans){
        double delta_accBalance = 0;
        double delta_budget = 0;
        double delta_saving = 0;
        if(tranType.equals("expense")){
            delta_accBalance = 0 - trans;

            if(dedic_to_spend<0){
                dedic_to_saving =dedic_to_saving - (trans - dedic_to_spend);
                dedic_to_spend=0;
            }else if(dedic_to_saving<0){
                //alert
                buffer =buffer - (trans - dedic_to_saving);
                dedic_to_saving=0;
            }
            else{
                dedic_to_spend-=trans;
            }
        }
        //income
        else{
            delta_accBalance = trans;
        }

        updateAccBalance(delta_accBalance);
        updateBudgetLeft();

    }
    public void updateAccBalance(double delta){}
    public void updateBudgetLeft(){}
    public void updateSaving(){};
}
