/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

/**
 *
 * @author baoha
 */
public class ChiTietHoaDon {
    private int maCT;
    private String maHD;
    private String maSP;
    private int soLuong;
    private double giaTien;
    private double thanhTien;
    public double getTongDoanhThu;

    public double getGetTongDoanhThu() {
        return getTongDoanhThu;
    }

    public void setGetTongDoanhThu(double getTongDoanhThu) {
        this.getTongDoanhThu = getTongDoanhThu;
    }
    
    public ChiTietHoaDon(String maHD, String maSP, int soLuong, double giaTien) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
    }
    
    public ChiTietHoaDon(int maCT, String maHD, String maSP, int soLuong, double giaTien,double thanhTien) {
        this.maCT = maCT;
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.thanhTien =thanhTien;
    }

    public ChiTietHoaDon() {
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getMaCT() {
        return maCT;
    }

    public void setMaCT(int maCT) {
        this.maCT = maCT;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    

}
