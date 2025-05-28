/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package poly.cafe.entity;

/**
 *
 * @author Dong Khanh
 */
public class DoUong {
    private String maDU;
    private LoaiDoUong LDU;
    private String tenDU;
    private Double giaDU;

    public DoUong(String maDU, LoaiDoUong LDU, String tenDU, Double giaDU) {
        this.maDU = maDU;
        this.LDU = LDU;
        this.tenDU = tenDU;
        this.giaDU = giaDU;
    }

    public DoUong() {
    }

    public String getMaDU() {
        return maDU;
    }

    public void setMaDU(String maDU) {
        this.maDU = maDU;
    }

    public LoaiDoUong getLDU() {
        return LDU;
    }

    public void setLDU(LoaiDoUong LDU) {
        this.LDU = LDU;
    }

    public String getTenDU() {
        return tenDU;
    }

    public void setTenDU(String tenDU) {
        this.tenDU = tenDU;
    }

    public Double getGiaDU() {
        return giaDU;
    }

    public void setGiaDU(Double giaDU) {
        this.giaDU = giaDU;
    }
    
    
}
