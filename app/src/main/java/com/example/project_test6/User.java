package com.example.project_test6;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class User {
//    public String username;
    public String email;
    public double balance;
    public String displayName;
    public int daily_budget;
    public int saving_goal;
    public int lvl;
    public Transaction[] transaction;
    public String[] categories;
    public Saving[] savings;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String displayName) {
        this.displayName = displayName;
        this.balance = 0;
        this.daily_budget =0;
        this.saving_goal =0;
        this.lvl=0;
    }

    public String getEmail() {
        return email;
    }

    public void setSavings(Saving[] savings) {
        this.savings = savings;
    }

    public double getBalance() {
        return balance;
    }

    public Saving[] getSavings() {
        return savings;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getDaily_budget() {
        return daily_budget;
    }

    public int getSaving_goal() {
        return saving_goal;
    }

    public int getLvl() {
        return lvl;
    }

    public Transaction[] getTransaction() {
        return transaction;
    }

    public String[] getCategories() {
        return categories;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
//=======
//>>>>>>> 502ab1d3799a5e9322e55aea1a02346bda5255a2

    public void setDaily_budget(int daily_budget) {
        this.daily_budget = daily_budget;
    }

    public void setSaving_goal(int saving_goal) {
        this.saving_goal = saving_goal;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setTransaction(Transaction[] transaction) {
        this.transaction = transaction;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }
}
