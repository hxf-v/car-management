package cn.edu.ahu.carmanagement.Domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: FZC
 * @Despriction: 车辆在校状态实体
 * @Date:Created in 15:08 2019/4/22
 */
@Entity
@Table(name = "schoolstatusrecord")
public class SchoolStatusRecord {

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

    @Column(name = "in_time")
    private Date inTime;                    //进入时间

    @Column(name = "out_time")
    private Date outTime;                   //离开时间

    @Column(name = "in_school_time")
    private Date inSchoolTime;              //在校总时长

    @Column(name = "normal_accounting")
    private String normalAccounting;        //正常计费（登记在册车辆为月卡）

    @Column(name = "state")
    private String state;                   //在校状态

    @Transient
    private String inTimeStr;

    @Transient
    private String outTimeStr;

    @Transient
    private String inSchoolTimeStr;

    public String getInTimeStr() {
        return inTimeStr;
    }

    public void setInTimeStr(String inTimeStr) {
        this.inTimeStr = inTimeStr;
    }

    public String getOutTimeStr() {
        return outTimeStr;
    }

    public void setOutTimeStr(String outTimeStr) {
        this.outTimeStr = outTimeStr;
    }

    public String getInSchoolTimeStr() {
        return inSchoolTimeStr;
    }

    public void setInSchoolTimeStr(String inSchoolTimeStr) {
        this.inSchoolTimeStr = inSchoolTimeStr;
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

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getNormalAccounting() {
        return normalAccounting;
    }

    public void setNormalAccounting(String normalAccounting) {
        this.normalAccounting = normalAccounting;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getInSchoolTime() {
        return inSchoolTime;
    }

    public void setInSchoolTime(Date inSchoolTime) {
        this.inSchoolTime = inSchoolTime;
    }
}
