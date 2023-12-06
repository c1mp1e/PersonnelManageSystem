 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
 
public class DbProcess{
    Connection connection = null;
    ResultSet rs = null;
 
    //mysql���ݿ�url
    String userMySql="root"; 
    String passwordMySql="123123";
    String urlMySql = "jdbc:mysql://localhost:3306/InfoDb?user="
            +userMySql+"&password="+passwordMySql + "&serverTimezone=UTC&useUnicode=true&characterEncoding=gbk";
    
    //sqlserver���ݿ�url
    //String urlSqlServer = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=InfoDb";
    
    public DbProcess() {
        try {
            //mysql���ݿ�����������������
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("mysql���ݿ��������سɹ�");
            
            //sqlserver���ݿ�����������������
        //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //System.out.println("sqlserver���ݿ��������سɹ�");
 
        }
        catch(java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    public void connect(){
        try{
            //mysql���ݿ�
            connection = DriverManager.getConnection(urlMySql);  
            
            //sqlserver���ݿ�
            //connection = DriverManager.getConnection(urlSqlServer);
            
 
            if(connection!=null){
                System.out.println("���ݿ����ӳɹ�");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void disconnect(){
        try{
            if(connection != null){
                connection.close();
                connection = null;
                System.out.println("���ݿ�Ͽ��ɹ�");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
 
 
    public ResultSet executeQuery(String sql) {
        try {
            System.out.println("executeQuery(). sql = " + sql);
            
            PreparedStatement pstm = connection.prepareStatement(sql);
            // ִ�в�ѯ
            rs = pstm.executeQuery();
        } 
        catch(SQLException ex) { 
            ex.printStackTrace();
        }
        return rs;
    }
    
    //����
    //executeUpdate �ķ���ֵ��һ��������ָʾ��Ӱ��������������¼�������
    //executeUpdate����ִ�� INSERT��UPDATE �� DELETE ���
    //�Լ� SQL DDL�����ݶ������ԣ���䣬���� CREATE TABLE �� DROP TABLE��
    
    //ִ������ɾ�������ķ���
    public int executeUpdate(String sql) {
        int count = 0;
        connect();
        try {
            Statement stmt = connection.createStatement();
            count = stmt.executeUpdate(sql);
        } 
        catch(SQLException ex) { 
            System.err.println(ex.getMessage());        
        }
        disconnect();
        return count;
    }
}