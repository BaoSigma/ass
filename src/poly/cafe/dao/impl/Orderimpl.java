/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.sql.*;
import java.util.List;
import poly.cafe.dao.entityDAO.HoaDonDAO;
import poly.cafe.entity.HoaDon;
import poly.cafe.util.XJdbc;



/**
 *
 * @author baoha
 */
public class Orderimpl implements HoaDonDAO{
    
    private static final String SP_INSERT_BILL = "{CALL Insert_bill(?, ?, ?)}"; // 3 tham số: maNV, ghiChu, @newMaHD OUTPUT

    @Override
    public HoaDon create(HoaDon entity) {
        String newMaHD = null;

        try (CallableStatement cs = XJdbc.openConnection().prepareCall(SP_INSERT_BILL)) {
            cs.setString(1, entity.getMaNV());
            cs.setString(2, entity.getGhiChu());
            cs.registerOutParameter(3, Types.VARCHAR);

            cs.executeUpdate();

            newMaHD = cs.getString(3); // Lấy mã hóa đơn mới tạo
            entity.setMaHD(newMaHD);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entity;
    }
    
    @Override
    public void update(HoaDon entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDon> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDon findById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
