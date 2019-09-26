package com.example.project_test6;

public class Ach {
    private String ach;
    private String condition;
    private int counter;
    public Ach(String ach, String condition, int counter){
        this.ach = ach;
        this.condition = condition;
        this.counter = counter;
    }

    public String getAch() {
        return ach;
    }

    public String getCondition() {
        return condition;
    }

    public String getCounter() {
        return String.valueOf((counter));
    }
}
