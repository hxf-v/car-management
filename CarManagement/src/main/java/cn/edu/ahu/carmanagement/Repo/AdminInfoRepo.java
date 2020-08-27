package cn.edu.ahu.carmanagement.Repo;

import cn.edu.ahu.carmanagement.Domain.AdminInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminInfoRepo extends JpaRepository<AdminInfo,Integer> {

    AdminInfo findByJobNumAndPassword(String jobNum,String password);
}
