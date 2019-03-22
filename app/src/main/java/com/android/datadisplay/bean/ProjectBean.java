package com.android.datadisplay.bean;

public class ProjectBean extends BaseBean{
    private int proId;
    private String proName;
    private String CustName;
    private String CustContact;
    private String CustPhone;
    private String userName;
    private String userContact;
    private String userPhone;
    private String proInfo;

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String custName) {
        CustName = custName;
    }

    public String getCustContact() {
        return CustContact;
    }

    public void setCustContact(String custContact) {
        CustContact = custContact;
    }

    public String getCustPhone() {
        return CustPhone;
    }

    public void setCustPhone(String custPhone) {
        CustPhone = custPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getProInfo() {
        return proInfo;
    }

    public void setProInfo(String proInfo) {
        this.proInfo = proInfo;
    }
}
