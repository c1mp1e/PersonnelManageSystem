 
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
 
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
 
class DatabaseCourseDesign2 extends JFrame implements ActionListener {
    // 定义组件
    JLabel jLStaff = null;//员工信息表
    JLabel jLText = null;//注释
    JLabel jLSelectQueryField = null;//选择查询字段
    JLabel jLEqual = null;//=
    JLabel jLsID = null;//工号
    //JLabel jLsPassword = null;//密码
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
 
    JTextField jTFQueryField = null;//查询字段
    JTextField jTFsID = null;//工号
    //JTextField jTFsPassword = null;//密码
    JTextField jTFsJob =null;//职务
    JTextField jTFsName = null;//姓名
    JTextField jTFsSex = null;//性别
    JTextField jTFsBirthday = null;//生日
    JTextField jTFsSpecialty = null;//专业技能
    JTextField jTFsAddress = null;//住址
    JTextField jTFsTel = null;//电话
    JTextField jTFsEmail = null;//邮箱
    JTextField jTFsState = null;//状态
    JTextField jTFsRemark = null;//备注
    
    JButton jBQuery = null;//查询
    JButton jBQueryAll = null;//查询所有记录
    JButton jBUpdate = null;//更新
    JButton jBReset = null;//重置密码
 
    JComboBox<String> jCBSelectQueryField = null;//查询字段
    JComboBox<String> jCBAuthority = null;//权限
    JComboBox<String> jCBDepartment = null;//部门
    //JComboBox<String> jCBJob = null;//职务
    JComboBox<String> jCBEdu_Level = null;//教育水平
    JPanel jP1, jP2,jP3,jP4,jP5,jP6,jP7 = null;
    JPanel jPTop, jPBottom = null;
    DefaultTableModel studentTableModel = null;
    JTable staffJTable = null;
    JScrollPane staffJScrollPane = null;
    Vector staffVector = null;
    Vector titleVector = null;
    
    private static DbProcess dbProcess;
    private Encryption encryption;
    String SelectQueryFieldStr = "工号";
    String jCBAuthorityStr="普通员工";
    String jCBDepartmentStr="0-生产部";
    String jCBJobStr="0-员工";
    String jCBEdu_LevelStr="0-小学";
    
    // 构造函数
    public DatabaseCourseDesign2() {
        this.encryption = new Encryption();
        // 创建标签组件
        jLStaff = new JLabel("员工信息表");
        jLText = new JLabel("注意：带*的为必填项          ");
        jLText.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLText.setForeground(Color.red);
        jLSelectQueryField = new JLabel("选择查询字段");
        jLEqual = new JLabel(" = ");
        jLsID = new JLabel("工号*");
        //jLsPassword = new JLabel("密码*");
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
 
        jTFQueryField = new JTextField(15);//查询字段
        jTFsID = new JTextField(15);//工号
        jTFsID.setEditable(false);
        //jTFsPassword = new JTextField(15);//密码
        jTFsJob = new JTextField(15);//职务
        jTFsJob.setEditable(false);
        jTFsName = new JTextField(15);//姓名
        jTFsSex = new JTextField(15);//性别
        jTFsBirthday = new JTextField(15);//生日
        jTFsSpecialty = new JTextField(15);//专业技能
        jTFsAddress = new JTextField(15);//住址
        jTFsTel = new JTextField(15);//电话
        jTFsEmail = new JTextField(15);//邮箱
        jTFsState = new JTextField(15);//状态
        jTFsState.setEditable(false);
        jTFsRemark = new JTextField(15);//备注
        
        jBQuery = new JButton("查询");
        jBQueryAll = new JButton("查询所有记录");
        jBUpdate = new JButton("更新员工信息");
        jBUpdate.setFont(new Font("微软雅黑",Font.BOLD,20));
        jBReset = new JButton("重置用户密码");
        jBReset.setFont(new Font("微软雅黑",Font.BOLD,20));
        //jBBack = new JButton("返回");
        //jBDeleteCurrentRecord = new JButton("删除当前记录");
        //jBDeleteAllRecords = new JButton("删除所有记录");
        // 设置监听
        jBQuery.addActionListener(this);
        jBQueryAll.addActionListener(this);
        jBUpdate.addActionListener(this);
        jBReset.addActionListener(this);
        //jBDeleteCurrentRecord.addActionListener(this);
        //jBDeleteAllRecords.addActionListener(this);
        
        jCBSelectQueryField = new JComboBox<String>();//查询字段
        jCBSelectQueryField.addItem("工号");//添加选项
        jCBSelectQueryField.addItem("权限");
        jCBSelectQueryField.addItem("姓名");  
        jCBSelectQueryField.addItem("性别");
        jCBSelectQueryField.addItem("生日");
        jCBSelectQueryField.addItem("部门");
        jCBSelectQueryField.addItem("职务");
        jCBSelectQueryField.addItem("教育水平");
        jCBSelectQueryField.addItem("专业技能");
        jCBSelectQueryField.addItem("状态");
 
        jCBAuthority = new JComboBox<String>();//权限
        //jCBAuthority.set
        jCBAuthority.addItem("普通员工");//添加选项
        jCBAuthority.addItem("管理员");
        jCBDepartment = new JComboBox<String>();//部门
        jCBDepartment.addItem("0-生产部");
        jCBDepartment.addItem("1-运营部");
        jCBDepartment.addItem("2-行政部");
        jCBDepartment.addItem("3-人事部");
        /*jCBJob = new JComboBox<String>();//职务
        jCBJob.addItem("员工");
        jCBJob.addItem("组长");
        jCBJob.addItem("主任");
        jCBJob.addItem("经理");*/
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
 
        jCBSelectQueryField.addItemListener(new ItemListener() {//下拉框事件监听  
            public void itemStateChanged(ItemEvent event) {  
                switch (event.getStateChange()) {  
                case ItemEvent.SELECTED:  
                    SelectQueryFieldStr = (String) event.getItem();  
                    System.out.println("选中：" + SelectQueryFieldStr);  
                    break;  
                case ItemEvent.DESELECTED:  
                    System.out.println("取消选中：" + event.getItem());  
                    break;  
                }
            }  
        });
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
        /*jCBJob.addItemListener(new ItemListener() {//查询下拉框事件监听
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
        });*/
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
    
        staffVector = new Vector();
        titleVector = new Vector();
 
        // 定义表头
        titleVector.add("工号");
        //titleVector.add("密码");
        titleVector.add("权限");
        titleVector.add("姓名");
        titleVector.add("性别");
        titleVector.add("生日");
        titleVector.add("部门");
        titleVector.add("职务");
        titleVector.add("教育水平");
        titleVector.add("专业技能");
        titleVector.add("住址");
        titleVector.add("电话");
        titleVector.add("邮箱");
        titleVector.add("状态");
        titleVector.add("备注");
 
        //studentTableModel = new DefaultTableModel(tableTitle, 15);
        staffJTable = new JTable(staffVector, titleVector);
        staffJTable.setPreferredScrollableViewportSize(new Dimension(910,220));
        staffJScrollPane = new JScrollPane(staffJTable);
        //分别设置水平和垂直滚动条自动出现
        staffJScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        staffJScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        //为表格添加监听器 
        staffJTable.addMouseListener(new MouseAdapter()
        { 
            public void mouseClicked(MouseEvent e) 
            { 
                int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // 获得行位置
                System.out.println("mouseClicked(). row = " + row);
                Vector v = new Vector();
                v = (Vector) staffVector.get(row);
 
                jTFsID.setText((String) v.get(0));// 工号
                //jTFsPassword.setText((String) v.get(1));// 密码
                if(String.valueOf(v.get(1)).equals("普通员工")){
                    jCBAuthority.setSelectedIndex(0);
                }else if (String.valueOf(v.get(1)).equals("管理员")){
                    jCBAuthority.setSelectedIndex(1);
                }
                jTFsName.setText((String) v.get(2));// 姓名
                jTFsSex.setText((String) v.get(3));// 性别
                jTFsBirthday.setText((String) v.get(4));// 生日
                //jCBDepartmentStr=((String) v.get(6));// 部门
                if(String.valueOf(v.get(5)).equals("生产部")){
                    jCBDepartment.setSelectedIndex(0);
                }else if (String.valueOf(v.get(5)).equals("运营部")){
                    jCBDepartment.setSelectedIndex(1);
                }else if (String.valueOf(v.get(5)).equals("行政部")){
                    jCBDepartment.setSelectedIndex(2);
                }else if (String.valueOf(v.get(5)).equals("人事部")){
                    jCBDepartment.setSelectedIndex(3);
                }
                jTFsJob.setText((String) v.get(6));// 职务
                /*if(String.valueOf(v.get(7)).equals("员工")){
                    jCBJob.setSelectedIndex(0);
                }else if (String.valueOf(v.get(7)).equals("组长")){
                    jCBJob.setSelectedIndex(1);
                }else if (String.valueOf(v.get(7)).equals("主任")){
                    jCBJob.setSelectedIndex(2);
                }else if (String.valueOf(v.get(7)).equals("经理")){
                    jCBJob.setSelectedIndex(3);
                }*/
                //jTFsEdu_Level.setText((String) v.get(8));// 教育水平
                if(String.valueOf(v.get(7)).equals("小学")){
                    jCBEdu_Level.setSelectedIndex(0);
                }else if (String.valueOf(v.get(7)).equals("初中")){
                    jCBEdu_Level.setSelectedIndex(1);
                }else if (String.valueOf(v.get(7)).equals("高中")){
                    jCBEdu_Level.setSelectedIndex(2);
                }else if (String.valueOf(v.get(7)).equals("职高")){
                    jCBEdu_Level.setSelectedIndex(3);
                }else if (String.valueOf(v.get(7)).equals("大本")){
                    jCBEdu_Level.setSelectedIndex(4);
                }else if (String.valueOf(v.get(7)).equals("大专")){
                    jCBEdu_Level.setSelectedIndex(5);
                }else if (String.valueOf(v.get(7)).equals("硕士")){
                    jCBEdu_Level.setSelectedIndex(6);
                }else if (String.valueOf(v.get(7)).equals("博士")){
                    jCBEdu_Level.setSelectedIndex(7);
                }else if (String.valueOf(v.get(7)).equals("博士后")){
                    jCBEdu_Level.setSelectedIndex(7);
                }
                jTFsSpecialty.setText((String) v.get(8));// 专业技能
                jTFsAddress.setText((String) v.get(9));// 住址
                jTFsTel.setText((String) v.get(10));// 电话
                jTFsEmail.setText((String) v.get(11));// 邮箱
                jTFsState.setText((String) v.get(12));//状态
                jTFsRemark.setText((String) v.get(13));// 备注
            }
        });
 
        jP1 = new JPanel();
        jP2 = new JPanel();
        jP3 = new JPanel();
        jP4 = new JPanel();
        jP5 = new JPanel();
        jP6 = new JPanel();
        jP7 = new JPanel();
        jPTop = new JPanel();
        jPBottom = new JPanel();
        
        jP1.add(jLStaff,BorderLayout.NORTH);
        jP2.add(staffJScrollPane);
        
        jP3.add(jLSelectQueryField);
        jP3.add(jCBSelectQueryField);
        jP3.add(jLEqual);
        jP3.add(jTFQueryField);
        jP3.add(jBQuery);
        jP3.add(jBQueryAll);
        jP3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP3.setPreferredSize(new Dimension(20,20));
        
        jP4.add(jLsID);
        jP4.add(jTFsID);
        //jP4.add(jLsPassword);
        //jP4.add(jTFsPassword);
        jP4.add(jLsAuthority);
        jP4.add(jCBAuthority);
        jP4.add(jLsDepartment);
        jP4.add(jCBDepartment);
        //jP4.add(jLsJob);
        //jP4.add(jCBJob);
        jP4.add(jLsEdu_Level);
        jP4.add(jCBEdu_Level);
        jP4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP4.setPreferredSize(new Dimension(20,20));
 
        jP5.add(jLsName);
        jP5.add(jTFsName);
        jP5.add(jLsSex);
        jP5.add(jTFsSex);
        jP5.add(jLsJob);
        jP5.add(jTFsJob);
        jP5.add(jLsBirthday);
        jP5.add(jTFsBirthday);
        jP5.add(jLsSpecialty);
        jP5.add(jTFsSpecialty);
        jP5.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP5.setPreferredSize(new Dimension(20,20));
 
        jP6.add(jLsAddress);
        jP6.add(jTFsAddress);
        jP6.add(jLsTel);
        jP6.add(jTFsTel);
        jP6.add(jLsEmail);
        jP6.add(jTFsEmail);
        jP6.add(jLsState);
        jP6.add(jTFsState);
        jP6.add(jLsRemark);
        jP6.add(jTFsRemark);
        jP6.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP6.setPreferredSize(new Dimension(20,20));
 
        jP7.add(jLText);
        jP7.add(jBUpdate);
        jP7.add(jBReset);
        jP7.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP7.setPreferredSize(new Dimension(20,20));
        
        jPTop.add(jP1);
        jPTop.add(jP2);
        
        jPBottom.setLayout(new GridLayout(5, 1));
        jPBottom.add(jP3);
        jPBottom.add(jP4);
        jPBottom.add(jP5);
        jPBottom.add(jP6);
        jPBottom.add(jP7);
 
        this.add("North", jPTop);
        this.add("South", jPBottom);
 
        this.setLayout(new GridLayout(2, 1));
        this.setTitle("员工档案查询修改");
        this.setSize(970, 650);
        this.setLocation(200, 10);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        dbProcess = new DbProcess();
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("查询")
            && !jTFQueryField.getText().isEmpty()){
            System.out.println("actionPerformed(). 查询");
            String sQueryField = jTFQueryField.getText().trim();
            jTFsID.setText("");
            //jTFsPassword.setText("");
            //jTFsAuthority.setText("");
            jTFsName.setText("");
            jTFsSex.setText("");
            jTFsBirthday.setText("");
            //jTFsDepartment.setText("");
            //jTFsJob.setText("");
            //jTFsEdu_Level.setText("");
            jTFsSpecialty.setText("");
            jTFsAddress.setText("");
            jTFsTel.setText("");
            jTFsEmail.setText("");
            jTFsState.setText("");
            jTFsRemark.setText("");
            queryProcess(sQueryField);
            jTFQueryField.setText("");
        }else if(e.getActionCommand().equals("查询所有记录")) {
            System.out.println("actionPerformed(). 查询所有记录");
            queryAllProcess();
        } else if(e.getActionCommand().equals("重置用户密码")
                && !jTFsID.getText().isEmpty()
                && !jTFsName.getText().isEmpty()
                && !jTFsSex.getText().isEmpty()
                && !jTFsJob.getText().isEmpty()
                && !jTFsBirthday.getText().isEmpty()
                && !jTFsSpecialty.getText().isEmpty()
                && !jTFsAddress.getText().isEmpty()
                && !jTFsTel.getText().isEmpty()
                && !jTFsEmail.getText().isEmpty()
                && !jTFsState.getText().isEmpty()){
            System.out.println("actionPerformed(). 重置用户密码");
            ResetProcess();
        }else if(e.getActionCommand().equals("更新员工信息")
                && !jTFsID.getText().isEmpty()
                && !jTFsName.getText().isEmpty()
                && !jTFsSex.getText().isEmpty()
                && !jTFsJob.getText().isEmpty()
                && !jTFsBirthday.getText().isEmpty()
                && !jTFsSpecialty.getText().isEmpty()
                && !jTFsAddress.getText().isEmpty()
                && !jTFsTel.getText().isEmpty()
                && !jTFsEmail.getText().isEmpty()
                && !jTFsState.getText().isEmpty()){
            try{
                String sql ="select sState from staff where sID = '"+jTFsID.getText().trim()+"';";
                dbProcess.connect();
                ResultSet rs = dbProcess.executeQuery(sql);
                rs.next();
                if(rs.getString("sState").equals("T")){
                    System.out.println("actionPerformed(). 更新");
                    updateProcess();
                }else {
                    JOptionPane.showMessageDialog(null,
                            "该员工已被辞退，无法更新！","错误",JOptionPane.ERROR_MESSAGE);
                }
                dbProcess.disconnect();
            }catch(SQLException sqle){
                System.out.println("sqle = " + sqle);
                JOptionPane.showMessageDialog(null,
                        "该员工已被辞退，无法更新！","错误",JOptionPane.ERROR_MESSAGE);
            }
        }/*else if(e.getActionCommand().equals("  返回  ")){
            System.out.println("actionPerformed(). 返回");
        }*//*else if(e.getActionCommand().equals("删除所有记录")){
            System.out.println("actionPerformed(). 删除所有记录");
            deleteAllRecordsProcess();
        }*/
    }
 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        try {
        	for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        		if("Nimbus".equals(info.getName())) {
        			javax.swing.UIManager.setLookAndFeel(info.getClassName());
        			break;
        		}
        	}
        }catch(Exception e){
        	e.printStackTrace();
        }
    	Login L = new Login();
    }
 
    public void queryProcess(String sQueryField)
    {
        try{
            // 建立查询条件
            String sql = "select * from staff where ";
            String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);
        
            /*if(queryFieldStr.equals("sAge")){//int sAge.
                sql = sql + queryFieldStr;
                sql = sql + " = " + sQueryField;
            }else{*/
                sql = sql + queryFieldStr;
                sql = sql + " = ";
                sql = sql + "'" + sQueryField + "';";
            //}
            
            System.out.println("queryProcess(). sql = " + sql);
    
            dbProcess.connect();
            ResultSet rs = dbProcess.executeQuery(sql);
    
            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            staffVector.clear();
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("sID"));
                //v.add(rs.getString("sPassword"));
                v.add(rs.getString("sAuthority"));
                v.add(rs.getString("sName"));
                v.add(rs.getString("sSex"));
                v.add(rs.getString("sBirthday"));
                v.add(rs.getString("sDepartment"));
                v.add(rs.getString("sJob"));
                v.add(rs.getString("sEdu_Level"));
                v.add(rs.getString("sSpecialty"));
                v.add(rs.getString("sAddress"));
                v.add(rs.getString("sTel"));
                v.add(rs.getString("sEmail"));
                v.add(rs.getString("sState"));
                v.add(rs.getString("sRemark"));
                staffVector.add(v);
            }
            
            staffJTable.updateUI();
 
            dbProcess.disconnect();
        }catch(SQLException sqle){
            System.out.println("sqle = " + sqle);
            JOptionPane.showMessageDialog(null,
                "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void queryAllProcess()
    {
        try{
            // 建立查询条件
            String sql = "select * from staff;";
            System.out.println("queryAllProcess(). sql = " + sql);
    
            dbProcess.connect();
            ResultSet rs = dbProcess.executeQuery(sql);
 
            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            staffVector.clear();
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("sID"));
                //v.add(rs.getString("sPassword"));
                v.add(rs.getString("sAuthority"));
                v.add(rs.getString("sName"));
                v.add(rs.getString("sSex"));
                v.add(rs.getString("sBirthday"));
                v.add(rs.getString("sDepartment"));
                v.add(rs.getString("sJob"));
                v.add(rs.getString("sEdu_Level"));
                v.add(rs.getString("sSpecialty"));
                v.add(rs.getString("sAddress"));
                v.add(rs.getString("sTel"));
                v.add(rs.getString("sEmail"));
                v.add(rs.getString("sState"));
                v.add(rs.getString("sRemark"));
                staffVector.add(v);
            }
            
            staffJTable.updateUI();
 
            dbProcess.disconnect();
        }catch(SQLException sqle){
            System.out.println("sqle = " + sqle);
            JOptionPane.showMessageDialog(null,
                "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }
 
    public void updateProcess()
    {
        String sID = jTFsID.getText().trim();
        //String sPassword = jTFsPassword.getText().trim();
        String sAuthority = jCBAuthorityStr;
        String sName = jTFsName.getText().trim();
        String sSex = jTFsSex.getText().trim();
        String sBirthday = jTFsBirthday.getText().trim();
        String sDepartment = "";
        String sJob = jTFsJob.getText().trim();
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
        /*
        if(jCBJobStr.equals("0-员工")){
            sJob="员工";
        }else if(jCBJobStr.equals("1-组长")){
            sJob="组长";
        }else if(jCBJobStr.equals("2-主任")){
            sJob="主任";
        }else if(jCBJobStr.equals("3-经理")){
            sJob="经理";
        }*/
 
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
        String sql = "update staff set sAuthority = '";
        //sql = sql + sPassword + "', sAuthority = '";
        sql = sql + sAuthority + "', sName = '";
        sql = sql + sName + "', sSex = '";
        sql = sql + sSex + "', sBirthday = '";
        sql = sql + sBirthday + "', sDepartment = '";
        sql = sql + sDepartment + "', sJob = '";
        sql = sql + sJob + "', sEdu_Level = '";
        sql = sql + sEdu_Level + "', sSpecialty = '";
        sql = sql + sSpecialty + "', sAddress = '";
        sql = sql + sAddress + "', sTel = '";
        sql = sql + sTel + "', sEmail = '";
        sql = sql + sEmail + "', sState = '";
        sql = sql + sState + "', sRemark = '";
        sql = sql + sRemark + "'";
        sql = sql + " WHERE sID = '" + sID + "';";
        System.out.println("updateProcess(). sql = " + sql);
        try{
            if (dbProcess.executeUpdate(sql) < 1) {
                System.out.println("updateProcess(). update database failed.");
            }else{
                JOptionPane.showMessageDialog(null,
                        "更新成功","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
        queryAllProcess();
    }
    public void ResetProcess(){
        String sPassword=this.encryption.encryption(jTFsID.getText().trim());
        String sql = "update staff set sPassword = '";
        sql = sql + sPassword + "'";
        sql = sql + " WHERE sID = '" + jTFsID.getText().trim() + "';";
        System.out.println("updateProcess(). sql = " + sql);
        try{
            if (dbProcess.executeUpdate(sql) < 1) {
                System.out.println("updateProcess(). Reset database failed.");
            }else{
                JOptionPane.showMessageDialog(null,
                        "重置密码成功","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }
    public String jCBSelectQueryFieldTransfer(String InputStr)
    {
        String outputStr = "";
        System.out.println("jCBSelectQueryFieldTransfer(). InputStr = " + InputStr);
        
        if(InputStr.equals("工号")){
            outputStr = "sID";
        }else if(InputStr.equals("权限")){
            outputStr = "sAuthority";
        }else if(InputStr.equals("姓名")){
            outputStr = "sName";
        }else if(InputStr.equals("性别")){
            outputStr = "sSex";
        }else if(InputStr.equals("生日")){
            outputStr = "sBirthday";
        }else if(InputStr.equals("部门")){
            outputStr = "sDepartment";
        }else if(InputStr.equals("职务")){
            outputStr = "sJob";
        }else if(InputStr.equals("教育水平")){
            outputStr = "sEdu_Level";
        }else if(InputStr.equals("专业技能")){
            outputStr = "sSpecialty";
        }else if(InputStr.equals("状态")){
            outputStr = "sState";
        }
        System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);
        return outputStr;
    }
}