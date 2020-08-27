package cn.edu.ahu.carmanagement.Dao;
import cn.edu.ahu.carmanagement.Domain.DrivingRouteRecord;
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
 * @Despriction: 车辆停放引导
 * @Date:Created in 15:40 2019/12/19
 */
@Repository
public class ParkingGuidanceDao {
    @PersistenceContext
    private EntityManager em;
    public int[] getShortestPaths(int[][] nodeMatrix) {
        int[] pathResult = new int[nodeMatrix.length];   //用于存放起始节点O到其它节点的最短距离
        boolean[] used = new boolean[nodeMatrix.length];  //用于判断节点是否被遍历
        used[0] = true;  //表示起始节点O已被遍历
        for(int i = 1;i < nodeMatrix.length;i++) {
            pathResult[i] = nodeMatrix[0][i];
            used[i] = false;
        }
        for(int i = 1;i < nodeMatrix.length;i++) {
            int min = Integer.MAX_VALUE;    //用于暂时存放起始节点O到i的最短距离，初始化为Integer.MAX_VALUE
            int k = 0;
            for(int j = 1;j < nodeMatrix.length;j++) {  //找到起始节点O到其它节点中距离最小的一个节点
                if(!used[j] && pathResult[j] != -1 && min > pathResult[j]) {
                    min = pathResult[j];
                    k = j;}
            }
            used[k] = true;    //将距离最小的顶点，记为已遍历
            for(int j = 1;j < nodeMatrix.length;j++) {
                //将起始节点O到其它节点的距离与加入中间节点k后的距离进行比较，更新最短距离
                if(!used[j]) {  //当节点j未被遍历时
                    //首先，节点k到节点j要能通行；
                    // 当起始节点O到节点j的距离大于起始节点O到k再到j的距离或起始节点O无法直接到达节点j时，更新起始节点O到节点j的最短距离
                    if(nodeMatrix[k][j] != -1 && (pathResult[j] > min + nodeMatrix[k][j] || pathResult[j] == -1))
                        pathResult[j] = min + nodeMatrix[k][j];}
            }
        }
        return pathResult;
    }
    //通过车牌号查询当前在校单个车辆的行驶路线信息（绘制轨迹图），按照时间正序排列
    public Page<DrivingRouteRecord> findByPlateNumAndState(Pageable pageable, String plateNum, String state){

        String dataSql = "select drivingRoute" +
                " from DrivingRouteRecord as drivingRoute" +
                " where drivingRoute.plateNum = :plateNum and drivingRoute.state = :state " +
                " order by drivingRoute.passingTime ";

        List<DrivingRouteRecord> drivingRouteRecordList = em.createQuery(dataSql,DrivingRouteRecord.class)
                .setParameter("plateNum",plateNum)
                .setParameter("state",state)
                .setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        String countSql = "select count(drivingRoute)" +
                " from DrivingRouteRecord as drivingRoute" +
                " where drivingRoute.plateNum = :plateNum and drivingRoute.state = :state ";

        Long count = em.createQuery(countSql,Long.class)
                .setParameter("plateNum",plateNum)
                .setParameter("state",state)
                .getSingleResult();

        //时间格式转换
        drivingRouteRecordList.forEach(DrivingRouteRecord ->{
            if (DrivingRouteRecord.getPassingTime() != null) {
                DrivingRouteRecord.setPassingTimeStr(DateStringTranUtil.dateToString(DrivingRouteRecord.getPassingTime()));
            }
        });
        return new PageImpl<>(drivingRouteRecordList, pageable, count);
    }
}
