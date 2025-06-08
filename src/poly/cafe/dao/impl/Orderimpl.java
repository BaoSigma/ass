/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.sql.*;
import java.util.List;
import poly.cafe.dao.entityDAO.HoaDonDAO;
import poly.cafe.entity.CaLam;
import poly.cafe.entity.HoaDon;
import poly.cafe.entity.theDD;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;



/**
 *
 * @author baoha
 */
public class Orderimpl{
    private String UpdateTDD = "Update TheDD set trangThai = ? Where ID = ? ";
    private String FindTheDD = "Select ID, trangThai From TheDD Where trangThai like N'%Chưa sử dụng%' ";
    private String SP_INSERT_BILL = "{CALL Insert_bill(?, ?, ?)}"; // 3 tham số: maNV, ghiChu, @newMaHD OUTPUT

    
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
   
    public void update(theDD entity) {
         Object[] values = {
            entity.getTrangThai(),
             entity.getID()
        };
        XJdbc.executeUpdate(UpdateTDD, values);
    }

    
    public List<theDD> findAll() {
        return XQuery.getBeanList(theDD.class, FindTheDD);
    }

    
}
