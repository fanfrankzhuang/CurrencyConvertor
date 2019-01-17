package JFrame;

import Service.Authentication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class Login extends JFrame {

	public static void main(String[] args) {
		Login frameTabel = new Login();
	}

	JButton login = new JButton("LOGIN");
	JPanel panel = new JPanel();
	JTextField user = new JTextField(15);
	JPasswordField password = new JPasswordField(15);
	JLabel us, pa,label, second, third;

	Login() {
		super("Login Authentication");
		setSize(400, 290);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setLocation(500, 285);
		setBackground(Color.lightGray);
		panel.setLayout(null);

		label = new JLabel("WELCOME TO CURRENCY CONVERTER");
		label.setBounds(85, 5, 300, 30);
		label.setForeground(Color.BLUE);
		add(label);

		second = new JLabel("LOGIN PAGE");
		second.setBounds(155, 25, 150, 30);
		second.setForeground(Color.BLUE);
		add(second);

		us = new JLabel("USERNAME:");
		us.setBounds(73, 80, 150, 25);
		add(us);
		user.setBounds(153, 80, 150, 25);

		pa = new JLabel("PASSWORD:");
		pa.setBounds(73, 120, 150, 25);
		add(pa);
		password.setBounds(153, 120, 150, 25);
		login.setBounds(150, 160, 80, 30);

		third = new JLabel("P.S: YOU MUST LOGIN TO GO TO FORM PAGE !");
		third.setBounds(75, 220, 400, 30);
		third.setForeground(Color.RED);
		add(third);

		panel.add(login);
		panel.add(user);
		panel.add(password);

		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionlogin();
	}

	public void actionlogin() {
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String uname = user.getText();
				char[] paswd = password.getPassword();
				Authentication authentication = new Authentication();
				try {
					if (authentication.checkLogin(uname, paswd)) {
						HomePage hp = new HomePage();
						hp.setVisible(true);
						dispose();
					} else {
						user.setText("");
						password.setText("");
						user.requestFocus();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}
		});
	}

}
