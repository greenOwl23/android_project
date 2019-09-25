package com.example.project_test6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.project_test6.ui.profile.ProfileFragment;

public class UserSetting extends AppCompatActivity {

    private Button button_logout;
    private Button button_user;
    private Button button_balance;
    private Button button_budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        button_user = findViewById(R.id.user_Account_Setting);
        button_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserSetting.this, User_Account_Setting.class));
            }
        });

        button_balance = findViewById(R.id.account_balance_Setting);
        button_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserSetting.this, MainPage.class));
            }
        });

        button_budget = findViewById(R.id.budget_setting);
        button_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserSetting.this, BudgetSetting.class));
            }
        });

        button_logout = findViewById(R.id.logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Logout user

            }
        });


    }
}
