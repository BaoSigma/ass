/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.prefs.Preferences;
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
    public static void clear() {
        user = null;
    }

    public static boolean isLogin() {
        return user != null;
    }

    public static boolean isManager() {
        return user != null && user.getChucVu().equalsIgnoreCase("Quản lý");
    }

    public static boolean isStaff() {
        return user != null && user.getChucVu().equalsIgnoreCase("Nhân viên");
    }

    public static boolean isViewer() {
        return user != null && user.getChucVu().equalsIgnoreCase("Phục vụ");
    }
    private static Preferences prefs = Preferences.userRoot().node("PolyLogin");
    public static void save() {
       if (user != null) {
        prefs.put("maNV", user.getMaNV() == null ? "" : user.getMaNV());
        prefs.put("hoTen", user.getHoTen() == null ? "" : user.getHoTen());
        prefs.put("sdt", user.getSdt() == null ? "" : user.getSdt());
        prefs.put("email", user.getEmail() == null ? "" : user.getEmail());
        prefs.put("matKhau", user.getMatKhau() == null ? "" : user.getMatKhau());
        prefs.put("chucVu", user.getChucVu() == null ? "" : user.getChucVu());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String namSinhStr = "";
        if (user.getNamSinh() != null) {
            namSinhStr = sdf.format(user.getNamSinh());
        }
        prefs.put("namSinh", namSinhStr);
    }
    }
    public static void load() {
        String maNV = prefs.get("maNV", null);
        if (maNV == null) {
            user = null;  // chưa lưu gì
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date namSinh = null;
        try {
            namSinh = sdf.parse(prefs.get("namSinh", "2000-01-01"));
        } catch (ParseException e) {
            namSinh = new Date();
        }

        user = NhanVien.builder()
                .maNV(maNV)
                .hoTen(prefs.get("hoTen", ""))
                .namSinh(namSinh)
                .sdt(prefs.get("sdt", ""))
                .email(prefs.get("email", ""))
                .matKhau(prefs.get("matKhau", ""))
                .chucVu(prefs.get("chucVu", ""))
                .build();
    }
}

