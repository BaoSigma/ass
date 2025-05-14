/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.entityDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import src.poly.cafe.dao.CRUDDAO;
import poly.cafe.entity.ChiTietPhieuBan;

/**
 *
 * @author micro
 */
public interface ChiTietPhieuBanDAO extends CRUDDAO<ChiTietPhieuBan, Object>{
    List<ChiTietPhieuBan> list = new ArrayList<>();
    @Override
    public default ChiTietPhieuBan create(ChiTietPhieuBan du) {
        list.add(du);
        return du;
    }
    
    @Override
    public default void deleteById(Object id){
        list.removeIf(ctpb -> ctpb.getMaDU().equals(id));
    }
    
    @Override
    public default void update(ChiTietPhieuBan entity){
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).getMaDU().equals(entity.getMaDU())){
                list.set(i, entity);
                return;
            }
        }
    }
    
    @Override
    public default List<ChiTietPhieuBan> findAll(){
        return list;
    }
    
    @Override
    public default ChiTietPhieuBan findById(Object id){
        Optional<ChiTietPhieuBan> ChiTietPhieuBan = list.stream().filter(ctpb -> ctpb.getMaDU().equals(id)).findFirst();
        return ChiTietPhieuBan.orElse(null);
    }
}