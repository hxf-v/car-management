package cn.edu.ahu.carmanagement.Controller;

import cn.edu.ahu.carmanagement.Dao.*;
import cn.edu.ahu.carmanagement.Domain.CarNumberGet;
import cn.edu.ahu.carmanagement.Domain.TemporaryParkingLotStatistics;
import cn.edu.ahu.carmanagement.Domain.TemporaryRouteRegister;
import cn.edu.ahu.carmanagement.Domain.TemporaryRouteUnRegister;
import cn.edu.ahu.carmanagement.Json.TemporaryParkingLotStatisticsJson;
import cn.edu.ahu.carmanagement.Json.WholeRouteRecordJson;
import cn.edu.ahu.carmanagement.Repo.*;
import cn.edu.ahu.carmanagement.Service.TimingTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: FZC
 * @Despriction: 定时任务模块
 * @Date:Created in 15:01 2019/7/11
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@Slf4j
public class TimingTaskController {

    @Autowired
    private final TimingTaskService timingTaskService;
    private final TemporaryRouteRegisterRepo temporaryRouteRegisterRepo;
    private final TemporaryRouteUnRegisterRepo temporaryRouteUnRegisterRepo;
    private final TemporaryRouteRegisterDao temporaryRouteRegisterDao;
    private final TemporaryRouteUnRegisterDao temporaryRouteUnRegisterDao;
    private final WholeRouteRecordRepo wholeRouteRecordRepo;
    private final CarNumberGetDao carNumberGetDao ;
    private final CarNumberGetRepo carNumberGetRepo;
    private final TemporaryParkingLotStatisticsDao temporaryParkingLotStatisticsDao;
    private final TemporaryParkingLotStatisticsRepo temporaryParkingLotStatisticsRepo;
    private final ParkingLotStatisticsDao parkingLotStatisticsDao;
    private final ParkingLotStatisticsRepo parkingLotStatisticsRepo;
    /**
     * 依赖注入
     * @param timingTaskService
     * @param temporaryRouteRegisterRepo
     * @param temporaryRouteUnRegisterRepo
     * @param temporaryRouteRegisterDao
     * @param temporaryRouteUnRegisterDao
     * @param carNumberGetRepo
     * @param temporaryParkingLotStatisticsRepo
     * @param parkingLotStatisticsDao
     * @param parkingLotStatisticsRepo
     */
    public TimingTaskController(TimingTaskService timingTaskService,
                                TemporaryRouteRegisterRepo temporaryRouteRegisterRepo,
                                TemporaryRouteUnRegisterRepo temporaryRouteUnRegisterRepo,
                                TemporaryRouteRegisterDao temporaryRouteRegisterDao,
                                TemporaryRouteUnRegisterDao temporaryRouteUnRegisterDao,
                                WholeRouteRecordRepo wholeRouteRecordRepo,
                                CarNumberGetDao carNumberGetDao,
                                CarNumberGetRepo carNumberGetRepo, TemporaryParkingLotStatisticsDao temporaryParkingLotStatisticsDao,
                                TemporaryParkingLotStatisticsRepo temporaryParkingLotStatisticsRepo, ParkingLotStatisticsDao parkingLotStatisticsDao, ParkingLotStatisticsRepo parkingLotStatisticsRepo) {
        this.timingTaskService = timingTaskService;
        this.temporaryRouteRegisterRepo = temporaryRouteRegisterRepo;
        this.temporaryRouteUnRegisterRepo = temporaryRouteUnRegisterRepo;
        this.temporaryRouteRegisterDao = temporaryRouteRegisterDao;
        this.temporaryRouteUnRegisterDao = temporaryRouteUnRegisterDao;
        this.wholeRouteRecordRepo = wholeRouteRecordRepo;
        this.carNumberGetDao=carNumberGetDao;
        this.carNumberGetRepo = carNumberGetRepo;
        this.temporaryParkingLotStatisticsDao=temporaryParkingLotStatisticsDao;
        this.temporaryParkingLotStatisticsRepo = temporaryParkingLotStatisticsRepo;
        this.parkingLotStatisticsDao = parkingLotStatisticsDao;
        this.parkingLotStatisticsRepo = parkingLotStatisticsRepo;
    }
    /**

     * @Author: Alex
     * @Despriction: 停车场更新   5秒钟更新一次
     * @Date:Created in 15:01 2019/7/11
     *
     */
    @Scheduled(cron = "*/5 * * * * ?")  //5秒钟
    //@Scheduled(cron = "0 */5 * * * ?")  //5分钟
    @Transactional
    public void ParkingLogStatistics(){
        List<CarNumberGet> CarNumberGetList= carNumberGetRepo.findAll();
        if (CarNumberGetList.size()!=0){
            String PlateNum=CarNumberGetList.get(0).getPlateNum();
            String Car_location=CarNumberGetList.get(0).getCar_location();
            List<TemporaryParkingLotStatistics> TemporaryParkingLotStatisticsList=temporaryParkingLotStatisticsRepo.findByPlateNum(PlateNum);
            if (TemporaryParkingLotStatisticsList.size()==0){
                TemporaryParkingLotStatisticsJson temporaryParkingLotStatisticsJson=new TemporaryParkingLotStatisticsJson();
                temporaryParkingLotStatisticsJson.setPlateNum(PlateNum);
                temporaryParkingLotStatisticsJson.setArea_id(Car_location);
                temporaryParkingLotStatisticsRepo.save(temporaryParkingLotStatisticsJson.convert());
            }else {
                temporaryParkingLotStatisticsRepo.deleteByPlateNum(PlateNum);
            }
            //本来应该是通过id删除这一条记录，但是没id，只能删除这一辆车的记录，所以在5秒内该表内不能存在同一辆车的两条以上记录
            carNumberGetRepo.deleteAllByPlateNum(PlateNum);
        }
        for (int i = 10; i < 20; i++) {
            List<TemporaryParkingLotStatistics> temporaryParkingLotStatisticsList=temporaryParkingLotStatisticsDao.findByArea_id(i+"");
            parkingLotStatisticsRepo.UpdateOccupy(temporaryParkingLotStatisticsList.size(),Integer.toString(i));
            parkingLotStatisticsRepo.UpdateSurplusAndUpdateTime(new Date(System.currentTimeMillis()),Integer.toString(i));
        }
    }

    /**
     * 定时启动-行驶记录临时表整理-校内车辆
     * 每日0时执行一次
     */
    //@Scheduled(cron = "0 0 1 1 * ?")  //每月1号凌晨1时
    @Scheduled(cron = "0 0 0 * * ?")  // 每日0时执行
    @Transactional
    public void arrangementInCar(){
        List<TemporaryRouteRegister> temporaryRouteRegisterList = temporaryRouteRegisterRepo.findByState("入校");
        Integer size = temporaryRouteRegisterList.size();
        List<String> plateNumList = new ArrayList<>();
        if (size > 0){
            for (int i = 0 ; i < size ; i ++){
                plateNumList.add(temporaryRouteRegisterList.get(i).getPlateNum());
            }
        }
        for (int j = 0 ; j < size ; j ++){
            String plateNum = plateNumList.get(j);
            if (temporaryRouteRegisterRepo.findByPlateNumAndState(plateNum,"入校") != null && temporaryRouteRegisterRepo.findByPlateNumAndState(plateNum,"离校") != null){

                //计算时间差
                long hour = 0;
                long day = 0;
                long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
                long nh = 1000 * 60 * 60;// 一小时的毫秒数
                long differ = temporaryRouteRegisterRepo.findByPlateNumAndState(plateNum,"离校").getShootingTime().getTime() - temporaryRouteRegisterRepo.findByPlateNumAndState(plateNum,"入校").getShootingTime().getTime();
                day = differ / nd;// 计算差多少天
                hour = differ % nd / nh + day * 24;// 计算差多少小时

                //计算停车费用cost
                long cost = hour * 3;

                //获取所有临时行驶记录中的equipId，并拼接成一条行驶路径节点字符串
                List<Integer> equipIdList = temporaryRouteRegisterDao.findEquipIdByPlateNumOrderByShootingTime(plateNum);
                String str = "";
                for (int k = 0 ; k < equipIdList.size() ; k ++){
                    str = str + String.valueOf(equipIdList.get(k)) + ",";
                }

                //将参数写入WholeRouteRecordJson中准备进行WholeRouteRecord添加
                WholeRouteRecordJson wholeRouteRecordJson = new WholeRouteRecordJson();
                wholeRouteRecordJson.setPlateNum(plateNum);
                wholeRouteRecordJson.setParkingTime(String.valueOf(hour));
                wholeRouteRecordJson.setCost(String.valueOf(cost));
                wholeRouteRecordJson.setInTime(temporaryRouteRegisterRepo.findByPlateNumAndState(plateNum,"入校").getEntryTime());
                wholeRouteRecordJson.setSettlementTime(new Date(System.currentTimeMillis()));
                wholeRouteRecordJson.setRouteAssemble(str);

                //添加全程行驶记录
                wholeRouteRecordRepo.save(wholeRouteRecordJson.convert());

                //删除临时行驶记录表中该车辆的实时行驶记录（注释是因为还没实现往临时表刷数据，删除会无法展示）
                //temporaryRouteRegisterRepo.deleteByPlateNum(plateNum);
            }
        }
    }

    /**
     * 定时启动-行驶记录临时表整理-校外车辆
     */
    //@Scheduled(cron = "0 0 1 1 * ?")  //每月1号凌晨1时
    //@Scheduled(cron = "0 */59 * * * ?")  //59分钟
    @Scheduled(cron = "0 0 0 * * ?")  // 每日0时执行

    @Transactional
    public void arrangementOutCar(){
        List<TemporaryRouteUnRegister> temporaryRouteUnRegisterList = temporaryRouteUnRegisterRepo.findByState("入校");
        Integer size = temporaryRouteUnRegisterList.size();
        List<String> plateNumList = new ArrayList<>();
        if (size > 0){
            for (int i = 0 ; i < size ; i ++){
                plateNumList.add(temporaryRouteUnRegisterList.get(i).getPlateNum());
            }
        }
        for (int j = 0 ; j < size ; j ++){
            String plateNum = plateNumList.get(j);
            if (temporaryRouteUnRegisterRepo.findByPlateNumAndState(plateNum,"入校") != null && temporaryRouteUnRegisterRepo.findByPlateNumAndState(plateNum,"离校") != null){

                //计算时间差
                long hour = 0;
                long day = 0;
                long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
                long nh = 1000 * 60 * 60;// 一小时的毫秒数
                long differ = temporaryRouteUnRegisterRepo.findByPlateNumAndState(plateNum,"离校").getShootingTime().getTime() - temporaryRouteUnRegisterRepo.findByPlateNumAndState(plateNum,"入校").getShootingTime().getTime();
                day = differ / nd;// 计算差多少天
                hour = differ % nd / nh + day * 24;// 计算差多少小时

                //计算停车费用cost
                long cost = hour * 3;

                //获取所有临时行驶记录中的equipId，并拼接成一条行驶路径节点字符串
                List<Integer> equipIdList = temporaryRouteUnRegisterDao.findEquipIdByPlateNumOrderByShootingTime(plateNum);
                String str = "";
                for (int k = 0 ; k < equipIdList.size() ; k ++){
                    str = str + String.valueOf(equipIdList.get(k)) + ",";
                }

                //将参数写入WholeRouteRecordJson中准备进行WholeRouteRecord添加
                WholeRouteRecordJson wholeRouteRecordJson = new WholeRouteRecordJson();
                wholeRouteRecordJson.setPlateNum(plateNum);
                wholeRouteRecordJson.setParkingTime(String.valueOf(hour));
                wholeRouteRecordJson.setCost(String.valueOf(cost));
                wholeRouteRecordJson.setInTime(temporaryRouteUnRegisterRepo.findByPlateNumAndState(plateNum,"入校").getEntryTime());
                wholeRouteRecordJson.setSettlementTime(new Date(System.currentTimeMillis()));
                wholeRouteRecordJson.setRouteAssemble(str);

                //添加全程行驶记录
                wholeRouteRecordRepo.save(wholeRouteRecordJson.convert());

                //删除临时行驶记录表中该车辆的实时行驶记录（原因同上）
                //temporaryRouteUnRegisterRepo.deleteByPlateNum(plateNum);
            }
        }
    }
}
