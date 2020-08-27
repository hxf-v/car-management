package cn.edu.ahu.carmanagement.Dao;

import cn.edu.ahu.carmanagement.Domain.DrivingRouteRecord;
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
 * @Despriction: 查询车辆行驶路线信息接口
 * @Date:Created in 15:40 2019/6/10
 */
@Repository
public class DrivingRouteRecordDao {

    @PersistenceContext
    private EntityManager em;

    //通过车牌号查询当前在校单个车辆的行驶路线信息（绘制轨迹图），按照时间正序排列
    public Page<DrivingRouteRecord> findByPlateNumAndState(Pageable pageable, String plateNum, String state){

        String dataSql = "select drivingRoute" +
                " from DrivingRouteRecord as drivingRoute" +
                " where drivingRoute.plateNum = :plateNum and drivingRoute.state = :state " +
                " order by drivingRoute.passingTime ";

        List<DrivingRouteRecord> drivingRouteRecordList = em.createQuery(dataSql,DrivingRouteRecord.class)
                .setParameter("plateNum",plateNum)
                .setParameter("state",state)
                .setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        String countSql = "select count(drivingRoute)" +
                " from DrivingRouteRecord as drivingRoute" +
                " where drivingRoute.plateNum = :plateNum and drivingRoute.state = :state ";

        Long count = em.createQuery(countSql,Long.class)
                .setParameter("plateNum",plateNum)
                .setParameter("state",state)
                .getSingleResult();

        //时间格式转换
        drivingRouteRecordList.forEach(DrivingRouteRecord ->{
            if (DrivingRouteRecord.getPassingTime() != null) {
                DrivingRouteRecord.setPassingTimeStr(DateStringTranUtil.dateToString(DrivingRouteRecord.getPassingTime()));
            }
        });
        return new PageImpl<>(drivingRouteRecordList, pageable, count);
    }
}
