package cn.edu.ahu.carmanagement.Vo;

/**
 * @Author: FZC
 * @Despriction:
 * @Date:Created in 15:06 2019/6/14
 */
public class ParkingRecordVo {

    private Integer id;                     //主键id
    private String plateNum;                //车牌号
    private String register;                //是否登记在册
    private String owner;                   //车主
    private String brand;                   //品牌
    private String color;                   //颜色
    private String department;              //所属院系或部门
    private String phoneNum;                //电话号码
    private String parkingArea;             //停车区域
    private String stoppingTimeStr;         //停入时间Str
    private String departureTimeStr;        //离开时间Str
    private String parkingTimeStr;          //停车时长Str
    private String parkingLegal;            //停车是否合法（是、否）
    private String illegalAccounting;       //违规计费（停车不合法时有此计费）
    private String state;                   //在校状态

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
