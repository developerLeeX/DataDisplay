package com.android.datadisplay.bean;

public class DeviceBean extends BaseBean{
    private int monitorID;
    private int PLCID;
    private int DID;
    private String DType;
    private String deviceName;
    private String company;
    private String model;
    private String attribute;
    private String remark;

    public int getMonitorID() {
        return monitorID;
    }

    public void setMonitorID(int monitorID) {
        this.monitorID = monitorID;
    }

    public int getPLCID() {
        return PLCID;
    }

    public void setPLCID(int PLCID) {
        this.PLCID = PLCID;
    }

    public int getDID() {
        return DID;
    }

    public void setDID(int DID) {
        this.DID = DID;
    }

    public String getDType() {
        return DType;
    }

    public void setDType(String DType) {
        this.DType = DType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
