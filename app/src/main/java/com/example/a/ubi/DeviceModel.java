package com.example.a.ubi;

public class DeviceModel {

    private int id;
    private String order_date;
    private String expire_date;
    private String heart_rate;
    private String respiratory;
    private String Bo2;
    private String posture;
    private String Steps;


    public DeviceModel(int id, String order_date, String expire_date, String heart_rate, String respiratory, String bo2, String posture, String steps) {
        this.id = id;
        this.order_date = order_date;
        this.expire_date = expire_date;
        this.heart_rate = heart_rate;
        this.respiratory = respiratory;
        Bo2 = bo2;
        this.posture = posture;
        Steps = steps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }

    public String getheart_rate() {
        return heart_rate;
    }

    public void setheart_rate(String heart_rate) {
        this.heart_rate = heart_rate;
    }

    public String getRespiratory() {
        return respiratory;
    }

    public void setRespiratory(String respiratory) {
        this.respiratory = respiratory;
    }

    public String getBo2() {
        return Bo2;
    }

    public void setBo2(String bo2) {
        Bo2 = bo2;
    }

    public String getPosture() {
        return posture;
    }

    public void setPosture(String posture) {
        this.posture = posture;
    }

    public String getSteps() {
        return Steps;
    }

    public void setSteps(String steps) {
        Steps = steps;
    }

    @Override
    public String toString() {
        return "DeviceModel{" +
                "id=" + id +
                ", order_date='" + order_date + '\'' +
                ", expire_date='" + expire_date + '\'' +
                ", heart_rate='" + heart_rate + '\'' +
                ", respiratory='" + respiratory + '\'' +
                ", Bo2='" + Bo2 + '\'' +
                ", posture='" + posture + '\'' +
                ", Steps='" + Steps + '\'' +
                '}';
    }



}
