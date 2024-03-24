package com.qgsg.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @TableName tb_device
 */
@TableName(value = "tb_device")
public class IOTDevice implements Serializable {
    /**
     * 设备ID 惟一
     */
    @TableId(value = "device_id")
    private Integer deviceId;

    /**
     *
     */
    @TableField(value = "device_name")
    private String deviceName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    public IOTDevice(){

    }
    public IOTDevice(Integer deviceId){
        this(deviceId,"");
    }
    public IOTDevice(Integer deviceId, String deviceName) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
    }

    /**
     * 设备ID 惟一
     */
    public Integer getDeviceId() {
        return deviceId;
    }

    /**
     * 设备ID 惟一
     */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    /**
     *
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     *
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }


    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        IOTDevice other = (IOTDevice) that;
        return (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
                && (this.getDeviceName() == null ? other.getDeviceName() == null : this.getDeviceName().equals(other.getDeviceName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getDeviceName() == null) ? 0 : getDeviceName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deviceId=").append(deviceId);
        sb.append(", deviceName=").append(deviceName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
