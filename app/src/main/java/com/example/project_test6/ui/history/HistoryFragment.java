package com.example.project_test6.ui.history;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_test6.Category;
import com.example.project_test6.MainPage;
import com.example.project_test6.R;
import com.example.project_test6.Transaction;
import com.example.project_test6.TransactionAdapterHistory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HistoryFragment extends Fragment {

    private HistoryViewModel dashboardViewModel;
    private ArrayList<Transaction> transactions;

    private RecyclerView recyclerView;
    private TransactionAdapterHistory adapter;
    private RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dRef;
    DatabaseReference userRef;
    FirebaseUser user;
    String uid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        transactions = new ArrayList<>();

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        dRef = firebaseDatabase.getReference().child("users").child(uid).child("Transactions");
        userRef = firebaseDatabase.getReference().child("users").child(uid);

        dRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsBitch: dataSnapshot.getChildren()){
                    Map map = (Map)dsBitch.getValue();
                    String amount = String.valueOf(map.get("amount"));
                    String cate = String.valueOf(map.get("category"));
                    String timestamp = String.valueOf(map.get("timestamp"));
                    String type = String.valueOf(map.get("type"));

                    Log.e(TAG, amount);
                    Log.e(TAG, cate);
                    Log.e(TAG, timestamp);
                    Log.e(TAG, type);

                    Double am = Double.parseDouble(amount);

                    Date date = null;
                    try {
                        date = new SimpleDateFormat("yyyy.MM.dd").parse(timestamp);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    createTransaction(date,type,cate, am);

                }

                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });

        return root;
    }
    public void removeItem(int position){
        transactions.remove(position);
//        String uid = user.getUid();
//        dRef.child("users").child(uid).child("Transactions").setValue(transactions);
        adapter.notifyItemRemoved(position);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createTransaction(Date date, String type, String category, double amount){
        transactions.add(new Transaction(date,type,category,amount));
    }

}