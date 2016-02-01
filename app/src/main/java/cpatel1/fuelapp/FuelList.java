package cpatel1.fuelapp;

import java.util.ArrayList;

/**
 * Created by chaitali on 16-01-30.
 */
public class FuelList {

    private ArrayList<FillUp> fills = new ArrayList<FillUp>();

    public void add(FillUp fill){
        fills.add(fill);
    }

    public boolean hasFill(FillUp fill){
        return fills.contains(fill);
    }

    public void delete(FillUp fill){
        fills.remove(fill);

    }

    public FillUp getFill(int index){
        return fills.get(index);
    }

    public ArrayList<FillUp> getFills(){
        return fills;
    }

    public int getCount(){
        return fills.size();
    }
}
