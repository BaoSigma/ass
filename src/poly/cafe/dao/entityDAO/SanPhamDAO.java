/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.entityDAO;

import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.CrudDAO;
import poly.cafe.entity.SanPham;

/**
 *
 * @author micro
 */
public interface SanPhamDAO extends CrudDAO<SanPham, Object>{
    List<SanPham> list = new ArrayList<>();    
}
