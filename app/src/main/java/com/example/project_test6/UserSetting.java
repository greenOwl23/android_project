package com.example.project_test6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class UserSetting extends AppCompatActivity {

    // UI display's variables
    private Button button;
    private Button button_user;
    private Button button_balance;
    private Button button_budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        //Setting onclick listener to buttons
        button_user = findViewById(R.id.user_Account_Setting);
        button_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(UserSetting.this, User_Account_Setting.class));
            }
        });
        button_balance = findViewById(R.id.account_balance_Setting);
        button_balance.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(UserSetting.this, BalanceSetting.class));
            }
        });
        button_budget = findViewById(R.id.budget_setting);
        button_budget.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(UserSetting.this, BudgetSetting.class));
            }
        });

        button = findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(UserSetting.this, Login.class));

                //Check values
                Log.d(TAG, "LOG OUT");
            }
        });

    }
}
