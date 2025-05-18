/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.entityDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import poly.cafe.entity.Nhanvien;
import src.poly.cafe.dao.CRUDDAO;

/**
 *
 * @author baoha
 */
public interface NhanVienDAO extends CRUDDAO<Nhanvien, Object>{
    List<Nhanvien> list = new ArrayList<>();
    @Override
    public default Nhanvien create(Nhanvien Ldu) {
        list.add(Ldu);
        return Ldu;
    }

    @Override
    public default void deleteById(Object id) {
        list.removeIf(ldu -> ldu.getMaNV().equals(id));
    }

    @Override
    public default void update(Nhanvien entity) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getMaNV().equals(entity.getMaNV())){
                list.set(i, entity);
                return;
            }
        }
    }

    @Override
    public default List<Nhanvien> findAll() {
        return list;
    }

    @Override
    public default Nhanvien findById(Object id) {
        Optional<Nhanvien> LoaiDoUong = list.stream().filter(ldu -> ldu.getMaNV().equals(id)).findFirst();
        return LoaiDoUong.orElse(null);
    }
}
