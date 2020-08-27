package cn.edu.ahu.carmanagement.Dao.Model;

import cn.edu.ahu.carmanagement.Domain.MonitorEquip;
import cn.edu.ahu.carmanagement.Domain.SchoolCarRegister;
import cn.edu.ahu.carmanagement.Domain.TemporaryRouteRegister;

public class TemporaryRouteRegisterModel {

    private TemporaryRouteRegister temporaryRouteRegister;

    private SchoolCarRegister schoolCarRegister;

    private MonitorEquip monitorEquip;

    public TemporaryRouteRegisterModel(TemporaryRouteRegister temporaryRouteRegister, SchoolCarRegister schoolCarRegister, MonitorEquip monitorEquip) {
        this.temporaryRouteRegister = temporaryRouteRegister;
        this.schoolCarRegister = schoolCarRegister;
        this.monitorEquip = monitorEquip;
    }

    public TemporaryRouteRegister getTemporaryRouteRegister() {
        return temporaryRouteRegister;
    }

    public void setTemporaryRouteRegister(TemporaryRouteRegister temporaryRouteRegister) {
        this.temporaryRouteRegister = temporaryRouteRegister;
    }

    public SchoolCarRegister getSchoolCarRegister() {
        return schoolCarRegister;
    }

    public void setSchoolCarRegister(SchoolCarRegister schoolCarRegister) {
        this.schoolCarRegister = schoolCarRegister;
    }

    public MonitorEquip getMonitorEquip() {
        return monitorEquip;
    }

    public void setMonitorEquip(MonitorEquip monitorEquip) {
        this.monitorEquip = monitorEquip;
    }
}
