/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;
/**
 *
 * @author baoha
 */
public class DoanhThu {
    private String maDT;
    private double tongTien;
    private int donDaTao;
    private String ghiChu;

    public DoanhThu(String maDT, double tongTien, int donDaTao, String ghiChu) {
        this.maDT = maDT;
        this.tongTien = tongTien;
        this.donDaTao = donDaTao;
        this.ghiChu = ghiChu;
    }

    public String getMaDT() {
        return maDT;
    }

    public void setMaDT(String maDT) {
        this.maDT = maDT;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public int getDonDaTao() {
        return donDaTao;
    }

    public void setDonDaTao(int donDaTao) {
        this.donDaTao = donDaTao;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
}
