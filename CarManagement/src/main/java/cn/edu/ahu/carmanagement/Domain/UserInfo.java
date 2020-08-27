package cn.edu.ahu.carmanagement.Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: AhuStudent
 * @Description: 用户实体
 * @Author: FZC
 * @Date: 2019/1/14 9:51
 */
@Entity
@Data
@Table(name = "userinfo")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;         //主键id，作为唯一ID

    @Column(name = "username")
    private String userName;    //用户名

    @Column(name = "account")
    private String account;     //账户（以手机号码作为account）

    @Column(name = "password")
    private String password;    //用户密码

    @Column(name = "plate_number")
    private String plateNum;    //车牌号码

    @Column(name = "time")
    private Date time;          //注册时间

    @Column(name = "state")
    private String state;       //状态

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
