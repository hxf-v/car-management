package cn.edu.ahu.carmanagement.Json;

import cn.edu.ahu.carmanagement.Domain.TemporaryRouteRegister;
import cn.edu.ahu.carmanagement.Domain.TemporaryRouteUnRegister;
import cn.edu.ahu.carmanagement.Domain.WholeRouteRecord;
import cn.edu.ahu.carmanagement.Utils.DateStringTranUtil;

import java.util.Date;

public class TemporaryRouteJson {

    private Integer id;             //主键id，作为唯一ID
    private String entryTimeStr;       //用户名
    private String departureTimeStr;   //账户
    private String plateNum;        //车牌号码
    private String color;        //车辆颜色
    private String model;        //车辆类型
    private String name;        //车主姓名
    private String jobNum;        //工号
    private Date shootingTime;        //拍摄时间
    private String equipId;        //拍摄设备id
    private String longitude;        //拍摄设备经度
    private String latitude;        //拍摄设备纬度
    private String state;        //行驶记录类型
    private String cost;           //停车费用

    private String plateNumModify;        //车牌号码修改

    //历史记录条件查询条件
    private String startTimeStr;       //用户名
    private String endTimeStr;   //账户

    //历史记录编辑
    private String routeAssemble;       //校内行驶路径编号集合
    private String parkingTime;          //总停放时长

    //校内车辆修改相关信息
    public TemporaryRouteRegister convertRegisterCarRecord(TemporaryRouteRegister temporaryRouteRegister){

        temporaryRouteRegister.setPlateNum(getPlateNumModify());
        temporaryRouteRegister.setColor(getColor());
        temporaryRouteRegister.setModel(getModel());
        if (getEntryTimeStr() != null){
            temporaryRouteRegister.setEntryTime(DateStringTranUtil.stringToDate(getEntryTimeStr()));
        }
        return temporaryRouteRegister;
    }

    //校外车辆修改相关信息
    public TemporaryRouteUnRegister convertUnRegisterCarRecord(TemporaryRouteUnRegister temporaryRouteUnRegister){

        temporaryRouteUnRegister.setPlateNum(getPlateNumModify());
        temporaryRouteUnRegister.setColor(getColor());
        temporaryRouteUnRegister.setModel(getModel());
        if (getEntryTimeStr() != null){
            temporaryRouteUnRegister.setEntryTime(DateStringTranUtil.stringToDate(getEntryTimeStr()));
        }
        return temporaryRouteUnRegister;
    }

    //车辆停车费用缴纳修改按钮
    public WholeRouteRecord convert(WholeRouteRecord wholeRouteRecord){

        wholeRouteRecord.setCost("已缴费");
        wholeRouteRecord.setState("已离校");
        return wholeRouteRecord;
    }

    //车辆历史行驶记录编辑
    public WholeRouteRecord convertEdit(WholeRouteRecord wholeRouteRecord){

        wholeRouteRecord.setRouteAssemble(getRouteAssemble());
        wholeRouteRecord.setPlateNum(getPlateNum());
        wholeRouteRecord.setCost(getCost());
        wholeRouteRecord.setState(getState());
        wholeRouteRecord.setParkingTime(getParkingTime());
        return wholeRouteRecord;
    }

    //车辆历史行驶记录删除
    public WholeRouteRecord convertDelete(WholeRouteRecord wholeRouteRecord){

        wholeRouteRecord.setState("已删除");
        return wholeRouteRecord;
    }


    public String getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(String parkingTime) {
        this.parkingTime = parkingTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntryTimeStr() {
        return entryTimeStr;
    }

    public void setEntryTimeStr(String entryTimeStr) {
        this.entryTimeStr = entryTimeStr;
    }

    public String getDepartureTimeStr() {
        return departureTimeStr;
    }

    public void setDepartureTimeStr(String departureTimeStr) {
        this.departureTimeStr = departureTimeStr;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public Date getShootingTime() {
        return shootingTime;
    }

    public void setShootingTime(Date shootingTime) {
        this.shootingTime = shootingTime;
    }

    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getRouteAssemble() {
        return routeAssemble;
    }

    public void setRouteAssemble(String routeAssemble) {
        this.routeAssemble = routeAssemble;
    }

    public String getPlateNumModify() {
        return plateNumModify;
    }

    public void setPlateNumModify(String plateNumModify) {
        this.plateNumModify = plateNumModify;
    }
}
