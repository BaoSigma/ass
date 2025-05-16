/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.entityDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import src.poly.cafe.dao.CRUDDAO;
import poly.cafe.entity.DoanhThu;

/**
 *
 * @author micro
 */
public interface DoanhThuDAO extends CRUDDAO<DoanhThu, Object>{
    List<DoanhThu> list = new ArrayList<>();
    @Override
    public default DoanhThu create(DoanhThu dt) {
        list.add(dt);
        return dt;
    }

    @Override
    public default void deleteById(Object id) {
        list.removeIf(dt -> dt.getMaDT().equals(id));
    }

    @Override
    public default void update(DoanhThu entity) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getMaDT().equals(entity.getMaDT())){
                list.set(i, entity);
                return;
            }
        }
    }

    @Override
    public default List<DoanhThu> findAll() {
        return list;
    }

    @Override
    public default DoanhThu findById(Object id) {
        Optional<DoanhThu> DoanhThu = list.stream().filter(dt -> dt.getMaDT().equals(id)).findFirst();
        return DoanhThu.orElse(null);
    }   
}
