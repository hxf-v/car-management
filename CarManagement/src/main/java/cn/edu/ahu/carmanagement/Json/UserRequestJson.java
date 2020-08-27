package cn.edu.ahu.carmanagement.Json;

import cn.edu.ahu.carmanagement.Domain.UserInfo;
import cn.edu.ahu.carmanagement.Utils.DateStringTranUtil;

public class UserRequestJson {

    private Integer id;         //主键id，作为唯一ID
    private String userName;    //用户名
    private String account;     //账户
    private String password;    //用户密码
    private String plateNum;    //车牌号码
    private String timeStr;     //注册时间Str
    private String state;       //状态

    private String startTimeStr;
    private String endTimeStr;


    //添加用户
    public UserInfo convertUser(){
        UserInfo userInfo = new UserInfo();

        userInfo.setUserName(getUserName());
        userInfo.setAccount(getAccount());
        userInfo.setPassword(getPassword());
        userInfo.setPlateNum(getPlateNum());
        if (getTimeStr() != null){
            userInfo.setTime(DateStringTranUtil.stringToDate(getTimeStr()));
        }
        userInfo.setState("启用");
        return userInfo;
    }

    //普通用户修改密码
    public UserInfo convertUserPassword(UserInfo userInfo){

        userInfo.setPassword(getPassword());
        return userInfo;
    }

    //普通用户修改绑定车辆号牌
    public UserInfo convertUserPlateNum(UserInfo userInfo){

        userInfo.setPlateNum(getPlateNum());;
        return userInfo;
    }

    //编辑用户
    public UserInfo convertEdit(UserInfo userInfo){

        userInfo.setUserName(getUserName());
        userInfo.setAccount(getAccount());
        userInfo.setPassword(getPassword());
        userInfo.setPlateNum(getPlateNum());
        if (getTimeStr() != null){
            userInfo.setTime(DateStringTranUtil.stringToDate(getTimeStr()));
        }
        userInfo.setState(getState());
        return userInfo;
    }

    //删除用户
    public UserInfo convertDelete(UserInfo userInfo){

        userInfo.setState("已删除");
        return userInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }
}
