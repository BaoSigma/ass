/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package poly.cafe.ui.manager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import poly.cafe.dao.entityDAO.DoanhThuDAO;
import poly.cafe.dao.impl.DoanhThuimpl;
import poly.cafe.entity.DoanhThuNhanVien;
import poly.cafe.entity.HoaDon;
import poly.cafe.entity.NhanVien;
import poly.cafe.util.XDialog;

/**
 *
 * @author baoha
 */
public class DoanhThuPanel extends javax.swing.JPanel {
    DoanhThuDAO dao = new DoanhThuimpl();
    private JTable activeTable;
    /**
     * Creates new form DoanhThuPanel
     */
    public DoanhThuPanel() {
        initComponents();
            if (!java.beans.Beans.isDesignTime()) {
        hienThiBieuDoDoanhThuTheoNgay();
        FillToTableDT1();
    }
    }
        public void hienThiBieuDoDoanhThuTheoNgay() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        DoanhThuDAO dao = new DoanhThuimpl();
        List<HoaDon> list = dao.DoanhThuTheoNgay();

        for (HoaDon hd : list) {
            String ngay = new SimpleDateFormat("dd-MM-yyyy").format(hd.getNgayTao());
            dataset.addValue(hd.getDoanhThuTheoNgay(), "Doanh thu", ngay);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ doanh thu theo ngày",
                "Ngày", 
                "Doanh thu (VNĐ)", 
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jPanel1.getWidth(), jPanel1.getHeight()));

        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(chartPanel, BorderLayout.CENTER);
        jPanel1.revalidate();
        jPanel1.repaint();
    }
         public void hienThiBieuDoDoanhThuNhanVien() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    DoanhThuDAO dao = new DoanhThuimpl();
    List<DoanhThuNhanVien> list = dao.DoanhThuNhanVien();

    for (DoanhThuNhanVien nv : list) {
        String tenNV = nv.getHoTen() + " (" + nv.getMaNV() + ")";
        dataset.addValue(nv.getDoanhThuTheoNhanVien(), "Doanh thu", tenNV);
    }

    JFreeChart barChart = ChartFactory.createBarChart(
            "Biểu đồ top 5 nhân viên có doanh thu cao nhất",
            "Nhân viên",
            "Doanh thu (VNĐ)",
            dataset,
            PlotOrientation.VERTICAL,
            false, true, false);

    ChartPanel chartPanel = new ChartPanel(barChart);
    chartPanel.setPreferredSize(new Dimension(jPanel5.getWidth(), jPanel5.getHeight()));

    jPanel5.removeAll();
    jPanel5.setLayout(new BorderLayout());
    jPanel5.add(chartPanel, BorderLayout.CENTER);
    jPanel5.revalidate();
    jPanel5.repaint();
}
         public void hienThiBieuDoTongDT() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    DoanhThuDAO dao = new DoanhThuimpl();
    Double tongDT = dao.layTongDoanhThu();

    if (tongDT != null) {
        dataset.addValue(tongDT, "Tổng doanh thu", "Tất cả");
    }

    JFreeChart barChart = ChartFactory.createBarChart(
            "Biểu đồ tổng doanh thu",
            "Tổng",
            "Doanh thu (VNĐ)",
            dataset,
            PlotOrientation.VERTICAL,
            false, true, false);

    ChartPanel chartPanel = new ChartPanel(barChart);
    chartPanel.setPreferredSize(new Dimension(jPanel7.getWidth(), jPanel7.getHeight()));

    jPanel7.removeAll();
    jPanel7.setLayout(new BorderLayout());
    jPanel7.add(chartPanel, BorderLayout.CENTER);
    jPanel7.revalidate();
    jPanel7.repaint();
}
         public void FillToTabledt3() {
    DefaultTableModel model = (DefaultTableModel) tblDT2.getModel(); // Giả sử bảng tên là tblTongDT
    model.setRowCount(0); // Xóa dữ liệu cũ

    DoanhThuDAO dao = new DoanhThuimpl();
    Double tongDoanhThu = dao.layTongDoanhThu(); // Gọi hàm lấy tổng doanh thu

    Object[] rowData = {
        "Tổng doanh thu", 
        tongDoanhThu
    };

    model.addRow(rowData);
}


        public void FillToTableDT1(){
             DefaultTableModel model = (DefaultTableModel) tblDT.getModel();
             List<HoaDon> items = new ArrayList<>();
        model.setRowCount(0);
        items = dao.DoanhThuTheoNgay();
        items.forEach(item -> {
            Object[] rowData = {
                item.getNgayTao(),
                item.getDoanhThuTheoNgay(),
            };
            model.addRow(rowData);
        });
        }
        public void FillToTableDT2(){
             DefaultTableModel model = (DefaultTableModel) tblDT1.getModel();
             List<DoanhThuNhanVien> items = new ArrayList<>();
        model.setRowCount(0);
        items = dao.DoanhThuNhanVien();
        items.forEach(item -> {
            Object[] rowData = {
                item.getMaNV(),
                item.getHoTen(),
                item.getDoanhThuTheoNhanVien()
            };
            model.addRow(rowData);
        });
        }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDT = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDT1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDT2 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        tblDT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Doanh thu theo ngày", "Doanh thu (VNĐ)"
            }
        ));
        jScrollPane2.setViewportView(tblDT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1054, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Doanh thu theo ngày", jPanel4);

        tblDT1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Doanh thu"
            }
        ));
        tblDT1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblDT1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane3.setViewportView(tblDT1);

        jPanel5.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel5AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 193, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1054, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Doanh thu nhân viên", jPanel2);

        tblDT2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Thông tin", "Doanh thu (VNĐ)"
            }
        ));
        tblDT2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblDT2AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane4.setViewportView(tblDT2);

        jPanel7.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel7AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1054, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 182, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tổng doanh thu", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblDT1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblDT1AncestorAdded
        // TODO add your handling code here:
        FillToTableDT2();
    }//GEN-LAST:event_tblDT1AncestorAdded

    private void jPanel5AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel5AncestorAdded
        // TODO add your handling code here:
        hienThiBieuDoDoanhThuNhanVien();
    }//GEN-LAST:event_jPanel5AncestorAdded

    private void tblDT2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblDT2AncestorAdded
        // TODO add your handling code here:
        hienThiBieuDoTongDT();
    }//GEN-LAST:event_tblDT2AncestorAdded

    private void jPanel7AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel7AncestorAdded
        // TODO add your handling code here:
        FillToTabledt3();
    }//GEN-LAST:event_jPanel7AncestorAdded
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblDT;
    private javax.swing.JTable tblDT1;
    private javax.swing.JTable tblDT2;
    // End of variables declaration//GEN-END:variables
}
