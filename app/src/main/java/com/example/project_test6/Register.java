package com.example.project_test6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        button= findViewById(R.id.register_Button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Create new user
                startActivity(new Intent(Register.this, MainPage.class));
            }
        });
    }

}
