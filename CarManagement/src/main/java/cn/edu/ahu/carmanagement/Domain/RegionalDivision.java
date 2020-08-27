package cn.edu.ahu.carmanagement.Domain;

import javax.persistence.*;

/**
 * @Author: FZC
 * @Despriction: 校内区域划分实体
 * @Date:Created in 17:33 2019/7/16
 */
@Entity
@Table(name = "regionaldivision")
public class RegionalDivision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;                     //主键id

    @Column(name = "name")
    private String name;                    //名称

    @Column(name = "department")
    private String department;              //所属部门

    @Column(name = "area_id")
    private String areaId;                  //区域id（String类型）

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

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
}
