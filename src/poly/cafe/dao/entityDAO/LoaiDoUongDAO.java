/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.entityDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import src.poly.cafe.dao.CRUDDAO;
import poly.cafe.entity.LoaiDoUong;
/**
 *
 * @author baoha
 */
public interface LoaiDoUongDAO extends CRUDDAO<LoaiDoUong, Object>{
    List<LoaiDoUong> list = new ArrayList<>();
}
