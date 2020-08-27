package cn.edu.ahu.carmanagement.Json;

import cn.edu.ahu.carmanagement.Domain.WholeRouteRecord;
import cn.edu.ahu.carmanagement.Utils.DateStringTranUtil;

import java.util.Date;

public class WholeRouteRecordJson {

    private Integer id;                 //主键id，作为唯一ID
    private String plateNum;            //车牌号码
    private String parkingTime;          //总停放时长
    private String cost;                 //总计费用
    private Date inTime;                //入校时间
    private Date settlementTime;        //结算时间
    private String routeAssemble;       //校内行驶路径编号集合
    private String state;               //车辆离校状态

    //添加全程行驶记录
    public WholeRouteRecord convert(){
        WholeRouteRecord wholeRouteRecord = new WholeRouteRecord();

        wholeRouteRecord.setPlateNum(getPlateNum());
        wholeRouteRecord.setParkingTime(getParkingTime());
        wholeRouteRecord.setCost(getCost());
        wholeRouteRecord.setInTime(getInTime());
        wholeRouteRecord.setSettlementTime(getSettlementTime());
        wholeRouteRecord.setRouteAssemble(getRouteAssemble());
        wholeRouteRecord.setState("未缴费");
        return wholeRouteRecord;
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

    public String getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(String parkingTime) {
        this.parkingTime = parkingTime;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(Date settlementTime) {
        this.settlementTime = settlementTime;
    }

    public String getRouteAssemble() {
        return routeAssemble;
    }

    public void setRouteAssemble(String routeAssemble) {
        this.routeAssemble = routeAssemble;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
