/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.entityDAO.HoaDonDAO;
import poly.cafe.entity.HoaDon;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;
import java.sql.*;
/**
 *
 * @author baoha
 */
public class HoaDonimpl implements HoaDonDAO{
    private String createsql= "EXEC Insert_HoaDon \n" +
"    @maNV = ?, \n" +
"    @ghiChu = ?, \n" +
"    @ngayTao = ?";
    private String deleteHoaDonByIdSQL = "DELETE FROM Hoadon WHERE maHD = ?";
    private String findAllHoaDonSQL = "SELECT * FROM Hoadon";
    private String findHoaDonByIdSQL = findAllHoaDonSQL + " WHERE maHD = ?";
    private String updateHoaDonSQL = "UPDATE Hoadon SET maNV = ?, ghiChu = ?, ngayTao = ? WHERE maHD = ?";

    @Override
    public HoaDon create(HoaDon entity) {
        Object[] values = {
            entity.getMaNV(),
            entity.getGhiChu(),
            entity.getNgayTao(),
        };
        XJdbc.executeUpdate(createsql, values);
        return entity;
    }

    @Override
    public void update(HoaDon entity) {
        Object[] values = {
            entity.getMaNV(),
            entity.getGhiChu(),
            entity.getNgayTao(),
            entity.getMaHD()
        };
        XJdbc.executeUpdate(updateHoaDonSQL, values);
    }

    @Override
    public void deleteById(Object id) {
        XJdbc.executeUpdate(deleteHoaDonByIdSQL, id);
    }

    @Override
    public List<HoaDon> findAll() {
        return XQuery.getBeanList(HoaDon.class, findAllHoaDonSQL);
    }

    @Override
    public HoaDon findById(Object id) {
        return XQuery.getSingleBean(HoaDon.class, findHoaDonByIdSQL, id);
    }
        public List<HoaDon> findByKeyword(String keyword) {
    String sql = findAllHoaDonSQL
        + " WHERE maHD LIKE ? OR maNV LIKE ? OR ghiChu LIKE ? OR ngayTao LIKE ?";

    String value = "%" + keyword + "%";
    return XQuery.getBeanList(HoaDon.class, sql, value, value, value, value);
}

  
    
}

