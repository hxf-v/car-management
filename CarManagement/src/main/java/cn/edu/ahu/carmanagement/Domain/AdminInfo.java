package cn.edu.ahu.carmanagement.Domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName: AhuStudent
 * @Description: 管理员实体
 * @Author: FZC
 * @Date: 2019/1/14 9:51
 */
@Entity
@Data
@Table(name = "admininfo")
public class AdminInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;         //主键id，作为唯一ID

    @Column(name = "name")
    private String name;        //姓名

    @Column(name = "job_number")
    private String jobNum;      //管理员工号

    @Column(name = "password")
    private String password;    //管理员密码

    @Column(name = "id_num")
    private String idNum;       //身份证密码

    @Column(name = "department")
    private String department;  //院系或部门

    @Column(name = "phone")
    private Integer phone;      //电话号码

    @Column(name = "authority")
    private String authority;   //权限类型

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
}
