package com.example.project_test6.ui.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_test6.R;
import com.example.project_test6.Saving;
import com.example.project_test6.Transaction;
import com.example.project_test6.TransactionAdapterHome;
import com.example.project_test6.insert_form;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    // Variables for recycler view transactions adapter
    private ArrayList<Transaction> transactions;
    private RecyclerView recyclerView;
    private TransactionAdapterHome adapter;
    private RecyclerView.LayoutManager layoutManager;

    // UI display's variables
    private TextView acc_balance;
    private TextView bud_left;
    private TextView total_saving;
    private TextView saving_goal;
    private TextView buffer;
    private TextView avgDay;
    private TextView avgMonth;
    private TextView avgYear;
    private Button updateButton;
    private String testNextDayLast;

    //Variables used for calculations
    double currentSaving;
    double fixed_dedic_spend;
    double fixed_dedic_save;
    double dedic_to_spend;
    double dedic_to_saving;
    double buffer_amount;
    double avgSaving;
    ArrayList<Saving> savingList = new ArrayList<Saving>();

    //Database variables
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dRef;
    DatabaseReference userRef;
    FirebaseUser user;
    String uid;
    Date currentTime;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //Floating button on homepage used for adding new transactions
        ImageView img = (ImageView) root.findViewById(R.id.button_insert);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), insert_form.class);
                startActivity(intent);
            }
        });

        //Initialize variables
        currentTime = Calendar.getInstance().getTime();
        acc_balance = root.findViewById(R.id.currentBalance);
        bud_left = root.findViewById(R.id.budget_left_amount);
        saving_goal = root.findViewById(R.id.saving_Goal_Amount);
        total_saving = root.findViewById(R.id.saving_Total_Amount);
        buffer = root.findViewById(R.id.Buffer_Amount);
        avgDay = root.findViewById(R.id.amount_day);
        avgMonth = root.findViewById(R.id.amount_month);
        avgYear = root.findViewById(R.id.amount_year);

        //Initialize variables for database
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        dRef = firebaseDatabase.getReference().child("users").child(uid).child("Transactions");
        userRef = firebaseDatabase.getReference().child("users").child(uid);

        //Initialize variables for recycler view transactions adapter
        transactions = new ArrayList<>();
        recyclerView = (RecyclerView) root.findViewById(R.id.recent_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TransactionAdapterHome(transactions);
        updateButton = root.findViewById(R.id.update);

        //Read in data from database for creating transaction object
        dRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsBitch : dataSnapshot.getChildren()) {
                    Map map = (Map) dsBitch.getValue();
                    String amount = String.valueOf(map.get("amount"));
                    String cate = String.valueOf(map.get("category"));
                    String timestamp = String.valueOf(map.get("timestamp"));
                    String type = String.valueOf(map.get("type"));
                    testNextDayLast = timestamp;
                    Double am = Double.parseDouble(amount);

                    //Check values
                    Log.e(TAG, amount);
                    Log.e(TAG, cate);
                    Log.e(TAG, timestamp);
                    Log.e(TAG, type);

                    //Parsing the date
                    Date date = null;
                    try {
                        date = new SimpleDateFormat("yyyy.MM.dd").parse(timestamp);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //Putting transactions object created from the database in a list
                    createTransaction(date, type, cate, am);

                    //Limit the transaction display to 4 items
                    if (transactions.size() > 4) {
                        transactions.remove(0);
                    }

                    //Update button that refresh data displayed, can only be pushed when the date changes
                    updateButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
                            Date nextDate = null;
                            try {
                                nextDate = simpleDateFormat.parse(testNextDayLast);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            Date check = new Date();

                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                            try {
                                check = formatter.parse(check.toString());//catch exception
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            long diff = check.getTime() - nextDate.getTime();
                            long seconds = diff / 1000;
                            long minutes = seconds / 60;
                            long hours = minutes / 60;
                            long days = hours / 24;

                            //Check values
                            Log.e("DIF", String.valueOf(diff));
                            Log.e("Seconds", String.valueOf(seconds));
                            Log.e("Minutes", String.valueOf(minutes));
                            Log.e("Hours", String.valueOf(hours));
                            Log.e("Day", String.valueOf(days));

                            //Check condition if date is different
                            if (days != 0) {
                                updateButton.setVisibility(View.VISIBLE);
                                calAverageSav();
                            } else {
                                updateButton.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "Can only add next day!",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
                //Put the transactions list in the adapter
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Read from the database
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Map map = (Map) dataSnapshot.getValue();
                String balance = String.valueOf(map.get("balance"));
                String daily_budget = String.valueOf(map.get("daily_budget_remain"));
                String saving_remain = String.valueOf(map.get("saving_remain"));
                String totalSave = String.valueOf(map.get("total_saving"));
                String Buffer = String.valueOf(map.get("buffer"));
                String Day_average = String.valueOf(map.get("avgSaving"));

                //Reformatting the data type
                buffer_amount = Double.parseDouble(String.valueOf(map.get("buffer")));
                avgSaving = Double.parseDouble(String.valueOf(map.get("avgSaving")));
                currentSaving = Double.parseDouble(String.valueOf(map.get("total_saving")));
                dedic_to_saving = Double.parseDouble(String.valueOf(map.get("saving_remain")));
                dedic_to_spend = Double.parseDouble(String.valueOf(map.get("daily_budget_remain")));
                fixed_dedic_save = Double.parseDouble(String.valueOf(map.get("saving_goal")));
                fixed_dedic_spend = Double.parseDouble(String.valueOf(map.get("daily_budget")));
                fixed_dedic_spend -= fixed_dedic_save;

                //Setting the display
                acc_balance.setText(balance);
                bud_left.setText(daily_budget);
                total_saving.setText(totalSave);
                saving_goal.setText(saving_remain);
                buffer.setText(Buffer);
                avgDay.setText("+" + Day_average);
                double Day = Double.parseDouble(Day_average);
                double Month = Day * 30;
                double Year = Day * 365;
                avgMonth.setText("+" + Month);
                avgYear.setText("+" + Year);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        return root;
    }

    //Method for creating and putting transactions into a list
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createTransaction(Date date, String type, String category, double amount) {
        transactions.add(new Transaction(date, type, category, amount));
    }

    //Calculate the average savings
    public void calAverageSav() {
        //calculate the average saving
        double savingSum = 0;
        if (savingList != null) {
            for (Saving saving : savingList) {
                savingSum += saving.getAmount();
            }
        } else {
            Log.e(TAG, "Saving list is Empty");
        }

        savingSum += dedic_to_saving;
        avgSaving = savingSum / (savingList.size() + 1);

        //update the current saving and buffer
        currentSaving += dedic_to_saving;
        buffer_amount += dedic_to_spend;

        //reset the amount dedicated to spending and saving back to their fixed amounts.
        dedic_to_spend = fixed_dedic_spend;
        dedic_to_saving = fixed_dedic_save;

        //Call the update methods to update the data to the database
        updateBudgetLeft(dedic_to_spend);
        updateTotalSaving(currentSaving);
        updateSaving(dedic_to_saving);
        updateBuffer(buffer_amount);
        updateDailySaving(avgSaving);

    }

    //Methods used to update data to the database
    public void updateBudgetLeft(double delta) {
        //Update them budget
        userRef.child("daily_budget_remain").setValue(delta);

    }

    public void updateSaving(double delta) {
        //Update them saving
        userRef.child("saving_remain").setValue(delta);
    }

    public void updateDailySaving(double delta) {
        //update them daily saving average
        userRef.child("avgSaving").setValue(delta);
    }

    public void updateBuffer(double delta) {
        //update them buffer
        userRef.child("buffer").setValue(delta);
    }

    public void updateTotalSaving(double delta) {
        //update them buffer
        userRef.child("total_saving").setValue(delta);
    }

}