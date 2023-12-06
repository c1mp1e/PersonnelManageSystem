 
import javax.swing.*;
 
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
 

public class Manage extends JFrame implements ActionListener{
    // 定义组件
    JButton jBInsert = null;//员工加入
    JButton jBChange = null;//人事变更
    JButton jBQueryUpdate = null;//查询修改
    JButton jBChangeMyself = null;//个人信息变更
 
    JPanel jP1, jP2,jP3,jP4 = null;
    JPanel jP = null;
 
    JScrollPane ManageJScrollPane = null;
 
    private static DbProcess MdbProcess;
    private static PersonnelChange P;
    private static NewEmployee N;
    private static Employee E;
    private static DatabaseCourseDesign2 D;
    private ResultSet rs;
    
    public Manage(ResultSet r) {
        MdbProcess = new DbProcess();
        rs = r;
        int pid=100000;
        try{
            String sql ="select max(pID) from personnel;";
            MdbProcess.connect();
            ResultSet rs = MdbProcess.executeQuery(sql);
            if(rs.next()){
                if(pid<=rs.getInt(1)){
                    pid=rs.getInt(1);
                }
                System.out.println("pid"+pid);
            }
            MdbProcess.disconnect();
        }catch(SQLException sqle){
            System.out.println("sqle = " + sqle);
            JOptionPane.showMessageDialog(null,
                    "无数据","错误",JOptionPane.ERROR_MESSAGE);
        }finally {
            System.out.println(pid);
            Manage.P = new PersonnelChange(pid+1);
        }
        // 组件
        jBInsert = new JButton("新员工档案录入");
        jBInsert.setFont(new Font("微软雅黑",Font.BOLD,15));
        jBChange = new JButton("  人事变更  ");
        jBChange.setFont(new Font("微软雅黑",Font.BOLD,15));
        jBQueryUpdate = new JButton("员工档案查询修改");
        jBQueryUpdate.setFont(new Font("微软雅黑",Font.BOLD,15));
        jBChangeMyself = new JButton(" 个人信息变更 ");
        jBChangeMyself.setFont(new Font("微软雅黑",Font.BOLD,15));
        // 设置监听
        jBInsert.addActionListener(this);
        jBChange.addActionListener(this);
        jBQueryUpdate.addActionListener(this);
        jBChangeMyself.addActionListener(this);
 
        ManageJScrollPane = new JScrollPane();
 
        jP1 = new JPanel();
        jP2 = new JPanel();
        jP3 = new JPanel();
        jP4 = new JPanel();
        jP = new JPanel();
 
        jP1.add(jBInsert);
        jP1.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP1.setPreferredSize(new Dimension(250,100));
 
        jP2.add(jBChange);
        jP2.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP2.setPreferredSize(new Dimension(250,100));
 
        jP3.add(jBQueryUpdate);
        jP3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP3.setPreferredSize(new Dimension(250,100));
 
        jP4.add(jBChangeMyself);
        jP4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP4.setPreferredSize(new Dimension(250,100));
 
        jP.setLayout(new GridLayout(3, 1));
        jP.add(jP1);
        jP.add(jP2);
        jP.add(jP3);
        jP.add(jP4);
 
        this.add("North", ManageJScrollPane);
        this.add("South", jP);
 
        this.setLayout(new BorderLayout());
        this.add(jP,BorderLayout.SOUTH);
 
        this.setTitle("管理界面");
        this.setSize(350, 400);
        this.setLocation(150, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        MdbProcess.disconnect();
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("新员工档案录入")){
            System.out.println("actionPerformed(). 新员工档案录入");
            int sid=10000;
            try{
                String sql ="select max(sID) from staff;";
                MdbProcess.connect();
                ResultSet rs = MdbProcess.executeQuery(sql);
                if(rs.next()){
                    if(sid<=rs.getInt(1)){
                        sid=rs.getInt(1);
                    }
                    System.out.println("sid"+sid);
                }
                MdbProcess.disconnect();
            }catch(SQLException sqle){
                System.out.println("sqle = " + sqle);
                JOptionPane.showMessageDialog(null,
                        "无数据","错误",JOptionPane.ERROR_MESSAGE);
            }finally {
                Manage.N = new NewEmployee(sid+1,P);
            }
        }else if(e.getActionCommand().equals("  人事变更  ")) {
            System.out.println("actionPerformed(). 人事变更");
            P.PersonnelVector.clear();
            P.PersonnelRecordJTable.updateUI();
            P.setVisible(true);
        }else if(e.getActionCommand().equals("员工档案查询修改")) {
            System.out.println("actionPerformed(). 员工档案查询修改");
            Manage.D = new DatabaseCourseDesign2();
        }else if(e.getActionCommand().equals(" 个人信息变更 ")) {  
			System.out.println("actionPerformed(). 个人信息变更");
			MdbProcess.connect();
            Manage.E = new Employee(rs);
            MdbProcess.disconnect();
    }
  
        }

}