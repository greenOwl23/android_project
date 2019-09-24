package com.example.project_test6.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_test6.R;
import com.example.project_test6.ach;
import com.example.project_test6.achAdapter;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private ArrayList<ach> aches;
    private ProfileViewModel profileViewModel;

    private RecyclerView recyclerView;
    private achAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProfileViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
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

        return root;

    }
    public void createAch(String ach, String condition, int counter){

        //
        aches.add(new ach(ach,condition,counter));
    }

}