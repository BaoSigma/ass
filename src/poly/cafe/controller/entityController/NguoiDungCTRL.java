/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.controller.entityController;

import poly.cafe.controller.CRUDCTRL;
import poly.cafe.entity.NguoiDung;
import poly.cafe.util.XDialog;

/**
 *
 * @author baoha
 */
public interface NguoiDungCTRL extends CRUDCTRL<NguoiDung>{
    void open();
    void login();
    default void exit(){
        if(XDialog.confirm("Bạn muốn thoát?")){
            System.exit(0);
        }
    }
}
