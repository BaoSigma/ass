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
    @Override
    public default NguoiDung create(NguoiDung Ldu) {
        list.add(Ldu);
        return Ldu;
    }

    @Override
    public default void deleteById(Object id) {
        list.removeIf(ldu -> ldu.getMaND().equals(id));
    }

    @Override
    public default void update(NguoiDung entity) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getMaND().equals(entity.getMaND())){
                list.set(i, entity);
                return;
            }
        }
    }

    @Override
    public default List<NguoiDung> findAll() {
        return list;
    }

    @Override
    public default NguoiDung findById(Object id) {
        Optional<NguoiDung> LoaiDoUong = list.stream().filter(ldu -> ldu.getTenND().equals(id)).findFirst();
        return LoaiDoUong.orElse(null);
    }
}
