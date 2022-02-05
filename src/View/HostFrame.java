package View;

import Controller.Service.GiaoVienService;
import Controller.Service.HocService;
import Controller.Service.HocSinhService;
import Controller.ServiceImpl.GiaoVienServiceImpl;
import Controller.ServiceImpl.HocServiceImpl;
import Controller.ServiceImpl.HocSinhServiceImpl;
import Controller.Validation.DateAndTimeUtils;
import Model.GiaoVien;
import Model.HocSinh;
import View.*;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.ui.FlatTextBorder;
import com.sun.jdi.connect.spi.Connection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.OptionPaneUI;
import net.miginfocom.swing.SwingComponentWrapper;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author leope
 */
public class HostFrame extends javax.swing.JFrame {

    private String tenDangNhap;
    private String chucVu;
    private DefaultTableModel tab1_student_tableModel;
    private HocSinhService hocSinhService;
    private GiaoVienService giaoVienService;
    private HocService hocService ;
    
    //
   

    /**
     * Creates new form MainFrame
     *
     */
    public HostFrame() {
        try {
            FlatIntelliJLaf.setup();
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        infoTab.setVisible(true);
        tab1.setVisible(false);
        tab2.setVisible(false);
        tab3.setVisible(false);
        tab4.setVisible(false);
        tab5.setVisible(false);
        // choose1.setBackground(new Color(0, 153, 204));
       // ImageIcon img = new ImageIcon("src\\View\\icons\\icons8_school_96px.png");
        //this.setIconImage(new ImageIcon("src\\View\\icons\\icons8_school_96px.png").getImage());
        tab1_student_tableModel = (DefaultTableModel) tab1_student_table.getModel();
        hocSinhService = new HocSinhServiceImpl(); // injection
        giaoVienService = new GiaoVienServiceImpl(); // injection 
        hocService= new HocServiceImpl(); // injection
        

        // set chức năng theo chức vụ giáo viên
//       this.setBounds(0, 0, 1280, 670);
//       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//       this.setBounds(0,0,screenSize.width, screenSize.height);
    }

    public void getLoginInfo(String userName, String position) {
        this.tenDangNhap = userName;
        this.chucVu = position;
        setFunction(this.chucVu);
        showTeacherInfoTab();
    }

    public void setFunction(String chucVu) {
        if (this.chucVu.equals("Giáo viên giảng dạy")) {
            choose5.setVisible(false);
            jSeparator2.setVisible(false);
        }
    }

    public void componentModify() {

    }
    

    public void showStudentInfo(List<HocSinh> listHocSinh) {
        Optional listHocSinhOptional = Optional.ofNullable(listHocSinh);
        if (listHocSinh.isEmpty() || !listHocSinhOptional.isPresent()) {
            return;
        } else {
            tab1_student_tableModel.setRowCount(0);
            for (HocSinh hs : listHocSinh) {
                tab1_student_tableModel.addRow(new Object[]{hs.getMaHocSinh(),
                    hs.getTenHocSinh(),
                    hs.getGioiTinh(),
                    hs.getNgaySinh(),
                    hs.getDiaChi(),
                    hs.getTenPhuHuynh(),
                    hs.getSoDienThoai()});
            }
        }
    }

    public void showTeacherInfoTab() {
        GiaoVien giaoVien = giaoVienService.findByTenDangNhap(tenDangNhap).get();
        infoTab_name_textField.setText(giaoVien.getTenGiaoVien());
        infoTab_address_textField.setText(giaoVien.getDiaChi());
        infoTab_id_textfield.setText(giaoVien.getMaGiaoVien());
        infoTab_sex_textField.setText(giaoVien.getGioiTinh());
        infoTab_birthday_textField.setText(DateAndTimeUtils.convertDateToStr(giaoVien.getNgaySinh()));
        infoTab_phone_textField.setText(giaoVien.getSoDienThoai());
        infoTab_position_Textfield.setText(giaoVien.getChucVu());
        infoTab_label.setText(giaoVien.getTenGiaoVien());
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
        infoTab_label = new javax.swing.JLabel();
        logOut_btn = new javax.swing.JButton();
        choose5 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        infoTab = new javax.swing.JPanel();
        infoTabHeader = new javax.swing.JLabel();
        infoTab_name_label = new javax.swing.JLabel();
        infoTab_sex_textField = new javax.swing.JTextField();
        infoTab_sex_label = new javax.swing.JLabel();
        infoTab_name_textField = new javax.swing.JTextField();
        infoTab_birthday_label = new javax.swing.JLabel();
        infoTab_birthday_textField = new javax.swing.JTextField();
        infoTab_phone_label = new javax.swing.JLabel();
        infoTab_phone_textField = new javax.swing.JTextField();
        infoTab_address_label = new javax.swing.JLabel();
        infoTab_address_textField = new javax.swing.JTextField();
        infoTab_position_label = new javax.swing.JLabel();
        infoTab_position_Textfield = new javax.swing.JTextField();
        infoTab_edit_button = new javax.swing.JButton();
        infoTab_schoolYear_label = new javax.swing.JLabel();
        infoTab_schoolYear_textField = new javax.swing.JTextField();
        infoTab_semester_label = new javax.swing.JLabel();
        infoTab_semeter_textField = new javax.swing.JTextField();
        infoTab_save_button = new javax.swing.JButton();
        infoTab_id_label = new javax.swing.JLabel();
        infoTab_id_textfield = new javax.swing.JTextField();
        tab1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab1_student_table = new javax.swing.JTable();
        tab1_searchAll_button = new javax.swing.JButton();
        tab1_search_textField = new javax.swing.JTextField();
        tab1_search_button = new javax.swing.JButton();
        tab1_search_comboBox = new javax.swing.JComboBox<>();
        tab1_Header = new javax.swing.JLabel();
        tab2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tab3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tab4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tab5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phần mềm quản lý đào tạo THCS");
        setIconImage(new ImageIcon("src\\View\\icons\\icons8_school_96px.png").getImage());
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(960, 670));
        setPreferredSize(new java.awt.Dimension(1210, 720));
        setSize(new java.awt.Dimension(1210, 720));
        getContentPane().setLayout(null);

        sidepane.setBackground(new java.awt.Color(0, 204, 204));
        sidepane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        choose4.setBackground(new java.awt.Color(0, 204, 204));
        choose4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        choose4.setForeground(new java.awt.Color(255, 255, 255));
        choose4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_report_card_48px_2.png"))); // NOI18N
        choose4.setText("Quản lý hạnh kiểm");
        choose4.setToolTipText("");
        choose4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        choose4.setDoubleBuffered(true);
        choose4.setFocusCycleRoot(true);
        choose4.setOpaque(true);
        choose4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                choose4MouseClicked(evt);
            }
        });
        sidepane.add(choose4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 230, 60));

        jSeparator1.setBackground(new java.awt.Color(0, 204, 204));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSeparator1.setOpaque(true);
        sidepane.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 230, 10));

        choose1.setBackground(new java.awt.Color(0, 204, 204));
        choose1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        choose1.setForeground(new java.awt.Color(255, 255, 255));
        choose1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/search_48px.png"))); // NOI18N
        choose1.setText("Tìm kiếm");
        choose1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        choose1.setDoubleBuffered(true);
        choose1.setFocusCycleRoot(true);
        choose1.setOpaque(true);
        choose1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                choose1MouseClicked(evt);
            }
        });
        sidepane.add(choose1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 230, 60));

        jSeparator2.setBackground(new java.awt.Color(0, 204, 204));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setOpaque(true);
        sidepane.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 230, 10));

        choose2.setBackground(new java.awt.Color(0, 204, 204));
        choose2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        choose2.setForeground(new java.awt.Color(255, 255, 255));
        choose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_student_male_48px.png"))); // NOI18N
        choose2.setText("Quản lý học sinh");
        choose2.setToolTipText("");
        choose2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        choose2.setDoubleBuffered(true);
        choose2.setFocusCycleRoot(true);
        choose2.setOpaque(true);
        choose2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                choose2MouseClicked(evt);
            }
        });
        sidepane.add(choose2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 230, 59));

        jSeparator3.setBackground(new java.awt.Color(0, 204, 204));
        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator3.setOpaque(true);
        sidepane.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 230, 10));

        choose3.setBackground(new java.awt.Color(0, 204, 204));
        choose3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        choose3.setForeground(new java.awt.Color(255, 255, 255));
        choose3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_scorecard_48px.png"))); // NOI18N
        choose3.setText("Quản lý điểm");
        choose3.setToolTipText("");
        choose3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        choose3.setDoubleBuffered(true);
        choose3.setFocusCycleRoot(true);
        choose3.setOpaque(true);
        choose3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                choose3MouseClicked(evt);
            }
        });
        sidepane.add(choose3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 230, 60));

        jSeparator4.setBackground(new java.awt.Color(0, 204, 204));
        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator4.setOpaque(true);
        sidepane.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 230, 10));

        infoTab_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        infoTab_label.setForeground(new java.awt.Color(255, 255, 255));
        infoTab_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoTab_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_male_user_100px.png"))); // NOI18N
        infoTab_label.setText("User");
        infoTab_label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        infoTab_label.setDoubleBuffered(true);
        infoTab_label.setFocusCycleRoot(true);
        infoTab_label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        infoTab_label.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        infoTab_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                infoTab_labelMouseClicked(evt);
            }
        });
        sidepane.add(infoTab_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, 130));

        logOut_btn.setBackground(new java.awt.Color(0, 204, 204));
        logOut_btn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        logOut_btn.setForeground(new java.awt.Color(255, 255, 255));
        logOut_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_Logout_52px.png"))); // NOI18N
        logOut_btn.setText("Đăng xuất");
        logOut_btn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        logOut_btn.setBorderPainted(false);
        logOut_btn.setContentAreaFilled(false);
        logOut_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logOut_btn.setOpaque(true);
        logOut_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOut_btnActionPerformed(evt);
            }
        });
        sidepane.add(logOut_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 570, 130, 60));

        choose5.setBackground(new java.awt.Color(0, 204, 204));
        choose5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        choose5.setForeground(new java.awt.Color(255, 255, 255));
        choose5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_teacher_60px_1.png"))); // NOI18N
        choose5.setText("Quản lý giáo viên");
        choose5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        choose5.setDoubleBuffered(true);
        choose5.setFocusCycleRoot(true);
        choose5.setOpaque(true);
        choose5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                choose5MouseClicked(evt);
            }
        });
        sidepane.add(choose5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 230, 60));

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        sidepane.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 230, 10));

        getContentPane().add(sidepane);
        sidepane.setBounds(0, 0, 270, 670);

        infoTab.setBackground(new java.awt.Color(255, 255, 255));
        infoTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        infoTabHeader.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        infoTabHeader.setForeground(new java.awt.Color(0, 204, 204));
        infoTabHeader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_male_user_100px.png"))); // NOI18N
        infoTabHeader.setText("Thông tin cá nhân");
        infoTab.add(infoTabHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 430, 100));

        infoTab_name_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        infoTab_name_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_name_label.setText("Họ  tên : ");
        infoTab.add(infoTab_name_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 80, 30));

        infoTab_sex_textField.setEditable(false);
        infoTab_sex_textField.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_sex_textField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        infoTab_sex_textField.setText("Nam");
        infoTab_sex_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTab_sex_textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoTab_sex_textFieldActionPerformed(evt);
            }
        });
        infoTab.add(infoTab_sex_textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 180, 30));

        infoTab_sex_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        infoTab_sex_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_sex_label.setText("Giới tính:");
        infoTab.add(infoTab_sex_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 80, 30));

        infoTab_name_textField.setEditable(false);
        infoTab_name_textField.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_name_textField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        infoTab_name_textField.setText("Đào Huy Chiến");
        infoTab_name_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTab.add(infoTab_name_textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 180, 30));

        infoTab_birthday_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        infoTab_birthday_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_birthday_label.setText("Ngày sinh :");
        infoTab.add(infoTab_birthday_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 80, 30));

        infoTab_birthday_textField.setEditable(false);
        infoTab_birthday_textField.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_birthday_textField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        infoTab_birthday_textField.setText("18/08/2001");
        infoTab_birthday_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTab_birthday_textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoTab_birthday_textFieldActionPerformed(evt);
            }
        });
        infoTab.add(infoTab_birthday_textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 180, 30));

        infoTab_phone_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        infoTab_phone_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_phone_label.setText("Số điện thoại:");
        infoTab.add(infoTab_phone_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 100, 30));

        infoTab_phone_textField.setEditable(false);
        infoTab_phone_textField.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_phone_textField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        infoTab_phone_textField.setText("0918394508");
        infoTab_phone_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTab.add(infoTab_phone_textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 120, 30));

        infoTab_address_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        infoTab_address_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_address_label.setText("Địa chỉ :");
        infoTab.add(infoTab_address_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 100, -1));

        infoTab_address_textField.setEditable(false);
        infoTab_address_textField.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_address_textField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        infoTab_address_textField.setText("Gia Lâm - Hà Nội");
        infoTab_address_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTab.add(infoTab_address_textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, 280, 30));

        infoTab_position_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        infoTab_position_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_position_label.setText("Chức vụ :");
        infoTab.add(infoTab_position_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 440, 70, 30));

        infoTab_position_Textfield.setEditable(false);
        infoTab_position_Textfield.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_position_Textfield.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        infoTab_position_Textfield.setText("Giáo viên giảng dạy");
        infoTab_position_Textfield.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTab.add(infoTab_position_Textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 472, 210, 30));

        infoTab_edit_button.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        infoTab_edit_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_edit_24px.png"))); // NOI18N
        infoTab_edit_button.setText("Chỉnh sửa thông tin");
        infoTab_edit_button.setToolTipText("Chỉnh sửa thông tin cá nhân");
        infoTab_edit_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        infoTab_edit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoTab_edit_buttonActionPerformed(evt);
            }
        });
        infoTab.add(infoTab_edit_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 580, 200, 50));

        infoTab_schoolYear_label.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_schoolYear_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        infoTab_schoolYear_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_schoolYear_label.setText("Năm học:");
        infoTab.add(infoTab_schoolYear_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 80, 30));

        infoTab_schoolYear_textField.setEditable(false);
        infoTab_schoolYear_textField.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_schoolYear_textField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        infoTab_schoolYear_textField.setText("2021-2022");
        infoTab_schoolYear_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTab.add(infoTab_schoolYear_textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, -1, -1));

        infoTab_semester_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        infoTab_semester_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_semester_label.setText("Học kỳ :");
        infoTab.add(infoTab_semester_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, -1, -1));

        infoTab_semeter_textField.setEditable(false);
        infoTab_semeter_textField.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_semeter_textField.setText("II");
        infoTab_semeter_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTab.add(infoTab_semeter_textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, -1, -1));

        infoTab_save_button.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        infoTab_save_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_save_24px.png"))); // NOI18N
        infoTab_save_button.setText("Lưu thông tin");
        infoTab_save_button.setToolTipText("Lưu các thông tin chỉnh sửa");
        infoTab_save_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        infoTab_save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoTab_save_buttonActionPerformed(evt);
            }
        });
        infoTab.add(infoTab_save_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 580, 200, 50));

        infoTab_id_label.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_id_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        infoTab_id_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_id_label.setText("Mã giáo viên: ");
        infoTab.add(infoTab_id_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 510, -1, -1));

        infoTab_id_textfield.setEditable(false);
        infoTab_id_textfield.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_id_textfield.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        infoTab_id_textfield.setText("GV0001");
        infoTab_id_textfield.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTab.add(infoTab_id_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, -1, -1));

        getContentPane().add(infoTab);
        infoTab.setBounds(270, 0, 930, 670);

        tab1.setBackground(new java.awt.Color(255, 255, 255));
        tab1.setPreferredSize(new java.awt.Dimension(702, 670));

        tab1_student_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã học sinh", "Tên học sinh", "Giới tính", "Ngày sinh", "Địa chỉ", "Tên phụ hyunh", "Số điện thoại"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tab1_student_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(tab1_student_table);

        tab1_searchAll_button.setText("Tìm tất cả học sinh");
        tab1_searchAll_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab1_searchAll_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab1_searchAll_buttonMouseClicked(evt);
            }
        });
        tab1_searchAll_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tab1_searchAll_buttonActionPerformed(evt);
            }
        });

        tab1_search_textField.setText("Nhập tên hoặc mã học sinh");

        tab1_search_button.setText("Tìm kiếm");
        tab1_search_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab1_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab1_search_buttonMouseClicked(evt);
            }
        });

        tab1_search_comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo tên", "Theo mã học sinh" }));

        tab1_Header.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        tab1_Header.setForeground(new java.awt.Color(0, 204, 204));
        tab1_Header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_search_100px.png"))); // NOI18N
        tab1_Header.setText("Tìm kiếm");

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(tab1_Header, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(tab1Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tab1_search_textField)
                    .addGroup(tab1Layout.createSequentialGroup()
                        .addComponent(tab1_search_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tab1_searchAll_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tab1_search_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(tab1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
                .addContainerGap())
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab1_Header)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(tab1_search_textField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tab1_search_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tab1_search_button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab1_searchAll_button)
                .addGap(66, 66, 66)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(tab1);
        tab1.setBounds(270, 0, 930, 670);

        tab2.setBackground(new java.awt.Color(255, 255, 255));
        tab2.setPreferredSize(new java.awt.Dimension(702, 670));

        jLabel4.setText("Đây là tab thứ 2");

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(302, 302, 302)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );
        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel4)
                .addContainerGap(559, Short.MAX_VALUE))
        );

        getContentPane().add(tab2);
        tab2.setBounds(270, 0, 930, 670);

        tab3.setBackground(new java.awt.Color(255, 255, 255));
        tab3.setPreferredSize(new java.awt.Dimension(702, 670));

        jLabel7.setText("đây là tab thứ 3");

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(328, Short.MAX_VALUE))
        );
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel7)
                .addContainerGap(574, Short.MAX_VALUE))
        );

        getContentPane().add(tab3);
        tab3.setBounds(270, 0, 930, 670);

        tab4.setBackground(new java.awt.Color(255, 255, 255));
        tab4.setPreferredSize(new java.awt.Dimension(702, 670));
        tab4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab4MouseClicked(evt);
            }
        });

        jLabel5.setText("Đây là tab thứ 4");

        javax.swing.GroupLayout tab4Layout = new javax.swing.GroupLayout(tab4);
        tab4.setLayout(tab4Layout);
        tab4Layout.setHorizontalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(436, Short.MAX_VALUE))
        );
        tab4Layout.setVerticalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(tab4);
        tab4.setBounds(270, 0, 930, 670);

        tab5.setBackground(new java.awt.Color(255, 255, 255));
        tab5.setPreferredSize(new java.awt.Dimension(702, 670));

        jLabel6.setText("Đây là tab thứ 5");

        javax.swing.GroupLayout tab5Layout = new javax.swing.GroupLayout(tab5);
        tab5.setLayout(tab5Layout);
        tab5Layout.setHorizontalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab5Layout.createSequentialGroup()
                .addContainerGap(665, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        tab5Layout.setVerticalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab5Layout.createSequentialGroup()
                .addContainerGap(540, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(114, 114, 114))
        );

        getContentPane().add(tab5);
        tab5.setBounds(270, 0, 930, 670);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void choose1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose1MouseClicked
        choose1.setBackground(new Color(0, 153, 204));
        choose2.setBackground(new Color(0, 204, 204));
        choose3.setBackground(new Color(0, 204, 204));
        choose4.setBackground(new Color(0, 204, 204));
        choose5.setBackground(new Color(0, 204, 204));
        tab1.setVisible(true);
        tab2.setVisible(false);
        tab3.setVisible(false);
        tab4.setVisible(false);
        tab5.setVisible(false);
        infoTab.setVisible(false);


    }//GEN-LAST:event_choose1MouseClicked

    private void choose2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose2MouseClicked
        choose2.setBackground(new Color(0, 153, 204));
        choose1.setBackground(new Color(0, 204, 204));
        choose3.setBackground(new Color(0, 204, 204));
        choose4.setBackground(new Color(0, 204, 204));
        choose5.setBackground(new Color(0, 204, 204));
        tab1.setVisible(false);
        tab2.setVisible(true);
        tab3.setVisible(false);
        tab4.setVisible(false);
        tab5.setVisible(false);
        infoTab.setVisible(false);

    }//GEN-LAST:event_choose2MouseClicked

    private void choose3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose3MouseClicked
        choose3.setBackground(new Color(0, 153, 204));
        choose1.setBackground(new Color(0, 204, 204));
        choose2.setBackground(new Color(0, 204, 204));
        choose4.setBackground(new Color(0, 204, 204));
        choose5.setBackground(new Color(0, 204, 204));
        tab1.setVisible(false);
        tab2.setVisible(false);
        tab3.setVisible(true);
        tab4.setVisible(false);
        tab5.setVisible(false);
        infoTab.setVisible(false);

    }//GEN-LAST:event_choose3MouseClicked

    private void tab4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseClicked

    }//GEN-LAST:event_tab4MouseClicked

    private void choose4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose4MouseClicked
        // TODO add your handling code here:
        choose4.setBackground(new Color(0, 153, 204));
        choose1.setBackground(new Color(0, 204, 204));
        choose2.setBackground(new Color(0, 204, 204));
        choose3.setBackground(new Color(0, 204, 204));
        choose5.setBackground(new Color(0, 204, 204));
        tab1.setVisible(false);
        tab2.setVisible(false);
        tab3.setVisible(false);
        tab4.setVisible(true);
        tab5.setVisible(false);
        infoTab.setVisible(false);

    }//GEN-LAST:event_choose4MouseClicked

    private void logOut_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOut_btnActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new LoginFrame().setVisible(true);
    }//GEN-LAST:event_logOut_btnActionPerformed

    private void choose5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose5MouseClicked
        // TODO add your handling code here:
        choose5.setBackground(new Color(0, 153, 204));
        choose1.setBackground(new Color(0, 204, 204));
        choose2.setBackground(new Color(0, 204, 204));
        choose3.setBackground(new Color(0, 204, 204));
        choose4.setBackground(new Color(0, 204, 204));
        tab1.setVisible(false);
        tab2.setVisible(false);
        tab3.setVisible(false);
        tab4.setVisible(false);
        tab5.setVisible(true);
        infoTab.setVisible(false);
    }//GEN-LAST:event_choose5MouseClicked

    private void infoTab_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoTab_labelMouseClicked
        // TODO add your handling code here:
        choose4.setBackground(new Color(0, 204, 204));
        choose1.setBackground(new Color(0, 204, 204));
        choose2.setBackground(new Color(0, 204, 204));
        choose3.setBackground(new Color(0, 204, 204));
        choose5.setBackground(new Color(0, 204, 204));
        infoTab.setVisible(true);
        tab1.setVisible(false);
        tab2.setVisible(false);
        tab3.setVisible(false);
        tab4.setVisible(false);
        tab5.setVisible(false);
        infoTab_save_button.setVisible(false);
    }//GEN-LAST:event_infoTab_labelMouseClicked

    private void infoTab_sex_textFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoTab_sex_textFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_infoTab_sex_textFieldActionPerformed

    private void infoTab_birthday_textFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoTab_birthday_textFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_infoTab_birthday_textFieldActionPerformed

    private void infoTab_edit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoTab_edit_buttonActionPerformed
        // TODO add your handling code here:
        infoTab_edit_button.setVisible(false);
        infoTab_save_button.setVisible(true);
        infoTab_name_textField.setBorder(new FlatTextBorder());
        infoTab_name_textField.setEditable(true);
        infoTab_sex_textField.setBorder(new FlatTextBorder());
        infoTab_sex_textField.setEditable(true);
        infoTab_birthday_textField.setBorder(new FlatTextBorder());
        infoTab_birthday_textField.setEditable(true);
        infoTab_phone_textField.setBorder(new FlatTextBorder());
        infoTab_phone_textField.setEditable(true);
        infoTab_address_textField.setBorder(new FlatTextBorder());
        infoTab_address_textField.setEditable(true);  
        
                
        
    }//GEN-LAST:event_infoTab_edit_buttonActionPerformed

    private void infoTab_save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoTab_save_buttonActionPerformed
         infoTab_save_button.setVisible(false);
        JOptionPane infoTab_saving_Option = new JOptionPane();
        int chooseOption = infoTab_saving_Option.showConfirmDialog(null, "Bạn có muốn các thông tin cá nhân ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (chooseOption == 0) {
            infoTab_name_textField.setText(infoTab_name_textField.getText());
            infoTab_name_textField.setBorder(BorderFactory.createEmptyBorder());
            infoTab_name_textField.setEditable(false);
            infoTab_sex_textField.setBorder(BorderFactory.createEmptyBorder());
            infoTab_sex_textField.setEditable(false);
            infoTab_birthday_textField.setBorder(BorderFactory.createEmptyBorder());
            infoTab_birthday_textField.setEditable(false);
            infoTab_phone_textField.setBorder(BorderFactory.createEmptyBorder());
            infoTab_phone_textField.setEditable(false);
            infoTab_address_textField.setBorder(BorderFactory.createEmptyBorder());
            infoTab_address_textField.setEditable(false); 
          //  infoTab_saving_Option.showMessageDialog(null, "Bạn đã lưu thông tin thành công !");
          GiaoVien giaoVienUpdate = giaoVienService.findByTenDangNhap(tenDangNhap).get();
          if(!infoTab_name_textField.equals("")) giaoVienUpdate.setTenGiaoVien(infoTab_name_textField.getText());
          if(!infoTab_sex_textField.equals("")) giaoVienUpdate.setGioiTinh(infoTab_sex_textField.getText());
          if(!infoTab_birthday_textField.equals("")) 
          {
              String birthday =infoTab_birthday_textField.getText();
              if(Controller.Validation.Validator.dateValidator2(birthday))
              {
                  try {
                      giaoVienUpdate.setNgaySinh(DateAndTimeUtils.convertStrToDate(birthday));
                  } catch (Exception ex) {
                      ex.printStackTrace();
                  }
              }
              else 
                  {
                      infoTab_saving_Option.showMessageDialog(null,"Vui lòng nhập đúng định dạng ngày sinh theo định dạng(Ngày/Tháng/Năm)!","Cảnh báo",JOptionPane.WARNING_MESSAGE);
                      showTeacherInfoTab();
                  }
          }
         giaoVienService.updateGiaoVien(giaoVienUpdate);
        } else {
            infoTab_saving_Option.showMessageDialog(null, "Vui lòng xác nhận lại","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }     
        infoTab_edit_button.setVisible(true);
      

    }//GEN-LAST:event_infoTab_save_buttonActionPerformed

    private void tab1_searchAll_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1_searchAll_buttonMouseClicked
    List<HocSinh> listHocSinh = hocSinhService.findAll();
        showStudentInfo(listHocSinh);
    }//GEN-LAST:event_tab1_searchAll_buttonMouseClicked

    private void tab1_searchAll_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tab1_searchAll_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tab1_searchAll_buttonActionPerformed

    private void tab1_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1_search_buttonMouseClicked
        // TODO add your handling code here:
        if(tab1_search_comboBox.getSelectedIndex() ==0 ) 
            {
                    if(hocSinhService.findByTenHocSinh(tab1_search_textField.getText()).size() <=0 ) {
                           new JOptionPane().showMessageDialog(null, "Không tìm thấy kết quả phù hợp","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    }                    
                    else 
                        showStudentInfo(hocSinhService.findByTenHocSinh(tab1_search_textField.getText()));
             }
        else 
            {
                Optional<HocSinh> hocSinhOptional = hocSinhService.findByMaHocSinh(tab1_search_textField.getText());
                if(hocSinhOptional.isPresent())  showStudentInfo(List.of(hocSinhOptional.get()));
                else
                    {
                        new JOptionPane().showMessageDialog(null, "Không tìm thấy học sinh phù hợp","Cảnh báo",JOptionPane.INFORMATION_MESSAGE);                  
                        showStudentInfo(List.of());
                    }                       
            }
    }//GEN-LAST:event_tab1_search_buttonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            FlatIntelliJLaf.setup();
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HostFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel choose1;
    private javax.swing.JLabel choose2;
    private javax.swing.JLabel choose3;
    private javax.swing.JLabel choose4;
    private javax.swing.JLabel choose5;
    private javax.swing.JPanel infoTab;
    private javax.swing.JLabel infoTabHeader;
    private javax.swing.JLabel infoTab_address_label;
    private javax.swing.JTextField infoTab_address_textField;
    private javax.swing.JLabel infoTab_birthday_label;
    private javax.swing.JTextField infoTab_birthday_textField;
    private javax.swing.JButton infoTab_edit_button;
    private javax.swing.JLabel infoTab_id_label;
    private javax.swing.JTextField infoTab_id_textfield;
    private javax.swing.JLabel infoTab_label;
    private javax.swing.JLabel infoTab_name_label;
    private javax.swing.JTextField infoTab_name_textField;
    private javax.swing.JLabel infoTab_phone_label;
    private javax.swing.JTextField infoTab_phone_textField;
    private javax.swing.JTextField infoTab_position_Textfield;
    private javax.swing.JLabel infoTab_position_label;
    private javax.swing.JButton infoTab_save_button;
    private javax.swing.JLabel infoTab_schoolYear_label;
    private javax.swing.JTextField infoTab_schoolYear_textField;
    private javax.swing.JLabel infoTab_semester_label;
    private javax.swing.JTextField infoTab_semeter_textField;
    private javax.swing.JLabel infoTab_sex_label;
    private javax.swing.JTextField infoTab_sex_textField;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JButton logOut_btn;
    private javax.swing.JPanel sidepane;
    private javax.swing.JPanel tab1;
    private javax.swing.JLabel tab1_Header;
    private javax.swing.JButton tab1_searchAll_button;
    private javax.swing.JButton tab1_search_button;
    private javax.swing.JComboBox<String> tab1_search_comboBox;
    private javax.swing.JTextField tab1_search_textField;
    private javax.swing.JTable tab1_student_table;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab4;
    private javax.swing.JPanel tab5;
    // End of variables declaration//GEN-END:variables
}
