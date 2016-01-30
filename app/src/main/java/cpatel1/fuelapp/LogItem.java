package cpatel1.fuelapp;

import java.util.Date;

/**
 * Created by chaitali on 16-01-26.
 */
public class LogItem extends FillUp implements Fillable {

    public LogItem(String date, String station, String grade, float odometer, float amount, float cost, float unit) {
        super(date, station, grade, odometer, amount, cost, unit);
    }



}
