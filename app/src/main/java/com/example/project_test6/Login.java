package com.example.project_test6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private Button button;
    private TextView email;
    private TextView password;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button= findViewById(R.id.login_Button);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        register.setText(R.string.register);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Login the user
                startActivity(new Intent(Login.this, MainPage.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

    }

}
