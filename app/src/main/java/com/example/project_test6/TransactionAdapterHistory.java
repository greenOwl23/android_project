package com.example.project_test6;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionAdapterHistory extends RecyclerView.Adapter <TransactionAdapterHistory.ViewHolder> {

    //Variables to be used for the adapter
    private ArrayList<Transaction> transactions;
    private onItemClickListener listener;

    //Interface for onclick listener
    public interface onItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // UI display's variables
        public TextView timestamp;
        public TextView type;
        public TextView category;
        public TextView amount;
        public CardView card;
        public ImageView delete_Button;

        public ViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            //Initialize variables
            timestamp = itemView.findViewById(R.id.timestamp);
            type = itemView.findViewById(R.id.type);
            category = itemView.findViewById(R.id.category);
            amount = itemView.findViewById(R.id.amount);
            card = itemView.findViewById(R.id.card1);
            delete_Button = itemView.findViewById(R.id.delete_Button);

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

            //Set onclick listener setting to the remove button in the adapter
            delete_Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int postion = getAdapterPosition();
                        if (postion != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(postion);
                        }
                    }
                }
            });
        }
    }

    //Specifying what data that needs to be put into the adapter
    public TransactionAdapterHistory(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    //Bind the adapter to a template display
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.template,parent,false);
        ViewHolder vh = new ViewHolder(v,listener);
        return vh;
    }

    //Displaying the content to the binded template display
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction current_transaction = this.transactions.get(position);
        holder.timestamp.setText(current_transaction.getTimestamp());
        String prev = position==0? "":this.transactions.get(position-1).getTimestamp();

        if (holder.timestamp.getText().equals(prev)){
            holder.timestamp.setVisibility(View.GONE);
            holder.card.setLayoutParams(new RelativeLayout.LayoutParams(0, 0));
        }
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

    @Override
    public int getItemCount() {
        return transactions.size();
    }


}
