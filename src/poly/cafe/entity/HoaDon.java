/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package poly.cafe.entity;

import java.util.Date;

/**
 *
 * @author Dong Khanh
 */
public class HoaDon {
    private String maHD;
    private NhanVien nv = new NhanVien();
    private String ghiChu;
    private Date ngayTao;
    private double doanhThuTheoNgay;
    public double getDoanhThuTheoNgay() {
    return doanhThuTheoNgay;
}

    public void setDoanhThuTheoNgay(double doanhThuTheoNgay) {
        this.doanhThuTheoNgay = doanhThuTheoNgay;
    }
    public HoaDon(String maHD, String ghiChu, Date ngayTao) {
        this.maHD = maHD;
        this.ghiChu = ghiChu;
        this.ngayTao = ngayTao;

    }

    public HoaDon() {
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaNV() {
        return nv.getMaNV();
    }

    public void setMaNV(String maNV) {
        nv.setMaNV(maNV);
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }


        
}
