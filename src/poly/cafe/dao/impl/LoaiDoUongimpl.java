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
import poly.cafe.util.XStr;

/**
 *
 * @author baoha
 */
public class LoaiDoUongimpl implements LoaiDoUongDAO{
    private String createsql = "INSERT INTO LoaiDoUong(maLDU, ten) VALUES (?,?)";
    private String deletsql = "DELETE FROM DoUong WHERE maLDU =?";
    private String findallsql = "SELECT * FROM LoaiDoUong";
    private String  findidsql = findallsql + "WHERE maLDU = ?";
    private String updatesql = "UPDATE DoUong SET ten = ? WHERE maLDU = ?";
    @Override
    public LoaiDoUong create(LoaiDoUong ldu) {
        ldu.setMaLDU(XStr.getKey());
        Object[] values ={
            ldu.getMaLDU(),ldu.getTen()
         };
        XJdbc.executeUpdate(createsql, values);
        return ldu;
    }

    @Override
    public void deleteById(Object MaLDU) {
       XJdbc.executeUpdate(deletsql, MaLDU);
    }

    @Override
    public List<LoaiDoUong> findAll() {
        return XQuery.getBeanList(LoaiDoUong.class, findallsql);
    }

    @Override
    public LoaiDoUong findById(Object id) {
        return XQuery.getSingleBean(LoaiDoUong.class, findidsql, id);
    }
}
