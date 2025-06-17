/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package poly.cafe.ui.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.beans.Beans;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import poly.cafe.controller.entityController.OrderController;
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
public class OrderPanel extends javax.swing.JPanel implements OrderController{
    List<theDD> items = new ArrayList<>();
    List<SanPham> SanPham = new ArrayList<>();
    Orderimpl dao = new Orderimpl();
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
    items = dao.findAll(); 
    if (items == null || items.isEmpty()) {
        System.out.println("Không có thẻ TheDD nào trạng thái 'Chưa sử dụng'");
        return; 
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

private double  tinhTongTien() {
    DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
    double tong = 0;
    for (int i = 0; i < model.getRowCount(); i++) {
        tong += (double) model.getValueAt(i, 3);
    }
    lblTongTien.setText(String.valueOf(tong));
    return tong;
}

    /**
     * Creates new form OrderPanel
     */
    public OrderPanel() {
        initComponents();
         setOpaque(true);
        DefaultTableModel modelDrink = new DefaultTableModel(
        new Object[]{"Mã sản phẩm", "Tên nước", "Giá tiền", "Ảnh"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblDrink.setModel(modelDrink);

        DefaultTableModel modelFood = new DefaultTableModel(
            new Object[]{"Mã sản phẩm", "Tên món", "Giá tiền", "Ảnh"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblFOOD.setModel(modelFood);
        DefaultTableModel modelOrder = new DefaultTableModel(
            new Object[]{"Mã sản phẩm", "Tên sản phẩm", "số lượng", "Giá tiền"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblOrder.setModel(modelOrder);
    }
    private void themVaoOrder(String maSP, String tenSP, double gia) {
    boolean found = false;
    DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();

    for (int i = 0; i < model.getRowCount(); i++) {
        if (model.getValueAt(i, 0).toString().equals(maSP)) {
            int soLuong = (int) model.getValueAt(i, 2);
            soLuong++;
            model.setValueAt(soLuong, i, 2);
            model.setValueAt(gia * soLuong, i, 3);
            found = true;
            break;
        }
    }

    if (!found) {
        Object[] rowData = { maSP, tenSP, 1, gia };
        model.addRow(rowData);
    }

    tinhTongTien();
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
        }
    }
}

lblTongTien.setText(String.format("%.1f", tong));

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
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDrink = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblFOOD = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        background2 = new poly.cafe.ui.manager.background();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
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

        tblDrink.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên nước", "Giá tiền", "Ảnh"
            }
        ));
        tblDrink.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblDrinkAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblDrink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDrinkMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDrink);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Drink", jPanel1);

        tblFOOD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên món", "Giá tiền", "Ảnh"
            }
        ));
        tblFOOD.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblFOODAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblFOOD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFOODMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblFOOD);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
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

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongTien.setText("0.0");

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
        tblOrder.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblOrderAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
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
                .addComponent(lblTongTien))
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
                    .addComponent(lblTongTien)
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
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        create();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:
        Xoa();
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        showbillall();
    }//GEN-LAST:event_btnPrintActionPerformed

    private void cbTDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTDDActionPerformed
        // TODO add your handling code here:
        filltoCombo();
    }//GEN-LAST:event_cbTDDActionPerformed

    private void tblDrinkAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblDrinkAncestorAdded
        // TODO add your handling code here:
        fillToTableDrink();
    }//GEN-LAST:event_tblDrinkAncestorAdded

    private void tblDrinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDrinkMouseClicked
        // TODO add your handling code here:
        int row = tblDrink.getSelectedRow();
        if (row != -1) {
            String maSP = tblDrink.getValueAt(row, 0).toString();
            String tenSP = tblDrink.getValueAt(row, 1).toString();
            double gia = (double) tblDrink.getValueAt(row, 2);
            
            themVaoOrder(maSP, tenSP, gia);
        }
    }//GEN-LAST:event_tblDrinkMouseClicked

    private void tblFOODMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFOODMouseClicked
        // TODO add your handling code here:
        int row = tblFOOD.getSelectedRow();
        if (row != -1) {
            String maSP = tblFOOD.getValueAt(row, 0).toString();
            String tenSP = tblFOOD.getValueAt(row, 1).toString();
            double gia = (double) tblFOOD.getValueAt(row, 2);
            
            themVaoOrder(maSP, tenSP, gia);
        }
    }//GEN-LAST:event_tblFOODMouseClicked

    private void tblFOODAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblFOODAncestorAdded
        // TODO add your handling code here:
        fillToTableFood();
    }//GEN-LAST:event_tblFOODAncestorAdded

    private void tblOrderAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblOrderAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblOrderAncestorAdded

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private poly.cafe.ui.manager.background background2;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnPrint;
    private javax.swing.JComboBox<String> cbTDD;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tblDrink;
    private javax.swing.JTable tblFOOD;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTextArea txtaBill;
    // End of variables declaration//GEN-END:variables



    @Override
    public void fillToTableDrink() {
        DefaultTableModel model = (DefaultTableModel) tblDrink.getModel();
        model.setRowCount(0);
        
        tblDrink.setRowHeight(160);
        TableColumnModel columnModel = tblDrink.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100); 
        columnModel.getColumn(1).setPreferredWidth(200); 
        columnModel.getColumn(2).setPreferredWidth(100); 
        columnModel.getColumn(3).setPreferredWidth(200);
        SanPham = dao.findDrink();
        SanPham.forEach(item -> {
             String imagePath = "src/poly/cafe/icons/" + item.getHinhAnh();
              ImageIcon icon = new ImageIcon(imagePath);
                Image scaledImage = icon.getImage().getScaledInstance(194, 149, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
            Object[] rowData = {
                item.getMaSP(),
                item.getTenDU(),
                item.getGiaDU(),
                scaledIcon,
                false
            };
            model.addRow(rowData);
        });
        tblDrink.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof ImageIcon) {
            JLabel lbl = new JLabel();
            lbl.setIcon((ImageIcon) value);
            lbl.setHorizontalAlignment(JLabel.CENTER);
            return lbl;
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
});
    }

    @Override
    public void fillToTableFood() {
        DefaultTableModel model = (DefaultTableModel) tblFOOD.getModel();
        model.setRowCount(0);
        tblFOOD.setRowHeight(160);
        TableColumnModel columnModel = tblFOOD.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100); 
        columnModel.getColumn(1).setPreferredWidth(200); 
        columnModel.getColumn(2).setPreferredWidth(100); 
        columnModel.getColumn(3).setPreferredWidth(200);
        SanPham = dao.findFood();
        SanPham.forEach(item -> {
             String imagePath = "src/poly/cafe/icons/" + item.getHinhAnh();
              ImageIcon icon = new ImageIcon(imagePath);
                Image scaledImage = icon.getImage().getScaledInstance(194, 149, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
            Object[] rowData = {
                item.getMaSP(),
                item.getTenDU(),
                item.getGiaDU(),
                scaledIcon,
                false
            };
            model.addRow(rowData);
        });
        tblFOOD.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if(value instanceof ImageIcon) {
            JLabel lbl = new JLabel();
            lbl.setIcon((ImageIcon) value);
            lbl.setHorizontalAlignment(JLabel.CENTER);
            return lbl;
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
});
    }
    
}
