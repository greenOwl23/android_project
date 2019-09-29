package com.example.project_test6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private EditText new_username;
    private EditText new_password;
    private Button button;
    private EditText email;
    private EditText password;
    private TextView register;
    private Context context;
    String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        new_username = findViewById(R.id.email_login);
        new_password = findViewById(R.id.password);
        button= findViewById(R.id.login_Button);
//        email = findViewById(R.id.email_login);
//        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        register.setText(R.string.register);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//<<<<<<< HEAD
                //Log in the user
//                startActivity(new Intent(Login.this, MainPage.class));
                signIn();
//=======
                //Login the user
//                startActivity(new Intent(Login.this, MainPage.class));
//>>>>>>> 3a72a548f0794335ccc99382c4c63b9eacaed02d
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

    }
    private void signIn(){
        String email = new_username.getText().toString();
        String password = new_password.getText().toString();

        if(!email.isEmpty() && !password.isEmpty() ){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");

//                            user = mAuth.getCurrentUser();
                                startActivity(new Intent(Login.this, MainPage.class));
                                //Start Background Service is true

                            } else {
                                // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else {
            Toast.makeText(getApplicationContext(), "Please fill in the username and passsword.",
                    Toast.LENGTH_SHORT).show();
        }



    }


}
