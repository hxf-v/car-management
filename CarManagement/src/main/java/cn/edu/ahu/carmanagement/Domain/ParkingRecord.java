package cn.edu.ahu.carmanagement.Domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: FZC
 * @Despriction: 车辆停车记录实体
 * @Date:Created in 14:20 2019/4/25
 */
@Entity
@Table(name = "parkingrecord")
public class ParkingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;                     //主键id

    @Column(name = "plate_num")
    private String plateNum;                //车牌号

    @Column(name = "register")
    private String register;                //是否登记在册

    @Column(name = "owner")
    private String owner;                   //车主

    @Column(name = "brand")
    private String brand;                   //品牌

    @Column(name = "color")
    private String color;                   //颜色

    @Column(name = "department")
    private String department;              //所属院系或部门

    @Column(name = "phone_num")
    private String phoneNum;                //电话号码

    @Column(name = "parking_area")
    private String parkingArea;             //停车区域

    @Column(name = "stopping_time")
    private Date stoppingTime;              //停入时间

    @Column(name = "departure_time")
    private Date departureTime;             //离开时间

    @Column(name = "parking_time")
    private Date parkingTime;               //停车时长

    @Column(name = "parking_legal")
    private String parkingLegal;            //停车是否合法（是、否）

    @Column(name = "illegal_accounting")
    private String illegalAccounting;       //违规计费（停车不合法时有此计费）

    @Column(name = "state")
    private String state;                   //在校状态

    @Transient
    private String stoppingTimeStr;

    @Transient
    private String departureTimeStr;

    @Transient
    private String parkingTimeStr;

    public String getStoppingTimeStr() {
        return stoppingTimeStr;
    }

    public void setStoppingTimeStr(String stoppingTimeStr) {
        this.stoppingTimeStr = stoppingTimeStr;
    }

    public String getDepartureTimeStr() {
        return departureTimeStr;
    }

    public void setDepartureTimeStr(String departureTimeStr) {
        this.departureTimeStr = departureTimeStr;
    }

    public String getParkingTimeStr() {
        return parkingTimeStr;
    }

    public void setParkingTimeStr(String parkingTimeStr) {
        this.parkingTimeStr = parkingTimeStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getParkingArea() {
        return parkingArea;
    }

    public void setParkingArea(String parkingArea) {
        this.parkingArea = parkingArea;
    }

    public Date getStoppingTime() {
        return stoppingTime;
    }

    public void setStoppingTime(Date stoppingTime) {
        this.stoppingTime = stoppingTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(Date parkingTime) {
        this.parkingTime = parkingTime;
    }

    public String getParkingLegal() {
        return parkingLegal;
    }

    public void setParkingLegal(String parkingLegal) {
        this.parkingLegal = parkingLegal;
    }

    public String getIllegalAccounting() {
        return illegalAccounting;
    }

    public void setIllegalAccounting(String illegalAccounting) {
        this.illegalAccounting = illegalAccounting;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
