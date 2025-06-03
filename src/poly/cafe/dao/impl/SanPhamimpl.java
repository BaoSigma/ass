/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.entityDAO.SanPhamDAO;
import poly.cafe.entity.SanPham;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;

/**
 *
 * @author baoha
 */
public class SanPhamimpl implements SanPhamDAO{
    private String createsql= "EXEC Insert_SanPham \n" +
"    @maLSP = ?, \n" +
"    @tenDU = ?, \n" +
"    @giaDU = ?\n";
    private String deleteSanPhamByIdSQL = "DELETE FROM SanPham WHERE maSP = ?";
    private String findAllSanPhamSQL = "SELECT * FROM SanPham";
    private String findSanPhamByIdSQL = findAllSanPhamSQL + " WHERE maSP = ?";
    private String updateSanPhamSQL = "UPDATE SanPham SET maLSP = ?, tenDU = ?,giaDU = ? WHERE maSP = ?";

        @Override
    public SanPham create(SanPham entity) {
        Object[] values = {
            entity.getLsp(),
            entity.getTenDU(),
            entity.getGiaDU()

        };
        XJdbc.executeUpdate(createsql, values);
        return entity;
    }

    @Override
    public void update(SanPham entity) {
        Object[] values = {
            entity.getLsp(),
            entity.getTenDU(),
            entity.getGiaDU(),
            entity.getMaSP(),
        };
        XJdbc.executeUpdate(updateSanPhamSQL, values);
    }

    @Override
    public void deleteById(Object id) {
        XJdbc.executeUpdate(deleteSanPhamByIdSQL, id);
    }

    @Override
    public List<SanPham> findAll() {
        return XQuery.getBeanList(SanPham.class, findAllSanPhamSQL);
    }

    @Override
    public SanPham findById(Object id) {
        return XQuery.getSingleBean(SanPham.class, findSanPhamByIdSQL, id);
    }
}
