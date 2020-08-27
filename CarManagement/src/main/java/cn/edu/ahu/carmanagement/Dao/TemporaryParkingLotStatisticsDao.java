package cn.edu.ahu.carmanagement.Dao;

import cn.edu.ahu.carmanagement.Domain.TemporaryParkingLotStatistics;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @Author: Alex
 * @Description:  停车场统计临时表   也是停车场实况
 * @Date: Created in 22:49 2020/8/11
 */
@Repository
public class TemporaryParkingLotStatisticsDao {
    @PersistenceContext
    private EntityManager em;

    public List<TemporaryParkingLotStatistics> findByArea_id(String area_id){
        String dataSql="select tps " +
                "from TemporaryParkingLotStatistics as tps " +
                "where tps.area_id=:area_id";
        List<TemporaryParkingLotStatistics> list=em.createQuery(dataSql,TemporaryParkingLotStatistics.class)
                .setParameter("area_id",area_id)
                .getResultList();
        return list;
    }
}

