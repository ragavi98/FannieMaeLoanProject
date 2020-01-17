import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;


public class Performance {
    public Performance()
    {

    }

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

    public void findPool(HashMap<String, Double[]> curr) {
        HashMap<String, Double[]> finalLoans = new HashMap<>();
        for (String s : curr.keySet()) {
            finalLoans.put(s, curr.get(s));
            if (sumUPB(finalLoans) > 40000000) {
                finalLoans.remove(s);
            }
        }
        finalLoans.forEach((k,v) -> System.out.println(k + " " + v[0] + " " + v[1]));
        System.out.println("Total UPB: " + sumUPB(finalLoans));
        System.out.println("WAC: " + findWAC(finalLoans));
    }

    private HashMap<String,Double[]> joinLoanNums(HashSet<String> acqSet, HashMap<String, Double[]> m) {
        HashMap<String,Double[]> output = new HashMap<String,Double[]>();

        for(String key:m.keySet())
        {
            if(m.containsKey(key))
            {
               output.put(key,m.get(key));
            }
        }
        return output;

    }

    public void initialParse(String fileName, int poolNum, HashSet<String> hashSet) {
        HashMap<String, Double[]> map = new HashMap<>();

        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            String pattern = "^(\\d+)\\|(\\d+\\.\\d+)\\|(\\d+\\.\\d+)\\|(\\d*)$";
            Pattern p = Pattern.compile(pattern);

            while ((str = br.readLine()) != null) {
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
        // map.forEach((k,v) -> System.out.println(k + " " + v[0] + " " + v[1]));
        HashMap<String, Double[]> hashMap = joinLoanNums(hashSet,map);
        HashMap<String, Double[]> hashMap2 = joinLoanNums(hashSet,map);
        ArrayList<String> rand = new ArrayList<String>();

        for(String key: hashMap.keySet())
        {
            rand.add(key);
        }
        Collections.sort(rand);


        //for (String s : rand) {
        //    hashMap2.put(s, hashMap.get(s));
        //}
        this.findPool(hashMap2);
    }

    public static void main(String[] args) {
        //initialParse("src/perf1.txt",1);


    }

}
