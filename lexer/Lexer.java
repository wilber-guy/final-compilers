package assign6.lexer ;

import java.io.* ;
import java.util.* ;
import assign6.lexer.* ;

public class Lexer {

    public int line = 1 ;
    private char peek = ' ' ;
    private Hashtable words = new Hashtable() ;
    private BufferedReader br;
    public Lexer () {

	    String filePath = new File("").getAbsolutePath();
        try
        {
            br=new BufferedReader(new InputStreamReader(new FileInputStream(filePath+"/input.txt")));   //Creation of File Reader object
        }
        catch(Exception e){}

        reserve(new Word("true",  Tag.BOOLEAN)) ;
        reserve(new Word("false", Tag.BOOLEAN)) ;
        reserve(new Word("if", Tag.IF));
		reserve(new Word("else", Tag.ELSE));
		reserve(new Word("for", Tag.FOR));
        reserve(Type.Int   );
        reserve(Type.Char  );
        reserve(Type.Bool  );  
        reserve(Type.Float );
    }

    void reserve (Word w) {
        words.put(w.lexeme, w);
    }

    void readch() throws IOException { 
	try{
            peek = (char)br.read();
        }
        catch(Exception e){}
    }

    public Token scan() throws IOException {

        for ( ; ; readch()) {

            if (peek == ' ' || peek == '\t') 
                continue ;
            else if (peek == '\n') 
                line = line + 1 ;
            else 
                break ;
        }

        if (Character.isDigit(peek)) {

            int v = 0 ;

            do {

                v = 10 * v + Character.digit(peek, 10) ;
                readch() ;

            } while (Character.isDigit(peek)) ;
			
			// Added for floats
			if(peek == '.') {
				readch();

				float f = 0.0f;		

		        do {
					
					
		            f =  f + ((float)Character.digit(peek, 10) / 10) ;
		            readch() ;

		        } while (Character.isDigit(peek)) ;

				f = f + v;
				return new Float(f);
			}
			
			
            return new Int(v) ;
        }

        if (Character.isLetter(peek)) {

            StringBuffer b = new StringBuffer() ;

            do {

                b.append(peek) ;
                readch();

            } while (Character.isLetterOrDigit(peek)) ;

            String s = b.toString() ;
            Word w = (Word) words.get(s) ;

			
            if (w != null)
                return w ;
            
            w = new Word(s, Tag.ID) ;

            words.put(s, w) ;
			

            return w ;
        }

        Token t = new Token(peek) ; 
        peek = ' ' ;
        return t ;
    }
}
