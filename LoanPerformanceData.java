import java.io.FileNotFoundException;
import java.util.HashSet;

public class LoanPerformanceData
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Parser parser = new Parser("src/outputAcq.txt");
        HashSet<String> hashSet = parser.parseFile(1);
        Performance perf = new Performance();

        perf.initialParse("src/outputPerf.txt",1,hashSet);


    }

}
