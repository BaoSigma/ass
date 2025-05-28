/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

/**
 *
 * @author ExpertBook
 */
public class LoaiDoUong {
    private int maLDU;
    private String tenLDu;

    public LoaiDoUong(int maLDU, String tenLDu) {
        this.maLDU = maLDU;
        this.tenLDu = tenLDu;
    }

    public LoaiDoUong() {
    }

    public int getMaLDU() {
        return maLDU;
    }

    public void setMaLDU(int maLDU) {
        this.maLDU = maLDU;
    }

    public String getTenLDu() {
        return tenLDu;
    }

    public void setTenLDu(String tenLDu) {
        this.tenLDu = tenLDu;
    }
}
