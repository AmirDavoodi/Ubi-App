package com.example.a.ubi;

public class UserModel {

    private int id;
    private String name;

    private int age;
    private String medicine;
    private String health_info;
    private String phone;
    private String heart_rate;
    private String respiratory;
    private String Bo2;
    private String posture;
    private String Steps;



    public UserModel(int id, String name, int age, String medicine, String health_info, String phone, String heart_rate, String respiratory, String bo2, String posture, String steps) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.medicine = medicine;
        this.health_info = health_info;
        this.phone = phone;
        this.heart_rate = heart_rate;
        this.respiratory = respiratory;
        Bo2 = bo2;
        this.posture = posture;
        Steps = steps;
    }


//    public UserModel(int id, String name, String medicine, String health_info, String phone, String heart_rate, String respiratory, String bo2, String posture, String steps) {
//        this.id = id;
//        this.name = name;
//        this.medicine = medicine;
//        this.health_info = health_info;
//        this.phone = phone;
//        this.heart_rate = heart_rate;
//        this.respiratory = respiratory;
//        Bo2 = bo2;
//        this.posture = posture;
//        Steps = steps;
//    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getHealth_info() {
        return health_info;
    }

    public void setHealth_info(String health_info) {
        this.health_info = health_info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(String heart_rate) {
        this.heart_rate = heart_rate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", medicine='" + medicine + '\'' +
                ", health_info='" + health_info + '\'' +
                ", phone='" + phone + '\'' +
                ", heart_rate='" + heart_rate + '\'' +
                ", respiratory='" + respiratory + '\'' +
                ", Bo2='" + Bo2 + '\'' +
                ", posture='" + posture + '\'' +
                ", Steps='" + Steps + '\'' +
                '}';
    }


}
