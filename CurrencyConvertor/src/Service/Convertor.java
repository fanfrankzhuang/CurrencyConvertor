package Service;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Convertor {
	
	
	public double convert(String from, String to, double amount) throws IOException {
        //String json = "{\"chinese yuan\":6.3,\"canadian dollar\":1.2}";
		File rateFile = new File("CurrencyRate.txt");
        Scanner scanner = new Scanner(rateFile);
		String json = scanner.nextLine();
		JSONObject jsonObject = new JSONObject(json);
		double result;
		
		double rate1 = Double.parseDouble(jsonObject.getString(from.toLowerCase()));
		double rate2 = Double.parseDouble(jsonObject.getString(to.toLowerCase()));
		
		result = amount/rate1 * rate2;
		return result;
	}
}
