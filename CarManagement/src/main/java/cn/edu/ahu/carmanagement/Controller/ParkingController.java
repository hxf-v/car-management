package cn.edu.ahu.carmanagement.Controller;

import cn.edu.ahu.carmanagement.Dao.ParkingGuidanceDao;
import cn.edu.ahu.carmanagement.Dao.ParkingLotStatisticsDao;
import cn.edu.ahu.carmanagement.Service.ParkingLotStatisticsService;
import cn.edu.ahu.carmanagement.Service.ParkingRecordService;
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
 * @Despriction: 校园车辆管理系统-停车管理
 * @Date:Created in 16:20 2019/4/25
 */
@Controller
@RequestMapping(value = "/parking")
public class ParkingController {

    @Autowired
    private final ParkingRecordService parkingRecordService;
    private final ParkingLotStatisticsService parkingLotStatisticsService;
    private final ParkingLotStatisticsDao parkingLotStatisticsDao;
    private final ParkingGuidanceDao parkingGuidanceDao;

    /**
     * 依赖注入
     * @param parkingRecordService
     * @param parkingLotStatisticsService
     * @param parkingLotStatisticsDao
     * @param parkingGuidanceDao
     */
    public ParkingController(
            ParkingRecordService parkingRecordService,
            ParkingLotStatisticsService parkingLotStatisticsService,
            ParkingLotStatisticsDao parkingLotStatisticsDao,
            ParkingGuidanceDao parkingGuidanceDao) {
        this.parkingRecordService = parkingRecordService;
        this.parkingLotStatisticsService = parkingLotStatisticsService;
        this.parkingLotStatisticsDao = parkingLotStatisticsDao;
        this.parkingGuidanceDao = parkingGuidanceDao;
    }

    /**
     * 校园车辆管理系统-停车管理-获取当前校内所有在校车辆最近的一条停车信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Response in(Integer pageNumber,Integer pageSize){
        //数据校验
        RequestParamCheck.checkNull(pageNumber, "pageNumber");
        RequestParamCheck.checkNull(pageSize, "pageSize");
        //pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return parkingRecordService.findCarParking(pageable,"在停");
    }

    @ResponseBody
    @RequestMapping(value = "/guidance", method = RequestMethod.GET)
    public Response in(int[][] adjMatrix){
        int[][] pathResult = new int[3][4];
        return new Response().success(parkingGuidanceDao.getShortestPaths(adjMatrix));
    }

    /**
     * 校园车辆管理系统-停车管理-查询单个车辆当前停车信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public Response one(String plateNum){
        return parkingRecordService.findOneCarParking(plateNum,"在停");
    }

    /**
     * 校园车辆管理系统-停车管理-查询单个车辆所有历史停车信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/one/record", method = RequestMethod.GET)
    public Response oneRecord(Integer pageNumber,Integer pageSize,String plateNum){
        //数据校验
        RequestParamCheck.checkNull(pageNumber, "pageNumber");
        RequestParamCheck.checkNull(pageSize, "pageSize");
        //pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return parkingRecordService.findOneCarRecordParking(pageable,plateNum);
    }

    /**
     * 校园车辆管理系统-停车管理-当前校内车位占用量概图
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/parkinglot/total", method = RequestMethod.GET)
    public Response parkingLotTotal(Integer pageNumber,Integer pageSize){
        //数据校验
        RequestParamCheck.checkNull(pageNumber, "pageNumber");
        RequestParamCheck.checkNull(pageSize, "pageSize");
        //pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return parkingLotStatisticsService.findParkingLotStatistics(pageable);
    }

    /**
     * 校园车辆管理系统-停车管理-当前校内车位占用量单个停车场详细信息及实时图片展示
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/parkinglot/single", method = RequestMethod.GET)
    public Response parkingLotSingle(String areaId){
        return parkingLotStatisticsService.findParkingLotStatisticsSingle(areaId);
    }
}
