package JFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener {
    JButton addCurrency, editRate, compute, exit;
    public HomePage() {
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addCurrency = new JButton("Add New Currency");
        addCurrency.setBounds(125,75,150, 50);
        editRate = new JButton("Edit Currency Rate");
        editRate.setBounds(125,175,150,50);
        compute = new JButton("Convert Computing");
        compute.setBounds(125,275,150,50);
        exit = new JButton("Exit");
        exit.setBounds(125,375,150,50);
        addCurrency.addActionListener(this);
        editRate.addActionListener(this);
        compute.addActionListener(this);
        exit.addActionListener(this);
        add(addCurrency);
        add(editRate);
        add(compute);
        add(exit);
        setSize(400,550);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
        setLayout(null);
        setVisible(true);

    }
    /*public static void main(String args[]) {
        new HomePage();
    }*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == compute) {
            CurrencyConverter w = new CurrencyConverter();
            w.setVisible(true);
        } else if (e.getSource() == exit){
            //this will display a confirm dialog box
            int choice = JOptionPane.showConfirmDialog(null,"Do you really want to quit?","Exit",JOptionPane.YES_NO_OPTION);
            if(choice==0) {
                dispose();
                System.exit(0);
            }
        } else if (e.getSource() == addCurrency) {
            AddCurrencyRate addCurrencyRate = new AddCurrencyRate();
        } else if (e.getSource() == editRate) {
            EditCurrencyRate editCurrencyRate = new EditCurrencyRate();
        }
    }


}
