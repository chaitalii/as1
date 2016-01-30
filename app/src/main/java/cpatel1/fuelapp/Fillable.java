package cpatel1.fuelapp;

import java.util.Date;

/**
 * Created by chaitali on 16-01-26.
 */
public interface Fillable {

    String getDate();
    String getStation();
    String getGrade();
    float getOdometer();
    float getAmount();
    float getUnit();
    float getCost();

}
