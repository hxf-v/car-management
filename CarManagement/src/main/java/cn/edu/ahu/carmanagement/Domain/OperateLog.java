package cn.edu.ahu.carmanagement.Domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: OperateLog
 * @Description: 操作日志实体
 * @Author: FZC
 * @Date: 2019/1/14 13:42
 */
@Entity
@Table(name = "operatelog")
public class OperateLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;                 //主键id

    @Column(name = "operate_type")
    private String operateType;         //操作类型

    @Column(name = "operator")
    private String operator;            //操作人员

    @Column(name = "object_of_operation")
    private String objectOfOperation;   //操作对象

    @Column(name = "operate_detail")
    private String operateDetail;       //操作细节

    @Column(name = "time")
    private Date time;                  //操作时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getObjectOfOperation() {
        return objectOfOperation;
    }

    public void setObjectOfOperation(String objectOfOperation) {
        this.objectOfOperation = objectOfOperation;
    }

    public String getOperateDetail() {
        return operateDetail;
    }

    public void setOperateDetail(String operateDetail) {
        this.operateDetail = operateDetail;
    }
}
