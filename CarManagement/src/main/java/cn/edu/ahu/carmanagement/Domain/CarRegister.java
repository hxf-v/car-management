package cn.edu.ahu.carmanagement.Domain;

import javax.persistence.*;

/**
 * @Author: FZC
 * @Despriction: 登记在册车主实体
 * @Date:Created in 10:53 2019/4/22
 */
@Entity
@Table(name = "carregister")
public class CarRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;             //主键id

    @Column(name = "xgh")
    private String xgh;             //学工号

    @Column(name = "name")
    private String name;            //姓名

    @Column(name = "department")
    private String department;      //所属院系或部门

    @Column(name = "plate_num")
    private String plateNum;        //车牌号

    @Column(name = "brand")
    private String brand;           //品牌

    @Column(name = "color")
    private String color;           //颜色

    @Column(name = "phone_num")
    private String phoneNum;        //电话号码

    @Column(name = "state")
    private String state;           //状态

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getXgh() {
        return xgh;
    }

    public void setXgh(String xgh) {
        this.xgh = xgh;
    }
}
