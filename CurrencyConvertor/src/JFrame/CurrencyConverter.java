package JFrame;

import Service.Convertor;
import Service.RateEditor;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;

public class CurrencyConverter extends JFrame implements ItemListener, ActionListener
{ 
    //initialized all the GUI components
    JPanel panel;
    JComboBox<String> convertFrom, convertTo;
    JTextField txtFrom, txtTo;
    JTextField fName, lName, age, occupation, country;
    JButton compute, exit;
    JButton pri;
    JLabel from, to;
    JLabel fn, ln, ag, oc, co;
    JLabel lblFrom, lblTo;
    JPanel mainPanel = new JPanel();
    JFrame frameToPrint;
    double input = 0;
    double result = 0;

    public CurrencyConverter() {

        //add all the GUI components
        super("Currency Converter: Red Devils"); 
        setSize(500,600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setLayout(null);
        setBackground(Color.lightGray);

        panel = new JPanel();
        panel.setBounds(10,10, 460, 420);
        panel.setLayout(null);
        add(panel);
        fn = new JLabel("FIRST NAME:");
        fn.setBounds(80,40,250,30);
        panel.add(fn);
        fName = new JTextField(15);
        fName.setBounds(165,40,200,30);
        panel.add(fName);
        
        ln = new JLabel("LAST NAME:");
        ln.setBounds(80,75,250,30);
        panel.add(ln);
        lName = new JTextField(15);
        lName.setBounds(165,75,200,30);
        panel.add(lName);
        
        ag = new JLabel("AGE:");
        ag.setBounds(80,110,250,30);
        panel.add(ag);
        age = new JTextField(15);
        age.setBounds(165,110,200,30);
        panel.add(age);
        
        oc = new JLabel("OCCUPATION:");
        oc.setBounds(80,145,250,30);
        panel.add(oc);
        occupation = new JTextField(15);
        occupation.setBounds(165,145,200,30);
        panel.add(occupation);
        
        co = new JLabel("COUNTRY:");
        co.setBounds(80,180,250,30);
        panel.add(co);
        country = new JTextField(15);
        country.setBounds(165,180,200,30);
        panel.add(country);

        lblFrom = new JLabel("FROM:");
        lblFrom.setBounds(65,215,250,30);
        lblFrom.setForeground(Color.BLUE);
        panel.add(lblFrom);

        RateEditor rateEditor = new RateEditor();
        List<String> currencyList = null;
        try {
            currencyList = rateEditor.getCurrencyList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        convertFrom = new JComboBox(currencyList.toArray());
        convertFrom.setBounds(65,240,150,30);
        panel.add(convertFrom);
        lblTo = new JLabel("TO:");
        lblTo.setBounds(230,215,250,30);
        lblTo.setForeground(Color.BLUE);
        panel.add(lblTo);
        convertTo = new JComboBox(currencyList.toArray());
        convertTo.setBounds(230,240,150,30);
        panel.add(convertTo);

        from = new JLabel("ENTER AMOUNT TO CONVERT:");
        from.setBounds(80,275,300,30);
        panel.add(from);
        txtFrom = new JTextField(15);
        txtFrom.setBounds(80,300,300,30);
        panel.add(txtFrom);

        compute = new JButton("COMPUTE");
        compute.setBounds(120,440,100,30);
        add(compute);

        to = new JLabel("TOTAL AMOUNT CONVERTED:");
        to.setBounds(80,335,300,30);
        panel.add(to);
        txtTo = new JTextField(15);
        txtTo.setBounds(80,360,300,30);
        txtTo.setEditable(false);
        txtTo.setForeground(Color.BLUE);
        panel.add(txtTo);

        exit = new JButton("EXIT");
        exit.setBounds(230,440,100,30);
        add(exit);

        panel.revalidate();
        panel.repaint();

        pri = new JButton("CONVERT PDF");
        pri.setBounds(155, 480, 150, 30);
        add(pri);
        pri.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printComponent(panel);
                JOptionPane.showMessageDialog(null, "Done!");
            }
        });


        //register all the GUI components who will listen or invoke the methods
        convertFrom.addItemListener(this);
        convertTo.addItemListener(this);
        compute.addActionListener(this);
        txtFrom.addActionListener(this);
        exit.addActionListener(this);
        
        //this will close the applet window if the user clicked the close or exit button
        /*addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) { 
                    exit(); 
                } 
            });*/
    }
    //the exit method
    public void exit(){ 
        setVisible(false);
        dispose();
        //System.exit(0);
    }

    public void printComponent(Component component) {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName(" Print Component ");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        pj.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX()+75, pf.getImageableY()+50);
                component.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        if (pj.printDialog() == false)
            return;

        try {
            pj.print();
        } catch (PrinterException ex) {
            // handle exception
        }
    }

    //show or make the applet window frame visible 
    public static void main(String args[]) { 
        CurrencyConverter w = new CurrencyConverter(); 
        w.setVisible(true); 
    }

    public void actionPerformed(ActionEvent e) {

        String msg = txtFrom.getText();
        if(e.getSource()==compute)
        {
            input = Double.parseDouble(txtFrom.getText());
        	Convertor convertor = new Convertor();
            try {
                result = convertor.convert(convertFrom.getSelectedItem().toString(), convertTo.getSelectedItem().toString(), input);
            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null,"System error!");
            }
            NumberFormat formatter = new DecimalFormat("#0.00");
        	result = Double.parseDouble(formatter.format(result));
        	txtTo.setText(""+ result);
        }
        //this will asked the user to exit or not
        else
        {
            dispose();
        }
    }
    public void itemStateChanged(ItemEvent e)
    {
    }
}