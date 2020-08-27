package cn.edu.ahu.carmanagement.Service.Impl;

import cn.edu.ahu.carmanagement.Domain.CarNumberGet;
import cn.edu.ahu.carmanagement.Domain.CarRegister;
import cn.edu.ahu.carmanagement.Domain.SchoolStatusRecord;
import cn.edu.ahu.carmanagement.Repo.CarNumberGetRepo;
import cn.edu.ahu.carmanagement.Repo.CarRegisterRepo;
import cn.edu.ahu.carmanagement.Repo.SchoolStatusRecordRepo;
import cn.edu.ahu.carmanagement.Service.TimingTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: FZC
 * @Despriction:
 * @Date:Created in 15:01 2019/7/11
 */
@Service
public class TimingTaskServiceImpl implements TimingTaskService {

    @Autowired
    private CarNumberGetRepo carNumberGetRepo;
    @Autowired
    private CarRegisterRepo carRegisterRepo;
    @Autowired
    private SchoolStatusRecordRepo schoolStatusRecordRepo;

    /**
     * 车牌比对写入 SchoolStatusRecord表（进校时）
     */
    @Override
    public void WriteInSchoolStatusRecord() {
        List<CarNumberGet> carNumberGetList = carNumberGetRepo.findByState("待处理");
        Integer length = carNumberGetList.size();

        for (Integer i = 0; i < length; i++) {
            CarNumberGet carNumberGet = carNumberGetList.get(i);
            CarRegister carRegister = carRegisterRepo.findByPlateNum(carNumberGet.getPlateNum());
            if (carRegister != null){
                //在册车辆
                SchoolStatusRecord schoolStatusRecord = new SchoolStatusRecord();
                schoolStatusRecord.setPlateNum(carRegister.getPlateNum());
                schoolStatusRecord.setRegister("是");
                schoolStatusRecord.setOwner(carRegister.getName());
                schoolStatusRecord.setBrand(carRegister.getBrand());
                schoolStatusRecord.setColor(carRegister.getColor());
                schoolStatusRecord.setDepartment(carRegister.getDepartment());
                schoolStatusRecord.setPhoneNum(carRegister.getPhoneNum());
                schoolStatusRecord.setInTime(carNumberGet.getTakeTime());
                schoolStatusRecord.setNormalAccounting("停车卡");
                schoolStatusRecord.setState("在校");
                schoolStatusRecordRepo.save(schoolStatusRecord);
            }else {
                //校外车辆
                SchoolStatusRecord schoolStatusRecord = new SchoolStatusRecord();
                schoolStatusRecord.setPlateNum(carNumberGet.getPlateNum());
                schoolStatusRecord.setRegister("否");
                schoolStatusRecord.setOwner("校外车辆");
                schoolStatusRecord.setBrand("校外车辆");
                schoolStatusRecord.setColor("校外车辆");
                schoolStatusRecord.setDepartment("校外车辆");
                schoolStatusRecord.setPhoneNum("校外车辆");
                schoolStatusRecord.setInTime(carNumberGet.getTakeTime());
                schoolStatusRecord.setState("在校");
                schoolStatusRecordRepo.save(schoolStatusRecord);
            }
            carNumberGet.setState("已处理");
            carNumberGetRepo.save(carNumberGet);
        }
    }
}
