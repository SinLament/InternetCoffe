package InternetCaffe;

import java.awt.EventQueue;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class DoiMK extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoiMK frame = new DoiMK();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DoiMK() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 437, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mật khẩu cũ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 37, 94, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblMtKhuMi = new JLabel("Mật khẩu mới");
		lblMtKhuMi.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMtKhuMi.setBounds(10, 115, 94, 21);
		contentPane.add(lblMtKhuMi);
		
		JLabel lblXcNhn = new JLabel("Xác nhận");
		lblXcNhn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblXcNhn.setBounds(10, 191, 94, 21);
		contentPane.add(lblXcNhn);
		
		JButton btnNewButton = new JButton("OK");

		btnNewButton.setBounds(48, 228, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(253, 228, 85, 21);
		contentPane.add(btnCancel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(126, 39, 287, 19);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(126, 117, 287, 19);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(126, 193, 287, 19);
		contentPane.add(passwordField_2);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check()) {
				    String user = "sa";
				    String ps = "123";
				    String url = "jdbc:sqlserver://localhost:1433;databaseName=INTERNETCOFFEE;encrypt=true;trustServerCertificate=true;";

				    try {
				        Connection con = DriverManager.getConnection(url, user, ps);
				        String id = "fuku";
				        String currentPassword = new String(passwordField.getPassword());
				        String checkPasswordSql = "SELECT MATKHAU FROM KHACHHANG WHERE IDKH = ?";
				        PreparedStatement checkPasswordStmt = con.prepareStatement(checkPasswordSql);
				        checkPasswordStmt.setString(1, id);
				        ResultSet rs = checkPasswordStmt.executeQuery();
				        if (rs.next()) {
				            String savedPassword = rs.getString("MATKHAU");
				            if (Arrays.equals(currentPassword.toCharArray(), savedPassword.toCharArray())) {
				                String newPassword = new String(passwordField_1.getPassword());
				                String updatePasswordSql = "UPDATE KHACHHANG SET MATKHAU = ? WHERE IDKH = ?";
				                PreparedStatement updatePasswordStmt = con.prepareStatement(updatePasswordSql);
				                updatePasswordStmt.setString(1, newPassword);
				                updatePasswordStmt.setString(2, id);
				                int rowsAffected = updatePasswordStmt.executeUpdate();
				                if (rowsAffected > 0) {
				                    JOptionPane.showMessageDialog(null, "Cập nhật mật khẩu thành công");
				                } else {
				                    JOptionPane.showMessageDialog(null, "Không thể cập nhật mật khẩu");
				                }
				            } else {
				                JOptionPane.showMessageDialog(null, "Sai mật khẩu hiện tại");
				            }
				        } else {
				            JOptionPane.showMessageDialog(null, "Không tìm thấy tài khoản");
				        }

				        con.close();
				    } catch (Exception ex) {
				        System.out.println(ex);
				    }
				}
			}
		});
	}
	boolean check() {
		if (passwordField.getPassword().equals("") || passwordField_1.getPassword().equals("") ||passwordField_2.getPassword().equals("")){
			JOptionPane.showMessageDialog(null,"không được để trống");
			return false;
		} 		
		String user="sa";
		String ps="123";
		String url="jdbc:sqlserver://localhost:1433;databaseName=INTERNETCOFFEE;encrypt=true;trustServerCertificate=true;";
		try {
			Connection con =DriverManager.getConnection(url,user,ps);
			String sql = "SELECT * FROM dbo.KHACHHANG WHERE IDKH=? AND MATKHAU=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "fuku");
			st.setString(2, new String(passwordField.getPassword()));
			ResultSet rs = st.executeQuery();
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return true;
	}
}
