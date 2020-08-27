package cn.edu.ahu.carmanagement.Dao.Model;

/**
 * @Author: FZC
 * @Despriction:
 * @Date:Created in 19:33 2019/7/16
 */
public class ParkingAnalysisModel2 {

    private Object parkingArea;
    private Object areaName;
    private Object buildingName;
    private Object department;
    private Object count;

    public ParkingAnalysisModel2(Object parkingArea, Object areaName, Object buildingName, Object department, Object count) {
        this.parkingArea = parkingArea;
        this.areaName = areaName;
        this.buildingName = buildingName;
        this.department = department;
        this.count = count;
    }

    public Object getParkingArea() {
        return parkingArea;
    }

    public void setParkingArea(Object parkingArea) {
        this.parkingArea = parkingArea;
    }

    public Object getAreaName() {
        return areaName;
    }

    public void setAreaName(Object areaName) {
        this.areaName = areaName;
    }

    public Object getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(Object buildingName) {
        this.buildingName = buildingName;
    }

    public Object getCount() {
        return count;
    }

    public void setCount(Object count) {
        this.count = count;
    }

    public Object getDepartment() {
        return department;
    }

    public void setDepartment(Object department) {
        this.department = department;
    }
}
