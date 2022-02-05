/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author leope
 */
public class TeacherFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public TeacherFrame() {
        initComponents();
        tab1.setVisible(true);
        choose1.setBackground(new Color(0, 153, 204));
        ImageIcon img = new ImageIcon("src\\View\\icons\\icons8_school_96px.png");
        this.setIconImage(img.getImage());
        
       
    }

    public void componentModify() {
        jSeparator1.setForeground(Color.WHITE);
        jSeparator2.setForeground(Color.WHITE);
        choose4.setBackground(Color.red);
        logOut_btn.setBackground(new Color(0, 204, 204));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidepane = new javax.swing.JPanel();
        choose4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        choose1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        choose2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        choose3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        logOut_btn = new javax.swing.JButton();
        choose5 = new javax.swing.JLabel();
        maintab = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tab1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tab2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tab3 = new javax.swing.JPanel();
        tab4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phần mềm quản lý đào tạo");
        setLocationByPlatform(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidepane.setBackground(new java.awt.Color(0, 204, 204));
        sidepane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        choose4.setBackground(new java.awt.Color(0, 204, 204));
        choose4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        choose4.setForeground(new java.awt.Color(255, 255, 255));
        choose4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_report_card_48px_2.png"))); // NOI18N
        choose4.setText("Quản lý hạnh kiểm");
        choose4.setToolTipText("");
        choose4.setOpaque(true);
        choose4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                choose4MouseClicked(evt);
            }
        });
        sidepane.add(choose4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 220, 60));

        jSeparator1.setBackground(new java.awt.Color(0, 204, 204));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSeparator1.setOpaque(true);
        sidepane.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 210, 10));

        choose1.setBackground(new java.awt.Color(0, 204, 204));
        choose1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        choose1.setForeground(new java.awt.Color(255, 255, 255));
        choose1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/search_48px.png"))); // NOI18N
        choose1.setText("Tìm kiếm");
        choose1.setOpaque(true);
        choose1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                choose1MouseClicked(evt);
            }
        });
        sidepane.add(choose1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 210, 59));

        jSeparator2.setBackground(new java.awt.Color(0, 204, 204));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setOpaque(true);
        sidepane.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 210, 10));

        choose2.setBackground(new java.awt.Color(0, 204, 204));
        choose2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        choose2.setForeground(new java.awt.Color(255, 255, 255));
        choose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_student_male_48px.png"))); // NOI18N
        choose2.setText("Quản lý học sinh");
        choose2.setToolTipText("");
        choose2.setOpaque(true);
        choose2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                choose2MouseClicked(evt);
            }
        });
        sidepane.add(choose2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 210, 59));

        jSeparator3.setBackground(new java.awt.Color(0, 204, 204));
        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator3.setOpaque(true);
        sidepane.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 210, 10));

        choose3.setBackground(new java.awt.Color(0, 204, 204));
        choose3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        choose3.setForeground(new java.awt.Color(255, 255, 255));
        choose3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_scorecard_48px.png"))); // NOI18N
        choose3.setText("Quản lý điểm");
        choose3.setToolTipText("");
        choose3.setOpaque(true);
        choose3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                choose3MouseClicked(evt);
            }
        });
        sidepane.add(choose3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 210, 60));

        jSeparator4.setBackground(new java.awt.Color(0, 204, 204));
        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator4.setOpaque(true);
        sidepane.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 210, 10));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_user_96px.png"))); // NOI18N
        jLabel1.setText("User");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        sidepane.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 100, 120));

        logOut_btn.setBackground(new java.awt.Color(0, 204, 204));
        logOut_btn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        logOut_btn.setForeground(new java.awt.Color(255, 255, 255));
        logOut_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_Logout_52px.png"))); // NOI18N
        logOut_btn.setText("Đăng xuất");
        logOut_btn.setBorder(null);
        logOut_btn.setBorderPainted(false);
        logOut_btn.setContentAreaFilled(false);
        logOut_btn.setOpaque(true);
        logOut_btn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                logOut_btnFocusGained(evt);
            }
        });
        logOut_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOut_btnActionPerformed(evt);
            }
        });
        sidepane.add(logOut_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 570, 130, 60));

        choose5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        choose5.setForeground(new java.awt.Color(255, 255, 255));
        choose5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_teacher_60px_1.png"))); // NOI18N
        choose5.setText("Quản lý giao viên");
        sidepane.add(choose5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 220, 50));

        getContentPane().add(sidepane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 670));

        maintab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("Đây là tab 1");
        maintab.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 270, -1));

        getContentPane().add(maintab, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 1010, 670));

        tab1.setBackground(new java.awt.Color(255, 255, 255));
        tab1.setPreferredSize(new java.awt.Dimension(702, 2467));

        jLabel2.setText("Tab1");

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGap(483, 483, 483)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(258, Short.MAX_VALUE))
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(458, Short.MAX_VALUE))
        );

        getContentPane().add(tab1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 1010, 670));

        tab2.setBackground(new java.awt.Color(255, 255, 255));
        tab2.setPreferredSize(new java.awt.Dimension(702, 2467));

        jLabel4.setText("Đây là tab 2");

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(352, 352, 352)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(544, Short.MAX_VALUE))
        );
        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jLabel4)
                .addContainerGap(526, Short.MAX_VALUE))
        );

        getContentPane().add(tab2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 1010, 670));

        tab3.setBackground(new java.awt.Color(255, 255, 255));
        tab3.setPreferredSize(new java.awt.Dimension(702, 2467));
        tab3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1010, Short.MAX_VALUE)
        );
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );

        getContentPane().add(tab3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 1010, 670));

        tab4.setBackground(new java.awt.Color(255, 255, 255));
        tab4.setPreferredSize(new java.awt.Dimension(702, 2467));

        javax.swing.GroupLayout tab4Layout = new javax.swing.GroupLayout(tab4);
        tab4.setLayout(tab4Layout);
        tab4Layout.setHorizontalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1010, Short.MAX_VALUE)
        );
        tab4Layout.setVerticalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );

        getContentPane().add(tab4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 1010, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void choose1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose1MouseClicked
        choose1.setBackground(new Color(0, 153, 204));
        choose2.setBackground(new Color(0, 204, 204));
        choose3.setBackground(new Color(0, 204, 204));
        choose4.setBackground(new Color(0, 204, 204));
        tab1.setVisible(true);
        tab2.setVisible(false);
        tab3.setVisible(false);
        tab4.setVisible(false);


    }//GEN-LAST:event_choose1MouseClicked

    private void choose2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose2MouseClicked
        choose1.setBackground(new Color(0, 204, 204));
        choose2.setBackground(new Color(0, 153, 204));
        choose3.setBackground(new Color(0, 204, 204));
        choose4.setBackground(new Color(0, 204, 204));
        tab1.setVisible(false);
        tab2.setVisible(true);
        tab3.setVisible(false);
        tab4.setVisible(false);


    }//GEN-LAST:event_choose2MouseClicked

    private void choose3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose3MouseClicked
        choose1.setBackground(new Color(0, 204, 204));
        choose2.setBackground(new Color(0, 204, 204));
        choose3.setBackground(new Color(0, 153, 204));
        choose4.setBackground(new Color(0, 204, 204));
        tab1.setVisible(false);
        tab2.setVisible(false);
        tab3.setVisible(true);
        tab4.setVisible(false);


    }//GEN-LAST:event_choose3MouseClicked

    private void tab3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseClicked

    }//GEN-LAST:event_tab3MouseClicked

    private void choose4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose4MouseClicked
        // TODO add your handling code here:
        choose1.setBackground(new Color(0, 204, 204));
        choose2.setBackground(new Color(0, 204, 204));
        choose3.setBackground(new Color(0, 204, 204));
        choose4.setBackground(new Color(0, 153, 204));
        tab1.setVisible(false);
        tab2.setVisible(false);
        tab3.setVisible(false);
        tab4.setVisible(true);

    }//GEN-LAST:event_choose4MouseClicked

    private void logOut_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOut_btnActionPerformed
        // TODO add your handling code here:
        this.dispose();
       new LoginFrame().setVisible(true);
    }//GEN-LAST:event_logOut_btnActionPerformed

    private void logOut_btnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_logOut_btnFocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_logOut_btnFocusGained

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
            java.util.logging.Logger.getLogger(TeacherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TeacherFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel choose1;
    private javax.swing.JLabel choose2;
    private javax.swing.JLabel choose3;
    private javax.swing.JLabel choose4;
    private javax.swing.JLabel choose5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton logOut_btn;
    private javax.swing.JPanel maintab;
    private javax.swing.JPanel sidepane;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab4;
    // End of variables declaration//GEN-END:variables
}
