package cn.edu.ahu.carmanagement.Json;

/**
 * 前台chart图所需Json
 */
public class ChartResponseJson {
    private Object name;
    private Object value;

    public ChartResponseJson(Object name, Object value) {
        this.name = name;
        this.value = value;
    }


    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
