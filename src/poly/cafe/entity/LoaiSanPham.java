/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package poly.cafe.entity;

/**
 *
 * @author Dong Khanh
 */
public class LoaiSanPham {
    private int maLSP;
    private String tenLDU;
    
    public LoaiSanPham(int maLSP, String tenLDU) {
        this.maLSP = maLSP;
        this.tenLDU = tenLDU;
    }

    public LoaiSanPham() {
    }

    public int getMaLSP() {
        return maLSP;
    }

    public void setMaLSP(int maLSP) {
        this.maLSP = maLSP;
    }

    public String getTenLDU() {
        return tenLDU;
    }

    public void setTenLDU(String tenLDU) {
        this.tenLDU = tenLDU;
    }
    
    
}
