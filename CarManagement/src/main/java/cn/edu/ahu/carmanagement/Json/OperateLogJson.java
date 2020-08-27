package cn.edu.ahu.carmanagement.Json;

import cn.edu.ahu.carmanagement.Domain.OperateLog;
import cn.edu.ahu.carmanagement.Utils.DateStringTranUtil;

/**
 * @ClassName: OperateLogJson
 * @Description: 操作日志所需Json
 * @Author: FZC
 * @Date: 2019/2/26 10:54
 */
public class OperateLogJson {

    private Integer id;
    private String operateType;         //操作类型
    private String operator;            //操作人员
    private String objectOfOperation;   //操作对象
    private String timeStr;             //操作时间
    private String operateDetail;       //操作细节

    public OperateLog convert(){
        OperateLog operateLog = new OperateLog();

        operateLog.setOperateType(getOperateType());
        operateLog.setOperator(getOperator());
        operateLog.setObjectOfOperation(getObjectOfOperation());
        operateLog.setOperateDetail(getOperateDetail());

        if (getTimeStr() != null){
            operateLog.setTime(DateStringTranUtil.stringToDate(getTimeStr()));
        }

        return operateLog;
    }

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

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
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
