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
    @Override
    public default LoaiDoUong create(LoaiDoUong Ldu) {
        list.add(Ldu);
        return Ldu;
    }

    @Override
    public default void deleteById(Object id) {
        list.removeIf(ldu -> ldu.getMaLDU().equals(id));
    }

    @Override
    public default void update(LoaiDoUong entity) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getMaLDU().equals(entity.getMaLDU())){
                list.set(i, entity);
                return;
            }
        }
    }

    @Override
    public default List<LoaiDoUong> findAll() {
        return list;
    }

    @Override
    public default LoaiDoUong findById(Object id) {
        Optional<LoaiDoUong> LoaiDoUong = list.stream().filter(ldu -> ldu.getMaLDU().equals(id)).findFirst();
        return LoaiDoUong.orElse(null);
    }
    
    
    
}
