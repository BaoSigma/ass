/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

/**
 *
 * @author PC
 */
public class DoanhThu  {
    private int ID;
    private String soDonDaTao;
    private Double tongThuNhap;
    private NhanVien nv = new NhanVien();

    public DoanhThu(int ID, String soDonDaTao, Double tongThuNhap, NhanVien nv) {
        this.ID = ID;
        this.soDonDaTao = soDonDaTao;
        this.tongThuNhap = tongThuNhap;
        this.nv = nv;
    }

    public DoanhThu() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSoDonDaTao() {
        return soDonDaTao;
    }

    public void setSoDonDaTao(String soDonDaTao) {
        this.soDonDaTao = soDonDaTao;
    }

    public Double getTongThuNhap() {
        return tongThuNhap;
    }

    public void setTongThuNhap(Double tongThuNhap) {
        this.tongThuNhap = tongThuNhap;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }
    
    
    
   
    
    
}
