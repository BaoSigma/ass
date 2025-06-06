/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.sql.*;



/**
 *
 * @author baoha
 */
public class Orderimpl {
    try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            // Gọi procedure Insert_HoaDon
            String callHD = "{call Insert_HoaDon(?, ?, ?)}";
            CallableStatement hdStmt = conn.prepareCall(callHD);
            hdStmt.setString(1, "NV001"); // mã nhân viên test
            hdStmt.setString(2, "Ghi chú test");
            hdStmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            hdStmt.execute();

            // Lấy mã HD mới nhất
            ResultSet rs = conn.createStatement().executeQuery("SELECT TOP 1 maHD FROM Hoadon ORDER BY maHD DESC");
            rs.next();
            String maHD = rs.getString("maHD");

            // Lưu chi tiết
            for (int i = 0; i < model.getRowCount(); i++) {
                String tenDU = model.getValueAt(i, 0).toString();
                int soLuong = (int) model.getValueAt(i, 1);
                double giaTien = (double) model.getValueAt(i, 3);

                // Lấy mã SP từ tên
                PreparedStatement ps = conn.prepareStatement("SELECT maSP FROM SanPham WHERE tenDU = ?");
                ps.setString(1, tenDU);
                ResultSet rsSP = ps.executeQuery();
                rsSP.next();
                String maSP = rsSP.getString("maSP");

                CallableStatement ctStmt = conn.prepareCall("{call Insert_ChiTietHoaDon(?, ?, ?, ?)}");
                ctStmt.setString(1, maHD);
                ctStmt.setString(2, maSP);
                ctStmt.setInt(3, soLuong);
                ctStmt.setDouble(4, giaTien);
                ctStmt.execute();
            }

            JOptionPane.showMessageDialog(null, "Lưu hóa đơn thành công!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
}
