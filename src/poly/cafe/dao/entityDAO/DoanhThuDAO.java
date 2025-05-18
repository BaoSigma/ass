/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.entityDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import poly.cafe.entity.DoanhThu;
import src.poly.cafe.dao.CRUDDAO;

/**
 *
 * @author baoha
 */
public interface DoanhThuDAO extends CRUDDAO<DoanhThu, Object>{
    List<DoanhThu> list = new ArrayList<>();
    @Override
    public default DoanhThu create(DoanhThu Ldu) {
        list.add(Ldu);
        return Ldu;
    }

    @Override
    public default void deleteById(Object id) {
        list.removeIf(ldu -> ldu.getMaDT().equals(id));
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
        Optional<DoanhThu> LoaiDoUong = list.stream().filter(ldu -> ldu.getMaDT().equals(id)).findFirst();
        return LoaiDoUong.orElse(null);
    }
}
