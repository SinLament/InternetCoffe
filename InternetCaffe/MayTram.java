package InternetCaffe;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Container;

import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class MayTram extends JFrame implements Runnable {

	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtMaMay;
	private JTextField txtTTG;
	private JTextField txtTGSD;
	private JTextField txtTGCL;
	private JTextField txtPhiMay;
	private JTextField txtTongTien;
	private JTable tblGame = new JTable();
	private JTextField txtSearch;
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=INTERNETCOFFEE;encrypt=true;trustServerCertificate=true;";
	String username = "sa";
	String password = "123";
	private ArrayList<MenuGame> listGame = new ArrayList<>();
	Time tgConlai;
	float giaMay;
	float tongTien = 0;
	String currentUser = "";
	String currentPass = "";
	private JPasswordField txtTreo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MayTram frame = new MayTram();
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
	public MayTram() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnMenuGame = new JButton("Menu Game");
		btnMenuGame.setBounds(16, 6, 117, 43);
		contentPane.add(btnMenuGame);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(319, 6, 275, 476);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ma may: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(6, 17, 82, 16);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nguoi dung: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(6, 45, 82, 16);
		panel.add(lblNewLabel_1);

		txtUser = new JTextField();
		txtUser.setBounds(100, 40, 155, 26);
		panel.add(txtUser);
		txtUser.setColumns(10);

		txtMaMay = new JTextField();
		txtMaMay.setColumns(10);
		txtMaMay.setBounds(100, 12, 155, 26);
		panel.add(txtMaMay);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Su dung",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(6, 78, 263, 117);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblTongThoiGian = new JLabel("Tong thoi gian:");
		lblTongThoiGian.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongThoiGian.setBounds(6, 28, 123, 16);
		panel_1.add(lblTongThoiGian);

		JLabel lblThoiGianSu = new JLabel("Thoi gian su dung:");
		lblThoiGianSu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblThoiGianSu.setBounds(6, 56, 123, 16);
		panel_1.add(lblThoiGianSu);

		JLabel lblThoiGianCon = new JLabel("Thoi gian con lai:");
		lblThoiGianCon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblThoiGianCon.setBounds(6, 84, 123, 16);
		panel_1.add(lblThoiGianCon);

		txtTTG = new JTextField();
		txtTTG.setColumns(10);
		txtTTG.setBounds(141, 23, 116, 26);
		panel_1.add(txtTTG);

		txtTGSD = new JTextField();
		txtTGSD.setColumns(10);
		txtTGSD.setBounds(141, 51, 116, 26);
		panel_1.add(txtTGSD);

		txtTGCL = new JTextField();
		txtTGCL.setColumns(10);
		txtTGCL.setBounds(141, 79, 116, 26);
		panel_1.add(txtTGCL);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Chi phi",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1.setBounds(6, 207, 263, 86);
		panel.add(panel_1_1);

		JLabel lblPhiMay = new JLabel("Phi may:");
		lblPhiMay.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhiMay.setBounds(6, 28, 91, 16);
		panel_1_1.add(lblPhiMay);

		JLabel lblTongTien = new JLabel("Tong tien:");
		lblTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongTien.setBounds(6, 54, 91, 16);
		panel_1_1.add(lblTongTien);

		txtPhiMay = new JTextField();
		txtPhiMay.setColumns(10);
		txtPhiMay.setBounds(109, 23, 148, 26);
		panel_1_1.add(txtPhiMay);

		txtTongTien = new JTextField();
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(109, 49, 148, 26);
		panel_1_1.add(txtTongTien);

		JButton btnDangXuat = new JButton("Dang Xuat");
		btnDangXuat.setBounds(152, 305, 117, 29);
		panel.add(btnDangXuat);

		JButton btnTuyChon = new JButton("Tuy Chon");
		btnTuyChon.setBounds(16, 305, 117, 29);
		panel.add(btnTuyChon);

		JButton btnTienIch = new JButton("Tien Ich");
		btnTienIch.setBounds(16, 346, 117, 29);
		panel.add(btnTienIch);

		JButton btnGiaoTiep = new JButton("Giao Tiep");
		btnGiaoTiep.setBounds(152, 346, 117, 29);
		panel.add(btnGiaoTiep);

		JButton btnDichVu = new JButton("Dich Vu");
		btnDichVu.setBounds(16, 387, 117, 29);
		panel.add(btnDichVu);

		JButton btnThongTin = new JButton("Thong Tin Khach Hang");
		btnThongTin.setBounds(16, 428, 253, 29);
		panel.add(btnThongTin);

		JButton btnTreo = new JButton("Treo May");
		btnTreo.setBounds(152, 387, 117, 29);
		panel.add(btnTreo);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(16, 61, 275, 421);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(tblGame);
		scrollPane.setBounds(6, 47, 263, 368);
		panel_2.add(scrollPane);

		JButton btnSearch = new JButton("Tim kiem");
		btnSearch.setBounds(158, 6, 117, 29);
		panel_2.add(btnSearch);

		txtSearch = new JTextField();
		txtSearch.setBounds(6, 9, 140, 26);
		panel_2.add(txtSearch);
		txtSearch.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(178, 197, 243, 119);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("May dang treo");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(60, 18, 126, 16);
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setBounds(6, 51, 69, 16);
		panel_3.add(lblNewLabel_3);
		
		txtTreo = new JPasswordField();
		txtTreo.setBounds(70, 46, 168, 26);
		panel_3.add(txtTreo);
		txtTreo.setColumns(10);
		
		JButton btnINTreo = new JButton("Tiep Tuc");
		btnINTreo.setBounds(69, 79, 117, 29);
		panel_3.add(btnINTreo);
		panel_3.setVisible(false);

		btnMenuGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel_2.isVisible())
					panel_2.setVisible(false);
				else
					panel_2.setVisible(true);

			}
		});
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDataMenuGame(txtSearch.getText());
			}
		});
		btnThongTin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongTin tt1 = new ThongTin();
				tt1.loadData(currentUser);
				tt1.setVisible(true);
			}
		});
		btnTreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_2.setVisible(false);
				btnMenuGame.setVisible(false);
				panel_3.setVisible(true);
			}
		});
		btnINTreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtTreo.getText().equals(currentPass)) {
					panel.setVisible(true);
					panel_2.setVisible(true);
					btnMenuGame.setVisible(true);
					panel_3.setVisible(false);
				}else JOptionPane.showMessageDialog(null, "Incorrect password");
			}
		});
		loadDataMenuGame("");
		loadUserMT("fuku", "MAY002"); 
	}

	public void loadDataMenuGame(String find) {
		DefaultTableModel modelGame = (DefaultTableModel) tblGame.getModel();
		String[] col = { "Ten Game", "The Loai" };
		modelGame.setRowCount(0);
		modelGame.setColumnIdentifiers(col);
		try {
			listGame.clear();
			Connection c = DriverManager.getConnection(url, username, password);
			String sql = "";
			if (find.equals(""))
				sql = "SELECT TENGAME, THELOAI FROM [dbo].[MENUGAME]";
			else
				sql = "SELECT TENGAME, THELOAI FROM [dbo].[MENUGAME] where TENGAME like N'" + find + "'";
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString(1);
				String kind = rs.getString(2);
				MenuGame mg = new MenuGame(name, kind);
				listGame.add(mg);
			}
			for (MenuGame menuGame : listGame) {
				Object[] row = { menuGame.tenGame, menuGame.theLoai };
				modelGame.addRow(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void loadUserMT(String user, String maMay) {
		try {
			currentUser = user;
			Connection c = DriverManager.getConnection(url, username, password);
			String sql = "SELECT IDKH, TG, matkhau FROM [dbo].[KHACHHANG] WHERE IDKH LIKE N'" + user + "'";
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString(1);
				Time tg = rs.getTime(2);
				tgConlai = rs.getTime(2);
				currentPass = rs.getString(3);
				txtUser.setText(name);
				txtTTG.setText(tg + "");
			}
			String sql1 = "SELECT TENMAYTRAM, GIATIEN FROM [dbo].[MAYTRAM] WHERE IDMAYTRAM LIKE N'" + maMay + "'";
			Statement st1 = c.createStatement();
			ResultSet rs1 = st1.executeQuery(sql1);
			while (rs1.next()) {
				String name = rs1.getString(1);
				float gia = rs1.getFloat(2);
				giaMay = rs1.getFloat(2);
				txtMaMay.setText(name);
				txtPhiMay.setText(gia + "");
			}
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		int second = 0;
		giaMay = (giaMay / 60) / 60;
		while (true) {
			Time time = new Time(0, 0, 0);
			time.setSeconds(second);
			second++;
			tgConlai.setSeconds(tgConlai.getSeconds() - 1);
			tongTien += + giaMay;
			txtTGSD.setText(time + "");
			txtTGCL.setText(tgConlai + "");
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			txtTongTien.setText(numberFormat.format(tongTien)+"");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}
