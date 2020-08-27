package cn.edu.ahu.carmanagement.Json;

/**
 * @Author: FZC
 * @Despriction:
 * @Date: Created in 16:48 2019/4/22
 */
public class ChartResponseJson2 {
    private Object id;
    private Object name;
    private Object plateNum;
    private Object value;

    public ChartResponseJson2(Object id, Object name, Object plateNum, Object value) {
        this.id = id;
        this.name = name;
        this.plateNum = plateNum;
        this.value = value;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(Object plateNum) {
        this.plateNum = plateNum;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
