package cpatel1.fuelapp;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by chaitali on 16-01-30.
 */
public class FuelListTest extends ActivityInstrumentationTestCase2{


    public FuelListTest() {
        super(FuelTrackActivity.class);
    }

    public void testAddFill(){
        FuelList fills = new FuelList();
        FillUp fill = new LogItem("2016-11-11", "costco", "regular", 500, 20, 10, 50);

        if(fills.hasFill(fill))
            throw new IllegalArgumentException("You already logged this entry");

        fills.add(fill);
        assertTrue(fills.hasFill(fill));

    }

    public void testHasFill(){
        FuelList fills = new FuelList();
        FillUp fill = new LogItem("2016-12-11", "Walmart", "extra", 8000, 30, 15, 50);

        assertFalse(fills.hasFill(fill));
        fills.add(fill);
        assertTrue(fills.hasFill(fill));

    }

    public void testGetFill(){
        FuelList fills = new FuelList();
        FillUp fill = new LogItem("2016-12-11", "Walmart", "extra", 8000, 30, 15, 50);

        fills.add(fill);
        FillUp returnedTweet = fills.getFill(0);

        assertEquals(returnedTweet.getStation(), fill.getStation());
        assertEquals(returnedTweet.getDate(), fill.getDate());
    }

    public void testGetTweets(){
        FuelList fills = new FuelList();
        FillUp fill = new LogItem("2016-12-11", "Walmart", "extra", 8000, 30, 15, 50);
        FillUp fill1 = new LogItem("2012-11-11", "costco", "regular", 500, 20, 10, 50);

        fills.add(fill);
        fills.add(fill1);

        assertEquals(fills.getFills(), fills.getFills());
    }

    public void testGetCount(){
        FuelList fills = new FuelList();
        FillUp fill = new LogItem ("2010-11-11", "costco", "regular", 500, 20, 10, 50);
        FillUp fill1 = new LogItem("2026-3-7", "Esso", "Diesel", 30000, 90, 45, 50);

        assertEquals(0, fills.getCount());

        fills.add(fill);
        fills.add(fill1);

        assertEquals(2,fills.getCount());
    }

    public void testDeleteTweet(){
        FuelList fills = new FuelList();
        FillUp fill = new LogItem("2010-11-11", "costco", "regular", 500, 20, 10, 50);

        fills.add(fill);
        fills.delete(fill);
        assertFalse(fills.hasFill(fill));
    }




}
