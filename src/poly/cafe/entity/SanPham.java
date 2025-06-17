/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

/**
 *
 * @author baoha
 */
public class SanPham extends LoaiSanPham{
    private String maSP;
    private String tenDU;
    private Double giaDU;
    private String hinhAnh;
    public SanPham(String maSP, String tenDU, Double giaDU, int maLSP, String tenLDU, String hinhAnh) {
        super(maLSP, tenLDU);
        this.maSP = maSP;
        this.tenDU = tenDU;
        this.giaDU = giaDU;
        this.hinhAnh = hinhAnh;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }



    public SanPham() {
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    @Override
    public void setMaLSP(int maLSP) {
        super.setMaLSP(maLSP); 
    }

    @Override
    public int getMaLSP() {
        return super.getMaLSP(); 
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
