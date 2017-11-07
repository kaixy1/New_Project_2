import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public  class Lexical_Analysis {
   public static ArrayList<String> saveTokens_Gram = new ArrayList<String>();//Creating arraylist;
    public static ArrayList<String> tokenType_Gram = new ArrayList<String>();//Creating arraylist;
    public static void main_Parsing(String file_input)
    {

        // A String test for input before changed to read it from a file
        //String input = "/*11+22-33tgege4455++++3.5E4+7.8{}{}{}{}****=ouhou);+*/((((yintcga(intx,floaty)y[int]== return ;";

        // Create tokens and print them
        ArrayList<Token> tokens = lex(file_input);

        // I commented because I don't need to print the tokens any more
        // by the way this is an advance for loop
        //for (Token token : tokens)
            //System.out.println(token);
    }

    public enum TokenType {

        // Token Regex
        DoubleComments("\\/\\*(.*)\\*\\/") , KEYWORD("else|if|int|return|void|while|float"), FLOAT("\\d+[.]\\d+([E|e][+-]?\\d+)?"), NUM("[0-9]+"), MIXSPECIAL("==|<=|>=|!="),SPECIAL("\\*|/|\\+|-|<|>|=|;|,|\\(|\\)|\\[|\\]|\\{|\\}"), ID("[a-zA-Z]+"), WHITESPACE("[ \t\f\r\n]+");

        public final String pattern;

        TokenType(String pattern) {
            this.pattern = pattern;
        }
    }

    public static class Token
    {
        public TokenType type;
        public String data;

        public Token(TokenType type, String data)
        {
            // this. read more about it
            //https://docs.oracle.com/javase/tutorial/java/javaOO/thiskey.html
            this.type = type;
            this.data = data;
        }

            // it supposed to be override the return ..
            // https://docs.oracle.com/javase/tutorial/java/IandI/override.html
        @Override
        public String toString() {
            return String.format("(%s %s)", type.name(), data);
        }
    }

    //  This array List saves token
    public static ArrayList<Token> lex(String input) {




        // The tokens to return
        ArrayList<Token> tokens = new ArrayList<Token>();

        // Starting the main logic for Lexical from here ... Let's GO
        StringBuffer tokenPatternsBuffer = new StringBuffer();
        for (TokenType tokenType : TokenType.values())
            tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
        Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

        // Starting to match tokens
        Matcher matcher = tokenPatterns.matcher(input);
        while (matcher.find())
        {
            // Double comments matching and skipping
            if (matcher.group(TokenType.DoubleComments.name()) != null)
            {
                if (matcher.group(TokenType.DoubleComments.name()) != null)
                continue;
            }
            // keyword matching
            else if (matcher.group(TokenType.KEYWORD.name()) != null) {
                tokens.add(new Token(TokenType.KEYWORD, matcher.group(TokenType.KEYWORD.name())));
                saveTokens_Gram.add(matcher.group(TokenType.KEYWORD.name()));
                tokenType_Gram.add("keyword");

                continue;
            }
            // float matching
            else if (matcher.group(TokenType.FLOAT.name()) != null) {
                tokens.add(new Token(TokenType.FLOAT, matcher.group(TokenType.FLOAT.name())));
                saveTokens_Gram.add(matcher.group(TokenType.FLOAT.name()));
                tokenType_Gram.add("float");

                continue;
            }
            // NUM matching
            else if (matcher.group(TokenType.NUM.name()) != null) {
                tokens.add(new Token(TokenType.NUM, matcher.group(TokenType.NUM.name())));
                saveTokens_Gram.add(matcher.group(TokenType.NUM.name()));
                tokenType_Gram.add("NUM");

                continue;
            }
            // Mix Special symbols matching
            else if (matcher.group(TokenType.MIXSPECIAL.name()) != null) {
                tokens.add(new Token(TokenType.MIXSPECIAL, matcher.group(TokenType.MIXSPECIAL.name())));
                saveTokens_Gram.add(matcher.group(TokenType.MIXSPECIAL.name()));
                tokenType_Gram.add("Special symbols");

                continue;
            }
            // Special symbols matching
            else if (matcher.group(TokenType.SPECIAL.name()) != null) {
                tokens.add(new Token(TokenType.SPECIAL, matcher.group(TokenType.SPECIAL.name())));
                saveTokens_Gram.add(matcher.group(TokenType.SPECIAL.name()));
                tokenType_Gram.add("Special symbols");

                continue;
            }
            // ID matching
            else if (matcher.group(TokenType.ID.name()) != null) {
                tokens.add(new Token(TokenType.ID, matcher.group(TokenType.ID.name())));
                saveTokens_Gram.add(matcher.group(TokenType.ID.name()));
                tokenType_Gram.add("ID");

                continue;
            }
            // WHITESPACE matching and skipping
            else if (matcher.group(TokenType.WHITESPACE.name()) != null) {
                continue;

            }


        }
        return tokens;
    }



}