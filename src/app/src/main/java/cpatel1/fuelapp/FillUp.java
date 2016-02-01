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
    DecimalFormat df1 = new DecimalFormat("##.#");
    DecimalFormat df3 = new DecimalFormat("##.###");

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


    public String toString(){

        String costStr = String.format("%.2f",cost);

        return date + "     " + station + "\nTotal Cost: " +  "$" + costStr
                + "\nFuel Grade: " + grade + "   Odometer: " + df1.format(odometer) + " km" +
                "\nAmount Filled: " + df3.format(amount)  + " L" + "\nFuel Unit Cost: " + df1.format(unit) + " ¢/L";


    }


}
