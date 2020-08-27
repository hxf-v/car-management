package cn.edu.ahu.carmanagement.Repo;

import cn.edu.ahu.carmanagement.Domain.SchoolCarRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolCarRegisterRepo extends JpaRepository<SchoolCarRegister,Integer> {

    SchoolCarRegister findByPlateNum(String plateNum);
}
