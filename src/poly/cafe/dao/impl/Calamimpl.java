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
    private String createsql= "EXEC Insert_Calam \n" +
"    @maCL = ?, \n" +
"    @maNV = ?, \n" +
"    @hoTenNV = ?";
    private String deleteCalamByIdSQL = "DELETE FROM Calam WHERE maCL = ?";
    private String findAllCalamSQL = "SELECT * FROM Calam";
    private String findCalamByIdSQL = findAllCalamSQL + " WHERE maCL = ?";
    private String updateCalamSQL = "UPDATE Calam SET maNV = ?, hoTenNV = ? WHERE maCL = ?";

        @Override
    public CaLam create(CaLam entity) {
        Object[] values = {
            entity.getMaCL(),
            entity.getMaNV(),
            entity.getHoTenNV()

        };
        XJdbc.executeUpdate(createsql, values);
        return entity;
    }

    @Override
    public void update(CaLam entity) {
        Object[] values = {
            entity.getMaNV(),
            entity.getHoTenNV(),
            entity.getMaCL(),
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
