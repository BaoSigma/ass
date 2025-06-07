/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.entityDAO.ChiTietHoaDonDAO;
import poly.cafe.entity.ChiTietHoaDon;
import poly.cafe.entity.HoaDon;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;
import java.sql.*;
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

    private static final String SP_INSERT_DETAIL = "{CALL Insert_ChiTietHoaDon(?, ?, ?, ?)}";

    private String deleteChiTietHoaDonByIdSQL = "DELETE FROM Chitiethoadon WHERE maCT = ?";
    private String findAllChiTietHoaDonSQL = "SELECT maHD, maSP, soLuong, giaTien, (soLuong*giaTien) AS thanhTien FROM Chitiethoadon";
    private String findChiTietHoaDonByIdSQL = findAllChiTietHoaDonSQL + " WHERE maCT = ?";
    private String updateChiTietHoaDonSQL = "UPDATE Chitiethoadon SET maHD = ?, maSP = ?, soLuong = ?, giaTien = ? WHERE maCT = ?";
    public void insertChiTietHoaDon(String maHD, String maSP, int soLuong, double giaTien) {
        try (CallableStatement cs = XJdbc.openConnection().prepareCall(SP_INSERT_DETAIL)) {
            cs.setString(1, maHD);
            cs.setString(2, maSP);
            cs.setInt(3, soLuong);
            cs.setDouble(4, giaTien);
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
         
    }
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

    
    public List<ChiTietHoaDon> getDetailsByMaHD(String maHD) {  
    List<ChiTietHoaDon> list = new ArrayList<>();
    try {
        String sql = "SELECT maCT, maHD, maSP, soLuong, giaTien FROM Chitiethoadon WHERE maHD = ?";
        ResultSet rs = XJdbc.executeQuery(sql, maHD);
        while (rs.next()) {
            ChiTietHoaDon ct = new ChiTietHoaDon();
            ct.setMaCT(rs.getInt("maCT"));           // mã chi tiết hóa đơn (nếu bạn có trường này)
            ct.setMaHD(rs.getString("maHD"));        // mã hóa đơn
            ct.setMaSP(rs.getString("maSP"));        // mã sản phẩm
            ct.setSoLuong(rs.getInt("soLuong"));     // số lượng
            ct.setGiaTien(rs.getDouble("giaTien"));  // giá tiền
            
            list.add(ct);
        }
        rs.getStatement().getConnection().close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
    }

}
