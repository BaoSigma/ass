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
    private String maNV;

    public NguoiDung() {
    }

    public NguoiDung(Nhanvien nv, String maND, String tenND, String matKhau, String chucVu, String maNV) {
        this.nv = nv;
        this.maND = maND;
        this.tenND = tenND;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
        this.maNV = maNV;
    }

    private NguoiDung(Builder builder) {
        this.nv = builder.nv;
        this.maND = builder.maND;
        this.tenND = builder.tenND;
        this.matKhau = builder.matKhau;
        this.chucVu = builder.chucVu;
        this.maNV = builder.maNV;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Nhanvien nv;
        private String maND;
        private String tenND;
        private String matKhau;
        private String chucVu;
        private String maNV;

        public Builder nv(Nhanvien nv) {
            this.nv = nv;
            if (nv != null) {
                this.maNV = nv.getMaNV();
            }
            return this;
        }

        public Builder maND(String maND) {
            this.maND = maND;
            return this;
        }

        public Builder tenND(String tenND) {
            this.tenND = tenND;
            return this;
        }

        public Builder matKhau(String matKhau) {
            this.matKhau = matKhau;
            return this;
        }

        public Builder chucVu(String chucVu) {
            this.chucVu = chucVu;
            return this;
        }

        public NguoiDung build() {
            return new NguoiDung(this);
        }
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
