package cn.edu.ahu.carmanagement.Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: FZC
 * @Despriction: 校内全程行驶记录表
 * @Date: Created in 16:48 2019/4/22
 */
@Entity
@Data
@Table(name = "wholerouterecord")
public class WholeRouteRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;                 //主键id，作为唯一ID

    @Column(name = "plate_number")
    private String plateNum;            //车牌号码

    @Column(name = "parking_time")
    private String parkingTime;          //总停放时长

    @Column(name = "cost")
    private String cost;                 //总计费用

    @Column(name = "in_time")
    private Date inTime;                //入校时间

    @Column(name = "settlement_time")
    private Date settlementTime;        //结算时间

    @Column(name = "route_assemble")
    private String routeAssemble;       //校内行驶路径编号集合

    @Column(name = "state")
    private String state;               //车辆离校状态

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

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
