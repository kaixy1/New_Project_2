public class Main {

    public static void main(String[] args) {


        readfile r = new readfile();
        //semantic_Analysis s = new semantic_Analysis();
        semantic_Analysis_1 s1 = new semantic_Analysis_1();

        r.openFile(args[0]);


        r.readFile();


        r.closeFile();


        //s.S();
        // Go to Grammar
        s1.S_1();







    }
}
