package cpatel1.fuelapp;

import java.text.DecimalFormat;

/**
 * Created by chaitali on 16-01-24.
 */
public abstract class FillUp {

    private String date;
    protected String station;
    protected String grade;
    protected float odometer;
    protected float amount;
    protected float unit;
    protected float cost;
    DecimalFormat df1 = new DecimalFormat("#.#");
    DecimalFormat df3 = new DecimalFormat("#.###");
    DecimalFormat money = new DecimalFormat("#.##");



    public FillUp(String date, String station, String grade, float odometer, float amount, float cost, float unit) {
        this.date = date;
        this.station = station;
        this.amount = amount;
        this.grade = grade;
        this.odometer = odometer;
        this.unit = unit;
        this.cost = cost;
    }


    /* GETTERS */
    public String getDate() {
        return date;
    }

    public String getStation() {
        return station;
    }

    public String getGrade() {
        return grade;
    }
    public float getOdometer() {
        return odometer;
    }

    public float getAmount() {
        return amount;
    }

    public float getUnit() {
        return unit;
    }

    public float getCost() {
        return cost;
    }

    /* SETTERS*/

    public void setDate(String date) {
        this.date = date;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setOdometer(float odometer) {
        this.odometer = odometer;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setUnit(float unit) {
        this.unit = unit;
    }

    public void setCost(float cost, float amount, float unit) {
        this.cost = (amount * unit)/100;
    }


    public String toString(){

        try {
            return date + " | " + station + " | " + grade + " | " + df1.format(odometer) + " | " + df3.format(amount)  + " | " + df1.format(unit)  + " | " +  "$" + money.format(cost);
        }
        catch(NullPointerException not){
            return date + " | " + station + " | " + grade + " | " + odometer + " | " + amount + unit + cost;
        }

    }


}
