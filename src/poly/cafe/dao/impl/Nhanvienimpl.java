/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.entityDAO.NhanVienDAO;
import poly.cafe.entity.LoaiDoUong;
import poly.cafe.entity.Nhanvien;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;

/**
 *
 * @author baoha
 */
public class Nhanvienimpl implements NhanVienDAO{
private final String createSql = "INSERT INTO NhanVien(maNV, hoVaTen,Namsinh,anh,maDT) VALUES(?,?,?,?, ?)";
    private final String updateSql = "UPDATE NhanVien SET hoVaTen=? WHERE maNV=?";
    private final String deleteByIdSql = "DELETE FROM NhanVien WHERE maNV=?";
    private final String findAllSql = "SELECT * FROM NhanVien";
    private final String findByIdSql = findAllSql + " WHERE hoVaTen=?";

    @Override
    public Nhanvien create(Nhanvien entity) {
        Object[] values = {
            entity.getMaNV(),
            entity.getHovaTen(),
            entity.getNamSinh(),
            entity.getAnh(),
            entity.getMaDT()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(Nhanvien entity) {
        Object[] values = {
            entity.getHovaTen(),
            entity.getMaNV()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public Nhanvien findById(Object id) {
        return XQuery.getSingleBean(Nhanvien.class, findByIdSql, id);
    }

    @Override
    public void deleteById(Object id) {
        XJdbc.executeUpdate(deleteByIdSql, id);
    }

    
    

    @Override
    public List<Nhanvien> findAll() {
        return XQuery.getBeanList(Nhanvien.class, findAllSql);
    }

}
