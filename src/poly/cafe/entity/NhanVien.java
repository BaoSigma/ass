/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;


import java.util.Date;
import lombok.Builder;
import poly.cafe.dao.entityDAO.NhanVienDAO;
import poly.cafe.dao.impl.NhanVienimpl;

/**
 *
 * @author baoha
 */
@Builder
public class NhanVien {
    private String maNV ;
    private String hoTen;
    private Date namSinh;
    private String sdt;
    private String email;
    private String matKhau;
    private String chucVu;

    public NhanVien() {
    }
    
    public NhanVien(String maNV, String hoTen, Date namSinh, String sdt, String email, String matKhau, String chucVu) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.sdt = sdt;
        this.email = email;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(Date namSinh) {
        this.namSinh = namSinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    
   
    }
