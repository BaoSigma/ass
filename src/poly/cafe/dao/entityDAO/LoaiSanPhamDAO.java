/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.entityDAO;

import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.CrudDAO;
import poly.cafe.entity.LoaiSanPham;

/**
 *
 * @author micro
 */
public interface LoaiSanPhamDAO extends CrudDAO<LoaiSanPham, Object>{
    List<LoaiSanPham> list = new ArrayList<>();    
}
