/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.entityDAO;

import java.util.List;
import poly.cafe.entity.ChiTietHoaDon;
import poly.cafe.entity.DoanhThuNhanVien;
import poly.cafe.entity.HoaDon;
import poly.cafe.entity.NhanVien;
import poly.cafe.entity.SanPham;

/**
 *
 * @author baoha
 */
public interface DoanhThuDAO{
    public Double layTongDoanhThu();
List<HoaDon> DoanhThuTheoNgay();
    List<DoanhThuNhanVien> DoanhThuNhanVien();
    
}
