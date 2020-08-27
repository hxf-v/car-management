package cn.edu.ahu.carmanagement.Controller;

import cn.edu.ahu.carmanagement.Dao.TemporaryRouteRegisterDao;
import cn.edu.ahu.carmanagement.Dao.TemporaryRouteUnRegisterDao;
import cn.edu.ahu.carmanagement.Domain.TemporaryRouteRegister;
import cn.edu.ahu.carmanagement.Domain.TemporaryRouteUnRegister;
import cn.edu.ahu.carmanagement.Json.AdminRequestJson;
import cn.edu.ahu.carmanagement.Json.OperateLogJson;
import cn.edu.ahu.carmanagement.Repo.*;
import cn.edu.ahu.carmanagement.Utils.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: FZC
 * @Despriction: 管理员首页Controller
 * @Date: Created in 16:48 2019/4/22
 */
@Controller
@RequestMapping(value = "/admin/homepage")
public class AdminHomePageController {

    private final AdminInfoRepo adminInfoRepo;
    private final OperateLogRepo operateLogRepo;
    private final ParkingLotStatisticsRepo parkingLotStatisticsRepo;
    private final TemporaryRouteRegisterRepo temporaryRouteRegisterRepo;
    private final TemporaryRouteUnRegisterRepo temporaryRouteUnRegisterRepo;
    private final TemporaryRouteRegisterDao temporaryRouteRegisterDao;
    private final TemporaryRouteUnRegisterDao temporaryRouteUnRegisterDao;
    private final SchoolCarRegisterRepo schoolCarRegisterRepo;

    /**
     * 依赖注入
     * @param adminInfoRepo
     * @param operateLogRepo
     * @param parkingLotStatisticsRepo
     * @param temporaryRouteRegisterRepo
     * @param temporaryRouteUnRegisterRepo
     * @param temporaryRouteRegisterDao
     * @param temporaryRouteUnRegisterDao
     * @param schoolCarRegisterRepo
     */
    public AdminHomePageController(AdminInfoRepo adminInfoRepo,
                                   OperateLogRepo operateLogRepo,
                                   ParkingLotStatisticsRepo parkingLotStatisticsRepo,
                                   TemporaryRouteRegisterRepo temporaryRouteRegisterRepo,
                                   TemporaryRouteUnRegisterRepo temporaryRouteUnRegisterRepo,
                                   TemporaryRouteRegisterDao temporaryRouteRegisterDao,
                                   TemporaryRouteUnRegisterDao temporaryRouteUnRegisterDao,
                                   SchoolCarRegisterRepo schoolCarRegisterRepo) {
        this.adminInfoRepo = adminInfoRepo;
        this.operateLogRepo = operateLogRepo;
        this.parkingLotStatisticsRepo = parkingLotStatisticsRepo;
        this.temporaryRouteRegisterRepo = temporaryRouteRegisterRepo;
        this.temporaryRouteUnRegisterRepo = temporaryRouteUnRegisterRepo;
        this.temporaryRouteRegisterDao = temporaryRouteRegisterDao;
        this.temporaryRouteUnRegisterDao = temporaryRouteUnRegisterDao;
        this.schoolCarRegisterRepo = schoolCarRegisterRepo;
    }

    /**
     * 校园车辆管理系统-管理员首页-登录
     * @param adminRequestJson
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody AdminRequestJson adminRequestJson){

        //日志写入模块
        OperateLogJson operateLogJson = new OperateLogJson();
        operateLogJson.setOperateType(adminRequestJson.getOperateType());
        operateLogJson.setOperator(adminRequestJson.getJobNum());
        operateLogJson.setObjectOfOperation(adminRequestJson.getJobNum());
        operateLogJson.setTimeStr(adminRequestJson.getNowTimeStr());

        String jobNum = adminRequestJson.getJobNum();
        String password = adminRequestJson.getPassword();

        if (adminInfoRepo.findByJobNumAndPassword(jobNum,password) != null){
            //写入日志
            operateLogRepo.save(operateLogJson.convert());
            return new Response().success(adminInfoRepo.findByJobNumAndPassword(jobNum,password));
        }else{
            return new Response().failure("工号或密码不正确，请查证后再试");
        }
    }

    /**
     * 校园车辆管理系统-管理员首页-最近入校的  一辆  汽车的所有行驶记录展示-校内车辆-包含注册车辆信息的连表查询
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/recentlycar/in", method = RequestMethod.GET)
    public Response recentlyCarIn(){

        List<TemporaryRouteRegister> temporaryRouteRegisterList = temporaryRouteRegisterDao.findCarByState("入校");
        String plateNum = temporaryRouteRegisterList.get(0).getPlateNum();

        return new Response().success(temporaryRouteRegisterDao.findByPlateNum(plateNum));
    }

    /**
     * 校园车辆管理系统-管理员首页-最近入校的  一辆  汽车的所有行驶记录展示-校外车辆-不包含注册车辆信息
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/recentlycar/out", method = RequestMethod.GET)
    public Response recentlyCarOut(){

        List<TemporaryRouteUnRegister> temporaryRouteUnRegisterList = temporaryRouteUnRegisterDao.findCarByState("入校");
        String plateNum = temporaryRouteUnRegisterList.get(0).getPlateNum();

        return new Response().success(temporaryRouteUnRegisterDao.findByPlateNum(plateNum));
    }

    /**
     * 校园车辆管理系统-管理员首页-通过车牌照搜索该辆车所有临时行驶记录-按时间顺序排好，显示行驶路径
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Response search(@RequestBody AdminRequestJson adminRequestJson){

        String plateNum = adminRequestJson.getPlateNum();

        if (schoolCarRegisterRepo.findByPlateNum(plateNum) != null){
            return new Response().success(temporaryRouteRegisterDao.findByPlateNum(plateNum));
        }else {
            return new Response().success(temporaryRouteUnRegisterDao.findByPlateNum(plateNum));
        }
    }
}
