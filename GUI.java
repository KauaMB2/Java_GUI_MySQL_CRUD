import java.awt.Font;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.util.Calendar;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import java.awt.*;

public class GUI implements ActionListener{
	private static JFrame gui=new JFrame();
	private static JButton buttonSearch;
	private static JButton buttonInsert;
	private static JButton buttonDelete;
	private static JButton buttonUpdate;
	private static JLabel labelSearch;
	private static JLabel labelNome;
	private static JLabel labelSetor;
	private static JLabel labelEmail;
	private static JLabel labelSalario;
	private static JPanel panel2;
	private static JPanel panel1;
	private static JTextField inputSearch;
	private static JTextField inputNome;
	private static JTextField inputSetor;
	private static JTextField inputEmail;
	private static JSlider trackbar;
	private static DefaultTableModel TableModel;
	private static JTable table;
	private static JScrollPane barraRolagem;
	private static JMenuBar menuBar;
	private static JMenu programaMenu;
	private static JMenu estiloMenu;
	private static JMenuItem exitItem;
	private static ImageIcon exitIcon;
	private static Object[][] dado;

	// Create a NumberFormat instance for Brazilian Real (R$)
    private static NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	public GUI(){

		labelSearch=new JLabel();
		labelSearch.setBounds(30,400,160,40);
		labelSearch.setText("Pesquisar(Nome):");
		labelSearch.setForeground(new Color(0x00FF00));//set text-color
		labelSearch.setFont(new Font(null,Font.PLAIN,20));//Set the font
		inputSearch=new JTextField();//Text Box
		inputSearch.setBounds(30,435,160,25);
		inputSearch.setFont(new Font("Consolas",Font.PLAIN,20));//Font
		inputSearch.setForeground(new Color(0x00FF00));//Color of lyrics
		inputSearch.setBackground(Color.white);//Backgound color
		inputSearch.setCaretColor(Color.blue);// Cor do cursor
		inputSearch.setEditable(true);//Set if the Textbox is editable or not
		buttonSearch=new JButton();
		buttonSearch.setBounds(30,470,120,30);//(x,y,Width,Height)
		buttonSearch.addActionListener(this);//Add Action Listener in the botton
		buttonSearch.setText("Pesquisar");//Set the button text
		buttonSearch.setFocusable(false);//Focus on the botton after clock it
		buttonSearch.setFont(new Font("Comic Sans",Font.BOLD,15));//Set font
		buttonSearch.setForeground(Color.green);//Set the color of the lyrics
		buttonSearch.setBackground(Color.lightGray);//Set the background color
		buttonSearch.setEnabled(true);//Set if the button is or not enable to click

		labelNome=new JLabel();
		labelNome.setBounds(230,400,160,40);
		labelNome.setText("Nome:");
		labelNome.setForeground(new Color(0x00FF00));//set text-color
		labelNome.setFont(new Font(null,Font.PLAIN,20));//Set the font
		inputNome=new JTextField();//Text Box
		inputNome.setBounds(230,435,160,25);
		inputNome.setFont(new Font("Consolas",Font.PLAIN,20));//Font
		inputNome.setForeground(new Color(0x00FF00));//Color of lyrics
		inputNome.setBackground(Color.white);//Backgound color
		inputNome.setCaretColor(Color.blue);// Cor do cursor
		inputNome.setEditable(true);//Set if the Textbox is editable or not

		labelSetor=new JLabel();
		labelSetor.setBounds(230,480,160,40);
		labelSetor.setText("Setor:");
		labelSetor.setForeground(new Color(0x00FF00));//set text-color
		labelSetor.setFont(new Font(null,Font.PLAIN,20));//Set the font
		inputSetor=new JTextField();//Text Box
		inputSetor.setBounds(230,515,160,25);
		inputSetor.setFont(new Font("Consolas",Font.PLAIN,20));//Font
		inputSetor.setForeground(new Color(0x00FF00));//Color of lyrics
		inputSetor.setBackground(Color.white);//Backgound color
		inputSetor.setCaretColor(Color.blue);// Cor do cursor
		inputSetor.setEditable(true);//Set if the Textbox is editable or not

		labelEmail=new JLabel();
		labelEmail.setBounds(430,400,160,40);
		labelEmail.setText("E-mail:");
		labelEmail.setForeground(new Color(0x00FF00));//set text-color
		labelEmail.setFont(new Font(null,Font.PLAIN,20));//Set the font
		inputEmail=new JTextField();//Text Box
		inputEmail.setBounds(430,435,160,25);
		inputEmail.setFont(new Font("Consolas",Font.PLAIN,20));//Font
		inputEmail.setForeground(new Color(0x00FF00));//Color of lyrics
		inputEmail.setBackground(Color.white);//Backgound color
		inputEmail.setCaretColor(Color.blue);// Cor do cursor
		inputEmail.setEditable(true);//Set if the Textbox is editable or not

		labelSalario=new JLabel();
		labelSalario.setBounds(430,480,180,40);
		labelSalario.setText("Salário(R$ 1212):");
		labelSalario.setForeground(new Color(0x00FF00));//set text-color
		labelSalario.setFont(new Font(null,Font.PLAIN,20));//Set the font
		trackbar=new JSlider();
		trackbar.setMinimum(1212);//min
    	trackbar.setMaximum(10000);//max
    	trackbar.setValue(1212);//Set the initial value
    	trackbar.setBounds(430,515,160,25);
    	trackbar.setBackground(Color.black);
    	// Add a change listener to the trackbar
	    trackbar.addChangeListener(new ChangeListener(){
	        public void stateChanged(ChangeEvent e){
	        	// Call the function when the value of the trackbar changes
	        	onTrackbarValueChanged();
	        }
	    });
		
		buttonInsert=new JButton();
		buttonInsert.setBounds(610,430,100,30);//(x,y,Width,Height)
		buttonInsert.addActionListener(this);//Add Action Listener in the botton
		buttonInsert.setText("Inserir");//Set the button text
		buttonInsert.setFocusable(false);//Focus on the botton after clock it
		buttonInsert.setFont(new Font("Comic Sans",Font.BOLD,15));//Set font
		buttonInsert.setForeground(Color.green);//Set the color of the lyrics
		buttonInsert.setBackground(Color.lightGray);//Set the background color
		buttonInsert.setEnabled(true);//Set if the button is or not enable to click

		buttonDelete=new JButton();
		buttonDelete.setBounds(610,480,100,30);//(x,y,Width,Height)
		buttonDelete.addActionListener(this);//Add Action Listener in the botton
		buttonDelete.setText("Deletar");//Set the button text
		buttonDelete.setFocusable(false);//Focus on the botton after clock it
		buttonDelete.setFont(new Font("Comic Sans",Font.BOLD,15));//Set font
		buttonDelete.setForeground(Color.green);//Set the color of the lyrics
		buttonDelete.setBackground(Color.lightGray);//Set the background color
		buttonDelete.setEnabled(true);//Set if the button is or not enable to click

		buttonUpdate=new JButton();
		buttonUpdate.setBounds(610,530,100,30);//(x,y,Width,Height)
		buttonUpdate.addActionListener(this);//Add Action Listener in the botton
		buttonUpdate.setText("Atualizar");//Set the button text
		buttonUpdate.setFocusable(false);//Focus on the botton after clock it
		buttonUpdate.setFont(new Font("Comic Sans",Font.BOLD,15));//Set font
		buttonUpdate.setForeground(Color.green);//Set the color of the lyrics
		buttonUpdate.setBackground(Color.lightGray);//Set the background color
		buttonUpdate.setEnabled(true);//Set if the button is or not enable to click

		panel1=new JPanel();
		panel1.setBounds(50,50,640,340);
		panel1.setBackground(Color.black);
		panel1.setBorder(BorderFactory.createLineBorder(Color.green,5));
		panel2=new JPanel();
		panel2.setBounds(0,400,734,210);
		panel2.setBackground(Color.black);
		panel2.setBorder(BorderFactory.createLineBorder(Color.green,5));

		gerarTabela();
		atualizarTabela("");

		menuBar=new JMenuBar();
		programaMenu=new JMenu("Programa");
		estiloMenu=new JMenu("Estilo");

		exitItem=new JMenuItem("Sair");
		programaMenu.addActionListener(this);
		programaMenu.setMnemonic(KeyEvent.VK_P);//Alt+p
		estiloMenu.setMnemonic(KeyEvent.VK_E);//Alt+e
		exitIcon=new ImageIcon("imgs/pink_door.png");
		exitItem.setIcon(exitIcon);

		programaMenu.add(exitItem);

		menuBar.add(programaMenu);
		menuBar.add(estiloMenu);

		// gui.add(menuBar);
		// ImageIcon logo=new ImageIcon("imgs/logo.png");
		// gui.setIconImage(logo.getImage());//Set a image for be the GUI Icon
		// gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// gui.setLayout(null);
		// gui.setSize(750,650);
		// gui.getContentPane().setBackground(Color.white);
		// gui.setResizable(false);

		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setLayout(null);
		gui.setJMenuBar(menuBar);
		gui.setSize(750,650);
		gui.setResizable(true);
		gui.getContentPane().setBackground(Color.white);
		ImageIcon logo=new ImageIcon("imgs/logo.png");
		gui.setIconImage(logo.getImage());//Set a image for be the GUI Icon

		gui.add(labelSearch);
		gui.add(buttonSearch);
		gui.add(inputSearch);
		gui.add(labelNome);
		gui.add(inputNome);
		gui.add(labelSetor);
		gui.add(inputSetor);
		gui.add(labelEmail);
		gui.add(inputEmail);
		gui.add(labelSalario);
		gui.add(trackbar, BorderLayout.NORTH);
		gui.add(buttonInsert);
		gui.add(buttonDelete);
		gui.add(buttonUpdate);
		gui.add(panel2);
		gui.add(panel1);
		gui.setVisible(true);
	}
	public static void gerarTabela(){
    	TableModel=new DefaultTableModel();
    	TableModel.addColumn("Id");
		TableModel.addColumn("Nome");
		TableModel.addColumn("Email");
		TableModel.addColumn("Horario");
		TableModel.addColumn("Setor");
		TableModel.addColumn("Salario");
    }
    public static void atualizarTabela(String inputSearch){
    	if(table!=null){
    		((DefaultTableModel) table.getModel()).setNumRows(0);
    	}
		dado=Select(inputSearch);
		for(int i=0;i<dado.length;i++){
			TableModel.insertRow(i,dado[i]);
		}
		table=new JTable(TableModel){
			public boolean isCellEditable(int rowIndex, int colIndex){
				return false;
			}
		};
		panel1.setLayout(new GridLayout(1,1));
		barraRolagem=new JScrollPane(table);
		panel1.add(barraRolagem);
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
    public static Object[][] Select(String nomeFunc){
        Connection conn=Conectar();
        Object[][] tableData;
        try{
            Statement s=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs;
            if(nomeFunc==""){
                rs=s.executeQuery("select * from empregados;");
            }else{
                rs=s.executeQuery("select * from empregados where  Nome LIKE '%"+nomeFunc+"%';");
            }
            rs.last();//Get the last line of the DB
            int linhas=rs.getRow();//Get the number of the last line of DB
            rs.beforeFirst();//It returns to the begin of the list of registers
            tableData=new Object[linhas][6];
            for(int i=0;rs.next()==true;i++){
                tableData[i][0]=rs.getString("Id");
                tableData[i][1]=rs.getString("Nome");
                tableData[i][2]=rs.getString("Email");
                tableData[i][3]=rs.getString("Horario");
                tableData[i][4]=rs.getString("Setor");
                tableData[i][5]=rs.getString("Salario");
            }
            conn.close();
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
            tableData=new Object[0][0];
        }
        panel1.removeAll();//Remove todos os elementos do panel1
        panel1.revalidate();//Reseta o panel1 para a tabela atualizada
        return tableData;
    }
    public static void Insert(String nomeFunc,String email,String nomeSetor,String salario){
    	Connection conn=Conectar();
	    try{
	        Statement s=conn.createStatement();
	        int id;
	        try{
	            ResultSet rs=s.executeQuery("SELECT MAX(Id) FROM empregados;");
	            id=rs.getInt("MAX(Id)");
	        }
	        catch(SQLException erro){
	            id=0;
	        }
	        Calendar data=Calendar.getInstance();
	        String pm_am;
	        if(data.get(Calendar.AM_PM)==0){
	        	pm_am=" AM";
	        }else{
	        	pm_am=" PM";
	        }
	        String horario=data.get(Calendar.DATE)+"/"+(data.get(Calendar.MONTH)+1)+"/"+data.get(Calendar.YEAR)+" - "+data.get(Calendar.HOUR)+"h "+data.get(Calendar.MINUTE)+"m"+pm_am;
	        if((nomeFunc.equals(""))||(email.equals(""))||(nomeSetor.equals(""))||(salario.equals(""))){
	        	JOptionPane.showMessageDialog(null, "Preencha todos os campos para inserir um dado.");
	        	return;
	        }
			inputNome.setText("");
			inputEmail.setText("");
			inputSetor.setText("");
			trackbar.setValue(1212);
			s.executeUpdate("insert into empregados values("+id+",'"+nomeFunc+"','"+email+"','"+horario+"','"+nomeSetor+"','"+salario+"');");
			conn.close();
		}
		catch(SQLException erro){
			JOptionPane.showMessageDialog(null, erro.getMessage());
		}
		panel1.removeAll();//Remove todos os elementos do panel1
        panel1.revalidate();//Reseta o panel1 para a tabela atualizada
    }
    public static void Delete(String nomeFunc){
        Connection conn=Conectar();
        try{
            Statement s=conn.createStatement();
            s.executeUpdate("delete from empregados where Nome='"+nomeFunc+"';");
            conn.close();
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
        panel1.removeAll();//Remove todos os elementos do panel1
        panel1.revalidate();//Reseta o panel1 para a tabela atualizada
    }
    public static void Update(String nomeAtual,String novoNome,String email,String nomeSetor,String salario) {
        Connection conn=conn=Conectar();
        Calendar data=Calendar.getInstance();
	    String pm_am;
	    if(data.get(Calendar.AM_PM)==0){
	        pm_am=" AM";
	    }else{
	    	pm_am=" PM";
        }
		String horario=data.get(Calendar.DATE)+"/"+(data.get(Calendar.MONTH)+1)+"/"+data.get(Calendar.YEAR)+" - "+data.get(Calendar.HOUR)+"h "+data.get(Calendar.MINUTE)+"m"+pm_am;
	    if((novoNome.equals(""))||(email.equals(""))||(nomeSetor.equals(""))||(salario.equals(""))){
	    	JOptionPane.showMessageDialog(null, "Preencha todos os campos para inserir um dado.");
	    	return;
	    }
        try{
        	Statement s=conn.createStatement();
            s.executeUpdate("update empregados set Nome='"+novoNome+"',Email='"+email+"',Horario='"+horario+"',Setor='"+nomeSetor+"',Salario='"+salario+"' where Nome='"+nomeAtual+"';");
            //update empregados set Nome='novoNome',Email='email',Horario='horario',Setor='nomeSempregadosetor',Salario='salario' where Nome='nomeAtual';
            conn.close();
            inputNome.setText("");
			inputEmail.setText("");
			inputSetor.setText("");
			trackbar.setValue(1212);
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
        panel1.removeAll();//Remove todos os elementos do panel1
        panel1.revalidate();//Reseta o panel1 para a tabela atualizada
    }
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==buttonSearch){
			atualizarTabela(inputSearch.getText());
			inputSearch.setText("");
		}
		if(e.getSource()==buttonInsert){
			String salarioValor=formatter.format(trackbar.getValue());
	        Insert(inputNome.getText(),inputEmail.getText(),inputSetor.getText(),salarioValor);
	        atualizarTabela("");
		}
		if(e.getSource()==buttonDelete){
			int rowNumber=table.getSelectedRow();
			if(rowNumber==-1){
				JOptionPane.showMessageDialog(null,"Selecione uma linha para ser deletada.");
				return;
			}
			String nomeFunc=table.getModel().getValueAt(rowNumber,1).toString();
			Delete(nomeFunc);
			atualizarTabela("");
		}
		if(e.getSource()==buttonUpdate){
			int rowNumber=table.getSelectedRow();
			if(rowNumber==-1){
				JOptionPane.showMessageDialog(null,"Selecione uma linha para ser deletada.");
				return;
			}
			String nomeAtual=table.getModel().getValueAt(rowNumber,1).toString();
			String salarioValor=formatter.format(trackbar.getValue());
			Update(nomeAtual,inputNome.getText(),inputEmail.getText(),inputSetor.getText(),salarioValor);
			atualizarTabela("");
		}
		if(e.getSource()==exitItem){
			System.exit(0);
		}
	}
	// The function to be called when the trackbar value changes
	public static void onTrackbarValueChanged() {
	    labelSalario.setText("Salário(R$ "+trackbar.getValue()+"):");
	}
}