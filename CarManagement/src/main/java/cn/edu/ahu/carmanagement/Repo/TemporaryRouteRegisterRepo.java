package cn.edu.ahu.carmanagement.Repo;

import cn.edu.ahu.carmanagement.Domain.TemporaryRouteRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemporaryRouteRegisterRepo extends JpaRepository<TemporaryRouteRegister,Integer> {

    List<TemporaryRouteRegister> findByPlateNum(String plateNum);

    List<TemporaryRouteRegister> findByState(String state);

    TemporaryRouteRegister findByPlateNumAndState(String plateNum,String state);

    void deleteByPlateNum(String plateNum);
}
