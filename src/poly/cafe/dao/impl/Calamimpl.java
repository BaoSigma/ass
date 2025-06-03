/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.entityDAO.CaLamDAO;
import poly.cafe.entity.CaLam;
import poly.cafe.entity.HoaDon;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;

/**
 *
 * @author baoha
 */
public class Calamimpl implements CaLamDAO{
    private String createsql= "INSERT INTO Calam(Buoi,maNV,hoTen) values(?,?,?)";
    private String deleteCalamByIdSQL = "DELETE FROM Calam WHERE ID = ?";
    private String findAllCalamSQL = " SELECT cl.ID, cl.Buoi, nv.maNV, nv.hoTen from Calam cl INNER JOIN  Nhanvien nv on cl.maNV = nv.maNV";
    private String findCalamByIdSQL = findAllCalamSQL + " WHERE maCL = ?";
    private String updateCalamSQL = "UPDATE Calam SET maNV = ?,Buoi = ?, hoTen = ? WHERE ID = ?";

        @Override
    public CaLam create(CaLam entity) {
        Object[] values = {
            entity.getBuoi(),
            entity.getMaNV(),
            entity.getHoTen()
        };
        XJdbc.executeUpdate(createsql, values);
        return entity;
    }

    @Override
    public void update(CaLam entity) {
        Object[] values = {
            entity.getMaNV(),
            entity.getBuoi(),
            entity.getHoTen(),
            entity.getID()
        };
        XJdbc.executeUpdate(updateCalamSQL, values);
    }

    @Override
    public void deleteById(Object id) {
        XJdbc.executeUpdate(deleteCalamByIdSQL, id);
    }

    @Override
    public List<CaLam> findAll() {
        return XQuery.getBeanList(CaLam.class, findAllCalamSQL);
    }

    @Override
    public CaLam findById(Object id) {
        return XQuery.getSingleBean(CaLam.class, findCalamByIdSQL, id);
    }
    
}
