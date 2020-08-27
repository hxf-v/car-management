package cn.edu.ahu.carmanagement.Service;

import cn.edu.ahu.carmanagement.Utils.Response;
import org.springframework.data.domain.Pageable;

public interface TemporaryRouteRegisterService {

    //最近入校的一辆汽车的所有行驶记录展示-校内车辆-包含注册车辆信息的连表查询
    Response findOneCarRecordHistory();
}
