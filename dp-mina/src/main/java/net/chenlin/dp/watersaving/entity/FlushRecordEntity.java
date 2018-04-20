package net.chenlin.dp.watersaving.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 冲水记录表
 *
 * @author zhlei
 * @date 2018-04-16
 *
 */
public class FlushRecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long toiletId;
    private Timestamp startTime;
    private Timestamp endTime;
    private BigDecimal flushValue;
    private Timestamp createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getToiletId() {
        return toiletId;
    }

    public void setToiletId(Long toiletId) {
        this.toiletId = toiletId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getFlushValue() {
        return flushValue;
    }

    public void setFlushValue(BigDecimal flushValue) {
        this.flushValue = flushValue;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
