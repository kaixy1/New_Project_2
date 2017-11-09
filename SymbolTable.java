import java.util.HashMap;

public class SymbolTable
{

    static HashMap<String,String> symTable= new HashMap();

    public SymbolTable(){
    }

    public static HashMap<String, String> getSymTable()
    {
        return symTable;
    }

    public static void setSymTable(String key, String val)
    {
        //if (symTable.put(key, val) == null) System.out.println(" oo[s");;
        System.out.println("size of : " + symTable.size() );
           String  mystring =  symTable.put(key, val);

    }

    public static boolean isInSymTbl(String key)
    {

        return symTable.containsKey(key);
    }


}

