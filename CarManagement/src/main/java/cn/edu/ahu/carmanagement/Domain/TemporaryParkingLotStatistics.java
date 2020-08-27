package cn.edu.ahu.carmanagement.Domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: Alex
 * @Description:  停车场统计临时表实体
 * @Date: Created in 23:49 2020/8/11
 */
@Entity
@Data
@Table(name="temporaryparkinglotstatistics")
public class TemporaryParkingLotStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="plate_number")
    private String plateNum;

    @Column(name="area_id")
    private String area_id;

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

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }
}
