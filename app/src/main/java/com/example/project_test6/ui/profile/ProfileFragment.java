package com.example.project_test6.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_test6.Login;
import com.example.project_test6.R;
import com.example.project_test6.ach;
import com.example.project_test6.achAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ProfileFragment extends Fragment {

    private ArrayList<ach> aches;
    private ProfileViewModel profileViewModel;

    private RecyclerView recyclerView;
    private achAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProfileViewModel notificationsViewModel;

    private Button btn_logout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        btn_logout = root.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), Login.class));
                Log.d(TAG,"LOG OUT");
            }
        });

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

        return root;

    }
    public void createAch(String ach, String condition, int counter){

        //
        aches.add(new ach(ach,condition,counter));
    }

}