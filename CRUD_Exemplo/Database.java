import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.Calendar;

public class Database{
    public static void main(String[] args){
        Connection conn=Conectar();
        try{
            Statement s=conn.createStatement();
            s.executeUpdate("CREATE DATABASE IF NOT EXISTS empresa;");
            s.executeUpdate("CREATE TABLE IF NOT EXISTS empregado(id INT NOT NULL AUTO_INCREMENT,Nome VARCHAR(255),Email VARCHAR(255),Horario VARCHAR(20),Setor VARCHAR(255),Salario VARCHAR(20),PRIMARY KEY (Id));");
            conn.close();
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
        Scanner scan=new Scanner(System.in);
    }
    public static Connection Conectar() {
        Connection conn=null;
        try{
            String url="jdbc:mysql://localhost:3306/empresa?user=root&password=12345";
            conn=DriverManager.getConnection(url);
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
        return conn;
    }
    public static void Select(String nomeFunc){
        Connection conn=null;
        conn=Conectar();
        try{
            Statement s=conn.createStatement();
            ResultSet rs;
            if(nomeFunc==""){
                rs=s.executeQuery("select * from empregados;");
            }else{
                rs=s.executeQuery("select * from empregados where Nome LIKE '%"+nomeFunc+"%';");
            }
            while(rs.next()){
                System.out.println(rs.getInt("Id"));
                System.out.println(rs.getString("Nome"));
                System.out.println(rs.getString("Horario"));
                System.out.println(rs.getString("Setor"));
                System.out.println(rs.getString("Salario"));
            }
            conn.close();
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }
    public static void Insert(String nomeFunc,String email ,String nomeSetor,String salario) {
        Connection conn=null;
        conn=Conectar();
        try{
            Statement s=conn.createStatement();
            int id;
            try{
                ResultSet rs=s.executeQuery("SELECT MAX(Id) FROM empregados;");
                id=rs.getInt("MAX(Id)");
                System.out.println(rs.getInt("MAX(Id)"));
            }catch(SQLException erro){
                id=0;
            }
            Calendar data=Calendar.getInstance();
            String horario=data.get(Calendar.DATE)+"/"+(data.get(Calendar.MONTH)+1)+"/"+data.get(Calendar.YEAR)+" - "+data.get(Calendar.DATE)+"h "+data.get(Calendar.SECOND)+"m";
            s.executeUpdate("insert into empregados values("+id+",'"+nomeFunc+"','"+email+","+horario+"','"+nomeSetor+"','"+salario+"');");
            conn.close();
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }
    public static void Delete(String nomeSetor) {
        Connection conn=null;
        conn=Conectar();
        try{
            Statement s=conn.createStatement();
            s.executeUpdate("delete from setores where empregados='"+nomeSetor+"';");
            conn.close();
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }
    public static void Update(String nomeAtual,String novoNome) {
        Connection conn=null;
        conn=Conectar();
        try{
            Statement s=conn.createStatement();
            s.executeUpdate("update setores set empregados='"+novoNome+"' where empregados='"+nomeAtual+"';");
            conn.close();
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }
}
