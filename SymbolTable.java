import java.util.HashMap;

public class SymbolTable
{

    HashMap<String,String> symTable;

    public SymbolTable(){
        HashMap<String, String> symTable = new HashMap(10);
        symTable.put("retType", "Test");
    }

    public HashMap<String, String> getSymTable()
    {
        return symTable;
    }

    public void setSymTable(String key, String val)
    {
        if (key == null) throw new IllegalArgumentException("calls put() with null key");

        symTable.put(key, val);
    }

    public boolean isInSymTbl(String key)
    {

        return symTable.containsKey(key);
    }


}

