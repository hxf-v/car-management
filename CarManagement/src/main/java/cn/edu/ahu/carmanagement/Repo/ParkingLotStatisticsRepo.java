package cn.edu.ahu.carmanagement.Repo;

import cn.edu.ahu.carmanagement.Domain.ParkingLotStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * @Author: FZC
 * @UpdateBy: Alex
 * @Despriction:
 * @Date:Created in 15:53 2019/7/15
 */
public interface ParkingLotStatisticsRepo extends JpaRepository<ParkingLotStatistics,Integer> {

    ParkingLotStatistics findByAreaId(String areaId);

    //    @Query(value = "select pls from ParkingLotStatistics pls where pls.relateAreaId like %:relateAreaId% ")
//    List<ParkingLotStatistics> findByRelateAreaId(@Param("relateAreaId")String relateAreaId);
    @Modifying
    @Query("update ParkingLotStatistics pls set pls.occupy = :occupy where pls.areaId = :areaId")
    Integer UpdateOccupy(@Param("occupy") int occupy,@Param("areaId") String areaId);

    @Modifying
    @Query("update ParkingLotStatistics pls set pls.surplus = pls.total-pls.occupy, pls.updateTime= :updateTime where pls.areaId = :areaId")
    void UpdateSurplusAndUpdateTime(@Param("updateTime") Date updateTime, @Param("areaId") String areaId);

}
