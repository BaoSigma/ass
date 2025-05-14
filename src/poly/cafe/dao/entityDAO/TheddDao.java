/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.entityDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import poly.cafe.entity.TheDD;
import src.poly.cafe.dao.CRUDDAO;

/**
 *
 * @author baoha
 */
public interface TheddDao extends CRUDDAO<TheDD, Object>{
    List<Thedd> list = new ArrayList<>();
    @Override
    public default Thedd create(Thedd dd) {
        list.add(dd);
        return dd;
    }

    @Override
    public default void deleteById(Object maDD) {
        list.removeIf(dd -> dd.getMaDD().equals(maDD));
    }

    @Override
    public default List<Thedd> findAll() {
        return list;
    }

    @Override
    public default Thedd findById(Object id) {
    Optional<Thedd> Thedd = list.stream().filter(dd -> dd.getMaLDU().equals(id)).findFirst();
        return Thedd.orElse(null);
    }

    @Override
    public default void update(Thedd entity) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getMaDD().equals(entity.getMaDD())){
                list.set(i, entity);
                return;
            }
        }
    }
 
}
