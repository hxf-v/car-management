package cn.edu.ahu.carmanagement.Controller;

import cn.edu.ahu.carmanagement.Dao.TemporaryRouteRegisterDao;
import cn.edu.ahu.carmanagement.Dao.TemporaryRouteUnRegisterDao;
import cn.edu.ahu.carmanagement.Dao.WholeRouteRecordDao;
import cn.edu.ahu.carmanagement.Json.TemporaryRouteJson;
import cn.edu.ahu.carmanagement.Repo.MonitorEquipRepo;
import cn.edu.ahu.carmanagement.Repo.SchoolCarRegisterRepo;
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
 * @Despriction: 管理员数据分析Controller
 * @Date: Created in 16:48 2019/4/22
 */
@Controller
@RequestMapping(value = "/admin/dataanalysis")
public class AdminDataAnalysisController {

    private final WholeRouteRecordDao wholeRouteRecordDao;
    private final MonitorEquipRepo monitorEquipRepo;
    private final TemporaryRouteRegisterDao temporaryRouteRegisterDao;
    private final TemporaryRouteUnRegisterDao temporaryRouteUnRegisterDao;
    private final SchoolCarRegisterRepo schoolCarRegisterRepo;

    /**
     * 依赖注入
     * @param wholeRouteRecordDao
     * @param monitorEquipRepo
     * @param temporaryRouteRegisterDao
     * @param temporaryRouteUnRegisterDao
     * @param schoolCarRegisterRepo
     */
    public AdminDataAnalysisController(WholeRouteRecordDao wholeRouteRecordDao,
                                       MonitorEquipRepo monitorEquipRepo,
                                       TemporaryRouteRegisterDao temporaryRouteRegisterDao,
                                       TemporaryRouteUnRegisterDao temporaryRouteUnRegisterDao,
                                       SchoolCarRegisterRepo schoolCarRegisterRepo) {
        this.wholeRouteRecordDao = wholeRouteRecordDao;
        this.monitorEquipRepo = monitorEquipRepo;
        this.temporaryRouteRegisterDao = temporaryRouteRegisterDao;
        this.temporaryRouteUnRegisterDao = temporaryRouteUnRegisterDao;
        this.schoolCarRegisterRepo = schoolCarRegisterRepo;
    }


    /**
     * 校园车辆管理系统-管理员数据分析-搜索框车牌号查询车辆-展示行驶节点GroupBy柱状图或饼状图
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Response search(@RequestBody TemporaryRouteJson temporaryRouteJson){

        String plateNum = temporaryRouteJson.getPlateNum();

        if (schoolCarRegisterRepo.findByPlateNum(plateNum) != null){
            return new Response().success(temporaryRouteRegisterDao.findByPlateNumGroupByCountTrr(plateNum));
        }else{
            return new Response().success(temporaryRouteUnRegisterDao.findByPlateNumGroupByCountTrr(plateNum));
        }
    }

    /**
     * 校园车辆管理系统-管理员数据分析-展示临时行驶记录表中各个节点出现频率的柱状图或饼状图-校内车辆
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public Response register(){

        return new Response().success(temporaryRouteRegisterDao.groupByCountTrr());
    }

    /**
     * 校园车辆管理系统-管理员数据分析-展示临时行驶记录表中各个节点出现频率的柱状图或饼状图-校外车辆
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/unregister", method = RequestMethod.GET)
    public Response unRegister(){

        return new Response().success(temporaryRouteUnRegisterDao.groupByCountTrr());
    }

    /**
     * 校园车辆管理系统-管理员数据分析-通过选定起止时间获取GroupBy车牌的饼状图
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/plateNum", method = RequestMethod.POST)
    public Response analysis(@RequestBody TemporaryRouteJson temporaryRouteJson){

        Date startTime = DateStringTranUtil.stringToDate(temporaryRouteJson.getStartTimeStr());
        Date endTime = DateStringTranUtil.stringToDate(temporaryRouteJson.getEndTimeStr());

        return new Response().success(wholeRouteRecordDao.groupByPlateNumBetweenStartAndEndTime(startTime,endTime,"已离校"));
    }
}
