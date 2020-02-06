package com.example.appointmentreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Add_Appointment extends AppCompatActivity {

    TextView txtDate;
    TextView txtTime;

    private int year;
    private int month;
    private int date;

    private int hour;
    private int minute;

    static final int DATE_DIALOG_ID = 999;
    static final int TIME_DIALOG_ID = 998;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__appointment);

        setCurrentDateAndTimeOnView();
    }

    public void setCurrentDateAndTimeOnView() {

        txtDate = findViewById(R.id.tvDatepicker);
        txtTime = findViewById(R.id.tvTimepicker);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        date = c.get(Calendar.DAY_OF_MONTH);

        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        txtTime.setText(new StringBuilder().append(pad(hour)).append(":").append(pad(minute)));
        txtDate.setText(new StringBuilder().append(date).append("-").append(month + 1).append("-").append(year).append(" "));

    }

    private DatePickerDialog.OnDateSetListener datePickerListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDate) {
            year = selectedYear;
            month = selectedMonth;
            date = selectedDate;

            txtDate.setText(new StringBuilder().append(date).append("-").append(month+1).append("-").append(year).append(" "));
        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerListner = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
            hour = selectedHour;
            minute = selectedMinute;

            txtTime.setText(new StringBuilder().append(pad(hour)).append(":").append(pad(minute)));
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {

        switch (id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,datePickerListner,year,month,date);

            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,timePickerListner,hour,minute,false);
        }

        return null;
    }

    //methods for showing the dialogs for date and time pickers
    public void editTxtDate(View view){
        showDialog(DATE_DIALOG_ID);
    }

    public void editTxtTime(View view){
        showDialog(TIME_DIALOG_ID);
    }
    private String pad(int c) {

        if (c >= 10) {
            return String.valueOf(c);
        } else {
            return "0"+ String.valueOf(c);
        }

    }

    public void cancelMethod(View view){

        finish();

    }

    public void addAppointmentMethod(View view){

        EditText etName = findViewById(R.id.etName);
        Spinner spinner = findViewById(R.id.spinnerTaskType);

        if(!(etName.getText().toString()).isEmpty()){
            Intent intent = new Intent();

            intent.putExtra("name", etName.getText().toString());

            intent.putExtra("type", spinner.getSelectedItem().toString());

            intent.putExtra("monthOfYear", DisplayTheMonthInCharacters(month));
            intent.putExtra("dayOfMonth", date);
            intent.putExtra("year", year);

            intent.putExtra("hour", FormatTheHour(hour));
            intent.putExtra("minute", minute);
            intent.putExtra("AMorPM", AMorPM(hour));

            setResult(RESULT_OK, intent);

            finish();
        }
        else{
            Toast toast = Toast.makeText(Add_Appointment.this, "Please enter an Appointment Name", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    //helper methods

    //Returns the Month Abbreviation for the corresponding number.
    private String DisplayTheMonthInCharacters(int passedMonth){
        switch(passedMonth){
            case 0:
                return "Jan";
            case 1:
                return"Feb";
            case 2:
                return"Mar";
            case 3:
                return"Apr";
            case 4:
                return"May";
            case 5:
                return"Jun";
            case 6:
                return"Jul";
            case 7:
                return"Aug";
            case 8:
                return"Sept";
            case 9:
                return"Oct";
            case 10:
                return"Nov";
            case 11:
                return"Dec";

        }
        return "";
    }

    //Converts the 24 hours PassedHour to a 12 Hour time.
    private int FormatTheHour(int passedHour){
        if (passedHour > 12){ passedHour -= 12; }
        return passedHour;
    }

    //Returns AM or PM depending on the hour (1-24)
    private String AMorPM(int passedHour){
        if (passedHour > 12){ return "PM"; }
        else{ return "AM"; }
    }


}
