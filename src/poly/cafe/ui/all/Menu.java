/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package poly.cafe.ui.all;

import com.formdev.flatlaf.FlatLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import poly.cafe.ui.manager.CaLamPanel;
import poly.cafe.ui.manager.ChiTietPanel;
import poly.cafe.ui.manager.HoaDonPanel;
import poly.cafe.ui.manager.NhanVienPanel;
import poly.cafe.ui.manager.PhanLoai;
import poly.cafe.ui.manager.SanPhamPanel;
import poly.cafe.ui.manager.TheDDPanel;



/**
 *
 * @author baoha
 */
public class Menu extends javax.swing.JFrame {
  
    public void addHoverTextEffect(JLabel label, Color normalColor, Color hoverColor) {
        label.setOpaque(true);
        label.setBackground(normalColor);

    label.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            label.setBackground(hoverColor);
            repaint();
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            label.setBackground(normalColor);
            repaint();
        }
    });
}
    
    
    private void initLabelEvents() {
    lblNhanVien.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            setView(new NhanVienPanel());
        }
    });

    lblCalam.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            setView(new CaLamPanel());
        }
    });

    lblThedd.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            setView(new TheDDPanel());
        }
    });

    lblHoaDon.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            setView(new HoaDonPanel());
        }
    });

    lblCT.addMouseListener(new MouseAdapter() {
        @Override
       public void mouseClicked(MouseEvent e) {
            setView(new ChiTietPanel()); // đoán: ICT là chi tiết
        }
    });

    lblSP.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            setView(new SanPhamPanel()); // đoán: ISP là sản phẩm
        }
    });

    lblPhanLoai.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            setView(new PhanLoai());
        }
    });
}

    
   public void setView(JPanel panel) {
    jPanel3.removeAll();
    jPanel3.setLayout(new BorderLayout());
    jPanel3.add(panel);
    jPanel3.revalidate();
    jPanel3.repaint();
}
    int rong = 150;
    int dai = 500;
    /**
     * Creates new form Menu
     */
    public void hover(){
        Color normal = new Color(0, 0, 0, 0);
        Color hover = new Color(255, 255, 255, 180);
        addHoverTextEffect(lblCT, normal, hover);
        addHoverTextEffect(lblCalam, normal, hover);
        addHoverTextEffect(lblDangNhap, normal, hover);
        addHoverTextEffect(lblHoaDon, normal, hover);
        addHoverTextEffect(lblNhanVien, normal, hover);
        addHoverTextEffect(lblPhanLoai, normal, hover);
        addHoverTextEffect(lblSP, normal, hover);
        addHoverTextEffect(lblThedd, normal, hover);
    }
    public Menu() {
        initComponents();
        openmenu();
        initLabelEvents(); // <-- Gọi ở đây
        setView(new NhanVienPanel());
        hover();
        setLocationRelativeTo(null);
    }
    public void openmenu(){
        
        new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i = 0; i<rong;i++){
                    menu.setSize(i,dai);
                    try {
                    Thread.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                
            }
            
        }).start();
    }
    
    public void closemenu(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i = rong;i>0;i--){
                    menu.setSize(i,dai);
                    try {
                    Thread.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                
            }
            
        }).start();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        menu = new poly.cafe.ui.all.back.menuBackGround();
        jLabel2 = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        lblCalam = new javax.swing.JLabel();
        lblThedd = new javax.swing.JLabel();
        lblHoaDon = new javax.swing.JLabel();
        lblCT = new javax.swing.JLabel();
        lblSP = new javax.swing.JLabel();
        lblPhanLoai = new javax.swing.JLabel();
        lblDangNhap = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        btnMinimize = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setUndecorated(true);

        menu.setPreferredSize(new java.awt.Dimension(150, 400));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Menu");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel2.setMaximumSize(new java.awt.Dimension(150, 32));

        lblNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/staff.png"))); // NOI18N
        lblNhanVien.setText("Nhân viên");
        lblNhanVien.setMaximumSize(new java.awt.Dimension(150, 20));
        lblNhanVien.setPreferredSize(new java.awt.Dimension(150, 20));

        lblCalam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCalam.setForeground(new java.awt.Color(255, 255, 255));
        lblCalam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCalam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/time.png"))); // NOI18N
        lblCalam.setText("Ca làm");
        lblCalam.setMaximumSize(new java.awt.Dimension(150, 20));
        lblCalam.setPreferredSize(new java.awt.Dimension(150, 20));

        lblThedd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblThedd.setForeground(new java.awt.Color(255, 255, 255));
        lblThedd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThedd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/thedd.png"))); // NOI18N
        lblThedd.setText("Thẻ đd");
        lblThedd.setMaximumSize(new java.awt.Dimension(150, 20));
        lblThedd.setPreferredSize(new java.awt.Dimension(150, 20));

        lblHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/bill (1).png"))); // NOI18N
        lblHoaDon.setText("Hóa đơn");
        lblHoaDon.setMaximumSize(new java.awt.Dimension(150, 20));
        lblHoaDon.setPreferredSize(new java.awt.Dimension(150, 20));

        lblCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCT.setForeground(new java.awt.Color(255, 255, 255));
        lblCT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/ct.png"))); // NOI18N
        lblCT.setText("Chi tiết HĐ");
        lblCT.setMaximumSize(new java.awt.Dimension(150, 20));
        lblCT.setPreferredSize(new java.awt.Dimension(150, 20));

        lblSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSP.setForeground(new java.awt.Color(255, 255, 255));
        lblSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/sanpham.png"))); // NOI18N
        lblSP.setText("Sản phẩm");

        lblPhanLoai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPhanLoai.setForeground(new java.awt.Color(255, 255, 255));
        lblPhanLoai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhanLoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/phanloaii.png"))); // NOI18N
        lblPhanLoai.setText("Phân loại");

        lblDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        lblDangNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDangNhap.setText("Đăng xuất");
        lblDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDangNhapMouseClicked(evt);
            }
        });

        jSeparator1.setPreferredSize(new java.awt.Dimension(150, 10));

        jSeparator2.setMaximumSize(new java.awt.Dimension(150, 3));
        jSeparator2.setPreferredSize(new java.awt.Dimension(150, 3));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/close.png"))); // NOI18N
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lblCalam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lblThedd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lblHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lblCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jSeparator3)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lblPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lblDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblCT, lblCalam, lblDangNhap, lblHoaDon, lblNhanVien, lblPhanLoai, lblSP, lblThedd});

        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(12, 12, 12)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lblNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblCalam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lblThedd, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSP)
                .addGap(18, 18, 18)
                .addComponent(lblPhanLoai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDangNhap)
                .addGap(16, 16, 16))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/closewin.png"))); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/minimize.png"))); // NOI18N
        btnMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizeActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/menu.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose)
                    .addComponent(btnMinimize)
                    .addComponent(jButton3))
                .addGap(0, 0, 0))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE))
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        closemenu();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void lblDangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangNhapMouseClicked
        // TODO add your handling code here:
        
        dispose();
    }//GEN-LAST:event_lblDangNhapMouseClicked

    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed
        // TODO add your handling code here:
        btnMinimize.setFocusPainted(false);
        btnMinimize.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        setExtendedState(Menu.ICONIFIED); // THU NHỎ TOÀN BỘ NGAY
    }
    });
    }//GEN-LAST:event_btnMinimizeActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        openmenu();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblCT;
    private javax.swing.JLabel lblCalam;
    private javax.swing.JLabel lblDangNhap;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblPhanLoai;
    private javax.swing.JLabel lblSP;
    private javax.swing.JLabel lblThedd;
    private poly.cafe.ui.all.back.menuBackGround menu;
    // End of variables declaration//GEN-END:variables
}
