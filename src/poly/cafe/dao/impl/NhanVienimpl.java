/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.entityDAO.NhanVienDAO;
import poly.cafe.entity.HoaDon;
import poly.cafe.entity.NhanVien;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;

/**
 *
 * @author baoha
 */
public class NhanVienimpl implements NhanVienDAO{
    private String createsql= "EXEC Insert_NhanVien \n" +
"    @hoTen = ?, \n" +
"    @namSinh = ?, \n" +
"    @sdt = ?, \n" +
"    @email = ?, \n" +
"    @matKhau = ?, \n" +
"    @chucVu = ?";
    private String deleteNhanVienByIdSQL = "DELETE FROM Nhanvien WHERE maNV = ?";
    private String findAllNhanVienSQL = "SELECT * FROM Nhanvien";
    private String findNhanVienByIdSQL = findAllNhanVienSQL + " WHERE maNV = ?";
    private String updateNhanVienSQL = "UPDATE Nhanvien SET hoTen = ?, namSinh = ?, sdt = ?, email = ?, matKhau = ?, chucVu = ? WHERE maNV = ?";

        @Override
    public NhanVien create(NhanVien entity) {
        Object[] values = {
            entity.getMaNV(),
            entity.getHoTen(),
            entity.getNamSinh(),
            entity.getSdt(),
            entity.getEmail(),
            entity.getMatKhau(),
            entity.getChucVu()
        };
        XJdbc.executeUpdate(createsql, values);
        return entity;
    }

    @Override
    public void update(NhanVien entity) {
        Object[] values = {
            entity.getHoTen(),
            entity.getNamSinh(),
            entity.getSdt(),
            entity.getEmail(),
            entity.getMatKhau(),
            entity.getChucVu(),
            entity.getMaNV()
        };
        XJdbc.executeUpdate(updateNhanVienSQL, values);
    }

    @Override
    public void deleteById(Object id) {
        XJdbc.executeUpdate(deleteNhanVienByIdSQL, id);
    }

    @Override
    public List<NhanVien> findAll() {
        return XQuery.getBeanList(NhanVien.class, findAllNhanVienSQL);
    }

    @Override
    public NhanVien findById(Object id) {
        return XQuery.getSingleBean(NhanVien.class, findNhanVienByIdSQL, id);
    }
    
}
