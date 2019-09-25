package com.example.project_test6;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Transaction {
    private Date timestamp;
    private String type;
    private Category category;
    private  double amount;

    public Transaction (Date timestamp, String type, Category category, double amount){
        this.timestamp = timestamp;
        this.type = type;
        this.category = category;
        this.amount = amount;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getTimestamp() {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
        String formatted_date = ft.format(timestamp);
        return formatted_date;
    }

    public String getType() {
        return type;
    }

    public String  getCategory() {
        return category.getCategory();
    }

    public String  getAmount() { return String.valueOf((amount)); }
}
