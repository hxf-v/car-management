package cn.edu.ahu.carmanagement.Dao;

import cn.edu.ahu.carmanagement.Dao.Model.TemporaryRouteRegisterModel;
import cn.edu.ahu.carmanagement.Domain.MonitorEquip;
import cn.edu.ahu.carmanagement.Domain.SchoolCarRegister;
import cn.edu.ahu.carmanagement.Domain.TemporaryRouteRegister;
import cn.edu.ahu.carmanagement.Domain.UserInfo;
import cn.edu.ahu.carmanagement.Json.ChartResponseJson;
import cn.edu.ahu.carmanagement.Json.ChartResponseJson2;
import cn.edu.ahu.carmanagement.Json.ChartResponseJson3;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: FZC
 * @Despriction: 校内车辆临时行驶记录查询接口
 * @Date:Created in 15:33 2019/4/25
 */
@Repository
public class TemporaryRouteRegisterDao {

    @PersistenceContext
    private EntityManager em;

    //查询临时表中所有入校记录，按照时间排序
    public List<TemporaryRouteRegister> findCarByState(String state){

        String dataSql = "select trr " +
                " from TemporaryRouteRegister as trr " +
                " where trr.state = :state " +
                " order by trr.entryTime desc ";

                List<TemporaryRouteRegister> list = em.createQuery(dataSql,TemporaryRouteRegister.class)
                .setParameter("state",state)
                .getResultList();

                return list;
    }

    //按照车牌查询该车的所有临时行驶记录
    public List<TemporaryRouteRegisterModel> findByPlateNum(String plateNum){

        String dataSql = "select trr,car,mop " +
                " from TemporaryRouteRegister  trr " +
                " left join SchoolCarRegister car on trr.plateNum = car.plateNum " +
                " left join MonitorEquip mop on trr.equipId = mop.id" +
                " where trr.plateNum = :plateNum " +
                " order by trr.shootingTime";

        List<Object[]> list = em.createQuery(dataSql,Object[].class)
                .setParameter("plateNum",plateNum)
                .getResultList();

        List<TemporaryRouteRegisterModel> temporaryRouteRegisterModelList = new ArrayList<>(list.size());

        list.forEach(e -> temporaryRouteRegisterModelList.add(convert(e)));

        return temporaryRouteRegisterModelList;
    }
    public List<TemporaryRouteRegister> findByPlateNum2(String plateNum){

        String dataSql = "select trr " +
                " from TemporaryRouteRegister as trr " +
                " where trr.plateNum = :plateNum " +
                " order by trr.shootingTime";

        List<TemporaryRouteRegister> list = em.createQuery(dataSql,TemporaryRouteRegister.class)
                .setParameter("plateNum",plateNum)
                .getResultList();
        return list;
    }

    private TemporaryRouteRegisterModel convert(Object[] e) {
        return new TemporaryRouteRegisterModel(
                (TemporaryRouteRegister) e[0],
                (SchoolCarRegister) e[1],
                (MonitorEquip) e[2]
        );
    }

    //搜索车牌号，GroupBy摄像头id统计id下的行驶记录数量（校内车辆）
    public List<ChartResponseJson2> findByPlateNumGroupByCountTrr(String plateNum){

        String dataSql = "select me.id,me.position,trr.plateNum,count(trr) " +
                " from TemporaryRouteRegister trr " +
                " left join MonitorEquip me on trr.equipId = me.id " +
                " where trr.plateNum = :plateNum" +
                " group by me.id,me.position,trr.plateNum " +
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

        String dataSql = "select me.id,me.position,count(trr) " +
                " from TemporaryRouteRegister trr " +
                " left join MonitorEquip me on trr.equipId = me.id " +
                " group by me.id,me.position " +
                " order by count(trr) desc";

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
    public List<TemporaryRouteRegisterModel> findRecentlyOne(String state){

        String dataSql = "select trr,car,me" +
                " from TemporaryRouteRegister trr " +
                " left join SchoolCarRegister car on trr.plateNum = car.plateNum " +
                " left join MonitorEquip me on trr.equipId = me.id " +
                " where trr.state = :state " +
                " order by trr.shootingTime desc ";

        List<Object[]> list = em.createQuery(dataSql,Object[].class)
                .setParameter("state",state)
                .getResultList();

        List<TemporaryRouteRegisterModel> temporaryRouteRegisterModelList = new ArrayList<>(list.size());

        list.forEach(e -> temporaryRouteRegisterModelList.add(convert(e)));

        return temporaryRouteRegisterModelList;
    }

    //按照车牌查询equipId作为行驶记录节点
    public List<Integer> findEquipIdByPlateNumOrderByShootingTime(String plateNum){

        String dataSql = "select trr.equipId " +
                " from TemporaryRouteRegister  trr " +
                " where trr.plateNum = :plateNum " +
                " order by trr.shootingTime ";

        List<Integer> list = em.createQuery(dataSql,Integer.class)
                .setParameter("plateNum",plateNum)
                .getResultList();

        return list;
    }

//    //按照车牌查询该车的所有临时行驶记录
//    public List<TemporaryRouteRegister> findByPlateNum(String plateNum){
//
//        String dataSql = "select trr,car " +
//                " from TemporaryRouteRegister  trr " +
//                " left join SchoolCarRegister car on trr.plateNum = car.plateNum " +
//                " where trr.plateNum = :plateNum ";
//
//        List<TemporaryRouteRegister> list = em.createQuery(dataSql,TemporaryRouteRegister.class)
//                .setParameter("plateNum",plateNum)
//                .getResultList();
//
//        return list;
//    }
}
