/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.entityDAO;
import java.util.List;
import java.util.ArrayList;
import poly.cafe.dao.CrudDAO;
import poly.cafe.entity.HoaDon;
/**
 *
 * @author baoha
 */
public interface HoaDonDAO extends CrudDAO<HoaDon, Object>{
    List<HoaDon> list = new ArrayList<>();
}
