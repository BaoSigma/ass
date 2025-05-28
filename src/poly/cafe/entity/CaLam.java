/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

/**
 *
 * @author PC
 */
public class CaLam {
    private String Buoi;
    private NhanVien nv = new NhanVien();

    public CaLam(String Buoi) {
        this.Buoi = Buoi;
    }

    public CaLam() {
    }

    public String getBuoi() {
        return Buoi;
    }

    public void setBuoi(String Buoi) {
        this.Buoi = Buoi;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

}
