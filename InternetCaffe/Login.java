package InternetCaffe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Login extends JFrame{
	JTextField txtUsername;
	JPasswordField txtPassword;
	ArrayList<User> listUser = new ArrayList<User>();

	public Login() {
		this.setVisible(true);
		this.loadData();
		this.init();
	}

	private void init() {
		this.setTitle("Sign Up");
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);

		JLabel lbUser = new JLabel("Username: ");
		JLabel lbPass = new JLabel("Password: ");
		txtUsername = new JTextField();
		txtPassword = new JPasswordField();
		JButton btnSign = new JButton("Sign Up");
		JButton btnCancel = new JButton("Cancel");
		lbUser.setBounds(20, 20, 80, 80);
		lbPass.setBounds(20, 60, 80, 80);
		txtUsername.setBounds(100, 45, 170, 30);
		txtPassword.setBounds(100, 85, 170, 30);
		btnSign.setBounds(100, 145, 60, 40);
		btnCancel.setBounds(160, 145, 60, 40);

		ActionListener ac = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String str = e.getActionCommand();
				if (str.equals("Sign Up")) {
					signAccount();
				} else
					System.exit(0);
			}
		};
		btnSign.addActionListener(ac);
		btnCancel.addActionListener(ac);

		JPanel pn1 = new JPanel();
		pn1.setLayout(null);
		pn1.setBounds(50, 30, 300, 200);
		pn1.setBorder(new TitledBorder(BorderFactory.createLoweredBevelBorder(), "Login", TitledBorder.LEFT,
				TitledBorder.ABOVE_TOP));
		pn1.add(lbUser);
		pn1.add(txtUsername);
		pn1.add(lbPass);
		pn1.add(txtPassword);
		pn1.add(btnSign);
		pn1.add(btnCancel);

		this.add(pn1);

	}


	public boolean checkUP() {
		if (txtUsername.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Nhap Username");
			txtUsername.requestFocus();
			return false;
		} else if (txtPassword.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Nhap Password");
			txtPassword.requestFocus();
			return false;
		} else
			return true;
	}

	public void signAccount() {
		String us = txtUsername.getText();
		String pw = txtPassword.getText();
		int checkNum = 0;
		for (User user : listUser) {
			if (checkUP()) {
				if (pw.equals(user.passWord) && us.equals(user.userName)) {
					checkNum++;
				}
			}
		}

		if (checkNum == 1) {
			MayTram frame = new MayTram();
			frame.loadUserMT(us, "MAY004");
			frame.setVisible(true);
			this.setVisible(false);
		} else JOptionPane.showMessageDialog(this, "Sai mat khau hoac tai khoan");
	}

	public static void main(String[] args) {
		new Login();
	}

	public void loadData() {
		try {
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=INTERNETCOFFEE;encrypt=true;trustServerCertificate=true;";
			String username = "sa";
			String password = "123";
			Connection c = DriverManager.getConnection(url, username, password);
			String sql = "SELECT IDKH, MATKHAU FROM [dbo].[KHACHHANG]";
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString(1);
				String pass = rs.getString(2);
				User us1 = new User(name, pass);
				listUser.add(us1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
