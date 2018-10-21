package com.example.a.ubi;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "patient_dao")
public class RegisterUserModel {
    @Id(autoincrement = true)
    private Long id;


    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "pathImg")
    private String pathImg;

    @Property(nameInDb = "gender")
    private String gender;

    @Property(nameInDb = "birthDate")
    private String birthDate;

    @Property(nameInDb = "address")
    private String address;

    @Property(nameInDb = "phone")
    private String phone;

    @Property(nameInDb = "insurence")
    private String insurence;

    @Property(nameInDb = "number_insurence")
    private String number_insurence;

    @Property(nameInDb = "natinal_code")
    private String natinal_code;

    @Property(nameInDb = "reasons_visit")
    private String reasons_visit;

    @Property(nameInDb = "condition")
    private String condition;

    @Property(nameInDb = "device")
    private String device;


    public RegisterUserModel(String name, String pathImg, String gender, String birthDate, String address, String phone, String insurence, String number_insurence, String natinal_code, String reasons_visit, String condition, String device) {
        this.name = name;
        this.pathImg = pathImg;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.insurence = insurence;
        this.number_insurence = number_insurence;
        this.natinal_code = natinal_code;
        this.reasons_visit = reasons_visit;
        this.condition = condition;
        this.device = device;
    }


    @Generated(hash = 1130643958)
    public RegisterUserModel(Long id, String name, String pathImg, String gender, String birthDate, String address, String phone, String insurence, String number_insurence, String natinal_code, String reasons_visit, String condition,
            String device) {
        this.id = id;
        this.name = name;
        this.pathImg = pathImg;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.insurence = insurence;
        this.number_insurence = number_insurence;
        this.natinal_code = natinal_code;
        this.reasons_visit = reasons_visit;
        this.condition = condition;
        this.device = device;
    }


    @Generated(hash = 814135406)
    public RegisterUserModel() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInsurence() {
        return insurence;
    }

    public void setInsurence(String insurence) {
        this.insurence = insurence;
    }

    public String getNumber_insurence() {
        return number_insurence;
    }

    public void setNumber_insurence(String number_insurence) {
        this.number_insurence = number_insurence;
    }

    public String getNatinal_code() {
        return natinal_code;
    }

    public void setNatinal_code(String natinal_code) {
        this.natinal_code = natinal_code;
    }

    public String getReasons_visit() {
        return reasons_visit;
    }

    public void setReasons_visit(String reasons_visit) {
        this.reasons_visit = reasons_visit;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }


    @Override
    public String toString() {
        return "RegisterUserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pathImg='" + pathImg + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", insurence='" + insurence + '\'' +
                ", number_insurance='" + number_insurence + '\'' +
                ", natinal_code='" + natinal_code + '\'' +
                ", reasons_visit='" + reasons_visit + '\'' +
                ", condition='" + condition + '\'' +
                ", device='" + device + '\'' +
                '}';
    }


}
