/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package src.poly.cafe.dao;

import java.util.List;

/**
 *
 * @author baoha
 */
public interface CRUDDAO<Entity, ID> {
    Entity create(Entity entity);
    void update(Entity entity);
    void deleteById(ID id);
    List<Entity> findAll();
    Entity findById(ID id);
}
