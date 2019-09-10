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
    private ArrayList<Transaction> transactions;

    private RecyclerView recyclerView;
    private TransactionAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        Category Food = new Category("Food");
        createExample(new Date(),"",Food,5);

        recyclerView = (RecyclerView) root.findViewById(R.id.history_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TransactionAdapter(transactions); // you can use that adapter after for loop in response(APICALLMETHOD)
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

    public void createExample(Date date, String type, Category category, int amount){
        transactions = new ArrayList<>();
        transactions.add(new Transaction(date,type,category,amount));
    }

}