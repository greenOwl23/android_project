package com.example.project_test6;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private EditText et_displayName;
    private EditText et_email;
    private EditText et_password;
    private EditText et_c_password;
    private Button btn_next;
    Context context = this;
    String TAG = "";
    FirebaseUser user;
    DatabaseReference dbRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbRoot = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        et_displayName = findViewById(R.id.display_name);
        et_email = findViewById(R.id.email_register);
        et_password = findViewById(R.id.new_password);
        et_c_password = findViewById(R.id.confirm_password);
        btn_next = findViewById(R.id.next_Button);

//        Intent serviceIntent = new Intent(context,FirebaseDB.class);
//        context.startService(serviceIntent);

        btn_next.setOnClickListener(this);

    }

    private void signUp() {
        final String displayName = et_displayName.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            user = mAuth.getCurrentUser();
//                            Intent intent = new Intent(Register.this,Readable.class);
//                            startActivity(intent);
                            onSuccessLogin(displayName);
                            Toast.makeText(context, "Success!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this,RegisterCont.class);
                            startActivity(intent);

//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });
    }

    protected void onSuccessLogin(String name) {
//        String username = user.getEmail();
        // Write new user
        writeNewUser(user.getUid(), name);
    }

    public void writeNewUser(String userId, String email) {
        User new_user = new User(email);
        dbRoot.child("users").child(userId).setValue(new_user);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_Button:
                signUp();
                break;

        }
    }
}