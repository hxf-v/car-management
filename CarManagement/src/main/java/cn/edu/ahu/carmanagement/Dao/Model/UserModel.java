package cn.edu.ahu.carmanagement.Dao.Model;

import cn.edu.ahu.carmanagement.Domain.SchoolCarRegister;
import cn.edu.ahu.carmanagement.Domain.UserInfo;

public class UserModel {

    private UserInfo userInfo;
    private SchoolCarRegister schoolCarRegister;

    public UserModel(UserInfo userInfo, SchoolCarRegister schoolCarRegister) {
        this.userInfo = userInfo;
        this.schoolCarRegister = schoolCarRegister;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public SchoolCarRegister getSchoolCarRegister() {
        return schoolCarRegister;
    }

    public void setSchoolCarRegister(SchoolCarRegister schoolCarRegister) {
        this.schoolCarRegister = schoolCarRegister;
    }
}
