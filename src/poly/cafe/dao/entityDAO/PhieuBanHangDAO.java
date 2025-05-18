/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.entityDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import poly.cafe.entity.PhieuBanHang;
import src.poly.cafe.dao.CRUDDAO;

/**
 *
 * @author baoha
 */
public interface PhieuBanHangDAO extends CRUDDAO<PhieuBanHang, Object>{
    List<PhieuBanHang> list = new ArrayList<>();
    @Override
    public default PhieuBanHang create(PhieuBanHang Ldu) {
        list.add(Ldu);
        return Ldu;
    }

    @Override
    public default void deleteById(Object id) {
        list.removeIf(ldu -> ldu.getMaPhieu().equals(id));
    }

    @Override
    public default void update(PhieuBanHang entity) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getMaPhieu().equals(entity.getMaPhieu())){
                list.set(i, entity);
                return;
            }
        }
    }

    @Override
    public default List<PhieuBanHang> findAll() {
        return list;
    }

    @Override
    public default PhieuBanHang findById(Object id) {
        Optional<PhieuBanHang> PhieuBanHang = list.stream().filter(ldu -> ldu.getMaPhieu().equals(id)).findFirst();
        return PhieuBanHang.orElse(null);
    }
}
