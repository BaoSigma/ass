package poly.cafe.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.prefs.Preferences;
import poly.cafe.entity.NhanVien;

public class XAuth {
    public static NhanVien user = null; // Không hardcode user nữa

    public static void clear() {
        user = null;
    }

    public static boolean isLogin() {
        return user != null;
    }

    public static boolean isManager() {
        return user != null && "Quản lý".equalsIgnoreCase(user.getChucVu());
    }

    public static boolean isStaff() {
        return user != null && "Nhân viên".equalsIgnoreCase(user.getChucVu());
    }

    public static boolean isViewer() {
        return user != null && "Phục vụ".equalsIgnoreCase(user.getChucVu());
    }

    private static final Preferences prefs = Preferences.userRoot().node("PolyLogin");

    public static void save() {
        if (user != null) {
            prefs.put("maNV", safe(user.getMaNV()));
            prefs.put("hoTen", safe(user.getHoTen()));
            prefs.put("sdt", safe(user.getSdt()));
            prefs.put("email", safe(user.getEmail()));
            prefs.put("matKhau", safe(user.getMatKhau()));
            prefs.put("chucVu", safe(user.getChucVu()));
            
            String namSinhStr = "";
            if (user.getNamSinh() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                namSinhStr = sdf.format(user.getNamSinh());
            }
            prefs.put("namSinh", namSinhStr);
        }
    }

    public static void load() {
        String maNV = prefs.get("maNV", null);
        if (maNV == null || maNV.isEmpty()) {
            user = null;
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date namSinh;
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

    private static String safe(String value) {
        return value == null ? "" : value;
    }
}
