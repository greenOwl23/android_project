package com.example.project_test6.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_test6.Category;
import com.example.project_test6.R;
import com.example.project_test6.Transaction;
import com.example.project_test6.TransactionAdapterHome;
import com.example.project_test6.insert_form;

import java.util.ArrayList;
import java.util.Date;

public class HomeFragment extends Fragment {

    private ArrayList<Transaction> transactions;
    private HomeViewModel homeViewModel;

    private RecyclerView recyclerView;
    private TransactionAdapterHome adapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView img = (ImageView) root.findViewById(R.id.button_insert);
        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), insert_form.class);
                startActivity(intent);
            }
        });
        transactions = new ArrayList<>();
        Category Food = new Category("Food");
        createTransaction(new Date(),"Income",Food,50000);
        createTransaction(new Date(),"Expense",Food,500);
        createTransaction(new Date(),"Income",Food,5000);
        createTransaction(new Date(),"Expense",Food,500);
        createTransaction(new Date(),"Expense",Food,500);
        createTransaction(new Date(),"Expense",Food,500);
        createTransaction(new Date(),"Expense",Food,500);
        if (transactions.size()>6){
            transactions.remove(0);
        }

        recyclerView = (RecyclerView) root.findViewById(R.id.recent_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TransactionAdapterHome(transactions);
        recyclerView.setAdapter(adapter);

        return root;
    }
    public void createTransaction(Date date, String type, Category category, int amount){
        transactions.add(new Transaction(date,type,category,amount));
    }

}