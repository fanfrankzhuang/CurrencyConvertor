package Service;

import org.json.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class RateEditor {
    private static File rateFile = new File("CurrencyRate.txt");

    public void addRate(String currency, String rate) {
        Scanner scanner;
        try {
            scanner = new Scanner(rateFile);
            String json = scanner.nextLine();
            JSONObject jsonObject = new JSONObject(json);
            if (currency != null && !"".equals(currency))  {
                if (jsonObject.has(currency.toLowerCase())) {
                    JOptionPane.showMessageDialog(null, "This kind of currency has already existed!");
                } else {
                    jsonObject.put(currency.toLowerCase(), rate);
                    PrintWriter printWriter = new PrintWriter("CurrencyRate.txt");
                    printWriter.println(jsonObject.toString());
                    printWriter.close();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter the currency and rate first!");
            }
        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "System Error!");
        }
    }

    public List<String> getCurrencyList() throws FileNotFoundException {
        List<String> list = new ArrayList();
        Scanner scanner = new Scanner(rateFile);
        String json = scanner.nextLine();
        JSONObject jsonObject = new JSONObject(json);
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            list.add(keys.next().toUpperCase());
        }
        Collections.sort(list);
        return list;
    }

    public String getRateByCurrency(String currency) {
        String rate = "";
        try {
            Scanner scanner = new Scanner(rateFile);
            String json = scanner.nextLine();
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(currency.toLowerCase())) {
                rate = jsonObject.getString(currency.toLowerCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rate;
    }

    public void updateRate(String currency, String rate) {
        try {
            Scanner scanner = new Scanner(rateFile);
            String json = scanner.nextLine();
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(currency.toLowerCase())) {
                jsonObject.put(currency.toLowerCase(), rate);
                PrintWriter printWriter = new PrintWriter("CurrencyRate.txt");
                printWriter.println(jsonObject.toString());
                printWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*    public static void main(String[] args) throws FileNotFoundException {
        RateEditor rateEditor = new RateEditor();
        rateEditor.updateRate("chinese yuan","7.0");
    }*/
}
