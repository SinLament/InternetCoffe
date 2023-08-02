package InternetCaffe;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import javax.swing.JSlider;
import javax.swing.SwingUtilities;

public class Mouse extends JFrame {

	private JPanel contentPane;
	private Robot robot;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mouse frame = new Mouse();
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
	public Mouse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Chuột");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 10, 60, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblMnHnh = new JLabel("Màn hình");
		lblMnHnh.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMnHnh.setBounds(10, 127, 89, 25);
		contentPane.add(lblMnHnh);
		
		JLabel lblmThanh = new JLabel("Âm thanh");
		lblmThanh.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblmThanh.setBounds(10, 242, 105, 25);
		contentPane.add(lblmThanh);
		
		JSlider Mslider = new JSlider();
		Mslider.setBounds(10, 58, 462, 22);
        Mslider.setMinimum(0);
        Mslider.setMaximum(100);
        Mslider.setValue(50);
		contentPane.add(Mslider);
        Mslider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int value = Mslider.getValue();
               
                
            }
        });
		
		JSlider ScreenSlider = new JSlider();
		ScreenSlider.setBounds(10, 182, 462, 22);
		
		ScreenSlider.setMinimum(0);
		ScreenSlider.setMaximum(225);
		ScreenSlider.setValue(50);
		ScreenSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                float brightnessValue = (float) ScreenSlider.getValue() / 100f;
                try {
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        contentPane.add(ScreenSlider);
		
		JSlider SounSlider = new JSlider();
		SounSlider.setBounds(10, 291, 462, 22);
		SounSlider.setMinimum(0);
		SounSlider.setMaximum(100);
		SounSlider.setValue(50);
		SounSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int value = SounSlider.getValue();

            }
        });
		contentPane.add(SounSlider);
		
	}
}
