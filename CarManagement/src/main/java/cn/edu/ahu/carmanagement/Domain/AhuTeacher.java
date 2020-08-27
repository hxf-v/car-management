package cn.edu.ahu.carmanagement.Domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName: AhuTeacher
 * @Description: 老师实体
 * @Author: FZC
 * @Date: 2019/1/14 9:51
 */
@Entity
@Data
@Table(name = "ahu_teacher")
public class AhuTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "xgh")       //学工号，作为唯一ID
    private String id;

    @Column(name = "xm")        //姓名
    private String xm;

    @Column(name = "bm")        //部门
    private String bm;

    @Column(name = "xb")        //性别
    private String xb;

    @Column(name = "sfzh")      //身份证号
    private String sfzh;

    @Column(name = "state")     //状态
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
