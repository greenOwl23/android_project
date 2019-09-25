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
import com.example.project_test6.MainPage;
import com.example.project_test6.R;
import com.example.project_test6.Transaction;
import com.example.project_test6.TransactionAdapterHistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryFragment extends Fragment {

    private HistoryViewModel dashboardViewModel;
    private ArrayList<Transaction> transactions;

    private RecyclerView recyclerView;
    private TransactionAdapterHistory adapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        transactions = new ArrayList<>();
        Category Food = new Category("Food");
        createTransaction(new Date(), "Income", Food, 50000);
        createTransaction(new Date(), "Expense", Food, 500);
        createTransaction(new Date(), "Income", Food, 5000);
        createTransaction(new Date(), "Expense", Food, 500);
        createTransaction(new Date(), "Income", Food, 50000);
        createTransaction(new Date(), "Expense", Food, 500);
        createTransaction(new Date(), "Income", Food, 5000);
        int year = 2019;
        int month = 9;
        int day = 24;
        String date = year + "/" + month + "/" + day;
        java.util.Date utilDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date Date_ = null;
        try {
            Date_ = formatter.parse(date);
        } catch (ParseException e) {

        }
        createTransaction(new Date(String.valueOf(Date_)), "Expense", Food, 500);
        createTransaction(new Date(), "Income", Food, 50000);
        createTransaction(new Date(), "Expense", Food, 500);
        createTransaction(new Date(), "Income", Food, 5000);
        createTransaction(new Date(), "Expense", Food, 500);

        recyclerView = (RecyclerView) root.findViewById(R.id.history_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TransactionAdapterHistory(transactions);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new TransactionAdapterHistory.onItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        return root;
    }

    public void createTransaction(Date date, String type, Category category, int amount){
        transactions.add(new Transaction(date,type,category,amount));
    }

}