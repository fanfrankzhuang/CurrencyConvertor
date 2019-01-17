package JFrame;

import Service.RateEditor;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class EditCurrencyRate extends JFrame implements ActionListener {
    JLabel description;
    JComboBox<List> currencyCB;
    JTextField rateTF;
    JButton submit, exit;
    EditCurrencyRate() {
        RateEditor rateEditor = new RateEditor();
        List<String> currencyList = null;
        try {
            currencyList = rateEditor.getCurrencyList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setTitle("Edit Currency Rate");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        description = new JLabel("The rate should be against US dollar");
        description.setBounds(35, 35, 350, 20);
        currencyCB = new JComboBox(currencyList.toArray());
        currencyCB.setBounds(35, 70, 150, 30);
        rateTF = new JTextField(rateEditor.getRateByCurrency(currencyCB.getSelectedItem().toString()));
        rateTF.setBounds(195, 70, 150, 30);
        submit = new JButton("Submit");
        submit.setBounds(90, 135, 80, 30);
        exit = new JButton("Exit");
        exit.setBounds(210, 135, 80, 30);
        add(description);
        add(currencyCB);
        add(rateTF);
        add(submit);
        add(exit);
        currencyCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // 只处理选中的状态
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String rate = rateEditor.getRateByCurrency(currencyCB.getSelectedItem().toString());
                    rateTF.setText(rate);
                }
            }
        });
        submit.addActionListener(this);
        exit.addActionListener(this);
        setSize(380, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
        setLayout(null);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            RateEditor rateEditor = new RateEditor();
            rateEditor.updateRate(currencyCB.getSelectedItem().toString(), rateTF.getText());
            JOptionPane.showMessageDialog(null,"Update Success!");
        } else if (e.getSource() == exit) {
            setVisible(false);
            dispose();
        }
    }

    /*public static void main(String[] args) {
        EditCurrencyRate editCurrencyRate = new EditCurrencyRate();
    }*/
}
