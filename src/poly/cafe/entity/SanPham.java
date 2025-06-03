/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

/**
 *
 * @author baoha
 */
public class SanPham {
    private String maSP;
    private LoaiSanPham lsp = new LoaiSanPham();
    private String tenDU;
    private Double giaDU;

    public SanPham(String maSP, String tenDU, Double giaDU) {
        this.maSP = maSP;
        this.tenDU = tenDU;
        this.giaDU = giaDU;
    }

    public SanPham() {
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getLsp() {
        return lsp.getMaLSP();
    }

    public void setLsp(String lsp) {
        this.lsp.setMaLSP(maSP);
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
