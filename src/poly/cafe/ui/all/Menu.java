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
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import poly.cafe.ui.manager.CaLamPanel;
import poly.cafe.ui.manager.ChiTietPanel;
import poly.cafe.ui.manager.DoanhThuPanel;
import poly.cafe.ui.manager.HoaDonPanel;
import poly.cafe.ui.manager.NhanVienPanel;
import poly.cafe.ui.manager.PhanLoai;
import poly.cafe.ui.manager.SanPhamPanel;
import poly.cafe.ui.manager.TheDDPanel;
import poly.cafe.ui.manager.check;
import poly.cafe.util.XAuth;
import poly.cafe.util.XDialog;

/**
 *
 * @author baoha
 */
public class Menu extends javax.swing.JFrame {

    check or = new check();
    private JPanel currentPanel;

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
                setView(new NhanVienPanel(false));
            }
        });
        lblDoanhThu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setView(new DoanhThuPanel());
            }
        });
        lblCalam.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setView(new CaLamPanel(false));
            }
        });

        lblThedd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setView(new TheDDPanel(false));
            }
        });
        lblOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                or.setVisible(false);

            }
        });
        lblHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setView(new HoaDonPanel(false));
        }
        });

        lblCT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setView(new ChiTietPanel(false)); // đoán: ICT là chi tiết
            }
        });

        lblSP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setView(new SanPhamPanel(false)); // đoán: ISP là sản phẩm
            }
        });

        lblPhanLoai.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setView(new PhanLoai(false));
            }
        });
    }

    public void setView(JPanel panel) {
        jPanel3.removeAll();
        jPanel3.setLayout(new BorderLayout());
        jPanel3.add(panel);
        jPanel3.revalidate();
        jPanel3.repaint();

        currentPanel = panel;

    }
    int rong = 208;
    int dai = 596;

    /**
     * Creates new form Menu
     */
    public void hover() {
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
        addHoverTextEffect(lblOrder, normal, hover);
        addHoverTextEffect(lblDoanhThu, normal, hover);

    }

    public Menu() {
        initComponents();
        openmenu();
        initLabelEvents(); // <-- Gọi ở đây
        setView(new NhanVienPanel(true));
        hover();
        setLocationRelativeTo(null);
        or.setDefaultCloseOperation(or.DISPOSE_ON_CLOSE);

    }

    public void openmenu() {
        if (currentPanel instanceof NhanVienPanel nv) {
            nv.setButtonVisible(true);
        } else if (currentPanel instanceof CaLamPanel cl) {
            cl.setButtonVisible(true);
        } else if (currentPanel instanceof TheDDPanel the) {
            the.setButtonVisible(true);
        } else if (currentPanel instanceof HoaDonPanel hd) {
            hd.setButtonVisible(true);
        } else if (currentPanel instanceof ChiTietPanel ct) {
            ct.setButtonVisible(true);
        } else if (currentPanel instanceof SanPhamPanel sp) {
            sp.setButtonVisible(true);
        } else if (currentPanel instanceof PhanLoai pl) {
            pl.setButtonVisible(true);
        }
        
        new Thread(() -> {
            jPanel1.getParent().setComponentZOrder(jPanel1, 0);

            for (int i = 0; i < rong; i++) {
                jPanel1.setSize(i, dai);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();

    }

    public void closemenu() {
        if (currentPanel instanceof NhanVienPanel nv) {
            nv.setButtonVisible(false);
        } else if (currentPanel instanceof CaLamPanel cl) {
            cl.setButtonVisible(false);
        } else if (currentPanel instanceof TheDDPanel the) {
            the.setButtonVisible(false);
        } else if (currentPanel instanceof HoaDonPanel hd) {
            hd.setButtonVisible(false);
        } else if (currentPanel instanceof ChiTietPanel ct) {
            ct.setButtonVisible(false);
        } else if (currentPanel instanceof SanPhamPanel sp) {
            sp.setButtonVisible(false);
        } else if (currentPanel instanceof PhanLoai pl) {
            pl.setButtonVisible(false);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = rong; i > 0; i--) {
                    jPanel1.setSize(i, dai);

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
        jPanel1 = new javax.swing.JPanel();
        menuBackGround1 = new poly.cafe.ui.all.back.menuBackGround();
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
        lblOrder = new javax.swing.JLabel();
        lblDoanhThu = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        btnMinimize = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1090, 596));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jLabel12.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel12AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        lblOrder.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrder.setForeground(new java.awt.Color(255, 255, 255));
        lblOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/icons8-order-50.png"))); // NOI18N
        lblOrder.setText("ORDER");

        lblDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/cafe/icons/icons8-money-20 (1).png"))); // NOI18N
        lblDoanhThu.setText("Doanh thu");
        lblDoanhThu.setMaximumSize(new java.awt.Dimension(150, 20));
        lblDoanhThu.setPreferredSize(new java.awt.Dimension(150, 20));

        javax.swing.GroupLayout menuBackGround1Layout = new javax.swing.GroupLayout(menuBackGround1);
        menuBackGround1.setLayout(menuBackGround1Layout);
        menuBackGround1Layout.setHorizontalGroup(
            menuBackGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblCalam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblOrder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(menuBackGround1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuBackGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(lblThedd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPhanLoai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(lblDoanhThu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBackGround1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menuBackGround1Layout.setVerticalGroup(
            menuBackGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBackGround1Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
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
                .addComponent(lblSP, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPhanLoai)
                .addGap(18, 18, 18)
                .addComponent(lblOrder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addComponent(lblDangNhap)
                .addContainerGap())
        );

        jPanel1.add(menuBackGround1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, -1));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 977, Short.MAX_VALUE)
                .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMinimize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, -1));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setEnabled(false);
        jPanel3.setFocusable(false);
        jPanel3.setLayout(new javax.swing.OverlayLayout(jPanel3));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 45, 1090, 551));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jPanel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel1AncestorAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel1AncestorAdded

    private void lblDangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangNhapMouseClicked
        // TODO add your handling code here:

        dispose();
    }//GEN-LAST:event_lblDangNhapMouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        closemenu();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel12AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel12AncestorAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel12AncestorAdded

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
                if (XAuth.user == null) {
                    XDialog.alert("Bạn cần phải đăng nhập");
                    new PolyLogin().setVisible(true);
                    return;
                } else {
                    new Menu().setVisible(true);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblCT;
    private javax.swing.JLabel lblCalam;
    private javax.swing.JLabel lblDangNhap;
    private javax.swing.JLabel lblDoanhThu;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblOrder;
    private javax.swing.JLabel lblPhanLoai;
    private javax.swing.JLabel lblSP;
    private javax.swing.JLabel lblThedd;
    private poly.cafe.ui.all.back.menuBackGround menuBackGround1;
    // End of variables declaration//GEN-END:variables
}
