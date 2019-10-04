package com.example.project_test6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class User_Account_Setting extends AppCompatActivity implements View.OnClickListener {

    // UI display's variables
    private EditText displayName;
    private EditText currentPassword;
    private EditText newPassword;
    private Button btn_save;

    //Database variables
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dRef;
    DatabaseReference userRef;
    FirebaseUser user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__account__setting);

        //Initialize variables
        displayName = findViewById(R.id.display_name);
        currentPassword = findViewById(R.id.new_pasword_reset);
        newPassword = findViewById(R.id.new_pasword_reset_confirm);
        btn_save = findViewById(R.id.save_Button);
        btn_save.setOnClickListener(this);

        //Initialize variables for database
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        userRef = firebaseDatabase.getReference().child("users").child(uid);

        //Read from database
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map map = (Map)dataSnapshot.getValue();
                String name = String.valueOf(map.get("displayName"));
                displayName.setText(name);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    //Method used to reset the displayName
    protected void resetName(){
        String newName = displayName.getText().toString();
        userRef.child("displayName").setValue(newName);
        startActivity(new Intent(User_Account_Setting.this, MainPage.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_Button:
                resetName();
                break;
        }
    }
}
