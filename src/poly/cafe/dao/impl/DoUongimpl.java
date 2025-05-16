/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.entityDAO.DoUongDAO;
import poly.cafe.entity.DoUong;

import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;
import poly.cafe.util.XStr;

/**
 *
 * @author baoha
 */
public class DoUongimpl implements DoUongDAO{
    private String createsql = "INSERT INTO DoUong(maDU, tenDU,anhDoUong,size,maLDU) VALUES (?,?,?,?,?)";
    private String deletsql = "DELETE FROM DoUong WHERE maDU =?";
    private String findallsql = "SELECT * FROM DoUong";
    private String  findidsql = findallsql + "WHERE maDU = ?";
    private String updatesql = "UPDATE DoUong SET tenDU = ? WHERE maDU = ?";

    @Override
    public DoUong create(DoUong du) {
        du.setMaDU(XStr.getKey());
        Object[] values ={
            du.getMaDU(),du.getTen(),du.getAnhDoUong(),du.getSize(),du.getMaLDU()

         };
        XJdbc.executeUpdate(createsql, values);
        return du;
    }

    @Override
    public void deleteById(Object maDU) {
       XJdbc.executeUpdate(deletsql, maDU);
    }

    @Override
    public List<DoUong> findAll() {
        return XQuery.getBeanList(DoUong.class, findallsql);
    }

    @Override
    public DoUong findById(Object id) {
        return XQuery.getSingleBean(DoUong.class, findidsql, id);
    }
}
