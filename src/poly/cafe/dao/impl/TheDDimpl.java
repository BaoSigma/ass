/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.entityDAO.theDDDAO;
import poly.cafe.entity.HoaDon;
import poly.cafe.entity.theDD;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;

/**
 *
 * @author baoha
 */
public class TheDDimpl implements theDDDAO{
    private String createsql= "EXEC Insert_TheDD \n" +
"    @trangThai = ?, \n" +
"    @maHD = ?";
    private String deleteTheDDByIdSQL = "DELETE FROM TheDD WHERE ID = ?";
private String findAllTheDDSQL = "SELECT * FROM TheDD";
private String findTheDDByIdSQL = findAllTheDDSQL + " WHERE ID = ?";
private String updateTheDDSQL = "UPDATE TheDD SET trangThai = ?, maHD = ? WHERE ID = ?";

        @Override
    public theDD create(theDD entity) {
        Object[] values = {
            entity.getID(),
            entity.getTrangThai(),
            entity.getHd(),
        };
        XJdbc.executeUpdate(createsql, values);
        return entity;
    }

    @Override
    public void update(theDD entity) {
        Object[] values = {
            entity.getTrangThai(),
            entity.getHd(),
            entity.getID()
        };
        XJdbc.executeUpdate(updateTheDDSQL, values);
    }

    @Override
    public void deleteById(Object id) {
        XJdbc.executeUpdate(deleteTheDDByIdSQL, id);
    }

    @Override
    public List<theDD> findAll() {
        return XQuery.getBeanList(theDD.class, findAllTheDDSQL);
    }

    @Override
    public theDD findById(Object id) {
        return XQuery.getSingleBean(theDD.class, findTheDDByIdSQL, id);
    }
    
}
