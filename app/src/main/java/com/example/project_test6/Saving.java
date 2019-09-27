package com.example.project_test6;

import java.sql.Timestamp;

public class Saving {
    private Timestamp timestamp;
    private double amount;

    public Saving(Timestamp timestamp, double amount) {
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public double getAmount() {
        return amount;
    }
}
