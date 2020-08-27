package cn.edu.ahu.carmanagement.Dao;

import cn.edu.ahu.carmanagement.Domain.SchoolStatusRecord;
import cn.edu.ahu.carmanagement.Utils.DateStringTranUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @Author: FZC
 * @Despriction:
 * @Date:Created in 21:31 2019/6/19
 */
@Repository
public class SchoolStatusRecordDao {
    @PersistenceContext
    private EntityManager em;

    //通过state查询SchoolStatusRecord表信息
    public Page<SchoolStatusRecord> findByState(Pageable pageable,String state){

        String dataSql = "select schoolStatus" +
                " from SchoolStatusRecord as schoolStatus" +
                " where schoolStatus.state = :state";

        List<SchoolStatusRecord> schoolStatusRecordList = em.createQuery(dataSql,SchoolStatusRecord.class)
                .setParameter("state",state)
                .setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        String countSql = "select count(schoolStatus)" +
                " from SchoolStatusRecord as schoolStatus" +
                " where schoolStatus.state = :state";

        Long count = em.createQuery(countSql,Long.class)
                .setParameter("state",state)
                .getSingleResult();

        //时间格式转换
        schoolStatusRecordList.forEach(SchoolStatusRecord -> {
            if (SchoolStatusRecord.getInTime() != null) {
                SchoolStatusRecord.setInTimeStr(DateStringTranUtil.dateToString(SchoolStatusRecord.getInTime()));
            }
            if (SchoolStatusRecord.getOutTime() != null) {
                SchoolStatusRecord.setOutTimeStr(DateStringTranUtil.dateToString(SchoolStatusRecord.getOutTime()));
            }
        });

        return new PageImpl<>(schoolStatusRecordList, pageable, count);
    }
}
