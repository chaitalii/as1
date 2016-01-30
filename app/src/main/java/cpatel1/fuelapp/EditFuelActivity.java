package cpatel1.fuelapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
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

public class EditFuelActivity extends AppCompatActivity implements View.OnClickListener {


    /* position of entry to be editted */
    int pos;

    private EditText entryDate;


    /* DatePicker */
    private DatePickerDialog entryDatePickerDialog;
    private SimpleDateFormat dateFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fuel);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent edit = getIntent();
        pos = edit.getIntExtra("position", 0);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);


        findViewsById();
        setDateTimeField();
        entryDate = (EditText) findViewById(R.id.editDate);


    }

    @Override
    protected void onStart() {
        super.onStart();
        loadValues();
    }

    public void deleteEntry(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Log Deleted!";
        int duration = Toast.LENGTH_SHORT;
        FuelTrackActivity.fills.remove(pos);
        Toast.makeText(context, text, duration).show();
        //FuelTrackActivity.fills.removeAll(FuelTrackActivity.fills);
        saveInFile();
        finish();
    }

    protected void loadValues() {

        /* get values to be edited and fill boxes */
        EditText date = (EditText) findViewById(R.id.editDate);
        EditText station = (EditText) findViewById(R.id.editStation);
        EditText grade = (EditText) findViewById(R.id.editGrade);
        EditText odometer = (EditText) findViewById(R.id.editOdometer);
        EditText amount = (EditText) findViewById(R.id.editAmount);
        EditText unit = (EditText) findViewById(R.id.editUnitCost);

        /* append data into EditText box */
        date.append(FuelTrackActivity.fills.get(pos).getDate());
        station.append(FuelTrackActivity.fills.get(pos).getStation());
        grade.append(FuelTrackActivity.fills.get(pos).getGrade());
        odometer.append(Float.toString(FuelTrackActivity.fills.get(pos).getOdometer()));
        amount.append(Float.toString(FuelTrackActivity.fills.get(pos).getAmount()));
        unit.append(Float.toString(FuelTrackActivity.fills.get(pos).getUnit()));

    }

    public void saveEntry(View view) {

        float odometer = 0;
        float amount = 0;
        float cost = 0;
        float unit = 0;

        /* get EditText boxes */
        EditText entryStation = (EditText) findViewById(R.id.editStation);
        EditText entryGrade = (EditText) findViewById(R.id.editGrade);
        EditText entryOdometer = (EditText) findViewById(R.id.editOdometer);
        EditText entryAmount = (EditText) findViewById(R.id.editAmount);
        EditText entryUnit = (EditText) findViewById(R.id.editUnitCost);

        /* get string from EditText */
        String date = entryDate.getText().toString();
        String station = entryStation.getText().toString();
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


        /* delete old entry at position and add edited one */
        FillUp newestLog = new LogItem(date, station, grade, odometer, amount, cost, unit);
        FuelTrackActivity.fills.remove(pos);
        FuelTrackActivity.fills.add(pos, newestLog);

        /* toast message */
        Context context = getApplicationContext();
        CharSequence text = "Log Saved!";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();

        /* save and exit activity */
        saveInFile();
        finish();


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

    // Took from http://androidopentutorials.com/android-datepickerdialog-on-edittext-click-event/ on Jan 25/16
    private void findViewsById() {
        entryDate = (EditText) findViewById(R.id.editDate);
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

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }


    public void onClick(View view) {
        if (view == entryDate) {
            entryDatePickerDialog.show();
        }
    }

}

