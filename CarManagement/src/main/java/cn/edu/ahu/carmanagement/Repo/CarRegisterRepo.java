package cn.edu.ahu.carmanagement.Repo;

import cn.edu.ahu.carmanagement.Domain.CarRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: FZC
 * @Despriction: 已注册车辆接口
 * @Date: Created in 16:48 2019/4/22
 */
public interface CarRegisterRepo extends JpaRepository<CarRegister,Integer> {

    Page<CarRegister> findByState(Pageable pageable,String state);

    Page<CarRegister>findByDepartmentAndState(Pageable pageable,String department,String state);

    CarRegister findByPlateNumAndState(String plateNum,String state);

    CarRegister findByPlateNum(String plateNum);
}
