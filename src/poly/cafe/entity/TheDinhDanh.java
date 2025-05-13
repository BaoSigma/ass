/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;
/**
 *
 * @author baoha
 */
public class TheDinhDanh {
    private String maDD;
    private String trangThai;

    public TheDinhDanh(String maDD, String trangThai) {
        this.maDD = maDD;
        this.trangThai = trangThai;
    }

    public String getMaDD() {
        return maDD;
    }

    public void setMaDD(String maDD) {
        this.maDD = maDD;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
}
