
package com.test.alex.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class DaysModel implements Parcelable{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("teacher")
    @Expose
    private String teacher;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("endTime")
    @Expose
    private String endTime;
    @SerializedName("weekDay")
    @Expose
    private int weekDay;
    @SerializedName("appointment_id")
    @Expose
    private String appointmentId;
    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("pay")
    @Expose
    private boolean pay;
    @SerializedName("appointment")
    @Expose
    private boolean appointment;
    @SerializedName("teacher_v2")
    @Expose
    private TeacherV2 teacherV2;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("availability")
    @Expose
    private int availability;

    protected DaysModel(Parcel in) {
        name = in.readString();
        description = in.readString();
        place = in.readString();
        teacher = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        weekDay = in.readInt();
        appointmentId = in.readString();
        serviceId = in.readString();
        pay = in.readByte() != 0;
        appointment = in.readByte() != 0;
        color = in.readString();
        availability = in.readInt();
    }

    public static final Creator<DaysModel> CREATOR = new Creator<DaysModel>() {
        @Override
        public DaysModel createFromParcel(Parcel in) {
            return new DaysModel(in);
        }

        @Override
        public DaysModel[] newArray(int size) {
            return new DaysModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public boolean isPay() {
        return pay;
    }

    public void setPay(boolean pay) {
        this.pay = pay;
    }

    public boolean isAppointment() {
        return appointment;
    }

    public void setAppointment(boolean appointment) {
        this.appointment = appointment;
    }

    public TeacherV2 getTeacherV2() {
        return teacherV2;
    }

    public void setTeacherV2(TeacherV2 teacherV2) {
        this.teacherV2 = teacherV2;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Name : " + getName() + "\n" + "Day : " + getWeekDay();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(place);
        dest.writeString(teacher);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeInt(weekDay);
        dest.writeString(appointmentId);
        dest.writeString(serviceId);
        dest.writeByte((byte) (pay ? 1 : 0));
        dest.writeByte((byte) (appointment ? 1 : 0));
        dest.writeString(color);
        dest.writeInt(availability);
    }
}
