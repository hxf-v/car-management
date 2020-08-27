package cn.edu.ahu.carmanagement.Domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: FZC
 * @UpdateBy: Alex
 * @Despriction: 停车位统计实体
 * @UpDateBy:  Alex
 * @Date:Created in 9:03 2019/7/3
 */
@Entity
@Table(name = "parkinglotstatistics")
public class ParkingLotStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;                     //主键id

    @Column(name = "area_name")
    private String areaName;                //停车区域名称

    @Column(name = "area_id")
    private String areaId;                  //停车区域id

    @Column(name = "occupy")
    private int occupy;                  //已被占用车位数量

    @Column(name = "surplus")
    private int surplus;                 //剩余车位数量

    @Column(name = "update_time")
    private Date updateTime;                //信息更新时间

    @Column(name = "total")
    private int total;                   //车位总数

    @Column(name = "pic_path")
    private String picPath;                 //照片路径

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public int getOccupy() {
        return occupy;
    }

    public void setOccupy(int occupy) {
        this.occupy = occupy;
    }

    public int getSurplus() {
        return surplus;
    }

    public void setSurplus(int surplus) {
        this.surplus = surplus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}
