package cn.edu.ahu.carmanagement.Repo;

import cn.edu.ahu.carmanagement.Domain.ParkingRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @Author: FZC
 * @Despriction:
 * @Date:Created in 15:27 2019/4/25
 */
public interface ParkingRecordRepo extends JpaRepository<ParkingRecord,Integer> {

    List<ParkingRecord> findByPlateNum(Pageable pageable, String plateNum);

    ParkingRecord findByPlateNumAndState(String plateNum, String state);

    List<ParkingRecord> findByState(Pageable pageable, String state);
}
