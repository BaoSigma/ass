/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.entityDAO.NguoiDungDAO;
import poly.cafe.entity.NguoiDung;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;
import poly.cafe.util.XStr;

/**
 *
 * @author Dong Khanh
 */
public class NguoiDungimpl implements NguoiDungDAO{
    private String createsql = "INSERT INTO NguoiDung(maND, tenND, matKhau, chucVu, maNV) VALUES (?,?,?,?,?)";
    private String deletsql = "DELETE FROM NguoiDung WHERE maND =?";
    private String findallsql = "SELECT * FROM NguoiDung";
    private String  findidsql = findallsql + "WHERE maND = ?";
    private String updatesql = "UPDATE NguoiDung SET tenND = ? WHERE maND = ?";

    @Override
    public NguoiDung create(NguoiDung nd) {
        nd.setMaND(XStr.getKey());
        Object[] values ={
            nd.getMaND(),nd.getTenND(),nd.getMatKhau(),nd.getChucVu(),nd.getMaNV()
        };
        XJdbc.executeUpdate(createsql, values);
        return nd;
    }

    @Override
    public void deleteById(Object maDU) {
       XJdbc.executeUpdate(deletsql, maDU);
    }

    @Override
    public List<NguoiDung> findAll() {
        return XQuery.getBeanList(NguoiDung.class, findallsql);
    }

    @Override
    public NguoiDung findById(Object id) {
        return XQuery.getSingleBean(NguoiDung.class, findidsql, id);
    }
}
