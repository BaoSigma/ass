/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package poly.cafe.ui.all;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import poly.cafe.controller.entityController.LoginCTR;
import poly.cafe.dao.entityDAO.NhanVienDAO;
import poly.cafe.dao.impl.LoginandSignupimpl;
import poly.cafe.dao.impl.NhanVienimpl;
import poly.cafe.entity.NhanVien;
import poly.cafe.util.XAuth;
import poly.cafe.util.XDialog;

/**
 *
 * @author baoha
 */
public class PolyLogin extends javax.swing.JFrame implements LoginCTR{
    
    
    /**
     * Creates new form PolyLogin
     */
    public PolyLogin() {
        initComponents();
        open();
        XAuth.load();
        if(XAuth.user != null){
            txtPass.setText(XAuth.user.getMatKhau());
            txtUser.setText(XAuth.user.getMaNV());
        }else{
            chkRemember.setSelected(false);
        }
           // Light Cyan
    }
   
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        chkRemember = new javax.swing.JCheckBox();
        btnLogin1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnClose1 = new javax.swing.JButton();
        chkShowPass = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(750, 490));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("LOGIN");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 750, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/manager.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, -1, -1));
        getContentPane().add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 250, -1));

        txtPass.setText("jPasswordField1");
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });
        getContentPane().add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 250, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/pass.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, -1, -1));

        chkRemember.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        chkRemember.setText("Remember");
        chkRemember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRememberActionPerformed(evt);
            }
        });
        getContentPane().add(chkRemember, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, -1, -1));

        btnLogin1.setText("Login");
        btnLogin1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogin1MouseClicked(evt);
            }
        });
        btnLogin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogin1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, 300, -1));

        jButton1.setText("ForgetPass");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 420, -1, -1));

        btnClose1.setText("Close");
        getContentPane().add(btnClose1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 420, 90, -1));

        chkShowPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowPassActionPerformed(evt);
            }
        });
        getContentPane().add(chkShowPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 240, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 330, 10));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 330, 10));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/Background_nht (1) (1).png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 750, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkRememberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRememberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkRememberActionPerformed

    private void chkShowPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowPassActionPerformed
        // TODO add your handling code here:
        if (chkShowPass.isSelected()) {
            txtPass.setEchoChar((char) 0); // Hiện mật khẩu
        } else {
            txtPass.setEchoChar('●'); // Ẩn mật khẩu bằng dấu chấm
        }
    }//GEN-LAST:event_chkShowPassActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new ForgetPass().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        new ForgetPass().setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked

    private void btnLogin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogin1ActionPerformed
        // TODO add your handling code here:
        login();
    }//GEN-LAST:event_btnLogin1ActionPerformed

    private void btnLogin1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogin1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btnLogin1MouseClicked

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed

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
            java.util.logging.Logger.getLogger(PolyLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PolyLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PolyLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PolyLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new PolyLogin().setVisible(true);
            }
             
                        // Màu xanh nhạt cực đẹp

        });
      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose1;
    private javax.swing.JButton btnLogin1;
    private javax.swing.JCheckBox chkRemember;
    private javax.swing.JCheckBox chkShowPass;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables

    @Override
    public void open() {
        setLocationRelativeTo(null);
    }

    @Override
    public void login() {
    String username = txtUser.getText().trim();
    String password = new String(txtPass.getPassword()).trim();

    if (username.isEmpty()) {
        XDialog.alert("Chưa nhập tài khoản!");
        return;
    }
    if (password.isEmpty()) {
        XDialog.alert("Chưa nhập mật khẩu!");
        return;
    }

    NhanVienDAO dao = new LoginandSignupimpl();
    NhanVien user = dao.findById(username);

    if (user == null) {
        XDialog.alert("Sai tài khoản!");
        return;
    }

    if (!password.equals(user.getMatKhau())) {
        XDialog.alert("Nhập sai mật khẩu!");
        return;
    }

    if (XDialog.confirm("Bạn có muốn đăng nhập không?")) {
        XAuth.user = user;

        // Nếu chọn Remember me thì lưu
        if (chkRemember.isSelected()) {
            XAuth.save();
        } else {
            XAuth.clear();  // không lưu thông tin
        }

        new Menu().setVisible(true);
        XDialog.alert("Đăng nhập thành công!");
        this.dispose();
    }
    }

    @Override
    public void exit() {
        LoginCTR.super.exit(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}
