package com.example.appointmentreminder;

import android.os.Parcel;
import android.os.Parcelable;

public class Appointment implements Parcelable {

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

    protected Appointment(Parcel in){

        name = in.readString();
        type = in.readString();
        monthDate = in.readString();
        dayDate = in.readInt();
        yearDate = in.readInt();
        hourTime = in.readInt();
        minuteTime = in.readInt();
        AMorPMTime = in.readString();

    }


    public static final Creator<Appointment> CREATOR = new Creator<Appointment>() {
        @Override
        public Appointment createFromParcel(Parcel parcel) {
            return new Appointment(parcel);
        }

        @Override
        public Appointment[] newArray(int i) {
            return new Appointment[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(monthDate);
        dest.writeInt(dayDate);
        dest.writeInt(yearDate);
        dest.writeInt(hourTime);
        dest.writeInt(minuteTime);
        dest.writeString(AMorPMTime);
    }
}
