package cn.edu.ahu.carmanagement.Service.Impl;

import cn.edu.ahu.carmanagement.Dao.ParkingRecordDao;
import cn.edu.ahu.carmanagement.Domain.ParkingRecord;
import cn.edu.ahu.carmanagement.Repo.ParkingRecordRepo;
import cn.edu.ahu.carmanagement.Service.ParkingRecordService;
import cn.edu.ahu.carmanagement.Utils.DateStringTranUtil;
import cn.edu.ahu.carmanagement.Utils.Response;
import cn.edu.ahu.carmanagement.Vo.ParkingRecordVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: FZC
 * @Despriction:
 * @Date:Created in 16:25 2019/6/11
 */
@Service
public class ParkingRecordServiceImpl implements ParkingRecordService {

    @Autowired
    private ParkingRecordRepo parkingRecordRepo;
    @Autowired
    private ParkingRecordDao parkingRecordDao;

    @Override
    public Response findOneCarParking(String plateNum, String state) {
        if (parkingRecordRepo.findByPlateNumAndState(plateNum,"在停") != null){
            return new Response().success(parkingRecordRepo.findByPlateNumAndState(plateNum,"在停"));
        }else {
            return new Response().failure("查无此车记录，请查证");
        }
    }

    @Override
    public Response findOneCarRecordParking(Pageable pageable, String plateNum) {
        return new Response().success(parkingRecordDao.findByPlateNum(pageable,plateNum));
    }

    @Override
    public Response findCarParking(Pageable pageable, String state) {
        return new Response().success(parkingRecordDao.findByState(pageable,state));
    }

//    @Override
//    public Response findOneCarRecordParking(Pageable pageable,String plateNum) {
//        List<ParkingRecord> parkingRecordList = parkingRecordRepo.findByPlateNum(pageable,plateNum);
//        List<ParkingRecordVo> parkingRecordVoList = new ArrayList<>();
//        //时间格式转换
//        for (ParkingRecord item:parkingRecordList){
//            ParkingRecordVo parkingRecordVo = new ParkingRecordVo();
//            BeanUtils.copyProperties(item,parkingRecordVo);
//            if (item.getParkingTime() == null)
//                parkingRecordVo.setParkingTimeStr(null);
//            else
//                parkingRecordVo.setParkingTimeStr(DateStringTranUtil.dateToString(item.getParkingTime()));
//
//            if (item.getDepartureTime() == null)
//                parkingRecordVo.setDepartureTimeStr(null);
//            else
//                parkingRecordVo.setDepartureTimeStr(DateStringTranUtil.dateToString(item.getDepartureTime()));
//            parkingRecordVoList.add(parkingRecordVo);
//
//            if (item.getStoppingTime() == null)
//                parkingRecordVo.setStoppingTimeStr(null);
//            else
//                parkingRecordVo.setStoppingTimeStr(DateStringTranUtil.dateToString(item.getStoppingTime()));
//            parkingRecordVoList.add(parkingRecordVo);
//        }
//        Integer size = parkingRecordVoList.size();
//        Long count = size.longValue();
//        return new Response().success(new PageImpl<>(parkingRecordVoList, pageable, count));
//    }

//    @Override
//    public Response findCarParking(Pageable pageable,String state) {
//        List<ParkingRecord> parkingRecordList = parkingRecordRepo.findByState(pageable,state);
//        List<ParkingRecordVo> parkingRecordVoList = new ArrayList<>();
//        //时间格式转换
//        for (ParkingRecord item:parkingRecordList){
//            ParkingRecordVo parkingRecordVo = new ParkingRecordVo();
//            BeanUtils.copyProperties(item,parkingRecordVo);
//            if (item.getParkingTime() == null)
//                parkingRecordVo.setParkingTimeStr(null);
//            else
//                parkingRecordVo.setParkingTimeStr(DateStringTranUtil.dateToString(item.getParkingTime()));
//
//            if (item.getDepartureTime() == null)
//                parkingRecordVo.setDepartureTimeStr(null);
//            else
//                parkingRecordVo.setDepartureTimeStr(DateStringTranUtil.dateToString(item.getDepartureTime()));
//            parkingRecordVoList.add(parkingRecordVo);
//
//            if (item.getStoppingTime() == null)
//                parkingRecordVo.setStoppingTimeStr(null);
//            else
//                parkingRecordVo.setStoppingTimeStr(DateStringTranUtil.dateToString(item.getStoppingTime()));
//            parkingRecordVoList.add(parkingRecordVo);
//        }
//        Integer size = parkingRecordVoList.size();
//        Long count = size.longValue();
//        return new Response().success(new PageImpl<>(parkingRecordVoList, pageable, count));
//    }
}
