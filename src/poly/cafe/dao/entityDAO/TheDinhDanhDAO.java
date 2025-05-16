/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.entityDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import src.poly.cafe.dao.CRUDDAO;
import poly.cafe.entity.TheDinhDanh;

/**
 *
 * @author micro
 */
public interface TheDinhDanhDAO extends CRUDDAO<TheDinhDanh, Object>{
    List<TheDinhDanh> list = new ArrayList<>();
    @Override
    public default TheDinhDanh create(TheDinhDanh Tdd) {
        list.add(Tdd);
        return Tdd;
    }

    @Override
    public default void deleteById(Object id) {
        list.removeIf(Tdd -> Tdd.getMaDD().equals(id));
    }

    @Override
    public default void update(TheDinhDanh entity) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getMaDD().equals(entity.getMaDD())){
                list.set(i, entity);
                return;
            }
        }
    }

    @Override
    public default List<TheDinhDanh> findAll() {
        return list;
    }

    @Override
    public default TheDinhDanh findById(Object id) {
        Optional<TheDinhDanh> TheDinhDanh = list.stream().filter(Tdd -> Tdd.getMaDD().equals(id)).findFirst();
        return TheDinhDanh.orElse(null);
    }   
}

