/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.entityDAO.TheddDao;
import poly.cafe.entity.TheDinhDanh;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;

/**
 *
 * @author Dong Khanh
 */
public class TheDinhDanhimpl implements TheddDao{
    private final String createSql = "INSERT INTO TheDD(maDD, trangThai) VALUES(?, ?)";
    private final String updateSql = "UPDATE TheDD SET trangThai =? WHERE maDD=?";
    private final String deleteByIdSql = "DELETE FROM TheDD WHERE maDD=?";    
    private final String findAllSql = "SELECT * FROM TheDD";
    private final String findByIdSql = findAllSql + " WHERE maDD=?";

    @Override
    public TheDinhDanh create(TheDinhDanh entity) {
        Object[] values = {
            entity.getMaDD(),
            entity.getTrangThai()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(TheDinhDanh entity) {
        Object[] values = {
            
            entity.getTrangThai(),
            entity.getMaDD()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public TheDinhDanh findById(Object id) {
        return XQuery.getSingleBean(TheDinhDanh.class, findByIdSql, id);
    }

    @Override
    public void deleteById(Object id) {
        XJdbc.executeUpdate(deleteByIdSql, id);
    }

    @Override
    public List<TheDinhDanh> findAll() {
        return XQuery.getBeanList(TheDinhDanh.class, findAllSql);
    }
}
