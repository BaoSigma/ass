/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.controller.entityController;

import poly.cafe.controller.CrudController;
import poly.cafe.entity.NhanVien;

/**
 *
 * @author baoha
 */
public interface SignUpCTR {
    void creatSignUp();
    void open();
    void close();
    NhanVien getForm();
}
