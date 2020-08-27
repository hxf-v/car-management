package cn.edu.ahu.carmanagement.Json;

/**
 * @ClassName: AdminRequestJson
 * @Description:
 * @Author: FZC
 * @Date: 2019/1/18 9:15
 */
public class AdminRequestJson {

    private Integer id;         //主键id，作为唯一ID
    private String name;        //姓名
    private String jobNum;      //管理员工号
    private String password;    //管理员密码
    private String idNum;       //身份证密码
    private String department;  //院系或部门
    private Integer phone;      //电话号码
    private String authority;   //权限类型
    private String objJobNum;   //操作对象工号
    private String plateNum;   //操作对象工号

    private String operator;        //超级管理员工号（日志用）
    private String operateType;     //操作类型（日志用）
    private String nowTimeStr;      //当前时间（日志用）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getNowTimeStr() {
        return nowTimeStr;
    }

    public void setNowTimeStr(String nowTimeStr) {
        this.nowTimeStr = nowTimeStr;
    }

    public String getObjJobNum() {
        return objJobNum;
    }

    public void setObjJobNum(String objJobNum) {
        this.objJobNum = objJobNum;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }
}
