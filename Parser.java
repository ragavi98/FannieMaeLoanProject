import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
public class Parser
{
private String textFile;
private HashSet<String> hashSet;

public Parser(String textFile)
{
    this.textFile = textFile;
    hashSet = new HashSet<String>();
}
public HashSet<String> parseFile(int poolNum) throws FileNotFoundException
{
    //HashSet
    File file = new File(textFile);
    Scanner sc= new Scanner(file);
    sc.nextLine();
    String dwellType = "";
    String term = null;
    int ltv = -1;
    int dti = -1;
    String pType = null;

    if(poolNum ==1 || poolNum ==2 || poolNum==5 || poolNum==6)
    {
        term = "360";

    }
    else if (poolNum ==3 || poolNum == 4)
    {
        term = "180";
    }
    if(poolNum ==1)
    {
        dti = 40;
        ltv = 85;
        pType = "SF";
    }
    else if(poolNum ==2)
    {
        dti = 45;
        ltv = 80;
        pType = "SF";
    }
    else if(poolNum ==3)
    {
        dti = 50;
        ltv = 86;
        pType= "SF";


    }
    else if(poolNum == 4)
    {
        dti = 35;
        ltv = 80;
        pType = "CO";
    }
    else if(poolNum == 5)
    {
        dti = 44;
        ltv = 87;
        pType = "SF";
    }
    else if(poolNum == 6)
    {
        dti = 43;
        ltv = 75;
        pType = "CO";
    }
    //sc.useDelimiter("\\|");
    //System.out.println(sc.nextLine());
    //System.out.println(sc.nextLine());
    int count = 1;
    while(sc.hasNextLine())
    {
        String line = sc.nextLine();
        if(line.length()==0)
        {
            continue;
        }
        String[] array = line.split("\\|");
        boolean possible = true;
        String loanID = array[0];

            if(!array[2].equals(term))
            {
                possible = false;
            }


            if(array[3].length()> 0 && Integer.parseInt(array[3])>ltv)
            {
                possible =false;
            }
            if(array[4].length()>0 && Integer.parseInt(array[4])>dti)
            {
                possible = false;
            }


            if(!array[5].equals(pType))
            {
                possible = false;
            }
            if(possible)
            {
               hashSet.add(loanID);
               System.out.println(loanID);
            }


    }
System.out.println(hashSet.size());
return hashSet;

}

}
