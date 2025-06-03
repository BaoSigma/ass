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
    private int ID;
    private String Buoi;
    private NhanVien nv = new NhanVien();
    private String hoTen; 
    public CaLam(String maCL,String hoTen,int ID) {
        this.Buoi = Buoi;
        this.hoTen = hoTen;
        this.ID = ID;
    }
    
    public CaLam() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public String getBuoi() {
        return Buoi;
    }

    public void setBuoi(String Buoi) {
        this.Buoi = Buoi;
    }

    public String getMaNV() {
        return nv.getMaNV();
    }

    public void setMaNV(String MaNV) {
        nv.setMaNV(MaNV);
    }

    public String getHoTen() {
        return nv.getHoTen();
    }

    public void setHoTen(String hoTenNV) {
        this.nv.setHoTen(hoTenNV);
    }


}
