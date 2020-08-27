package cn.edu.ahu.carmanagement.Model;

import cn.edu.ahu.carmanagement.Domain.SchoolCarRegister;
import cn.edu.ahu.carmanagement.Domain.WholeRouteRecord;

public class WholeRouteRecordModel {

    private WholeRouteRecord wholeRouteRecord;
    private SchoolCarRegister schoolCarRegister;

    public WholeRouteRecordModel(WholeRouteRecord wholeRouteRecord, SchoolCarRegister schoolCarRegister) {
        this.wholeRouteRecord = wholeRouteRecord;
        this.schoolCarRegister = schoolCarRegister;
    }

    public WholeRouteRecord getWholeRouteRecord() {
        return wholeRouteRecord;
    }

    public void setWholeRouteRecord(WholeRouteRecord wholeRouteRecord) {
        this.wholeRouteRecord = wholeRouteRecord;
    }

    public SchoolCarRegister getSchoolCarRegister() {
        return schoolCarRegister;
    }

    public void setSchoolCarRegister(SchoolCarRegister schoolCarRegister) {
        this.schoolCarRegister = schoolCarRegister;
    }
}
