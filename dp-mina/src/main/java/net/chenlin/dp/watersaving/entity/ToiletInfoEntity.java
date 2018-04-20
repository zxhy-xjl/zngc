package net.chenlin.dp.watersaving.entity;

import java.io.Serializable;

/**
 * 卫生间信息
 *
 * @author zhlei
 * @date 2018-04-16
 *
 */
public class ToiletInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 卫生间id
     */
    private Long id;

    /**
     * 卫生间名称
     */
    private String toiletName;

    private String toiletCode;

    private Integer status;

    private String deviceCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToiletName() {
        return toiletName;
    }

    public void setToiletName(String toiletName) {
        this.toiletName = toiletName;
    }

    public String getToiletCode() {
        return toiletCode;
    }

    public void setToiletCode(String toiletCode) {
        this.toiletCode = toiletCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }
}
