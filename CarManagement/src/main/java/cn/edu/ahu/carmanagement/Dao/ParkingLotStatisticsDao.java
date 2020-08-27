package cn.edu.ahu.carmanagement.Dao;

import cn.edu.ahu.carmanagement.Dao.Model.ParkingAnalysisModel;
import cn.edu.ahu.carmanagement.Dao.Model.ParkingAnalysisModel2;
import cn.edu.ahu.carmanagement.Domain.ParkingLotStatistics;
import cn.edu.ahu.carmanagement.Domain.ParkingRecord;
import cn.edu.ahu.carmanagement.Domain.RegionalDivision;
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
 * @Despriction: 停车场管理Dao
 * @Date:Created in 18:55 2019/7/16
 */
@Repository
public class ParkingLotStatisticsDao {

    @PersistenceContext
    private EntityManager em;

    public Page<ParkingAnalysisModel> findByPlateNum(Pageable pageable, String plateNum){

        String dataSql = "select pr,pls,rd " +
                " from ParkingRecord as pr " +
                " left join ParkingLotStatistics as pls on pr.parkingArea = pls.areaId " +
                " left join RegionalDivision as rd on pls.areaId = rd.areaId " +
                " where pr.plateNum = :plateNum ";

        List<Object[]> list = em.createQuery(dataSql,Object[].class)
                .setParameter("plateNum",plateNum)
                .setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        String countSql = "select count(pr)" +
                " from ParkingRecord as pr " +
                " where pr.plateNum = :plateNum ";

        Long count = em.createQuery(countSql,Long.class)
                .setParameter("plateNum",plateNum)
                .getSingleResult();

        List<ParkingAnalysisModel> parkingAnalysisModelList = new ArrayList<>(list.size());

        list.forEach(e-> parkingAnalysisModelList.add(convert(e)));

        return new PageImpl<>(parkingAnalysisModelList, pageable, count);
    }

    public Page<ParkingAnalysisModel2> findByPlateNumGroupByBuilding(Pageable pageable, String plateNum){

        String dataSql = "select pr.parkingArea,pls.areaName,rd.name,rd.department,count(pr.id) as total " +
                " from ParkingRecord as pr " +
                " left join ParkingLotStatistics as pls on pr.parkingArea = pls.areaId " +
                " left join RegionalDivision as rd on pls.areaId = rd.areaId " +
                " where pr.plateNum = :plateNum " +
                " group by pr.parkingArea,pls.areaName,rd.name,rd.department " +
                " order by total desc ";

        List<Object[]> list = em.createQuery(dataSql,Object[].class)
                .setParameter("plateNum",plateNum)
                .setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        String countSql = "select count(pr.parkingArea)" +
                " from ParkingRecord as pr " +
                " where pr.plateNum = :plateNum ";

        Long count = em.createQuery(countSql,Long.class)
                .setParameter("plateNum",plateNum)
                .getSingleResult();

        List<ParkingAnalysisModel2> parkingAnalysisModel2List = new ArrayList<>(list.size());

        list.forEach(e-> parkingAnalysisModel2List.add(convert2(e)));

        return new PageImpl<>(parkingAnalysisModel2List, pageable, count);
    }

    private ParkingAnalysisModel convert(Object[] e){
        return new ParkingAnalysisModel(
                (ParkingRecord) e[0],
                (ParkingLotStatistics) e[1],
                (RegionalDivision) e[2]
        );
    }

    private ParkingAnalysisModel2 convert2(Object[] e){
        return new ParkingAnalysisModel2(
                e[0],
                e[1],
                e[2],
                e[3],
                e[4]
        );
    }
}
