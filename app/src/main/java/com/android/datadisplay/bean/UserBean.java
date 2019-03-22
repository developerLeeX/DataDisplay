package com.android.datadisplay.bean;

import java.io.Serializable;

public class UserBean implements Serializable {
    /**
     * LoginName : admin
     * UserName : 超级管理员
     * Password : c4ca4238a0b923820dcc509a6f75849b
     * Phone : 18201437203
     * EMail : lixunemail@163.com
     * CreateTime : 2019-03-14 16:10:55.1860092 08:00
     * IsLock : 0
     * ForbidMessage :
     * LoginCount : 2
     * LastLoginTime : 2019-03-14 16:12:17.5190092 08:00
     * LastLoginIpAddress : 127.0.0.1
     * LoginFailedCount : 2
     * LastLoginMode :
     */

    private String LoginName;
    private String UserName;
    private String Password;
    private String Phone;
    private String EMail;
    private String CreateTime;
    private String IsLock;
    private String ForbidMessage;   //状态信息     空字符串代表登录失败
    private int LoginCount;
    private String LastLoginTime;
    private String LastLoginIpAddress;
    private int LoginFailedCount;
    private String LastLoginMode;

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String LoginName) {
        this.LoginName = LoginName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getIsLock() {
        return IsLock;
    }

    public void setIsLock(String IsLock) {
        this.IsLock = IsLock;
    }

    public String getForbidMessage() {
        return ForbidMessage;
    }

    public void setForbidMessage(String ForbidMessage) {
        this.ForbidMessage = ForbidMessage;
    }

    public int getLoginCount() {
        return LoginCount;
    }

    public void setLoginCount(int LoginCount) {
        this.LoginCount = LoginCount;
    }

    public String getLastLoginTime() {
        return LastLoginTime;
    }

    public void setLastLoginTime(String LastLoginTime) {
        this.LastLoginTime = LastLoginTime;
    }

    public String getLastLoginIpAddress() {
        return LastLoginIpAddress;
    }

    public void setLastLoginIpAddress(String LastLoginIpAddress) {
        this.LastLoginIpAddress = LastLoginIpAddress;
    }

    public int getLoginFailedCount() {
        return LoginFailedCount;
    }

    public void setLoginFailedCount(int LoginFailedCount) {
        this.LoginFailedCount = LoginFailedCount;
    }

    public String getLastLoginMode() {
        return LastLoginMode;
    }

    public void setLastLoginMode(String LastLoginMode) {
        this.LastLoginMode = LastLoginMode;
    }
}
