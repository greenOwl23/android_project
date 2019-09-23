package com.example.project_test6;

public class ach {
    private String ach;
    private String condition;
    private int counter;
    public ach(String ach, String condition, int counter){
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
