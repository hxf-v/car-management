package cn.edu.ahu.carmanagement.Repo;

import cn.edu.ahu.carmanagement.Domain.CarNumberGet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: FZC
 * @UpdateBy: Alex
 * @Despriction:
 * @Date: Created in 16:48 2019/4/22
 */
public interface CarNumberGetRepo extends JpaRepository<CarNumberGet,Integer> {

    List<CarNumberGet> findByState(String state);

    void deleteAllByPlateNum(String plateNum);
}
