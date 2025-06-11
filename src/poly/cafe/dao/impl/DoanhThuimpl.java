/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.entityDAO.DoanhThuDAO;
import poly.cafe.entity.ChiTietHoaDon;
import poly.cafe.entity.HoaDon;
import poly.cafe.entity.NhanVien;
import poly.cafe.util.XQuery;
import java.sql.*;
import poly.cafe.entity.DoanhThuNhanVien;
import poly.cafe.util.XJdbc;
/**
 *
 * @author baoha
 */
public class DoanhThuimpl implements DoanhThuDAO{
    String TongDoanhThuSQL = "SELECT SUM(soLuong * giaTien) AS TongDoanhThu FROM Chitiethoadon";
    String DoanhThuTheoNgaySQL = "SELECT \n" +
"    HD.ngayTao,\n" +
"    SUM(CT.soLuong * CT.giaTien) AS DoanhThuTheoNgay\n" +
"FROM \n" +
"    Hoadon HD\n" +
"JOIN \n" +
"    Chitiethoadon CT ON HD.maHD = CT.maHD\n" +
"GROUP BY \n" +
"    HD.ngayTao\n" +
"ORDER BY \n" +
"    HD.ngayTao";
    String DoanhThuNhanVienSQL="SELECT \n" +
"    NV.hoTen,\n" +
"    NV.maNV,\n" +
"    SUM(CT.soLuong * CT.giaTien) AS DoanhThuTheoNhanVien\n" +
"FROM \n" +
"    Hoadon HD\n" +
"JOIN \n" +
"    Chitiethoadon CT ON HD.maHD = CT.maHD\n" +
"JOIN \n" +
"    Nhanvien NV ON HD.maNV = NV.maNV\n" +
"GROUP BY \n" +
"    NV.hoTen, NV.maNV";
   @Override
public Double layTongDoanhThu() {
    return XJdbc.getValue(TongDoanhThuSQL);
}

    @Override
    public List<HoaDon> DoanhThuTheoNgay() {
        return XQuery.getBeanList(HoaDon.class, DoanhThuTheoNgaySQL);
    }

    @Override
    public List<DoanhThuNhanVien> DoanhThuNhanVien() {
        return XQuery.getBeanList(DoanhThuNhanVien.class, DoanhThuNhanVienSQL);
    }
    
}
