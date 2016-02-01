package cpatel1.fuelapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddFuelActivity extends AppCompatActivity implements View.OnClickListener {


    /* initialize EditText variables */
    private EditText entryDate;
    private EditText entryStation;
    private EditText entryGrade;
    private EditText entryOdometer;
    private EditText entryAmount;
    private EditText entryUnit;


    /* initialize DatePicker variables */
    private DatePickerDialog entryDatePickerDialog;
    private SimpleDateFormat dateFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fuel);

        /* for toolbar */
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        /* for DatePicker */
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        findViewsById();
        setDateTimeField();

        entryStation = (EditText) findViewById(R.id.stationText);
        entryDate = (EditText) findViewById(R.id.dateText);
        entryGrade = (EditText) findViewById(R.id.gradeText);
        entryOdometer = (EditText) findViewById(R.id.odometerText);
        entryAmount = (EditText) findViewById(R.id.amountText);
        entryUnit = (EditText) findViewById(R.id.unitText);

        
    }

    public void saveEntry(View view){

        /* initialize float variable */
        float odometer;
        float amount;
        float cost;
        float unit;

        /* get text from EditText */
        String station = entryStation.getText().toString();
        String date = entryDate.getText().toString();
        String grade = entryGrade.getText().toString();


        /* check for valid input */
        try {
            odometer = Float.valueOf(entryOdometer.getText().toString());
        } catch(NumberFormatException wrong){
            entryOdometer.setError("Invalid Input...");
            return;
        }

        try {
            amount = Float.valueOf(entryAmount.getText().toString());
        } catch(NumberFormatException wrong){
            entryAmount.setError("Invalid Input...");
            return;
        }

        try {
            unit = Float.valueOf(entryUnit.getText().toString());
        } catch(NumberFormatException wrong){
            entryUnit.setError("Invalid Input...");
            return;
        }

        cost = (amount * unit)/100;


        /* add new entry to fills */
        FillUp newestLog = new LogItem(date, station,grade, odometer, amount, cost, unit);
        FuelTrackActivity.fills.add(newestLog);

        /* toast message */
        Context context = getApplicationContext();
        CharSequence saved = "Log Saved!";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, saved, duration).show();

        /* end add activity */
        saveInFile();
        finish();


    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

    }


    // Took from http://androidopentutorials.com/android-datepickerdialog-on-edittext-click-event/ on Jan 25/16
    private void findViewsById() {
        entryDate = (EditText) findViewById(R.id.dateText);
        entryDate.setInputType(InputType.TYPE_NULL);
        entryDate.requestFocus();

    }

    private void setDateTimeField() {
        entryDate.setOnClickListener(this);


        Calendar newCalendar = Calendar.getInstance();
        entryDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                entryDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }





    @Override
    public void onClick(View view) {
        if(view == entryDate) {
            entryDatePickerDialog.show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_child, menu);
        return true;
    }

    protected void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FuelTrackActivity.FILENAME, 0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(FuelTrackActivity.fills, out);
            out.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }



}
