package cn.edu.ahu.carmanagement.Repo;

import cn.edu.ahu.carmanagement.Domain.OperateLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: OperateLogRepo
 * @Description: 操作日志接口
 * @Author: FZC
 * @Date: 2019/2/26 11:02
 */
public interface OperateLogRepo extends JpaRepository<OperateLog, Integer> {

}
