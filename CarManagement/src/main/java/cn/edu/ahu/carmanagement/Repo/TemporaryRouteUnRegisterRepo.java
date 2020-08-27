package cn.edu.ahu.carmanagement.Repo;

import cn.edu.ahu.carmanagement.Domain.TemporaryRouteUnRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemporaryRouteUnRegisterRepo extends JpaRepository<TemporaryRouteUnRegister,Integer> {

    List<TemporaryRouteUnRegister> findByPlateNum(String plateNum);

    List<TemporaryRouteUnRegister> findByState(String state);

    TemporaryRouteUnRegister findByPlateNumAndState(String plateNum,String state);

    void deleteByPlateNum(String plateNum);
}
