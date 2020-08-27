package cn.edu.ahu.carmanagement.Json;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * 前台BootstrapTable 所需数据JSON类型
 */
public class TableResponseJson<T> {
    private List<T> rows;
    private Long total;

    public TableResponseJson(){
        this.rows = new ArrayList<>();
        total = 0L;
    }

    public TableResponseJson(Page<T> page) {
        this.rows = page.getContent();
        this.total = page.getTotalElements();
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
