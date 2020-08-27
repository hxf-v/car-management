package cn.edu.ahu.carmanagement.Dao;

import cn.edu.ahu.carmanagement.Domain.CarNumberGet;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @Author: Alex
 * @Description:  车牌识别
 * @Date: Created in 22:47 2020/8/11
 */
@Repository
public class CarNumberGetDao {
    @PersistenceContext
    private EntityManager em;

    public List<CarNumberGet> findAll(){
        String dataSql="select cng from CarNumberGet cng";

        List<CarNumberGet> list=em.createQuery(dataSql,CarNumberGet.class).getResultList();
        return list;
    }
}

