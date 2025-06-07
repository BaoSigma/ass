/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

import java.util.Date;

/**
 *
 * @author ExpertBook
 */
public class theDD extends HoaDon{
    private int ID;
    private String trangThai;
    

    public theDD(int ID, String trangThai, String maHD, String ghiChu, Date ngayTao) {
        super(maHD, ghiChu, ngayTao);
        this.ID = ID;
        this.trangThai = trangThai;
    }
    

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

    @Override
    public String getMaHD() {
        return super.getMaHD();
    }

    @Override
    public void setMaHD(String maHD) {
        super.setMaHD(maHD); 
    }
    

}
