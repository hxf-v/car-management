package cn.edu.ahu.carmanagement.Vo;

/**
 * @Author: FZC
 * @Despriction:
 * @Date:Created in 15:13 2019/6/13
 */
public class SchoolStatusRecordVo {

    private Integer id;                     //主键id
    private String plateNum;                //车牌号
    private String register;                //是否登记在册
    private String owner;                   //车主
    private String brand;                   //品牌
    private String color;                   //颜色
    private String department;              //所属院系或部门
    private String phoneNum;                //电话号码
    private String inTimeStr;               //进入时间
    private String outTimeStr;              //离开时间
    private String inSchoolTimeStr;         //在校总时长
    private String normalAccounting;        //正常计费（登记在册车辆为月卡）
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
}
