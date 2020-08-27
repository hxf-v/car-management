package cn.edu.ahu.carmanagement.Repo;

import cn.edu.ahu.carmanagement.Domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepo extends JpaRepository<UserInfo,Integer> {

    UserInfo findByAccountAndPassword(String account,String password);

    UserInfo findByAccount(String account);
}
