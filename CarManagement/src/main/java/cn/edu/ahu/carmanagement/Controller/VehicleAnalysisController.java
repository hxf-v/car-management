package cn.edu.ahu.carmanagement.Controller;

import cn.edu.ahu.carmanagement.Service.ParkingLotStatisticsService;
import cn.edu.ahu.carmanagement.Utils.RequestParamCheck;
import cn.edu.ahu.carmanagement.Utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: FZC
 * @Despriction: 校园车辆管理系统-车辆分析
 * @Date:Created in 15:23 2019/7/16
 */
@Controller
@RequestMapping(value = "/analysis")
public class VehicleAnalysisController {

    @Autowired
    private final ParkingLotStatisticsService parkingLotStatisticsService;

    public VehicleAnalysisController(
            ParkingLotStatisticsService parkingLotStatisticsService) {
        this.parkingLotStatisticsService = parkingLotStatisticsService;
    }


    /**
     * 校园车辆管理系统-车辆分析-车牌查询所有停车记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/parkinglot/all", method = RequestMethod.GET)
    public Response parkingLotAll(Integer pageNumber,Integer pageSize,String plateNum){
        //数据校验
        RequestParamCheck.checkNull(pageNumber, "pageNumber");
        RequestParamCheck.checkNull(pageSize, "pageSize");
        //pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return new Response().success(parkingLotStatisticsService.findParkingLotStatisticsByPlateNum(pageable,plateNum));
    }
    /**
     * 校园车辆管理系统-车辆分析-车牌查询停车记录和建筑物关联
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/parkinglot/groupby", method = RequestMethod.GET)
    public Response parkingLotGroupBy(Integer pageNumber,Integer pageSize,String plateNum){
        //数据校验
        RequestParamCheck.checkNull(pageNumber, "pageNumber");
        RequestParamCheck.checkNull(pageSize, "pageSize");
        //pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return new Response().success(parkingLotStatisticsService.findParkingLotStatisticsByPlateNumGroupByBuilding(pageable,plateNum));
    }
}
