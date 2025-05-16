/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.entityDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import src.poly.cafe.dao.CRUDDAO;
import poly.cafe.entity.NguoiDung;

/**
 *
 * @author micro
 */
public interface NguoiDungDAO extends CRUDDAO<NguoiDung, Object> {    
    List<NguoiDung> list = new ArrayList<>();
    @Override
    public default NguoiDung create(NguoiDung nd){
        list.add(nd);
        return nd;
    }
    
    @Override
    public default void deleteById(Object id){
        list.removeIf(nd -> nd.getMaND().equals(id));
    }
    
    @Override
    public default void update(NguoiDung entity){
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).getMaND().equals(entity.getMaND())){
                list.set(i, entity);
                return;
            }
        }
    }
    
    @Override
    public default List<NguoiDung> findAll(){
        return list;
    }
    
    @Override
    public default NguoiDung findById(Object id) {
        Optional<NguoiDung> NguoiDung = list.stream().filter(nd -> nd.getMaND().equals(id)).findFirst();
        return NguoiDung.orElse(null);
        }
    }
