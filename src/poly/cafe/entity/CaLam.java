package poly.cafe.entity;

public class CaLam {
    private int ID;
    private String buoi;
    private NhanVien nv = new NhanVien();

    public CaLam() {
    }

    public CaLam(int ID, String buoi, NhanVien nv) {
        this.ID = ID;
        this.buoi = buoi;
        this.nv = nv;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBuoi() {
        return buoi;
    }

    public void setBuoi(String buoi) {
        this.buoi = buoi;
    }

    public NhanVien getNhanVien() {
        return nv;
    }

    public void setNhanVien(NhanVien nv) {
        this.nv = nv;
    }

    public String getMaNV() {
        return nv != null ? nv.getMaNV() : null;
    }

    public String getHoTen() {
        return nv != null ? nv.getHoTen() : null;
    }
    public void setMaNV(String maNV){
        this.nv.setMaNV(maNV);
        
    }
    public void setHoTen(String hoTen){
        this.nv.setHoTen(hoTen);
    }
    
}
