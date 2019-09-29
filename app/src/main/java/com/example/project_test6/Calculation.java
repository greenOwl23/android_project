package com.example.project_test6;

import java.util.ArrayList;

public class Calculation {
    public double acc_bal;
    public double allowance_per_day;
    public double fixed_dedic_spend;
    public double fixed_dedic_save;
    public double dedic_to_spend;
    public double dedic_to_saving;
    public double buffer;
    public double current_saving;

    public Calculation(double acc_bal, double allowance_per_day, double dedic_to_saving){
        this.acc_bal=acc_bal;
        this.allowance_per_day=allowance_per_day;
        this.dedic_to_spend=allowance_per_day-dedic_to_saving;
        this. dedic_to_saving = dedic_to_saving;
        this.fixed_dedic_spend=dedic_to_spend;
        this.fixed_dedic_save=dedic_to_saving;
    }

    public ArrayList<Double> calTransac(String tranType, double trans){
        ArrayList<Double> toPush = new ArrayList<Double>();
        //expense
        if(tranType.equals("expense")){
            acc_bal-=trans;
            if(dedic_to_spend<0){
                //alert
                System.out.print("You have used up the budget for the day. Your expense will now be subtracted to your dedicated saving amount. ");
                dedic_to_spend=0;
                dedic_to_saving-=trans;
            }
            if(dedic_to_saving<0){
                //alert
                System.out.print("You have used up the amount dedicated to saving. Your expense will now be subtracted from buffer.");
                dedic_to_saving=0;
                buffer-=trans;

            }
            else{
                dedic_to_spend-=trans;
            }
        }
        //income
        else{
            acc_bal+=trans;
        }

        toPush.add(acc_bal);
        toPush.add(dedic_to_spend);
        toPush.add(dedic_to_saving);

        return toPush;
    }

    public ArrayList<Double> end_Of_Day_Cal(int date, ArrayList<Double> preSavings){//get the current time and date
        ArrayList<Double> toPush = new ArrayList<>();
        double savingSum=0;
        //Left over amount from dedicated to spending is added to the buffer
        if(dedic_to_spend>=0){
            buffer+=dedic_to_spend;
        }
        //Left over amount from dedicated to saving is added to current saving
        if(dedic_to_saving>=0){
            current_saving+=dedic_to_saving;
        }
        dedic_to_spend=fixed_dedic_spend;//reset back to dedicated spending ammount
        dedic_to_saving=fixed_dedic_save;//reset back to dedicated spending amount

        //loop to get the previous savings and add them up
        for(int i = 0; i<preSavings.size();i++){
            savingSum+=preSavings.get(i);
        }
        savingSum+=current_saving;
        double savingAvgPerDay = savingSum/7;
        //Add to arraylist to push back to database
        toPush.add(buffer);
        toPush.add(current_saving);
        toPush.add(dedic_to_spend);
        toPush.add(dedic_to_saving);
        toPush.add(savingAvgPerDay);

        return toPush;
    }
}
