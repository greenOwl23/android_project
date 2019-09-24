package com.example.project_test6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    private Button button;
    private TextView displayName;
    private TextView email;
    private TextView password;
    private TextView passwordConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        button= findViewById(R.id.next_Button);
        displayName = findViewById(R.id.display_name);
        email = findViewById(R.id.email_register);
        password = findViewById(R.id.new_password);
        passwordConfirm = findViewById(R.id.confirm_password);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Create new user

                startActivity(new Intent(Register.this, RegisterCont.class));
            }
        });
    }

}
