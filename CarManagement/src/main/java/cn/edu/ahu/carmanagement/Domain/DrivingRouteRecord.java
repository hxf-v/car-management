package cn.edu.ahu.carmanagement.Domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: FZC
 * @Despriction: 汽车行驶路线记录实体
 * @Date:Created in 14:39 2019/4/25
 */
@Entity
@Table(name = "drivingrouterecord")
public class DrivingRouteRecord {

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

    @Column(name = "camera")
    private String camera;                  //经过摄像头名称

    @Column(name = "longitude")
    private String longitude;               //摄像头经度

    @Column(name = "latitude")
    private String latitude;                //摄像头纬度

    @Column(name = "passing_time")
    private Date passingTime;               //经过时间

    @Column(name = "state")
    private String state;                   //在校状态

    @Transient
    private String passingTimeStr;

    public String getPassingTimeStr() {
        return passingTimeStr;
    }

    public void setPassingTimeStr(String passingTimeStr) {
        this.passingTimeStr = passingTimeStr;
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

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Date getPassingTime() {
        return passingTime;
    }

    public void setPassingTime(Date passingTime) {
        this.passingTime = passingTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
