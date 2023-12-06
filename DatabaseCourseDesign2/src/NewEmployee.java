 
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class NewEmployee extends JFrame implements ActionListener {
    // 定义组件
    JLabel jLNewEmployee = null;//新员工信息
    JLabel jLText = null;//注释
    JLabel jLsID = null;//工号
    JLabel jLsPassword = null;//密码
    JLabel jLsAuthority = null;//权限
    JLabel jLsName = null;//姓名
    JLabel jLsSex = null;//性别
    JLabel jLsBirthday = null;//生日
    JLabel jLsDepartment = null;//部门
    JLabel jLsJob = null;//职务
    JLabel jLsEdu_Level = null;//教育水平
    JLabel jLsSpecialty = null;//专业技能
    JLabel jLsAddress = null;//住址
    JLabel jLsTel = null;//电话
    JLabel jLsEmail = null;//邮箱
    JLabel jLsState = null;//状态
    JLabel jLsRemark = null;//备注
 
    JTextField jTFsID = null;//工号
    JTextField jTFsPassword = null;//密码
    JTextField jTFsName = null;//姓名
    JTextField jTFsSex = null;//性别
    JTextField jTFsBirthday = null;//生日
    JTextField jTFsSpecialty = null;//专业技能
    JTextField jTFsAddress = null;//住址
    JTextField jTFsTel = null;//电话
    JTextField jTFsEmail = null;//邮箱
    JTextField jTFsState = null;//状态
    JTextField jTFsRemark = null;//备注
 
    JComboBox<String> jCBAuthority = null;//权限
    JComboBox<String> jCBDepartment = null;//部门
    JComboBox<String> jCBJob = null;//职务
    JComboBox<String> jCBEdu_Level = null;//教育水平
    String jCBAuthorityStr="普通员工";
    String jCBDepartmentStr="0-生产部";
    String jCBJobStr="0-员工";
    String jCBEdu_LevelStr="0-小学";
    JButton jBInsert = null;//返回
    //JButton jBDeleteCurrentRecord = null;//删除当前记录
    //JButton jBDeleteAllRecords = null;//删除所有记录
 
    //JComboBox jCBSelectQueryField = null;
 
    JPanel jP1, jP2,jP3, jP4, jP5 = null;
    JPanel jP = null;
    DefaultTableModel studentTableModel = null;
 
    JScrollPane NewEmployeeJScrollPane = null;
 
    private static DbProcess NdbProcess;
    private static PersonnelChange P;
    private static Encryption encryption;
    private static int sid;
    // 构造函数
    public NewEmployee(int id,PersonnelChange P) {
        NewEmployee.encryption=new Encryption();
        NewEmployee.P=P;
        NewEmployee.sid=id;
        // 创建标签组件
        jLNewEmployee = new JLabel("新员工信息");
        jLNewEmployee.setFont(new Font("微软雅黑",Font.BOLD,25));
        jLText = new JLabel("注意：带*的为必填项          ");
        jLText.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLText.setForeground(Color.red);
        jLsID = new JLabel("工号*");
        jLsPassword = new JLabel("密码*");
        jLsAuthority = new JLabel("权限*");
        jLsName = new JLabel("姓名*");
        jLsSex = new JLabel("性别*");
        jLsBirthday = new JLabel("生日*");
        jLsDepartment = new JLabel("部门*");
        jLsJob = new JLabel("职务*");
        jLsEdu_Level = new JLabel("教育水平*");
        jLsSpecialty = new JLabel("专业技能*");
        jLsAddress = new JLabel("住址*");
        jLsTel = new JLabel("电话*");
        jLsEmail = new JLabel("邮箱*");
        jLsState = new JLabel("状态*");
        jLsRemark = new JLabel("备注");
        NdbProcess = new DbProcess();
        jTFsID = new JTextField(15);//工号
        jTFsID.setEditable(false);
        jTFsID.setText(String.valueOf(sid));
        jTFsPassword = new JTextField(15);//密码
        jTFsPassword.setEditable(false);
        jTFsPassword.setText(String.valueOf(sid));
        jTFsName = new JTextField(15);//姓名
        jTFsSex = new JTextField(15);//性别
        jTFsBirthday = new JTextField(15);//生日
        jTFsSpecialty = new JTextField(15);//专业技能
        jTFsAddress = new JTextField(15);//住址
        jTFsTel = new JTextField(15);//电话
        jTFsEmail = new JTextField(15);//邮箱
        jTFsState = new JTextField(15);//状态
        jTFsState.setEditable(false);
        jTFsState.setText("T");
        jTFsRemark = new JTextField(15);//备注
 
        jBInsert = new JButton("录入新员工");
        jBInsert.setFont(new Font("微软雅黑",Font.BOLD,20));
        // 设置监听
        jBInsert.addActionListener(this);
 
        jCBAuthority = new JComboBox<String>();//权限
        //jCBAuthority.set
        jCBAuthority.addItem("普通员工");//添加选项
        jCBAuthority.addItem("管理员");
        jCBDepartment = new JComboBox<String>();//部门
        jCBDepartment.addItem("0-生产部");
        jCBDepartment.addItem("1-运营部");
        jCBDepartment.addItem("2-行政部");
        jCBDepartment.addItem("3-人事部");
        jCBJob = new JComboBox<String>();//职务
        jCBJob.addItem("0-员工");
        jCBJob.addItem("1-组长");
        jCBJob.addItem("2-主任");
        jCBJob.addItem("3-经理");
        jCBEdu_Level = new JComboBox<String>();//教育水平
        jCBEdu_Level.addItem("0-小学");
        jCBEdu_Level.addItem("1-初中");
        jCBEdu_Level.addItem("2-高中");
        jCBEdu_Level.addItem("3-职高");
        jCBEdu_Level.addItem("4-大本");
        jCBEdu_Level.addItem("5-大专");
        jCBEdu_Level.addItem("6-硕士");
        jCBEdu_Level.addItem("7-博士");
        jCBEdu_Level.addItem("8-博士后");
 
        jCBAuthority.addItemListener(new ItemListener() {//查询下拉框事件监听
            public void itemStateChanged(ItemEvent event) {
                switch (event.getStateChange()) {
                    case ItemEvent.SELECTED:
                        jCBAuthorityStr = (String) event.getItem();
                        System.out.println("选中：" + jCBAuthorityStr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中：" + event.getItem());
                        break;
                }
            }
        });
        jCBDepartment.addItemListener(new ItemListener() {//查询下拉框事件监听
            public void itemStateChanged(ItemEvent event) {
                switch (event.getStateChange()) {
                    case ItemEvent.SELECTED:
                        jCBDepartmentStr = (String) event.getItem();
                        System.out.println("选中：" + jCBDepartmentStr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中：" + event.getItem());
                        break;
                }
            }
        });
        jCBJob.addItemListener(new ItemListener() {//查询下拉框事件监听
            public void itemStateChanged(ItemEvent event) {
                switch (event.getStateChange()) {
                    case ItemEvent.SELECTED:
                        jCBJobStr = (String) event.getItem();
                        System.out.println("选中：" + jCBJobStr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中：" + event.getItem());
                        break;
                }
            }
        });
        jCBEdu_Level.addItemListener(new ItemListener() {//查询下拉框事件监听
            public void itemStateChanged(ItemEvent event) {
                switch (event.getStateChange()) {
                    case ItemEvent.SELECTED:
                        jCBEdu_LevelStr = (String) event.getItem();
                        System.out.println("选中：" + jCBEdu_LevelStr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中：" + event.getItem());
                        break;
                }
            }
        });
 
        //studentTableModel = new DefaultTableModel(tableTitle, 15);
        NewEmployeeJScrollPane = new JScrollPane();
 
        jP1 = new JPanel();
        jP2 = new JPanel();
        jP3 = new JPanel();
        jP4 = new JPanel();
        jP5 = new JPanel();
        jP = new JPanel();
 
        //jP1.add(NewEmployeeJScrollPane);
 
        jP1.add(jLNewEmployee);
        jP1.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP1.setPreferredSize(new Dimension(250,80));
 
        jP2.add(jLsID);
        jP2.add(jTFsID);
        jP2.add(jLsPassword);
        jP2.add(jTFsPassword);
        jP2.add(jLsAuthority);
        jP2.add(jCBAuthority);
        jP2.add(jLsDepartment);
        jP2.add(jCBDepartment);
        jP2.add(jLsJob);
        jP2.add(jCBJob);
        jP2.add(jLsEdu_Level);
        jP2.add(jCBEdu_Level);
        jP2.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP2.setPreferredSize(new Dimension(250,80));
 
        jP3.add(jLsName);
        jP3.add(jTFsName);
        jP3.add(jLsSex);
        jP3.add(jTFsSex);
        jP3.add(jLsBirthday);
        jP3.add(jTFsBirthday);
        jP3.add(jLsSpecialty);
        jP3.add(jTFsSpecialty);
        jP3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP3.setPreferredSize(new Dimension(250,80));
 
        jP4.add(jLsAddress);
        jP4.add(jTFsAddress);
        jP4.add(jLsTel);
        jP4.add(jTFsTel);
        jP4.add(jLsEmail);
        jP4.add(jTFsEmail);
        jP4.add(jLsState);
        jP4.add(jTFsState);
        jP4.add(jLsRemark);
        jP4.add(jTFsRemark);
        jP4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP4.setPreferredSize(new Dimension(250,80));
 
 
        //jP5.setLayout(new BorderLayout());
        jP5.add(jLText);
        jP5.add(jBInsert);
        jP5.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP5.setPreferredSize(new Dimension(250,80));
 
        jP.setLayout(new GridLayout(5, 1));
        jP.add(jP1);
        jP.add(jP2);
        jP.add(jP3);
        jP.add(jP4);
        jP.add(jP5);
 
        this.add("North", NewEmployeeJScrollPane);
        this.add("South", jP);
 
        this.setLayout(new BorderLayout());
        this.add(jP,BorderLayout.SOUTH);
 
        this.setTitle("新员工档案的录入");
        this.setSize(970, 450);
        this.setLocation(200, 150);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("录入新员工")
            && !jTFsPassword.getText().isEmpty()
            && !jTFsName.getText().isEmpty()
            && !jTFsSex.getText().isEmpty()
            && !jTFsBirthday.getText().isEmpty()
            && !jTFsSpecialty.getText().isEmpty()
            && !jTFsAddress.getText().isEmpty()
            && !jTFsTel.getText().isEmpty()
            && !jTFsEmail.getText().isEmpty()
            && !jTFsState.getText().isEmpty()){
            System.out.println("actionPerformed(). 录入新员工");
            insertProcess();
            sid++;
            jTFsID.setText(String.valueOf(sid));
            jTFsPassword.setText(String.valueOf(sid));
            jTFsName.setText("");
            jTFsSex.setText("");
            jTFsBirthday.setText("");
            jTFsSpecialty.setText("");
            jTFsAddress.setText("");
            jTFsTel.setText("");
            jTFsEmail.setText("");
            jTFsRemark.setText("");
        }
    }
    public void insertProcess(){
        String sID = jTFsID.getText().trim();
        String sPassword = jTFsPassword.getText().trim();
        String sAuthority = jCBAuthorityStr;
        String sName = jTFsName.getText().trim();
        String sSex = jTFsSex.getText().trim();
        String sBirthday = jTFsBirthday.getText().trim();
        String sDepartment = "";
        String sJob = "";
        String sEdu_Level = "";
        String sSpecialty = jTFsSpecialty.getText().trim();
        String sAddress = jTFsAddress.getText().trim();
        String sTel = jTFsTel.getText().trim();
        String sEmail = jTFsEmail.getText().trim();
        String sState = jTFsState.getText().trim();
        String sRemark = jTFsRemark.getText().trim();
        if(jCBDepartmentStr.equals("0-生产部")){
            sDepartment="生产部";
        }else if(jCBDepartmentStr.equals("1-运营部")){
            sDepartment="运营部";
        }
        else if(jCBDepartmentStr.equals("2-行政部")){
            sDepartment="行政部";
        }
        else if(jCBDepartmentStr.equals("3-人事部")){
            sDepartment="人事部";
        }
 
        if(jCBJobStr.equals("0-员工")){
            sJob="员工";
        }else if(jCBJobStr.equals("1-组长")){
            sJob="组长";
        }else if(jCBJobStr.equals("2-主任")){
            sJob="主任";
        }else if(jCBJobStr.equals("3-经理")){
            sJob="经理";
        }
 
        if(jCBEdu_LevelStr.equals("0-小学")){
            sEdu_Level="小学";
        }else if(jCBEdu_LevelStr.equals("1-初中")){
            sEdu_Level="初中";
        }else if(jCBEdu_LevelStr.equals("2-高中")){
            sEdu_Level="高中";
        }else if(jCBEdu_LevelStr.equals("3-职高")){
            sEdu_Level="职高";
        }else if(jCBEdu_LevelStr.equals("4-大本")){
            sEdu_Level="大本";
        }else if(jCBEdu_LevelStr.equals("5-大专")){
            sEdu_Level="大专";
        }else if(jCBEdu_LevelStr.equals("6-硕士")){
            sEdu_Level="硕士";
        }else if(jCBEdu_LevelStr.equals("7-博士")){
            sEdu_Level="博士";
        }else if(jCBEdu_LevelStr.equals("8-博士后")){
            sEdu_Level="博士后";
        }
        // 建立更新条件
        String sql = "insert into staff values('";
        sql = sql + sID + "','";
        sql = sql + sPassword + "','";
        sql = sql + sAuthority + "','";
        sql = sql + sName + "','";
        sql = sql + sSex + "','";
        sql = sql + sBirthday + "','";
        sql = sql + sDepartment + "','";
        sql = sql + sJob + "','";
        sql = sql + sEdu_Level + "','";
        sql = sql + sSpecialty + "','";
        sql = sql + sAddress + "','";
        sql = sql + sTel + "','";
        sql = sql + sEmail + "','";
        sql = sql + sState + "','";
        sql = sql + sRemark + "');";
        System.out.println("updateProcess(). sql = " + sql);
        try{
            if (NdbProcess.executeUpdate(sql) < 1) {
                System.out.println("updateProcess(). update database failed.");
            }else{
                //String sql = "insert into staff values('";
                JOptionPane.showMessageDialog(null,
                        "录入新员工成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                int pid=100000;
                try{
                    sql ="select max(pID) from personnel;";
                    System.out.println("queryProcess(). sql = " + sql);
                    NdbProcess.connect();
                    ResultSet rs = NdbProcess.executeQuery(sql);
                    if(rs.next()){
                        if(pid<=rs.getInt(1)){
                            pid=rs.getInt(1);
                            System.out.println("pid"+pid);
                        }
                    }
                    NdbProcess.disconnect();
                }catch(SQLException sqle){
                    System.out.println("sqle = " + sqle);
                    JOptionPane.showMessageDialog(null,
                            "空集合","错误",JOptionPane.ERROR_MESSAGE);
                }finally {
                    System.out.println(pid);
                    sql = "insert into personnel values('";
                    sql = sql + (pid+1) + "','";
                    sql = sql + sID + "','";
                    sql = sql + "0" + "','";
                    sql = sql + "新员工录入" + "');";
                    if (NdbProcess.executeUpdate(sql) < 1) {
                        System.out.println("updateProcess(). update database failed.");
                    }else{
                        P.jTFChangeID.setText(String.valueOf(pid+2));
                        //this.P.setVisible(true);
                        JOptionPane.showMessageDialog(null,
                                "人事变动成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
 
            }
        }catch(Exception e){
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }
}