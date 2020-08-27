package cn.edu.ahu.carmanagement.Dao;

import cn.edu.ahu.carmanagement.Dao.Model.TemporaryRouteRegisterModel;
import cn.edu.ahu.carmanagement.Dao.Model.TemporaryRouteUnRegisterModel;
import cn.edu.ahu.carmanagement.Domain.MonitorEquip;
import cn.edu.ahu.carmanagement.Domain.SchoolCarRegister;
import cn.edu.ahu.carmanagement.Domain.TemporaryRouteRegister;
import cn.edu.ahu.carmanagement.Domain.TemporaryRouteUnRegister;
import cn.edu.ahu.carmanagement.Json.ChartResponseJson2;
import cn.edu.ahu.carmanagement.Json.ChartResponseJson3;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: FZC
 * @Despriction: 校内车辆临时行驶记录查询接口
 * @Date:Created in 15:33 2019/4/25
 */
@Repository
public class TemporaryRouteUnRegisterDao {

    @PersistenceContext
    private EntityManager em;

    //查询临时表中所有入校记录，按照时间排序
    public List<TemporaryRouteUnRegister> findCarByState(String state){

        String dataSql = "select trr " +
                " from TemporaryRouteUnRegister as trr " +
                " where trr.state = :state " +
                " order by trr.entryTime desc ";

        List<TemporaryRouteUnRegister> list = em.createQuery(dataSql,TemporaryRouteUnRegister.class)
                .setParameter("state",state)
                .getResultList();

        return list;
    }

    //按照车牌查询该车的所有临时行驶记录
    public List<TemporaryRouteUnRegisterModel> findByPlateNum(String plateNum){

        String dataSql = "select trr,mop " +
                " from TemporaryRouteUnRegister  trr " +
                " left join MonitorEquip mop on trr.equipId = mop.id" +
                " where trr.plateNum = :plateNum " +
                " order by trr.shootingTime ";

        List<Object[]> list = em.createQuery(dataSql,Object[].class)
                .setParameter("plateNum",plateNum)
                .getResultList();

        List<TemporaryRouteUnRegisterModel> temporaryRouteUnRegisterModelList = new ArrayList<>(list.size());

        list.forEach(e -> temporaryRouteUnRegisterModelList.add(convert(e)));

        return temporaryRouteUnRegisterModelList;
    }
    public List<TemporaryRouteUnRegister> findByPlateNum2(String plateNum){

        String dataSql = "select trr " +
                " from TemporaryRouteUnRegister as trr " +
                " where trr.plateNum = :plateNum " +
                " order by trr.shootingTime";

        List<TemporaryRouteUnRegister> list = em.createQuery(dataSql,TemporaryRouteUnRegister.class)
                .setParameter("plateNum",plateNum)
                .getResultList();
        return list;
    }

    private TemporaryRouteUnRegisterModel convert(Object[] e) {
        return new TemporaryRouteUnRegisterModel(
                (TemporaryRouteUnRegister) e[0],
                (MonitorEquip) e[1]
        );
    }

    //搜索车牌号，GroupBy摄像头id统计id下的行驶记录数量（校外车辆）
    public List<ChartResponseJson2> findByPlateNumGroupByCountTrr(String plateNum){

        String dataSql = "select me.id,me.position,trr.plateNum,count(trr) " +
                " from TemporaryRouteUnRegister trr " +
                " left join MonitorEquip me on trr.equipId = me.id " +
                " where trr.plateNum = :plateNum" +
                " group by me.id,me.position,trr.plateNum  " +
                " order by count(trr) desc";

        List<Object[]> list = em.createQuery(dataSql,Object[].class)
                .setParameter("plateNum",plateNum)
                .getResultList();

        List<ChartResponseJson2> chartResponseJson2List = new ArrayList<>(list.size());

        list.forEach(e-> chartResponseJson2List.add(convertChart2(e)));

        return chartResponseJson2List;
    }

    private ChartResponseJson2 convertChart2(Object[] e){
        return new ChartResponseJson2(
                e[0],
                e[1],
                e[2],
                e[3]
        );
    }


    //GroupBy摄像头id统计id下的行驶记录数量
    public List<ChartResponseJson3> groupByCountTrr(){

        String dataSql = "select me.id,me.position,count(trur) " +
                " from TemporaryRouteUnRegister trur " +
                " left join MonitorEquip me on trur.equipId = me.id " +
                " group by me.id,me.position " +
                " order by count(trur) desc";

        List<Object[]> list = em.createQuery(dataSql,Object[].class)
                .getResultList();

        List<ChartResponseJson3> chartResponseJson3List = new ArrayList<>(list.size());

        list.forEach(e-> chartResponseJson3List.add(convertChart3(e)));

        return chartResponseJson3List;
    }

    private ChartResponseJson3 convertChart3(Object[] e){
        return new ChartResponseJson3(
                e[0],
                e[1],
                e[2]
        );
    }

    //获取最近一个入校车辆的临时行驶记录-校内车辆
    public List<TemporaryRouteUnRegisterModel> findRecentlyOne(String state){

        String dataSql = "select trr,me" +
                " from TemporaryRouteRegister trr " +
                " left join MonitorEquip me on trr.equipId = me.id " +
                " where trr.state = :state " +
                " order by trr.shootingTime desc ";

        List<Object[]> list = em.createQuery(dataSql,Object[].class)
                .setParameter("state",state)
                .getResultList();

        List<TemporaryRouteUnRegisterModel> temporaryRouteUnRegisterModelList = new ArrayList<>(list.size());

        list.forEach(e -> temporaryRouteUnRegisterModelList.add(convert(e)));

        return temporaryRouteUnRegisterModelList;
    }

    //按照车牌查询equipId作为行驶记录节点
    public List<Integer> findEquipIdByPlateNumOrderByShootingTime(String plateNum){

        String dataSql = "select trr.equipId " +
                " from TemporaryRouteUnRegister trr " +
                " where trr.plateNum = :plateNum " +
                " order by trr.shootingTime ";

        List<Integer> list = em.createQuery(dataSql,Integer.class)
                .setParameter("plateNum",plateNum)
                .getResultList();

        return list;
    }
}
