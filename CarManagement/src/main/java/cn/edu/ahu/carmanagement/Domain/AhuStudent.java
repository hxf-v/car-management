package cn.edu.ahu.carmanagement.Domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName: AhuStudent
 * @Description: 学生实体
 * @Author: FZC
 * @Date: 2019/1/14 9:51
 */
@Entity
@Data
@Table(name = "ahu_student")
public class AhuStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "xgh")
    private String id;          //学工号，作为唯一ID

    @Column(name = "xm")
    private String xm;          //姓名

    @Column(name = "yx")
    private String yx;          //院系

    @Column(name = "nj")
    private String nj;          //年级

    @Column(name = "xb")
    private String xb;          //性别

    @Column(name = "sfzh")
    private String sfzh;        //身份证号

    @Column(name = "byzt")
    private String byzt;        //毕业状态

    @Column(name = "state")
    private String state;       //状态

    @Column(name = "type")
    private String type;        //本科/研究生类型

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

    public String getYx() {
        return yx;
    }

    public void setYx(String yx) {
        this.yx = yx;
    }

    public String getNj() {
        return nj;
    }

    public void setNj(String nj) {
        this.nj = nj;
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

    public String getByzt() {
        return byzt;
    }

    public void setByzt(String byzt) {
        this.byzt = byzt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
