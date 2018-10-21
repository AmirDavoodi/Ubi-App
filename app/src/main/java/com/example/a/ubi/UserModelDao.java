package com.example.a.ubi;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "user_doa")
public class UserModelDao {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "age")
    private int age;


    @Property(nameInDb = "medicine")
    private String medicine;

    @Property(nameInDb = "health_info")
    private String health_info;

    @Property(nameInDb = "phone")
    private String phone;

    @Property(nameInDb = "heart_rate")
    private String heart_rate;

    @Property(nameInDb = "heart_rate_v")
    private String heart_rate_;

    @Property(nameInDb = "respiratory")
    private String respiratory;

    @Property(nameInDb = "Bo2")
    private String Bo2;

    @Property(nameInDb = "posture")
    private String posture;


    @Property(nameInDb = "Steps")
    private String Steps;


    public UserModelDao(String name, int age, String medicine, String health_info, String phone, String heart_rate, String heart_rate_, String respiratory, String bo2, String posture, String steps) {
        this.name = name;
        this.age = age;
        this.medicine = medicine;
        this.health_info = health_info;
        this.phone = phone;
        this.heart_rate = heart_rate;
        this.heart_rate_ = heart_rate_;
        this.respiratory = respiratory;
        Bo2 = bo2;
        this.posture = posture;
        Steps = steps;
    }


//    public UserModelDao(String name, int age, String medicine, String health_info, String phone, String heart_rate, String respiratory, String bo2, String posture, String steps) {
//        this.name = name;
//        this.age = age;
//        this.medicine = medicine;
//        this.health_info = health_info;
//        this.phone = phone;
//        this.heart_rate = heart_rate;
//        this.respiratory = respiratory;
//        Bo2 = bo2;
//        this.posture = posture;
//        Steps = steps;
//    }




    @Generated(hash = 1810680448)
    public UserModelDao(Long id, String name, int age, String medicine, String health_info, String phone, String heart_rate, String heart_rate_, String respiratory, String Bo2, String posture,
            String Steps) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.medicine = medicine;
        this.health_info = health_info;
        this.phone = phone;
        this.heart_rate = heart_rate;
        this.heart_rate_ = heart_rate_;
        this.respiratory = respiratory;
        this.Bo2 = Bo2;
        this.posture = posture;
        this.Steps = Steps;
    }


    @Generated(hash = 1046552287)
    public UserModelDao() {
    }




    public String getHeart_rate_() {
        return heart_rate_;
    }

    public void setHeart_rate_(String heart_rate_) {
        this.heart_rate_ = heart_rate_;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(String heart_rate) {
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
        return "UserModelDao{" +
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




    public Long getId() {
        return this.id;
    }




    public void setId(Long id) {
        this.id = id;
    }

}
