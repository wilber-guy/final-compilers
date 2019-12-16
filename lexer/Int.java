package assign6.lexer ;

public class Int extends Token {
    
    public final int value ;
    
    public Int(int v) { 
        
        super(Tag.INT) ; 
        value = v ; 
    }

    public String toString() { 
    
        return "" + value ; 
    }
}
