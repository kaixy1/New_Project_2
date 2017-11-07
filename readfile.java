import java.io.File;
import java.util.Scanner;
import java.lang.String;


public class readfile
{

    private Scanner x;

    public void openFile(String filename)
    {
        try
        {
            // read from the file by scanner
            // I got this from the java book p678

            x = new Scanner(new File(filename));
        }
        catch (Exception e)
        {
            System.out.println("could not find file");
        }
    }

    public void readFile()
    {
        
        while(x.hasNext())
        {
            Lexical_Analysis.main_Parsing(x.nextLine());


        }
        Lexical_Analysis.saveTokens_Gram.add("$");
        Lexical_Analysis.tokenType_Gram.add("$");

    }

    public void closeFile()
    {
        x.close();
    }
}
