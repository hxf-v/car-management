package cn.edu.ahu.carmanagement.Dao;

import cn.edu.ahu.carmanagement.Domain.SchoolCarRegister;
import cn.edu.ahu.carmanagement.Domain.WholeRouteRecord;
import cn.edu.ahu.carmanagement.Json.ChartResponseJson;
import cn.edu.ahu.carmanagement.Model.WholeRouteRecordModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: FZC
 * @UpdateBy: Alex
 * @Despriction: 车辆校内全程行驶记录查询相关接口
 * @Date: Created in 16:48 2019/4/22
 */
@Repository
public class WholeRouteRecordDao {

    @PersistenceContext
    private EntityManager em;

    //校内车辆确定开始和截至时间查询车辆全程行驶记录历史和校内注册车辆的相关信息
    public List<WholeRouteRecordModel> findByPlateNumBetweenStartAndEndTime(String plateNum,Date startTime,Date endTime){

        String dataSql = "select wrr,car " +
                " from WholeRouteRecord wrr " +
                " left join SchoolCarRegister car on car.plateNum = wrr.plateNum " +
                " where wrr.plateNum = :plateNum " +
                " and (wrr.settlementTime between :startTime and :endTime) ";

        List<Object[]> list = em.createQuery(dataSql,Object[].class)
                .setParameter("plateNum",plateNum)
                .setParameter("startTime",startTime)
                .setParameter("endTime",endTime)
                .getResultList();

        List<WholeRouteRecordModel> wholeRouteRecordModelList = new ArrayList<>(list.size());

        list.forEach(e-> wholeRouteRecordModelList.add(convert(e)));

        return wholeRouteRecordModelList;
    }

    private WholeRouteRecordModel convert(Object[] e){
        return new WholeRouteRecordModel(
                (WholeRouteRecord) e[0],
                (SchoolCarRegister) e[1]
        );
    }

    //输入开始何截至时间（前端有默认）（按照结算时间来处理），车牌模糊查询
    public List<WholeRouteRecord> findLikePlateNumBetweenStartAndEndTime(String plateNum,Date startTime,Date endTime,String state){

        String dataSql = "select wrr " +
                " from WholeRouteRecord wrr " +
                " where wrr.plateNum like :plateNum " +
                " and (wrr.settlementTime between :startTime and :endTime) " +
                " and wrr.state <> :state " +
                "order by wrr.id desc ";

        List<WholeRouteRecord> list = em.createQuery(dataSql,WholeRouteRecord.class)
                .setParameter("plateNum","%" + plateNum +"%")
                .setParameter("state",state)
                .setParameter("startTime",startTime)
                .setParameter("endTime",endTime)
                .getResultList();
        return list;
    }

    //车辆分析-确定起止时间查询历史全程行驶记录的方法(已离校)
    public List<ChartResponseJson> groupByPlateNumBetweenStartAndEndTime(Date startTime, Date endTime,String state){

        String dataSql = "select wrr.plateNum,count(wrr) " +
                " from WholeRouteRecord wrr " +
                " where wrr.state = :state" +
                " and (wrr.settlementTime between :startTime and :endTime) " +
                " group by wrr.plateNum " +
                " order by count(wrr) desc";

        List<Object[]> list = em.createQuery(dataSql,Object[].class)
                .setParameter("startTime",startTime)
                .setParameter("endTime",endTime)
                .setParameter("state",state)
                .getResultList();

        List<ChartResponseJson> chartResponseJsonList = new ArrayList<>(list.size());

        list.forEach(e-> chartResponseJsonList.add(convertChart(e)));

        return chartResponseJsonList;
    }

    private ChartResponseJson convertChart(Object[] e){
        return new ChartResponseJson(
                e[0],
                e[1]
        );
    }
}