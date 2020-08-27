package cn.edu.ahu.carmanagement.Controller;

import cn.edu.ahu.carmanagement.Dao.TemporaryRouteRegisterDao;
import cn.edu.ahu.carmanagement.Dao.WholeRouteRecordDao;
import cn.edu.ahu.carmanagement.Domain.WholeRouteRecord;
import cn.edu.ahu.carmanagement.Json.TemporaryRouteJson;
import cn.edu.ahu.carmanagement.Repo.*;
import cn.edu.ahu.carmanagement.Utils.DateStringTranUtil;
import cn.edu.ahu.carmanagement.Utils.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author: FZC
 * @UpdateBy: Alex
 * @Despriction: 管理员历史记录管理Controller
 * @Date: Created in 16:48 2019/4/22
 */
@Controller
@RequestMapping(value = "/admin/record")
public class AdminRecordManagementController {

    private final ParkingLotStatisticsRepo parkingLotStatisticsRepo;
    private final SchoolCarRegisterRepo schoolCarRegisterRepo;
    private final TemporaryRouteRegisterRepo temporaryRouteRegisterRepo;
    private final TemporaryRouteUnRegisterRepo temporaryRouteUnRegisterRepo;
    private final TemporaryRouteRegisterDao temporaryRouteRegisterDao;
    private final WholeRouteRecordRepo wholeRouteRecordRepo;
    private final WholeRouteRecordDao wholeRouteRecordDao;

    /**
     * 依赖注入
     * @param parkingLotStatisticsRepo
     * @param schoolCarRegisterRepo
     * @param temporaryRouteRegisterRepo
     * @param temporaryRouteUnRegisterRepo
     * @param temporaryRouteRegisterDao
     * @param wholeRouteRecordRepo
     * @param wholeRouteRecordDao
     */
    public AdminRecordManagementController(ParkingLotStatisticsRepo parkingLotStatisticsRepo,
                                           SchoolCarRegisterRepo schoolCarRegisterRepo,
                                           TemporaryRouteRegisterRepo temporaryRouteRegisterRepo,
                                           TemporaryRouteUnRegisterRepo temporaryRouteUnRegisterRepo,
                                           TemporaryRouteRegisterDao temporaryRouteRegisterDao,
                                           WholeRouteRecordRepo wholeRouteRecordRepo,
                                           WholeRouteRecordDao wholeRouteRecordDao) {
        this.parkingLotStatisticsRepo = parkingLotStatisticsRepo;
        this.schoolCarRegisterRepo = schoolCarRegisterRepo;
        this.temporaryRouteRegisterRepo = temporaryRouteRegisterRepo;
        this.temporaryRouteUnRegisterRepo = temporaryRouteUnRegisterRepo;
        this.temporaryRouteRegisterDao = temporaryRouteRegisterDao;
        this.wholeRouteRecordRepo = wholeRouteRecordRepo;
        this.wholeRouteRecordDao = wholeRouteRecordDao;
    }

    /**
     * 校园车辆管理系统-管理员历史记录管理-历史行驶记录条件查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/conditionquery", method = RequestMethod.POST)
    public Response conditionQuery(@RequestBody TemporaryRouteJson temporaryRouteJson){
        String plateNum = temporaryRouteJson.getPlateNum();
        Date startTime = DateStringTranUtil.stringToDate(temporaryRouteJson.getStartTimeStr());
        Date endTime = DateStringTranUtil.stringToDate(temporaryRouteJson.getEndTimeStr());
        return new Response().success(wholeRouteRecordDao.findLikePlateNumBetweenStartAndEndTime(plateNum,startTime,endTime,"已删除"));
    }

    /**
     * 校园车辆管理系统-管理员历史记录管理-编辑历史行驶记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Response edit(@RequestBody TemporaryRouteJson temporaryRouteJson){

        Integer id = temporaryRouteJson.getId();
        WholeRouteRecord wholeRouteRecord = wholeRouteRecordRepo.findById(id).get();
        wholeRouteRecordRepo.save(temporaryRouteJson.convertEdit(wholeRouteRecord));
        return new Response().success("编辑成功");
    }

    /**
     * 校园车辆管理系统-管理员历史记录管理-删除历史行驶记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody TemporaryRouteJson temporaryRouteJson){

        Integer id = temporaryRouteJson.getId();
        WholeRouteRecord wholeRouteRecord = wholeRouteRecordRepo.findById(id).get();
        wholeRouteRecordRepo.save(temporaryRouteJson.convertDelete(wholeRouteRecord));
        return new Response().success("删除成功");
    }
}
