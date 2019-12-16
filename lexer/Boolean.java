package assign6.lexer ;

public class Boolean extends Token {
    
    public final boolean value ;
    
    public Boolean(boolean v) { 
        
        super(Tag.BOOLEAN) ; 
        value = v ; 
    }

    public String toString() { 
    
        return "" + value ; 
    }
}
