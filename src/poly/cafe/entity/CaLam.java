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
    private String maCL;
    private NhanVien nv = new NhanVien();
    private String hoTenNV;

    public CaLam(String maCL, String hoTenNV) {
        this.maCL = maCL;
        this.hoTenNV = hoTenNV;
    }

    public CaLam() {
    }

    public String getMaCL() {
        return maCL;
    }

    public void setMaCL(String maCL) {
        this.maCL = maCL;
    }

    public String getMaNV() {
        return nv.getMaNV();
    }

    public void setMaNV(String MaNV) {
        nv.setMaNV(MaNV);
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }


}
