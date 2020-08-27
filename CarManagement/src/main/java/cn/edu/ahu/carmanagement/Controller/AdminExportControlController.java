package cn.edu.ahu.carmanagement.Controller;

import cn.edu.ahu.carmanagement.Dao.TemporaryRouteRegisterDao;
import cn.edu.ahu.carmanagement.Dao.TemporaryRouteUnRegisterDao;
import cn.edu.ahu.carmanagement.Domain.TemporaryRouteRegister;
import cn.edu.ahu.carmanagement.Domain.TemporaryRouteUnRegister;
import cn.edu.ahu.carmanagement.Domain.WholeRouteRecord;
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
 * @Despriction: 管理员出口控制Controller
 * @Date: Created in 16:48 2019/4/22
 */
@Controller
@RequestMapping(value = "/admin/export")
public class AdminExportControlController {

    private final ParkingLotStatisticsRepo parkingLotStatisticsRepo;
    private final SchoolCarRegisterRepo schoolCarRegisterRepo;
    private final TemporaryRouteRegisterRepo temporaryRouteRegisterRepo;
    private final TemporaryRouteUnRegisterRepo temporaryRouteUnRegisterRepo;
    private final TemporaryRouteRegisterDao temporaryRouteRegisterDao;
    private final TemporaryRouteUnRegisterDao temporaryRouteUnRegisterDao;
    private final WholeRouteRecordRepo wholeRouteRecordRepo;

    /**
     * 依赖注入
     * @param parkingLotStatisticsRepo
     * @param schoolCarRegisterRepo
     * @param temporaryRouteRegisterRepo
     * @param temporaryRouteUnRegisterRepo
     * @param temporaryRouteRegisterDao
     * @param wholeRouteRecordRepo
     */
    public AdminExportControlController(ParkingLotStatisticsRepo parkingLotStatisticsRepo,
                                        SchoolCarRegisterRepo schoolCarRegisterRepo,
                                        TemporaryRouteRegisterRepo temporaryRouteRegisterRepo,
                                        TemporaryRouteUnRegisterRepo temporaryRouteUnRegisterRepo,
                                        TemporaryRouteRegisterDao temporaryRouteRegisterDao,
                                        WholeRouteRecordRepo wholeRouteRecordRepo,
                                        TemporaryRouteUnRegisterDao temporaryRouteUnRegisterDao) {
        this.parkingLotStatisticsRepo = parkingLotStatisticsRepo;
        this.schoolCarRegisterRepo = schoolCarRegisterRepo;
        this.temporaryRouteRegisterRepo = temporaryRouteRegisterRepo;
        this.temporaryRouteUnRegisterRepo = temporaryRouteUnRegisterRepo;
        this.temporaryRouteRegisterDao = temporaryRouteRegisterDao;
        this.wholeRouteRecordRepo = wholeRouteRecordRepo;
        this.temporaryRouteUnRegisterDao=temporaryRouteUnRegisterDao;
    }

    /**
     * 校园车辆管理系统-管理员出口控制-默认显示当前最近离校的一个校内车辆的信息（包含图片base64码）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/recentlyone", method = RequestMethod.GET)
    public Response recentlyOne(){

        return new Response().success(temporaryRouteRegisterDao.findRecentlyOne("离校").get(0));
    }

    /**
     * 校园车辆管理系统-管理员出口控制-校内停车场实况查询（停车场实况按钮）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/parkinglot", method = RequestMethod.GET)
    public Response parkingLot(){

        return new Response().success(parkingLotStatisticsRepo.findAll());
    }

    /**
     * 校园车辆管理系统-管理员出口控制-查询当前车辆的校内所有行驶记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/realtime", method = RequestMethod.POST)
    public Response realTime(@RequestBody TemporaryRouteJson temporaryRouteJson){

        String plateNum = temporaryRouteJson.getPlateNum();
        if (schoolCarRegisterRepo.findByPlateNum(plateNum) != null){
            return new Response().success(temporaryRouteRegisterDao.findByPlateNum2(plateNum));
        }else{
            return new Response().success(temporaryRouteUnRegisterDao.findByPlateNum2(plateNum));
        }
    }

    /**
     * 校园车辆管理系统-管理员出口控制-根据人工查证对当前临时行驶记录信息进行修改（信息修改按钮）
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

    /**
     * 校园车辆管理系统-管理员出口控制-人工收费按钮
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/manualcharge", method = RequestMethod.POST)
    public Response manualCharge(@RequestBody TemporaryRouteJson temporaryRouteJson){

        String plateNum = temporaryRouteJson.getPlateNum();
        //System.out.println(plateNum);
        if (wholeRouteRecordRepo.findByPlateNumAndState(plateNum,"未缴费") != null){
            WholeRouteRecord wholeRouteRecord = wholeRouteRecordRepo.findByPlateNumAndState(plateNum,"未缴费");
            wholeRouteRecordRepo.save(temporaryRouteJson.convert(wholeRouteRecord));
            return new Response().success("缴费成功，可以离场");
        }else {
            return new Response().failure("该车辆已缴费");
        }
    }
}
