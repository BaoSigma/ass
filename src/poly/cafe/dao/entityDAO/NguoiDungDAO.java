/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.entityDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import poly.cafe.entity.NguoiDung;
import src.poly.cafe.dao.CRUDDAO;

/**
 *
 * @author baoha
 */
public interface NguoiDungDAO extends  CRUDDAO<NguoiDung, Object>{
    List<NguoiDung> list = new ArrayList<>();

}
