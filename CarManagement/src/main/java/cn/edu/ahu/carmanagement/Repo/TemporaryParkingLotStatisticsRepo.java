package cn.edu.ahu.carmanagement.Repo;

import cn.edu.ahu.carmanagement.Domain.TemporaryParkingLotStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: Alex
 * @Description:
 * @Date: Created in 10:32 2020/8/12
 */
public interface TemporaryParkingLotStatisticsRepo extends JpaRepository<TemporaryParkingLotStatistics,Integer> {
    List<TemporaryParkingLotStatistics> findByPlateNum(String plateNum);
    void deleteByPlateNum(String plateNum);
}
