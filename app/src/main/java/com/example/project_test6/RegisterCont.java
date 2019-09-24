package com.example.project_test6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterCont extends AppCompatActivity {

    private Button button;
    private TextView openBalance;
    private TextView savingGoal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cont);


        button= findViewById(R.id.finish_Button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Create new user
                startActivity(new Intent(RegisterCont.this, MainPage.class));
            }
        });
    }
}
