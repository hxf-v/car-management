package cn.edu.ahu.carmanagement.Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: SchoolCarRegister
 * @Description: 注册车辆实体
 * @Author: FZC
 * @Date: 2019/1/14 9:51
 */
@Entity
@Data
@Table(name = "schoolcarregister")
public class SchoolCarRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;         //主键id，作为唯一ID

    @Column(name = "name")
    private String name;        //车主姓名

    @Column(name = "id_num")
    private String idNum;       //身份证密码

    @Column(name = "department")
    private String department;  //院系或部门

    @Column(name = "phone")
    private Integer phone;      //电话号码

    @Column(name = "mailbox")
    private String mailbox;     //电子邮箱

    @Column(name = "plate_number")
    private String plateNum;    //车牌号码

    @Column(name = "time")
    private Date time;          //注册时间

    @Column(name = "state")
    private String state;       //车辆状态

    @Column(name = "imgs")
    private String imgs;       //车辆状态

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

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
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

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }
}
