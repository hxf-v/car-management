package cn.edu.ahu.carmanagement.Json;

import cn.edu.ahu.carmanagement.Domain.CarRegister;

/**
 * @Author: FZC
 * @Despriction: 添加车辆所需Json
 * @Date: Created in 16:48 2019/4/22
 */
public class CarRegisterJson {

    private Integer id;             //主键id(notnull)
    private String xgh;             //学工号
    private String name;            //姓名
    private String department;      //所属院系或部门
    private String plateNum;        //车牌号
    private String brand;           //品牌
    private String color;           //颜色
    private String phoneNum;        //电话号码
    private String state;           //状态
    private String sf;              //身份（教师或学生）
    private String idNum;           //身份证号码

    private Integer pageNumber;     //页数
    private Integer pageSize;       //页面大小

    //添加新车辆信息所需Json
    public CarRegister convetAdd(){
        CarRegister carRegister = new CarRegister();

        carRegister.setXgh(getXgh());
        carRegister.setName(getName());
        carRegister.setDepartment(getDepartment());
        carRegister.setPlateNum(getPlateNum());
        carRegister.setBrand(getBrand());
        carRegister.setColor(getColor());
        carRegister.setPhoneNum(getPhoneNum());
        carRegister.setState("启用");

        return carRegister;
    }

    //修改车辆信息所需Json
    public CarRegister convetEdit(CarRegister carRegister){

        carRegister.setName(getName());
        carRegister.setDepartment(getDepartment());
        carRegister.setPlateNum(getPlateNum());
        carRegister.setBrand(getBrand());
        carRegister.setColor(getColor());
        carRegister.setPhoneNum(getPhoneNum());
        carRegister.setState(getState());

        return carRegister;
    }

    //删除车辆信息所需Json
    public CarRegister convetDelete(CarRegister carRegister){

        carRegister.setState("弃用");

        return carRegister;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getXgh() {
        return xgh;
    }

    public void setXgh(String xgh) {
        this.xgh = xgh;
    }

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }
}
