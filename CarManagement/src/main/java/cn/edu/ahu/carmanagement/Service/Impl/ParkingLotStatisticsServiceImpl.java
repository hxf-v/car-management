package cn.edu.ahu.carmanagement.Service.Impl;

import cn.edu.ahu.carmanagement.Dao.ParkingLotStatisticsDao;
import cn.edu.ahu.carmanagement.Repo.ParkingLotStatisticsRepo;
import cn.edu.ahu.carmanagement.Service.ParkingLotStatisticsService;
import cn.edu.ahu.carmanagement.Utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author: FZC
 * @Despriction:
 * @Date:Created in 15:57 2019/7/15
 */
@Service
public class ParkingLotStatisticsServiceImpl implements ParkingLotStatisticsService {

    @Autowired
    ParkingLotStatisticsRepo parkingLotStatisticsRepo;
    @Autowired
    ParkingLotStatisticsDao parkingLotStatisticsDao;

    @Override
    public Response findParkingLotStatistics(Pageable pageable) {
        return new Response().success(parkingLotStatisticsRepo.findAll(pageable));
    }

    @Override
    public Response findParkingLotStatisticsSingle(String areaId) {
        return new Response().success(parkingLotStatisticsRepo.findByAreaId(areaId));
    }

    @Override
    public Response findParkingLotStatisticsByPlateNum(Pageable pageable, String plateNum) {
        return new Response().success(parkingLotStatisticsDao.findByPlateNum(pageable,plateNum));
    }

    @Override
    public Response findParkingLotStatisticsByPlateNumGroupByBuilding(Pageable pageable, String plateNum) {
        return new Response().success(parkingLotStatisticsDao.findByPlateNumGroupByBuilding(pageable,plateNum));
    }
}
