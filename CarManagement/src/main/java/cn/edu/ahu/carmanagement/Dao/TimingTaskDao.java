package cn.edu.ahu.carmanagement.Dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @Author: FZC
 * @Despriction: 定时任务接口
 * @Date:Created in 15:33 2019/4/25
 */
@Repository
public class TimingTaskDao {

    @PersistenceContext
    private EntityManager em;
}
