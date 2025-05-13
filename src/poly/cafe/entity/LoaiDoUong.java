/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;
/**
 *
 * @author baoha
 */
public class LoaiDoUong {
    private String maLDU;
    private String ten;

    public LoaiDoUong(String maLDU, String ten) {
        this.maLDU = maLDU;
        this.ten = ten;
    }

    public String getMaLDU() {
        return maLDU;
    }

    public void setMaLDU(String maLDU) {
        this.maLDU = maLDU;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
    
}
