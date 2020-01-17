import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;


public class Performance {
	
	public static double sumUPB(HashMap<String, Double[]> m) {
		double sum = 0.0;
		
		for (Double[] v : m.values()) {
			sum += v[1];
		}
		return sum;
	}
	
	public static double findWAC(HashMap<String, Double[]> m) {
		double wac = 0.0;
		
		for (Double[] v : m.values()) {
			double interest = v[0] / 100 + 1;
			wac += v[0] * interest;
		}
		wac = wac / sumUPB(m);
		return wac;
	}
	
	public static void findPool(HashMap<String, Double[]> curr) {
		HashMap<String, Double[]> finalLoans = new HashMap<>();
		
		
	}
	
	public static void joinLoanNums(HashSet<String> acqSet, HashMap<String, Double[]> m) {
		
	}
	
	public static void initialParse(String fileName, int poolNum) {
		HashMap<String, Double[]> map = new HashMap<>();
		
		try {
			RandomAccessFile file = new RandomAccessFile(fileName,"r");
			String str;
			String pattern = "^(\\d+)\\|(\\d+\\.\\d+)\\|(\\d+\\.\\d+)\\|(\\d*)$";
			Pattern p = Pattern.compile(pattern);
			
			while ((str = file.readLine()) != null) {
				Matcher m = p.matcher(str);
				if (m.find()) {
					String name = m.group(1);
					String status = m.group(4);
					Double[] temp = new Double[2];
					temp[0] = Double.parseDouble(m.group(2));
					temp[1] = Double.parseDouble(m.group(3));
					
					if (!status.equals("")) {
						map.remove(name);
					} else {
						map.put(name,temp);
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.forEach((k,v) -> System.out.println(k + " " + v[0] + " " + v[1]));
	}

	public static void main(String[] args) {
		initialParse("src/tst1",1);
		

	}

}
