package cn.edu.ahu.carmanagement.Dao;

import cn.edu.ahu.carmanagement.Dao.Model.SchoolStatusRecordModel;
import cn.edu.ahu.carmanagement.Domain.DrivingRouteRecord;
import cn.edu.ahu.carmanagement.Domain.ParkingRecord;
import cn.edu.ahu.carmanagement.Domain.SchoolStatusRecord;
import cn.edu.ahu.carmanagement.Utils.DateStringTranUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: FZC
 * @Despriction: 车牌查询接口
 * @Date:Created in 15:33 2019/4/25
 */
@Repository
public class SchoolStatusRecordModelDao {

    @PersistenceContext
    private EntityManager em;

    //通过车牌号查询当前在校单个车辆的所有信息
    public Page<SchoolStatusRecordModel> findByPlateNumAndState(Pageable pageable,String plateNum,String state){

        String dataSql = "select drivingRoute,park,schoolStatus" +
                " from DrivingRouteRecord as drivingRoute" +
                " left join ParkingRecord as park on drivingRoute.plateNum = park.plateNum" +
                " left join SchoolStatusRecord as schoolStatus on drivingRoute.plateNum = schoolStatus.plateNum" +
                " where drivingRoute.plateNum = :plateNum and schoolStatus.state = :state";

        List<Object[]> list = em.createQuery(dataSql,Object[].class)
                .setParameter("plateNum",plateNum)
                .setParameter("state",state)
                .setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        String countSql = "select count(drivingRoute)" +
                " from DrivingRouteRecord as drivingRoute" +
                " left join ParkingRecord as park on drivingRoute.plateNum = park.plateNum" +
                " left join SchoolStatusRecord as schoolStatus on drivingRoute.plateNum = schoolStatus.plateNum" +
                " where drivingRoute.plateNum = :plateNum and schoolStatus.state = :state";

        Long count = em.createQuery(countSql,Long.class)
                .setParameter("plateNum",plateNum)
                .setParameter("state",state)
                .getSingleResult();

        List<SchoolStatusRecordModel> schoolStatusRecordModelList = new ArrayList<>();

        list.forEach(e-> schoolStatusRecordModelList.add(convert(e)));

        //时间格式转换
        schoolStatusRecordModelList.forEach(e -> {
            if (e.getSchoolStatusRecord().getInTime() != null) {
                e.getSchoolStatusRecord().setInTimeStr(DateStringTranUtil.dateToString(e.getSchoolStatusRecord().getInTime()));
            }
            if (e.getSchoolStatusRecord().getOutTime() != null){
                e.getSchoolStatusRecord().setOutTimeStr(DateStringTranUtil.dateToString(e.getSchoolStatusRecord().getOutTime()));
            }
            if (e.getSchoolStatusRecord().getInSchoolTime() != null){
                e.getSchoolStatusRecord().setInSchoolTimeStr(DateStringTranUtil.dateToString(e.getSchoolStatusRecord().getInSchoolTime()));
            }
            if (e.getParkingRecord().getStoppingTime() != null){
                e.getParkingRecord().setStoppingTimeStr(DateStringTranUtil.dateToString(e.getParkingRecord().getStoppingTime()));
            }
            if (e.getParkingRecord().getDepartureTime() != null){
                e.getParkingRecord().setDepartureTimeStr(DateStringTranUtil.dateToString(e.getParkingRecord().getDepartureTime()));
            }
            if (e.getParkingRecord().getParkingTime() != null){
                e.getParkingRecord().setParkingTimeStr(DateStringTranUtil.dateToString(e.getParkingRecord().getParkingTime()));
            }
            if (e.getDrivingRouteRecord().getPassingTime() != null){
                e.getDrivingRouteRecord().setPassingTimeStr(DateStringTranUtil.dateToString(e.getDrivingRouteRecord().getPassingTime()));
            }
        });

        return new PageImpl<>(schoolStatusRecordModelList, pageable, count);
    }

    //通过车牌号查询车辆所有历史纪录
    public Page<SchoolStatusRecordModel> findByPlateNum(Pageable pageable, String plateNum){

        String dataSql = "select drivingRoute,park,schoolStatus" +
                " from DrivingRouteRecord as drivingRoute" +
                " left join ParkingRecord as park on drivingRoute.plateNum = park.plateNum" +
                " left join SchoolStatusRecord as schoolStatus on drivingRoute.plateNum = schoolStatus.plateNum" +
                " where drivingRoute.plateNum = :plateNum ";

        List<Object[]> list = em.createQuery(dataSql,Object[].class)
                .setParameter("plateNum",plateNum)
                .setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        String countSql = "select count(drivingRoute)" +
                " from DrivingRouteRecord as drivingRoute" +
                " where drivingRoute.plateNum = :plateNum ";

        Long count = em.createQuery(countSql,Long.class)
                .setParameter("plateNum",plateNum)
                .getSingleResult();

        List<SchoolStatusRecordModel> schoolStatusRecordModelList = new ArrayList<>();

        list.forEach(e-> schoolStatusRecordModelList.add(convert(e)));

        //时间格式转换
        schoolStatusRecordModelList.forEach(e -> {
            if (e.getSchoolStatusRecord().getInTime() != null) {
                e.getSchoolStatusRecord().setInTimeStr(DateStringTranUtil.dateToString(e.getSchoolStatusRecord().getInTime()));
            }
            if (e.getSchoolStatusRecord().getOutTime() != null){
                e.getSchoolStatusRecord().setOutTimeStr(DateStringTranUtil.dateToString(e.getSchoolStatusRecord().getOutTime()));
            }
            if (e.getSchoolStatusRecord().getInSchoolTime() != null){
                e.getSchoolStatusRecord().setInSchoolTimeStr(DateStringTranUtil.dateToString(e.getSchoolStatusRecord().getInSchoolTime()));
            }
            if (e.getParkingRecord().getStoppingTime() != null){
                e.getParkingRecord().setStoppingTimeStr(DateStringTranUtil.dateToString(e.getParkingRecord().getStoppingTime()));
            }
            if (e.getParkingRecord().getDepartureTime() != null){
                e.getParkingRecord().setDepartureTimeStr(DateStringTranUtil.dateToString(e.getParkingRecord().getDepartureTime()));
            }
            if (e.getParkingRecord().getParkingTime() != null){
                e.getParkingRecord().setParkingTimeStr(DateStringTranUtil.dateToString(e.getParkingRecord().getParkingTime()));
            }
            if (e.getDrivingRouteRecord().getPassingTime() != null){
                e.getDrivingRouteRecord().setPassingTimeStr(DateStringTranUtil.dateToString(e.getDrivingRouteRecord().getPassingTime()));
            }
        });
        return new PageImpl<>(schoolStatusRecordModelList, pageable, count);
    }

    private SchoolStatusRecordModel convert(Object[] e){
        return new SchoolStatusRecordModel(
                (DrivingRouteRecord)e[0],
                (ParkingRecord)e[1],
                (SchoolStatusRecord)e[2]
        );
    }
}
