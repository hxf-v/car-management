package cn.edu.ahu.carmanagement.Json;

import cn.edu.ahu.carmanagement.Domain.TemporaryParkingLotStatistics;

/**
 * @Author: Alex
 * @Description:
 * @Date: Created in 10:32 2020/8/12
 */
public class TemporaryParkingLotStatisticsJson {
    private String plateNum;
    private String area_id;

    public TemporaryParkingLotStatistics convert(){
        TemporaryParkingLotStatistics temporaryParkingLotStatistics=new TemporaryParkingLotStatistics();
        temporaryParkingLotStatistics.setPlateNum(getPlateNum());
        temporaryParkingLotStatistics.setArea_id(getArea_id());
        return temporaryParkingLotStatistics;
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

