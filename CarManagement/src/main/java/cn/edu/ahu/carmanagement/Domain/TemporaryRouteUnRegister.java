package cn.edu.ahu.carmanagement.Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: AhuStudent
 * @Description: 校外车辆形式记录临时表实体
 * @Author: FZC
 * @Date: 2019/1/14 9:51
 */
@Entity
@Data
@Table(name = "temporaryrouteunregister")
public class TemporaryRouteUnRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;             //主键id，作为唯一ID

    @Column(name = "entry_time")
    private Date entryTime;       //用户名

    @Column(name = "departure_time")
    private Date departureTime;   //账户

    @Column(name = "plate_number")
    private String plateNum;        //车牌号码

    @Column(name = "color")
    private String color;        //车辆颜色

    @Column(name = "model")
    private String model;        //车辆类型

    @Column(name = "shooting_time")
    private Date shootingTime;        //拍摄时间

    @Column(name = "equip_id")
    private Integer equipId;        //拍摄设备id

    @Column(name = "longitude")
    private String longitude;        //拍摄设备经度

    @Column(name = "latitude")
    private String latitude;        //拍摄设备纬度

    @Column(name = "state")
    private String state;        //行驶记录类型

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
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

    public Date getShootingTime() {
        return shootingTime;
    }

    public void setShootingTime(Date shootingTime) {
        this.shootingTime = shootingTime;
    }

    public Integer getEquipId() {
        return equipId;
    }

    public void setEquipId(Integer equipId) {
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
}
