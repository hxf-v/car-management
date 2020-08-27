package cn.edu.ahu.carmanagement.Service;

import cn.edu.ahu.carmanagement.Utils.Response;
import org.springframework.data.domain.Pageable;


/**
 * @Author: FZC
 * @Despriction:
 * @Date:Created in 15:55 2019/7/15
 */
public interface ParkingLotStatisticsService {

    Response findParkingLotStatistics(Pageable pageable);

    Response findParkingLotStatisticsSingle(String areaId);

    Response findParkingLotStatisticsByPlateNum(Pageable pageable,String plateNum);

    Response findParkingLotStatisticsByPlateNumGroupByBuilding(Pageable pageable,String plateNum);
}
