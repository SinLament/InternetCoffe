package InternetCaffe;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ThongTin extends JFrame {

	private JPanel contentPane;
	private JTextField txtTen;
	private JTextField txtCCCD;
	private JTextField txtSDT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongTin frame = new ThongTin();
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
	public ThongTin() {
		setBounds(100, 100, 450, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Thong Tin Ca Nhan", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ho Ten: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(68, 55, 61, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CCCD: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(68, 100, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("SDT: ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(68, 147, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		txtTen = new JTextField();
		txtTen.setBounds(141, 50, 207, 26);
		contentPane.add(txtTen);
		txtTen.setColumns(10);
		
		txtCCCD = new JTextField();
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(141, 95, 207, 26);
		contentPane.add(txtCCCD);
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(141, 142, 207, 26);
		contentPane.add(txtSDT);
		
		txtTen.setEditable(false);
		txtCCCD.setEditable(false);
		txtSDT.setEditable(false);
	}
	public void loadData(String user) {
		try {
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=INTERNETCOFFEE;encrypt=true;trustServerCertificate=true;";
			String username = "SA";
			String password = "123456789@Aa";
			Connection c = DriverManager.getConnection(url, username, password);
			String sql = "SELECT HOTEN, CCCD, SDT FROM [dbo].[KHACHHANG] WHERE IDKH LIKE N'" + user + "'";
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString(1);
				String cccd = rs.getString(2);
				String sdt = rs.getString(3);
				
				txtTen.setText(name);
				txtCCCD.setText(cccd);
				txtSDT.setText(sdt);
			}
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
