package com.example.a.ubi;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "device_doa")
public class DeviceModelDao {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "id_device")
    private String id_device;

    @Property(nameInDb = "order_date")
    private String order_date;

    @Property(nameInDb = "expire_date")
    private String expire_date;

    @Property(nameInDb = "heart_rate")
    private String heart_rate;

    @Property(nameInDb = "respiratory")
    private String respiratory;

    @Property(nameInDb = "Bo2")
    private String Bo2;

    @Property(nameInDb = "posture")
    private String posture;

    @Property(nameInDb = "Steps")
    private String Steps;


    public DeviceModelDao(String id,String order_date, String expire_date, String heart_rate, String respiratory, String bo2, String posture, String steps) {
        this.id_device=id;
        this.order_date = order_date;
        this.expire_date = expire_date;
        this.heart_rate = heart_rate;
        this.respiratory = respiratory;
        Bo2 = bo2;
        this.posture = posture;
        Steps = steps;
    }



    @Generated(hash = 1652100632)
    public DeviceModelDao(Long id, String id_device, String order_date, String expire_date, String heart_rate, String respiratory, String Bo2, String posture,
            String Steps) {
        this.id = id;
        this.id_device = id_device;
        this.order_date = order_date;
        this.expire_date = expire_date;
        this.heart_rate = heart_rate;
        this.respiratory = respiratory;
        this.Bo2 = Bo2;
        this.posture = posture;
        this.Steps = Steps;
    }



    @Generated(hash = 938377009)
    public DeviceModelDao() {
    }


    public String getId_device() {
        return id_device;
    }

    public void setId_device(String id_device) {
        this.id_device = id_device;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "DeviceModelDao{" +
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
