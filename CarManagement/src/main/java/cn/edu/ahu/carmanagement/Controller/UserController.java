package cn.edu.ahu.carmanagement.Controller;

import cn.edu.ahu.carmanagement.Dao.UserInfoDao;
import cn.edu.ahu.carmanagement.Dao.WholeRouteRecordDao;
import cn.edu.ahu.carmanagement.Domain.UserInfo;
import cn.edu.ahu.carmanagement.Json.UserRequestJson;
import cn.edu.ahu.carmanagement.Repo.*;
import cn.edu.ahu.carmanagement.Utils.DateStringTranUtil;
import cn.edu.ahu.carmanagement.Utils.RequestParamCheck;
import cn.edu.ahu.carmanagement.Utils.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author: FZC
 * @Despriction:
 * @Date: Created in 16:48 2019/4/22
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserInfoRepo userInfoRepo;
    private final OperateLogRepo operateLogRepo;
    private final SchoolCarRegisterRepo schoolCarRegisterRepo;
    private final TemporaryRouteRegisterRepo temporaryRouteRegisterRepo;
    private final TemporaryRouteUnRegisterRepo temporaryRouteUnRegisterRepo;
    private final WholeRouteRecordDao wholeRouteRecordDao;
    private final WholeRouteRecordRepo wholeRouteRecordRepo;
    private final ParkingLotStatisticsRepo parkingLotStatisticsRepo;
    private final UserInfoDao userInfoDao;

    /**
     * 依赖注入
     * @param userInfoRepo
     * @param operateLogRepo
     * @param schoolCarRegisterRepo
     * @param temporaryRouteRegisterRepo
     * @param temporaryRouteUnRegisterRepo
     * @param wholeRouteRecordDao
     * @param wholeRouteRecordRepo
     * @param parkingLotStatisticsRepo
     * @param userInfoDao
     */
    public UserController(UserInfoRepo userInfoRepo,
                          OperateLogRepo operateLogRepo,
                          SchoolCarRegisterRepo schoolCarRegisterRepo,
                          TemporaryRouteRegisterRepo temporaryRouteRegisterRepo,
                          TemporaryRouteUnRegisterRepo temporaryRouteUnRegisterRepo,
                          WholeRouteRecordDao wholeRouteRecordDao,
                          WholeRouteRecordRepo wholeRouteRecordRepo,
                          ParkingLotStatisticsRepo parkingLotStatisticsRepo,
                          UserInfoDao userInfoDao) {
        this.userInfoRepo = userInfoRepo;
        this.operateLogRepo = operateLogRepo;
        this.schoolCarRegisterRepo = schoolCarRegisterRepo;
        this.temporaryRouteRegisterRepo = temporaryRouteRegisterRepo;
        this.temporaryRouteUnRegisterRepo = temporaryRouteUnRegisterRepo;
        this.wholeRouteRecordDao = wholeRouteRecordDao;
        this.wholeRouteRecordRepo = wholeRouteRecordRepo;
        this.parkingLotStatisticsRepo = parkingLotStatisticsRepo;
        this.userInfoDao = userInfoDao;
    }

    /**
     * 校园车辆管理系统-普通用户-登录
     * @param userRequestJson
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody UserRequestJson userRequestJson){

        String account = userRequestJson.getAccount();
        String password = userRequestJson.getPassword();

        //数据校验
        RequestParamCheck.checkNull(account, "account");
        RequestParamCheck.checkNull(password, "password");

        if (userInfoRepo.findByAccountAndPassword(account,password) != null){
            return new Response().success(userInfoDao.findByAccountAndPassword(account,password).get(0));
        }else{
            return new Response().failure("账户或密码不正确，请查证后再试");
        }
    }

    /**
     * 校园车辆管理系统-普通用户-注册
     * @param userRequestJson
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(@RequestBody UserRequestJson userRequestJson){

        String account = userRequestJson.getAccount();
        String password = userRequestJson.getPassword();

        //数据校验
        RequestParamCheck.checkNull(account, "account");
        RequestParamCheck.checkNull(password, "password");

        if (userInfoRepo.findByAccountAndPassword(account,password) == null){
            userInfoRepo.save(userRequestJson.convertUser());
            return new Response().success();
        }else{
            return new Response().failure("账户已存在，请直接登录");
        }
    }

    /**
     * 校园车辆管理系统-普通用户-查询当前用户校内实时行驶记录
     * @param userRequestJson
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/realtimerecord", method = RequestMethod.POST)
    public Response realTimeRecord(@RequestBody UserRequestJson userRequestJson){

        String plateNum = userRequestJson.getPlateNum();

        //数据校验
        RequestParamCheck.checkNull(plateNum, "plateNum");

        if (schoolCarRegisterRepo.findByPlateNum(plateNum) != null){
            //校内车辆实时行驶记录
            return new Response().success(temporaryRouteRegisterRepo.findByPlateNum(plateNum));
        }else{
            //校外车辆实时行驶记录
            return new Response().success(temporaryRouteUnRegisterRepo.findByPlateNum(plateNum));
        }
    }

    /**
     * 校园车辆管理系统-普通用户-停车场实况查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/parkinglot", method = RequestMethod.GET)
    public Response parkingLot(){

        return new Response().success(parkingLotStatisticsRepo.findAll());
    }

    /**
     * 校园车辆管理系统-普通用户-通过确定起止时间查询本车辆历史行驶记录
     * @param userRequestJson
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/historyrecord", method = RequestMethod.POST)
    public Response historyRecord(@RequestBody UserRequestJson userRequestJson){

        String plateNum = userRequestJson.getPlateNum();
        Date startTime = DateStringTranUtil.stringToDate(userRequestJson.getStartTimeStr());
        Date endTime = DateStringTranUtil.stringToDate(userRequestJson.getEndTimeStr());

        //数据校验
        RequestParamCheck.checkNull(plateNum, "plateNum");

        if (schoolCarRegisterRepo.findByPlateNum(plateNum) != null){
            return new Response().success(wholeRouteRecordDao.findByPlateNumBetweenStartAndEndTime(plateNum,startTime,endTime));
        }else{
            return new Response().success(wholeRouteRecordRepo.findByPlateNumBetweenStartAndEndTime(plateNum,startTime,endTime));
        }
    }

    /**
     * 校园车辆管理系统-普通用户-修改密码
     * @param userRequestJson
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public Response password(@RequestBody UserRequestJson userRequestJson){

        String account = userRequestJson.getAccount();

        //数据校验
        RequestParamCheck.checkNull(account, "account");

        if (userInfoRepo.findByAccount(account) != null){
            UserInfo userInfo = userInfoRepo.findByAccount(account);
            userInfoRepo.save(userRequestJson.convertUserPassword(userInfo));
            return new Response().success("账户密码修改成功");
        }else{
            return new Response().failure("账户不存在，请查证");
        }
    }

    /**
     * 校园车辆管理系统-普通用户-修改账户绑定车辆号牌
     * @param userRequestJson
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/platenum", method = RequestMethod.POST)
    public Response plateNum(@RequestBody UserRequestJson userRequestJson){

        String account = userRequestJson.getAccount();

        //数据校验
        RequestParamCheck.checkNull(account, "account");

        if (userInfoRepo.findByAccount(account) != null){
            UserInfo userInfo = userInfoRepo.findByAccount(account);
            userInfoRepo.save(userRequestJson.convertUserPlateNum(userInfo));
            return new Response().success("账户绑定号牌修改成功");
        }else{
            return new Response().failure("账户不存在，请查证");
        }
    }

    /**
     * 校园车辆管理系统-普通用户-线上缴费功能需要删除
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/parkinglot", method = RequestMethod.POST)
//    public Response parkingLot(){
//
//        return new Response().success(parkingLotStatisticsRepo.findAll());
//    }
}
