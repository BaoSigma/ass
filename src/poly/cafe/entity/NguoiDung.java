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
public class NguoiDung {
    private Nhanvien nv;
    private String maND;
    private String tenND;
    private String matKhau;
    private String chucVu;
    private String maNV = nv.getMaNV();

    public NguoiDung(Nhanvien nv, String maND, String tenND, String matKhau, String chucVu) {
        this.nv = nv;
        this.maND = maND;
        this.tenND = tenND;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
    }

    public Nhanvien getNv() {
        return nv;
    }

    public void setNv(Nhanvien nv) {
        this.nv = nv;
    }

    public String getMaND() {
        return maND;
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }

    public String getTenND() {
        return tenND;
    }

    public void setTenND(String tenND) {
        this.tenND = tenND;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    
    

}
