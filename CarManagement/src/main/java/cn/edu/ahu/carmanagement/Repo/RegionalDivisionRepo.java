package cn.edu.ahu.carmanagement.Repo;

import cn.edu.ahu.carmanagement.Domain.RegionalDivision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Author: FZC
 * @Despriction: 区域话分查询接口
 * @Date:Created in 17:47 2019/7/16
 */
public interface RegionalDivisionRepo extends JpaRepository<RegionalDivision,Integer> {

}
