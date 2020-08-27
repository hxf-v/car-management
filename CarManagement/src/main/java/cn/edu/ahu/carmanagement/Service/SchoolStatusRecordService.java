package cn.edu.ahu.carmanagement.Service;

import cn.edu.ahu.carmanagement.Utils.Response;
import org.springframework.data.domain.Pageable;


/**
 * @Author: FZC
 * @Despriction: 在校车辆记录Service
 * @Date:Created in 16:48 2019/4/22
 */
public interface SchoolStatusRecordService {

    //通过车牌号查询精准查询在校单辆车记录
    Response findOneCarRecordIn(String plateNum);

    //通过车牌号查询车辆所有历史纪录
    Response findOneCarRecordHistory(Pageable pageable,String plateNum);

    //通过车牌号查询当前在校单个车辆的所有信息
    Response findOneCarRecordInSchool(Pageable pageable,String plateNum,String state);

    //通过车牌号查询当前在校单个车辆的行驶路线信息（绘制轨迹图）
    Response findOneCarRouteInSchool(Pageable pageable,String plateNum,String state);

    //通过在校离校状态获取车辆信息
    Response findAllCarByState(Pageable pageable, String state);
}
