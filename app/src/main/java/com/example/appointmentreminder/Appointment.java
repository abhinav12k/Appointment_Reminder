package com.example.appointmentreminder;

public class Appointment {

    public String name;
    public String type;
    public String monthDate;
    public int dayDate;
    public int yearDate;
    public int hourTime;
    public int minuteTime;
    public String AMorPMTime;

    public Appointment(String appointmentName,String appointmentType,String appointmentMonth,int appointmentDay,int appointmentYear,int hour,int minute,String AMorPMtime){

        name = appointmentName;
        type = appointmentType;
        monthDate = appointmentMonth;
        dayDate = appointmentDay;
        yearDate = appointmentYear;
        hourTime = hour;
        minuteTime = minute;
        AMorPMTime = AMorPMtime;

    }
    


}
