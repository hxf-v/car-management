package cn.edu.ahu.carmanagement.Repo;

import cn.edu.ahu.carmanagement.Domain.AhuStudent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: FZC
 * @Despriction:
 * @Date:Created in 16:27 2019/7/15
 */
public interface AhuStudentRepo extends JpaRepository<AhuStudent,Integer> {

    AhuStudent findByIdAndSfzh(String id,String sfzh);
}
