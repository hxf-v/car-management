package cn.edu.ahu.carmanagement.Service;

import cn.edu.ahu.carmanagement.Utils.Response;
import org.springframework.data.domain.Pageable;

/**
 * @Author: FZC
 * @Despriction: 车辆停车记录Service
 * @Date:Created in 16:24 2019/6/11
 */
public interface ParkingRecordService {

    //通过车牌号查询单个车辆当前停车信息
    Response findOneCarParking(String plateNum,String state);

    //通过车牌号查询单个车辆当前停车信息
    Response findOneCarRecordParking(Pageable pageable,String plateNum);

    //获取当前校内所有在校车辆最近的一条停车信息
    Response findCarParking(Pageable pageable, String state);
}
