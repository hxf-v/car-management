package cn.edu.ahu.carmanagement.Controller;

import cn.edu.ahu.carmanagement.Repo.CarNumberGetRepo;
import cn.edu.ahu.carmanagement.Repo.SchoolStatusRecordRepo;
import cn.edu.ahu.carmanagement.Service.SchoolStatusRecordService;
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
 * @Despriction: 校园车辆管理系统-首页（时间格式均已修改）
 * @Date:Created in 16:45 2019/4/22
 */
@Controller
@RequestMapping(value = "/home")
public class HomePageController {

    private final SchoolStatusRecordRepo schoolStatusRecordRepo;
    private final SchoolStatusRecordService schoolStatusRecordService;
    private final CarNumberGetRepo carNumberGetRepo;

    /**
     * 依赖注入
     * @param schoolStatusRecordRepo
     * @param schoolStatusRecordService
     * @param carNumberGetRepo
     */
    @Autowired
    public HomePageController(SchoolStatusRecordRepo schoolStatusRecordRepo,
                              SchoolStatusRecordService schoolStatusRecordService,
                              CarNumberGetRepo carNumberGetRepo) {
        this.schoolStatusRecordRepo = schoolStatusRecordRepo;
        this.schoolStatusRecordService = schoolStatusRecordService;
        this.carNumberGetRepo = carNumberGetRepo;
    }

    /**
     * 校园车辆管理系统-首页-获取当前进入校内所有车辆信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/in", method = RequestMethod.GET)
    public Response in(Integer pageNumber,Integer pageSize){
        //数据校验
        RequestParamCheck.checkNull(pageNumber, "pageNumber");
        RequestParamCheck.checkNull(pageSize, "pageSize");
        //pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return schoolStatusRecordService.findAllCarByState(pageable,"在校");
    }

    /**
     * 校园车辆管理系统-首页-获取所有离校车辆信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/out", method = RequestMethod.GET)
    public Response out(Integer pageNumber,Integer pageSize){
        //数据校验
        RequestParamCheck.checkNull(pageNumber, "pageNumber");
        RequestParamCheck.checkNull(pageSize, "pageSize");
        //pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return schoolStatusRecordService.findAllCarByState(pageable,"离校");
    }

    /**
     * 校园车辆管理系统-首页-通过车牌号精准查询车辆所有历史信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/one/all", method = RequestMethod.GET)
    public Response oneAll(Integer pageNumber,Integer pageSize,String plateNum){
        //数据校验
        RequestParamCheck.checkNull(pageNumber, "pageNumber");
        RequestParamCheck.checkNull(pageSize, "pageSize");
        //pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return schoolStatusRecordService.findOneCarRecordHistory(pageable,plateNum);
    }

    /**
     * 校园车辆管理系统-首页-通过车牌号查询当前在校单个车辆的所有信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/one/now", method = RequestMethod.GET)
    public Response oneNow(Integer pageNumber,Integer pageSize,String plateNum){
        //数据校验
        RequestParamCheck.checkNull(pageNumber, "pageNumber");
        RequestParamCheck.checkNull(pageSize, "pageSize");
        //pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return schoolStatusRecordService.findOneCarRecordInSchool(pageable,plateNum,"在校");
    }

    /**
     * 校园车辆管理系统-首页-通过车牌号查询当前在校单个车辆的行驶路线信息（绘制轨迹图）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/one/route", method = RequestMethod.GET)
    public Response oneRoute(Integer pageNumber,Integer pageSize,String plateNum){
        //数据校验
        RequestParamCheck.checkNull(pageNumber, "pageNumber");
        RequestParamCheck.checkNull(pageSize, "pageSize");
        //pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return schoolStatusRecordService.findOneCarRouteInSchool(pageable,plateNum,"在校");
    }

    /**
     * 校园车辆管理系统-首页-车牌比对接口（识别系统车牌和在册车辆车牌比对）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/compare", method = RequestMethod.POST)
    public Response compare(){
        if (carNumberGetRepo.findByState("待处理") != null){
            return new Response().success(carNumberGetRepo.findByState("待处理"));
        }else {
            return new Response().failure("查证失败，请重试");
        }
    }

    /**
     * 校园车辆管理系统-首页-停车场区域信息获取
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/parkinglot", method = RequestMethod.GET)
    public Response parkingLot(Integer pageNumber,Integer pageSize){
        //数据校验
        RequestParamCheck.checkNull(pageNumber, "pageNumber");
        RequestParamCheck.checkNull(pageSize, "pageSize");
        //pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return schoolStatusRecordService.findAllCarByState(pageable,"在校");
    }
}
