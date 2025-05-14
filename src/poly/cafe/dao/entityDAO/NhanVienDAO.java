/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.entityDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import src.poly.cafe.dao.CRUDDAO;
import poly.cafe.entity.Nhanvien;

/**
 *
 * @author micro
 */
public interface NhanVienDAO extends CRUDDAO<Nhanvien, Object> {    
    List<Nhanvien> list = new ArrayList<>();
    @Override
    public default Nhanvien create(Nhanvien Nv){
        list.add(Nv);
        return Nv;
    }
    
    @Override
    public default void deleteById(Object id){
        list.removeIf(nv -> nv.getMaNV().equals(id));
    }
    
    @Override
    public default void update(Nhanvien entity){
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).getMaNV().equals(entity.getMaNV())){
                list.set(i, entity);
                return;
            }
        }
    }
    
    @Override
    public default List<Nhanvien> findAll(){
        return list;
    }
    
    @Override
    public default Nhanvien findById(Object id) {
        Optional<Nhanvien> Nhanvien = list.stream().filter(Nv -> Nv.getMaNV().equals(id)).findFirst();
        return Nhanvien.orElse(null);
        }
    }