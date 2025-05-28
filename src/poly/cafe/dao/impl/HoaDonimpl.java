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

/**
 *
 * @author baoha
 */
public class HoaDonimpl implements HoaDonDAO{
    private String createsql= "EXEC ThemHoaDon \n" +
"	@maNV = '?',\n" +
"    @ghiChu = '?',\n" +
"    @ngayTao = '?',\n" +
"    @thanhTien = '?';";
    private String deleteByIdSQL = "DELETE FROM HoaDon Where maHD =?";
    private String findAllSQL = "SELECT * FROM HoaDon";
    private String findByIdSQL = findAllSQL + "Where maHD = ?";
    private String UpdateSQL = "Update HoaDon set maNV = ?, ghiChu = ?, ngayTao = ?, thanhTien = ? WHERE maHD = ? ";
    @Override
    public HoaDon create(HoaDon entity) {
    Object[] values = {
    entity.getMaHD(),
    entity.getNv(),
    entity.getNgayTao(),
    entity.getThanhTien(),
    entity.getGhiChu()
    };
    XJdbc.executeUpdate(createsql, values);
    return entity;
    }

    @Override
    public void update(HoaDon entity) {
        Object[] values = {
    entity.getMaHD(),
    entity.getNv(),
    entity.getNgayTao(),
    entity.getThanhTien(),
    entity.getGhiChu()
    };
    XJdbc.executeUpdate(UpdateSQL, values);
    }

    @Override
    public void deleteById(Object id) {
        XJdbc.executeUpdate(deleteByIdSQL, id);
    }

    @Override
    public List<HoaDon> findAll() {
        return XQuery.getBeanList(HoaDon.class, findAllSQL);
    }

    @Override
    public HoaDon findById(Object id) {
        return XQuery.getSingleBean(HoaDon.class, findByIdSQL, id);
    }
    
}
