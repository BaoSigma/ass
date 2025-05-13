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
public class PhieuBanHang {
    private String maPhieu;
    private java.sql.Date ngayTao;
    private String ghiChu;
    private DoUong du;
    private TheDinhDanh tdd;
    private Nhanvien nv;
    private String maDU = du.getMaDU();
    private String maDD = tdd.getMaDD();
    private String maNV = nv.getMaNV();

    public PhieuBanHang(String maPhieu, Date ngayTao, String ghiChu, DoUong du, TheDinhDanh tdd, Nhanvien nv) {
        this.maPhieu = maPhieu;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
        this.du = du;
        this.tdd = tdd;
        this.nv = nv;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public DoUong getDu() {
        return du;
    }

    public void setDu(DoUong du) {
        this.du = du;
    }

    public TheDinhDanh getTdd() {
        return tdd;
    }

    public void setTdd(TheDinhDanh tdd) {
        this.tdd = tdd;
    }

    public Nhanvien getNv() {
        return nv;
    }

    public void setNv(Nhanvien nv) {
        this.nv = nv;
    }

    public String getMaDU() {
        return maDU;
    }

    public void setMaDU(String maDU) {
        this.maDU = maDU;
    }

    public String getMaDD() {
        return maDD;
    }

    public void setMaDD(String maDD) {
        this.maDD = maDD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    
}
