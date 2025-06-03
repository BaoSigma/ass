/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

/**
 *
 * @author ExpertBook
 */
public class theDD {
    private int ID;
    private String trangThai;
    private  HoaDon hd =  new HoaDon();

    public theDD(int ID, String trangThai) {
        this.ID = ID;
        this.trangThai = trangThai;
    }

    public theDD() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getHd() {
        return hd.getMaHD();
    }

    public void setHd(String hd) {
        this.hd.setMaHD(hd);
    }

}
