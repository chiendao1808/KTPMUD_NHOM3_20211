package View;

import Controller.Service.GiaoVienService;
import Controller.Service.HanhKiemService;
import Controller.Service.HocService;
import Controller.Service.HocSinhService;
import Controller.Service.LopService;
import Controller.ServiceImpl.GiaoVienServiceImpl;
import Controller.ServiceImpl.HanhKiemServiceImpl;
import Controller.ServiceImpl.HocServiceImpl;
import Controller.ServiceImpl.HocSinhServiceImpl;
import Controller.ServiceImpl.LopServiceImpl;
import Controller.Validation.DateAndTimeUtils;
import Model.GiaoVien;
import Model.HanhKiem;
import Model.Hoc;
import Model.HocSinh;
import Model.Lop;
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
import java.awt.Font;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author leope
 */
public class HostFrame extends javax.swing.JFrame  {

    private String tenDangNhap;
    private GiaoVien giaoVien;
    private String chucVu;
    private DefaultTableModel tab1_student_tableModel;
    private DefaultTableModel tab2_tableModel;
    private DefaultTableModel tab3_tableModel;
    private DefaultTableModel tab4_tableModel;
    private DefaultTableModel tab5_tableModel;
    private HocSinhService hocSinhService;
    private LopService lopService;
    private GiaoVienService giaoVienService;
    private HocService hocService ;
    private HanhKiemService hanhKiemService;
    
    
    //
    public HostFrame() {
            try {
            FlatIntelliJLaf.setup();
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
        } catch (Exception e) {
            e.printStackTrace();
        }
         initComponents();
        this.setResizable(false);
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
        tab1_student_tableModel.setRowCount(0);
        //tab1
        tab2_tableModel = (DefaultTableModel) tab2_table.getModel();
        tab2_tableModel.setRowCount(0);
        //tab3
        tab3_tableModel = (DefaultTableModel) tab3_table.getModel();
        tab3_tableModel.setRowCount(0);
        //tab4
        tab4_tableModel = (DefaultTableModel) tab4_table.getModel();
        tab4_tableModel.setRowCount(0);
        //tab5
        tab5_tableModel = (DefaultTableModel) tab5_table.getModel();
        tab5_tableModel.setRowCount(0);
        hocSinhService = new HocSinhServiceImpl(); // injection
        giaoVienService = new GiaoVienServiceImpl(); // injection 
        hocService= new HocServiceImpl(); // injection
        lopService  = new LopServiceImpl(); // injection
        hanhKiemService= new HanhKiemServiceImpl(); // injection
       // giaoVien= giaoVienService.findByTenDangNhap(tenDangNhap).get();
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
    

    public void showStudentInfo(List<HocSinh> listHocSinh, DefaultTableModel tableModel ) {
        Optional listHocSinhOptional = Optional.ofNullable(listHocSinh);
        if (listHocSinh.isEmpty() || !listHocSinhOptional.isPresent()) {
            return;
        } else {
            tableModel.setRowCount(0);
            for (HocSinh hs : listHocSinh) {
                String lopHoc = "";
                if (hocService.findByYearHoc(hs.getMaHocSinh(), tab1_year_comboBox.getSelectedItem().toString()).isPresent())
                    lopHoc = hocService.findByYearHoc(hs.getMaHocSinh(), tab1_year_comboBox.getSelectedItem().toString()).get().getLop().getTenLop();
               tableModel.addRow(new Object[]{hs.getMaHocSinh(),
                                                                            hs.getTenHocSinh(),
                                                                            hs.getGioiTinh(),
                                                                            DateAndTimeUtils.convertDateToStr(hs.getNgaySinh()),
                                                                            hs.getDiaChi(),
                                                                            hs.getTenPhuHuynh(),
                                                                            hs.getSoDienThoai(),
                                                                            lopHoc });
            }
        }
    }
    
    public void showTeacherInfo(List<GiaoVien> listGiaoVien, DefaultTableModel tableModel)
            {
                Optional listGiaoVienOptional = Optional.ofNullable(listGiaoVien);    
                if(listGiaoVien.isEmpty() || !listGiaoVienOptional.isPresent()) return;
                else{
                    tableModel.setRowCount(0);
                    for(GiaoVien gv : listGiaoVien)
                        {     
                            String lopChuNhiem ="";
                            Optional<Lop>lopOptional = giaoVienService.findAllLopChuNhiem(gv.getMaGiaoVien(),tab5_year_comboBox.getSelectedItem().toString());
                            if(lopOptional.isPresent())  lopChuNhiem = lopOptional.get().getTenLop();
                            tableModel.addRow(new Object[]{gv.getMaGiaoVien(),
                                                                                        gv.getTenGiaoVien(),
                                                                                        DateAndTimeUtils.convertDateToStr(gv.getNgaySinh()),
                                                                                        gv.getGioiTinh(),
                                                                                        gv.getDiaChi(),
                                                                                        gv.getChucVu(),
                                                                                        gv.getSoDienThoai(),
                                                                                        lopChuNhiem});                                                    
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
    
    //public void showScoreInfoTab(){}
    
    public void showHKTab(List<HanhKiem> listHanhKiem, DefaultTableModel tableModel)
            {
                Optional listHKOptional = Optional.ofNullable(listHanhKiem);
                   if(listHanhKiem.isEmpty() || !listHKOptional.isPresent()) return;
                   else
                       {
                           tableModel.setRowCount(0);
                           for(HanhKiem hk : listHanhKiem)
                               {
                                   tableModel.addRow(new Object[]{hk.getHocSinh().getMaHocSinh(),
                                                                                               hk.getHocSinh().getTenHocSinh(),
                                                                                               hk.getLoiViPham(),
                                                                                               hk.getNghiCoPhep(),
                                                                                               hk.getNghiKhongPhep(),
                                                                                               hanhKiemService.proccessHanhKiem(hk)                                                   
                                   });                                  
                               }
                       }
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
        tab1_search_textField = new javax.swing.JTextField();
        tab1_search_button = new javax.swing.JButton();
        tab1_search_comboBox = new javax.swing.JComboBox<>();
        tab1_Header = new javax.swing.JLabel();
        tab1_year_comboBox = new javax.swing.JComboBox<>();
        tab2 = new javax.swing.JPanel();
        tab2_table_label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab2_table = new javax.swing.JTable();
        tab2_search_button = new javax.swing.JButton();
        tab2_name_textField = new javax.swing.JTextField();
        tab2_name_label = new javax.swing.JLabel();
        tab2_search_textField = new javax.swing.JTextField();
        tab2_birthday_textField = new javax.swing.JTextField();
        tab2_birthday_label = new javax.swing.JLabel();
        tab2_sex_label = new javax.swing.JLabel();
        tab2_address_textField = new javax.swing.JTextField();
        tab2_address_label = new javax.swing.JLabel();
        tab2_parent_textField = new javax.swing.JTextField();
        tab2_parent_label = new javax.swing.JLabel();
        tab2_phone_textField = new javax.swing.JTextField();
        tab2_phone_label = new javax.swing.JLabel();
        tab2_add_button = new javax.swing.JButton();
        tab2_head_label = new javax.swing.JLabel();
        tab2_info_label = new javax.swing.JLabel();
        tab2_stdID_textField = new javax.swing.JTextField();
        tab2_stdID_label = new javax.swing.JLabel();
        tab2_update_button = new javax.swing.JButton();
        tab2_delete_button = new javax.swing.JButton();
        tab2_year_comboBox = new javax.swing.JComboBox<>();
        tab2_sex_comboBox = new javax.swing.JComboBox<>();
        tab2_reset_button = new javax.swing.JButton();
        tab3 = new javax.swing.JPanel();
        tab3_head_label = new javax.swing.JLabel();
        tab3_name_textField = new javax.swing.JTextField();
        tab3_name_label = new javax.swing.JLabel();
        tab3_search_textField12 = new javax.swing.JTextField();
        tab3_stdID_textField = new javax.swing.JTextField();
        tab3_info_label = new javax.swing.JLabel();
        tab3_stdID_label = new javax.swing.JLabel();
        tab3_score1_textField = new javax.swing.JTextField();
        tab3_score1_label = new javax.swing.JLabel();
        tab3_score2_textField = new javax.swing.JTextField();
        tab3_score2_label = new javax.swing.JLabel();
        tab3_score3_textField = new javax.swing.JTextField();
        tab3_score3_label = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tab3_table = new javax.swing.JTable();
        tab3_add_button = new javax.swing.JButton();
        tab3_search_button = new javax.swing.JButton();
        tab3_update_button = new javax.swing.JButton();
        tab3_table_header = new javax.swing.JLabel();
        tab3_year_comboBox = new javax.swing.JComboBox<>();
        tab3_semester_comboBox = new javax.swing.JComboBox<>();
        tab3_subject_comboBox = new javax.swing.JComboBox<>();
        tab4 = new javax.swing.JPanel();
        tab4_head_label = new javax.swing.JLabel();
        tab4_numAllow_textField = new javax.swing.JTextField();
        tab4_year_comboBox = new javax.swing.JComboBox<>();
        tab4_name_textField = new javax.swing.JTextField();
        tab4_numAllow_label = new javax.swing.JLabel();
        tab4_semester_comboBox = new javax.swing.JComboBox<>();
        tab4_numNotAllow_textField = new javax.swing.JTextField();
        tab4_name_label = new javax.swing.JLabel();
        tab4_numNotAllow_label = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tab4_table = new javax.swing.JTable();
        tab4_class_textField = new javax.swing.JTextField();
        tab4_stdID_textField = new javax.swing.JTextField();
        tab4_add_button = new javax.swing.JButton();
        tab4_info_label = new javax.swing.JLabel();
        tab4_stdID_label = new javax.swing.JLabel();
        tab4_search_button = new javax.swing.JButton();
        tab4_fault_textField = new javax.swing.JTextField();
        tab4_update_button = new javax.swing.JButton();
        tab4_fault_label = new javax.swing.JLabel();
        tab4_table_label = new javax.swing.JLabel();
        tab4_reset_button = new javax.swing.JButton();
        tab5 = new javax.swing.JPanel();
        tab5_add_button = new javax.swing.JButton();
        tab5_head_label = new javax.swing.JLabel();
        tab5_info_label = new javax.swing.JLabel();
        tab5_teacherID_textField = new javax.swing.JTextField();
        tab5_table_label = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tab5_table = new javax.swing.JTable();
        tab5_teacherID_label = new javax.swing.JLabel();
        tab5_update_button = new javax.swing.JButton();
        tab5_search_button = new javax.swing.JButton();
        tab5_mainClass_textfield = new javax.swing.JTextField();
        tab5_searchMethod_comboBox = new javax.swing.JComboBox<>();
        tab5_mainClass_label = new javax.swing.JLabel();
        tab5_name_textField = new javax.swing.JTextField();
        tab5_name_label = new javax.swing.JLabel();
        tab5_search_textField = new javax.swing.JTextField();
        tab5_birthday_textField = new javax.swing.JTextField();
        tab5_birtday_textField = new javax.swing.JLabel();
        tab5_sex_label = new javax.swing.JLabel();
        tab5_address_textField = new javax.swing.JTextField();
        tab5_address_label = new javax.swing.JLabel();
        tab5_role_textField = new javax.swing.JTextField();
        tab5_role_label = new javax.swing.JLabel();
        tab5_phone_textField = new javax.swing.JTextField();
        tab5_phone_label = new javax.swing.JLabel();
        tab5_delete_button = new javax.swing.JButton();
        tab5_sex_comboBox = new javax.swing.JComboBox<>();
        tab5_year_comboBox = new javax.swing.JComboBox<>();
        tab5_account_label = new javax.swing.JLabel();
        tab5_account_textField = new javax.swing.JTextField();
        tab5_password_label = new javax.swing.JLabel();
        tab5_password_passwordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phần mềm quản lý đào tạo THCS");
        setIconImage(new ImageIcon("src\\View\\icons\\icons8_school_96px.png").getImage());
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(1280, 780));
        setPreferredSize(new java.awt.Dimension(1380, 800));
        setSize(new java.awt.Dimension(1380, 800));
        getContentPane().setLayout(null);

        sidepane.setBackground(new java.awt.Color(0, 204, 204));
        sidepane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        choose4.setBackground(new java.awt.Color(0, 204, 204));
        choose4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                choose4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                choose4MouseExited(evt);
            }
        });
        sidepane.add(choose4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 230, 60));

        jSeparator1.setBackground(new java.awt.Color(0, 204, 204));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSeparator1.setOpaque(true);
        sidepane.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 230, 10));

        choose1.setBackground(new java.awt.Color(0, 204, 204));
        choose1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                choose1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                choose1MouseExited(evt);
            }
        });
        sidepane.add(choose1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 230, 60));

        jSeparator2.setBackground(new java.awt.Color(0, 204, 204));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setOpaque(true);
        sidepane.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 230, 10));

        choose2.setBackground(new java.awt.Color(0, 204, 204));
        choose2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                choose2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                choose2MouseExited(evt);
            }
        });
        sidepane.add(choose2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 230, 59));

        jSeparator3.setBackground(new java.awt.Color(0, 204, 204));
        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator3.setOpaque(true);
        sidepane.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 230, 10));

        choose3.setBackground(new java.awt.Color(0, 204, 204));
        choose3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                choose3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                choose3MouseExited(evt);
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
        sidepane.add(logOut_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 670, 130, 60));

        choose5.setBackground(new java.awt.Color(0, 204, 204));
        choose5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                choose5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                choose5MouseExited(evt);
            }
        });
        sidepane.add(choose5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 230, 60));

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        sidepane.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 230, 10));

        getContentPane().add(sidepane);
        sidepane.setBounds(0, 0, 270, 780);

        infoTab.setBackground(new java.awt.Color(255, 255, 255));
        infoTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        infoTabHeader.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        infoTabHeader.setForeground(new java.awt.Color(0, 204, 204));
        infoTabHeader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_male_user_100px.png"))); // NOI18N
        infoTabHeader.setText("Thông tin cá nhân");
        infoTab.add(infoTabHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 430, 100));

        infoTab_name_label.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        infoTab_name_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_name_label.setText("Họ  tên : ");
        infoTab.add(infoTab_name_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 80, 30));

        infoTab_sex_textField.setEditable(false);
        infoTab_sex_textField.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_sex_textField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        infoTab_sex_textField.setText("Nam");
        infoTab_sex_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTab_sex_textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoTab_sex_textFieldActionPerformed(evt);
            }
        });
        infoTab.add(infoTab_sex_textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 180, 30));

        infoTab_sex_label.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        infoTab_sex_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_sex_label.setText("Giới tính:");
        infoTab.add(infoTab_sex_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 80, 30));

        infoTab_name_textField.setEditable(false);
        infoTab_name_textField.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_name_textField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        infoTab_name_textField.setText("Đào Huy Chiến");
        infoTab_name_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTab.add(infoTab_name_textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 180, 30));

        infoTab_birthday_label.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        infoTab_birthday_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_birthday_label.setText("Ngày sinh :");
        infoTab.add(infoTab_birthday_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 100, 30));

        infoTab_birthday_textField.setEditable(false);
        infoTab_birthday_textField.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_birthday_textField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        infoTab_birthday_textField.setText("18/08/2001");
        infoTab_birthday_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTab_birthday_textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoTab_birthday_textFieldActionPerformed(evt);
            }
        });
        infoTab.add(infoTab_birthday_textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 180, 30));

        infoTab_phone_label.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        infoTab_phone_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_phone_label.setText("Số điện thoại:");
        infoTab.add(infoTab_phone_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 110, 30));

        infoTab_phone_textField.setEditable(false);
        infoTab_phone_textField.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_phone_textField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        infoTab_phone_textField.setText("0918394508");
        infoTab_phone_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTab.add(infoTab_phone_textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 120, 30));

        infoTab_address_label.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        infoTab_address_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_address_label.setText("Địa chỉ :");
        infoTab.add(infoTab_address_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 100, -1));

        infoTab_address_textField.setEditable(false);
        infoTab_address_textField.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_address_textField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        infoTab_address_textField.setText("Gia Lâm - Hà Nội");
        infoTab_address_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTab.add(infoTab_address_textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, 280, 30));

        infoTab_position_label.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        infoTab_position_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_position_label.setText("Chức vụ :");
        infoTab.add(infoTab_position_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 440, 90, 30));

        infoTab_position_Textfield.setEditable(false);
        infoTab_position_Textfield.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_position_Textfield.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
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
        infoTab_schoolYear_label.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        infoTab_schoolYear_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_schoolYear_label.setText("Năm học:");
        infoTab.add(infoTab_schoolYear_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 80, 30));

        infoTab_schoolYear_textField.setEditable(false);
        infoTab_schoolYear_textField.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_schoolYear_textField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        infoTab_schoolYear_textField.setText("2021-2022");
        infoTab_schoolYear_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTab.add(infoTab_schoolYear_textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, -1, -1));

        infoTab_semester_label.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        infoTab_semester_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_semester_label.setText("Học kỳ :");
        infoTab.add(infoTab_semester_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, -1, -1));

        infoTab_semeter_textField.setEditable(false);
        infoTab_semeter_textField.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_semeter_textField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        infoTab_semeter_textField.setText("I");
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
        infoTab_id_label.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        infoTab_id_label.setForeground(new java.awt.Color(0, 204, 204));
        infoTab_id_label.setText("Mã giáo viên: ");
        infoTab.add(infoTab_id_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 510, -1, -1));

        infoTab_id_textfield.setEditable(false);
        infoTab_id_textfield.setBackground(new java.awt.Color(255, 255, 255));
        infoTab_id_textfield.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        infoTab_id_textfield.setText("GV0001");
        infoTab_id_textfield.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTab.add(infoTab_id_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, -1, -1));

        getContentPane().add(infoTab);
        infoTab.setBounds(270, 0, 1100, 780);

        tab1.setBackground(new java.awt.Color(255, 255, 255));
        tab1.setPreferredSize(new java.awt.Dimension(702, 670));

        tab1_student_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã học sinh", "Tên học sinh", "Giới tính", "Ngày sinh", "Địa chỉ", "Tên phụ huynh", "Số điện thoại", "Lớp"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab1_student_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tab1_student_table.setGridColor(new java.awt.Color(51, 51, 51));
        tab1_student_table.setShowGrid(true);
        tab1_student_table.getTableHeader().setResizingAllowed(false);
        jScrollPane1.setViewportView(tab1_student_table);
        tab1_student_table.getAccessibleContext().setAccessibleParent(tab1_student_table);

        tab1_search_textField.setText("Nhập tên hoặc mã học sinh");

        tab1_search_button.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tab1_search_button.setText("Tìm kiếm");
        tab1_search_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab1_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab1_search_buttonMouseClicked(evt);
            }
        });

        tab1_search_comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm tất cả học sinh", "Theo tên", "Theo mã học sinh" }));

        tab1_Header.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        tab1_Header.setForeground(new java.awt.Color(0, 204, 204));
        tab1_Header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icons8_search_100px.png"))); // NOI18N
        tab1_Header.setText("Tìm kiếm");

        tab1_year_comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo năm học", "2019-2020", "2020-2021", "2021-2022", "2022-2023", "2023-2024" }));

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(tab1_Header, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tab1_search_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tab1Layout.createSequentialGroup()
                                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tab1_search_comboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tab1_year_comboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(tab1_search_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1094, Short.MAX_VALUE))
                .addContainerGap())
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab1_Header)
                .addGap(73, 73, 73)
                .addComponent(tab1_search_textField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tab1Layout.createSequentialGroup()
                        .addComponent(tab1_search_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tab1_year_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tab1_search_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(tab1);
        tab1.setBounds(270, 0, 1100, 780);

        tab2.setBackground(new java.awt.Color(255, 255, 255));
        tab2.setOpaque(false);
        tab2.setPreferredSize(new java.awt.Dimension(702, 670));

        tab2_table_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tab2_table_label.setForeground(new java.awt.Color(0, 204, 204));
        tab2_table_label.setText("Thông tin tìm kiếm");

        tab2_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HS", "Họ và tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Họ tên PH", "SĐT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab2_table.setGridColor(new java.awt.Color(51, 51, 51));
        tab2_table.setShowGrid(true);
        tab2_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab2_tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tab2_table);

        tab2_search_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tab2_search_button.setText("Tìm kiếm");
        tab2_search_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab2_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab2_search_buttonMouseClicked(evt);
            }
        });
        tab2_search_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tab2_search_buttonActionPerformed(evt);
            }
        });

        tab2_name_label.setText("Họ và tên:");

        tab2_search_textField.setText("Nhập lớp");
        tab2_search_textField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab2_search_textFieldMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tab2_search_textFieldMouseReleased(evt);
            }
        });

        tab2_birthday_label.setText("Ngày sinh:");

        tab2_sex_label.setText("Giới tính:");

        tab2_address_label.setText("Địa chỉ:");

        tab2_parent_label.setText("Họ tên phụ huynh:");

        tab2_phone_textField.setToolTipText("");

        tab2_phone_label.setText("SĐT liên lạc:");

        tab2_add_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tab2_add_button.setText("Thêm học sinh");
        tab2_add_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab2_add_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab2_add_buttonMouseClicked(evt);
            }
        });

        tab2_head_label.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        tab2_head_label.setForeground(new java.awt.Color(0, 204, 204));
        tab2_head_label.setText("QUẢN LÝ HỌC SINH");

        tab2_info_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tab2_info_label.setForeground(new java.awt.Color(0, 204, 204));
        tab2_info_label.setText("Thông tin học sinh");

        tab2_stdID_label.setText("Mã học sinh");

        tab2_update_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tab2_update_button.setText("Cập nhật thông tin");
        tab2_update_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab2_update_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tab2_update_buttonActionPerformed(evt);
            }
        });

        tab2_delete_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tab2_delete_button.setText("Xóa học sinh");
        tab2_delete_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab2_delete_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tab2_delete_buttonActionPerformed(evt);
            }
        });

        tab2_year_comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo năm học", "2019-2020", "2020-2021", "2021-2022", "2022-2023", "2023-2024", " " }));
        tab2_year_comboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tab2_sex_comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        tab2_sex_comboBox.setToolTipText("");

        tab2_reset_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tab2_reset_button.setText("Đặt lại");
        tab2_reset_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab2_reset_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab2_reset_buttonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addComponent(tab2_head_label, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab2Layout.createSequentialGroup()
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab2Layout.createSequentialGroup()
                                .addComponent(tab2_sex_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(183, 183, 183))
                            .addGroup(tab2Layout.createSequentialGroup()
                                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tab2Layout.createSequentialGroup()
                                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tab2_phone_label, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tab2_stdID_label, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(28, 28, 28)
                                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tab2_stdID_textField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tab2_phone_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tab2_parent_textField)
                                            .addComponent(tab2_address_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tab2_sex_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(tab2Layout.createSequentialGroup()
                                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tab2_birthday_label, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tab2_name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(35, 35, 35)
                                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tab2_birthday_textField, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tab2_name_textField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)))
                                    .addComponent(tab2_address_label, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tab2_parent_label))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(tab2Layout.createSequentialGroup()
                                .addComponent(tab2_add_button, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(tab2_update_button, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tab2_delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(tab2_reset_button, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(553, 553, 553))))
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(tab2_info_label, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(410, 410, 410)
                .addComponent(tab2_table_label, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tab2_search_textField)
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addComponent(tab2_year_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tab2_search_button)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tab2_address_textField, tab2_birthday_textField, tab2_name_textField, tab2_parent_textField, tab2_phone_textField, tab2_sex_comboBox, tab2_stdID_textField});

        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(tab2_head_label, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab2_search_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tab2_year_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tab2_search_button))
                .addGap(18, 18, 18)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tab2_info_label)
                    .addComponent(tab2_table_label))
                .addGap(25, 25, 25)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab2_name_label)
                            .addComponent(tab2_name_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab2_birthday_label, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab2_birthday_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tab2_sex_comboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(tab2_sex_label))
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab2Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(tab2_address_label))
                            .addGroup(tab2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(tab2_address_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(tab2_parent_label))
                            .addGroup(tab2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(tab2_parent_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab2_phone_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab2_phone_label))
                        .addGap(18, 18, 18)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab2_stdID_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab2_stdID_label))
                        .addGap(313, 313, 313))
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab2_reset_button, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab2_delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab2_update_button, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab2_add_button, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        getContentPane().add(tab2);
        tab2.setBounds(270, 0, 1100, 780);

        tab3.setBackground(new java.awt.Color(255, 255, 255));
        tab3.setPreferredSize(new java.awt.Dimension(702, 670));

        tab3_head_label.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        tab3_head_label.setForeground(new java.awt.Color(0, 204, 204));
        tab3_head_label.setText("QUẢN LÝ ĐIỂM");

        tab3_name_textField.setText("Nhập tên");

        tab3_name_label.setText("Họ và tên:");

        tab3_search_textField12.setText("Nhập tên lớp");
        tab3_search_textField12.setToolTipText("");

        tab3_stdID_textField.setText("Nhập mã HS");

        tab3_info_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tab3_info_label.setForeground(new java.awt.Color(0, 204, 204));
        tab3_info_label.setText("Nhập thông tin về điểm");

        tab3_stdID_label.setText("Mã HS:");

        tab3_score1_textField.setText("Nhập điểm HS 1");

        tab3_score1_label.setText("Điểm hệ số 1:");

        tab3_score2_textField.setText("Nhập điểm HS 2");

        tab3_score2_label.setText("Điểm hệ số 2:");

        tab3_score3_textField.setText("Nhập điểm HS 3");

        tab3_score3_label.setText("Điểm hệ số 3:");

        tab3_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HS", "Họ và tên", "Điểm HS 1", "Điểm GK", "Điểm CK", "TB môn học"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab3_table.setGridColor(new java.awt.Color(51, 51, 51));
        tab3_table.setShowGrid(true);
        tab3_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab3_tableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tab3_table);

        tab3_add_button.setText("Thêm điểm");
        tab3_add_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tab3_search_button.setText("Tìm kiếm");
        tab3_search_button.setToolTipText("");
        tab3_search_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab3_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab3_search_buttonMouseClicked(evt);
            }
        });

        tab3_update_button.setText("Cập nhật điểm");
        tab3_update_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tab3_table_header.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tab3_table_header.setForeground(new java.awt.Color(0, 204, 204));
        tab3_table_header.setText("Thông tin tìm kiếm");

        tab3_year_comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo năm học", "2019-2020", "2020-2021", "2021-2022", "2022-2023", "2023-2024" }));

        tab3_semester_comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo học kỳ", "Học kỳ I", "Học kỳ II" }));

        tab3_subject_comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo môn", " " }));

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab3Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(tab3_info_label, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(312, 312, 312)
                        .addComponent(tab3_table_header, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tab3_head_label, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tab3Layout.createSequentialGroup()
                                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tab3_search_textField12, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                    .addComponent(tab3_search_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tab3_year_comboBox, 0, 113, Short.MAX_VALUE)
                                    .addComponent(tab3_subject_comboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(tab3_semester_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(tab3Layout.createSequentialGroup()
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab3Layout.createSequentialGroup()
                                .addComponent(tab3_score3_label, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tab3_score3_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tab3Layout.createSequentialGroup()
                                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tab3_name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tab3_stdID_label, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tab3_name_textField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tab3_stdID_textField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(tab3Layout.createSequentialGroup()
                                .addComponent(tab3_score1_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(tab3_score1_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tab3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(tab3_score2_label, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tab3_score2_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37))
                    .addGroup(tab3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tab3_add_button, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tab3_update_button, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(247, 247, 247))
        );
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab3_head_label, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tab3_search_textField12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tab3_year_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tab3_semester_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tab3_search_button)
                    .addComponent(tab3_subject_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tab3_info_label, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tab3_table_header, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab3Layout.createSequentialGroup()
                        .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab3_name_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab3_name_label))
                        .addGap(34, 34, 34)
                        .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab3_stdID_label)
                            .addComponent(tab3_stdID_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab3_score1_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab3_score1_label, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab3_score2_label)
                            .addComponent(tab3_score2_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab3_score3_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab3_score3_label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab3_add_button, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab3_update_button, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        getContentPane().add(tab3);
        tab3.setBounds(270, 0, 1100, 780);

        tab4.setBackground(new java.awt.Color(255, 255, 255));
        tab4.setPreferredSize(new java.awt.Dimension(702, 670));

        tab4_head_label.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        tab4_head_label.setForeground(new java.awt.Color(0, 204, 204));
        tab4_head_label.setText("QUẢN LÝ HẠNH KIỂM");

        tab4_year_comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo năm học", "2019-2020", "2020-2021", "2021-2022", "2022-2023", "2023-2024" }));

        tab4_numAllow_label.setText("Có phép:");

        tab4_semester_comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo học kỳ", "Học kỳ I", "Học kỳ II" }));

        tab4_name_label.setText("Họ và tên:");

        tab4_numNotAllow_label.setText("Không phép:");

        tab4_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HS", "Họ và tên", "Lỗi vi phạm", "Có phép", "Không phép", "Hạnh kiểm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab4_table.setGridColor(new java.awt.Color(51, 51, 51));
        tab4_table.setShowGrid(true);
        tab4_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab4_tableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tab4_table);

        tab4_class_textField.setText("Nhập lớp");

        tab4_add_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tab4_add_button.setText("Thêm thông tin HK");
        tab4_add_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab4_add_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab4_add_buttonMouseClicked(evt);
            }
        });

        tab4_info_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tab4_info_label.setForeground(new java.awt.Color(0, 204, 204));
        tab4_info_label.setText("Nhập thông tin về hạnh kiểm");

        tab4_stdID_label.setText("Mã HS:");

        tab4_search_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tab4_search_button.setText("Tìm kiếm");
        tab4_search_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab4_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab4_search_buttonMouseClicked(evt);
            }
        });

        tab4_update_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tab4_update_button.setText("Cập nhật thông tin HK");
        tab4_update_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab4_update_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab4_update_buttonMouseClicked(evt);
            }
        });

        tab4_fault_label.setText("Lỗi vi phạm:");

        tab4_table_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tab4_table_label.setForeground(new java.awt.Color(0, 204, 204));
        tab4_table_label.setText("Thông tin tìm kiếm");

        tab4_reset_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tab4_reset_button.setText("Đặt lại");
        tab4_reset_button.setToolTipText("");
        tab4_reset_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab4_reset_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab4_reset_buttonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tab4Layout = new javax.swing.GroupLayout(tab4);
        tab4.setLayout(tab4Layout);
        tab4Layout.setHorizontalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(tab4Layout.createSequentialGroup()
                                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tab4Layout.createSequentialGroup()
                                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(tab4Layout.createSequentialGroup()
                                                .addGap(17, 17, 17)
                                                .addComponent(tab4_name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab4Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(tab4_numAllow_label, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(tab4_stdID_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(tab4_fault_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(18, 18, 18))
                                    .addGroup(tab4Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(tab4_numNotAllow_label, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tab4_fault_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tab4_numAllow_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tab4_numNotAllow_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tab4_stdID_textField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tab4_name_textField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(tab4Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(tab4_add_button, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tab4_update_button)))
                        .addGap(38, 38, 38))
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(tab4_reset_button, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
            .addGroup(tab4Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(tab4_info_label, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tab4_table_label, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(297, 297, 297))
            .addGroup(tab4Layout.createSequentialGroup()
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tab4_search_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab4Layout.createSequentialGroup()
                                .addComponent(tab4_year_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tab4_semester_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tab4_class_textField)))
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(tab4_head_label, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tab4_fault_label, tab4_name_label, tab4_numAllow_label, tab4_numNotAllow_label, tab4_stdID_label});

        tab4Layout.setVerticalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab4Layout.createSequentialGroup()
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(tab4_class_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab4_name_label)
                            .addComponent(tab4_name_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab4_stdID_label)
                            .addComponent(tab4_stdID_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab4_fault_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab4_fault_label))
                        .addGap(35, 35, 35)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab4_numAllow_label)
                            .addComponent(tab4_numAllow_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab4_numNotAllow_label)
                            .addComponent(tab4_numNotAllow_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab4_update_button, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab4_add_button, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(tab4_reset_button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tab4_head_label, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab4_year_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab4_semester_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(tab4_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab4_info_label, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab4_table_label, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(tab4);
        tab4.setBounds(270, 0, 1100, 780);

        tab5.setBackground(new java.awt.Color(255, 255, 255));
        tab5.setPreferredSize(new java.awt.Dimension(702, 670));

        tab5_add_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tab5_add_button.setText("Thêm giáo viên");
        tab5_add_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tab5_head_label.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        tab5_head_label.setForeground(new java.awt.Color(0, 204, 204));
        tab5_head_label.setText("QUẢN LÝ GIÁO VIÊN");

        tab5_info_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tab5_info_label.setForeground(new java.awt.Color(0, 204, 204));
        tab5_info_label.setText("Thông tin Giáo viên");

        tab5_teacherID_textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tab5_teacherID_textFieldActionPerformed(evt);
            }
        });

        tab5_table_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tab5_table_label.setForeground(new java.awt.Color(0, 204, 204));
        tab5_table_label.setText("Thông tin tìm kiếm");

        tab5_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã GV", "Họ và tên", "Ngày sinh", "Giới tính", "Địa chỉ", "Chức vụ", "SĐT", "Lớp CN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab5_table.setGridColor(new java.awt.Color(51, 51, 51));
        tab5_table.setShowGrid(true);
        tab5_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab5_tableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tab5_table);

        tab5_teacherID_label.setText("Mã giáo viên");

        tab5_update_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tab5_update_button.setText("Cập nhật thông tin");
        tab5_update_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tab5_search_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tab5_search_button.setText("Tìm kiếm");
        tab5_search_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab5_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab5_search_buttonMouseClicked(evt);
            }
        });

        tab5_mainClass_textfield.setToolTipText("");

        tab5_searchMethod_comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm tất cả giáo viên", "Theo tên", "Theo mã GV" }));

        tab5_mainClass_label.setText("Lớp chủ nhiệm");

        tab5_name_label.setText("Họ và tên:");

        tab5_search_textField.setText("Nhập tên, mã GV");

        tab5_birtday_textField.setText("Ngày sinh:");

        tab5_sex_label.setText("Giới tính:");

        tab5_address_label.setText("Địa chỉ:");

        tab5_role_label.setText("Chức vụ");

        tab5_phone_label.setText("SĐT liên lạc:");

        tab5_delete_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tab5_delete_button.setText("Xóa giáo viên");
        tab5_delete_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tab5_sex_comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        tab5_year_comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo năm học", "2019-2020", "2020-2021", "2021-2022", "2022-2023", "2023-2024" }));

        tab5_account_label.setText("Tài khoản:");

        tab5_password_label.setText("Mật khẩu:");

        javax.swing.GroupLayout tab5Layout = new javax.swing.GroupLayout(tab5);
        tab5.setLayout(tab5Layout);
        tab5Layout.setHorizontalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(tab5_head_label, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(620, 620, 620))
                    .addGroup(tab5Layout.createSequentialGroup()
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tab5_searchMethod_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab5_search_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab5Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(tab5_year_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tab5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tab5_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tab5_table_label, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(295, 295, 295))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab5Layout.createSequentialGroup()
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tab5_address_label, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tab5Layout.createSequentialGroup()
                                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tab5_role_label, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tab5_sex_label, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tab5_name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tab5_birtday_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tab5_birthday_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tab5_name_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(tab5_role_textField, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                        .addComponent(tab5_address_textField, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                        .addComponent(tab5_sex_comboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(tab5Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(tab5_info_label, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tab5Layout.createSequentialGroup()
                                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tab5_phone_label, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tab5_mainClass_label, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tab5_teacherID_label, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tab5_account_label, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tab5_password_label, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tab5_teacherID_textField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                    .addComponent(tab5_mainClass_textfield, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                    .addComponent(tab5_phone_textField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tab5_account_textField)
                                    .addComponent(tab5_password_passwordField))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab5Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab5Layout.createSequentialGroup()
                                .addComponent(tab5_add_button, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(tab5_update_button, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(tab5_delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101))))))
        );
        tab5Layout.setVerticalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab5_head_label, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab5Layout.createSequentialGroup()
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tab5_search_textField, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(tab5_year_comboBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab5_searchMethod_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab5_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tab5_info_label)
                        .addGap(20, 20, 20)
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab5_name_label)
                            .addComponent(tab5_name_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab5_birtday_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab5_birthday_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab5_sex_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab5_sex_label))
                        .addGap(18, 18, 18)
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tab5_address_label)
                            .addComponent(tab5_address_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab5_role_label)
                            .addComponent(tab5_role_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab5_phone_label)
                            .addComponent(tab5_phone_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tab5_mainClass_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tab5_mainClass_label))
                        .addGap(132, 132, 132))
                    .addGroup(tab5Layout.createSequentialGroup()
                        .addComponent(tab5_table_label)
                        .addGap(18, 18, 18)
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(tab5Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab5Layout.createSequentialGroup()
                                .addGap(381, 381, 381)
                                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tab5_teacherID_label)
                                    .addComponent(tab5_teacherID_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tab5_update_button, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tab5_add_button, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tab5_account_label)
                                    .addComponent(tab5_account_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tab5_delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))))
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tab5_password_label)
                    .addComponent(tab5_password_passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        getContentPane().add(tab5);
        tab5.setBounds(270, 0, 1100, 780);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void choose1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose1MouseClicked
        choose1.setFont(new Font("Segoe UI",Font.BOLD,18));
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
         choose2.setFont(new Font("Segoe UI",Font.BOLD,18));
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
         choose3.setFont(new Font("Segoe UI",Font.BOLD,18));
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

    private void choose4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose4MouseClicked
        // TODO add your handling code here:
        choose4.setFont(new Font("Segoe UI",Font.BOLD,18));
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
         choose5.setFont(new Font("Segoe UI",Font.BOLD,18));
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
            if (!infoTab_name_textField.equals("")) {
                giaoVienUpdate.setTenGiaoVien(infoTab_name_textField.getText());
            }
            if (!infoTab_sex_textField.equals("")) {
                giaoVienUpdate.setGioiTinh(infoTab_sex_textField.getText());
            }
            if (!infoTab_birthday_textField.equals("")) {
                String birthday = infoTab_birthday_textField.getText();
                if (Controller.Validation.Validator.dateValidator2(birthday)) {
                    try {
                        giaoVienUpdate.setNgaySinh(DateAndTimeUtils.convertStrToDate(birthday));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    infoTab_saving_Option.showMessageDialog(null, "Vui lòng nhập đúng định dạng ngày sinh theo định dạng(Ngày/Tháng/Năm)!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    showTeacherInfoTab();
                }
            }
            giaoVienService.updateGiaoVien(giaoVienUpdate);
        } else {
            infoTab_saving_Option.showMessageDialog(null, "Vui lòng xác nhận lại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        infoTab_edit_button.setVisible(true);
    }//GEN-LAST:event_infoTab_save_buttonActionPerformed

    private void tab1_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1_search_buttonMouseClicked
        if (tab1_year_comboBox.getSelectedIndex() != 0) {
            if (tab1_search_comboBox.getSelectedIndex() == 0) {
                showStudentInfo(hocSinhService.findAll(), tab1_student_tableModel);
            } else if (tab1_search_comboBox.getSelectedIndex() == 1) {
                if (hocSinhService.findByTenHocSinh(tab1_search_textField.getText()).size() <= 0) {
                    new JOptionPane().showMessageDialog(null, "Không tìm thấy kết quả phù hợp", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    showStudentInfo(hocSinhService.findByTenHocSinh(tab1_search_textField.getText()), tab1_student_tableModel);
                }
            } else {
                Optional<HocSinh> hocSinhOptional = hocSinhService.findByMaHocSinh(tab1_search_textField.getText());
                if (hocSinhOptional.isPresent()) {
                    showStudentInfo(List.of(hocSinhOptional.get()), tab1_student_tableModel);
                } else {
                    new JOptionPane().showMessageDialog(null, "Không tìm thấy học sinh phù hợp", "Cảnh báo", JOptionPane.INFORMATION_MESSAGE);
                    showStudentInfo(List.of(), tab1_student_tableModel);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn năm học", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_tab1_search_buttonMouseClicked

    private void tab2_search_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tab2_search_buttonActionPerformed
        String lopHoc = "";
        String namHoc = "";
        if (!tab2_search_textField.equals("")) {
            lopHoc = tab2_search_textField.getText();
        } else {
            new JOptionPane().showMessageDialog(null, "Vui lòng nhập tên lớp", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        if (tab2_year_comboBox.getSelectedIndex() != 0) {
            namHoc = tab2_year_comboBox.getSelectedItem().toString();
        } else {
            new JOptionPane().showMessageDialog(null, "Vui lòng chọn năm học", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        Optional<Lop> lopHocOptional = lopService.findLop(lopHoc, namHoc);
        if (lopHocOptional.isPresent()) {
            showStudentInfo(hocSinhService.findByLop(lopHocOptional.get()), tab2_tableModel);
        } else {
            new JOptionPane().showMessageDialog(null, "Không tìm thấy lớp ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            tab2_tableModel.setRowCount(0);
        }
    }//GEN-LAST:event_tab2_search_buttonActionPerformed

    private void tab2_search_textFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2_search_textFieldMouseClicked
        tab2_search_textField.setText("");
    }//GEN-LAST:event_tab2_search_textFieldMouseClicked

    private void tab2_search_textFieldMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2_search_textFieldMouseReleased
        tab2_search_textField.setText("Nhập tên, mã HS, hoặc lớp");
    }//GEN-LAST:event_tab2_search_textFieldMouseReleased

    private void tab2_add_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2_add_buttonMouseClicked
        HocSinh hocSinh = new HocSinh();
        String maHocSinh = tab2_stdID_textField.getText();
        String tenHocSinh = tab2_name_textField.getText();
        String ngaySinh = tab2_birthday_textField.getText();
        String gioiTinh = tab2_sex_comboBox.getSelectedItem().toString();
        String diaChi = tab2_address_textField.getText();
        String tenPhuHuynh = tab2_parent_textField.getText();
        String soDienThoai = tab2_phone_textField.getText();
        boolean infoCheck = true;
        boolean addCheck = true;
        if (!maHocSinh.equals("")) {
            if (hocSinhService.findByMaHocSinh(maHocSinh).isPresent()) {
                new JOptionPane().showMessageDialog(null, "Học sinh đã tồn tại !");
            } else {
                hocSinh.setMaHocSinh(maHocSinh);
                if (!tenHocSinh.equals("")) {
                    hocSinh.setTenHocSinh(tenHocSinh);
                } else {
                    infoCheck = false;
                }
                if (!ngaySinh.equals("")) {
                    if (Controller.Validation.Validator.dateValidator2(ngaySinh)) {
                        try {
                            hocSinh.setNgaySinh(DateAndTimeUtils.convertStrToDate(ngaySinh));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        new JOptionPane().showMessageDialog(null, "Vui lòng nhập đúng định dạng ngày tháng (dd/MM/YYYY)");
                        addCheck = false;
                    }
                } else {
                    infoCheck = false;
                }
                hocSinh.setGioiTinh(gioiTinh);
                if (!diaChi.equals("")) {
                    hocSinh.setDiaChi(diaChi);
                } else {
                    infoCheck = false;
                }
                if (!tenPhuHuynh.equals("")) {
                    hocSinh.setTenPhuHuynh(tenPhuHuynh);
                } else {
                    infoCheck = false;
                }
                if (!soDienThoai.equals("")) {
                    if (Controller.Validation.Validator.phoneValidator(soDienThoai)) {
                        hocSinh.setSoDienThoai(soDienThoai);
                    } else {
                        new JOptionPane().showMessageDialog(null, "Số điện thoại không hợp lệ vui lòng nhập lại");
                        addCheck = false;
                    }
                } else {
                    infoCheck = false;
                }
            }
        } else {
            infoCheck = false;
        }
        if (infoCheck == false)
            new JOptionPane().showMessageDialog(null, "Vui lòng nhập đủ thông tin học sinh !");
        else {
            if (addCheck == true) {
                if (lopService.findLop(tab2_search_textField.getText(), tab2_year_comboBox.getSelectedItem().toString()).isPresent()) {
                    new JOptionPane().showMessageDialog(null, "Thêm thành công học sinh " + tenHocSinh);
                    hocSinhService.addHocSinh(hocSinh);
                    hocService.addHoc(tab2_search_textField.getText(), tab2_year_comboBox.getSelectedItem().toString(), hocSinh.getMaHocSinh());
                } else {
                    new JOptionPane().showMessageDialog(null, "Không tìm thấy lớp", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                tab2_search_buttonMouseClicked(evt);
            }
        }
    }//GEN-LAST:event_tab2_add_buttonMouseClicked

    private void tab2_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2_tableMouseClicked
        if(tab2_table.getSelectedRowCount()==1)
            {
                int selectedRow = tab2_table.getSelectedRow();
                String maHocSinh = tab2_table.getValueAt(selectedRow,0).toString();
                HocSinh hocSinhSelected = hocSinhService.findByMaHocSinh(maHocSinh).get();
                tab2_name_textField.setText(hocSinhSelected.getTenHocSinh());
                tab2_sex_comboBox.setSelectedIndex(hocSinhSelected.getGioiTinh().equals("Nam")?0:1);
                tab2_birthday_textField.setText(DateAndTimeUtils.convertDateToStr(hocSinhSelected.getNgaySinh()));
                tab2_address_textField.setText(hocSinhSelected.getDiaChi());
                tab2_parent_textField.setText(hocSinhSelected.getTenPhuHuynh());
                tab2_phone_textField.setText(hocSinhSelected.getSoDienThoai());
                tab2_stdID_textField.setText(hocSinhSelected.getMaHocSinh());                                         
            }
    }//GEN-LAST:event_tab2_tableMouseClicked

    private void tab3_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3_search_buttonMouseClicked
        
    }//GEN-LAST:event_tab3_search_buttonMouseClicked

    // không lỗi 
    private void tab4_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4_tableMouseClicked
       if(tab4_table.getSelectedRowCount() ==1)
           {
               int selectedRow = tab4_table.getSelectedRow();
               String maHocSinh = tab4_table.getValueAt(selectedRow,0).toString();
               HocSinh hocSinhSelected = hocSinhService.findByMaHocSinh(maHocSinh).get();
               tab4_name_textField.setText(hocSinhSelected.getTenHocSinh());
               tab4_stdID_textField.setText(hocSinhSelected.getMaHocSinh());
               tab4_fault_textField.setText(tab4_table.getValueAt(selectedRow, 2).toString());
               tab4_numAllow_textField.setText(tab4_table.getValueAt(selectedRow,3).toString());
               tab4_numNotAllow_textField.setText(tab4_table.getValueAt(selectedRow,4).toString());
           }
    }//GEN-LAST:event_tab4_tableMouseClicked

    // không lỗi
    private void tab5_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab5_tableMouseClicked
      if(tab5_table.getSelectedRowCount() == 1 )
          {
              int selectedRow = tab5_table.getSelectedRow();
              String maGiaoVien = tab5_table.getValueAt(selectedRow,0).toString();
              GiaoVien giaoVienSelected  = giaoVienService.findByMaGiaoVien(maGiaoVien).get();
              tab5_teacherID_textField.setText(giaoVienSelected.getMaGiaoVien());
              tab5_name_textField.setText(giaoVienSelected.getTenGiaoVien());
             tab5_birthday_textField.setText(tab5_table.getValueAt(selectedRow,2).toString());
              tab5_sex_comboBox.setSelectedIndex(giaoVienSelected.getGioiTinh().equals("Nam")?0:1);
              tab5_address_textField.setText(giaoVienSelected.getDiaChi());
              tab5_phone_textField.setText(giaoVienSelected.getSoDienThoai());
              tab5_role_textField.setText(giaoVienSelected.getChucVu());   
              tab5_mainClass_textfield.setText(tab5_table.getValueAt(selectedRow,7).toString());
          }
    }//GEN-LAST:event_tab5_tableMouseClicked

    private void tab5_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab5_search_buttonMouseClicked
             if(tab5_year_comboBox.getSelectedIndex()!=0)
                 {
                     if(tab5_searchMethod_comboBox.getSelectedIndex()==0)
                         {
                             if(giaoVienService.findAllGiaoVien().isEmpty()) new JOptionPane().showMessageDialog(null,"Không tìm thấy giáo viên nào!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                             showTeacherInfo(giaoVienService.findAllGiaoVien(), tab5_tableModel);
                         }
                     else if(tab5_searchMethod_comboBox.getSelectedIndex()==1)
                         {
                             if(!tab5_search_textField.getText().equals(""))
                             showTeacherInfo(giaoVienService.findByTenGiaoVien(tab5_search_textField.getText()), tab5_tableModel);
                             else JOptionPane.showMessageDialog(null, "Vui lòng nhập tên giáo viên cần tìm");
                         }
                     else {
                         if(!tab5_search_textField.getText().equals(""))
                             showTeacherInfo(List.of(giaoVienService.findByMaGiaoVien(tab5_search_textField.getText()).get()), tab5_tableModel);
                         else new JOptionPane().showMessageDialog(null,"Vui lòng nhập mã giáo viên!","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                     }
                 } else new JOptionPane().showMessageDialog(null,"Vui lòng chọn năm học !","Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_tab5_search_buttonMouseClicked

    // không lỗi
    private void tab4_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4_search_buttonMouseClicked
            Optional<Lop> lopOptional = lopService.findLop(tab4_class_textField.getText(), tab4_year_comboBox.getSelectedItem().toString());
            if(!lopOptional.isPresent()) 
                {
                   new JOptionPane().showMessageDialog(null,"Không tìm thấy lớp vui lòng kiểm tra lại thông tin !");
                   return;
                }
            List<HocSinh> listHocSinh = hocSinhService.findByLop(lopOptional.get());
            List<HanhKiem> listHanhKiem = new ArrayList<>();
            String hocKy ="";
            if(tab4_semester_comboBox.getSelectedIndex()==1) hocKy ="I";
            else if (tab4_semester_comboBox.getSelectedIndex()==2) hocKy ="II";
            for(HocSinh hs : listHocSinh)
                {
                    Optional<HanhKiem> hanhKiemOptional = hanhKiemService.findHanhKiem(hs.getMaHocSinh(),tab4_year_comboBox.getSelectedItem().toString(),
                                                                                                                                                           hocKy);
                    if(hanhKiemOptional.isPresent())
                    listHanhKiem.add(hanhKiemOptional.get());
                }
            showHKTab(listHanhKiem,tab4_tableModel);
    }//GEN-LAST:event_tab4_search_buttonMouseClicked

    private void tab3_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3_tableMouseClicked
          if(tab3_table.getSelectedRowCount()==1)
           {
               int selectedRow = tab3_table.getSelectedRow();
               String maHocSinh = tab3_table.getValueAt(selectedRow,0).toString();
               HocSinh hocSinhSelected = hocSinhService.findByMaHocSinh(maHocSinh).get();
               tab3_name_textField.setText(hocSinhSelected.getTenHocSinh());
               tab3_stdID_textField.setText(hocSinhSelected.getMaHocSinh());
               tab3_score1_textField.setText(tab3_table.getValueAt(selectedRow,2).toString());
               tab3_score2_textField.setText(tab3_table.getValueAt(selectedRow, 3).toString());
               tab3_score3_textField.setText(tab3_table.getValueAt(selectedRow, 4).toString());
           }
    }//GEN-LAST:event_tab3_tableMouseClicked

    private void tab4_add_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4_add_buttonMouseClicked
        HanhKiem hanhKiem = new HanhKiem();
        String namHoc ="";
        String hocKy=""; 
        String lopHoc ="";
        boolean infoCheck =true;
        boolean addCheck = true;
        boolean existCheck = true;
        if(!tab4_class_textField.getText().equals("")) lopHoc=tab4_class_textField.getText();
        else infoCheck=false;
        if(tab4_year_comboBox.getSelectedIndex()!=0)  namHoc =tab4_year_comboBox.getSelectedItem().toString();    
        else infoCheck=false;
        if(tab4_semester_comboBox.getSelectedIndex()!=0)
        hocKy = tab4_semester_comboBox.getSelectedIndex()==1?"I":"II";
        else infoCheck=false;
        hanhKiem.setNamHoc(namHoc);
        hanhKiem.setHocKy(hocKy);
        if(hanhKiemService.findHanhKiem(tab4_stdID_textField.getText(), namHoc, hocKy).isPresent())
            {
                new JOptionPane().showMessageDialog(null, "Đã tồn tại kết quả hạnh kiểm");
                return;
            }
        if(!tab4_stdID_textField.equals(""))
            {
                Optional<HocSinh> hocSinhOptional = hocSinhService.findByMaHocSinh(tab4_stdID_textField.getText());    
                Optional<Hoc> hocOptional = hocService.findHoc(lopHoc, namHoc,tab4_stdID_textField.getText());
                if(!hocSinhOptional.isPresent())
                    {
                    new JOptionPane().showMessageDialog(null,"Không tìm thấy học sinh này");
                    existCheck=false;
                    }
                else if(!hocOptional.isPresent())
                    {
                    new JOptionPane().showMessageDialog(null, "Học sinh không thuộc lớp này !");
                    existCheck =false;
                    }
                else {
                    hanhKiem.setHocSinh(hocSinhOptional.get());
                    if(tab4_fault_textField.getText().equals("")) hanhKiem.setLoiViPham(" ");
                    else hanhKiem.setLoiViPham(tab4_fault_textField.getText());
                    try{
                    if(!tab4_numAllow_textField.getText().equals("")) 
                     hanhKiem.setNghiCoPhep(Integer.parseInt(tab4_numAllow_textField.getText()));
                    else infoCheck =false;
                     if(!tab4_numNotAllow_textField.getText().equals("")) 
                     hanhKiem.setNghiCoPhep(Integer.parseInt(tab4_numNotAllow_textField.getText()));
                     else infoCheck=false;  }
                    catch(Exception exp )
                        {
                            addCheck =false;
                            exp.printStackTrace();
                        }
                }
            }else infoCheck =false;
        if(infoCheck==false)
            {
                new JOptionPane().showMessageDialog(null,"Vui lòng chọn và nhập đủ thông tin !");
            }else{
            if(addCheck && existCheck)
                {
                    hanhKiemService.addHanhKiem(hanhKiem);
                }
          }
        tab4_search_buttonMouseClicked(evt);
    }//GEN-LAST:event_tab4_add_buttonMouseClicked

    private void tab4_update_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4_update_buttonMouseClicked
        if(tab4_table.getSelectedRowCount()!=1)
            {
                new JOptionPane().showMessageDialog(null,"Vui lòng chọn một học sinh để cập nhật hạnh kiểm");
            }
        else {
            int selectedRow = tab4_table.getSelectedRow();
            String namHoc =tab4_year_comboBox.getSelectedItem().toString();
            String hocKy  = "";
            if(tab4_semester_comboBox.getSelectedIndex()!=0)
                hocKy = tab4_semester_comboBox.getSelectedIndex()==1?"I":"II";
            String maHocSinh = tab4_table.getValueAt(selectedRow,0).toString();
            HanhKiem  hanhKiemUpdate = hanhKiemService.findHanhKiem(maHocSinh, namHoc, hocKy).get();
            boolean addCheck =true;
            if(!tab4_name_textField.equals("")) hanhKiemUpdate.getHocSinh().setTenHocSinh(tab4_name_textField.getText());
            hanhKiemUpdate.setLoiViPham(tab4_fault_textField.getText());
            try {
                if(!tab4_numAllow_textField.equals("")) hanhKiemUpdate.setNghiCoPhep(Integer.parseInt(tab4_numAllow_textField.getText()));
                if(!tab4_numNotAllow_textField.equals("")) hanhKiemUpdate.setNghiKhongPhep(Integer.parseInt(tab4_numNotAllow_textField.getText()));
            } catch (Exception e) {
                addCheck =false;
                e.printStackTrace();
            }
            if(addCheck==false) new JOptionPane().showMessageDialog(null,"Vui lòng kiểm tra lại số buổi nghỉ ( là các số tự nhiên)");
            else {
                hanhKiemService.updateHanhKiem(hanhKiemUpdate);
            }
        }
        tab4_search_buttonMouseClicked(evt);
    }//GEN-LAST:event_tab4_update_buttonMouseClicked

    private void tab5_teacherID_textFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tab5_teacherID_textFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tab5_teacherID_textFieldActionPerformed

    private void tab2_update_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tab2_update_buttonActionPerformed
         if(tab2_table.getSelectedRowCount()!=1)
            {
                new JOptionPane().showMessageDialog(null, "Vui lòng chọn một học sinh để cập nhật ");
            }
        else {
            int selectedRow = tab2_table.getSelectedRow();
            String maHocSinh = tab2_table.getValueAt(selectedRow,0).toString();
            HocSinh hocSinhUpdate = hocSinhService.findByMaHocSinh(maHocSinh).get();
            if(!tab2_name_textField.getText().equals("")) hocSinhUpdate.setTenHocSinh(tab2_name_textField.getText());
            if(!tab2_birthday_textField.equals("")) {
                if(!Controller.Validation.Validator.dateValidator2(tab2_birthday_textField.getText()))
                    new JOptionPane().showMessageDialog(null,"Vui lòng kiểm tra lại ngày sinh (dd/MM/yyyy)");
                else 
                    try {
                    hocSinhUpdate.setNgaySinh(DateAndTimeUtils.convertStrToDate(tab2_birthday_textField.getText()));
                } catch (Exception e) {
                    e.printStackTrace();
                }   
            }
            hocSinhUpdate.setGioiTinh(tab2_sex_comboBox.getSelectedItem().toString());
            if(!tab2_address_textField.getText().equals("")) hocSinhUpdate.setDiaChi(tab2_address_textField.getText());
            if(!tab2_parent_textField.getText().equals("")) hocSinhUpdate.setTenPhuHuynh(tab2_parent_textField.getText());
            if(!tab2_phone_textField.getText().equals(""))
                {
                    if(Controller.Validation.Validator.phoneValidator(tab2_phone_textField.getText()))
                        hocSinhUpdate.setSoDienThoai(tab2_phone_textField.getText());
                    else new JOptionPane().showMessageDialog(null,"Vui lòng kiểm tra lại định dạng số điện thoại");
                }
            if(!tab2_stdID_textField.equals("")  && hocSinhService.findByMaHocSinh(tab2_stdID_textField.getText()).isPresent())
                hocSinhUpdate.setMaHocSinh(tab2_stdID_textField.getText());
            else new JOptionPane().showMessageDialog(null, "Mã học sinh không hợp lệ chưa tổn tại !");
             hocSinhService.updateHocSinh(hocSinhUpdate);
             }
         tab2_search_buttonActionPerformed(evt);
    }//GEN-LAST:event_tab2_update_buttonActionPerformed

    private void tab2_reset_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2_reset_buttonMouseClicked
        tab2_name_textField.setText("");
        tab2_birthday_textField.setText("");
        tab2_address_textField.setText("");
        tab2_parent_textField.setText("");
        tab2_stdID_textField.setText("");
        tab2_phone_textField.setText("");
        
    }//GEN-LAST:event_tab2_reset_buttonMouseClicked

    private void tab4_reset_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4_reset_buttonMouseClicked
        tab4_name_textField.setText("");
        tab4_stdID_textField.setText("");
        tab4_fault_textField.setText("");
        tab4_numAllow_textField.setText("");
        tab4_numNotAllow_textField.setText("");
    }//GEN-LAST:event_tab4_reset_buttonMouseClicked

    private void tab2_delete_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tab2_delete_buttonActionPerformed
       if(tab2_table.getSelectedRowCount()!=1)
            new JOptionPane().showMessageDialog(null,"Vui lòng chọn một học sinh muốn xóa");
        else {
            int selectedRow = tab2_table.getSelectedRow();
            String maHocSinh = tab2_table.getValueAt(selectedRow, 0).toString();
            Optional<Hoc> hocOptional = hocService.findHoc(tab2_search_textField.getText(),tab2_year_comboBox.getSelectedItem().toString(), maHocSinh);
            if(!hocOptional.isPresent()) new JOptionPane().showMessageDialog(null, "Học sinh đã chọn không học lớp này");
            else hocService.deleteHoc(hocOptional.get());
            boolean deleteCheck = hocSinhService.deleteHocSinh(maHocSinh);   
            System.out.println(deleteCheck);          
            new JOptionPane().showMessageDialog(null,"Xóa thành công!");    
            tab2_search_buttonActionPerformed(evt);
       }
    }//GEN-LAST:event_tab2_delete_buttonActionPerformed

    private void tab2_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2_search_buttonMouseClicked
         String lopHoc ="";
        String namHoc ="";
        if(!tab2_search_textField.equals("")) lopHoc =tab2_search_textField.getText();
        else new JOptionPane().showMessageDialog(null,"Vui lòng nhập tên lớp","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        if(tab2_year_comboBox.getSelectedIndex()!=0)  namHoc =tab2_year_comboBox.getSelectedItem().toString();
        else new JOptionPane().showMessageDialog(null,"Vui lòng chọn năm học","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        Optional<Lop> lopHocOptional = lopService.findLop(lopHoc,namHoc);
        if(lopHocOptional.isPresent())
            {
                showStudentInfo(hocSinhService.findByLop(lopHocOptional.get()), tab2_tableModel);
            }
        else {
            new JOptionPane().showMessageDialog(null,"Không tìm thấy lớp ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            tab2_tableModel.setRowCount(0);
        }
    }//GEN-LAST:event_tab2_search_buttonMouseClicked

    private void choose4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose4MouseEntered
       choose4.setFont(new Font("Segoe UI",Font.BOLD,18));
    }//GEN-LAST:event_choose4MouseEntered

    private void choose1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose1MouseEntered
       choose1.setFont(new Font("Segoe UI",Font.BOLD,18));
    }//GEN-LAST:event_choose1MouseEntered

    private void choose2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose2MouseEntered
        choose2.setFont(new Font("Segoe UI",Font.BOLD,18));
    }//GEN-LAST:event_choose2MouseEntered

    private void choose3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose3MouseEntered
        choose3.setFont(new Font("Segoe UI",Font.BOLD,18));
    }//GEN-LAST:event_choose3MouseEntered

    private void choose5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose5MouseEntered
        choose5.setFont(new Font("Segoe UI",Font.BOLD,18));
    }//GEN-LAST:event_choose5MouseEntered

    private void choose4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose4MouseExited
        choose4.setFont(new Font("Segoe UI",Font.PLAIN,20));
    }//GEN-LAST:event_choose4MouseExited

    private void choose1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose1MouseExited
        choose1.setFont(new Font("Segoe UI",Font.PLAIN,20));
    }//GEN-LAST:event_choose1MouseExited

    private void choose2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose2MouseExited
        choose2.setFont(new Font("Segoe UI",Font.PLAIN,20));
    }//GEN-LAST:event_choose2MouseExited

    private void choose3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose3MouseExited
        choose3.setFont(new Font("Segoe UI",Font.PLAIN,20));
    }//GEN-LAST:event_choose3MouseExited

    private void choose5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choose5MouseExited
        choose5.setFont(new Font("Segoe UI",Font.PLAIN,20));
    }//GEN-LAST:event_choose5MouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JButton logOut_btn;
    private javax.swing.JPanel sidepane;
    private javax.swing.JPanel tab1;
    private javax.swing.JLabel tab1_Header;
    private javax.swing.JButton tab1_search_button;
    private javax.swing.JComboBox<String> tab1_search_comboBox;
    private javax.swing.JTextField tab1_search_textField;
    private javax.swing.JTable tab1_student_table;
    private javax.swing.JComboBox<String> tab1_year_comboBox;
    private javax.swing.JPanel tab2;
    private javax.swing.JButton tab2_add_button;
    private javax.swing.JLabel tab2_address_label;
    private javax.swing.JTextField tab2_address_textField;
    private javax.swing.JLabel tab2_birthday_label;
    private javax.swing.JTextField tab2_birthday_textField;
    private javax.swing.JButton tab2_delete_button;
    private javax.swing.JLabel tab2_head_label;
    private javax.swing.JLabel tab2_info_label;
    private javax.swing.JLabel tab2_name_label;
    private javax.swing.JTextField tab2_name_textField;
    private javax.swing.JLabel tab2_parent_label;
    private javax.swing.JTextField tab2_parent_textField;
    private javax.swing.JLabel tab2_phone_label;
    private javax.swing.JTextField tab2_phone_textField;
    private javax.swing.JButton tab2_reset_button;
    private javax.swing.JButton tab2_search_button;
    private javax.swing.JTextField tab2_search_textField;
    private javax.swing.JComboBox<String> tab2_sex_comboBox;
    private javax.swing.JLabel tab2_sex_label;
    private javax.swing.JLabel tab2_stdID_label;
    private javax.swing.JTextField tab2_stdID_textField;
    private javax.swing.JTable tab2_table;
    private javax.swing.JLabel tab2_table_label;
    private javax.swing.JButton tab2_update_button;
    private javax.swing.JComboBox<String> tab2_year_comboBox;
    private javax.swing.JPanel tab3;
    private javax.swing.JButton tab3_add_button;
    private javax.swing.JLabel tab3_head_label;
    private javax.swing.JLabel tab3_info_label;
    private javax.swing.JLabel tab3_name_label;
    private javax.swing.JTextField tab3_name_textField;
    private javax.swing.JLabel tab3_score1_label;
    private javax.swing.JTextField tab3_score1_textField;
    private javax.swing.JLabel tab3_score2_label;
    private javax.swing.JTextField tab3_score2_textField;
    private javax.swing.JLabel tab3_score3_label;
    private javax.swing.JTextField tab3_score3_textField;
    private javax.swing.JButton tab3_search_button;
    private javax.swing.JTextField tab3_search_textField12;
    private javax.swing.JComboBox<String> tab3_semester_comboBox;
    private javax.swing.JLabel tab3_stdID_label;
    private javax.swing.JTextField tab3_stdID_textField;
    private javax.swing.JComboBox<String> tab3_subject_comboBox;
    private javax.swing.JTable tab3_table;
    private javax.swing.JLabel tab3_table_header;
    private javax.swing.JButton tab3_update_button;
    private javax.swing.JComboBox<String> tab3_year_comboBox;
    private javax.swing.JPanel tab4;
    private javax.swing.JButton tab4_add_button;
    private javax.swing.JTextField tab4_class_textField;
    private javax.swing.JLabel tab4_fault_label;
    private javax.swing.JTextField tab4_fault_textField;
    private javax.swing.JLabel tab4_head_label;
    private javax.swing.JLabel tab4_info_label;
    private javax.swing.JLabel tab4_name_label;
    private javax.swing.JTextField tab4_name_textField;
    private javax.swing.JLabel tab4_numAllow_label;
    private javax.swing.JTextField tab4_numAllow_textField;
    private javax.swing.JLabel tab4_numNotAllow_label;
    private javax.swing.JTextField tab4_numNotAllow_textField;
    private javax.swing.JButton tab4_reset_button;
    private javax.swing.JButton tab4_search_button;
    private javax.swing.JComboBox<String> tab4_semester_comboBox;
    private javax.swing.JLabel tab4_stdID_label;
    private javax.swing.JTextField tab4_stdID_textField;
    private javax.swing.JTable tab4_table;
    private javax.swing.JLabel tab4_table_label;
    private javax.swing.JButton tab4_update_button;
    private javax.swing.JComboBox<String> tab4_year_comboBox;
    private javax.swing.JPanel tab5;
    private javax.swing.JLabel tab5_account_label;
    private javax.swing.JTextField tab5_account_textField;
    private javax.swing.JButton tab5_add_button;
    private javax.swing.JLabel tab5_address_label;
    private javax.swing.JTextField tab5_address_textField;
    private javax.swing.JLabel tab5_birtday_textField;
    private javax.swing.JTextField tab5_birthday_textField;
    private javax.swing.JButton tab5_delete_button;
    private javax.swing.JLabel tab5_head_label;
    private javax.swing.JLabel tab5_info_label;
    private javax.swing.JLabel tab5_mainClass_label;
    private javax.swing.JTextField tab5_mainClass_textfield;
    private javax.swing.JLabel tab5_name_label;
    private javax.swing.JTextField tab5_name_textField;
    private javax.swing.JLabel tab5_password_label;
    private javax.swing.JPasswordField tab5_password_passwordField;
    private javax.swing.JLabel tab5_phone_label;
    private javax.swing.JTextField tab5_phone_textField;
    private javax.swing.JLabel tab5_role_label;
    private javax.swing.JTextField tab5_role_textField;
    private javax.swing.JComboBox<String> tab5_searchMethod_comboBox;
    private javax.swing.JButton tab5_search_button;
    private javax.swing.JTextField tab5_search_textField;
    private javax.swing.JComboBox<String> tab5_sex_comboBox;
    private javax.swing.JLabel tab5_sex_label;
    private javax.swing.JTable tab5_table;
    private javax.swing.JLabel tab5_table_label;
    private javax.swing.JLabel tab5_teacherID_label;
    private javax.swing.JTextField tab5_teacherID_textField;
    private javax.swing.JButton tab5_update_button;
    private javax.swing.JComboBox<String> tab5_year_comboBox;
    // End of variables declaration//GEN-END:variables
}
