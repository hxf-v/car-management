package cn.edu.ahu.carmanagement.Dao.Model;

import cn.edu.ahu.carmanagement.Domain.DrivingRouteRecord;
import cn.edu.ahu.carmanagement.Domain.ParkingRecord;
import cn.edu.ahu.carmanagement.Domain.SchoolStatusRecord;

/**
 * @Author: FZC
 * @Despriction:
 * @Date:Created in 15:49 2019/4/25
 */
public class SchoolStatusRecordModel {

    private DrivingRouteRecord drivingRouteRecord;

    private ParkingRecord parkingRecord;

    private SchoolStatusRecord schoolStatusRecord;

    public SchoolStatusRecordModel(
            DrivingRouteRecord drivingRouteRecord,
            ParkingRecord parkingRecord,
            SchoolStatusRecord schoolStatusRecord) {
        this.drivingRouteRecord = drivingRouteRecord;
        this.parkingRecord = parkingRecord;
        this.schoolStatusRecord = schoolStatusRecord;
    }

    public DrivingRouteRecord getDrivingRouteRecord() {
        return drivingRouteRecord;
    }

    public void setDrivingRouteRecord(DrivingRouteRecord drivingRouteRecord) {
        this.drivingRouteRecord = drivingRouteRecord;
    }

    public ParkingRecord getParkingRecord() {
        return parkingRecord;
    }

    public void setParkingRecord(ParkingRecord parkingRecord) {
        this.parkingRecord = parkingRecord;
    }

    public SchoolStatusRecord getSchoolStatusRecord() {
        return schoolStatusRecord;
    }

    public void setSchoolStatusRecord(SchoolStatusRecord schoolStatusRecord) {
        this.schoolStatusRecord = schoolStatusRecord;
    }
}
