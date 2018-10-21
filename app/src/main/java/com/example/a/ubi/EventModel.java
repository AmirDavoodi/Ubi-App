package com.example.a.ubi;

public class EventModel {

    private String time;

    private String day;
    private String Date;
    private String Msg;



    public EventModel(String time, String day, String date, String msg) {
        this.time = time;
        this.day = day;
        Date = date;
        Msg = msg;
    }


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


    @Override
    public String toString() {
        return "EventModel{" +
                "time='" + time + '\'' +
                ", day='" + day + '\'' +
                ", Date='" + Date + '\'' +
                ", Msg='" + Msg + '\'' +
                '}';
    }




}
