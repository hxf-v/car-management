package cn.edu.ahu.carmanagement.Dao;

import cn.edu.ahu.carmanagement.Domain.ParkingRecord;
import cn.edu.ahu.carmanagement.Utils.DateStringTranUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @Author: FZC
 * @Despriction:
 * @Date:Created in 8:48 2019/6/20
 */
@Repository
public class ParkingRecordDao {
    @PersistenceContext
    private EntityManager em;

    //通过状态查询停车记录
    public Page<ParkingRecord> findByState(Pageable pageable,String state){

        String dataSql = "select parkRd" +
                " from ParkingRecord as parkRd" +
                " where parkRd.state = :state ";

        List<ParkingRecord> parkingRecordList = em.createQuery(dataSql,ParkingRecord.class)
                .setParameter("state",state)
                .setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        String countSql = "select count(parkRd)" +
                " from ParkingRecord as parkRd" +
                " where parkRd.state = :state ";

        Long count = em.createQuery(countSql,Long.class)
                .setParameter("state",state)
                .getSingleResult();

        //时间格式转换
        parkingRecordList.forEach(DrivingRouteRecord ->{
            if (DrivingRouteRecord.getStoppingTime() != null) {
                DrivingRouteRecord.setStoppingTimeStr(DateStringTranUtil.dateToString(DrivingRouteRecord.getStoppingTime()));
            }
            if (DrivingRouteRecord.getDepartureTime() != null) {
                DrivingRouteRecord.setDepartureTimeStr(DateStringTranUtil.dateToString(DrivingRouteRecord.getDepartureTime()));
            }
            if (DrivingRouteRecord.getParkingTime() != null) {
                DrivingRouteRecord.setParkingTimeStr(DateStringTranUtil.dateToString(DrivingRouteRecord.getParkingTime()));
            }
        });
        return new PageImpl<>(parkingRecordList, pageable, count);
    }

    //通过车牌号查询单个车辆停车记录
    public Page<ParkingRecord> findByPlateNum(Pageable pageable,String plateNum){

        String dataSql = "select parkRd" +
                " from ParkingRecord as parkRd" +
                " where parkRd.plateNum = :plateNum ";

        List<ParkingRecord> parkingRecordList = em.createQuery(dataSql,ParkingRecord.class)
                .setParameter("plateNum",plateNum)
                .setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        String countSql = "select count(parkRd)" +
                " from ParkingRecord as parkRd" +
                " where parkRd.plateNum = :plateNum ";

        Long count = em.createQuery(countSql,Long.class)
                .setParameter("plateNum",plateNum)
                .getSingleResult();

        //时间格式转换
        parkingRecordList.forEach(DrivingRouteRecord ->{
            if (DrivingRouteRecord.getStoppingTime() != null) {
                DrivingRouteRecord.setStoppingTimeStr(DateStringTranUtil.dateToString(DrivingRouteRecord.getStoppingTime()));
            }
            if (DrivingRouteRecord.getDepartureTime() != null) {
                DrivingRouteRecord.setDepartureTimeStr(DateStringTranUtil.dateToString(DrivingRouteRecord.getDepartureTime()));
            }
            if (DrivingRouteRecord.getParkingTime() != null) {
                DrivingRouteRecord.setParkingTimeStr(DateStringTranUtil.dateToString(DrivingRouteRecord.getParkingTime()));
            }
        });
        return new PageImpl<>(parkingRecordList, pageable, count);
    }
}
