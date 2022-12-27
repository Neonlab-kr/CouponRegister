import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;

public class CouponControlClass {
	private BufferedWriter writer = null;
	private BufferedReader reader = null;
	Vector<String> GuildMember = new Vector<String>();
	DefaultListModel model;

	void MemberFileSave() {
		try {
			for (String nickname : GuildMember)
				writer.write(nickname + "\n");
			writer.flush();
			writer.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	void MemberFileRead(JList memberList) throws IOException {
		File file = new File("./Member.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));

		String str;
		while ((str = reader.readLine()) != null) {
			model = (DefaultListModel) memberList.getModel();
			model.addElement(str);
			GuildMember.add(str);
		}
		reader.close();
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8"));
	}

	void CouponRegister(JTextField couponInput) {
		try {
			for (String nickname : GuildMember) {
				Desktop.getDesktop().browse(new URI("https://gift.supermembers.net/coupon/?code=" + couponInput.getText()
						+ "&scode=zio&name=" + nickname + "&ok=1"));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		couponInput.setText("");
	}

	void AddMember(JTextField memberInput, JList memberList) {
		String inputText = memberInput.getText().trim();
		if (inputText == null || inputText.length() == 0)
			return;
		model.addElement(inputText);
		GuildMember.add(inputText);
		memberInput.setText("");
		// memberScroll.getVerticalScrollBar().setValue(memberScroll.getVerticalScrollBar().getMaximum());//자동 스크롤 동작 안함
	}

	void DeleteMember(JList memberList) {
		if (memberList.getSelectedIndex() >= 0) {
			GuildMember.remove(memberList.getSelectedIndex());
			model.remove(memberList.getSelectedIndex());
		}
	}
}
