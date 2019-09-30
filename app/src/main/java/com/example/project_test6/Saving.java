package com.example.project_test6;

import java.sql.Timestamp;
import java.util.Date;

public class Saving {
    private String timestamp;
    private double amount;
    private boolean status;

    public Saving(){}

    public Saving(String timestamp, double amount,boolean status) {
        this.timestamp = timestamp;
        this.amount = amount;
        this.status = false;
    }


    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }
    public boolean getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Saving{" +
                "timestamp=" + timestamp +
                ", amount=" + amount +
                '}';
    }
}
