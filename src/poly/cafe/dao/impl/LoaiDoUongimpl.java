/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.entityDAO.LoaiDoUongDAO;
import poly.cafe.entity.LoaiDoUong;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;

/**
 *
 * @author baoha
 */
public class LoaiDoUongimpl implements LoaiDoUongDAO{
    private final String createSql = "INSERT INTO LoaiDoUong(maLDU, ten) VALUES(?, ?)";
    private final String updateSql = "UPDATE LoaiDoUong SET ten=? WHERE maLDU=?";
    private final String deleteByIdSql = "DELETE FROM LoaiDoUong WHERE maLDU=?";
    
    private final String findAllSql = "SELECT * FROM LoaiDoUong";
    private final String findByIdSql = findAllSql + " WHERE ten=?";

    @Override
    public LoaiDoUong create(LoaiDoUong entity) {
        Object[] values = {
            entity.getMaLDU(),
            entity.getTen()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(LoaiDoUong entity) {
        Object[] values = {
            entity.getMaLDU(),
            entity.getTen()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public LoaiDoUong findById(Object id) {
        return XQuery.getSingleBean(LoaiDoUong.class, findByIdSql, id);
    }

    @Override
    public void deleteById(Object id) {
        XJdbc.executeUpdate(deleteByIdSql, id);
    }

    
    

    @Override
    public List<LoaiDoUong> findAll() {
        return XQuery.getBeanList(LoaiDoUong.class, findAllSql);
    }


}
