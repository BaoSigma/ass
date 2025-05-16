/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.entityDAO.ChiTietPhieuBanDAO;
import poly.cafe.entity.ChiTietPhieuBan;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;
import poly.cafe.util.XStr;

/**
 *
 * @author Dong Khanh
 */
public class ChiTietPhieuBanimpl implements ChiTietPhieuBanDAO{
    private String createsql = "INSERT INTO ChiTietPhieuBan(ID, maDU, gia) VALUES (?,?,?)";
    private String deletsql = "DELETE FROM ChiTietPhieuBan WHERE ID =?";
    private String findallsql = "SELECT * FROM ChiTietPhieuBan";
    private String  findidsql = findallsql + "WHERE ID = ?";
    private String updatesql = "UPDATE ChiTietPhieuBan SET maDU = ? WHERE maDU = ?";

    @Override
    public ChiTietPhieuBan create(ChiTietPhieuBan ctpb) {
        ctpb.setMaDU(XStr.getKey());
        Object[] values ={
            ctpb.getID(),ctpb.getGia(),ctpb.getDu()
        };
        XJdbc.executeUpdate(createsql, values);
        return ctpb;
    }

    @Override
    public void deleteById(Object maDU) {
       XJdbc.executeUpdate(deletsql, maDU);
    }

    @Override
    public List<ChiTietPhieuBan> findAll() {
        return XQuery.getBeanList(ChiTietPhieuBan.class, findallsql);
    }

    @Override
    public ChiTietPhieuBan findById(Object id) {
        return XQuery.getSingleBean(ChiTietPhieuBan.class, findidsql, id);
    }
}
