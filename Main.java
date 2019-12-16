package assign6 ;

import assign6.unparser.*;
import assign6.lexer.* ;
import assign6.parser.* ;
import assign6.typechecker.* ;
import assign6.intercode.* ;

public class Main {

    public static void main (String[] args) {

        Lexer lexer = new Lexer() ;
        Parser parser = new Parser(lexer) ;
		TypeChecker typechecker = new TypeChecker(parser) ;
		InterCode intercode = new InterCode(parser) ;		
        Unparser unparser = new Unparser(parser) ;
	
    }
}
