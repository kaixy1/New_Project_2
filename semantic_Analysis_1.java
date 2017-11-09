import java.util.HashMap;
import java.util.LinkedList;

public class semantic_Analysis_1 {
        // tokenType_saveToken_index
        public static int token_index = 0;
        public static int scope =  0;
        //SymbolTable symb = new SymbolTable();
        LinkedList mylink = new LinkedList();
        HashMap<String,String> symTable= new HashMap();

        public static String currentToken;

        private static Lexical_Analysis r ;
         //Lexical_Analysis r = new Lexical_Analysis();


        public static void getCurrentToken()
        {

            currentToken = Lexical_Analysis.tokenType_Gram.get(token_index);
            //System.out.println("meeee: " + currentToken);
            if(currentToken != "ID" && currentToken != "NUM" && currentToken != "float")
            {
                currentToken = Lexical_Analysis.saveTokens_Gram.get(token_index);
            }

        }


        public void S_1()
        {



            getCurrentToken();

            if (currentToken.equals("int") || currentToken.equals("void") || currentToken.equals("float")) {
                A();
            } else {
                REJECT_Method();
            }

        }

         public void A()
        {
            if (currentToken.equals("int") || currentToken.equals("void") || currentToken.equals("float")) {
                B();
                dLPrime();
            }

            else {
                REJECT_Method();
            }

        }

        public  void dLPrime()
        {
            if (currentToken.equals("int") || currentToken.equals("void") || currentToken.equals("float")) {
                B();
                dLPrime();
            } else if (currentToken.equals("$"))

            {
                System.out.println("\nACCEPT");
                return;
            } else
                {
                REJECT_Method();
            }

        }

        public  void B()
        {
            if (currentToken.equals("int") || currentToken.equals("void") || currentToken.equals("float"))
            {
                symTable.put("RetType",currentToken);

                mylink.add(scope, symTable);

                D();
                if (currentToken.equals("ID")) {
                    symTable.put("FuncName", currentToken);
                   // mylink.add(scope,symTable);

                    token_index++;
                    getCurrentToken();
                    dPrime();
                } else {
                    REJECT_Method();
                }
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void dPrime()
        {
            if (currentToken.equals(";"))
            {
                token_index++;
                getCurrentToken();
            }
            else if (currentToken.equals("["))
            {
                token_index++;
                getCurrentToken();
                if(currentToken.equals("NUM"))
                {
                    token_index++;
                    getCurrentToken();
                    if(currentToken.equals("]"))
                    {
                        token_index++;
                        getCurrentToken();
                        if(currentToken.equals(";"))
                        {
                            token_index++;
                            getCurrentToken();
                        }
                        else
                        {
                            REJECT_Method();
                        }

                    }
                    else
                    {
                        REJECT_Method();
                    }
                }
                else
                {
                    REJECT_Method();
                }
            }

            else if(currentToken.equals("("))
            {
                token_index++;
                getCurrentToken();
                F();
                if(currentToken.equals(")"))
                {
                    token_index++;
                    getCurrentToken();
                    compound_stmt();
                }
                else
                {
                    REJECT_Method();
                }
            }
        else
        {
            REJECT_Method();
        }

        }

        public static void D()
        {
            if(currentToken.equals("int"))
            {
                    token_index++;
                    getCurrentToken();
            }

            else if(currentToken.equals("void"))
            {
                token_index++;
                getCurrentToken();
            }
            else if(currentToken.equals("float"))
            {


                token_index++;
                getCurrentToken();
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void F()
        {
            if (currentToken.equals("int"))
            {
                token_index++;
                getCurrentToken();
                HPrime();
                G();

            }
            else if(currentToken.equals("float"))
            {
                token_index++;
                getCurrentToken();
                HPrime();
                G();

            }
            else if(currentToken.equals("void"))
            {
                token_index++;
                getCurrentToken();
                FPrime();
            }
            else
            {
                REJECT_Method();
            }


        }

        public static void FPrime()
        {
            if (currentToken.equals("ID"))
            {
                token_index++;
                getCurrentToken();
                HPrime();
                G();
            }
            else if(currentToken.equals(")"))
            {
                return;
            }
            else
            {
                REJECT_Method();
            }

        }
        public static void G()
        {
            if(currentToken.equals(","))
            {
                token_index++;
                getCurrentToken();
                H();
                G();
            }
            else if(currentToken.equals(")"))
            {
                return;
            }
            else
            {
                REJECT_Method();
            }
        }
        public static void H()
        {
            if (currentToken.equals("int") || currentToken.equals("void") || currentToken.equals("float"))
            {
                D();
                HPrime();
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void HPrime()
        {
            if(currentToken.equals("ID"))
            {
                token_index++;
                getCurrentToken();
                HPrime_Prime();
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void HPrime_Prime()
        {
            if(currentToken.equals("[")) {
                token_index++;
                getCurrentToken();
                if (currentToken.equals("]")) {
                    token_index++;
                    getCurrentToken();
                } else {
                    REJECT_Method();
                }
            }
            else if(currentToken.equals(",") || currentToken.equals(")"))
            {
                return;
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void compound_stmt()
        {
            if(currentToken.equals("{"))
            {
                token_index++;
                getCurrentToken();
                J();
                SL();
                if(currentToken.equals("}"))
                {
                    token_index++;
                    getCurrentToken();
                }
                else
                {
                    REJECT_Method();
                }
            }
            else
            {
                REJECT_Method();
            }

        }

        public static void J()
        {
            if (currentToken.equals("int") || currentToken.equals("void") || currentToken.equals("float"))
            {
                D();
                if(currentToken.equals("ID"))
                {
                    token_index++;
                    getCurrentToken();
                    JPrime();
                }
                else
                {
                    REJECT_Method();
                }
            }
            else if ( currentToken.equals("ID") ||  currentToken.equals("(") || currentToken.equals("NUM")
                    || currentToken.equals(";") ||  currentToken.equals("if") || currentToken.equals("{")
                    || currentToken.equals("while") ||  currentToken.equals("return") ||  currentToken.equals("}"))
            {
                return;
            }

            else
            {
                REJECT_Method();
            }
        }

        public static void JPrime()
        {
            if(currentToken.equals(";"))
            {
                token_index++;
                getCurrentToken();
                J();
            }
            else if(currentToken.equals("["))
            {
                token_index++;
                getCurrentToken();
                if(currentToken.equals("NUM"))
                {
                    token_index++;
                    getCurrentToken();
                    if(currentToken.equals("]"))
                    {
                        token_index++;
                        getCurrentToken();
                        if(currentToken.equals(";"))
                        {
                            token_index++;
                            getCurrentToken();
                            J();
                        }
                        else
                        {
                            REJECT_Method();
                        }
                    }
                    else
                    {
                        REJECT_Method();
                    }
                }
                else
                {
                    REJECT_Method();
                }
            }
            else if(currentToken.equals("ID") || currentToken.equals("(") || currentToken.equals("NUM")
                    || currentToken.equals(";") || currentToken.equals("if") || currentToken.equals("{")
                    || currentToken.equals("while") || currentToken.equals("return") || currentToken.equals("}"))
            {
                return;
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void SL()
        {
            if(currentToken.equals("ID") || currentToken.equals("(") || currentToken.equals("NUM")
                    || currentToken.equals(";") || currentToken.equals("if") || currentToken.equals("{")
                    || currentToken.equals("while") || currentToken.equals("return") || currentToken.equals("float"))
            {
                statement();
                SL();
            }
            else if(currentToken.equals("}"))
            {
                return;
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void statement()
        {
            if(currentToken.equals("ID") || currentToken.equals("(") || currentToken.equals("NUM")
                   || currentToken.equals("float") || currentToken.equals(";"))
            {
                expression_stmt();
            }
            else if(currentToken.equals("{") )
            {
                compound_stmt();
            }
            else if(currentToken.equals("if"))
            {
                selection_stmt();
            }
            else if(currentToken.equals("while"))
            {
                iteration_stmt();
            }
            else if(currentToken.equals("return"))
            {
                return_stmt();
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void expression_stmt()
        {
            if(currentToken.equals("ID") || currentToken.equals("(") || currentToken.equals("NUM")
                    || currentToken.equals("float"))
            {
                expression();
                if(currentToken.equals(";"))
                {
                    token_index++;
                    getCurrentToken();
                }
                else
                {
                    REJECT_Method();
                }
            }
            else if(currentToken.equals(";"))
            {
                token_index++;
                getCurrentToken();
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void selection_stmt()
        {
            if(currentToken.equals("if"))
            {
                token_index++;
                getCurrentToken();
                if(currentToken.equals("("))
                {
                    token_index++;
                    getCurrentToken();
                    expression();
                    if(currentToken.equals(")"))
                    {
                        token_index++;
                        getCurrentToken();
                        statement();
                        ST();
                    }
                    else
                    {
                        REJECT_Method();
                    }
                }
                else
                {
                    REJECT_Method();
                }
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void ST()
        {
            if(currentToken.equals("else"))
            {
                token_index++;
                getCurrentToken();
                statement();
            }
            else if(currentToken.equals("ID") || currentToken.equals("(") || currentToken.equals("NUM")
                    || currentToken.equals(";") || currentToken.equals("if") || currentToken.equals("{")
                    || currentToken.equals("while") || currentToken.equals("return") || currentToken.equals("}")
                    || currentToken.equals("else"))
            {
                return;
            }
            else
            {
                REJECT_Method();
            }

        }
        public static void iteration_stmt()
        {
            if (currentToken.equals("while"))
            {
                token_index++;
                getCurrentToken();
                if(currentToken.equals("("))
                {
                    token_index++;
                    getCurrentToken();
                    expression();
                    if(currentToken.equals(")"))
                    {
                        token_index++;
                        getCurrentToken();
                        statement();
                    }
                    else
                    {
                        REJECT_Method();
                    }
                }
                else
                {
                    REJECT_Method();
                }
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void return_stmt()
        {
            if(currentToken.equals("return"))
            {
                token_index++;
                getCurrentToken();
                expression_stmt();
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void expression()
        {
            if(currentToken.equals("ID"))
            {
                token_index++;
                getCurrentToken();
                expressionPrime();
            }
            else if(currentToken.equals("("))
            {
                token_index++;
                getCurrentToken();
                expression();
                if(currentToken.equals(")"))
                {
                    token_index++;
                    getCurrentToken();
                    termPrime();
                    AEPrime();
                    SE();
                }
                else
                {
                    REJECT_Method();
                }
            }
            else if(currentToken.equals("NUM"))
            {
                token_index++;
                getCurrentToken();
                termPrime();
                AEPrime();
                SE();
            }
            else if(currentToken.equals("float"))
            {
                token_index++;
                getCurrentToken();
                termPrime();
                AEPrime();
                SE();
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void expressionPrime()
        {
            if(currentToken.equals("[") || currentToken.equals("=") || currentToken.equals("/")
                    || currentToken.equals("*") || currentToken.equals("+") || currentToken.equals("-")
                    || currentToken.equals("==") || currentToken.equals("<=") || currentToken.equals(">=")
                    || currentToken.equals("!=") || currentToken.equals(">")
                    || currentToken.equals("<")  )
            {
                varPrime();
                expressionPrime_Prime();
            }
            else if(currentToken.equals("("))
        {
            token_index++;
            getCurrentToken();
            args();
            if(currentToken.equals(")"))
            {
                token_index++;
                getCurrentToken();
                termPrime();
                AEPrime();
                SE();
            }
        } else if (currentToken.equals(";") || currentToken.equals(")") || currentToken.equals("]")||
                    currentToken.equals(","))
            {
                return;
            }
        else
        {
            REJECT_Method();
        }
        }

        public static void expressionPrime_Prime()
        {
            if(currentToken.equals("="))
            {
                token_index++;
                getCurrentToken();
                expression();
            }
            else if(currentToken.equals("/") || currentToken.equals("*")
                    || currentToken.equals("+") || currentToken.equals("-")
                   || currentToken.equals("<") || currentToken.equals(">") || currentToken.equals("==")
                    || currentToken.equals("!=")|| currentToken.equals("<=")
                    || currentToken.equals(">="))
            {
                termPrime();
                AEPrime();
                SE();
            }else if (currentToken.equals(";") || currentToken.equals(")") || currentToken.equals("]")|| currentToken.equals(","))
            {
                return;
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void varPrime()
        {
            if(currentToken.equals("["))
            {
                token_index++;
                getCurrentToken();
                expression();
                if(currentToken.equals("]"))
                {
                    token_index++;
                    getCurrentToken();
                }
                else
                {
                    REJECT_Method();
                }
            }
            else if(currentToken.equals("=") || currentToken.equals("/") || currentToken.equals("*")
            || currentToken.equals("+") || currentToken.equals("-")  || currentToken.equals(">") ||
                    currentToken.equals("<")|| currentToken.equals("==") || currentToken.equals("!=") ||
                    currentToken.equals(";") ||currentToken.equals(")") ||currentToken.equals("]") ||
            currentToken.equals(",") || currentToken.equals("<=") || currentToken.equals(">="))
            {
                return;
            }
            else
            {
                REJECT_Method();
            }

        }

        public static void SE()
        {
            if(currentToken.equals("<=") || currentToken.equals(">=") || currentToken.equals("==")
                    || currentToken.equals("!=") || currentToken.equals("<") ||currentToken.equals(">"))
            {
                relop();
                additive_expression();
            }
            else if(currentToken.equals(";") || currentToken.equals(")") || currentToken.equals("]")
                    || currentToken.equals(","))
            {
                return;
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void relop()
        {
            if(currentToken.equals("<="))
            {
                token_index++;
                getCurrentToken();
            }
            else if(currentToken.equals(">="))
            {
                token_index++;
                getCurrentToken();
            }
            else if(currentToken.equals("=="))
            {
                token_index++;
                getCurrentToken();
            }
            else if(currentToken.equals("!="))
            {
                token_index++;
                getCurrentToken();
            }
            else if(currentToken.equals(">"))
            {
                token_index++;
                getCurrentToken();
            }
            else if(currentToken.equals("<"))
            {
                token_index++;
                getCurrentToken();
            }
            else
            {
                REJECT_Method();
            }

        }

        public static void additive_expression()
        {
            if(currentToken.equals("(") || currentToken.equals("ID") || currentToken.equals("NUM")
                    || currentToken.equals("float"))
            {
                term();
                AEPrime();
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void AEPrime()
        {
            if(currentToken.equals("+") || currentToken.equals("-"))
            {
                addop();
                term();
                AEPrime();
            }
            else if(currentToken.equals("<") || currentToken.equals(">") || currentToken.equals("==")
                    || currentToken.equals("!=") || currentToken.equals(";") || currentToken.equals(")")
                    || currentToken.equals("]") || currentToken.equals(",") || currentToken.equals(">=") || currentToken.equals("<=") )
            {
                return;
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void addop()
        {
            if(currentToken.equals("+"))
            {
                token_index++;
                getCurrentToken();
            }
            else if(currentToken.equals("-"))
            {
                token_index++;
                getCurrentToken();
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void term()
        {
            if(currentToken.equals("(") || currentToken.equals("ID") || currentToken.equals("NUM")
                    || currentToken.equals("float"))
            {
                factor();
                termPrime();
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void termPrime()
        {
            if(currentToken.equals("*") || currentToken.equals("/"))
            {
                mulop();
                factor();
                termPrime();
            }
            else if( currentToken.equals("<=") || currentToken.equals(">=") || currentToken.equals("==")
                    || currentToken.equals("!=") || currentToken.equals("+") || currentToken.equals("-")
                    || currentToken.equals("<") || currentToken.equals(">")
                    || currentToken.equals(";") || currentToken.equals(")") || currentToken.equals("]")
                    || currentToken.equals(","))
            {
                return;
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void mulop()
        {
            if(currentToken.equals("*"))
            {
                token_index++;
                getCurrentToken();
            }
            else if(currentToken.equals("/"))
            {
                token_index++;
                getCurrentToken();
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void factor()
        {
            if(currentToken.equals("("))
            {
                token_index++;
                getCurrentToken();
                expression();
                if(currentToken.equals(")"))
                {
                    token_index++;
                    getCurrentToken();
                }
                else
                {
                    REJECT_Method();
                }
            }
            else if(currentToken.equals("ID"))
            {
                token_index++;
                getCurrentToken();
                factorPrime();
            }
            else if(currentToken.equals("NUM"))
            {
                token_index++;
                getCurrentToken();
            }
            else if(currentToken.equals("float"))
            {
                token_index++;
                getCurrentToken();
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void factorPrime()
        {
            if(currentToken.equals("["))
            {
                varPrime();
            }
            else if(currentToken.equals("("))
            {
                token_index++;
                getCurrentToken();
                args();
                if(currentToken.equals(")"))
                {
                    token_index++;
                    getCurrentToken();
                }
                else
                {
                    REJECT_Method();
                }
            }
            else if(currentToken.equals("/") || currentToken.equals("*") || currentToken.equals("+")
                    || currentToken.equals("-") || currentToken.equals(";") || currentToken.equals(")")
                    || currentToken.equals("]") || currentToken.equals(",") || currentToken.equals("<")
                    || currentToken.equals("<=") || currentToken.equals(">=")
                    || currentToken.equals(">") || currentToken.equals("==") || currentToken.equals("!="))
            {
                return;
            }
            else
            {
                REJECT_Method();
            }

        }
        public static void args()
        {
            if(currentToken.equals("ID") || currentToken.equals("(") || currentToken.equals("NUM"))
            {
                args_list();
            }
            else if(currentToken.equals(")"))
            {
                return;
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void args_list()
        {
            if(currentToken.equals("ID") || currentToken.equals("(") || currentToken.equals("NUM")
                    || currentToken.equals("float"))
            {
                expression();
                AL();
            }
            else
            {
                REJECT_Method();
            }
        }

        public static void AL()
        {
            if(currentToken.equals(","))
            {
                token_index++;
                getCurrentToken();
                expression();
                AL();
            }
            else if(currentToken.equals(")"))
            {
                return;
            }
            else
            {
                REJECT_Method();

            }
        }

        public static void REJECT_Method()
        {
            System.out.println("REJECT");
            System.exit(1);
        }

    }
