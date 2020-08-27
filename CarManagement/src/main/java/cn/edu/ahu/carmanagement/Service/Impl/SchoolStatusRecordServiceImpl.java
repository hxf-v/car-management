package cn.edu.ahu.carmanagement.Service.Impl;

import cn.edu.ahu.carmanagement.Dao.DrivingRouteRecordDao;
import cn.edu.ahu.carmanagement.Dao.SchoolStatusRecordDao;
import cn.edu.ahu.carmanagement.Dao.SchoolStatusRecordModelDao;
import cn.edu.ahu.carmanagement.Repo.DrivingRouteRecordRepo;
import cn.edu.ahu.carmanagement.Repo.SchoolStatusRecordRepo;
import cn.edu.ahu.carmanagement.Service.SchoolStatusRecordService;
import cn.edu.ahu.carmanagement.Utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author: FZC
 * @Despriction:
 * @Date:Created in 16:49 2019/4/22
 */
@Service
public class SchoolStatusRecordServiceImpl implements SchoolStatusRecordService {

    @Autowired
    private SchoolStatusRecordRepo schoolStatusRecordRepo;
    @Autowired
    private SchoolStatusRecordDao schoolStatusRecordDao;
    @Autowired
    private SchoolStatusRecordModelDao schoolStatusRecordModelDao;
    @Autowired
    private DrivingRouteRecordRepo drivingRouteRecordRepo;
    @Autowired
    private DrivingRouteRecordDao drivingRouteRecordDao;
    
    @Override
    public Response findOneCarRecordIn(String plateNum) {
        
        if (schoolStatusRecordRepo.findByPlateNumAndState(plateNum,"在校") != null){
            return new Response().success(schoolStatusRecordRepo.findByPlateNumAndState(plateNum,"在校"));
        }else {
            return new Response().failure("查询车辆不在校内");
        }
    }

    @Override
    public Response findOneCarRecordHistory(Pageable pageable,String plateNum) {
        return new Response().success(schoolStatusRecordModelDao.findByPlateNum(pageable,plateNum));
    }

    @Override
    public Response findOneCarRecordInSchool(Pageable pageable,String plateNum, String state) {
        return new Response().success(schoolStatusRecordModelDao.findByPlateNumAndState(pageable,plateNum,state));
    }

    @Override
    public Response findOneCarRouteInSchool(Pageable pageable,String plateNum, String state) {
        if (drivingRouteRecordRepo.findByPlateNumAndState(plateNum,state) != null){
            return new Response().success(drivingRouteRecordDao.findByPlateNumAndState(pageable,plateNum,state));
        }else {
            return new Response().failure("查询车辆不在校内");
        }
    }

    @Override
    public Response findAllCarByState(Pageable pageable, String state) {
        return new Response().success(schoolStatusRecordDao.findByState(pageable,state));
    }

//    @Override
//    public Response findAllCarByState(Pageable pageable, String state) {
//
//        List<SchoolStatusRecord> schoolStatusRecordList = schoolStatusRecordRepo.findByState(state);
//        List<SchoolStatusRecordVo> schoolStatusRecordVoList = new ArrayList<>();
//        //时间格式转换
//        for (SchoolStatusRecord item:schoolStatusRecordList){
//            SchoolStatusRecordVo schoolStatusRecordVo = new SchoolStatusRecordVo();
//            BeanUtils.copyProperties(item,schoolStatusRecordVo);
//            if (item.getInTime() == null)
//                schoolStatusRecordVo.setInTimeStr(null);
//            else
//                schoolStatusRecordVo.setInTimeStr(DateStringTranUtil.dateToString(item.getInTime()));
//
//            if (item.getOutTime() == null)
//                schoolStatusRecordVo.setOutTimeStr(null);
//            else
//                schoolStatusRecordVo.setOutTimeStr(DateStringTranUtil.dateToString(item.getOutTime()));
//            schoolStatusRecordVoList.add(schoolStatusRecordVo);
//        }
//        Integer size = schoolStatusRecordVoList.size();
//        Long count = size.longValue();
//
//        return new Response().success(new PageImpl<>(schoolStatusRecordVoList, pageable, count));
//    }
}
