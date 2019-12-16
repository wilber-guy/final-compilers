package assign6.lexer ;

public class Token {

    public int tag ;
    
    public Token (int t) {

        tag = t ;
    }

    public String toString() {

        return "" + (char)tag ;
    }
}
