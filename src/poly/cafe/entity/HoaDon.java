/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package poly.cafe.entity;

/**
 *
 * @author Dong Khanh
 */
public class HoaDon {
    private String maHD;
    private NhanVien nv = new NhanVien();
    private String ghiChu;
    private String ngayTao;
    private Double thanhTien;

    public HoaDon(String maHD, String ghiChu, String ngayTao, Double thanhTien) {
        this.maHD = maHD;
        this.ghiChu = ghiChu;
        this.ngayTao = ngayTao;
        this.thanhTien = thanhTien;
    }

    public HoaDon() {
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Double thanhTien) {
        this.thanhTien = thanhTien;
    }

        
}
