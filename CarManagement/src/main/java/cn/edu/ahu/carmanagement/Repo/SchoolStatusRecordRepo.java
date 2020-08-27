package cn.edu.ahu.carmanagement.Repo;

import cn.edu.ahu.carmanagement.Domain.SchoolStatusRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: FZC
 * @Despriction: 车辆在校状态信息接口
 * @Date: Created in 16:48 2019/4/22
 */
public interface SchoolStatusRecordRepo extends JpaRepository<SchoolStatusRecord,Integer> {

    List<SchoolStatusRecord> findByState(String state);

    SchoolStatusRecord findByPlateNumAndState(String plateNum, String state);
}
