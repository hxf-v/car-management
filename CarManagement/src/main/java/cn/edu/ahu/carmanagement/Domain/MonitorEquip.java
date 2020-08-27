package cn.edu.ahu.carmanagement.Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: AhuStudent
 * @Description: 注册车辆实体
 * @Author: FZC
 * @Date: 2019/1/14 9:51
 */
@Entity
@Data
@Table(name = "monitorequip")
public class MonitorEquip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;         //主键id，作为唯一ID

    @Column(name = "position")
    private String position;    //摄像头位置简述

    @Column(name = "longitude")
    private String longitude;   //经度

    @Column(name = "latitude")
    private String latitude;    //纬度

    @Column(name = "type")
    private String type;        //摄像头型号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
