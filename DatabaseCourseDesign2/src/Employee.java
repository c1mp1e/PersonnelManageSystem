 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
 

public class Employee extends JFrame implements ActionListener {
    // 定义组件
    JLabel jLEmployee = null;//员工信息
    JLabel jLText = null;//注释
    JLabel jLsID = null;//工号
    JLabel jLsPassword1 = null;//密码1
    JLabel jLsPassword2 = null;//密码2
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
    JPasswordField jPFsPassword1 = null;//密码1
    JPasswordField jPFsPassword2 = null;//密码2
    JTextField jTFsAuthority = null;//权限
    JTextField jTFsName = null;//姓名
    JTextField jTFsSex = null;//性别
    JTextField jTFsBirthday = null;//生日
    JTextField jTFsDepartment = null;//部门
    JTextField jTFsJob = null;//职务
    JTextField jTFsEdu_Level = null;//教育水平
    JTextField jTFsSpecialty = null;//专业技能
    JTextField jTFsAddress = null;//住址
    JTextField jTFsTel = null;//电话
    JTextField jTFsEmail = null;//邮箱
    JTextField jTFsState = null;//状态
    JTextField jTFsRemark = null;//备注
 
    JButton jBUpdateInfo = null;//更新信息
    JButton jBUpdatePwd = null;//修改密码
 
    //JComboBox jCBSelectQueryField = null;
 
    JPanel jP1, jP2,jP3, jP4, jP5,jP6 = null;
    JPanel jP = null;
 
    JScrollPane EmployeeJScrollPane = null;
 
    private static DbProcess EdbProcess;
    private static Encryption encryption;
    // 构造函数
    
    public Employee(ResultSet rs) {
        Employee.encryption=new Encryption();
        // 创建标签组件
        try {
            jLEmployee = new JLabel("员工信息");
            jLEmployee.setFont(new Font("微软雅黑",Font.BOLD,25));
            jLText = new JLabel("注意：更新信息需要填写上方所有带*的内容，修改密码需要输入两次新密码！");
            jLText.setFont(new Font("微软雅黑",Font.BOLD,15));
            jLText.setForeground(Color.red);
            jLsID = new JLabel("工号*");
            jLsPassword1 = new JLabel("新密码*");
            jLsPassword2 = new JLabel("确认新密码*");
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
            EdbProcess = new DbProcess();
            jTFsID = new JTextField(15);//工号
            jTFsID.setEditable(false);
            jTFsID.setText(rs.getString(1));
 
            jPFsPassword1 = new JPasswordField(15);//密码
            //jTFsPassword1.setText(rs.getString(2));
            jPFsPassword2 = new JPasswordField(15);//密码
            //jTFsPassword2.setText(rs.getString(2));
 
            jTFsAuthority = new JTextField(15);//权限
            jTFsAuthority.setEditable(false);
            jTFsAuthority.setText(rs.getString(3));
 
            jTFsName = new JTextField(15);//姓名
            jTFsName.setEditable(false);
            jTFsName.setText(rs.getString(4));
 
            jTFsSex = new JTextField(15);//性别
            jTFsSex.setEditable(false);
            jTFsSex.setText(rs.getString(5));
 
            jTFsBirthday = new JTextField(15);//生日
            jTFsBirthday.setEditable(false);
            jTFsBirthday.setText(rs.getString(6));
 
            jTFsDepartment = new JTextField(15);//部门
            jTFsDepartment.setEditable(false);
            jTFsDepartment.setText(rs.getString(7));
 
            jTFsJob = new JTextField(15);//职务
            jTFsJob.setEditable(false);
            jTFsJob.setText(rs.getString(8));
 
            jTFsEdu_Level = new JTextField(15);//教育水平
            jTFsEdu_Level.setEditable(false);
            jTFsEdu_Level.setText(rs.getString(9));
 
            jTFsSpecialty = new JTextField(15);//专业技能
            jTFsSpecialty.setText(rs.getString(10));
 
            jTFsAddress = new JTextField(15);//住址
            jTFsAddress.setText(rs.getString(11));
 
            jTFsTel = new JTextField(15);//电话
            jTFsTel.setText(rs.getString(12));
 
            jTFsEmail = new JTextField(15);//邮箱
            jTFsEmail.setText(rs.getString(13));
 
            jTFsState = new JTextField(15);//状态
            jTFsState.setEditable(false);
            jTFsState.setText(rs.getString(14));
 
            jTFsRemark = new JTextField(15);//备注
            jTFsRemark.setEditable(false);
            jTFsRemark.setText(rs.getString(15));
 
            jBUpdateInfo = new JButton("更新信息");
            jBUpdateInfo.setFont(new Font("微软雅黑",Font.BOLD,20));
            jBUpdatePwd = new JButton("修改密码");
            jBUpdatePwd.setFont(new Font("微软雅黑",Font.BOLD,20));
            // 设置监听
            jBUpdateInfo.addActionListener(this);
            jBUpdatePwd.addActionListener(this);
            //studentTableModel = new DefaultTableModel(tableTitle, 15);
            EmployeeJScrollPane = new JScrollPane();
 
            jP1 = new JPanel();
            jP2 = new JPanel();
            jP3 = new JPanel();
            jP4 = new JPanel();
            jP5 = new JPanel();
            jP6 = new JPanel();
            jP = new JPanel();
 
            //jP1.add(NewEmployeeJScrollPane);
 
            jP1.add(jLEmployee);
            jP1.setLayout(new FlowLayout(FlowLayout.CENTER));
            jP1.setPreferredSize(new Dimension(250,80));
 
            jP2.add(jLsID);
            jP2.add(jTFsID);
 
            jP2.add(jLsAuthority);
            jP2.add(jTFsAuthority);
            jP2.add(jLsName);
            jP2.add(jTFsName);
            jP2.add(jLsSex);
            jP2.add(jTFsSex);
            jP2.setLayout(new FlowLayout(FlowLayout.CENTER));
            jP2.setPreferredSize(new Dimension(250,80));
 
            jP3.add(jLsBirthday);
            jP3.add(jTFsBirthday);
            jP3.add(jLsDepartment);
            jP3.add(jTFsDepartment);
            jP3.add(jLsJob);
            jP3.add(jTFsJob);
            jP3.add(jLsEdu_Level);
            jP3.add(jTFsEdu_Level);
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
 
            jP5.add(jLText);
            jP5.add(jBUpdateInfo);
            jP5.setLayout(new FlowLayout(FlowLayout.CENTER));
            jP5.setPreferredSize(new Dimension(250,80));
 
            jP6.add(jLsPassword1);
            jP6.add(jPFsPassword1);
            jP6.add(jLsPassword2);
            jP6.add(jPFsPassword2);
            jP6.add(jBUpdatePwd);
            jP6.setLayout(new FlowLayout(FlowLayout.CENTER));
            jP6.setPreferredSize(new Dimension(250,80));
 
            jP.setLayout(new GridLayout(6, 1));
            jP.add(jP1);
            jP.add(jP2);
            jP.add(jP3);
            jP.add(jP4);
            jP.add(jP5);
            jP.add(jP6);
 
            this.add("North", EmployeeJScrollPane);
            this.add("South", jP);
 
            this.setLayout(new BorderLayout());
            this.add(jP,BorderLayout.SOUTH);
 
            this.setTitle("个人信息更改");
            this.setSize(970, 450);
            this.setLocation(200, 150);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setVisible(true);
            this.setResizable(false);
            
            EdbProcess.disconnect();
        }catch (SQLException sqle){
            System.out.println("sqle = " + sqle);
            JOptionPane.showMessageDialog(null,
                    "你输入的工号不存在，请重新输入！","错误",JOptionPane.ERROR_MESSAGE);
        }
 
    }
  
	public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("更新信息")
                && !jTFsSpecialty.getText().isEmpty()
                && !jTFsAddress.getText().isEmpty()
                && !jTFsTel.getText().isEmpty()
                && !jTFsEmail.getText().isEmpty()){
            System.out.println("actionPerformed(). 更新");
            updateProcess();
             
        }else if(e.getActionCommand().equals("修改密码")
                &&!String.valueOf(jPFsPassword1.getPassword()).equals("")
                &&!String.valueOf(jPFsPassword2.getPassword()).equals("")){
            String sPassword1 = new String(jPFsPassword1.getPassword());
            String sPassword2 = new String(jPFsPassword2.getPassword());
            if(sPassword1.equals(sPassword2)){
                changePwdProcess(sPassword1);
            }else{
                System.out.println("两次密码不一致");
                JOptionPane.showMessageDialog(null,
                        "两次密码不一致，请确认后再输入！","提示",JOptionPane.INFORMATION_MESSAGE);
            }
            jPFsPassword1.setText("");
            jPFsPassword2.setText("");
        }
    }
    
    public void queryAllProcess()
    {
        try{
            // 建立查询条件
            String sql = "select pID,pChange,personnel.sID,sName,sJob,sState,pDescription from personnel,staff where ";
            //String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);
            sql = sql + "personnel.sID = staff.sID order by pID asc;";
            System.out.println("queryAllProcess(). sql = " + sql);
 
            EdbProcess.connect();
            ResultSet rs = EdbProcess.executeQuery(sql);
 
            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            
 
            EdbProcess.disconnect();
 
        }catch(Exception sqle){
            System.out.println("sqle = " + sqle);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }
 
    
    public void updateProcess(){
        String sID = jTFsID.getText().trim();
        //String sPassword = jPFsPassword1.getText().trim();
        String sSpecialty = jTFsSpecialty.getText().trim();
        String sAddress = jTFsAddress.getText().trim();
        String sTel = jTFsTel.getText().trim();
        String sEmail = jTFsEmail.getText().trim();
 
        // 建立更新条件
        String sql = "update staff set sSpecialty = '";
        //sql = sql + sPassword + "', sSpecialty = '";
        sql = sql + sSpecialty + "', sAddress = '";
        sql = sql + sAddress + "', sTel = '";
        sql = sql + sTel + "', sEmail = '";
        sql = sql + sEmail + "'";
        sql = sql + " WHERE sID = '" + sID + "';";
        System.out.println("updateProcess(). sql = " + sql);
        try{
            if (EdbProcess.executeUpdate(sql) < 1) {
                System.out.println("updateProcess(). update database failed.");
            }else{
                JOptionPane.showMessageDialog(null,
                        "更新成功！","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
        queryAllProcess();
        
    }
    public void changePwdProcess(String sPassword){
    	String sID = jTFsID.getText().trim();
        String sql = "update staff set sPassword = '";
        sql = sql + this.encryption.encryption(sPassword)  + "'";
        sql = sql + " where sID = '" + sID+ "';";
        System.out.println("changePwdProcess(). sql = " + sql);
        try{
            if(EdbProcess.executeUpdate(sql) < 1) {
                System.out.println("changePwdProcess(). update database failed.");
            }else{
                JOptionPane.showMessageDialog(null,
                        "修改密码成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                
            }
        }catch(Exception e){
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
        queryAllProcess();
    }
}
