package cn.edu.ahu.carmanagement.Domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: FZC
 * @UpdateBy： Alex
 * @Despriction: 车牌识别系统识别结果实体
 * @Date: Created in 16:48 2019/4/22
 */
@Entity
@Table(name = "carnumberget")
public class CarNumberGet {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)       //毛哥不会为表写入主键id，我也没办法，只能换主键
//    @Column(name = "id")
//    private Integer id;             //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private String plateNum;        //车牌号

    @Column(name = "confidence")
    private String confidence;      //识别准确率

    @Column(name = "state")
    private String state;           //处理状态(默认待处理)

    @Column(name = "take_time")
    private Date takeTime;        //抓拍时间

    @Column(name = "pic_path")
    private String picPath;         //照片路径

    @Column(name = "car_location")
    private String car_location;         //照片路径

    public String getCar_location() {
        return car_location;
    }

    public void setCar_location(String car_location) {
        this.car_location = car_location;
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}
