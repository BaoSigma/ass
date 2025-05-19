/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.entityDAO.NguoiDungDAO;
import poly.cafe.entity.LoaiDoUong;
import poly.cafe.entity.NguoiDung;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;

/**
 *
 * @author baoha
 */
public class NguoiDungimpl implements NguoiDungDAO{
    private final String createSql = "INSERT INTO NguoiDung(maND, tenND, matKhau, chucVu, maNV) VALUES(?, ?)";
    private final String updateSql = "UPDATE NguoiDung SET matKhau=? WHERE maND=?";
    private final String deleteByIdSql = "DELETE FROM NguoiDung WHERE maND=?";
    private final String findAllSql = "SELECT * FROM NguoiDung ";
    private final String findByIdSql = findAllSql + " WHERE maND=?";
    @Override
    public NguoiDung create(NguoiDung entity) {
        Object[] values = {
            entity.getMaND(),
            entity.getTenND(),
            entity.getMaNV(),
            entity.getMatKhau(),
            entity.getChucVu()
            
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(NguoiDung entity) {
        Object[] values = {
            
            entity.getMatKhau(),
            entity.getMaND(),
        };
        XJdbc.executeUpdate(updateSql,values);
    }

    @Override
    public NguoiDung findById(Object id) {
        return XQuery.getSingleBean(NguoiDung.class, findByIdSql, id);
    }

    @Override
    public void deleteById(Object id) {
        XJdbc.executeUpdate(deleteByIdSql, id);
    }

    
    

    @Override
    public List<NguoiDung> findAll() {
        return XQuery.getBeanList(NguoiDung.class, findAllSql);
    }


}
