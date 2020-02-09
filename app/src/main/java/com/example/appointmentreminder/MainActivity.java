package com.example.appointmentreminder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ArrayList<Appointment> appointmentArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            createSomeTestAppointmentsToStartWith();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelableArrayList("Appointment_ArrayList",appointmentArrayList);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        appointmentArrayList = savedInstanceState.getParcelableArrayList("Appointment_ArrayList");

        for(int i=0;i<appointmentArrayList.size();i++){
            populateTable(i);
        }

    }

    public void createSomeTestAppointmentsToStartWith(){

        appointmentArrayList.add(new Appointment("Doctor's Visit","Personal","Oct", 9, 2016, 9, 00, "AM"));
        appointmentArrayList.add(new Appointment("Hair Cut appointment","Personal","Oct", 10, 2016,9,30,"AM"));
        appointmentArrayList.add(new Appointment("Meeting with Accountant","Personal","Oct", 11, 2016,11,00,"AM"));
        appointmentArrayList.add(new Appointment("Boss/HR Meeting","Work","Oct", 12, 2016,2,30,"PM"));
        appointmentArrayList.add(new Appointment("Teacher Conference","School","Nov", 1, 2016,9,30,"AM"));
        appointmentArrayList.add(new Appointment("Dentist For Son","Health","Nov", 1, 2016,9,30,"AM"));
        appointmentArrayList.add(new Appointment("Dinner With Friends","Other","Nov", 1, 2016,9,30,"AM"));

        for(int i=0;i<appointmentArrayList.size();i++){
            populateTable(i);
        }
    }


    private String SetToDateTime(Appointment appointment){

        long currentDateAndTime = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d,yyyy");
        String todaysDate = dateFormat.format(currentDateAndTime);

        String passDate = appointment.monthDate +" " + appointment.dayDate +", " + appointment.yearDate; //Tasks date formated the same way

        if(Objects.equals(todaysDate, passDate)){ //Compare today's date and passed date, return time if dates match
            return appointment.hourTime +":" +appointment.minuteTime +" " +appointment.AMorPMTime;
        }
        return appointment.monthDate +" " + appointment.dayDate +", " + appointment.yearDate; //Otherwise, return the date

    }

    public void populateTable(int arrayListCounter){

        TableLayout appointmentTBL = findViewById(R.id.appointmentTable);
        TableRow newTableRow = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
        newTableRow.setLayoutParams(lp);

        TextView txtName = new TextView(this);
        txtName.setLayoutParams(lp);
        txtName.setText(appointmentArrayList.get(arrayListCounter).name);
        txtName.setWidth(140);
        txtName.setTextSize(18);
        txtName.setGravity(Gravity.CENTER);
        txtName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView txtType = new TextView(this);
        txtType.setLayoutParams(lp);
        txtType.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        txtType.setText(appointmentArrayList.get(arrayListCounter).type);
        txtType.setWidth(140);
        txtType.setTextSize(18);
        txtType.setGravity(Gravity.CENTER);

        TextView txtDate = new TextView(this);
        txtDate.setLayoutParams(lp);
        txtDate.setTextSize(18);
        txtDate.setGravity(Gravity.CENTER);
        txtDate.setWidth(140);
        txtDate.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        txtDate.setText(SetToDateTime(appointmentArrayList.get(arrayListCounter)));

        newTableRow.addView(txtName);
        newTableRow.addView(txtType);
        newTableRow.addView(txtDate);

        appointmentTBL.addView(newTableRow,arrayListCounter+1);

    }

    public void AddAppointmentBtn(View view){
        startActivityForResult(new Intent(this,Add_Appointment.class),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                appointmentArrayList.add(new Appointment( data.getStringExtra("name"),data.getStringExtra("type"),
                        data.getStringExtra("monthOfYear"), data.getIntExtra("dayOfMonth", 0), data.getIntExtra("year", 1111),
                        data.getIntExtra("hour", 11),data.getIntExtra("minute", 11),data.getStringExtra("AMorPM")));
                populateTable(appointmentArrayList.size()-1);
            }

        }

    }
}
