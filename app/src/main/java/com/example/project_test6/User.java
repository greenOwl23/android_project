package com.example.project_test6;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

//Constructor for user object
@IgnoreExtraProperties
public class User {
    private double daily_budget;
    public String email;
    public double balance;
    public String displayName;
    public double saving_goal;
    public double saving_remain;
    public int lvl;
    public Transaction[] transaction;
    public String[] categories;
    public double daily_budget_remain;
    public double buffer;
    public double total_saving;
    public double avgSaving;
    public ArrayList<Double> savings;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String displayName) {
        this.displayName = displayName;
        this.balance = 0;
        this.daily_budget = 0;
        this.daily_budget_remain = 0;
        this.saving_goal = 0;
        this.lvl = 0;
        this.buffer = 0;
        this.total_saving = 0;
        this.savings = new ArrayList<Double>();
        this.avgSaving = 0;
        this.saving_remain = 0;
    }

    public void setSaving_goal(double saving_goal) {
        this.saving_goal = saving_goal;
    }

    public double getSaving_remain() {
        return saving_remain;
    }

    public void setSavings(ArrayList<Double> savings) {
        this.savings = savings;
    }

    public ArrayList<Double> getSavings() {
        return savings;
    }

    public void setAvgSaving(double avgSaving) {
        this.avgSaving = avgSaving;
    }


    public double getAvgSaving() {
        return avgSaving;
    }

    public double getDaily_budget() {
        return daily_budget;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getSaving_goal() {
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

    public double getDaily_budget_remain() {
        return daily_budget_remain;
    }

    public double getBuffer() {
        return buffer;
    }

    public double getTotal_saving() {
        return total_saving;
    }

    public void setDaily_budget(double daily_budget) {
        this.daily_budget = daily_budget;
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


    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setTransaction(Transaction[] transaction) {
        this.transaction = transaction;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public void setDaily_budget_remain(double daily_budget_remain) {
        this.daily_budget_remain = daily_budget_remain;
    }

    public void setBuffer(double buffer) {
        this.buffer = buffer;
    }

    public void setTotal_saving(double total_saving) {
        this.total_saving = total_saving;
    }
}
