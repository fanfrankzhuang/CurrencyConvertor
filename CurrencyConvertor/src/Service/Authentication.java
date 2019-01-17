package Service;

import org.json.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Authentication {
    public boolean checkLogin (String username, char[] password) throws FileNotFoundException {
        boolean flag = false;
        File file = new File("Login.txt");
        Scanner scanner = new Scanner(file);
        String loginStr = scanner.nextLine();
        JSONObject login = new JSONObject(loginStr);
        if (!login.has(username.toLowerCase())) {
            JOptionPane.showMessageDialog(null, "Username doesn't exist!");
        } else {
            if (login.getString(username.toLowerCase()).trim().equals(new String(password))) {
                flag = true;
            } else {
                JOptionPane.showMessageDialog(null, "Password error! Please try again.");
            }
        }
        return flag;
    }
}
