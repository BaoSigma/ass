/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

/**
 *
 * @author baoha
 */
public class ChiTietPhieuBan {
    private int ID;
    private double gia;
    private DoUong du;
    private String maDU = du.getMaDU();

    public ChiTietPhieuBan(int ID, double gia, DoUong du) {
        this.ID = ID;
        this.gia = gia;
        this.du = du;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public DoUong getDu() {
        return du;
    }

    public void setDu(DoUong du) {
        this.du = du;
    }

    public String getMaDU() {
        return maDU;
    }

    public void setMaDU(String maDU) {
        this.maDU = maDU;
    }
    
    
}
