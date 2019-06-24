package com.example.demo.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 *
 * @author gengen.wang
 * @date 2019/06/24
 */
public class PriceVersion implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 总值
     */
    private BigDecimal total;

    /**
     * 消费前
     */
    private BigDecimal front;

    /**
     * 消费后
     */
    private BigDecimal end;

    /**
     * 并发版本控制
     */
    private Integer version;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table price_version
     *
     * @mbg.generated Mon Jun 24 11:32:38 CST 2019
     */
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getFront() {
        return front;
    }

    public void setFront(BigDecimal front) {
        this.front = front;
    }

    public BigDecimal getEnd() {
        return end;
    }

    public void setEnd(BigDecimal end) {
        this.end = end;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}