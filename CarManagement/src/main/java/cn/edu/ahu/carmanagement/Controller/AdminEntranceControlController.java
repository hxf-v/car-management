package cn.edu.ahu.carmanagement.Controller;

import cn.edu.ahu.carmanagement.Dao.TemporaryRouteRegisterDao;
import cn.edu.ahu.carmanagement.Dao.TemporaryRouteUnRegisterDao;
import cn.edu.ahu.carmanagement.Domain.TemporaryRouteRegister;
import cn.edu.ahu.carmanagement.Domain.TemporaryRouteUnRegister;
import cn.edu.ahu.carmanagement.Json.TemporaryRouteJson;
import cn.edu.ahu.carmanagement.Repo.*;
import cn.edu.ahu.carmanagement.Utils.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: FZC
 * @Despriction: 管理员入口控制Controller
 * @Date: Created in 16:48 2019/4/22
 */
@Controller
@RequestMapping(value = "/admin/entrance")
public class AdminEntranceControlController {

    private final AdminInfoRepo adminInfoRepo;
    private final OperateLogRepo operateLogRepo;
    private final ParkingLotStatisticsRepo parkingLotStatisticsRepo;
    private final TemporaryRouteRegisterDao temporaryRouteRegisterDao;
    private final TemporaryRouteUnRegisterDao temporaryRouteUnRegisterDao;
    private final SchoolCarRegisterRepo schoolCarRegisterRepo;
    private final TemporaryRouteRegisterRepo temporaryRouteRegisterRepo;
    private final TemporaryRouteUnRegisterRepo temporaryRouteUnRegisterRepo;

    /**
     * 依赖注入
     * @param adminInfoRepo
     * @param operateLogRepo
     * @param parkingLotStatisticsRepo
     * @param temporaryRouteRegisterDao
     * @param temporaryRouteUnRegisterDao
     * @param schoolCarRegisterRepo
     * @param temporaryRouteRegisterRepo
     * @param temporaryRouteUnRegisterRepo
     */
    public AdminEntranceControlController(AdminInfoRepo adminInfoRepo,
                                          OperateLogRepo operateLogRepo,
                                          ParkingLotStatisticsRepo parkingLotStatisticsRepo,
                                          TemporaryRouteRegisterDao temporaryRouteRegisterDao,
                                          TemporaryRouteUnRegisterDao temporaryRouteUnRegisterDao,
                                          SchoolCarRegisterRepo schoolCarRegisterRepo,
                                          TemporaryRouteRegisterRepo temporaryRouteRegisterRepo,
                                          TemporaryRouteUnRegisterRepo temporaryRouteUnRegisterRepo) {
        this.adminInfoRepo = adminInfoRepo;
        this.operateLogRepo = operateLogRepo;
        this.parkingLotStatisticsRepo = parkingLotStatisticsRepo;
        this.temporaryRouteRegisterDao = temporaryRouteRegisterDao;
        this.temporaryRouteUnRegisterDao = temporaryRouteUnRegisterDao;
        this.schoolCarRegisterRepo = schoolCarRegisterRepo;
        this.temporaryRouteRegisterRepo = temporaryRouteRegisterRepo;
        this.temporaryRouteUnRegisterRepo = temporaryRouteUnRegisterRepo;
    }

    /**
     * 校园车辆管理系统-管理员入口控制-默认显示当前最近入校的一个校内车辆的信息（包含图片base64码）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/recentlyone", method = RequestMethod.GET)
    public Response recentlyOne(){

        return new Response().success(temporaryRouteRegisterDao.findRecentlyOne("入校").get(0));
    }

    /**
     * 校园车辆管理系统-管理员入口控制-校内停车场实况查询（停车场实况按钮）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/parkinglot", method = RequestMethod.GET)
    public Response parkingLot(){

        return new Response().success(parkingLotStatisticsRepo.findAll());
    }

    /**
     * 校园车辆管理系统-管理员入口控制-最近入校的四辆汽车的第一条行驶记录展示-按照入校时间排序-校内车辆(前端取4个)（右上方表单1）
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/recentlycar/in", method = RequestMethod.GET)
    public Response recentlyCarIn(){

        return new Response().success(temporaryRouteRegisterDao.findCarByState("入校"));
    }

    /**
     * 校园车辆管理系统-管理员入口控制-最近入校的四辆汽车的第一条行驶记录展示-按照入校时间排序-校外车辆(前端取4个)（右上方表单2）
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/recentlycar/out", method = RequestMethod.GET)
    public Response recentlyCarOut(){

        return new Response().success(temporaryRouteUnRegisterDao.findCarByState("入校"));
    }

    /**
     * 校园车辆管理系统-管理员入口控制-根据人工查证对当前临时行驶记录信息进行修改（信息修改按钮）
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/nowcar/modify", method = RequestMethod.POST)
    public Response nowCarModify(@RequestBody TemporaryRouteJson temporaryRouteJson){

        String plateNum = temporaryRouteJson.getPlateNum();
        if (schoolCarRegisterRepo.findByPlateNum(plateNum) != null){
            List<TemporaryRouteRegister> temporaryRouteRegisterList = temporaryRouteRegisterRepo.findByPlateNum(plateNum);
            Integer size = temporaryRouteRegisterList.size();
            for (int i = 0 ; i < size ; i++){
                temporaryRouteRegisterRepo.save(temporaryRouteJson.convertRegisterCarRecord(temporaryRouteRegisterList.get(i)));
            }
            return new Response().success("修改成功");
        }else{
            List<TemporaryRouteUnRegister> temporaryRouteUnRegisterList = temporaryRouteUnRegisterRepo.findByPlateNum(plateNum);
            Integer size = temporaryRouteUnRegisterList.size();
            for (int i = 0 ; i < size ; i++){
                temporaryRouteUnRegisterRepo.save(temporaryRouteJson.convertUnRegisterCarRecord(temporaryRouteUnRegisterList.get(i)));
            }
            return new Response().success("修改成功");
        }
    }
}
