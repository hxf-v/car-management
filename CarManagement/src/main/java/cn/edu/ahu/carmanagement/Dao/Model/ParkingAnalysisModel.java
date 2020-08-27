package cn.edu.ahu.carmanagement.Dao.Model;

import cn.edu.ahu.carmanagement.Domain.ParkingLotStatistics;
import cn.edu.ahu.carmanagement.Domain.ParkingRecord;
import cn.edu.ahu.carmanagement.Domain.RegionalDivision;

/**
 * @Author: FZC
 * @Despriction:
 * @Date:Created in 19:07 2019/7/16
 */
public class ParkingAnalysisModel {

    private ParkingRecord parkingRecord;

    private ParkingLotStatistics parkingLotStatistics;

    private RegionalDivision regionalDivision;

    public ParkingAnalysisModel(
            ParkingRecord parkingRecord,
            ParkingLotStatistics parkingLotStatistics,
            RegionalDivision regionalDivision) {
        this.parkingRecord = parkingRecord;
        this.parkingLotStatistics = parkingLotStatistics;
        this.regionalDivision = regionalDivision;
    }

    public ParkingRecord getParkingRecord() {
        return parkingRecord;
    }

    public void setParkingRecord(ParkingRecord parkingRecord) {
        this.parkingRecord = parkingRecord;
    }

    public ParkingLotStatistics getParkingLotStatistics() {
        return parkingLotStatistics;
    }

    public void setParkingLotStatistics(ParkingLotStatistics parkingLotStatistics) {
        this.parkingLotStatistics = parkingLotStatistics;
    }

    public RegionalDivision getRegionalDivision() {
        return regionalDivision;
    }

    public void setRegionalDivision(RegionalDivision regionalDivision) {
        this.regionalDivision = regionalDivision;
    }
}
