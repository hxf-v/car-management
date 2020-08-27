package cn.edu.ahu.carmanagement.Repo;

import cn.edu.ahu.carmanagement.Domain.DrivingRouteRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: FZC
 * @Despriction:
 * @Date:Created in 15:27 2019/4/25
 */
public interface DrivingRouteRecordRepo extends JpaRepository<DrivingRouteRecord,Integer> {

    List<DrivingRouteRecord> findByPlateNumAndState(String PlateNum, String state);
}
