package com.example.project_test6.ui.profile;

//<<<<<<< HEAD
//=======
//>>>>>>> 3a72a548f0794335ccc99382c4c63b9eacaed02d
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//<<<<<<< HEAD
import android.widget.Button;
//=======
import android.widget.ImageView;
import android.widget.TextView;
//>>>>>>> 3a72a548f0794335ccc99382c4c63b9eacaed02d

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

        import com.example.project_test6.R;
import com.example.project_test6.UserSetting;
import com.example.project_test6.Ach;
import com.example.project_test6.achAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ProfileFragment extends Fragment {

    private ArrayList<Ach> aches;
    private ProfileViewModel profileViewModel;
    private RecyclerView recyclerView;
    private achAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView userImg;
    private ProfileViewModel notificationsViewModel;
    private TextView displayName;
    private TextView displayBalance;
    private TextView displaySaving;
    private TextView displayLvl;
    private TextView saving_remain;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference dRef;
    DatabaseReference userRef;
    FirebaseUser user;
    String uid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        aches = new ArrayList<>();
        createAch("Banana","Eat a banana",2);
        createAch("Banana","Eat a banana",2);
        createAch("Banana","Eat a banana",2);
        createAch("Banana","Eat a banana",2);
        createAch("Banana","Eat a banana",2);
        createAch("Banana","Eat a banana",2);

        recyclerView = (RecyclerView) root.findViewById(R.id.ach);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new achAdapter(aches);
        recyclerView.setAdapter(adapter);

        userImg = root.findViewById(R.id.user_img);
        userImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserSetting.class));
            }
        });



        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        displayName = root.findViewById(R.id.name);
        displayBalance = root.findViewById(R.id.account_balance);
        displaySaving = root.findViewById(R.id.saving);
        displayLvl = root.findViewById(R.id.user_lvl);
        saving_remain = root.findViewById(R.id.saving);
        firebaseDatabase = FirebaseDatabase.getInstance();
        userRef = firebaseDatabase.getReference().child("users").child(uid);
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Map map = (Map)dataSnapshot.getValue();
//              String value = dataSnapshot.getValue(String.class);
                String name = String.valueOf(map.get("displayName"));
                String balance = String.valueOf(map.get("balance"));
                String lvl = String.valueOf(map.get("lvl"));
                String Saving_remain = String.valueOf(map.get("saving_remain"));
//                String saving = String.valueOf(map.get(saving));
//                Log.e(TAG, "Value is: " + value);

                displayName.setText(name);
                displayBalance.setText("Account Balance : " +balance);
                displayLvl.setText("Level : "+lvl);
                saving_remain.setText("Saving : "+Saving_remain);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });







        return root;

    }
    public void createAch(String ach, String condition, int counter){

        //
        aches.add(new Ach(ach,condition,counter));
    }

}