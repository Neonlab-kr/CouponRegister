import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Coupon extends JFrame {

	private JPanel contentPane;
	private JTextField couponInput;
	private JTextField memberInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Coupon frame = new Coupon();
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
	public Coupon() {
		String GuildMember[]= {"울프_","다연1","Neonlab","세인트폴리아","재현3","남검","재현2","히하호","유소현","사불상","실바니아","후루꾸루","BlackList","엘지마트","닥터핸즈","이쁘이","겨울나뭇","롤로노아조로","번집"};
		setTitle("마상지 쿠폰 등록기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 401, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel couponPanel = new JPanel();
		couponPanel.setBackground(Color.WHITE);
		couponPanel.setBounds(12, 302, 360, 43);
		contentPane.add(couponPanel);
		couponPanel.setLayout(null);
		
		couponInput = new JTextField();
		couponInput.setBounds(108, 11, 116, 21);
		couponPanel.add(couponInput);
		couponInput.setColumns(10);
		
		JButton registerButton = new JButton("\uC77C\uAD04 \uB4F1\uB85D\uD558\uAE30");
		registerButton.setBounds(236, 10, 109, 23);
		couponPanel.add(registerButton);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for(int i=0;i<GuildMember.length;i++) {
						Desktop.getDesktop().browse(new URI("https://gift.supermembers.net/coupon/?code="+ couponInput.getText() +"&scode=zio_kr&name="+GuildMember[i]+"&ok=1"));
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JLabel couponText = new JLabel("\uCFE0\uD3F0\uBC88\uD638\uC785\uB825 : ");
		couponText.setBounds(12, 14, 84, 15);
		couponPanel.add(couponText);
		
		JPanel memberPanel = new JPanel();
		memberPanel.setBounds(12, 10, 361, 284);
		contentPane.add(memberPanel);
		memberPanel.setLayout(null);
		
		memberInput = new JTextField();
		memberInput.setBounds(104, 5, 122, 21);
		memberPanel.add(memberInput);
		memberInput.setColumns(10);
		
		JLabel nicknameText = new JLabel("닉네임입력 :");
		nicknameText.setBounds(17, 8, 75, 15);
		memberPanel.add(nicknameText);
		
		JButton addButton = new JButton("추가");
		addButton.setBounds(232, 4, 57, 23);
		memberPanel.add(addButton);
		
		JButton deleteButton = new JButton("삭제");
		deleteButton.setBounds(292, 4, 57, 23);
		memberPanel.add(deleteButton);
		
		JList memberList = new JList();
		memberList.setBounds(12, 33, 337, 241);
		memberPanel.add(memberList);
	}
}
