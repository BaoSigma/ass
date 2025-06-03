/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.util;

import java.util.Date;
import poly.cafe.entity.NhanVien;

/**
 *
 * @author baoha
 */
public class XAuth {
    public static NhanVien user = NhanVien.builder()
        .maNV("NV001")
        .hoTen("Nguyễn Văn A")
        .namSinh(new Date()) // hoặc new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01")
        .sdt("0911111111")
        .email("a@gmail.com")
        .matKhau("mk123")
        .chucVu("Quản lý")
        .build(); // Biến user sẽ được thay thế sau khi đăng nhập
}

