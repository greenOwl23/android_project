package com.example.project_test6;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter <TransactionAdapter.ViewHolder> {
    private ArrayList<Transaction> example;
    private onItemClickListener listener;

    public interface onItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView timestamp;
        public TextView type;
        public TextView category;
        public TextView amount;

        public ViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            timestamp = itemView.findViewById(R.id.timestamp);
            type = itemView.findViewById(R.id.type);

//            if (type.getText().toString().equals("Income")){
//                type.setTextColor(Color.GREEN);
//            }else{
//                type.setTextColor(Color.RED);
//            }

            category = itemView.findViewById(R.id.category);
            amount = itemView.findViewById(R.id.amount);

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

    public TransactionAdapter(ArrayList<Transaction> example) {
        this.example = example;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.template,parent,false);
        ViewHolder vh = new ViewHolder(v,listener);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction current_transaction = this.example.get(position);

        holder.timestamp.setText(current_transaction.getTimestamp());
        holder.type.setText(current_transaction.getType());
        holder.category.setText(current_transaction.getCategory());
        holder.amount.setText(current_transaction.getAmount());

    }

    @Override
    public int getItemCount() {
        return example.size();
    }


}
