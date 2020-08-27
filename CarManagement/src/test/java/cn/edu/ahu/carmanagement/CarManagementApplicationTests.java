package cn.edu.ahu.carmanagement;

import cn.edu.ahu.carmanagement.Dao.ParkingLotStatisticsDao;
import cn.edu.ahu.carmanagement.Dao.SchoolStatusRecordModelDao;
import cn.edu.ahu.carmanagement.Dao.TemporaryRouteRegisterDao;
import cn.edu.ahu.carmanagement.Dao.WholeRouteRecordDao;
import cn.edu.ahu.carmanagement.Domain.SchoolCarRegister;
import cn.edu.ahu.carmanagement.Repo.*;
import cn.edu.ahu.carmanagement.Service.SchoolStatusRecordService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CarManagementApplicationTests {

    @Autowired
    private SchoolStatusRecordRepo schoolStatusRecordRepo;
    @Autowired
    private SchoolStatusRecordModelDao schoolStatusRecordModelDao;
    @Autowired
    private CarRegisterRepo carRegisterRepo;
    @Autowired
    private SchoolStatusRecordService schoolStatusRecordService;
    @Autowired
    private ParkingLotStatisticsRepo parkingLotStatisticsRepo;
    @Autowired
    private ParkingLotStatisticsDao parkingLotStatisticsDao;
    @Autowired
    private WholeRouteRecordDao wholeRouteRecordDao;
    @Autowired
    private SchoolCarRegisterRepo schoolCarRegisterRepo;
    @Autowired
    private WholeRouteRecordRepo wholeRouteRecordRepo;
    @Autowired
    private TemporaryRouteRegisterDao temporaryRouteRegisterDao;
    @Autowired
    private TemporaryRouteRegisterRepo temporaryRouteRegisterRepo;
    @Autowired
    private TemporaryRouteUnRegisterRepo temporaryRouteUnRegisterRepo;


    @Test
    public void test() throws Exception {
        Pageable pageable = PageRequest.of(0, 15);
        //Assert.assertEquals(2,schoolStatusRecordRepo.findByInTimeIsNotNullAndOutTimeIsNull().size());
        //Assert.assertEquals(2, carRegisterRepo.findByDepartmentAndState("网络中心","启用").size());
        //Assert.assertEquals(5, schoolStatusRecordDao.findByPlateNum("皖ABC222").size());
        //Assert.assertEquals(5, schoolStatusRecordService.findOneCarRecordHistory("皖ABC222").size());
        //Assert.assertEquals(5, parkingLotStatisticsRepo.findByRelateAreaId("4").size());
        //Assert.assertEquals(5, parkingLotStatisticsDao.findByPlateNum(pageable,"皖ABC444").getTotalElements());
        //Assert.assertEquals(88, temporaryRouteUnRegisterRepo.findByPlateNum("苏AB6688").size());
        //Assert.assertEquals(5, wholeRouteRecordDao.findLikePlateNumBetweenStartAndEndTime("皖","2010-10-10 00:00:00","2010-10-10 00:00:00","已缴费","已离场").size());
        //Assert.assertEquals(5, wholeRouteRecordRepo.findAll().size());
        Assert.assertEquals(5,temporaryRouteRegisterDao.findEquipIdByPlateNumOrderByShootingTime("皖ANB520").size());
    }
}

//    @Test
//    public void test2() throws Exception {
//        List<SchoolStatusRecordModel> schoolStatusRecordModelList = schoolStatusRecordService.findOneCarRecordHistory("皖ABC222");
//        System.out.println(schoolStatusRecordModelList.hashCode());
//    }
//}
