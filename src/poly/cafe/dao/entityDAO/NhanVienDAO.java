/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.entityDAO;

import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.CrudDAO;
import poly.cafe.entity.NhanVien;

/**
 *
 * @author micro
 */
public interface NhanVienDAO extends CrudDAO<NhanVien, Object>{
    List<NhanVien> list = new ArrayList<>();    
}
