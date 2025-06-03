/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.entityDAO.ChiTietHoaDonDAO;
import poly.cafe.entity.ChiTietHoaDon;
import poly.cafe.entity.HoaDon;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;

/**
 *
 * @author baoha
 */
public class ChiTietImpl implements ChiTietHoaDonDAO {
    private String createsql= "EXEC Insert_ChiTietHoaDon \n" +
"    @maHD = ?, \n" +
"    @maSP = ?, \n" +
"    @soLuong = ?, \n" +
"    @giaTien = ?";
    private String deleteChiTietHoaDonByIdSQL = "DELETE FROM Chitiethoadon WHERE maCT = ?";
    private String findAllChiTietHoaDonSQL = "SELECT maHD, maSP, soLuong, giaTien, (soLuong*giaTien) AS thanhTien FROM Chitiethoadon";
    private String findChiTietHoaDonByIdSQL = findAllChiTietHoaDonSQL + " WHERE maCT = ?";
    private String updateChiTietHoaDonSQL = "UPDATE Chitiethoadon SET maHD = ?, maSP = ?, soLuong = ?, giaTien = ? WHERE maCT = ?";

        @Override
    public ChiTietHoaDon create(ChiTietHoaDon entity) {
        Object[] values = {  
            entity.getMaHD(),
            entity.getMaSP(),
            entity.getSoLuong(),
            entity.getGiaTien()
        };
        XJdbc.executeUpdate(createsql, values);
        return entity;
    }

    @Override
    public void update(ChiTietHoaDon entity) {
        Object[] values = {
            entity.getMaHD(),
            entity.getMaSP(),
            entity.getSoLuong(),
            entity.getGiaTien(),
            entity.getMaCT()
        };
        XJdbc.executeUpdate(updateChiTietHoaDonSQL, values);
    }

    @Override
    public void deleteById(Object id) {
        XJdbc.executeUpdate(deleteChiTietHoaDonByIdSQL, id);
    }

    @Override
    public List<ChiTietHoaDon> findAll() {
        return XQuery.getBeanList(ChiTietHoaDon.class, findAllChiTietHoaDonSQL);
    }

    @Override
    public ChiTietHoaDon findById(Object id) {
        return XQuery.getSingleBean(ChiTietHoaDon.class, findChiTietHoaDonByIdSQL, id);
    }
    
}
