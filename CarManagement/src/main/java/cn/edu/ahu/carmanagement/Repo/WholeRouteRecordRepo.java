package cn.edu.ahu.carmanagement.Repo;

import cn.edu.ahu.carmanagement.Domain.WholeRouteRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WholeRouteRecordRepo extends JpaRepository<WholeRouteRecord,Integer> {

    //校外车辆确定开始和截至时间查询车辆全程行驶记录历史
    @Query(value = "select wrr from WholeRouteRecord wrr where wrr.plateNum = :plateNum and (wrr.settlementTime between :startTime and :endTime)")
    List<WholeRouteRecord> findByPlateNumBetweenStartAndEndTime(@Param("plateNum")String plateNum, @Param("startTime")Date startTime, @Param("endTime")Date endTime);

    WholeRouteRecord findByPlateNumAndState(String plateNum,String state);

//    WholeRouteRecord findById(Integer id);
}
