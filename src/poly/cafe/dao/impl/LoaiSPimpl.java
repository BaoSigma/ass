/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.entityDAO.LoaiSanPhamDAO;
import poly.cafe.entity.HoaDon;
import poly.cafe.entity.LoaiSanPham;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;

/**
 *
 * @author baoha
 */
public class LoaiSPimpl implements LoaiSanPhamDAO{
    private String createsql= "EXEC Insert_LoaiSanPham \n" +
"    @tenLDU = ?";
    private String deleteLoaiSanPhamByIdSQL = "DELETE FROM LoaiSanPham WHERE maLSP = ?";
    private String findAllLoaiSanPhamSQL = "SELECT * FROM LoaiSanPham";
    private String findLoaiSanPhamByIdSQL = findAllLoaiSanPhamSQL + " WHERE maLSP = ?";
    private String updateLoaiSanPhamSQL = "UPDATE LoaiSanPham SET tenLDU = ? WHERE maLSP = ?";

        @Override
    public LoaiSanPham create(LoaiSanPham entity) {
        Object[] values = {
            entity.getMaLSP(),
            entity.getTenLDU()
            
        };
        XJdbc.executeUpdate(createsql, values);
        return entity;
    }

    @Override
    public void update(LoaiSanPham entity) {
        Object[] values = {
            entity.getTenLDU(),
            entity.getMaLSP()

        };
        XJdbc.executeUpdate(updateLoaiSanPhamSQL, values);
    }

    @Override
    public void deleteById(Object id) {
        XJdbc.executeUpdate(deleteLoaiSanPhamByIdSQL, id);
    }

    @Override
    public List<LoaiSanPham> findAll() {
        return XQuery.getBeanList(LoaiSanPham.class, findAllLoaiSanPhamSQL);
    }

    @Override
    public LoaiSanPham findById(Object id) {
        return XQuery.getSingleBean(LoaiSanPham.class, findLoaiSanPhamByIdSQL, id);
    }
    
}
