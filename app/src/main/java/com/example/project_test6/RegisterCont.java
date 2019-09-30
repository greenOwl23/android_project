package com.example.project_test6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterCont extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference dbRoot;
    private Button btn_done;
    private EditText openBalance;
    private EditText budget;
    private EditText savingGoal;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cont);
        dbRoot = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        openBalance = findViewById(R.id.account_balance_setup);
        budget = findViewById(R.id.budget_setup);
        savingGoal = findViewById(R.id.saving_setup);

        btn_done= findViewById(R.id.finish_Button);
        btn_done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setUp();

            }
        });
    }
    protected void setUp(){

        String str_Balance = openBalance.getText().toString();
        String str_Buget = budget.getText().toString();
        String str_savingGoal = savingGoal.getText().toString();
        if(!str_Balance.isEmpty() && !str_Buget.isEmpty() && !str_savingGoal.isEmpty()){
            double opBalance = Double.parseDouble(openBalance.getText().toString());
            double opBuget = Double.parseDouble(budget.getText().toString());
            double opSavingGoal = Double.parseDouble(savingGoal.getText().toString());
            double opDecSpen = opBuget-opSavingGoal;
            double opDecSaving = opSavingGoal;
            String uid = user.getUid();


            dbRoot.child("users").child(uid).child("balance").setValue(opBalance);
            dbRoot.child("users").child(uid).child("daily_budget").setValue(opBuget);
            dbRoot.child("users").child(uid).child("saving_goal").setValue(opSavingGoal);
//            dbRoot.child("users").child(uid).child("dedic_to_saving").setValue(opDecSaving);
            dbRoot.child("users").child(uid).child("daily_budget_remain").setValue(opDecSpen);
            dbRoot.child("users").child(uid).child("saving_remain").setValue(opSavingGoal);
//            dbRoot.child("users").child(uid).child("Transactions").push().setValue();
            startActivity(new Intent(RegisterCont.this, MainPage.class));
        }else{
            Toast.makeText(context, "Please fill in all the information!",
                    Toast.LENGTH_SHORT).show();

        }



    }
}
