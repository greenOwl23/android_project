package com.example.project_test6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class achAdapter extends RecyclerView.Adapter <achAdapter.ViewHolder>{

    //Variables to be used for the adapter
    private ArrayList<Ach> aches;
    private onItemClickListener listener;

    //Interface for onclick listener
    public interface onItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(achAdapter.onItemClickListener listener){
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // UI display's variables
        public TextView ach;
        public TextView condition;
        public TextView counter;
        public CardView card;

        public ViewHolder(@NonNull View itemView, final achAdapter.onItemClickListener listener) {
            super(itemView);

            //Initialize variables
            ach = itemView.findViewById(R.id.ach);
            condition = itemView.findViewById(R.id.condition);
            counter = itemView.findViewById(R.id.counter);
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
    public achAdapter(ArrayList<Ach> aches) {
        this.aches = aches;
    }

    //Bind the adapter to a template display
    @NonNull
    public achAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.template3,parent,false);
        achAdapter.ViewHolder vh = new achAdapter.ViewHolder(v,listener);
        return vh;
    }

    //Displaying the content to the binded template display
    public void onBindViewHolder(@NonNull achAdapter.ViewHolder holder, int position) {
        Ach current_achievement = this.aches.get(position);
        holder.ach.setText(current_achievement.getAch());
        holder.condition.setText(current_achievement.getCondition());
        holder.counter.setText(current_achievement.getCounter());

    }
    public int getItemCount() {
        return aches.size();
    }
}
