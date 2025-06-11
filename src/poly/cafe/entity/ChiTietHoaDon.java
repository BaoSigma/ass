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
    private double getTongDoanhThu;
    private String trangThai;
    private int ID;
    public ChiTietHoaDon(int maCT, String maHD, String maSP, int soLuong, double giaTien, double thanhTien, double getTongDoanhThu, String trangThai,int ID) {
        this.maCT = maCT;
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.thanhTien = thanhTien;
        this.getTongDoanhThu = getTongDoanhThu;
        this.trangThai = trangThai;
        this.ID = ID;
    }
    public double getGetTongDoanhThu() {
        return getTongDoanhThu;
    }

    public int getID() {
        return ID;
    }

    public void setID(int maDD) {
        this.ID = maDD;
    }

    public void setGetTongDoanhThu(double getTongDoanhThu) {
        this.getTongDoanhThu = getTongDoanhThu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
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
