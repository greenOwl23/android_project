package com.example.project_test6;

import java.sql.Timestamp;
import java.util.Date;

public class Saving {
    private String timestamp;
    private double amount;
    private boolean isSaved;

    public Saving(){}

    public Saving(String timestamp, double amount,boolean isSaved) {
        this.timestamp = timestamp;
        this.amount = amount;
        this.isSaved = isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }
    public boolean isSaved() {
        return isSaved;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Saving{" +
                "timestamp=" + timestamp +
                ", amount=" + amount +
                ", isSaved=" + isSaved +
                '}';
    }
}
