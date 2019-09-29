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


public class insert_form extends Activity {
    private FirebaseAuth mAuth;
    DatabaseReference dbRoot;
    private Spinner typeList, category;
    private Button btn_Add;
    private EditText amount;
    Context context = this;
    String TAG = "InsertForm";


    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dRef;
    DatabaseReference userRef;
    String uid;

    String Food = "Food";
    String Car = "Car";
    String Health = "Health";
    User localUser;
    double fixed_dedic_spend = 0;
    double fixed_dedic_save = 0;
    double dedic_to_spend = 0;
    double dedic_to_saving = 0;
    double buffer = 0;
    double current_saving = 0;
    double accBalance = 0;

    String localDisplayName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_form);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        user = mAuth.getCurrentUser();
        uid = user.getUid();
        dbRoot = FirebaseDatabase.getInstance().getReference();
        userRef = firebaseDatabase.getReference().child("users").child(uid);

        // Read from the database

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Map map = (Map) dataSnapshot.getValue();
//                String value = dataSnapshot.getValue(String.class);
                accBalance = Double.parseDouble(String.valueOf(map.get("balance")));
                fixed_dedic_spend = Double.parseDouble(String.valueOf(map.get("daily_budget")));
                fixed_dedic_save = Double.parseDouble(String.valueOf(map.get("saving_goal")));
                current_saving = Double.parseDouble(String.valueOf(map.get("total_saving")));
                buffer = Double.parseDouble(String.valueOf(map.get("buffer")));
                accBalance = Double.parseDouble(String.valueOf(map.get("balance")));
                dedic_to_spend = Double.parseDouble(String.valueOf(map.get("daily_budget_remain")));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
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

    public void addItemsOnSpinner2() {
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
//        Log.e(TAG,localDisplayName);
        if (user != null) {
            btn_Add.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String ip_type = typeList.getSelectedItem().toString();
                    String ip_category = category.getSelectedItem().toString();
                    String str_amount = amount.getText().toString();

                    if (!str_amount.isEmpty()) {
                        int ip_amount = Integer.parseInt(amount.getText().toString());
                        Transaction newTransaction = new Transaction(ip_type, ip_category, ip_amount);
                        dbRoot.child("users").child(uid).child("Transactions").push().setValue(newTransaction);
                        Toast.makeText(context, "Added!",
                                Toast.LENGTH_SHORT).show();

                        calTransac(typeList.getSelectedItem().toString(), ip_amount);

                        startActivity(new Intent(insert_form.this, MainPage.class));
                    } else {
                        Toast.makeText(context, "Please add the amount!",
                                Toast.LENGTH_SHORT).show();
                    }


                }

            });
        }

    }

    public void calTransac(String tranType, double trans) {

        //Read them data boi
        Log.e(TAG, String.valueOf(accBalance));
        if (tranType.equals("Expense")) {

            accBalance -= trans;

            if (dedic_to_spend <= 0) {
//                System.out.print("You have used up the budget for the day. Your expense will now be subtracted to your dedicated saving amount. ");
                dedic_to_saving -= (trans + Math.abs(dedic_to_spend));
                dedic_to_spend = 0;
            } else if (dedic_to_saving < 0) {
//                System.out.print("You have used up the amount dedicated to saving. Your expense will now be subtracted from buffer.");

                buffer -= (trans + Math.abs(dedic_to_saving));
                dedic_to_saving = 0;
            } else {
                if (dedic_to_spend <= trans) {
                    dedic_to_saving -= (trans + Math.abs(dedic_to_spend));
                    dedic_to_spend = 0;
                } else {
                    dedic_to_spend -= trans;
                }

            }
        }
        //income
        else {
            accBalance += trans;
        }

        updateAccBalance(accBalance);
        updateBudgetLeft(dedic_to_spend);
        updateSaving(dedic_to_saving);

    }

    public void updateAccBalance(double delta) {
        // Write a message to the database
        userRef.child("balance").setValue(delta);

    }

    public void updateBudgetLeft(double delta) {
        //Update them budget
        userRef.child("daily_budget_remain").setValue(delta);

    }

    public void updateSaving(double delta) {
        //Update them saving
        userRef.child("saving_goal").setValue(delta);

    }

    ;
}
