/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package poly.cafe.ui.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.Beans;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import poly.cafe.dao.entityDAO.ChiTietHoaDonDAO;
import poly.cafe.dao.entityDAO.HoaDonDAO;
import poly.cafe.dao.impl.ChiTietImpl;
import poly.cafe.dao.impl.HoaDonimpl;
import poly.cafe.dao.impl.Orderimpl;
import poly.cafe.entity.ChiTietHoaDon;
import poly.cafe.entity.HoaDon;
import poly.cafe.entity.SanPham;
import poly.cafe.entity.theDD;
import poly.cafe.util.XAuth;
import poly.cafe.util.XDate;
import poly.cafe.util.XDialog;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;

/**
 *
 * @author baoha
 */
public class OrderPanel extends javax.swing.JPanel {
    List<theDD> items = new ArrayList<>();
    @Override
    protected void paintChildren(Graphics g) {
   
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
    
        GradientPaint gp = new GradientPaint(0, 0, Color.decode("#6dd5fa"), 0, height, Color.decode("#ffffff"));
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, width, height, 5, 5);

        g2.dispose();
        super.paintChildren(g); 
    }
    
    public void create() {
    if (!XAuth.isLogin()) {
        XDialog.alert("Bạn cần đăng nhập để tạo hóa đơn!");
        return;
    }

    if (XDialog.confirm("Bạn thực sự muốn thêm hóa đơn?")) {
        HoaDon entity = new HoaDon();
        entity.setMaNV(XAuth.user.getMaNV());
        entity.setGhiChu("");

        Orderimpl dao = new Orderimpl();
        entity = dao.create(entity); // Tạo hóa đơn, lấy mã mới

        ChiTietImpl ctDao = new ChiTietImpl(); // DAO chi tiết
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            String maSP = model.getValueAt(i, 0).toString();
            int soLuong = Integer.parseInt(model.getValueAt(i, 2).toString());
            double giaTien = Double.parseDouble(model.getValueAt(i, 3).toString());

            ctDao.insertChiTietHoaDon(entity.getMaHD(), maSP, soLuong, giaTien);
        }
        capnhattrangthai();
        showBill(entity.getMaHD());
        XDialog.alert("Thêm hóa đơn thành công! Mã hóa đơn: " + entity.getMaHD());
    }
}   
    public void capnhattrangthai(){
        Orderimpl dao = new Orderimpl();
        int index = cbTDD.getSelectedIndex();
        if (index >= 0) {
            theDD the = items.get(index);  // lấy đối tượng tương ứng từ list
            the.setTrangThai("Đang sử dụng");
            dao.update(the);
            filltoCombo();  // load lại dữ liệu
        }
    }
    public void showbillall() {
        HoaDon entity = new HoaDon();
        Orderimpl dao = new Orderimpl();
        ChiTietImpl ctDao = new ChiTietImpl(); // DAO chi tiết
          showBill(entity.getMaHD());
    
    }
    public void filltoCombo(){
        Orderimpl dao = new Orderimpl();
    cbTDD.removeAllItems();
    items = dao.findAll();  // hoặc findAll()
    if (items == null || items.isEmpty()) {
        System.out.println("Không có thẻ TheDD nào trạng thái 'Chưa sử dụng'");
        return; // hoặc có thể thêm item mặc định
    }
    items.forEach(item -> {
        String display = item.getID() + " - " + item.getTrangThai();
        cbTDD.addItem(display);
    });
    }
    public void showBill(String maHD) {
    String ngayHienTai = XDate.format(XDate.now(), "dd/MM/yyyy");
    String therung = cbTDD.getSelectedItem().toString();
    try {
        
        txtaBill.setText("");
        txtaBill.append("\tPOLYCAFFEE\n");
        txtaBill.append("  Nhân viên: " + XAuth.user.getMaNV() + "\n");
        txtaBill.append("  Thẻ định danh: " + therung + "\n");
        txtaBill.append("  Ngày: " + ngayHienTai + "\n");
        txtaBill.append("-------------------------------------------------------\n");
        txtaBill.append(" Tên món     Số lượng     Giá tiền \n");
        txtaBill.append("-------------------------------------------------------\n");

        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String tenMon = model.getValueAt(i, 1).toString();
            int soLuong = Integer.parseInt(model.getValueAt(i, 2).toString());
            double giaTien = Double.parseDouble(model.getValueAt(i, 3).toString());

            txtaBill.append(String.format(" %-10s %5d %12.0f\n", tenMon, soLuong, giaTien));
        }

        txtaBill.append("-------------------------------------------------------\n");
        txtaBill.append(String.format("Tổng tiền: %.0f\n", tinhTongTien()));

    } catch (Exception e) {
        e.printStackTrace();
    }
}

private double tinhTongTien() {
    double tong = 0;
    DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
    for (int i = 0; i < model.getRowCount(); i++) {
        Object val = model.getValueAt(i, 3);
        if (val != null) {
            try {
                tong += Double.parseDouble(val.toString());
            } catch (NumberFormatException e) {
                // Bỏ qua nếu dữ liệu sai kiểu
            }
        }
    }
    return tong;
}

    /**
     * Creates new form OrderPanel
     */
    public OrderPanel() {
        initComponents();
         setOpaque(true);
         if (!Beans.isDesignTime()) {
        filltoCombo();
    }
    }
    public void themMon(String tenDU) {
    SanPham sp = XQuery.getSingleBean(SanPham.class, "SELECT maSP, tenDU, giaDU FROM SanPham WHERE tenDU = ?", tenDU);
    if (sp != null) {
            DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
            boolean found = false;
            for (int i = 0; i < model.getRowCount(); i++) {
                if (Objects.equals(model.getValueAt(i, 1), sp.getTenDU())) {
                    Object val = model.getValueAt(i, 2);
                    int soLuongCu = Integer.parseInt(val.toString());
                    int soLuongMoi = soLuongCu + 1;
                    double thanhTien = soLuongMoi * sp.getGiaDU();
                    model.setValueAt(soLuongMoi, i, 2);
                    model.setValueAt(thanhTien, i, 3);
                    found = true;
                    break;
                }
            }
            if (!found) {
                Object[] row = new Object[]{
                    sp.getMaSP(),
                    sp.getTenDU(),
                    1,
                    sp.getGiaDU()
                };
                model.addRow(row);
            }
       capNhatTongTien();
   }
     }
    public void Xoa(){
        int selectedRow = tblOrder.getSelectedRow();
if (selectedRow != -1) {
    DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
    int soLuong = Integer.parseInt(model.getValueAt(selectedRow, 2).toString());
    if (soLuong > 1) {
        soLuong -= 1;
        model.setValueAt(soLuong, selectedRow, 2);
        double donGia = Double.parseDouble(model.getValueAt(selectedRow, 3).toString()) / (soLuong + 1);
        double thanhTienMoi = soLuong * donGia;
        model.setValueAt(thanhTienMoi, selectedRow, 3);
    } else {
        model.removeRow(selectedRow);
    }
    capNhatTongTien();
} else {
    XDialog.alert("Vui lòng chọn món cần xóa!");
}
    }
    public void capNhatTongTien() {
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
    double tong = 0;

    for (int i = 0; i < model.getRowCount(); i++) {
        Object val = model.getValueAt(i, 3);
        if (val != null) {
            try {
                tong += Double.parseDouble(val.toString());
            } catch (NumberFormatException e) {
                // Có thể log hoặc bỏ qua nếu dữ liệu không đúng kiểu
            }
        }
    }
    
    lblThanhtien.setText(String.format("%.1f", tong));
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnCaffeDen = new javax.swing.JButton();
        btnEpCam = new javax.swing.JButton();
        btnTSsocola = new javax.swing.JButton();
        btnCaffeSua = new javax.swing.JButton();
        btnTSTT = new javax.swing.JButton();
        btnSocola = new javax.swing.JButton();
        btnSodaChanh = new javax.swing.JButton();
        btnTraXanh = new javax.swing.JButton();
        btnEpDuaHau = new javax.swing.JButton();
        btnMatchaLatte = new javax.swing.JButton();
        btnEpTao = new javax.swing.JButton();
        btnEpOi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnKhoaiTay = new javax.swing.JButton();
        btnThitxien = new javax.swing.JButton();
        btnComchien = new javax.swing.JButton();
        btnGa = new javax.swing.JButton();
        btnBanhmi = new javax.swing.JButton();
        btnPho = new javax.swing.JButton();
        btnMixao = new javax.swing.JButton();
        btnBanhuot = new javax.swing.JButton();
        btnChagio = new javax.swing.JButton();
        btnXoi = new javax.swing.JButton();
        btnHutieu = new javax.swing.JButton();
        btnCanh = new javax.swing.JButton();
        lblKhoaiTay = new javax.swing.JLabel();
        lblComChien = new javax.swing.JLabel();
        lblGa = new javax.swing.JLabel();
        lblThitXien = new javax.swing.JLabel();
        lblBanhmi = new javax.swing.JLabel();
        lblPho = new javax.swing.JLabel();
        lblMyxao = new javax.swing.JLabel();
        lblBanhuot = new javax.swing.JLabel();
        lblChagio = new javax.swing.JLabel();
        lblXoi = new javax.swing.JLabel();
        lblHuTieu = new javax.swing.JLabel();
        lblCanh = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        background2 = new poly.cafe.ui.manager.background();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblThanhtien = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        btnDel = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaBill = new javax.swing.JTextArea();
        cbTDD = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));

        btnCaffeDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/Ca-Phe-Den-scaled.jpg"))); // NOI18N
        btnCaffeDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaffeDenActionPerformed(evt);
            }
        });

        btnEpCam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/Nuoc-ep-Cam-Thom-Tuoi.png"))); // NOI18N
        btnEpCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEpCamActionPerformed(evt);
            }
        });

        btnTSsocola.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/CHOCOLATE.jpg"))); // NOI18N
        btnTSsocola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTSsocolaActionPerformed(evt);
            }
        });

        btnCaffeSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/5-cach-pha-ca-phe-sua-tuoi-khong-duong-co.jpeg"))); // NOI18N
        btnCaffeSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaffeSuaActionPerformed(evt);
            }
        });

        btnTSTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/Trà-sữa-Trân-châu-đen-1.png"))); // NOI18N
        btnTSTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTSTTActionPerformed(evt);
            }
        });

        btnSocola.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/socola-1545379578.jpg"))); // NOI18N
        btnSocola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSocolaActionPerformed(evt);
            }
        });

        btnSodaChanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/sodachanh.jpg"))); // NOI18N
        btnSodaChanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSodaChanhActionPerformed(evt);
            }
        });

        btnTraXanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/txkd-op2-928.png"))); // NOI18N
        btnTraXanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraXanhActionPerformed(evt);
            }
        });

        btnEpDuaHau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/whhqu005_1735032007.jpg"))); // NOI18N
        btnEpDuaHau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEpDuaHauActionPerformed(evt);
            }
        });

        btnMatchaLatte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/matchalatte (1).jpg"))); // NOI18N
        btnMatchaLatte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMatchaLatteActionPerformed(evt);
            }
        });

        btnEpTao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/eptao (3).jpg"))); // NOI18N
        btnEpTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEpTaoActionPerformed(evt);
            }
        });

        btnEpOi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/nuocep (1).jpg"))); // NOI18N
        btnEpOi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEpOiActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cà phê đen");
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Cà phê sữa");
        jLabel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nước ép cam");
        jLabel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Trà sữa socola");
        jLabel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Trà sữa TT");
        jLabel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Socola");
        jLabel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Soda chanh");
        jLabel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Trà xanh");
        jLabel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Ép dưa hấu");
        jLabel12.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Matcha latte");
        jLabel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Ép táo");
        jLabel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Ép ổi");
        jLabel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEpDuaHau, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTSTT, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnMatchaLatte, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEpTao, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addComponent(btnEpOi, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnSocola, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSodaChanh, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnTraXanh, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCaffeSua, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnCaffeDen, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEpCam, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTSsocola, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCaffeDen, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEpCam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTSsocola, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCaffeSua, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTraXanh, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSodaChanh, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSocola, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTSTT, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEpOi, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEpTao, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMatchaLatte, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEpDuaHau, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Drink", jPanel1);

        btnKhoaiTay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/Khoai_tay_chien_co_lon (2).png"))); // NOI18N
        btnKhoaiTay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoaiTayActionPerformed(evt);
            }
        });

        btnThitxien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/Bún-Thịt-Nướng-Vietnamese-Grilled-Pork-Takestwoeggs-Final-1.jpg"))); // NOI18N
        btnThitxien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThitxienActionPerformed(evt);
            }
        });

        btnComchien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/AGF-1677-com-chien-duogn-chau.png (1).png"))); // NOI18N
        btnComchien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComchienActionPerformed(evt);
            }
        });

        btnGa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/kinnh-nghiệm-mở-quán-gà-rán-7 (1).jpg"))); // NOI18N
        btnGa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGaActionPerformed(evt);
            }
        });

        btnBanhmi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/quan-banh-mi-thit-nuong-ngon (1).jpg"))); // NOI18N
        btnBanhmi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanhmiActionPerformed(evt);
            }
        });

        btnPho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/PhoBo-e1446825512455 (1).jpg"))); // NOI18N
        btnPho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhoActionPerformed(evt);
            }
        });

        btnMixao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/mi-xao-bo-1 (1).jpg"))); // NOI18N
        btnMixao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMixaoActionPerformed(evt);
            }
        });

        btnBanhuot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/241123-banh-cuon-buffet-poseidon-4 (1).jpg"))); // NOI18N
        btnBanhuot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanhuotActionPerformed(evt);
            }
        });

        btnChagio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/Screenshot 2025-06-04 220028.png"))); // NOI18N
        btnChagio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChagioActionPerformed(evt);
            }
        });

        btnXoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/Screenshot 2025-06-04 215833.png"))); // NOI18N
        btnXoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoiActionPerformed(evt);
            }
        });

        btnHutieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/sddefault (1).jpg"))); // NOI18N
        btnHutieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHutieuActionPerformed(evt);
            }
        });

        btnCanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/Thanh-pham-1-1-5372-1671269525 (1).jpg"))); // NOI18N
        btnCanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanhActionPerformed(evt);
            }
        });

        lblKhoaiTay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKhoaiTay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKhoaiTay.setText("Khoai tây");
        lblKhoaiTay.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        lblKhoaiTay.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lblKhoaiTayAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        lblComChien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblComChien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComChien.setText("Cơm chiên");
        lblComChien.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblGa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblGa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGa.setText("Gà rán");
        lblGa.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblThitXien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblThitXien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThitXien.setText("Thịt xiên");
        lblThitXien.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblBanhmi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBanhmi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBanhmi.setText("Bánh mì");
        lblBanhmi.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblPho.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPho.setText("Phở");
        lblPho.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblMyxao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMyxao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMyxao.setText("Mỳ xào");
        lblMyxao.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblBanhuot.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBanhuot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBanhuot.setText("Bánh ướt");
        lblBanhuot.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblChagio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblChagio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChagio.setText("Chả giò");
        lblChagio.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblXoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblXoi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXoi.setText("Xôi");
        lblXoi.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblHuTieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHuTieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHuTieu.setText("Hủ tiếu");
        lblHuTieu.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblCanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCanh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCanh.setText("Canh");
        lblCanh.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnChagio, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBanhuot, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnMixao, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPho, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBanhmi, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnXoi, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHutieu, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addComponent(btnCanh, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblKhoaiTay, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblThitXien, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblComChien, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblGa, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblBanhuot, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMyxao, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPho, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblBanhmi, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblChagio, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblXoi, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblHuTieu, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCanh, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnKhoaiTay, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThitxien, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnComchien, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGa, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKhoaiTay, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThitxien, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnComchien, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGa, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBanhmi, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPho, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMixao, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBanhuot, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblComChien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKhoaiTay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblThitXien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBanhuot, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMyxao, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPho, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBanhmi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCanh, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHutieu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoi, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChagio, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChagio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblXoi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHuTieu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCanh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(102, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Food", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ORDER");

        javax.swing.GroupLayout background2Layout = new javax.swing.GroupLayout(background2);
        background2.setLayout(background2Layout);
        background2Layout.setHorizontalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        background2Layout.setVerticalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Thành tiền");

        lblThanhtien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblThanhtien.setText("0.0");

        jButton1.setText("Confirm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jSeparator2.setForeground(new java.awt.Color(153, 153, 153));

        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá tiền"
            }
        ));
        jScrollPane1.setViewportView(tblOrder);

        btnDel.setText("Delete");
        btnDel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        btnPrint.setText("print bill");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        txtaBill.setColumns(20);
        txtaBill.setRows(5);
        jScrollPane2.setViewportView(txtaBill);

        cbTDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTDDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTDD, javax.swing.GroupLayout.Alignment.TRAILING, 0, 406, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblThanhtien))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(background2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbTDD, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThanhtien)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnPrint))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(btnDel))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnKhoaiTayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoaiTayActionPerformed
        // TODO add your handling code here:
         themMon("Khoai Tây");
       
    }//GEN-LAST:event_btnKhoaiTayActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        create();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnThitxienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThitxienActionPerformed
        // TODO add your handling code here:
        themMon("Thịt xiên");

    }//GEN-LAST:event_btnThitxienActionPerformed

    private void btnComchienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComchienActionPerformed
        // TODO add your handling code here:
        themMon("Cơm Chiên");

    }//GEN-LAST:event_btnComchienActionPerformed

    private void btnGaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGaActionPerformed
        // TODO add your handling code here:
        themMon("Gà");

    }//GEN-LAST:event_btnGaActionPerformed

    private void btnBanhuotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanhuotActionPerformed
        // TODO add your handling code here:
        themMon("Bánh Mì");

    }//GEN-LAST:event_btnBanhuotActionPerformed

    private void btnMixaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMixaoActionPerformed
        // TODO add your handling code here:
        themMon("Phở");

    }//GEN-LAST:event_btnMixaoActionPerformed

    private void btnPhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhoActionPerformed
        // TODO add your handling code here:
        themMon("Mì Xào");

    }//GEN-LAST:event_btnPhoActionPerformed

    private void btnBanhmiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanhmiActionPerformed
        // TODO add your handling code here:
        themMon("Bánh Ướt");

    }//GEN-LAST:event_btnBanhmiActionPerformed

    private void btnChagioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChagioActionPerformed
        // TODO add your handling code here:
        themMon("Chả Giò");

    }//GEN-LAST:event_btnChagioActionPerformed

    private void btnXoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoiActionPerformed
        // TODO add your handling code here:
        themMon("Xôi");

    }//GEN-LAST:event_btnXoiActionPerformed

    private void btnHutieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHutieuActionPerformed
        // TODO add your handling code here:
        themMon("Hủ Tiếu");

    }//GEN-LAST:event_btnHutieuActionPerformed

    private void btnCanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanhActionPerformed
        // TODO add your handling code here:
        themMon("Canh");
    }//GEN-LAST:event_btnCanhActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:
        Xoa();
    }//GEN-LAST:event_btnDelActionPerformed

    private void lblKhoaiTayAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lblKhoaiTayAncestorAdded
        // TODO add your handling code here:
        
    }//GEN-LAST:event_lblKhoaiTayAncestorAdded

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        showbillall();
    }//GEN-LAST:event_btnPrintActionPerformed

    private void cbTDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTDDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTDDActionPerformed

    private void btnCaffeSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaffeSuaActionPerformed
        // TODO add your handling code here:
        themMon("Cà phê sữa");
    }//GEN-LAST:event_btnCaffeSuaActionPerformed

    private void btnCaffeDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaffeDenActionPerformed
        // TODO add your handling code here:
        themMon("Cà phê đen");
    }//GEN-LAST:event_btnCaffeDenActionPerformed

    private void btnEpCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEpCamActionPerformed
        // TODO add your handling code here:
        themMon("Nước ép cam thơm");
    }//GEN-LAST:event_btnEpCamActionPerformed

    private void btnTSsocolaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTSsocolaActionPerformed
        // TODO add your handling code here:
        themMon("Trà sữa socola");
    }//GEN-LAST:event_btnTSsocolaActionPerformed

    private void btnTSTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTSTTActionPerformed
        // TODO add your handling code here:
        themMon("Trà sữa truyền thống");
    }//GEN-LAST:event_btnTSTTActionPerformed

    private void btnSocolaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSocolaActionPerformed
        // TODO add your handling code here:
        themMon("Ca cao nóng");
    }//GEN-LAST:event_btnSocolaActionPerformed

    private void btnSodaChanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSodaChanhActionPerformed
        // TODO add your handling code here:
        themMon("Soda chanh");
    }//GEN-LAST:event_btnSodaChanhActionPerformed

    private void btnTraXanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraXanhActionPerformed
        // TODO add your handling code here:
        themMon("Trà xanh 0 độ");
    }//GEN-LAST:event_btnTraXanhActionPerformed

    private void btnEpDuaHauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEpDuaHauActionPerformed
        // TODO add your handling code here:
        themMon("Nước ép dưa hấu");
    }//GEN-LAST:event_btnEpDuaHauActionPerformed

    private void btnMatchaLatteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatchaLatteActionPerformed
        // TODO add your handling code here:
        themMon("Matcha latte");
    }//GEN-LAST:event_btnMatchaLatteActionPerformed

    private void btnEpTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEpTaoActionPerformed
        // TODO add your handling code here:
        themMon("Nước ép táo");
    }//GEN-LAST:event_btnEpTaoActionPerformed

    private void btnEpOiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEpOiActionPerformed
        // TODO add your handling code here:
        themMon("Nước ép ổi");
    }//GEN-LAST:event_btnEpOiActionPerformed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private poly.cafe.ui.manager.background background2;
    private javax.swing.JButton btnBanhmi;
    private javax.swing.JButton btnBanhuot;
    private javax.swing.JButton btnCaffeDen;
    private javax.swing.JButton btnCaffeSua;
    private javax.swing.JButton btnCanh;
    private javax.swing.JButton btnChagio;
    private javax.swing.JButton btnComchien;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEpCam;
    private javax.swing.JButton btnEpDuaHau;
    private javax.swing.JButton btnEpOi;
    private javax.swing.JButton btnEpTao;
    private javax.swing.JButton btnGa;
    private javax.swing.JButton btnHutieu;
    private javax.swing.JButton btnKhoaiTay;
    private javax.swing.JButton btnMatchaLatte;
    private javax.swing.JButton btnMixao;
    private javax.swing.JButton btnPho;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSocola;
    private javax.swing.JButton btnSodaChanh;
    private javax.swing.JButton btnTSTT;
    private javax.swing.JButton btnTSsocola;
    private javax.swing.JButton btnThitxien;
    private javax.swing.JButton btnTraXanh;
    private javax.swing.JButton btnXoi;
    private javax.swing.JComboBox<String> cbTDD;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblBanhmi;
    private javax.swing.JLabel lblBanhuot;
    private javax.swing.JLabel lblCanh;
    private javax.swing.JLabel lblChagio;
    private javax.swing.JLabel lblComChien;
    private javax.swing.JLabel lblGa;
    private javax.swing.JLabel lblHuTieu;
    private javax.swing.JLabel lblKhoaiTay;
    private javax.swing.JLabel lblMyxao;
    private javax.swing.JLabel lblPho;
    private javax.swing.JLabel lblThanhtien;
    private javax.swing.JLabel lblThitXien;
    private javax.swing.JLabel lblXoi;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTextArea txtaBill;
    // End of variables declaration//GEN-END:variables
}
