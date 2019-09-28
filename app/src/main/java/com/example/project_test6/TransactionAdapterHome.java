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

public class TransactionAdapterHome extends RecyclerView.Adapter <TransactionAdapterHistory.ViewHolder>{
    private ArrayList<Transaction> example;
    private TransactionAdapterHistory.onItemClickListener listener;


    public interface onItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(TransactionAdapterHistory.onItemClickListener listener){
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView type;
        public TextView category;
        public TextView amount;
        public CardView card;

        public ViewHolder(@NonNull View itemView, final TransactionAdapterHistory.onItemClickListener listener) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
            category = itemView.findViewById(R.id.category);
            amount = itemView.findViewById(R.id.amount);
            card = itemView.findViewById(R.id.card2);

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

    public TransactionAdapterHome(ArrayList<Transaction> example) {
        this.example = example;
    }

    @NonNull
    public TransactionAdapterHistory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.template2,parent,false);
        TransactionAdapterHistory.ViewHolder vh = new TransactionAdapterHistory.ViewHolder(v,listener);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onBindViewHolder(@NonNull TransactionAdapterHistory.ViewHolder holder, int position) {
        Transaction current_transaction = this.example.get(position);
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
        return example.size();
    }

}
