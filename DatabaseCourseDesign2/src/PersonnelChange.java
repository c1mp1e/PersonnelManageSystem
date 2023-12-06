 
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
 
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
 
class PersonnelChange extends JFrame implements ActionListener {
    // 定义组件
	
    JLabel jLPersonnelRecord = null;//人事变更记录表
    JLabel jLSelectQueryField = null;//选择查询字段
    JLabel jLText2 = null;//注释1
    JLabel jLText1 = null;//注释2
    JLabel jLEqual1 = null;//=
    JLabel jLEqual2 = null;//=
    JLabel jLChangeID = null;//变更编号
    JLabel jLsID = null;//需要进行变更的员工工号
    JLabel jLSelectChangeCode = null;//代码
    JLabel jLAfter;//变之后的内容
    JLabel jLChangeDescription = null;//详细描述
 
    JTextField jTFQueryField = null;//查询字段
    JTextField jTFChangeID = null;//变更编号
    JTextField jTFsID = null;//需要进行变更的员工工号
    JTextField jTFChangeDescription = null;//详细描述
 
    JButton jBQuery = null;//查询
    JButton jBQueryAll = null;//查询所有记录
    JButton jBChange = null;//变更
 
    //JButton jBDeleteCurrentRecord = null;//删除当前记录
    //JButton jBDeleteAllRecords = null;//删除所有记录
 
    //JComboBox jCBSelectQueryField = null;
    JComboBox<String> jCBSelectQueryField = null;//查询字段
    JComboBox<String> jCBChangeCode = null;//代码
    JComboBox<String> jCBJob = null;//职务
    JPanel jP1, jP2,jP3,jP4,jP5,jP6,jP7,jP8,jP9,jP10 = null;
    JPanel jPTop, jPBottom = null;
    DefaultTableModel studentTableModel = null;
    JTable PersonnelRecordJTable = null;
    JScrollPane PersonnelRecordJScrollPane = null;
    Vector PersonnelVector = null;
    Vector titleVector = null;
 
    private static DbProcess PdbProcess;
    private static int pid;
    String SelectQueryFieldStr = "记录编号";
    String SelectChangeCodeStr = "1-职务变动";
    String jCBJobStr="0-员工";
    // 构造函数
    public PersonnelChange(int pid) {
        this.pid=pid;
        // 创建标签组件
        jLPersonnelRecord = new JLabel("人事变更记录表");
        jLText1 = new JLabel("注意：带*的为必填项，进行职务变动需要选择变动后的职务，辞退不需要处理");
        jLText1.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLText1.setForeground(Color.red);
        jLText2 = new JLabel("变更代码：0(新员工加入)，1(职务变动)，2(辞退)");
        jLText2.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLText2.setForeground(Color.red);
        jLSelectQueryField = new JLabel("选择查询字段");
        jLSelectQueryField.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLEqual1 = new JLabel(" = ");
        jLEqual1.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLEqual2 = new JLabel(" = ");
        jLEqual2.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLChangeID = new JLabel("记录编号*：");
        jLChangeID.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLsID = new JLabel("需要进行变更的员工工号*：");
        jLsID.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLSelectChangeCode = new JLabel("需要变更的代码*");
        jLSelectChangeCode.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLAfter = new JLabel("改变后的职务：");
        jLAfter.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLChangeDescription = new JLabel("详细描述*：");
        jLChangeDescription.setFont(new Font("微软雅黑",Font.BOLD,15));
 
        jTFQueryField = new JTextField(15);//查询字段
        jTFChangeID = new JTextField(15);
        jTFChangeID.setEditable(false);
        jTFChangeID.setText(String.valueOf(pid));
        jTFsID = new JTextField(15);//需要进行变更的员工工号
 
        jTFChangeDescription = new JTextField(25);
 
        jBQuery = new JButton("查询");
        jBQueryAll = new JButton("查询所有记录");
        jBChange = new JButton("变更");
        jBChange.setFont(new Font("微软雅黑",Font.BOLD,20));
 
        // 设置监听
        jBQuery.addActionListener(this);
        jBQueryAll.addActionListener(this);
        jBChange.addActionListener(this);
 
        jCBSelectQueryField = new JComboBox<String>();//查询字段
        jCBSelectQueryField.addItem("记录编号");//添加选项
        jCBSelectQueryField.addItem("员工工号");
        jCBSelectQueryField.addItem("变更代码");
        jCBChangeCode = new JComboBox<String>();//变更代码
        jCBChangeCode.addItem("1-职务变动");
        jCBChangeCode.addItem("2-辞退");
        jCBJob = new JComboBox<String>();//职务
        jCBJob.addItem("0-员工");
        jCBJob.addItem("1-组长");
        jCBJob.addItem("2-主任");
        jCBJob.addItem("3-经理");
 
        jCBSelectQueryField.addItemListener(new ItemListener() {//查询下拉框事件监听
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
        jCBChangeCode.addItemListener(new ItemListener() {//变更代码下拉框事件监听
            public void itemStateChanged(ItemEvent event) {
                switch (event.getStateChange()) {
                    case ItemEvent.SELECTED:
                        SelectChangeCodeStr = (String) event.getItem();
                        System.out.println("选中：" + SelectChangeCodeStr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中：" + event.getItem());
                        break;
                }
            }
        });
        jCBJob.addItemListener(new ItemListener() {//职务下拉框事件监听
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
        PersonnelVector = new Vector();
        titleVector = new Vector();
 
        // 定义表头
        titleVector.add("记录编号");
        titleVector.add("变更代码");
        titleVector.add("员工工号");
        titleVector.add("员工姓名");
        titleVector.add("员工职务");
        titleVector.add("员工状态");
        titleVector.add("详细记录");
 
        //studentTableModel = new DefaultTableModel(tableTitle, 15);
        PersonnelRecordJTable = new JTable(PersonnelVector, titleVector);
        PersonnelRecordJTable.setPreferredScrollableViewportSize(new Dimension(660,240));
        PersonnelRecordJScrollPane = new JScrollPane(PersonnelRecordJTable);
        //分别设置水平和垂直滚动条自动出现
        PersonnelRecordJScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        PersonnelRecordJScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
 
        //为表格添加监听器
        PersonnelRecordJTable.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // 获得行位置
                System.out.println("mouseClicked(). row = " + row);
            }
        });
 
        jP1 = new JPanel();
        jP2 = new JPanel();
        jP3 = new JPanel();
        jP4 = new JPanel();
        jP5 = new JPanel();
        jP6 = new JPanel();
        jP7 = new JPanel();
        jP8 = new JPanel();
        jP9 = new JPanel();
        jP10 = new JPanel();
        jPTop = new JPanel();
        jPBottom = new JPanel();
 
        jP1.add(jLPersonnelRecord,BorderLayout.NORTH);
        jP2.add(PersonnelRecordJScrollPane);
 
        jP10.add(jLText2);
        jP10.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP10.setPreferredSize(new Dimension(20,20));
 
        jP3.add(jLSelectQueryField);
        jP3.add(jCBSelectQueryField);
        jP3.add(jLEqual1);
        jP3.add(jTFQueryField);
        jP3.add(jBQuery);
        jP3.add(jBQueryAll);
        jP3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP3.setPreferredSize(new Dimension(20,20));
 
        jP4.add(jLChangeID);
        jP4.add(jTFChangeID);
        jP4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP4.setPreferredSize(new Dimension(20,20));
 
        jP5.add(jLsID);
        jP5.add(jTFsID);
        jP5.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP5.setPreferredSize(new Dimension(20,20));
 
        jP6.add(jLSelectChangeCode);
        jP6.add(jCBChangeCode);
        jP6.add(jLAfter);
        jP6.add(jCBJob);
        jP6.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP6.setPreferredSize(new Dimension(20,20));
 
        jP7.add(jLChangeDescription);
        jP7.add(jTFChangeDescription);
        jP7.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP7.setPreferredSize(new Dimension(20,20));
 
 
        jP8.add(jBChange);
        jP8.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP8.setPreferredSize(new Dimension(20,20));
 
        jP9.add(jLText1);
        //jP9.add(jLText2);
        jP9.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP9.setPreferredSize(new Dimension(20,20));
 
        jPTop.add(jP1);
        jPTop.add(jP2);
 
        jPBottom.setLayout(new GridLayout(8, 1));
        jPBottom.add(jP10);
        jPBottom.add(jP3);
        jPBottom.add(jP4);
        jPBottom.add(jP5);
        jPBottom.add(jP6);
        jPBottom.add(jP7);
        jPBottom.add(jP8);
        jPBottom.add(jP9);
 
        this.add("North", jPTop);
        this.add("South", jPBottom);
 
        this.setLayout(new GridLayout(2, 1));
        this.setTitle("人事变更");
        this.setSize(700, 700);
        this.setLocation(300, 10);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(false);
        this.setResizable(false);
        PdbProcess = new DbProcess();
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("查询")
                && !jTFQueryField.getText().isEmpty()){
            System.out.println("actionPerformed(). 查询");
            String sQueryField = jTFQueryField.getText().trim();
            queryProcess(sQueryField);
            jTFQueryField.setText("");
        }else if(e.getActionCommand().equals("查询所有记录")) {
            System.out.println("actionPerformed(). 查询所有记录");
            queryAllProcess();
        }else if(e.getActionCommand().equals("变更")
                && !jTFsID.getText().isEmpty()
                && !jTFChangeDescription.getText().isEmpty()){
            int sid=10001;
           
            try{
                String sql ="select max(sID) from staff;";
                PdbProcess.connect();
                ResultSet rs = PdbProcess.executeQuery(sql);
                if(rs.next()){
                    if(sid<=rs.getInt(1))
                        sid=rs.getInt(1);
                    System.out.println("sid"+sid);
                }
                PdbProcess.disconnect();
            }catch(SQLException sqle){
                System.out.println("sqle = " + sqle);
                JOptionPane.showMessageDialog(null,
                        "空集合","错误",JOptionPane.ERROR_MESSAGE);
            }finally {
                if(sid<Integer.parseInt(jTFsID.getText().trim())||10000>=Integer.parseInt(jTFsID.getText().trim())){
                    JOptionPane.showMessageDialog(null,
                            "员工工号不存在","错误",JOptionPane.ERROR_MESSAGE);
                }else{
                    int pid=100000;
                    try{
                        String sql ="select max(pID) from personnel;";
                        PdbProcess.connect();
                        ResultSet rs = PdbProcess.executeQuery(sql);
                        if(rs.next()){
                            if(pid<=rs.getInt(1)){
                                pid=rs.getInt(1);
                            }
                            System.out.println("pid"+pid);
                        }
                        PdbProcess.disconnect();
                    }catch(SQLException sqle){
                        System.out.println("sqle = " + sqle);
                        JOptionPane.showMessageDialog(null,
                                "空集合","错误",JOptionPane.ERROR_MESSAGE);
                    }finally {
                        try{
                            String sql ="select sState from staff where sID = '"+jTFsID.getText().trim()+"';";
                            PdbProcess.connect();
                            ResultSet rs = PdbProcess.executeQuery(sql);
                            rs.next();
                            if(rs.getString("sState").equals("T")){
                                if(SelectChangeCodeStr.equals("1-职务变动")){
                                    System.out.println("actionPerformed(). 职务变动");
                                    ChangeProcess(0);
                                    pid++;
                                    System.out.println("pid="+pid);
                                    jTFChangeID.setText(String.valueOf(pid+1));
                                    jTFsID.setText("");
                                    jTFChangeDescription.setText("");
                                }
                                if(SelectChangeCodeStr.equals("2-辞退")){
                                    System.out.println("actionPerformed(). 辞退");
                                    ChangeProcess(1);
                                    pid++;
                                    System.out.println("pid="+pid);
                                    jTFChangeID.setText(String.valueOf(pid+1));
                                    jTFsID.setText("");
                                    jTFChangeDescription.setText("");
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "该员工已被辞退，无法变更！","错误",JOptionPane.ERROR_MESSAGE);
                            }
                            PdbProcess.disconnect();
                        }catch(SQLException sqle){
                            JOptionPane.showMessageDialog(null,
                                    "该员工已被辞退，无法变更！","错误",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }
 
    public void queryProcess(String sQueryField)
    {
        try{
            // 建立查询条件
            String sql = "select pID,pChange,personnel.sID,sName,sJob,sState,pDescription from personnel,staff where ";
            String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);
            sql = sql + queryFieldStr;
            sql = sql + " = ";
            sql = sql + "'" + sQueryField + "'";
            sql = sql + "and personnel.sID = staff.sID ;";
 
            System.out.println("queryProcess(). sql = " + sql);
 
            PdbProcess.connect();
            ResultSet rs = PdbProcess.executeQuery(sql);
 
            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            PersonnelVector.clear();
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("pID"));
                v.add(rs.getString("pChange"));
                v.add(rs.getString("sID"));
                v.add(rs.getString("sName"));
                v.add(rs.getString("sJob"));
                v.add(rs.getString("sState"));
                v.add(rs.getString("pDescription"));
                PersonnelVector.add(v);
            }
 
            PersonnelRecordJTable.updateUI();
 
            PdbProcess.disconnect();
 
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
            String sql = "select pID,pChange,personnel.sID,sName,sJob,sState,pDescription from personnel,staff where ";
            //String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);
            sql = sql + "personnel.sID = staff.sID order by pID asc;";
            System.out.println("queryAllProcess(). sql = " + sql);
 
            PdbProcess.connect();
            ResultSet rs = PdbProcess.executeQuery(sql);
 
            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            PersonnelVector.clear();
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("pID"));
                v.add(rs.getString("pChange"));
                v.add(rs.getString("sID"));
                v.add(rs.getString("sName"));
                v.add(rs.getString("sJob"));
                v.add(rs.getString("sState"));
                v.add(rs.getString("pDescription"));
                PersonnelVector.add(v);
            }
 
            PersonnelRecordJTable.updateUI();
 
            PdbProcess.disconnect();
 
        }catch(SQLException sqle){
            System.out.println("sqle = " + sqle);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }
 
    public void ChangeProcess(int code)
    {
        String pID = jTFChangeID.getText().trim();
        String sID = jTFsID.getText().trim();
        String pChange="";
        String sJob="";
        String pDescription = jTFChangeDescription.getText().trim();
 
        if(jCBJobStr.equals("0-员工")){
            sJob="员工";
        }else if(jCBJobStr.equals("1-组长")){
            sJob="组长";
        }else if(jCBJobStr.equals("2-主任")){
            sJob="主任";
        }else if(jCBJobStr.equals("3-经理")){
            sJob="经理";
        }
        String sql="";
        if(code==0){
            pChange="1";
            sql = "update staff set sJob = '";
            sql = sql + sJob+"'";
            sql = sql + " WHERE sID = '"+sID+"';";
        }
        if(code==1){
            pChange="2";
            sql = "update staff set sState = 'F' WHERE sID = '"+sID+"';";
        }
        try{
            if (PdbProcess.executeUpdate(sql) < 1) {
                System.out.println("insertProcess(). update database failed.");
            }
        }catch(Exception e){
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }finally {
            // 建立插入条件
            sql = "insert into personnel values('";
            sql = sql + pID + "','";
            sql = sql + sID + "','";
            sql = sql + pChange + "','";
            sql = sql + pDescription + "');";
 
            System.out.println("insertProcess(). sql = " + sql);
            try{
                if (PdbProcess.executeUpdate(sql) < 1) {
                    System.out.println("insertProcess(). insert database failed.");
                }else{
                    JOptionPane.showMessageDialog(null,
                            "变更成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception e){
                System.out.println("e = " + e);
                JOptionPane.showMessageDialog(null,
                        "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
            }
            queryAllProcess();
        }
    }
    public String jCBSelectQueryFieldTransfer(String InputStr)
    {
        String outputStr = "";
        System.out.println("jCBSelectQueryFieldTransfer(). InputStr = " + InputStr);
 
        if(InputStr.equals("记录编号")){
            outputStr = "pID";
        }else if(InputStr.equals("员工工号")){
            outputStr = "personnel.sID";
        }else if(InputStr.equals("变更代码")){
            outputStr = "pChange";
        }
        System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);
        return outputStr;
    }
}