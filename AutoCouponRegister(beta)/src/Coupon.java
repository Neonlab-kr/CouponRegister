import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.Vector;
import java.awt.Desktop;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Coupon extends JFrame {

	private JPanel contentPane;
	private JTextField couponInput;
	private JTextField memberInput;
	private JScrollPane memberScroll;
	private BufferedWriter writer = null;
	private BufferedReader reader = null;
	Vector<String> GuildMember = new Vector<String>();

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
	 * 
	 * @throws IOException
	 */
	public Coupon() throws IOException {
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
				try {
					for(String nickname:GuildMember)
						writer.write(nickname+"\n");
					writer.flush();
					writer.close();
				} catch (IOException io) {
					io.printStackTrace();
				}
			}

			});
		
		setTitle("마상지 쿠폰 등록기 (제작자 : Neonlab)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel couponPanel = new JPanel();
		couponPanel.setBackground(Color.WHITE);
		couponPanel.setBounds(12, 328, 398, 43);
		contentPane.add(couponPanel);
		couponPanel.setLayout(null);

		couponInput = new JTextField();
		couponInput.setBounds(108, 11, 127, 21);
		couponPanel.add(couponInput);
		couponInput.setColumns(10);

		JButton registerButton = new JButton("\uC77C\uAD04 \uB4F1\uB85D\uD558\uAE30");
		registerButton.setBounds(247, 10, 139, 23);
		couponPanel.add(registerButton);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for (String nickname:GuildMember) {
						Desktop.getDesktop().browse(new URI("https://gift.supermembers.net/coupon/?code="
								+ couponInput.getText() + "&scode=zio_kr&name=" + nickname + "&ok=1"));
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
				couponInput.setText("");
			}
		});

		JLabel couponText = new JLabel("\uCFE0\uD3F0\uBC88\uD638\uC785\uB825 : ");
		couponText.setBounds(12, 14, 84, 15);
		couponPanel.add(couponText);

		JPanel memberPanel = new JPanel();
		memberPanel.setBounds(12, 10, 398, 308);
		contentPane.add(memberPanel);
		memberPanel.setLayout(null);

		memberInput = new JTextField();
		memberInput.setBounds(99, 5, 122, 21);
		memberPanel.add(memberInput);
		memberInput.setColumns(10);

		JLabel nicknameText = new JLabel("닉네임입력 :");
		nicknameText.setBounds(12, 8, 75, 15);
		memberPanel.add(nicknameText);

		JList memberList = new JList(new DefaultListModel());
		DefaultListModel model = (DefaultListModel) memberList.getModel();
		memberList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane memberListScroll = new JScrollPane(memberList);
		memberListScroll.setBounds(12, 33, 374, 265);
		memberPanel.add(memberListScroll);

		File file = new File("./src/Member.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));

		String str;
		while ((str = reader.readLine()) != null) {
			model.addElement(str);
			GuildMember.add(str);
		}
		reader.close();

		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8"));
		JButton addButton = new JButton("추가");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputText = memberInput.getText().trim();
				if (inputText == null || inputText.length() == 0)
					return;
				model.addElement(inputText);
				GuildMember.add(inputText);
				memberInput.setText("");
				//memberScroll.getVerticalScrollBar().setValue(memberScroll.getVerticalScrollBar().getMaximum());//자동 스크롤 동작 안함
			}
		});
		addButton.setBounds(226, 4, 76, 23);
		memberPanel.add(addButton);

		JButton deleteButton = new JButton("삭제");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (memberList.getSelectedIndex() >= 0)
					GuildMember.remove(memberList.getSelectedIndex());
					model.remove(memberList.getSelectedIndex());
			}
		});
		deleteButton.setBounds(310, 4, 76, 23);
		memberPanel.add(deleteButton);

		memberScroll = new JScrollPane();
		memberScroll.setBounds(215, 64, 57, 23);
		memberPanel.add(memberScroll);
	}
}
