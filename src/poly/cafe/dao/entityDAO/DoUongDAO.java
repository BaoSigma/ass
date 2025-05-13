/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.entityDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import poly.cafe.entity.DoUong;
import src.poly.cafe.dao.CRUDDAO;

/**
 *
 * @author baoha
 */
public interface DoUongDAO extends CRUDDAO<DoUong, Object>{
    List<DoUong> list = new ArrayList<>();
    @Override
    public default DoUong create(DoUong du) {
        list.add(du);
        return du;
    }

    @Override
    public default void deleteById(Object maDU) {
        list.removeIf(du -> du.getMaDU().equals(maDU));
    }

    @Override
    public default List<DoUong> findAll() {
        return list;
    }

    @Override
    public default DoUong findById(Object id) {
    Optional<DoUong> DoUong = list.stream().filter(du -> du.getMaLDU().equals(id)).findFirst();
        return DoUong.orElse(null);
    }

    @Override
    public default void update(DoUong entity) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getMaDU().equals(entity.getMaDU())){
                list.set(i, entity);
                return;
            }
        }
    }
 
}
