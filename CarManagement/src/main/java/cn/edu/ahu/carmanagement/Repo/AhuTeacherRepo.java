package cn.edu.ahu.carmanagement.Repo;

import cn.edu.ahu.carmanagement.Domain.AhuTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: FZC
 * @Despriction:
 * @Date:Created in 16:28 2019/7/15
 */
public interface AhuTeacherRepo extends JpaRepository<AhuTeacher,Integer> {

    AhuTeacher findByIdAndSfzh(String id,String sfzh);
}
