package cn.edu.ahu.carmanagement.Dao.Model;

import cn.edu.ahu.carmanagement.Domain.MonitorEquip;
import cn.edu.ahu.carmanagement.Domain.TemporaryRouteUnRegister;

public class TemporaryRouteUnRegisterModel {

    private TemporaryRouteUnRegister temporaryRouteRegister;

    private MonitorEquip monitorEquip;

    public TemporaryRouteUnRegisterModel(TemporaryRouteUnRegister temporaryRouteRegister, MonitorEquip monitorEquip) {
        this.temporaryRouteRegister = temporaryRouteRegister;
        this.monitorEquip = monitorEquip;
    }

    public TemporaryRouteUnRegister getTemporaryRouteRegister() {
        return temporaryRouteRegister;
    }

    public void setTemporaryRouteRegister(TemporaryRouteUnRegister temporaryRouteRegister) {
        this.temporaryRouteRegister = temporaryRouteRegister;
    }

    public MonitorEquip getMonitorEquip() {
        return monitorEquip;
    }

    public void setMonitorEquip(MonitorEquip monitorEquip) {
        this.monitorEquip = monitorEquip;
    }
}
