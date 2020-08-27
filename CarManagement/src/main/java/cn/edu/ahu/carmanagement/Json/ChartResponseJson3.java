package cn.edu.ahu.carmanagement.Json;

public class ChartResponseJson3 {
    private Object id;
    private Object name;
    private Object value;

    public ChartResponseJson3(Object id, Object name, Object value) {
        this.id = id;
        this.name = name;
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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
