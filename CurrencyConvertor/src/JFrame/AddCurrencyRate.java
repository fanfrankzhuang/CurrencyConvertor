package JFrame;

import Service.RateEditor;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AddCurrencyRate extends JFrame implements ActionListener {
    JLabel curLabel, rateLabel, description;
    JTextField curTF, rateTF;
    JTextArea area;
    JButton showRate, addRate, exit;
    AddCurrencyRate() {
        setTitle("Add Currency Rate");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        curLabel = new JLabel("Currency:");
        curLabel.setBounds(35, 35, 170, 20);
        rateLabel = new JLabel("Rate:");
        rateLabel.setBounds(215, 35, 170, 20);
        curTF = new JTextField();
        curTF.setBounds(35, 65, 170, 30);
        rateTF = new JTextField();
        rateTF.setBounds(215, 65, 170, 30);
        addRate = new JButton("Add");
        addRate.setBounds(35, 105, 80, 30);
        description = new JLabel("The rates of Currencies against US Dollar are as follow:");
        description.setBounds(35, 145, 350, 20);
        area = new JTextArea();
        area.setBounds(35, 175, 350, 400);
        showRate = new JButton("Show");
        showRate.setBounds(35, 585, 80, 30);
        exit = new JButton("Exit");
        exit.setBounds(130,585,80,30);
        addRate.addActionListener(this);
        showRate.addActionListener(this);
        exit.addActionListener(this);
        /*curTF.addActionListener(this);
        rateTF.addActionListener(this);*/
        add(curLabel);
        add(rateLabel);
        add(curTF);
        add(rateTF);
        add(description);
        add(addRate);
        add(area);
        add(showRate);
        add(exit);
        setSize(460, 680);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
        setLayout(null);
        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // Add new Currency Rate
        if (e.getSource() == addRate) {
            try {
                String rate = rateTF.getText();
                String currency = curTF.getText();
                if(Double.parseDouble(rate) <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter the correct rate!");
                }
                if (currency != null && !"".equals(currency))  {
                    RateEditor rateEditor = new RateEditor();
                    rateEditor.addRate(currency, rate);
                    JOptionPane.showMessageDialog(null, "Add Rate Success!");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the currency and rate first!");
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Something wrong! Please check the currency and rate!");
            }

        } else if (e.getSource() == showRate) { //show Currency Rate already exit in the file.
            File rateFile = new File("CurrencyRate.txt");
            Scanner scanner;
            area.setText("");
            try {
                scanner = new Scanner(rateFile);
                String allRate = scanner.nextLine().replace("{", "").replace("}", "");
                String[] rates = allRate.split(",");
                for (int i = 0; i < rates.length; i++) {
                    area.append(rates[i].replace("\"", " ").toUpperCase() + "\n");
                }
            } catch (FileNotFoundException e1) {
                JOptionPane.showMessageDialog(null, "System Error!");
            }
        } else if (e.getSource() == exit) {
            setVisible(false);
            dispose();
        }
    }

    /*public static void main(String[] args) {
        AddCurrencyRate addCurrencyRate = new AddCurrencyRate();
    }*/
}
