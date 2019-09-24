package com.example.project_test6;

import java.util.ArrayList;

public class user {
    private String username;
    private String password;
    private int daily_budget;
    private int saving_goal;
    private int lvl;
    private int balance;
    private Transaction transaction[];

    ArrayList<Category> categories = createCategories();

    public ArrayList<Category> createCategories(){
        categories.add(new Category("Food"));
        categories.add(new Category("Car"));
        categories.add(new Category("Health"));
        categories.add(new Category("Shopping"));
        categories.add(new Category("Entertainment"));
        categories.add(new Category("Utility"));

        return categories;
    }

//    public user(String username, String password){
//        this.username = username;
//        this.password = password;
//
//    }

    public user(int daily_budget, int saving_goal, int lvl, int balance, Transaction transaction[]){
     this.daily_budget = daily_budget;
     this.saving_goal = saving_goal;
     this.lvl = lvl;
     this.balance = balance;
     this.transaction = transaction;
    }

}
