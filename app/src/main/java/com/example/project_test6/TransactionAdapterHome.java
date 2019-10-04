package com.example.project_test6;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionAdapterHome extends RecyclerView.Adapter <TransactionAdapterHome.ViewHolder>{

    //Variables to be used for the adapter
    private ArrayList<Transaction> transactions;
    private TransactionAdapterHome.onItemClickListener listener;

    //Interface for onclick listener
    public interface onItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(TransactionAdapterHome.onItemClickListener listener){
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // UI display's variables
        public TextView type;
        public TextView category;
        public TextView amount;
        public CardView card;

        public ViewHolder(@NonNull View itemView, final TransactionAdapterHome.onItemClickListener listener) {
            super(itemView);
            //Initialize variables
            type = itemView.findViewById(R.id.type);
            category = itemView.findViewById(R.id.category);
            amount = itemView.findViewById(R.id.amount);
            card = itemView.findViewById(R.id.card2);

            //Set onclick listener setting to the items in the adapter
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int postion = getAdapterPosition();
                        if (postion != RecyclerView.NO_POSITION){
                            listener.onItemClick(postion);
                        }
                    }
                }
            });
        }
    }

    //Specifying what data that needs to be put into the adapter
    public TransactionAdapterHome(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    //Bind the adapter to a template display
    @NonNull
    public TransactionAdapterHome.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.template2,parent,false);
        TransactionAdapterHome.ViewHolder vh = new TransactionAdapterHome.ViewHolder(v,listener);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction current_transaction = this.transactions.get(position);
        holder.type.setText(current_transaction.getType());
        holder.category.setText(current_transaction.getCategory());
        if (holder.type.getText().toString().equals("Income")){
            holder.amount.setText("+"+current_transaction.getAmount());
            holder.amount.setTextColor(Color.GREEN);
        }else{
            holder.amount.setText("-"+current_transaction.getAmount());
            holder.amount.setTextColor(Color.RED);
        }

    }
    public int getItemCount() {
        return transactions.size();
    }

}
