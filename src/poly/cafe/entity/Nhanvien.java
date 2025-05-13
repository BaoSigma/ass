/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

import java.sql.Date;

/**
 *
 * @author baoha
 */
public class Nhanvien {
    private String maNV;
    private String hovaTen;
    private java.sql.Date namSinh;
    private String anh;
    private DoanhThu dt;
    private String maDT = dt.getMaDT();

    public Nhanvien(String maNV, String hovaTen, Date namSinh, String anh, DoanhThu dt) {
        this.maNV = maNV;
        this.hovaTen = hovaTen;
        this.namSinh = namSinh;
        this.anh = anh;
        this.dt = dt;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHovaTen() {
        return hovaTen;
    }

    public void setHovaTen(String hovaTen) {
        this.hovaTen = hovaTen;
    }

    public Date getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(Date namSinh) {
        this.namSinh = namSinh;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public DoanhThu getDt() {
        return dt;
    }

    public void setDt(DoanhThu dt) {
        this.dt = dt;
    }

    public String getMaDT() {
        return maDT;
    }

    public void setMaDT(String maDT) {
        this.maDT = maDT;
    }
    
}
