/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

/**
 *
 * @author baoha
 */
public class DoUong extends LoaiDoUong{
        private String maDU;
    private String tenDU;
    private String anhDoUong;
    private String Size;


    public DoUong(String maDU, String tenDU, String anhDoUong, String Size, String maLDU, String ten) {
        super(maLDU, ten);
        this.maDU = maDU;
        this.tenDU = tenDU;
        this.anhDoUong = anhDoUong;
        this.Size = Size;
    }

    public String getMaDU() {
        return maDU;
    }

    public void setMaDU(String maDU) {
        this.maDU = maDU;
    }

    public String getTenDU() {
        return tenDU;
    }

    public void setTenDU(String tenDU) {
        this.tenDU = tenDU;
    }

    public String getAnhDoUong() {
        return anhDoUong;
    }

    public void setAnhDoUong(String anhDoUong) {
        this.anhDoUong = anhDoUong;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }
    
}
