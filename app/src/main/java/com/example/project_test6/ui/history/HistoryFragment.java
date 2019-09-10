package com.example.project_test6.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_test6.Category;
import com.example.project_test6.Popup;
import com.example.project_test6.R;
import com.example.project_test6.Transaction;
import com.example.project_test6.TransactionAdapter;
import com.example.project_test6.insert_form;

import java.util.ArrayList;
import java.util.Date;

public class HistoryFragment extends Fragment {

    private HistoryViewModel dashboardViewModel;
    private ArrayList<Transaction> example;

    private RecyclerView recyclerView;
    private TransactionAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container, false);

        createExample();

        recyclerView = (RecyclerView) root.findViewById(R.id.history_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TransactionAdapter(example); // you can use that adapter after for loop in response(APICALLMETHOD)
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new TransactionAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), Popup.class);
                startActivity(intent);
            }
        });

        return root;
    }

    public void createExample(){
        Category Food = new Category("Food");
        Category Car = new Category("Car");
        Category Health = new Category("Health");

        example = new ArrayList<>();

        example.add(new Transaction(new Date(),"Income",Food,50000));
        example.add(new Transaction(new Date(),"Expense",Food,500));
        example.add(new Transaction(new Date(),"Expense",Car,5000));
        example.add(new Transaction(new Date(),"Expense",Health,1000));
        example.add(new Transaction(new Date(),"Income",Food,50000));
    }

}