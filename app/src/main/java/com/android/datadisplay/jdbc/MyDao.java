package com.android.datadisplay.jdbc;


import com.android.datadisplay.bean.DeviceBean;
import com.android.datadisplay.bean.MonitorBean;
import com.android.datadisplay.bean.PLCBean;
import com.android.datadisplay.bean.ProjectBean;
import com.android.datadisplay.bean.UserBean;
import com.android.datadisplay.utils.MD5Util;
import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyDao {
    private static final String MYSQL_URL ="jdbc:mysql://192.168.1.21:3306/hh_wt_scada";
    private static final String MYSQL_USER="root";
    private static final String MYSQL_PWD = "android951229";
    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UserBean login(String user , String pwd){
        UserBean userBean=null;

        try {
            Connection connection = DriverManager.getConnection(MYSQL_URL,MYSQL_USER,MYSQL_PWD);
            Statement statement = connection.createStatement();
            String sql = "select * from huser where LoginName='"+user+"' and PassWord='"+ MD5Util.getMD5String(pwd) +"';";

            ResultSet set = statement.executeQuery(sql);
            if (set.next()){
                userBean = new UserBean();
                userBean.setLoginName(user);
                userBean.setPassword(pwd);
                userBean.setEMail(set.getString("Email"));
                userBean.setPhone(set.getString("Phone"));
                userBean.setUserName(set.getString("UserName"));
                userBean.setIsLock(set.getString("IsLock"));
            }
            return userBean;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBean;
    }
    //查询所有项目信息
    public static List<ProjectBean> queryProject(){
        List<ProjectBean> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(MYSQL_URL,MYSQL_USER,MYSQL_PWD);
            Statement statement = connection.createStatement();
            String sql= "select * from pproject";
            ResultSet set = statement.executeQuery(sql);
            ProjectBean projectBean;
            while (set.next()){
                projectBean = new ProjectBean();
                projectBean.setProId(set.getInt("ProID"));
                projectBean.setProName(set.getString("ProName"));
                projectBean.setCustName(set.getString("CustName"));
                projectBean.setCustContact(set.getString("CustContact"));
                projectBean.setCustPhone(set.getString("CustPhone"));
                projectBean.setUserName(set.getString("UserName"));
                projectBean.setUserContact(set.getString("UserContact"));
                projectBean.setUserPhone(set.getString("UserPhone"));
                projectBean.setProInfo(set.getString("ProInfo"));
                list.add(projectBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //根据项目id查询项目下监测点
    public static List<MonitorBean> queryMonitor(int proID){
        List<MonitorBean> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(MYSQL_URL,MYSQL_USER,MYSQL_PWD);
            Statement statement = connection.createStatement();
            String sql= "select * from pmonitor where ProID="+proID+";";
            ResultSet set = statement.executeQuery(sql);
            MonitorBean monitorBean;
            while (set.next()){
                monitorBean = new MonitorBean();
                monitorBean.setProID(set.getInt("ProID"));
                monitorBean.setMonitorID(set.getInt("MonitorID"));
                monitorBean.setMonitorName(set.getString("MonitorName"));
                monitorBean.setMonitorNum(set.getString("MonitorNum"));
                monitorBean.setProductName(set.getString("ProductName"));
                monitorBean.setProductSize(set.getString("ProductSize"));
                monitorBean.setProvince(set.getString("Province"));
                monitorBean.setCity(set.getString("City"));
                monitorBean.setDistrict(set.getString("District"));
                monitorBean.setAddress(set.getString("Address"));
                monitorBean.setLng(set.getString("Lng"));
                monitorBean.setLat(set.getString("Lat"));
                monitorBean.setRemark(set.getString("Remark"));
                list.add(monitorBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据id查询plc表
     * @param id 上级监测点的id
     * @return plc数据集合
     */
    public static List<PLCBean> queryPLCByID(int id){
        List<PLCBean> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(MYSQL_URL,MYSQL_USER,MYSQL_PWD);
            Statement statement = connection.createStatement();
            String sql = "select * from pplc where MonitorID="+id+";";
            ResultSet set = statement.executeQuery(sql);
            PLCBean plcBean=null;
            while (set.next()){
                plcBean = new PLCBean();
                plcBean.setMonitorID(id);
                plcBean.setPLCID(set.getInt("PLCID"));
                plcBean.setCompany(set.getString("Company"));
                plcBean.setModel(set.getString("Model"));
                plcBean.setName(set.getString("Name"));
                plcBean.setIP(set.getString("IP"));
                plcBean.setPort(set.getInt("Port"));
                plcBean.setProtocol(set.getString("Protocol"));
                plcBean.setRemark(set.getString("Remark"));
                list.add(plcBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static List<DeviceBean> queryDeviceByID(int plcID){
        List<DeviceBean> deviceList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(MYSQL_URL,MYSQL_USER,MYSQL_PWD);
            Statement statement = connection.createStatement();
            String sql = "select * from pdevice where PLCID="+plcID+";";
            ResultSet set = statement.executeQuery(sql);
            DeviceBean deviceBean;
            while (set.next()){
                deviceBean = new DeviceBean();
                deviceBean.setMonitorID(set.getInt("MonitorID"));
                deviceBean.setPLCID(plcID);
                deviceBean.setDID(set.getInt("DID"));
                deviceBean.setDType(set.getString("DType"));
                deviceBean.setDeviceName(set.getString("DeviceName"));
                deviceBean.setCompany(set.getString("Company"));
                deviceBean.setModel(set.getString("Model"));
                deviceBean.setAttribute(set.getString("Attribute"));
                deviceBean.setRemark(set.getString("Remark"));
                deviceList.add(deviceBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deviceList;
    }
}
